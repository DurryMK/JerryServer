package server.process;

import http.servlet.ServletRequest;
import http.servlet.ServletResponse;

public interface Processor {
	public void process(ServletRequest request,ServletResponse response);
}
