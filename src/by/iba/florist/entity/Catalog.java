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

	private String fileName;
	private String fileType;
	private ArrayList<Flower> productDatabase;
	
	{
		this.productDatabase = new ArrayList<Flower>();
	}
	
	public Catalog () {
		
	}
	
	public File initFileCatalog(String fileName, String fileType) {
		String filePath = new String("D:/java_training/web/workspace/FloristWeb/" + fileName + "." + fileType);	
		File file = new File(filePath);
		return file;	
	}

	public String getfileName() {
		return fileName;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}

	public String getfileType() {
		return fileType;
	}

	public void setfileType(String fileType) {
		this.fileType = fileType;
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
		return "Catalog [fileName=" + fileName + ", fileType=" + fileType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
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
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileType == null) {
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		return true;
	}	

	
}
