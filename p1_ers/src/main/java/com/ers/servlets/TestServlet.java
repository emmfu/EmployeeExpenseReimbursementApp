package com.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 3826942023234396003L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Project 1 Servlet started init() invoke!");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TestServlet doGet() invoked!");
		System.out.println(req.getAttribute("request-state")); //will be null if req was not forwarded by another servlet
		System.out.println(this.getServletConfig().getInitParameter("someParam")); //returns someValue if redirected - see? if from TestServlet
		System.out.println(this.getServletContext().getInitParameter("contextParam")); //return contextValue from the servletContext in our web.xml
		resp.getWriter().write("<h1>/test endpoint works!!</h1>");
	}
	
	@Override
	public void destroy() {
		System.out.println("TestServelt shutting down!");
	}
	
}
