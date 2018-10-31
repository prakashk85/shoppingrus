package com.au.shoppingrus;

public enum Products {
	IPAD("ipd", "Super iPad", 549.99), MBP("mbp", "MacBook Pro", 1399.99), ATV("atv", "Apple TV", 109.50), VGA("vga",
			"VGA Adapter", 30.00);

	private Product product;

	Products(String id, String desc, double unitPrice) {
		this.product = new Product(id, desc, unitPrice);
	}

	public Product getProduct() {
		return product;
	}
}
