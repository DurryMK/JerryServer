package Test;

import java.text.SimpleDateFormat;

import java.util.Date;

import bean.Cookie;
import http.servlet.base.HttpServlet;
import http.servlet.base.HttpServletRequest;
import http.servlet.base.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private int count = 0;

	public HelloServlet() {
		super();
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result += sdf.format(new Date()) + ";Visit:" + (count++) + "\r\n";

		result += "获取到的cookies:\r\n";

		for (Cookie c : request.getCookies()) {
			result += c.getName() + "=" + c.getName()+"\r\n";
		}

		response.textWriter(result);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		super.service(request, response);
	}

}
