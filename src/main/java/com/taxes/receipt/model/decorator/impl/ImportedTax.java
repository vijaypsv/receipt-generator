package com.taxes.receipt.model.decorator.impl;

import java.math.BigDecimal;

import com.taxes.receipt.model.decorator.BasketItemDecorator;
import com.taxes.receipt.model.interfaces.BasketItem;

public class ImportedTax extends BasketItemDecorator {

	public ImportedTax(BasketItem basketItem) {
		super(basketItem);
	}

	@Override
	public BigDecimal getTaxPercent() {
		return super.getTaxPercent().add(addImportedTax());
	}

	private BigDecimal addImportedTax() {
		return new BigDecimal(0.05);
	}
}
