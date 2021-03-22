package com.techelevator;

import java.math.BigDecimal;
import org.junit.*;

public class CandyTest {

	private Candy objectUnderTest;
		
	@Before
	public void reinstantiate() {
	this.objectUnderTest = new Candy("Reeses", new BigDecimal("2.25"));
	}
	
	@Test
	public void test_getName() {
		//assert
		Assert.assertEquals("Reeses", objectUnderTest.getName());
	}
	
	@Test
	public void test_getPrice() {
		Assert.assertEquals(new BigDecimal("2.25"), objectUnderTest.getPrice());
	}
	
	@Test
	public void test_getSound() {
		Assert.assertEquals("Munch Munch, Yum!", objectUnderTest.getSound());
	}

}
