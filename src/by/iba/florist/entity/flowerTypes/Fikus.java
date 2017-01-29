package by.iba.florist.entity.flowerTypes;


import by.iba.florist.entity.FlowerPot;

public class Fikus extends FlowerPot {

	public Fikus(String name, double price) {
		super(name, price);
	}
	
	public Fikus() {
		super();
	}
	
	public Fikus(long id, String name, double price) {
		super(id, name, price);
	}
	
	public Fikus(String name, double price, String description) {
		super(name, price, description);
	}

	public void grow() {
		System.out.println("Fikus is growing...");
	}

	public void spoil() {
		System.out.println("Fikus is spoiled");
	}

}