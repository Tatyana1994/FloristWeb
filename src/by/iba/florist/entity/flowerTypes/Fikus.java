package by.iba.florist.entity.flowerTypes;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import by.iba.florist.entity.FlowerPot;

public class Fikus extends FlowerPot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fikus(String name, double price) {
		super(name, price);
	}
	
	public Fikus() {
		super();
	}
	
	public Fikus(long id, String name, double price) {
		super(id, name, price);
	}
	
	@JsonCreator
	public Fikus(@JsonProperty("name")String name, @JsonProperty("price")double price, @JsonProperty("description")String description) {
		super(name, price, description);
	}

	public void grow() {
		System.out.println("Fikus is growing...");
	}

	public void spoil() {
		System.out.println("Fikus is spoiled");
	}

}