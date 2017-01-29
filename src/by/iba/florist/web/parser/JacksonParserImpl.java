package by.iba.florist.web.parser;

import java.io.File;
import java.io.IOException;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonParserImpl implements JacksonParser {

	@Override
	public Object getObjectFromJSON(File file, Class c) throws WrongFileFormatException {
		ObjectMapper mapper = new ObjectMapper();

		//JSON from file to Object
		Object obj;
		
		try {
			obj = mapper.readValue(file, Catalog.class);
			return obj;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;	
	}

	@Override
	public void saveObjectToJSON(File file, Object o) {
		ObjectMapper mapper = new ObjectMapper();
		Catalog obj = new Catalog();

		//Object to JSON in file
		try {
			mapper.writeValue(file, o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}


}
