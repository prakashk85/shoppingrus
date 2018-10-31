package com.au.shoppingrus.rules;

import java.util.Map;

import com.au.shoppingrus.Product;

/**
 * 
 * 
 * Rule: When the purchased quantity is more than a certain threshold, items
 * will be sold at a reduced price
 * 
 * Eg: Product A which usually costs $200, would cost only $150 if purchased
 * more than 4.
 *
 */

public class BulkDiscountRule implements PricingRule {
	private final int thresholdQuantity;
	private final double reducedUnitPrice;
	private final Product product;

	public BulkDiscountRule(Product product, int thresholdQuantity, double reducedUnitPrice) {
		this.thresholdQuantity = thresholdQuantity;
		this.reducedUnitPrice = reducedUnitPrice;
		this.product = product;
	}

	@Override
	public double calculate(int quantity, Map<Product, Integer> items) {
		if (quantity > this.thresholdQuantity) {
			return this.reducedUnitPrice * quantity;
		} else {
			return this.product.getUnitPrice() * quantity;
		}
	}

}
