/**
 * 
 */
package com.au.shoppingrus.rules;

import java.util.Map;

import com.au.shoppingrus.Product;

/**
 * 
 * Pricing Rule interface to calculate the price for scanned items based on
 * implemented rule. Three rules are implemented as part of this project: \
 * 1. BulkDiscountRule 
 * 2. ComplimentaryRule 
 * 3. QuantityDiscountRule
 * 
 * New rules can be implemented.
 * 
 * Note: This interface just calculates the price applying rules. It doesnt give
 * comparison like Actual Cost and Discounted Cost or a message to teller to add
 * complementary product if not in cart
 *
 *
 *	Possible enhancement:
 *		Multiple rules for same product - with priority either set at rule level or algorithm to find best offer and apply
 */
public interface PricingRule {

	double calculate(int quantity, Map<Product, Integer> items);
}
