package com.taxes.receipt.model.parser;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.taxes.receipt.model.factory.BasketItemFactory;
import com.taxes.receipt.model.interfaces.BasketItem;

public class BasketItemParser {

	private static final String PATTERN = "(\\d+)\\s(.*?)\\sat\\s(\\d+\\.\\d{2}?)";

	private BasketItemFactory basketItemFactory;

	public BasketItemParser() {
		basketItemFactory = new BasketItemFactory();
	}

	public BasketItem parse(String input) {

		BasketItem basketItem = null;

		Matcher matcher = Pattern.compile(PATTERN).matcher(input);

		if (matcher.matches()) {
			int quantity = Integer.parseInt(matcher.group(1));
			String name = matcher.group(2);
			BigDecimal price = new BigDecimal(matcher.group(3));

			basketItem = basketItemFactory.getBasketItem(price, name, quantity);
		}

		return basketItem;
	}
}