package by.iba.florist.web.parser;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import by.iba.florist.customExceptions.WrongFileFormatException;

public interface JsonXmlParser {
	
    <T> T getObjectFromXML(File file, Class<T> c) throws JAXBException, WrongFileFormatException, FileNotFoundException;
    <T> void saveObjectToXML(File file, T o) throws JAXBException;
    
    <T> T getObjectFromJSON(File file, Class<T> c) throws JAXBException, FileNotFoundException;
    <T> void saveObjectToJSON(File file, T o) throws JAXBException;
    
}