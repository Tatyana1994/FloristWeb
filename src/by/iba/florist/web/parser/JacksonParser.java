package by.iba.florist.web.parser;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonParser implements JSONParser {

	@Override
	public <T> T getObjectFromJSON(File file, Class<T> c) throws WrongFileFormatException {
		ObjectMapper mapper = new ObjectMapper();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//JSON from file to Object
		T obj;
		
		try {
			obj = (T) mapper.readValue(file, Catalog.class);
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
	public <T> void saveObjectToJSON(File file, T o) throws JsonGenerationException, JsonMappingException, JsonProcessingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		Object obj = (T) new Object();

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
		String jsonInString = mapper.writeValueAsString(o);
		
	}


}
