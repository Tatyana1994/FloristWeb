package by.iba.florist.entity.flowerTypes;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonCreator
	public Chrysantemum(@JsonProperty("name")String name, @JsonProperty("price")double price, @JsonProperty("description")String description) {
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