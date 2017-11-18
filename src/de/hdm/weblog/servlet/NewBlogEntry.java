package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateBlogeintrag
 */
@WebServlet("/NewBlogEntry")
public class NewBlogEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public NewBlogEntry() {
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

		String html = String.format("<!doctype html>%n" + "<html>%n" + "<head>%n" + "</head>%n%n" + "<body>%n"
				+ "<a style=\"border: 1px solid black; background-color: yellow; padding: 2px;\" href=\"ShowBlog\">Home</a> <a href=\"NewBlogEntry\">New</a> <a href=\"About\">About</a>%n"
				+ "<a href=\"http://hdm-stuttgart.de\">"
				+ "   <img style=\"float: right;\"  src=\"https://www.hdm-stuttgart.de/stylesheets_bilder/logo_web.png\" alt=\"HdM Logo\">"
				+ "</a>%n%n<br><br>");
		out.println(html);

		out.println("<h1>meinBlog <br> <small>neuen Eintrag erstellen</small></h1>");

		html = String.format("<form action=\"ShowBlog\" method=\"post\">%n" + "<fieldset>%n"
				+ "<legend>Eintrag</legend>" + "  <label for=\"email\">Titel</label>"
				+ "  <input name=\"titel\" type=\"text\" id=\"titel\" required/><br/>"
				+ "  <label for=\"text\">Untertitel</label>"
				+ "  <input name=\"untertitel\" type=\"text\" id=\"untertitel\" required/><br/>"
				+ "  <label for=\"text\">Inhalt</label>"
				+ "  <textarea name=\"text\" type=\"text\" id=\"inhalt\" required rows=\"10\" cols=\"60\"></textarea>"
				+ "</fieldset>");
		out.println(html);

		html = String.format("<fieldset>" + "<legend> Autor </legend>" + "  <label for=\"text\">Vorname</label>"
				+ "  <input name=\"vorname\" type=\"text\" id=\"vorname\" required><br/>"
				+ "  <label for=\"text\">Nachname</label>"
				+ "  <input name=\"name\" type=\"text\" id=\"nachname\" required><br/>"
				+ "  <label for=\"email\">Email</label>"
				+ "  <input name=\"email\" type=\"email\" id=\"email\" required>" + "</fieldset>");
		out.println(html);

		out.println("<input type=\"Submit\" name=\"newEntry\" value=\"Speichern\">");
		out.println("</form>");

		out.println("</body>");
		out.println("</html>");

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
