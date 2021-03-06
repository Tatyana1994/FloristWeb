package by.iba.florist.entity.flowerTypes;


import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import by.iba.florist.entity.FlowerCut;

@XmlRootElement(name = "flower")
public class Tulip extends FlowerCut {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tulip(String name, double price) {
		super(name, price);
	}
	
	public Tulip() {
		super();
	}
	
	public Tulip(long id, String name, double price) {
		super(id, name, price);
	}
	
	@JsonCreator
	public Tulip(@JsonProperty("name")String name, @JsonProperty("price")double price, @JsonProperty("description")String description) {
		super(name, price, description);
	}

	public void spoil() {
		System.out.println("Tulip is spoiled");
	}

}
