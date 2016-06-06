package de.hdm.weblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hdm.weblog.BlogAdministration;
import de.hdm.weblog.Blogeintrag;
import de.hdm.weblog.Kommentar;

/**
 * Demonstrator f�r die Interaktion mit dem <code>BankServer</code>.
 * <p>
 * <b>Anwendungsfall:</b> Erstellen eines Reports.
 * 
 * @see de.hdm.bankProject.BankServer
 * 
 * @author Thies, Rathke
 */
public class EntryCreation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BlogAdministration verwaltung = new BlogAdministration();
		Blogeintrag be = null;

		if (request.getParameter("title") != "" && request.getParameter("subtitle") != ""
				&& request.getParameter("text") != "") {
			
			verwaltung.createBlogeintrag(request.getParameter("text"), request.getParameter("title"),
					request.getParameter("subtitle"));
		}

		Vector<Blogeintrag> blogs = verwaltung.findAll();
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		out.println(docType);
		out.println("<html><body>");

		if (blogs.size()>0) {
			be = blogs.get(blogs.size()-1);
			out.println("<h2>" + be.getTitel() + "</h2>\n"
					+ "<h3>" + be.getUntertitel() +  "</h3>\n"
					+ be.getInhalt());
			
			out.println("<ul>");
			for (Kommentar kom : be.getKommentare()) {
				out.println("<li>" + kom.getInhalt() + "</li>");
			}
			out.println("</ul>");
		} else {
			out.println("<h2>Nothing here");
		}			
		
		out.println("</body></html>");
	}

}