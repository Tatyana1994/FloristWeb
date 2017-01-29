package by.iba.florist.entity.flowerTypes;


import javax.xml.bind.annotation.XmlRootElement;

import by.iba.florist.entity.FlowerCut;

@XmlRootElement(name = "flower")
public class Tulip extends FlowerCut {

	public Tulip(String name, double price) {
		super(name, price);
	}
	
	public Tulip() {
		super();
	}
	
	public Tulip(long id, String name, double price) {
		super(id, name, price);
	}
	
	public Tulip(String name, double price, String description) {
		super(name, price, description);
	}

	// @Override
	// public String toString() {
	// return "Tulip sort: " + getSort();
	// }

	public void spoil() {
		System.out.println("Tulip is spoiled");
	}

}
