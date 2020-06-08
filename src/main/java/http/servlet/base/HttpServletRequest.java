package http.servlet.base;

import http.servlet.ServletRequest;

public interface HttpServletRequest extends ServletRequest {

	public String getMethod();

	public String getRequestURI();

}
