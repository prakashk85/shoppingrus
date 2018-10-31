package com.au.shoppingrus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.au.shoppingrus.rules.BulkDiscountRule;
import com.au.shoppingrus.rules.ComplimentaryRule;
import com.au.shoppingrus.rules.PricingRule;
import com.au.shoppingrus.rules.QuantityDiscountRule;

public class Checkout {

	// mapping of each product & their quantities in the cart
	private Map<Product, Integer> items;

	// mapping of rules for applicable products
	private final Map<Product, PricingRule> pricingRules;

	public Checkout(Map<Product, PricingRule> pricingRules) {
		this.items = new LinkedHashMap<>();
		this.pricingRules = pricingRules;
	}

	/*
	 * Clears the items in the cart (map)
	 */
	public void clearItems() {
		this.items = new LinkedHashMap<>();
	}

	/*
	 * Adds item to the cart (map)
	 */
	public void scan(Product p) {
		items.put(p, items.containsKey(p) ? items.get(p) + 1 : 1);
	}

	/*
	 * Calculates total bill amount for the cart applying rules on each Product,
	 * if any
	 */
	public double total() {
		double total = 0;

		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			PricingRule rule = pricingRules.get(entry.getKey());

			if (rule != null) {
				total += rule.calculate(entry.getValue(), items);
			} else {
				total += entry.getKey().getUnitPrice() * entry.getValue();
			}

		}

		return total;
	}

	/*
	 * Configures rules for the session
	 * 
	 */
	public static Map<Product, PricingRule> configRules() {
		Map<Product, PricingRule> rulesMap = new HashMap<>();

		// Rule for iPad which will be sold at $499.99 instead of $599.99 when
		// purchased more than 4
		rulesMap.put(Products.IPAD.getProduct(), new BulkDiscountRule(Products.IPAD.getProduct(), 4, 499.99));

		// Rule for apple tv which will have 3 for 2 deal
		rulesMap.put(Products.ATV.getProduct(), new QuantityDiscountRule(Products.ATV.getProduct(), 3, 2));

		// Rule for vga which will be free for macbook pro
		rulesMap.put(Products.VGA.getProduct(),
				new ComplimentaryRule(Products.VGA.getProduct(), Products.MBP.getProduct(), 1, 0));

		return rulesMap;
	}

	public static void main(String[] args) {
		Checkout co = new Checkout(configRules());

		// 3 apple tvs - 3 for 2: will cost only 2 X apple tv
		// 1 VGA - no discount
		co.scan(Products.ATV.getProduct());
		co.scan(Products.ATV.getProduct());
		co.scan(Products.ATV.getProduct());
		co.scan(Products.VGA.getProduct());

		System.out.println(co.total());

		co.clearItems();

		// 5 iPads - each at price of $499.99
		// 2 apple tvs - no discount
		co.scan(Products.ATV.getProduct());
		co.scan(Products.IPAD.getProduct());
		co.scan(Products.IPAD.getProduct());
		co.scan(Products.ATV.getProduct());
		co.scan(Products.IPAD.getProduct());
		co.scan(Products.IPAD.getProduct());
		co.scan(Products.IPAD.getProduct());

		System.out.println(co.total());

		co.clearItems();

		// 1 VGA along with MacBook Pro - free
		// 1 MacBook Pro & iPad - no discount
		co.scan(Products.MBP.getProduct());
		co.scan(Products.VGA.getProduct());
		co.scan(Products.IPAD.getProduct());

		System.out.println(co.total());

		co.clearItems();

		// Add different products in different quantities to calculate offer
		// prices

		// do something here ...
	}

}