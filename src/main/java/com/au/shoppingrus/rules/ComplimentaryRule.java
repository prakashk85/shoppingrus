package com.au.shoppingrus.rules;

import java.util.Map;

import com.au.shoppingrus.Product;

/**
 * 
 * 
 * Rule: When a particular product is purchased, another product will be sold at
 * a discounted rate (0 to <100%)
 * 
 * Eg: Product A costs 50% of original price if purchased along with Product B
 * Eg: Product C costs $0 if purchased with Product D
 *
 */

public class ComplimentaryRule implements PricingRule {

	private final int offerCount;
	private final double reducedUnitPrice;
	private final Product buyProduct;
	private final Product complementaryProduct;

	public ComplimentaryRule(Product complementaryProduct, Product buyProduct, int offerCount,
			double reducedUnitPrice) {
		this.offerCount = offerCount;
		this.reducedUnitPrice = reducedUnitPrice;
		this.buyProduct = buyProduct;
		this.complementaryProduct = complementaryProduct;
	}

	@Override
	public double calculate(int quantity, Map<Product, Integer> items) {
		int buyCount = items.containsKey(buyProduct) ? items.get(buyProduct) : 0;
		int calcCount = buyCount / offerCount;

		if (calcCount >= quantity) {
			return quantity * reducedUnitPrice;
		} else {
			return (calcCount * reducedUnitPrice) + ((quantity - calcCount) * complementaryProduct.getUnitPrice());
		}
	}

}
