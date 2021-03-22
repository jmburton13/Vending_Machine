package com.techelevator;

import java.math.BigDecimal;
import org.junit.*;

public class DrinkTest {

	private Drink objectUnderTest;
		
	@Before
	public void reinstantiate() {
	this.objectUnderTest = new Drink("Diet Coke", new BigDecimal("1.25"));
	}
	
	@Test
	public void test_getName() {
		//assert
		Assert.assertEquals("Diet Coke", objectUnderTest.getName());
	}
	
	@Test
	public void test_getPrice() {
		Assert.assertEquals(new BigDecimal("1.25"), objectUnderTest.getPrice());
	}
	
	@Test
	public void test_getSound() {
		Assert.assertEquals("Glug Glug, Yum!", objectUnderTest.getSound());
	}

}
