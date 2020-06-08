package http.servlet;


import java.util.Map;

import bean.Cookie;
import bean.impl.Session;

public interface ServletRequest {
	public String getRealPath();

	public Object getAttribute(String key);

	public void setAttribute(String key,Object value);

	public String getParameter(String key);

	public Map<String, String> getParameterMap();

	public void parse();

	public String getServerName();
	
	public Session getSession();

	public int getServerPort();
	
	public Cookie[] getCookies();
}
