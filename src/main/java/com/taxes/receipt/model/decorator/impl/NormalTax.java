package com.taxes.receipt.model.decorator.impl;

import java.math.BigDecimal;

import com.taxes.receipt.model.decorator.BasketItemDecorator;
import com.taxes.receipt.model.interfaces.BasketItem;

public class NormalTax extends BasketItemDecorator {
	
	public NormalTax(BasketItem basketItem) {
		super(basketItem);
	}
	
	@Override
	public BigDecimal getTaxPercent() {
		return super.getTaxPercent().add(addNormalTax());
	}
	
	private BigDecimal addNormalTax() {		
		return new BigDecimal(0.1);
	}
}
