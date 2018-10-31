package com.au.shoppingrus.rules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BulkDiscountRuleTest.class, ComplimentaryRuleTest.class, QuantityDiscountRuleTest.class })
public class PricingRulesTestSuite {

}
