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
import de.hdm.weblog.Kommentar;

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
	
		out.println("<div class=\"container-fluid\"><br><br>");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-md-6\">");
		out.println("<a href=\"ShowBlog\" class=\"btn btn-primary\">Zurück</a>");
		
		out.println("<div class=\"page-header\">"+
                "<h1>meinBlog <small>neuen Eintrag erstellen</small></h1>"+
            "</div>");
		

		out.println("<form action=\"NewComment\" method=\"post\">");
		
		out.println("<h3>Eintrag</h3>");
		
		 out.println("<div class=\"form-group\">"+
		    "<label for=\"email\">Titel</label>"+
		    "<input type=\"email\" class=\"form-control\" id=\"titel\">"+
		  "</div>");
		 
		 out.println("<div class=\"form-group\">"+
				    "<label for=\"text\">Untertitel</label>"+
				    "<input type=\"text\" class=\"form-control\" id=\"untertitel\">"+
				  "</div>");
		 
		 out.println("<div class=\"form-group\">"+
				    "<label for=\"text\">Inhalt</label>"+
				    "<textarea type=\"text\" class=\"form-control\" id=\"inhalt\"></textarea>"+
				  "</div>");
		 
		 out.println("<h3>Autor</h3>");
		 
		 out.println("<div class=\"form-group\">"+
				    "<label for=\"text\">Vorname</label>"+
				    "<input type=\"text\" class=\"form-control\" id=\"vorname\">"+
				  "</div>");
		 
		 out.println("<div class=\"form-group\">"+
				    "<label for=\"text\">Nachname</label>"+
				    "<input type=\"text\" class=\"form-control\" id=\"nachname\">"+
				  "</div>");
		 
		 out.println("<div class=\"form-group\">"+
				    "<label for=\"email\">Email</label>"+
				    "<input type=\"email\" class=\"form-control\" id=\"email\">"+
				  "</div>");

		 
		out.println("<button type=\"submit\" name=\"NewComment\" class=\"btn btn-default\">Erstellen</button>");
		out.println("</form>");
		
		

		
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
