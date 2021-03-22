package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Item {

	public Drink (String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Glug Glug, Yum!";
	}
	
}