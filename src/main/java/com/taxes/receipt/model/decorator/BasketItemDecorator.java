package com.taxes.receipt.model.decorator;

import java.math.BigDecimal;

import com.taxes.receipt.model.interfaces.BasketItem;

/**
 * Decorator Abtraction for adding taxes 
 * @author vijay
 *
 */
public abstract class BasketItemDecorator implements BasketItem {
	private BasketItem basketItem;

	public BasketItemDecorator(BasketItem basketItem) {
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
