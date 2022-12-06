package com.taxes.receipt.model;

import java.math.BigDecimal;

public class BasketItemNormalTaxDecorator extends BasketItemTaxDecorator {
	
	public BasketItemNormalTaxDecorator(BasketItem basketItem) {
		super(basketItem);
	}
	
	@Override
	public BigDecimal getTaxPercent() {
		return super.getTaxPercent().add(addNormalTax());
	}
	
	private BigDecimal addNormalTax() {		
		return BigDecimal.valueOf(0.1);
	}
}
