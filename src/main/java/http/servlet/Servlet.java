package http.servlet;

/**
 * 定义servlet的生命周期
 */
public interface Servlet {
	
	public void init();

	public void destroy();

	public void service(ServletRequest request, ServletResponse response);
}
