package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;



public class InventoryTests {
	
	@Test
	public void contents_has_potato_crisps_and_details() throws FileNotFoundException {
		//Arrange
		Inventory inventory = new Inventory();
		BigDecimal expectedPrice = new BigDecimal("3.05");
			
		//Act
		String resultName = inventory.getContents().get("A1").peek().getName();
		BigDecimal resultPrice = inventory.getContents().get("A1").peek().getPrice();
		
		//Assert
		Assert.assertEquals("Potato Crisps", resultName);
		Assert.assertEquals(expectedPrice, resultPrice);
	}
	
	@Test
	public void contents_has_triplemint_and_details() throws FileNotFoundException {
		Inventory inventory = new Inventory();
		BigDecimal expectedPrice = new BigDecimal(".75");
			
		//Act
		String resultName = inventory.getContents().get("D4").peek().getName();
		BigDecimal resultPrice = inventory.getContents().get("D4").peek().getPrice();
		
		//Assert
		Assert.assertEquals("Triplemint", resultName);
		Assert.assertEquals(expectedPrice, resultPrice);
	}
	
	@Test
	public void popItem_at_A1_returns_a_Chip() throws FileNotFoundException {
		//Arrange
		Inventory inventory = new Inventory();
		BigDecimal expectedPrice = new BigDecimal("3.05");
		Chip expectedReturn = new Chip("Potato Crisps", expectedPrice);
		
		//Act
		Item result = inventory.popItem("A1");
		
		//Assert
		Assert.assertTrue(result.getClass() == expectedReturn.getClass());
	}
	
	
	@Test
	public void inventory_fills_stack_with_5_items() throws FileNotFoundException {
		//Arrange
		Inventory inventory = new Inventory();
		Chip expectedReturn = new Chip("Potato Crisps", new BigDecimal("3.05"));
		
		
		//Act
		inventory.popItem("A1");
		inventory.popItem("A1");
		inventory.popItem("A1");
		inventory.popItem("A1");
		Item result = inventory.popItem("A1");
		
		
		//Assert
		Assert.assertTrue("Test expected to return a Chip but did not", result.getClass() == expectedReturn.getClass());
	}
	
	
	@Test
	public void popItem_returns_null_when_stack_is_empty() throws FileNotFoundException {
		//Arrange
		Inventory inventory = new Inventory();
		
		//Act
		inventory.popItem("A1");
		inventory.popItem("A1");
		inventory.popItem("A1");
		inventory.popItem("A1");
		inventory.popItem("A1");
		Item result = inventory.popItem("A1");
		
		//Assert
		Assert.assertEquals(null, result);
	}
	
	// PRINTCONTENTS NOT TESTED VIA UNIT TEST BUT CAN BE CALLED IN THE MAIN METHOD FOR TEST

}
