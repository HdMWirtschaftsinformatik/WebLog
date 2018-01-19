package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.hdm.weblog.server.BlogAdministrationImpl;
import de.hdm.weblog.shared.BlogAdministration;
import de.hdm.weblog.shared.Blogeintrag;
import de.hdm.weblog.shared.Kommentar;
import de.hdm.weblog.shared.Person;

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

		HttpSession session = request.getSession(true);
		BlogAdministration adm = (BlogAdministration) session.getAttribute("BlogAdmin");
		Person autor = (Person) session.getAttribute("Autor");

		if (adm == null) {
			adm = new BlogAdministrationImpl();
			session.setAttribute("BlogAdmin", adm);
		}

		if (request.getParameter("email") != null) {
			autor = adm.findPersonByEmail((String) request.getAttribute("email"));
			if (autor == null) {
				autor = adm.createPerson(request.getParameter("name"), request.getParameter("vorname"),
						request.getParameter("email"));
			}
			session.setAttribute("Autor", autor);
		}

		if (autor != null) {
			if (request.getParameter("newEntry") != null) {
				adm.createBlogeintrag(request.getParameter("text"), autor, request.getParameter("titel"),
						request.getParameter("untertitel"));
			} else if (request.getParameter("newComment") != null) {
				Blogeintrag be = adm.findBlogeintragById(Integer.parseInt(request.getParameter("id")));
				adm.createKommentar(request.getParameter("text"), autor, be);
			} else if (request.getParameter("deleteBlogEntry") != null) {
				Blogeintrag be = adm.findBlogeintragById(Integer.parseInt(request.getParameter("id")));
				if (be.getAutor().getId() == autor.getId()) {
					adm.deleteBlogeintrag(be);
				}
			}
		}

		Vector<Blogeintrag> blogs = adm.findAllLatestFirst();

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

		out.println("<h1>meinBlog <small>ein WI-Projekt</small></h1>");

		for (Blogeintrag be : blogs) {

			html = String.format("<form action=\"BlogEntryAction\" method=\"post\">%n"
					+ "<h3><br>%n <small style=\"margin-left: 1em;\"> %s </small></h3>%n" + "<p>von %s%n</p>"
					+ "<p>%n%s%n</p>%n", be.getTitel(), be.getUntertitel(), be.getAutor(), be.getInhalt());
			out.println(html);

			for (Kommentar kom : be.getKommentare()) {
				out.println("<ul><li>" + kom.getInhalt() + "</li></ul>");
			}

			html = "<input type=\"hidden\" name=\"id\" value=\"" + be.getId() + "\"/>"
					+ "<button type=\"submit\" name=\"action\" value=\"NewComment\">kommentieren</button> "
					+ "<button type=\"submit\" name=\"action\" value=\"DeleteBlogEntry\">löschen</button>\n<hr/>\n"
					+ "</form>";
			out.println(html);
		}

		out.println("</body>");
		out.println("</html>");

		out.close();

	}

}