package by.iba.florist.web.parser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;

public interface JSONParser {

	<T> T getObjectFromJSON(File file, Class<T> c) throws WrongFileFormatException;
    <T> void saveObjectToJSON(File file, T o) throws JsonGenerationException, JsonMappingException, JsonProcessingException, IOException;
	
}
