package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.BlogAdministrationImpl;
import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Kommentar;
import de.hdm.weblog.Person;

/**
 * Servlet implementation class CreateBlogeintrag
 */
@WebServlet("/BlogEntryAction")
public class BlogEntryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Date datum;

	public BlogEntryAction() {
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

		out.println("<h1>meinBlog <br/></h1>");

		int id = 0;
		if (request.getParameter("action") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			BlogAdministration adm = new BlogAdministrationImpl();
			Blogeintrag be = adm.findBlogeintragById(id);

			html = String.format("<h3> %s <br>%n <small style=\"margin-left: 1em;\"> %s </small></h3>%n"
					+ "<p>von %s%n</p>" + "<p>%n%s%n</p>%n", be.getTitel(), be.getUntertitel(), be.getAutor(),
					be.getInhalt());
			out.println(html);

			for (Kommentar kom : be.getKommentare()) {
				out.println("<ul><li>" + kom.getInhalt() + "</li></ul>");
			}
			out.println("<hr>");

		}

		html = "<form action=\"ShowBlog\" method=\"post\">  <input type=\"hidden\" name=\"id\" value=\"" + id + "\">";
		out.println(html);
				
		if (request.getParameter("action").equals("NewComment")) {
			html = "<h3>Kommentar anlegen</h3> "
					+ "<textarea name=\"text\" type=\"text\" id=\"text\" required cols=\"60\" rows=\"6\"></textarea> <br/> <br/>"
					+ "<button type=\"Submit\" name=\"newComment\" value=\"Speichern\">Speichern</button>  <br/> <br/>";
		} else if (request.getParameter("action").equals("DeleteBlogEntry")) {
			html = "<h3>Blogeintrag löschen</h3> <br/>"
					+ "<button type=\"Submit\" name=\"deleteBlogEntry\" value=\"Loeschen\">Löschen</button> <br/> <br/>"; 
		}
		
		out.println(html);

		HttpSession session = request.getSession(true);
		Person autor = (Person) session.getAttribute("Autor");
		if (autor == null) {
			html = String.format("<fieldset>" + "    <legend>Autor</legend>"
					+ "    <label for=\"text\">Vorname</label> <input name=\"vorname\" type=\"text\" id=\"vorname\" required/> <br/>"
					+ "    <label for=\"text\">Name</label> <input name=\"name\" type=\"text\" id=\"name\" required/> <br/>"
					+ "    <label for=\"email\">Email</label> <input name=\"email\" type=\"email\" id=\"email\" required/>"
					+ "  </fieldset>  <br/> <br/>");
			out.println(html);
		}


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
