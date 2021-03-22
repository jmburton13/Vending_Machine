package com.techelevator;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

public class SalesLogTests {
	
	@Test
	public void constructor_creates_map() throws FileNotFoundException {
		//Arrange
		SalesLog salesLog = new SalesLog();
		
		//Assert
		Assert.assertEquals(salesLog.getTotalSales().getClass(), (HashMap.class));
	}
	
	@Test
	public void totalSales_contains_expected_keys() throws FileNotFoundException {
		//Arrange
		SalesLog salesLog = new SalesLog();
		
		//Assert
		Assert.assertTrue("Expected to find Potato Crisps but did not", salesLog.getTotalSales().containsKey("Potato Crisps"));
		Assert.assertTrue("Expected to find Triplemint but did not",  salesLog.getTotalSales().containsKey("Triplemint"));
	}
	
	@Test
	public void newSale_updates_quantity_sold() throws FileNotFoundException {
		//Arrange
		SalesLog salesLog = new SalesLog();
		String keyInput = "Triplemint";
		BigDecimal price = new BigDecimal("0.75");
		int expectedQuantity = 1;
		
		//Act
		salesLog.newSale(keyInput, price);
		
		//Assert
		Assert.assertEquals(expectedQuantity, salesLog.getNumberOfSales(keyInput));
		
	}
	
	@Test
	public void newSale_updates_grossSales() throws FileNotFoundException {
		//Arrange
		SalesLog salesLog = new SalesLog();
		String keyInput = "Triplemint";
		BigDecimal price = new BigDecimal("0.75");
		BigDecimal expectedGrossSales = new BigDecimal("2.25");
		
		//Act
		salesLog.newSale(keyInput, price);
		salesLog.newSale(keyInput, price);
		salesLog.newSale(keyInput, price);
		
		//Assert
		Assert.assertEquals(expectedGrossSales, salesLog.getGrossSales());
	}
}
