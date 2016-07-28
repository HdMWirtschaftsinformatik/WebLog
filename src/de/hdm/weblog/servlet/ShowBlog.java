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
import de.hdm.weblog.Kommentar;
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
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType);
		out.println("<html>");
		out.println("<head>");
		//Bootstrap min.css CDN
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
		//Bootstrap min.js CDN
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");
		out.println("</head>");
		
		
		
		out.println("<body>");

		for (Blogeintrag be : blogs) {
			out.println("<h2>" + be.getTitel() + "</h2>\n" + "<h3>" + be.getUntertitel() + "</h3>\n" + be.getInhalt());
			out.println("von " + be.getAutor());

			out.println("<ul>");
			for (Kommentar kom : be.getKommentare()) {
				out.println("<li>" + kom.getInhalt() + "</li>");
			}
			out.println("<form action=\"NewComment\" method=\"post\">");
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + be.getId() + "\">");
			out.println("<input type=\"Submit\" name=\"NewComment\" value=\"Kommentieren\">");
			out.println("</form>");
			out.println("</ul>");
			out.println("---------------------------------\n\n");
		}

		out.println("</body>");
		out.println("</html>");

		out.close();

	}

}