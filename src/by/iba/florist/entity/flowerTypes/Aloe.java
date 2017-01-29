package by.iba.florist.entity.flowerTypes;


import by.iba.florist.entity.FlowerPot;

public class Aloe extends FlowerPot {

	public Aloe(String name, double price) {
		super(name, price);
	}
	
	public Aloe() {
		super();
	}
	
	public Aloe(long id, String name, double price) {
		super(id, name, price);
	}
	
	public Aloe(String name, double price, String description) {
		super(name, price, description);
	}

	public void spoil() {
		System.out.println("Aloe is spoiled");
	}

	public void grow() {
		System.out.println("Aloe is growing...");
	}

	public void extractSap() {

	}
}