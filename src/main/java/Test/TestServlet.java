package Test;

import bean.Cookie;
import http.servlet.base.HttpServlet;
import http.servlet.base.HttpServletRequest;
import http.servlet.base.HttpServletResponse;

public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie c = new Cookie("TestCookie", "[Test]");
		response.addCookie(c);
		String result = "������һ��Cookie";
		response.textWriter(result);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.service(request, response);
	}

}
