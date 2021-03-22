package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Item {

	public Gum (String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public String getSound() {
		return "Chew Chew, Yum!";
	}
	
}