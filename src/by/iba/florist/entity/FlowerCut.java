package by.iba.florist.entity;


import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import by.iba.florist.console.FreshDegree;
import by.iba.florist.console.SmellingFlower;
import by.iba.florist.entity.flowerTypes.Chrysantemum;
import by.iba.florist.entity.flowerTypes.Rose;
import by.iba.florist.entity.flowerTypes.Tulip;

@XmlSeeAlso({ Tulip.class, Rose.class, Chrysantemum.class }) 
@JsonSubTypes({
@Type(value = Tulip.class),
@Type(value = Rose.class),
@Type(value = Chrysantemum.class),
})
public abstract class FlowerCut extends Flower implements SmellingFlower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FreshDegree freshness;

	public FlowerCut(String name, double price) {
		super(name, price);
	}
	
	public FlowerCut(String name, double price, String description) {
		super(name, price, description);
	}
	
	public FlowerCut() {
		super();
	}
	
	public FlowerCut(long id, String name, double price) {
		super(id, name, price);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public FreshDegree getFreshnessDegree() {
		return freshness;
	}

	public void setFreshness(FreshDegree degree) {
		this.freshness = degree;
	}

	public abstract void spoil();

	public void smell() {
		System.out.println("Smells good");
	}

}