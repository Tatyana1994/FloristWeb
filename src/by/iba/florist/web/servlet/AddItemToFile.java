package by.iba.florist.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import by.iba.florist.web.entity.Item;
import by.iba.florist.web.parser.*;


/**
 * Servlet implementation class AddItemToFile
 */
public class AddItemToFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemToFile() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name    = request.getParameter("name");
		Double price   = Double.parseDouble(request.getParameter("price"));
		String comment = request.getParameter("comment");		
		
		System.out.println("Save to File...");
		System.out.println("Name: " + name + "; \nPrice: " + price + "; \nDescription: " + comment);		
		
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = response.getWriter();
		out.println("<p>Item " + name + " is added to Catalog!</p>");		
		
		JaxbParser parser = new JaxbParser();
		
		Item item = new Item(name, price, comment);	
				
		File catalog = new File("catalog");		
		try {
			parser.saveObjectToJSON(catalog, item);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
