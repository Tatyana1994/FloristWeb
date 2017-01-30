package by.iba.florist.web.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.entity.Catalog;

public class GsonParserImpl implements GsonParser {

	@Override
	public Object getObjectFromJSON(File file, Class c) throws WrongFileFormatException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveObjectToJSON(File file, Object o) {
		
		Gson gson = new Gson();
		
		FileWriter file_writer;
		try {
			file_writer = new FileWriter(file);
			
			// 1. Java object to JSON, and save into a file
			gson.toJson(o, file_writer);

			// 2. Java object to JSON, and assign to a String
			String jsonInString = gson.toJson(o);
		
		} catch (IOException e) {
			e.printStackTrace();
		} 

		
	}

}
