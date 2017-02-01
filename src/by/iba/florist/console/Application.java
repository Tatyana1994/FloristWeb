package by.iba.florist.console;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

import by.iba.florist.customExceptions.FileXMLDoesNotExistYet;
import by.iba.florist.customExceptions.WrongFileFormatException;
import by.iba.florist.customExceptions.WrongOperationNumberException;
import by.iba.florist.entity.Cart;
import by.iba.florist.entity.flowerTypes.Aloe;
import by.iba.florist.entity.flowerTypes.Begonia;
import by.iba.florist.entity.flowerTypes.Chrysantemum;
import by.iba.florist.entity.flowerTypes.Fikus;
import by.iba.florist.entity.flowerTypes.Rose;
import by.iba.florist.entity.flowerTypes.Tulip;

//add initial comment

public class Application {

	public static void initApplication() {

		Rose r = new Rose("Rose", 3.15);
		Tulip t = new Tulip("Tulip", 2.2);
		Begonia b = new Begonia("Begonia", 3.45);
		Chrysantemum ch = new Chrysantemum("Chrysantemum", 2.56);
		Fikus f = new Fikus("Fikus", 2.567);
		Aloe al = new Aloe("Aloe", 6.334);
		Begonia bg = new Begonia("Begonia Special", 50.984);
		bg.setCountryOrigin("Brasil");

		Florist.addToStock(r);
		Florist.addToStock(t);
		Florist.addToStock(b);
		Florist.addToStock(ch);
		Florist.addToStock(f);
		Florist.addToStock(al);
		Florist.addToStock(t);
		Florist.addToStock(new Tulip("Tulip", 3.567));
		Florist.addToStock(bg);

	}

	public static void main(String[] args) throws WrongOperationNumberException, JAXBException, WrongFileFormatException,
													FileXMLDoesNotExistYet, FileNotFoundException {

		initApplication();
		Cart cart = new Cart();		
		System.out.println(cart.toString());
		ConsoleMenuCartEditor cm = new ConsoleMenuCartEditor(cart);

		while (true) {
			cm.chooseOperation();
			cm.executeCartEditor();
		}

	}

}
