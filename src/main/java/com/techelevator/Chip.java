package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Item {

	public Chip(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Crunch Crunch, Yum!";
	}
	
}
