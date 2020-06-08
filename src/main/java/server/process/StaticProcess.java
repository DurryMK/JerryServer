package server.process;

import http.servlet.ServletRequest;
import http.servlet.ServletResponse;
import server.JeHttpServletResponse;

public class StaticProcess implements Processor {
	public void process(ServletRequest request, ServletResponse response) {
		((JeHttpServletResponse)response).sendRedirect();
	}
}
