package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Inventory {
	private Map<String, Stack<Item>> contents = new HashMap<>();
	private File file = new File("vendingmachine.csv");
	
	public Inventory() throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(file);
		
		while(fileReader.hasNextLine()) {
			String fileLine = fileReader.nextLine();
			String[] fileLineArray = fileLine.split("\\|");
			
			String code = fileLineArray[0];
			String name = fileLineArray[1];
			BigDecimal price = new BigDecimal(fileLineArray[2]);
			String type = fileLineArray[3];
			
			if (contents.containsKey(code) == false) {
				Stack<Item> stack = new Stack<>();
				contents.put(code, stack);
			}
			
			Item newItem;
			
			if (type.equals("Chip")) {newItem = new Chip(name, price);}
			else if (type.equals("Candy")) {newItem = new Candy(name, price);}
			else if (type.equals("Gum")) {newItem = new Gum(name, price);}
			else if (type.equals("Drink")) {newItem = new Drink(name, price);}
			else newItem = null;
		
			
			
			if (newItem != null) {
				for (int i = 0; i < 5; i++) {contents.get(code).push(newItem);}
			}
				
		}
		
	}

	public Map<String, Stack<Item>> getContents() {
		return contents;
	}
	
	public int getStackSize(String code) {
		int returnSize = contents.get(code).size();
		return returnSize;
	}
	
	public Item popItem(String code) {
		try {
			Item returnItem = contents.get(code).pop();
			return returnItem;
		} catch (EmptyStackException e) {
			return null;
		} 
	}
	
	public void printContents() throws FileNotFoundException {
		Scanner fileReader = new Scanner(file);
				
			while(fileReader.hasNextLine()) {
				String fileLine = fileReader.nextLine();
				String[] fileLineArray = fileLine.split("\\|");
				
				String code = fileLineArray[0];
//				String name = fileLineArray[1]; //COMMENTED OUT BECAUSE UNNEEDED VARIABLES
//				BigDecimal price = new BigDecimal(fileLineArray[2]);
//				String type = fileLineArray[3];
				
//				if (contents.get(code) == null || contents.get(code).peek() == null) {
//					System.out.println(fileLine + " SOLD OUT");
//				}
				
				if (contents.get(code).size() == 0) {
					System.out.println(fileLine + " Quantity: " + "SOLD OUT");
				}
				else {
					System.out.println(fileLine + " Quantity: " + contents.get(code).size());
				}

				
			}
		
		
		
	}
//		for (String key : contents.keySet()) { COMMENTED OUT BECAUSE WE WERE UNABLE TO PRINT THE NAME FOR AN EMPTY STACK
//			
//			if (contents.get(key).isEmpty() == false) {
//			
//			String printLine = "";
//			printLine += key + " | " + contents.get(key).peek().getName() + " | " + contents.get(key).peek().getPrice() + " | " + getStackSize(key); // print key | .getName | .getPrice | quantity
//			System.out.println(printLine);
//			
//			} else { System.out.println("");
//		}
//
//	}
//	
//}
}

