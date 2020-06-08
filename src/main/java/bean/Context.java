package bean;

import java.util.Map;

import http.servlet.Servlet;

public interface Context {
	
	public Map<String , Servlet> getServlets();
	
	public void addServlet(String name,Servlet servlet);
}
