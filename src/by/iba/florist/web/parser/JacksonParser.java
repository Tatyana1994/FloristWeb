package by.iba.florist.web.parser;

import java.io.File;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;

public interface JacksonParser {

	Object getObjectFromJSON(File file, Class c) throws WrongFileFormatException;
    void saveObjectToJSON(File file, Object o);
	
}
