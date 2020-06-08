package bean;

import java.util.HashMap;
import java.util.Map;

import http.servlet.Servlet;

public class ServletContext implements Context {

	private Map<String, Servlet> servlets = new HashMap<String, Servlet>();

	private static ServletContext servletContext;

	public static synchronized ServletContext getInstance() {
		if (servletContext == null)
			servletContext = new ServletContext();
		return servletContext;
	}

	public Map<String, Servlet> getServlets() {
		return this.servlets;
	}

	public void addServlet(String name,Servlet servlet) {
		this.servlets.put(name, servlet);
	}

}
