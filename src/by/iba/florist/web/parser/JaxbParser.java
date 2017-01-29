package by.iba.florist.web.parser;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.customExceptions.*;

public interface JaxbParser {
	
    Object getObjectFromXML(File file, Class c) throws JAXBException, WrongFileFormatException;
    void saveObjectToXML(File file, Object o) throws JAXBException;
    
    Object getObjectFromJSON(File file, Class c) throws JAXBException;
    void saveObjectToJSON(File file, Object o) throws JAXBException;
    
}