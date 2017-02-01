package by.iba.florist.entity;

import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import by.iba.florist.entity.flowerTypes.Aloe;
import by.iba.florist.entity.flowerTypes.Begonia;
import by.iba.florist.entity.flowerTypes.Chrysantemum;
import by.iba.florist.entity.flowerTypes.Fikus;
import by.iba.florist.entity.flowerTypes.Rose;
import by.iba.florist.entity.flowerTypes.Tulip;


@XmlSeeAlso({ Begonia.class, Aloe.class, Fikus.class })
@JsonSubTypes({
@Type(value = Fikus.class),
@Type(value = Aloe.class),
@Type(value = Begonia.class),
})
public abstract class FlowerPot extends Flower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String potMaterial;
	private String countryOrigin;
	private boolean hungary;

	public FlowerPot(String name, double price) {
		super(name, price);
	}
	
	public FlowerPot(String name, double price, String description) {
		super(name, price, description);
	}
	
	public FlowerPot() {
		super();
	}
	
	public FlowerPot(long id, String name, double price) {
		super(id, name, price);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public void setPotMaterial(String potMaterial) {
		this.potMaterial = potMaterial;
	}

	/**
	 * @return the countryOrigin
	 */
	public String getCountryOrigin() {
		return countryOrigin;
	}

	/**
	 * @param countryOrigin
	 *            the countryOrigin to set
	 */
	@JsonIgnoreProperties(ignoreUnknown = true)
	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public String getPotMaterial() {
		return potMaterial;
	}

	public boolean isHungary() {
		return hungary;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public void setHungary() {
		hungary = true;
	}

	public void pour() {
		hungary = false;
	}

	public abstract void grow();

	public void adept() {
		System.out.println("Flower is adepted in pot");
	}

}