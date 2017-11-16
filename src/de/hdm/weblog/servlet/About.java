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
		out.println("<li>"+
                 "<a href=\"#\">Home</a>"+
                "</li>"+
               "<li>"+
                    "<a href=\"NewBlogEntry\">New</a>"+
                "</li>"+
               "<li class=\"active\">"+
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
                "<h1>meinBlog <small>About</small></h1>"+
            "</div>");
		
		
		out.println("<p>Github: <a href=\"https://github.com/ft027/Software_Werkzeuge\">klick</a></p>");
		

		
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
