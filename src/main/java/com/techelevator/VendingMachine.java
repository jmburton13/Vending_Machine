package com.techelevator;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class VendingMachine {

	private static Inventory inventory;
	private static SalesLog salesLog;
	private static BigDecimal balance = new BigDecimal("0.00");
	private static Scanner keyboardInput = new Scanner(System.in);
	private static File log = new File("log.txt");
	private static FileWriter fileWriter;
	private static PrintWriter printWriter;
	
	
	public static void main(String[] args) throws IOException { //main method goes here
		
		
		//Construct new inventory
		inventory = new Inventory();
		salesLog = new SalesLog();
		fileWriter = new FileWriter(log, true);
		printWriter = new PrintWriter(fileWriter);
		printWriter.println("\r" + ">" + timeStamp() + " Boot Sequence Initiated");
		printWriter.flush();
		

		
		//invoke greeting/welcome banner
		welcomeBanner();
		
		//startMenu displays first options
		startMenu();
		
		
	
	}
	
	
	
	
	
	
	
	
	public static void welcomeBanner() {
		System.out.println("=========================================");
		System.out.println("=== WELCOME TO THE VENDO-MATIC 800!!! ===");
		System.out.println("=========================================");
		System.out.println();
	}
	/*
	public static Inventory populateMachine() throws FileNotFoundException {
		Inventory inventory = new Inventory();
		return inventory;
	}
	*/
	
	public static void startMenu() {
		
		System.out.println("> (1) Display Vending Machine Items");
		System.out.println("> (2) Purchase");
		System.out.println("> (3) Exit");
		
		//refactor as while loop w/ while input != 2 || 3
		
		String userChoice = keyboardInput.nextLine();
		
		if (userChoice.equals("1")) {
			try {
				inventory.printContents();
			} catch (FileNotFoundException e) {
				e.printStackTrace(); //maybe change?? 
			}
			System.out.println();
			startMenu();
			
		}
		else if (userChoice.equals("2")) {
			purchaseMenu();
			//take user to purchase steps
			//new method advances program
		}
		else if (userChoice.equals("3")) {
			thankYou();
		}
		else if (userChoice.equals("4")) {
			System.out.println("Generating Sales Report"); //change this?
			salesLog.printSalesLog(timeStamp());
			thankYou();
		}
		else {
			System.out.println("Invalid input!");
			startMenu();
		}
	}
	
	
	public static void purchaseMenu() {
		//balance = new BigDecimal("0.00");
		
		//Scanner purchaseMenuChoice = new Scanner(System.in);
		
		System.out.println("> (1) Feed Money");
		System.out.println("> (2) Select Product");
		System.out.println("> (3) Finish Transaction");
		System.out.println();
		System.out.println("Current money provided: $" + balance);
		
		String userPurchaseChoice = keyboardInput.nextLine();
		
		if (userPurchaseChoice.equals("1")) {
			feedMoney();
			//need way back to menu
		}
		else if (userPurchaseChoice.equals("2")) {
			purchaseItem();
			//do something else
		}
		else if (userPurchaseChoice.equals("3")) {
			finishTransaction();

			//do the third thing
		}
		else {
			System.out.println("Invalid input!");
			purchaseMenu();
		}
		
		
	}
	
	public static void feedMoney() {
		//Scanner insertMoney = new Scanner(System.in);
		
		System.out.println("Your balance is $" + balance);
		System.out.println();
		
		System.out.println("> (1) Insert $1");
		System.out.println("> (2) Insert $2");
		System.out.println("> (5) Insert $5");
		System.out.println("> (10) Insert $10");
		System.out.println("> (X) Done inserting.");
		
		String dollaDollaBillsYall = keyboardInput.nextLine();
		
		if (dollaDollaBillsYall.equals("1") || dollaDollaBillsYall.equals("2") || dollaDollaBillsYall.equals("5") || dollaDollaBillsYall.equals("10")) {
			BigDecimal billInputValue = new BigDecimal(dollaDollaBillsYall + ".00");
			balance = balance.add(billInputValue);
			
			//print out timestamp, action type, amount added, and balance, e.g. 01/01/2016 12:00:00 PM FEED MONEY: $5.00 $5.00
			printWriter.println("\r" + ">" + timeStamp() + " FEED MONEY $" + billInputValue + " $" + balance);
			printWriter.flush();
			feedMoney();
		}
		else if (dollaDollaBillsYall.toLowerCase().equals("x")) {
			purchaseMenu();
		}
		else if (dollaDollaBillsYall.equals("20")) {
			System.out.println("This machine doesn't take $20s!!");
			feedMoney();
		}
		else {
			System.out.println("Invalid input!");
			System.out.println("=========================================");
			feedMoney();
		}
		
	}
	
	
	public static void purchaseItem() {
		//Scanner itemChoice = new Scanner(System.in);
		
		try {
			inventory.printContents();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); //maybe change per earlier??
		}
		
		System.out.println("What snack strikes your fancy today?");
		System.out.println("====== Or press \"X\" to exit ======");
		
		String userChoice = keyboardInput.nextLine();
		
		userChoice = userChoice.toUpperCase(); //allows "a4" input
		
		if (userChoice.toLowerCase().equals("x")) {
			purchaseMenu();
		}
		
		//if the user chooses something that isn't in the map
		if (!inventory.getContents().containsKey(userChoice)) {
			System.out.println("Invalid selection!");
			System.out.println("=========================================");
			purchaseItem();
		}

		
		Item dispensedItem = inventory.popItem(userChoice);
		
		if (dispensedItem != null) {
			//determines if balance covers the cost of options
			if (balance.compareTo(dispensedItem.getPrice()) > 0) {
				System.out.println(dispensedItem.getName());
				System.out.println(dispensedItem.getPrice());
				
				BigDecimal preBalance = balance;
				
				balance = balance.subtract(dispensedItem.getPrice());
				System.out.println("Your balance is $" + balance);
				
				System.out.println(dispensedItem.getSound());
				
				//print to log: time stamp, item name, item position, starting balance, and balance after purchase  >01/01/2016 12:00:20 PM Crunchie B4 $10.00 $8.50
				printWriter.println("\r" + ">" + timeStamp() + " " + dispensedItem.getName() + " " + userChoice + " $" + preBalance + " $" + balance);
				printWriter.flush();
				
				salesLog.newSale(dispensedItem.getName(), dispensedItem.getPrice());

				purchaseMenu();
			}
			else {
				inventory.getContents().get(userChoice).push(dispensedItem);
				System.out.println("You do not have enough balance to buy that!");
				System.out.println("======== Please insert more money ========");
				System.out.println();
				purchaseItem();
			}
		}
		else {
			System.out.println("Invalid selection!");
			System.out.println("=========================================");
			purchaseItem();
		}
	}
	
	public static void finishTransaction() {
		//return change
		System.out.println("Change dispensed: $" + balance);

		BigDecimal preBalance = balance;
		
		BigDecimal quarterValue = new BigDecimal("0.25");
		int quarters = 0;
		
		BigDecimal dimeValue = new BigDecimal("0.10");
		int dimes = 0;
		
		BigDecimal nickelValue = new BigDecimal("0.05");
		int nickels = 0;
		
		while(balance.compareTo(quarterValue) >= 0) {
			quarters++;
			balance = balance.subtract(quarterValue);
		}
		
		while(balance.compareTo(dimeValue) >= 0) {
			dimes++;
			balance = balance.subtract(dimeValue);
		}
		
		while(balance.compareTo(nickelValue) >= 0) {
			nickels++;
			balance = balance.subtract(nickelValue);
		}
		
		System.out.println("Quarters: " + quarters + ", " + "Dimes: " + dimes + ", " + "Nickels: " + nickels);
		
		//zero out balance
		balance = balance.subtract(balance);
		
		//print to log timestamp, GIVE CHANGE, their current balance, and end balance $0.00 >01/01/2016 12:01:35 PM GIVE CHANGE: $7.50 $0.00
		printWriter.println("\r" + ">" + timeStamp() + " GIVE CHANGE $" + preBalance + " $" + balance);
		printWriter.flush();
		
		//run thankYou() to end
		thankYou();
	}


	public static void thankYou() {
		System.out.println("Thank you for using VENDO-MATIC 800!");
		System.out.println("===== Please enjoy again soon! =====");
		System.out.println();
		System.out.println();
		welcomeBanner();
		System.out.println();
		System.out.println();
		startMenu();	
	}
	
	public static String timeStamp() {
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		return formatter.format(timeStamp);
	}
	
}
