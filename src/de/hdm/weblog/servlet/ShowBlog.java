package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Blogeintrag.Kommentar;
import de.hdm.weblog.Person;

@WebServlet("/ShowBlog")
public class ShowBlog extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Person person = null;
		BlogAdministration adm = new BlogAdministration();
		if (request.getParameter("newEntry") != null) {

			person = adm.createPerson(request.getParameter("name"), request.getParameter("vorname"),
					request.getParameter("email"));
			adm.createBlogeintrag(request.getParameter("text"), person, request.getParameter("titel"),
					request.getParameter("untertitel"));
		} else if (request.getParameter("newComment") != null) {
			person = adm.createPerson(request.getParameter("name"), request.getParameter("vorname"),
					request.getParameter("email"));
			Blogeintrag be = adm.findBlogeintragById(Integer.parseInt(request.getParameter("id")));
			adm.createKommentar(request.getParameter("text"), person, be);

		}

		Vector<Blogeintrag> blogs = adm.findAllLatestFirst();

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

		out.println("<h1>meinBlog <small>ein WI-Projekt</small></h1>");

		for (Blogeintrag be : blogs) {

			html = String.format("<h3> %s <br>%n <small style=\"margin-left: 1em;\"> %s </small></h3>%n"
					+ "<p>von %s%n</p>" + "<p>%n%s%n</p>%n", be.getTitel(), be.getUntertitel(), be.getAutor(),
					be.getInhalt());
			out.println(html);

			for (Kommentar kom : be.getKommentare()) {
				out.println("<ul><li>" + kom.getInhalt() + "</li></ul>");
			}

			html = String.format("<form action=\"NewComment\" method=\"post\"> %n"
					+ "<input type=\"hidden\" name=\"id\" value=\"" + be.getId() + "\"> %n"
					+ "<button type=\"submit\" name=\"NewComment\">kommentieren</button> %n" + "</form><br><hr>");
			out.println(html);
		}

		out.println("</body>");
		out.println("</html>");

		out.close();

	}

}