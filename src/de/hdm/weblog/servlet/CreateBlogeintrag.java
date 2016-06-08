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
import de.hdm.weblog.Person;

/**
 * Servlet implementation class CreateBlogeintrag
 */
@WebServlet("/CreateBlogeintrag")
public class CreateBlogeintrag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Date datum;

	public CreateBlogeintrag() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		if (request.getParameter("absenden") != null) {
		
		BlogAdministration adm = new BlogAdministration();
		Person person;
		Blogeintrag blogeintrag;

	

		
		person = adm.createPerson(request.getParameter("name"), request.getParameter("vorname"),
				request.getParameter("email"));
		blogeintrag = adm.createBlogeintrag(request.getParameter("text"), person, request.getParameter("titel"),
				request.getParameter("untertitel"));

		// Test: ID des angelegten Blogeintrags anzeigen

		PrintWriter writer = response.getWriter();

		writer.println("<html>");
		writer.println("<head><title>meinBlog</title></head>");
		writer.println("<body>");
		writer.println("<p><h4>Der Blogeintrag wurde unter der ID: " + blogeintrag.getId()
				+ " erfolgreich angelegt.</h4></p>");

		writer.println("<body>");
		writer.println("</html>");

		writer.close();
		}
		
		else {
			
			PrintWriter writer = response.getWriter();

			writer.println("<html>");
			writer.println("<head><title>meinBlog</title></head>");
			writer.println("<body>");
			writer.println("<p><h4>Diese Seite ist nicht mehr gültig</h4></p>");

			writer.println("<body>");
			writer.println("</html>");

			writer.close();
			
			
			
			
			
		}
		
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
