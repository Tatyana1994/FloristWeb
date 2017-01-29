package by.iba.florist.entity.flowerTypes;


import by.iba.florist.entity.FlowerCut;

public class Chrysantemum extends FlowerCut {

	public Chrysantemum(String name, double price) {
		super(name, price);
	}

	public Chrysantemum() {
		super();
	}
	
	public Chrysantemum(long id, String name, double price) {
		super(id, name, price);
	}
	
	public Chrysantemum(String name, double price, String description) {
		super(name, price, description);
	}

	
	private boolean multiflorous;

	public boolean isMultiflorous() {
		return multiflorous;
	}

	public void spoil() {
		System.out.println("Chrysantemum is spoiled");
	}

}