package com.au.shoppingrus.rules;

import java.util.Map;

import com.au.shoppingrus.Product;

/**
 * 
 * 
 * Rule: When items are purchased in certain numbers, total cost is calculated
 * for less quantity
 * 
 * Eg: Buying 4 of Product A which usually costs $100 each, would result in
 * total bill of $300
 *
 */
public class QuantityDiscountRule implements PricingRule {
	private final int buyCount;
	private final int payCount;
	private final Product product;

	public QuantityDiscountRule(Product product, int buyCount, int payCount) {
		this.buyCount = buyCount;
		this.payCount = payCount;
		this.product = product;
	}

	@Override
	public double calculate(int quantity, Map<Product, Integer> items) {
		int pack = quantity / this.buyCount;
		int balance = quantity % this.buyCount;
		return (pack * payCount + balance) * this.product.getUnitPrice();
	}

}
