package by.iba.florist.entity.flowerTypes;


import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import by.iba.florist.entity.FlowerCut;

@XmlRootElement(name = "flower")
public class Rose extends FlowerCut {

	private int steamLength;

	public Rose(String name, double price) {
		super(name, price);
	}
	
	public Rose() {
		super();
	}
	
	public Rose(long id, String name, double price) {
		super(id, name, price);
	}

	@JsonCreator
	public Rose(@JsonProperty("name")String name, @JsonProperty("price")double price, @JsonProperty("description")String description) {
		super(name, price, description);
	}
	
	/**
	 * @return the steamLength
	 */
	public int getSteamLength() {
		return steamLength;
	}

	/**
	 * @param steamLength
	 *            the steamLength to set
	 */
	public void setSteamLength(int steamLength) {
		this.steamLength = steamLength;
	}

	public void spoil() {
		System.out.println("Rose is spoiled");
	}

	public void smell() {
		System.out.println("Smells like rose");
	}

	public void sting() {

	}

}