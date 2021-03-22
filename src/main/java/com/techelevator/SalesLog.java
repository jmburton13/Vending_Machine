package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesLog {

	private Map<String, Integer> totalSales = new HashMap<>();
	private File mapSource = new File("vendingmachine.csv");
	private BigDecimal grossSales;
	
	public SalesLog() throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(mapSource);
		
		grossSales = new BigDecimal("0.00");
		
		while(fileReader.hasNextLine()) {
			String fileLine = fileReader.nextLine();
			String[] fileLineArray = fileLine.split("\\|");
			
			String productName = fileLineArray[1];
			
			if (!totalSales.containsKey(productName)) {
				totalSales.put(productName, 0);
			}
		}
	}
	
	public Map<String, Integer> getTotalSales() {
		return totalSales;
	}
	
	public int getNumberOfSales(String keyInput) {
		return totalSales.get(keyInput);
	}
	
	public void newSale(String keyInput, BigDecimal price) {
		if (totalSales.containsKey(keyInput)) {
			int numberOfSales = getNumberOfSales(keyInput);
			numberOfSales++;
			totalSales.put(keyInput, numberOfSales);
			grossSales = grossSales.add(price);
			
		}
	}
	
	public BigDecimal getGrossSales() {
		return grossSales;
	}
	
	public void printSalesLog(String timeStamp) {
		File salesLogAtTime = new File("Sales_Log_at_" + (timeStamp.replace(" ", "_").replace(":", "_").replace("/", "_") +".txt"));
		
		try {
			FileWriter fileWriter = new FileWriter(salesLogAtTime, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for (String key : totalSales.keySet()) {
				printWriter.println(key + "|" + getNumberOfSales(key) + "\r");
				printWriter.flush();
			}
			
			printWriter.println("**TOTAL SALES** $" + getGrossSales());
			printWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
