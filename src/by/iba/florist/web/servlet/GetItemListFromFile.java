package by.iba.florist.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import java.io.*;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;
import by.iba.florist.entity.Flower;
import by.iba.florist.web.parser.JaxbParserImpl;

/**
 * Servlet implementation class GetItemfromFile
 */
@WebServlet("/GetItemfromFile")
public class GetItemListFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetItemListFromFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String file_name   = request.getParameter("file_name");
		String file_type   = request.getParameter("file_type");
		
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = response.getWriter();
		
		Catalog cat = new Catalog();
		File file = cat.initFileCatalog(file_name, file_type);
		
		if (file.exists()) {
			if (file_type.equals("xml")) {
				JaxbParserImpl xml_parser = new JaxbParserImpl();
				Catalog cat_extract;
				try {
					cat_extract = (Catalog) xml_parser.getObjectFromXML(file, Catalog.class);
					cat_extract.showList();

					out.println("<h3>ProductList From Catalog - " + file_name + "." + file_type +  "</h3>");
					out.println("<table border=2>");
					out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Description</b></td></tr>");
					for (Flower fl : cat_extract.getProductDatabase()) {
						out.println("<tr><td>" + fl.getName() + "</td>" + 
									"<td>" + fl.getPrice() + "</td>" +
								    "<td>" + fl.getDescription() + "</td></tr>");
					}
					out.println("</table>");
				} catch (JAXBException e) {
					e.printStackTrace();
				} catch (WrongFileFormatException e) {
					e.printStackTrace();
				}				
			} 
		} else {
				out.println("<h3><font color=red>ERROR! File doesn't exist</h3>" );
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
