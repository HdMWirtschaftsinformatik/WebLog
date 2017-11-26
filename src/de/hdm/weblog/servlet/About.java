package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateBlogeintrag
 */
@WebServlet("/About")
public class About extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Date datum;

	public About() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String html = String.format("<!doctype html>%n" + "<html>%n" + "<head>%n"
				+ "<link rel=stylesheet type=\"text/css\" href=\"css/style.css\">%n" + "</head>%n%n" + "<body>%n"
				+ "<a class=\"blogActionButton\" href=\"ShowBlog\">Home</a> "
				+ "<a class=\"blogActionButton\" href=\"NewBlogEntry\">New</a> "
				+ "<a class=\"blogActionButton\" href=\"About\">About</a>%n"
				+ "<a href=\"http://hdm-stuttgart.de\">"
				+ "   <img style=\"float: right;\"  src=\"https://www.hdm-stuttgart.de/stylesheets_bilder/logo_web.png\" alt=\"HdM Logo\">"
				+ "</a>%n%n<br><br>");
		out.println(html);	
		
		html = String.format(
                "<h1>meinBlog <br/><small>About</small></h1>"
                + "<p><a href=\"https://github.com/HdMWirtschaftsinformatik/WebLog\">Github</a></p>"
                + "</body>"
                + "</html>");
		out.println(html);

		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
