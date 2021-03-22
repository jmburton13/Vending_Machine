package com.techelevator;

import org.junit.*;
import java.math.BigDecimal;

public class ChipTest {

	private Chip objectUnderTest;
		
	@Before
	public void reinstantiate() {
	this.objectUnderTest = new Chip("Pringles", new BigDecimal("3.05"));
	}
	
	@Test
	public void test_getName() {
		//assert
		Assert.assertEquals("Pringles", objectUnderTest.getName());
	}
	
	@Test
	public void test_getPrice() {
		Assert.assertEquals(new BigDecimal("3.05"), objectUnderTest.getPrice());
	}
	
	@Test
	public void test_getSound() {
		Assert.assertEquals("Crunch Crunch, Yum!", objectUnderTest.getSound());
	}

}
