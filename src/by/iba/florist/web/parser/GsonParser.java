package by.iba.florist.web.parser;

import java.io.File;
import java.io.IOException;

import by.iba.florist.customExceptions.WrongFileFormatException;

public interface GsonParser {
	
	Object getObjectFromJSON(File file, Class c) throws WrongFileFormatException;
    void saveObjectToJSON(File file, Object o);

}
