package by.iba.florist.console;

import java.util.ArrayList;

import by.iba.florist.entity.Flower;

/**
 * Created by Tatsiana on 03.10.2016.
 */
public class Florist {

	private static ArrayList<Flower> productAvailableList = new ArrayList<>();

	public static void addToStock(Flower fl) {
		productAvailableList.add(fl);
	}

	public static ArrayList<Flower> getFlowerStock() {
		return productAvailableList;
	}

	public static void showProductAvailableList() {

		System.out.println("List of products in stock:");
		System.out.println("");
		System.out.printf("%13s %15s %6s", "Id", "Name", "Price");
		System.out.println("");
		System.out.printf("-------------------------------------");
		System.out.println("");

		for (Flower flower : productAvailableList) {
			flower.showInfo();
		}
	}

}
