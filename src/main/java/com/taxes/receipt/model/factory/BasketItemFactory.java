package com.taxes.receipt.model.factory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.taxes.receipt.model.TaxType;
import com.taxes.receipt.model.decorator.impl.ImportedTax;
import com.taxes.receipt.model.decorator.impl.NormalTax;
import com.taxes.receipt.model.impl.BasketItemImpl;
import com.taxes.receipt.model.interfaces.BasketItem;
import com.taxes.receipt.util.StringPool;

public class BasketItemFactory {

	private Map<String, TaxType> taxTypeMap = new HashMap<>();

	public BasketItemFactory() {
		// In the future get our tax type from external source maybe??
		taxTypeMap.put("book", TaxType.EXEMPT);
		taxTypeMap.put("music CD", TaxType.NORMAL);
		taxTypeMap.put("chocolate bar", TaxType.EXEMPT);
		taxTypeMap.put("box of chocolates", TaxType.EXEMPT);
		taxTypeMap.put("bottle of perfume", TaxType.NORMAL);
		taxTypeMap.put("packet of headache pills", TaxType.EXEMPT);
	}

	/**
	 * Creates a {@link com.taxes.receipt.model.interfaces.BasketItem
	 * BasketItem}
	 * 
	 * @param price
	 *            price of the item. I am asuming that this is the price of all
	 *            the items, and not only one. If that is the case this should
	 *            no work properly
	 * @param name
	 *            name of the item
	 * @param quantity
	 *            quantity
	 * @return {@link com.taxes.receipt.model.interfaces.BasketItem BasketItem}
	 *         created
	 */
	public BasketItem getBasketItem(BigDecimal price, String name, Integer quantity) {
		BasketItem item = new BasketItemImpl(price, name, quantity);

		// Check if its imported
		boolean imported = name.contains(StringPool.IMPORTED);
		if (imported) {
			item = new ImportedTax(item);
			name = name.replace(StringPool.IMPORTED, StringPool.BLANK);
		}

		// Check if it has taxes
		TaxType taxType = getTaxType(name);
		if (taxType.equals(TaxType.NORMAL)) {
			item = new NormalTax(item);
		}

		return item;
	}

	/**
	 * Gets the tax type of the item
	 * 
	 * @param name
	 *            of the item
	 * @return {@link com.taxes.receipt.model.TaxType TaxType} of the item
	 */
	private TaxType getTaxType(String name) {
		if (taxTypeMap.containsKey(name)) {
			return taxTypeMap.get(name);
		}
		// If its not on the list we apply the normal tax
		return TaxType.NORMAL;
	}

}
