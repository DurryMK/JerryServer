package server.process;

import java.net.URL;


import java.net.URLClassLoader;
import java.util.Map;

import bean.ServletContext;
import http.servlet.Servlet;
import http.servlet.ServletRequest;
import http.servlet.ServletResponse;
import http.servlet.base.HttpServlet;
import http.servlet.base.HttpServletRequest;
import http.servlet.base.HttpServletResponse;
import server.Constants;

public class DynamicProcess implements Processor {
	public void process(ServletRequest request, ServletResponse response) {
		String uri = ((HttpServletRequest) request).getRequestURI();// 资源地址
		URL[] urls = new URL[1];
		try {
			urls[0] = new URL("file", null, Constants.JERRYSERVER_PATH);// 项目路径
			URLClassLoader ucl = new URLClassLoader(urls);// 类加载器扫描urls数组中的路径
			Class clazz = ucl.loadClass(getClassName(uri));// 加载指定的类
			String name = clazz.getName();
			Map<String, Servlet> servlets = ServletContext.getInstance().getServlets();
			Servlet servlet = null;
			// 第一次访问
			if (servlets.get(name) == null) {
				Object o = clazz.newInstance();
				if (o != null && o instanceof Servlet) {
					// 实例化调用
					servlet = (Servlet) o;
					servlet.init();
					// 存入ServletContext
					ServletContext.getInstance().addServlet(name, servlet);
				}
			} else {
				// 已经访问过
				servlet = servlets.get(name);
			}
			((HttpServlet) servlet).service((HttpServletRequest) request, (HttpServletResponse) response);
		} catch (Exception e) {
			e.printStackTrace();
			response.outWrite(500, Constants.p500);
		}
	}
	/**
	 * 获取请求中的类名
	 */
	private String getClassName(String uri) {
		return (uri.substring(uri.indexOf("/") + 1, uri.lastIndexOf(".")).replace("/", "."));
	}

}
