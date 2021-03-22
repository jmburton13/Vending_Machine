package com.techelevator;

import java.math.BigDecimal;
import org.junit.*;

public class GumTest {

	private Gum objectUnderTest;
		
	@Before
	public void reinstantiate() {
	this.objectUnderTest = new Gum("Big Red", new BigDecimal("1.20"));
	}
	
	@Test
	public void test_getName() {
		//assert
		Assert.assertEquals("Big Red", objectUnderTest.getName());
	}
	
	@Test
	public void test_getPrice() {
		Assert.assertEquals(new BigDecimal("1.20"), objectUnderTest.getPrice());
	}
	
	@Test
	public void test_getSound() {
		Assert.assertEquals("Chew Chew, Yum!", objectUnderTest.getSound());
	}

}
