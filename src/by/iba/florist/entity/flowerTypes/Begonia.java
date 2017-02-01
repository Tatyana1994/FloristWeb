package by.iba.florist.entity.flowerTypes;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import by.iba.florist.console.BloomingFlower;
import by.iba.florist.entity.FlowerPot;

public class Begonia extends FlowerPot implements BloomingFlower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Begonia(String name, double price) {
		super(name, price);
	}
	
	public Begonia() {
		super();
	}
	
	public Begonia(long id, String name, double price) {
		super(id, name, price);
	}
	
	@JsonCreator
	public Begonia(@JsonProperty("name")String name, @JsonProperty("price")double price, @JsonProperty("description")String description) {
		super(name, price, description);
	}

	public void grow() {
		System.out.println("Begonia is growing...");
	}

	public void spoil() {
		System.out.println("Begonia is spoiled");
	}

	public void bloom() {

	}
}