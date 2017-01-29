package by.iba.florist.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "cart")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idCart;

	private List<Flower> productList;
	
	private double price;

	public Cart() {
		this.productList = new ArrayList<Flower>();
		this.price = 0;
		Date date = new Date();
		this.idCart = Math.abs((int) date.hashCode());

	}

	@XmlElement
	public void setIdCart(Integer idCart) {
		this.idCart = idCart;
	}


	@XmlElementWrapper(name = "productList")
	@XmlElement(name = "flower")
	public void setProductList(List<Flower> productList) {
		this.productList = productList;
	}

	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the idCart
	 */
	public Integer getIdCart() {
		return idCart;
	}

	public List<Flower> getProductList() {
		return productList;
	}

	/**
	 * @return the price
	 */

	public double getPrice() {
		return price;
	}

	public void addToCart(Flower fl) {
		this.productList.add(fl);
		this.price += fl.getPrice();
		// System.out.println("Flower is added to Cart: " + fl.toString());

	}

	public void getTotalPrice() {
		String formattedDouble = String.format("%.2f", price);
		System.out.println("Total price in Basket: " + formattedDouble);
	}

	public void showList() {

		System.out.printf("%13s %15s %6s", "Id", "Name", "Price");
		System.out.println("");
		System.out.printf("-------------------------------------");
		System.out.println("");

		for (Flower fl : this.productList) {
			System.out.printf("%13s %15s %6s", fl.getId(), fl.getName(), fl.getPrice());
			System.out.println("");
		}

	}

	public void showListNames() {

		List<Flower> productList = new ArrayList<Flower>();
		productList = this.productList;

		System.out.println("Products in the cart sorted by name:");
		System.out.printf("%13s %15s %6s", "Id", "Name", "Price");
		System.out.println("");
		System.out.printf("-------------------------------------");
		System.out.println("");

		NameCompare nameCompare = new NameCompare();
		Collections.sort(productList, nameCompare);

		for (Flower fl : productList) {
			System.out.printf("%13s %15s %6s", fl.getId(), fl.getName(), fl.getPrice());
			System.out.println("");
		}

		System.out.println("");

	}

	public void showListPrices() {

		List<Flower> productList = new ArrayList<Flower>();
		productList = this.productList;

		System.out.println("Products in the cart sorted by price:");
		System.out.printf("%13s %15s %6s", "Id", "Name", "Price");
		System.out.println("");
		System.out.printf("-------------------------------------");
		System.out.println("");

		PriceCompare priceCompare = new PriceCompare();
		Collections.sort(productList, priceCompare);
		Collections.reverse(productList);

		for (Flower fl : productList) {
			System.out.printf("%13s %15s %6s", fl.getId(), fl.getName(), fl.getPrice());
			System.out.println("");
		}

		System.out.println("");

	}

	public void showListWithoutDuplicates() {

		List<Flower> productList = new ArrayList<Flower>();
		productList = this.productList;

		System.out.println("List of products without duplicates:");
		System.out.printf("%13s %15s %6s", "Id", "Name", "Price");
		System.out.println("");
		System.out.printf("-------------------------------------");
		System.out.println("");

		Set<Flower> productSet = new HashSet<Flower>();
		productSet.addAll(productList);

		for (Flower fl : productSet) {
			System.out.printf("%13s %15s %6s", fl.getId(), fl.getName(), fl.getPrice());
			System.out.println("");
		}

	}

	public void removeProduct(String name) {

		Iterator<Flower> iter = productList.iterator();

		while (iter.hasNext()) {
			Flower fl = iter.next();
			if (fl.getName().equals(name)) {
				System.out.println("Product is deleted from the list: " + fl.getName() + "; " + fl.getPrice());
				iter.remove();
				price -= fl.getPrice();
			}
		}
	}

	public void removeProduct(String name, double price) {

		try {
			Iterator<Flower> iter = productList.iterator();

			while (iter.hasNext()) {
				Flower fl = iter.next();
				if (fl.getName().equals(name) && (fl.getPrice()) == price) {
					System.out.println("Product is deleted from the list: " + fl.getName() + "; " + fl.getPrice());
					iter.remove();
					price -= fl.getPrice();
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("There are no such a product in the list!");
		}

	}
	
	public void saveCartToFile(String fileName) {
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            oos.writeObject(this);
            System.out.println("Object is serialized and added to file...");
        }
        
		catch(Exception ex){    
            System.out.println(ex.getMessage());
        } 
	}
	
	public Cart getCartFromFile(String fileName) {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            Cart p = (Cart)ois.readObject();
            System.out.printf("Id: %d \t Price: %f \t ProductList: %s \n", p.idCart, p.price, p.productList.toString());
            return p;
        }
       
		catch(Exception ex){      
            System.out.println(ex.getMessage());
        }
		
		return this; 
		
	}

	class NameCompare implements Comparator<Flower> {
		public int compare(Flower fl1, Flower fl2) {
			return fl1.getName().compareTo(fl2.getName());
		}
	}

	class PriceCompare implements Comparator<Flower> {
		public int compare(Flower fl1, Flower fl2) {
			return Double.compare(fl1.getPrice(), fl2.getPrice());
		}
	}

	public void clear() {
		price = 0;
		productList.clear();
		System.out.println("The cart is cleaned");
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cart [idCart=" + idCart + ", price=" + price + "] \nProducts: [" + productList.toString() + "]";
	}

}
