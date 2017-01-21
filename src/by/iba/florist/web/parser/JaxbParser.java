package by.iba.florist.web.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.json.simple.JSONObject;

import by.iba.florist.web.customExceptions.*;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class JaxbParser implements Parser {

	@Override
    public Object getObjectFromXML(File file, Class c) throws JAXBException, WrongFileFormatException {
    	
        if (file.getName().indexOf(".xml") == -1) {
        	throw new WrongFileFormatException("File is not of appropriate format");
        }		
		JAXBContext context = JAXBContext.newInstance(c); 
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = (Object) unmarshaller.unmarshal(file);      
        return object;
        
    }
 
    @Override
    public void saveObjectToXML(File file, Object o) throws JAXBException {
    	    	
        JAXBContext context = JAXBContext.newInstance(o.getClass());      
        Marshaller marshaller = context.createMarshaller();     
        marshaller.marshal(o, file);  
        System.out.println("Object is saved in XML format");
        
    }

	@Override
	public Object getObjectFromJSON(File file, Class c) throws JAXBException {
		
			
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
	public void saveObjectToJSON(File file, Object o) throws JAXBException {
		
				
		System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");  	
		JAXBContext context = JAXBContext.newInstance(o.getClass().getName(), o.getClass().getClassLoader());     
        Marshaller marshaller = context.createMarshaller();       
        marshaller.setProperty(MarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@") ;       
        // Set the Marshaller media type to JSON or XML
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
        		"application/json");               
        // Set it to true if you need to include the JSON root element in the JSON output
        marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);       
        // Set it to true if you need the JSON output to formatted
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        System.out.println("Object is saved in JSON format");       
        marshaller.marshal(o, file);
		
	}

}