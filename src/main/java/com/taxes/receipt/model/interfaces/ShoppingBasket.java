package com.taxes.receipt.model.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.taxes.receipt.util.StringPool;

public interface ShoppingBasket {

	/**
	 * Gets the list of all the items in the basket
	 * 
	 * @return {@link java.util.List List} of
	 *         {@link com.taxes.receipt.model.interfaces.BasketItem BasketItem}
	 */
	List<BasketItem> getItemsList();

	/**
	 * Add a new {@link com.taxes.receipt.model.interfaces.BasketItem
	 * BasketItem} to the basket
	 * 
	 * @param item
	 *            {@link com.taxes.receipt.model.interfaces.BasketItem
	 *            BasketItem} to add
	 * @return {@link com.taxes.receipt.model.interfaces.ShoppingBasket
	 *         ShoppingBasket} with the new item added
	 */
	ShoppingBasket add(BasketItem item);

	/**
	 * Returns all the taxes applid to the basket
	 * 
	 * @return taxes applied
	 */
	BigDecimal getTaxes();

	/**
	 * Returns the full price of the basket
	 * 
	 * @return fullPrice
	 */
	BigDecimal getTotal();

	/**
	 * Generates the String for the receipt
	 * 
	 * @return receipt for the basket
	 */
	default String getReceipt() {
		StringBuilder receiptBuilder = new StringBuilder();

		receiptBuilder.append(StringPool.RECEIPT);
		for (BasketItem item : getItemsList()) {
			receiptBuilder.append(
					String.format(StringPool.ITEM_FORMAT, item.getQuantity(), item.getName(), item.getFullPrice()));
		}

		receiptBuilder.append(String.format(StringPool.SALES_TAXES + "%.2f\n", getTaxes()));
		receiptBuilder.append(String.format(StringPool.TOTAL + "%.2f", getTotal()));

		return receiptBuilder.toString();
	}
}