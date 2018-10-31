package com.au.shoppingrus.rules;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.au.shoppingrus.Product;
import com.au.shoppingrus.Products;

public class BulkDiscountRuleTest {
	private PricingRule pricingRule;
	private Product product;
	private double reducedPrice;
	private int threshold;

	@Before
	public void setUp() throws Exception {
		product = Products.ATV.getProduct();
		reducedPrice = 100;
		threshold = 4;
		pricingRule = new BulkDiscountRule(product, threshold, reducedPrice);
	}

	@Test
	public void test_moreThanThreshold() {
		int quantity = threshold + 2;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals(quantity * reducedPrice, cost, 0);
	}

	@Test
	public void test_lessThanThreshold() {
		int quantity = threshold - 1;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals(quantity * product.getUnitPrice(), cost, 0);
	}

	@Test
	public void test_atThreshold() {
		int quantity = threshold;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals(quantity * product.getUnitPrice(), cost, 0);
	}

}
