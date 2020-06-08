package http.servlet.base;

import http.servlet.Servlet;

import http.servlet.ServletRequest;
import http.servlet.ServletResponse;

public abstract class HttpServlet implements Servlet {
	public void init() {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	};

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	};

	protected void doHead(HttpServletRequest request, HttpServletResponse response) {
	};

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
	};

	public void service(HttpServletRequest request, HttpServletResponse response) {
		// 取出request中的 method
		String method = request.getMethod();

		if ("get".equalsIgnoreCase(method)) {

			doGet(request, response);
		} else if ("post".equalsIgnoreCase(method)) {

			doPost(request, response);
		} else if ("head".equalsIgnoreCase(method)) {

			doHead(request, response);
		} else if ("delete".equalsIgnoreCase(method)) {

			doDelete(request, response);
		}

	}

	public void service(ServletRequest request, ServletResponse response) {
		this.service((HttpServletRequest) request, (HttpServletResponse) response);
	}
}
