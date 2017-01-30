package by.iba.florist.entity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@XmlRootElement
@JsonTypeInfo(use = Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
public class Catalog implements Serializable {

	private String file_name;
	private String file_type;
	private ArrayList<Flower> productDatabase;
	
	{
		this.productDatabase = new ArrayList<Flower>();
	}
	
	public Catalog () {
		
	}
	
	public File initFileCatalog(String file_name, String file_type) {
		String filePath = new String("D:/java_training/web/workspace/FloristWeb/" + file_name + "." + file_type);	
		File file = new File(filePath);
		System.out.println("File is initialized: " + file.getAbsolutePath());
		return file;
		
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	@XmlElementWrapper(name = "productList")
	@XmlElement(name = "flower")
	public ArrayList<Flower> getProductDatabase() {
		return productDatabase;
	}
	
	public void showList() {
		int i = 1;
		for (Flower fl : this.productDatabase) {
			System.out.println("Flower " + i + ": " + fl.toString());
			i++;
		}
	}
	
	public void addItem(Flower fl) {
		this.getProductDatabase().add(fl);
	}

	public void setProductDatabase(ArrayList<Flower> productDatabase) {
		this.productDatabase = productDatabase;
	}

	@Override
	public String toString() {
		return "Catalog [file_name=" + file_name + ", file_type=" + file_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file_name == null) ? 0 : file_name.hashCode());
		result = prime * result + ((file_type == null) ? 0 : file_type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Catalog other = (Catalog) obj;
		if (file_name == null) {
			if (other.file_name != null)
				return false;
		} else if (!file_name.equals(other.file_name))
			return false;
		if (file_type == null) {
			if (other.file_type != null)
				return false;
		} else if (!file_type.equals(other.file_type))
			return false;
		return true;
	}	

	
}
