package Test;

import bean.impl.Session;

public class Test {
	public static void main(String[] args) {
		Session s = new Session();
		s.setAttribute("asd", 123);
		s.removeAttribute("asd");
		System.out.println(s.getAttribute("asd"));;
	}
}

class Servlet {
	private static Servlet instance = new Servlet();

	private Servlet() {

	}

	public static Servlet getInstance() {
		return instance;
	}

	public void services() {
		System.out.println("do something");
	}
}

class Client extends Thread {
	private Servlet servlet;

	public Client(Servlet servlet) {
		this.servlet = servlet;
	}

	public void run() {
		servlet.services();
		// System.out.println("do something");
	}
}
