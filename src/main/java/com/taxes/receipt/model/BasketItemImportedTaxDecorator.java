package com.taxes.receipt.model;

import java.math.BigDecimal;

public class BasketItemImportedTaxDecorator extends BasketItemTaxDecorator {

	public BasketItemImportedTaxDecorator(BasketItem basketItem) {
		super(basketItem);
	}

	@Override
	public BigDecimal getTaxPercent() {
		return super.getTaxPercent().add(addImportedTax());
	}

	private BigDecimal addImportedTax() {
		return BigDecimal.valueOf(0.05);
	}
}
