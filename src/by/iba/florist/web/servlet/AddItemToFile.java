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

import by.iba.florist.entity.Catalog;
import by.iba.florist.entity.flowerTypes.*;
import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.*;
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

		String name        = request.getParameter("name");
		Double price       = Double.parseDouble(request.getParameter("price"));
		String comment     = request.getParameter("comment");
		String file_name   = request.getParameter("file_name");
		String file_type   = request.getParameter("file_type");
		String flower_type = request.getParameter("flower_type");
		
		System.out.println("Save to File...");
		System.out.println("Name: " + name + "; \nPrice: " + price + "; \nDescription: " + comment);		
		
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = response.getWriter();
		out.println("<p>Item " + name + " is added to Catalog!</p>");		
		
		Catalog cat = new Catalog();	
		File file = cat.initFileCatalog(file_name, file_type);
				
		Flower fl = null;
		
		if (flower_type.equals("aloe")) {
			Flower fl_new = new Aloe(name, price, comment);
			fl = fl_new;
		}
		if (flower_type.equals("begonia")) {
			Flower fl_new = new Begonia(name, price, comment);
			fl = fl_new;
		}
		if (flower_type.equals("fikus")) {
			Flower fl_new = new Fikus(name, price, comment);
			fl = fl_new;
		}
		if (flower_type.equals("rose")) {
			Flower fl_new = new Rose(name, price, comment);
			fl = fl_new;
		}
		if (flower_type.equals("tulip")) {
			Flower fl_new = new Tulip(name, price, comment);
			fl = fl_new;
		}
		if (flower_type.equals("chrysantemum")) {
			Flower fl_new = new Chrysantemum(name, price, comment);
			fl = fl_new;
		}
		try {
			if (file_type.equals("xml")) {
				JaxbParserImpl xml_parser = new JaxbParserImpl();
				if (file.exists()) {
					
					Catalog cat_new = (Catalog) xml_parser.getObjectFromXML(file, Catalog.class);				
					cat_new.addItem(fl);				
					xml_parser.saveObjectToXML(file, cat_new);
					
					} else {
						if (file_name != null) {
							cat.addItem(fl);
							xml_parser.saveObjectToXML(file, cat);
							}
						}
			} 
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (WrongFileFormatException e) {
			e.printStackTrace();
		}
		
//		try {
			if (file_type.equals("json")) {
				JacksonParserImpl json_parser = new JacksonParserImpl();
				if (file.exists()) {
					
					Catalog cat_new;
					try {
						cat_new = (Catalog) json_parser.getObjectFromJSON(file, Catalog.class);
						cat_new.addItem(fl);				
						json_parser.saveObjectToJSON (file, cat_new);
					} catch (WrongFileFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
				} else {
					 		if (file_name != null) {
					 			cat.addItem(fl);
							json_parser.saveObjectToJSON(file, cat);
					 		}					
				}
				
			} 	
//		} 
		
}
			
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
