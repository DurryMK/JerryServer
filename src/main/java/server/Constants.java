package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import bean.ServerConfig;
import http.servlet.Servlet;

public class Constants {
	/**加载server.xml路径*/
	public final static String SERVERCONFIG = "conf/server.xml";

	/**web.xml路径*/
	public final static String WEBCONFIG = "conf/web.xml";

	/**日志对象*/
	public final static Logger logger = Logger.getLogger(Constants.class);
	
	/**服务器路径*/
	public final static String JERRYSERVER_PATH = System.getProperty("user.dir");
	
	/**返回所有响应类型*/
	private static Map<String ,String> types = null;
	
	public static Map<String ,String> getTypes(){
		if(types!=null){
			return types;
		}
		types = Tools.parserWebXml();
		return types;
	}
	
	/**返回Server.xml的配置项*/
	public static ServerConfig config = null;
	
	public static ServerConfig getServerCongfig() throws Exception{
		if(config!=null){
			return config;
		}
		config = Tools.parserServerXml();
		return config;
	}
	
	public static List<Map<String ,Servlet >> servletConfig = new ArrayList<Map<String,Servlet>>();
	
	/** 响应页面路径*/
	public static final String p500 = "ROOT/500.html";
	public static final String p404 = "ROOT/404.html";
}
