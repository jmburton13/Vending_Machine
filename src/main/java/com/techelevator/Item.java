package com.techelevator;

import java.math.BigDecimal;

public abstract class Item {

	private String name;
	private BigDecimal price;
	
	//constructor
	public Item (String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	//extends to child classes
	public abstract String getSound();
	
	//getters
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
}
