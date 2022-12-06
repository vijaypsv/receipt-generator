package com.taxes.receipt.model;

import java.math.BigDecimal;

/**
 * BasketItem decorator for adding taxes
 * @author vijay
 *
 */
public abstract class BasketItemTaxDecorator extends BasketItem {
	private final BasketItem basketItem;

	protected BasketItemTaxDecorator(BasketItem basketItem) {
		super();
		this.basketItem = basketItem;
	}

	@Override
	public int getQuantity() {
		return basketItem.getQuantity();
	}

	@Override
	public BigDecimal getPrice() {
		return basketItem.getPrice();
	}

	@Override
	public String getName() {
		return basketItem.getName();
	}

	@Override
	public BigDecimal getTaxPercent() {
		return basketItem.getTaxPercent();
	}

	@Override
	public BigDecimal getTax() {
		BigDecimal fullTax = getTaxPercent().multiply(this.getPrice());
		return scale(round(fullTax));
	}

	@Override
	public BigDecimal getFullPrice() {
		return  scale(getPrice().add(this.getTax()));
	}
}
