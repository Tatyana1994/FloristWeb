package by.iba.florist.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import java.io.*;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;
import by.iba.florist.entity.Flower;
import by.iba.florist.web.parser.JacksonParser;
import by.iba.florist.web.parser.JaxbParser;

/**
 * Servlet implementation class GetItemfromFile
 */
@WebServlet("/GetItemfromFile")
public class GetItemListFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(GetItemListFromFile.class);
       
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

		String fileName   = request.getParameter("fileName");
		String fileType   = request.getParameter("fileType");
		
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = response.getWriter();
		
		Catalog cat = new Catalog();
		File file = cat.initFileCatalog(fileName, fileType);
		
		if (file.exists()) {
			if (fileType.equals("xml")) {
				JaxbParser xmlParser = new JaxbParser();
				Catalog catExtract;
				try {
					catExtract = (Catalog) xmlParser.getObjectFromXML(file, Catalog.class);
					catExtract.showList();

					out.println("<h3>ProductList From Catalog - " + fileName + "." + fileType +  "</h3>");
					out.println("<table border=2>");
					out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Description</b></td></tr>");
					for (Flower fl : catExtract.getProductDatabase()) {
						out.println("<tr><td>" + fl.getName() + "</td>" + 
									"<td>" + fl.getPrice() + "</td>" +
								    "<td>" + fl.getDescription() + "</td></tr>");
					}
					out.println("</table>");
				} catch (IOException e) {
					logger.error(">>>>>>>>>> ERROR - " + e.getMessage());
					out.println("<font color=red>FileNotFoundExceptionERROR: </font>" + e.getMessage());
					out.println("<p><b>Try again!</b></p>");
					e.printStackTrace();
				} catch (JAXBException e) {
					logger.error(">>>>>>>>>> ERROR - " + file.getAbsolutePath() + " - Access is denied");
					out.println("JAXBExceptionERROR - Access is denied");
					e.printStackTrace();
				} catch (WrongFileFormatException e) {
					out.println("WrongFileFormatExceptionERROR");
					e.printStackTrace();
				}
			}
			if (fileType.equals("json")) {
					JacksonParser jsonParser = new JacksonParser();
					Catalog catExtract;
						try {
							catExtract = (Catalog) jsonParser.getObjectFromJSON(file, Catalog.class);

							out.println("<h3>ProductList From Catalog - " + fileName + "." + fileType +  "</h3>");
							out.println("<table border=2>");
							out.println("<tr><td><b>Name</b></td><td><b>Price</b></td><td><b>Description</b></td></tr>");
					    	for (Flower fl : catExtract.getProductDatabase()) {
								out.println("<tr><td>" + fl.getName() + "</td>" + 
											"<td>" + fl.getPrice() + "</td>" +
										    "<td>" + fl.getDescription() + "</td></tr>");
							}
							out.println("</table>");
															
						} catch (Exception e) {
							logger.error(">>>>>>>>>> ERROR - " + file.getAbsolutePath() + " - Access is denied");
							out.println("FileNotFoundExceptionERROR - Access is denied");
							e.printStackTrace();
						} 
		  
			} 
		} else {
			  out.println("<h3><font color=red>ERROR! File doesn't exist</h3>" );
		}
				
		out.println("<br><p><a href = \"index.jsp\">HOME</a></p>");	
		
}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
