package com.au.shoppingrus.rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.au.shoppingrus.Product;
import com.au.shoppingrus.Products;

public class QuantityDiscountRuleTest {
	private PricingRule pricingRule;
	private Product product;
	private int buyCount;
	private int payCount;

	@Before
	public void setUp() throws Exception {
		product = Products.IPAD.getProduct();
		buyCount = 3;
		payCount = 2;
		pricingRule = new QuantityDiscountRule(product, buyCount, payCount);
	}

	@Test
	public void test_sameQuantity() {
		int quantity = buyCount;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals(payCount * product.getUnitPrice(), cost, 0);
	}

	@Test
	public void test_multipleBundle() {
		int numBundle = 2;
		int quantity = buyCount * numBundle;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals(payCount * numBundle * product.getUnitPrice(), cost, 0);
	}

	@Test
	public void test_lessQuantity() {
		int quantity = buyCount - 1;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals(quantity * product.getUnitPrice(), cost, 0);
	}

	@Test
	public void test_moreQuantity() {
		int quantity = buyCount + 1;
		double cost = pricingRule.calculate(quantity, null);
		assertEquals((payCount + 1) * product.getUnitPrice(), cost, 0);
	}

}
