package by.iba.florist.web.parser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.iba.florist.customExceptions.*;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;


public class JaxbParserImpl implements JaxbParser {

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