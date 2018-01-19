package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.hdm.weblog.shared.Person;

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

		String html = String.format("<!doctype html>%n" + "<html>%n" + "<head>%n"
				+ "<link rel=stylesheet type=\"text/css\" href=\"css/style.css\">%n" + "</head>%n%n" + "<body>%n"
				+ "<a class=\"blogActionButton\" href=\"ShowBlog\">Home</a> "
				+ "<a class=\"blogActionButton\" href=\"NewBlogEntry\">New</a> "
				+ "<a class=\"blogActionButton\" href=\"About\">About</a>%n" + "<a href=\"http://hdm-stuttgart.de\">"
				+ "   <img style=\"float: right;\"  src=\"https://www.hdm-stuttgart.de/stylesheets_bilder/logo_web.png\" alt=\"HdM Logo\">"
				+ "</a>%n%n<br><br>");
		out.println(html);

		out.println("<h1>meinBlog <br> <small>neuen Eintrag erstellen</small></h1>");

		html = String.format("<form action=\"ShowBlog\" method=\"post\">%n" + "<fieldset>%n"
				+ "<legend>Eintrag</legend>" + "  <label for=\"titel\">Titel:</label>"
				+ "  <input name=\"titel\" type=\"text\" id=\"titel\" required/><br/>"
				+ "  <label for=\"untertitel\">Untertitel:</label>"
				+ "  <input name=\"untertitel\" type=\"text\" id=\"untertitel\" required/><br/>"
				+ "  <label for=\"inhalt\">Inhalt:</label>"
				+ "  <textarea name=\"text\" type=\"text\" id=\"inhalt\" required rows=\"10\" cols=\"60\"></textarea>"
				+ "</fieldset>");
		out.println(html);

		HttpSession session = request.getSession(true);
		Person autor = (Person) session.getAttribute("Autor");
		if (autor == null) {
			html = String.format("<fieldset>" + "<legend> Autor </legend>" + "  <label for=\"vorname\">Vorname:</label>"
					+ "  <input name=\"vorname\" type=\"text\" id=\"vorname\" required/><br/>"
					+ "  <label for=\"nachname\">Nachname:</label>"
					+ "  <input name=\"name\" type=\"text\" id=\"nachname\" required/><br/>"
					+ "  <label for=\"email\">Email:</label>"
					+ "  <input name=\"email\" type=\"email\" id=\"email\" required/>" + "</fieldset>");
			out.println(html);
		}

		out.println("<input type=\"Submit\" name=\"newEntry\" value=\"Speichern\"/>");
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
