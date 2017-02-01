package by.iba.florist.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.*;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
		String fileName   = request.getParameter("fileName");
		String fileType   = request.getParameter("fileType");
		String flowerType = request.getParameter("flowerType");
		
		logger.debug(">>>>>>>>>>>> name = " + name);
		logger.debug(">>>>>>>>>>>> price = " + price);
		logger.debug(">>>>>>>>>>>> comment = " + comment);
		logger.debug(">>>>>>>>>>>> fileName = " + fileName);
		logger.debug(">>>>>>>>>>>> fileType = " + fileType);
		logger.debug(">>>>>>>>>>>> flowerType = " + flowerType);
		
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = response.getWriter();
		out.println("<h3>Action Add <font color=red>Item = " + name + "</font> to Catalog...</h3>");		
		
		Catalog cat = new Catalog();	
		File file = cat.initFileCatalog(fileName, fileType);
				
		Flower fl = null;
		
		if (flowerType.equals("aloe")) {
			Flower fl_new = new Aloe(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flowerType);
		}
		if (flowerType.equals("begonia")) {
			Flower fl_new = new Begonia(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flowerType);
		}
		if (flowerType.equals("fikus")) {
			Flower fl_new = new Fikus(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flowerType);
		}
		if (flowerType.equals("rose")) {
			Flower fl_new = new Rose(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flowerType);
		}
		if (flowerType.equals("tulip")) {
			Flower fl_new = new Tulip(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flowerType);
		}
		if (flowerType.equals("chrysantemum")) {
			Flower fl_new = new Chrysantemum(name, price, comment);
			fl = fl_new;
			logger.info(">>>>>>>>>>>> ITEM type = " + flowerType);
		}
		try {
			if (fileType.equals("xml")) {
				JaxbParser xmlParser = new JaxbParser();
				if (file.exists()) {
					logger.info(">>>>>>>>>>>> File exists already = " + file.getAbsolutePath());
					logger.info(">>>>>>>>>>>> GET object from XML file = " + fl.toString());
				
					Catalog cat_new = (Catalog) xmlParser.getObjectFromXML(file, Catalog.class);
					cat_new.addItem(fl);	
					logger.info(">>>>>>>>>>>> SAVE object to XML file = " + file.getAbsolutePath());
					xmlParser.saveObjectToXML(file, cat_new);
					out.println("<p>Item is added to existing catalog: " + fileName + "." + fileType + "</p>");
					
					} else {
						logger.info(">>>>>>>>>>>> File doesn't exist. Create the new one = " + file.getAbsolutePath());
						if (fileName != null) {
							cat.addItem(fl);
							xmlParser.saveObjectToXML(file, cat);
							
							out.println("<p>Item is added to the new created catalog: " + fileName + "." + fileType + "</p>");
							
							}
						}
			} 
		} catch (JAXBException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
			out.println("<p>" + e.getMessage() + "</p>");
		} catch (WrongFileFormatException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
			out.println("<p>" + e.getMessage() + "</p>");
		}
		
		try {
			if (fileType.equals("json")) {
				JacksonParser jsonParser = new JacksonParser();
				if (file.exists()) {
					logger.info(">>>>>>>>>>>> File exists already = " + file.getAbsolutePath());
					Catalog cat_new;
					try {
						logger.info(">>>>>>>>>>>> GET object from JSON file = " + fl.toString());
						cat_new = (Catalog) jsonParser.getObjectFromJSON(file, Catalog.class);
						cat_new.addItem(fl);				
						logger.info(">>>>>>>>>>>> SAVE object from JSON file = " + file.getAbsolutePath());
						jsonParser.saveObjectToJSON (file, cat_new);
						
						out.println("<p>Item is added to existing catalog: " + fileName + "." + fileType + "</p>");
						
					} catch (WrongFileFormatException e) {
						logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
						e.printStackTrace();
						}  
					} else {
							logger.info(">>>>>>>>>>>> File doesn't exist. Create the new one = " + file.getAbsolutePath());
					 		if (fileName != null) {
					 			cat.addItem(fl);
					 			jsonParser.saveObjectToJSON(file, cat);
					 			
					 			out.println("<p>Item is added to the new created catalog: " + fileName + "." + fileType + "</p>");
					 		}					
					}
				
			}			
			out.println("<a href = \"index.jsp\">HOME</a>");	
		} catch (JsonGenerationException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
			out.println("<p>" + e.getMessage() + "</p>");
		} catch (JsonMappingException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
			out.println("<p>" + e.getMessage() + "</p>");
		} catch (JsonProcessingException e) {
			logger.error(">>>>>>>>>>>> ERROR = " + e.getMessage());
			e.printStackTrace();
			out.println("<p>" + e.getMessage() + "</p>");
		} 
}
			
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
