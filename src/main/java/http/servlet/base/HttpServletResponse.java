package http.servlet.base;

import http.servlet.ServletResponse;

public interface HttpServletResponse extends ServletResponse {
	public void sendRedirect();
}
