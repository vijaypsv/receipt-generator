package com.taxes.receipt.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.taxes.receipt.constants.ReceiptConstants;

public class ShoppingBasket {
    private final List<BasketItem> itemsList;

    public ShoppingBasket(List<BasketItem> itemsList) {
        super();
        this.itemsList = itemsList;
    }

    public ShoppingBasket() {
        this( new ArrayList<>());
    }

    /**
	 * Gets the list of all the items in the basket
	 * 
	 * @return {@link java.util.List List} of
	 *         {@link BasketItem BasketItem}
	 */
    public List<BasketItem> getItemsList() {
        return this.itemsList;
    }

	/**
	 * Add a new {@link BasketItem
	 * BasketItem} to the basket
	 * 
	 * @param item
	 *            {@link BasketItem
	 *            BasketItem} to add
	 * @return {@link ShoppingBasket
	 *         ShoppingBasket} with the new item added
	 */
    public ShoppingBasket add(BasketItem item) {
        this.itemsList.add(item);
        return this;
    }

	/**
	 * Returns all the taxes applid to the basket
	 * 
	 * @return taxes applied
	 */
    public BigDecimal getTaxes() {
        return itemsList
            .stream()
            .map(BasketItem::getTax)
            .reduce(new BigDecimal(0), (a, b) -> a.add(b));
    }

	/**
	 * Returns the full price of the basket
	 * 
	 * @return fullPrice
	 */
    public BigDecimal getTotal() {
        return itemsList
            .stream()
            .map(BasketItem::getFullPrice)
            .reduce(new BigDecimal(0), (a, b) -> a.add(b));
    }

	/**
	 * Generates the String for the receipt
	 * 
	 * @return receipt for the basket
	 */
	public String getReceipt() {
		StringBuilder receiptBuilder = new StringBuilder();

		receiptBuilder.append(ReceiptConstants.RECEIPT);
		for (BasketItem item : getItemsList()) {
			receiptBuilder.append(
					String.format(ReceiptConstants.ITEM_FORMAT, item.getQuantity(), item.getName(), item.getFullPrice()));
		}

		receiptBuilder.append(String.format(ReceiptConstants.SALES_TAXES + "%.2f\n", getTaxes()));
		receiptBuilder.append(String.format(ReceiptConstants.TOTAL + "%.2f", getTotal()));

		return receiptBuilder.toString();
	}
}