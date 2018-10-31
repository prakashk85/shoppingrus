package com.au.shoppingrus.rules;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.au.shoppingrus.Product;
import com.au.shoppingrus.Products;

public class ComplimentaryRuleTest {

	private int offerCount;
	private double reducedUnitPrice;
	private Product buyProduct;
	private Product complementaryProduct;
	private Map<Product, Integer> items;
	private PricingRule pricingRule;
	private int buyQuantity;

	@Before
	public void setUp() throws Exception {
		offerCount = 1;
		buyQuantity = 3;
		reducedUnitPrice = 0;
		buyProduct = Products.MBP.getProduct();
		complementaryProduct = Products.VGA.getProduct();
		items = new LinkedHashMap<>();
		items.put(buyProduct, buyQuantity);
		pricingRule = new ComplimentaryRule(complementaryProduct, buyProduct, offerCount, reducedUnitPrice);
	}

	@Test
	public void test_sameQuantity() {
		int quantity = buyQuantity;
		double cost = pricingRule.calculate(quantity, items);
		assertEquals(quantity * reducedUnitPrice, cost, 0);
	}

	@Test
	public void test_lessQuantity() {
		int quantity = buyQuantity - 1;
		double cost = pricingRule.calculate(quantity, items);
		assertEquals(quantity * reducedUnitPrice, cost, 0);
	}

	@Test
	public void test_moreQuantity() {
		int quantity = buyQuantity + 2;
		double cost = pricingRule.calculate(quantity, items);
		assertEquals(buyQuantity * reducedUnitPrice + 2 * complementaryProduct.getUnitPrice(), cost, 0);
	}

}
