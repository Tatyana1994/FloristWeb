package by.iba.florist.web.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.iba.florist.customExceptions.*;
import by.iba.florist.web.servlet.AddItemToFile;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import org.apache.log4j.*;
import org.apache.log4j.Logger;

public class JaxbParser implements JsonXmlParser {

	private static Logger logger = Logger.getLogger(AddItemToFile.class);
	
	@Override
    public <T> T getObjectFromXML(File file, Class<T> c) throws JAXBException, WrongFileFormatException, FileNotFoundException {
    	
        if (file.getName().indexOf(".xml") == -1) {
        	throw new WrongFileFormatException("File is not of appropriate format");
        }		
		JAXBContext context = JAXBContext.newInstance(c); 
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T object = (T) unmarshaller.unmarshal(file);      
        return object;
        
    }
 
    @Override
    public <T> void saveObjectToXML(File file, T o) throws JAXBException {
    	    	
        JAXBContext context = JAXBContext.newInstance(o.getClass());      
        Marshaller marshaller = context.createMarshaller();     
        marshaller.marshal(o, file);  
        System.out.println("Object is saved in XML format");
        
    }

	@Override
	public Object getObjectFromJSON(File file, Class c) throws JAXBException, FileNotFoundException {
		
		System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");		
        JAXBContext context = JAXBContext.newInstance(c);       
        Unmarshaller unmarshaller = context.createUnmarshaller();     
        // Set the Unmarshaller media type to JSON or XML
     	unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE,
     			"application/json");
     	// Set it to true if you need to include the JSON root element in the
     	// JSON input
     	unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);   	
		unmarshaller.setProperty(UnmarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@");	        
		//StreamSource source = new StreamSource(file);	
        Object obj = (Object) unmarshaller.unmarshal(file);     
        System.out.println(obj);           
        return obj;      
	}

	@Override
	public <T> void saveObjectToJSON(File file, T o) throws JAXBException {
				
		JAXBContext context = JAXBContext.newInstance(Object.class);  	
		     
        Marshaller marshaller = context.createMarshaller();             
        
        marshaller.setProperty("eclipselink.media-type",
        		"application/json");               
       
        marshaller.setProperty("eclipselink.json.include-root", false);       
       
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        System.out.println("Object is saved in JSON format");       
        marshaller.marshal(o, file);
		
	}

}