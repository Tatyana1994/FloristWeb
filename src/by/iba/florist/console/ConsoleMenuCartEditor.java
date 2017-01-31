package by.iba.florist.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import by.iba.florist.entity.Cart;
import by.iba.florist.entity.Catalog;
import by.iba.florist.entity.Flower;
import by.iba.florist.web.parser.JaxbParser;
import by.iba.florist.web.parser.*;
import by.iba.florist.customExceptions.*;

public class ConsoleMenuCartEditor {

	private int operation = 0;
	private Cart cart;
	
	private boolean is_XML_saved = false;

	ConsoleMenuCartEditor(Cart cart) {
		this.cart = cart;
		System.out.println("");
		System.out.println("Chose what to do: ");
		System.out.println("1 - Show list of available products; ");
		System.out.println("2 - Add products to cart; ");
		System.out.println("3 - Total price of order; ");
		System.out.println("4 - Remove product by name; ");
		System.out.println("5 - Remove product by name and price; ");
		System.out.println("6 - Clear cart; ");
		System.out.println("7 - Show list of items in the cart sorted by name; ");
		System.out.println("8 - Show list of items in the cart sorted by price; ");
		System.out.println("9 - Show list of items in the cart without duplicates; ");
		System.out.println("10 - Save cart to file with java serialization mechanism; ");
		System.out.println("11 - Get cart from file with java deserialization mechanism; ");
		System.out.println("12 - Save cart to .xml file with JAXB; ");
		System.out.println("13 - Get cart from .xml file with JAXB; ");
		System.out.println("14 - Save cart to .json file with JAXB; ");
		System.out.println("15 - Get cart from .json file with JAXB; ");
		System.out.println("0  - exit; ");
		
	}	
	
	public boolean isIs_XML_saved() {
		return is_XML_saved;
	}

	public void setIs_XML_saved(boolean is_XML_saved) {
		this.is_XML_saved = is_XML_saved;
	}


	public Cart getCart() {
		return cart;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	@SuppressWarnings("resource")
	public int chooseOperation() throws WrongOperationNumberException {

		try {

			System.out.println("\nYour Choice:");

			Scanner sc = new Scanner(System.in);
			int oper = sc.nextInt();
			if ((oper > 15) || (oper < 0)) throw new WrongOperationNumberException (
					"Wrong operation number! Enter number 1-15!", oper);
			setOperation(oper);

			System.out.println("Your choice is: " + getOperation());
			sc.close();
			
			return oper;
			
		} catch (InputMismatchException e) {
			System.out.println("Wrong! Incorrect operation input!");
			return 0;
		}
	}

	@SuppressWarnings("finally")
	public void executeCartEditor() throws JAXBException, WrongFileFormatException, FileXMLDoesNotExistYet, WrongFileFormatException, FileNotFoundException {

		int operation = this.getOperation();
		switch (operation) {
		case 1: {
			Florist.showProductAvailableList();

			break;
		}
		case 2: {
			System.out.println("Enter product id to add to the cart: ");
			Scanner sc = new Scanner(System.in);
			long id = sc.nextLong();
			for (Flower fl : Florist.getFlowerStock()) {
				if (fl.getId() == id) {
					cart.addToCart(fl);
					System.out.println("Product is added to the cart: " + fl.toString());
				}
				
			}
			
			sc.close();
			
			break;
		}
		
		case 3: {
			cart.getTotalPrice();
			break;
		}
		case 4: {
			System.out.println("Enter name to remove: ");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			sc.close();
			cart.removeProduct(name);
			
			break;
		}
		case 5: {
			try {
				System.out.println("Enter name to remove: ");
				Scanner sc = new Scanner(System.in);
				String name = sc.nextLine();
				System.out.println("Enter price to remove: ");
				double price = sc.nextDouble();
				cart.removeProduct(name, price);
				sc.close();				
			} catch (InputMismatchException e) {
				System.out.println("There are no such product in the list");
			} finally {	
				break;
			}
			
			
		}
		case 6: {
			cart.clear();
			break;
		}
		case 7: {
			cart.showListNames();
			break;
		}
		case 8: {
			cart.showListPrices();
			break;
		}
		case 9: {
			cart.showListWithoutDuplicates();
			break;
		}
		
		case 10: {
			cart.saveCartToFile("cart.data");
			break;
		}
		
		case 11: {
			cart.getCartFromFile("cart.data");
			break;
		}
		
		case 12: {
			JaxbParser parser = new JaxbParser();
	        File file_xml = new File("cart.xml");
	        
			parser.saveObjectToXML(file_xml, cart);
			
			this.is_XML_saved = true;
			
			break;
		}
		
		case 13: {
	        
	        if (this.is_XML_saved == false) throw new FileXMLDoesNotExistYet("File doesn't exist yet!");
			
			JaxbParser parser = new JaxbParser();
	        File file_xml = new File("cart.xml");
	        
	        Cart cart = (Cart) parser.getObjectFromXML(file_xml, Cart.class);
	        System.out.println(cart);
	        
	        break;
		}
		
		case 14: {
			JaxbParser parser = new JaxbParser();
	        File file_json = new File("cart.json");
	        
			parser.saveObjectToJSON(file_json, cart);
			break;
		}
		
		case 15: {
			JaxbParser parser = new JaxbParser();
	        File file_json = new File("cart.json");
	        
			parser.getObjectFromJSON(file_json, cart.getClass());
			break;
		}
		
		case 0: {
			System.out.println("Program is closed");;
			System.exit(0);
		}

		default: {
			System.out.println("There ere no such a number");
			break;
		}

		}

	}

}
