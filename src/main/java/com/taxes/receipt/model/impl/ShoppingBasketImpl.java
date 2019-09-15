package com.taxes.receipt.model.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.taxes.receipt.model.interfaces.BasketItem;
import com.taxes.receipt.model.interfaces.ShoppingBasket;

public class ShoppingBasketImpl implements ShoppingBasket {
	
	private List<BasketItem> itemsList;	
	
	public ShoppingBasketImpl() {
		this( new ArrayList<BasketItem>());
	}

	public ShoppingBasketImpl(List<BasketItem> itemsList) {
		super();
		this.itemsList = itemsList;
	}
	
	@Override
	public ShoppingBasket add(BasketItem item) {
		this.itemsList.add(item);
		return this;
	}

	@Override
	public List<BasketItem> getItemsList() {
		return this.itemsList;
	}

	@Override
	public BigDecimal getTaxes() {
		
		return itemsList
				.stream()
				.map(BasketItem::getTax)
				.reduce(new BigDecimal(0), (a, b) -> a.add(b));
	}

	@Override
	public BigDecimal getTotal() {
		return itemsList
				.stream()
				.map(BasketItem::getFullPrice)
				.reduce(new BigDecimal(0), (a, b) -> a.add(b));
	}

}
