package by.iba.florist.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import by.iba.florist.console.Color;

@XmlRootElement(name="flower")
@XmlSeeAlso({ FlowerCut.class, FlowerPot.class }) 
@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
@JsonSubTypes({
@Type(value = FlowerCut.class),
@Type(value = FlowerPot.class),
})
public abstract class Flower implements Serializable{


	private String name;
	private String description;
	private Color color;
	private double price;
	private String sort;

	private static int count = 0;
	private long id;

	public Flower() {
		count++;
		this.id = calcId();
	}

	public Flower(String name) {
		this.id = calcId();
		this.name = name;
		count++;
	}
	
	public Flower(String name, double price) {
		this.name = name;
		this.price = price;
		this.id = calcId();
		count++;
	}
	
	public Flower(long id, String name, double price) {
		this.name = name;
		this.price = price;
		this.id = id;
		count++;
	}
	
	public Flower(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		count++;
	}

	public long calcId() {

		Date date = new Date();
		long ms = date.getTime() + (long) (Math.random() * 400) / 2;

		return ms;

	}

	@XmlAttribute(name = "id")
	public long getId() {
		return id;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	@XmlAttribute(name = "price")
	public double getPrice() {
		return price;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@XmlAttribute(name = "color")
	public Color getColor() {
		return color;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@XmlAttribute(name = "sort")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@XmlAttribute(name = "description")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static int getCount() {
		return count;
	}

	public abstract void spoil();


	public void die() {
		spoil();
		System.out.println("Flower is spoiled thoroughly");
		count--;
		System.out.println("Flower is dead");
	}

	@Override
	public String toString() {
		return "Flower name: " + name + "; price: " + price;
	}

	public void showInfo() {
		System.out.printf("%13s %15s %6s", getId(), getName(), getPrice());
		System.out.println("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flower other = (Flower) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

}
