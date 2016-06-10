package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.Blogeintrag;

/**
 * Servlet implementation class CreateBlogeintrag
 */
@WebServlet("/NewComment")
public class NewComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Date datum;

	public NewComment() {
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
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType);
		out.println("<html><body>");

		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			BlogAdministration adm = new BlogAdministration();
			Blogeintrag be = adm.findBlogeintragById(id);
			out.println("<h2>" + be.getTitel() + "</h2>\n" + "<h3>" + be.getUntertitel() + "</h3>\n" + be.getInhalt());
			out.println("von " + be.getAutor());
		}

		out.println("<h2>Kommentar anlegen</h2>"
				+ "<form action=\"ShowBlog\" method=\"post\">"
				+ "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">"
				+ "<table>"
				+ "<tr>"
				+ "<td><b>Kommentar</b></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><label>Text:</label></td>"
				+ "<td><textarea id=\"text\" name=\"text\" cols=\"35\" rows=\"4\"></textarea></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><b>Autor</b></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><label>Name:</label></td>"
				+ "<td><input type=\"text\" name=\"name\" size=\"30\" maxlength=\"30\"></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><label>Vorname:</label></td>"
				+ "<td><input type=\"text\" name=\"vorname\" size=\"30\" maxlength=\"30\"></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><label>Email:</label></td>"
				+ "<td><input type=\"text\" name=\"email\" size=\"30\" maxlength=\"30\"></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td></td>"
				+ "<td><input type=\"Submit\" name=\"newComment\" value=\"Speichern\"></td>"
				+ "</tr>"
				+ "</table>"
				+ "</form>");
		
		out.println("</body></html>");

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
