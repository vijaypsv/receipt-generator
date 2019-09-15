package com.taxes.receipt.model.impl;

import java.math.BigDecimal;

import com.taxes.receipt.model.interfaces.BasketItem;

public class BasketItemImpl implements BasketItem {

	protected BigDecimal price;
	protected String name;
	private Integer quantity;

	public BasketItemImpl(BigDecimal price, String name, Integer quantity) {
		super();
		this.price = price;
		this.name = name;
		this.quantity = quantity;
	}

	@Override
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public BigDecimal getPrice() {
		return this.price;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public BigDecimal getTaxPercent() {
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getTax() {
		return new BigDecimal(0);
	}

	@Override
	public BigDecimal getFullPrice() {
		return  scale(this.price.add(this.getTax()));
	}
}