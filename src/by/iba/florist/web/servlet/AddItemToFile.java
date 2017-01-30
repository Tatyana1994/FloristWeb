package by.iba.florist.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.*;
import org.apache.log4j.Logger;

import com.sun.istack.internal.logging.*;

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

	private static Logger logger = Logger.getLogger(AddItemToFile.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	   System.out.println("Initialising log4j");
	   String log4jLocation = config.getInitParameter("log4j-properties-location");
	 
	   ServletContext sc = config.getServletContext();
	 
	   if (log4jLocation == null) {
	      System.out.println("No log4j properites...");
	      BasicConfigurator.configure();
	   } else {
	      String webAppPath = sc.getRealPath("/");
	      String log4jProp = webAppPath + log4jLocation;
	      File output = new File(log4jProp);
	 
	      if (output.exists()) {
	         System.out.println("Initialising log4j with: " + log4jProp);
	         logger.info("Initialising log4j with: " + log4jProp);
	         PropertyConfigurator.configure(log4jProp);
	      } else {
	         System.out.println("Find not found (" + log4jProp + ").");
	         BasicConfigurator.configure();
	      }
	   }
	 
	   super.init(config);
	   
	}
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

		logger.info(">>>>>>>>>>>> Get parameters from request: ");
		
		String name        = request.getParameter("name");
		Double price       = Double.parseDouble(request.getParameter("price"));
		String comment     = request.getParameter("comment");
		String file_name   = request.getParameter("file_name");
		String file_type   = request.getParameter("file_type");
		String flower_type = request.getParameter("flower_type");
		
		logger.debug(">>>>>>>>>>>> name = " + name);
		logger.debug(">>>>>>>>>>>> price = " + price);
		logger.debug(">>>>>>>>>>>> comment = " + comment);
		logger.debug(">>>>>>>>>>>> file_name = " + file_name);
		logger.debug(">>>>>>>>>>>> file_type = " + file_type);
		logger.debug(">>>>>>>>>>>> flower_type = " + flower_type);
		
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
			logger.info(">>>>>>>>>>>> ITEM type = " + flower_type);
		}
		if (flower_type.equals("begonia")) {
			Flower fl_new = new Begonia(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flower_type);
		}
		if (flower_type.equals("fikus")) {
			Flower fl_new = new Fikus(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flower_type);
		}
		if (flower_type.equals("rose")) {
			Flower fl_new = new Rose(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flower_type);
		}
		if (flower_type.equals("tulip")) {
			Flower fl_new = new Tulip(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flower_type);
		}
		if (flower_type.equals("chrysantemum")) {
			Flower fl_new = new Chrysantemum(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flower_type);
		}
		try {
			if (file_type.equals("xml")) {
				JaxbParserImpl xml_parser = new JaxbParserImpl();
				if (file.exists()) {
					logger.info(">>>>>>>>>>>> File exists already = " + file.getAbsolutePath());
					logger.info(">>>>>>>>>>>> GET object from XML file = " + fl.toString());
					Catalog cat_new = (Catalog) xml_parser.getObjectFromXML(file, Catalog.class);				
					cat_new.addItem(fl);	
					logger.info(">>>>>>>>>>>> SAVE object to XML file = " + file.getAbsolutePath());
					xml_parser.saveObjectToXML(file, cat_new);
					
					} else {
						logger.info(">>>>>>>>>>>> File doesn't exist. Create the new one = " + file.getAbsolutePath());
						if (file_name != null) {
							cat.addItem(fl);
							xml_parser.saveObjectToXML(file, cat);
							}
						}
			} 
		} catch (JAXBException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
		} catch (WrongFileFormatException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
		}
		
//		try {
			if (file_type.equals("json")) {
				JacksonParserImpl json_parser = new JacksonParserImpl();
				if (file.exists()) {
					logger.info(">>>>>>>>>>>> File exists already = " + file.getAbsolutePath());
					Catalog cat_new;
					try {
						logger.info(">>>>>>>>>>>> GET object from JSON file = " + fl.toString());
						cat_new = (Catalog) json_parser.getObjectFromJSON(file, Catalog.class);
						cat_new.addItem(fl);				
						logger.info(">>>>>>>>>>>> SAVE object from JSON file = " + file.getAbsolutePath());
						json_parser.saveObjectToJSON (file, cat_new);
					} catch (WrongFileFormatException e) {
						logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
						e.printStackTrace();
					}						
				} else {
							logger.info(">>>>>>>>>>>> File doesn't exist. Create the new one = " + file.getAbsolutePath());
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
