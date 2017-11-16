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
		System.out.println(request.getParameter("newComment"));
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
		//CSS
		out.println("<link rel=\"stylesheet\" href=\"/IT2Weblog/css/style.css\" type=\"text/css\">");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<div class=\"container-fluid\"><br><br>");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-md-6\">");
		out.println("<ul class=\"nav nav-pills\">");
		out.println("<li class=\"active\">"+
                 "<a href=\"ShowBlog\">Home</a>"+
                "</li>"+
               "<li>"+
                    "<a href=\"NewBlogEntry\">New</a>"+
                "</li>"+
               "<li>"+
                   "<a href=\"About\">About</a>"+
                "</li>"+

                "<a class=\"navbar-brand navbar-right\" href=\"http://hdm-stuttgart.de\"><img src=\"http://www2.pic-upload.de/img/30253146/Logo_Graustufen_1.gif\" alt=\"Dispute Bills\">"+
                "</a>"+

                "<br><br>");
		
		out.println("</ul>");
		//End of md-12
		out.println("</div>");
		//End of Row
		out.println("</div>");
		
		
		
		
		
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-md-6\">");
		
		
		out.println("<div class=\"page-header\">"+
                "<h1>meinBlog <small>ein WI-Projekt</small></h1>"+
            "</div>");
		
		
		
		
		
		for (Blogeintrag be : blogs) {
			
			//Titel
			out.println("<h3>" + be.getTitel());
			//Subtitle
			out.println("<small>" + be.getUntertitel() + "</small></h3>");
			//Content
			out.println("<p>"+be.getInhalt()+"</p>");
			//Author
			out.println("<p>von " + be.getAutor()+"</p>");

			for (Kommentar kom : be.getKommentare()) {
				out.println("<ul><li>" + kom.getInhalt() + "</li></ul>");
			}
			out.println("<form action=\"NewComment\" method=\"post\">");
			out.println("<input type=\"hidden\" name=\"id\" value=\"" + be.getId() + "\">");
			out.println("<button type=\"submit\" name=\"NewComment\" class=\"btn btn-primary\">kommentieren</button>");
			out.println("</form>");
			out.println("<br>");
			out.println("<hr>");
		}
		
		
		//End of md-12
		out.println("</div>");
		//End of Row
		out.println("</div>");
		//End of Container
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

		out.close();

	}

}