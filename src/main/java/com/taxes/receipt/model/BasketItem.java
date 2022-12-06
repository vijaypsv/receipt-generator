package com.taxes.receipt.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BasketItem {
	private static final double DEFAULT_ROUND = 0.05;

    protected BigDecimal price;
    protected String name;
    private Integer quantity;

    public BasketItem() {
    }

    public BasketItem(BigDecimal price, String name, Integer quantity) {
        super();
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

	/**
	 * Returns the quantity
	 * 
	 * @return quantity
	 */
    public int getQuantity() {
        return this.quantity;
    }

	/**
	 * Returns the price of all the items bought (taking quantity into account)
	 * 
	 * @return price of the items
	 */
    public BigDecimal getPrice() {
        return this.price;
    }

	/**
	 * Returns the name of the item
	 * 
	 * @return name of the item
	 */
    public String getName() {
        return this.name;
    }

	/**
	 * Gets the tax percent applied to the item
	 * 
	 * @return tax percent applied
	 */
    public BigDecimal getTaxPercent() {
        return new BigDecimal(0);
    }

	/**
	 * Gets the tax applied to the item (taking quantity into account)
	 * 
	 * @return tax applied (taking quantity into account)
	 */
    public BigDecimal getTax() {
        return new BigDecimal(0);
    }

	/**
	 * Gets the full price (including taxes) of the item (taking quantity into
	 * account)
	 * 
	 * @return full price (including taxes) (taking quantity into account)
	 */
    public BigDecimal getFullPrice() {
        return  scale(this.price.add(this.getTax()));
    }

	/**
	 * Scales a {@link java.math.BigDecimal BigDecimal} value
	 * 
	 * @param value
	 *            {@link java.math.BigDecimal BigDecimal} to scale
	 * @return scaled {@link java.math.BigDecimal BigDecimal}
	 */
    protected BigDecimal scale(BigDecimal value) {
		return value.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Rounds a {@link java.math.BigDecimal BigDecimal} value
	 * 
	 * @param value
	 *            {@link java.math.BigDecimal BigDecimal} to round
	 * @return rounded {@link java.math.BigDecimal BigDecimal}
	 */
    protected BigDecimal round(BigDecimal value) {
		BigDecimal rounding = BigDecimal.valueOf(DEFAULT_ROUND);
		return rounding.signum() == 0 ? value : (value.divide(rounding, 0, RoundingMode.UP)).multiply(rounding);
	}
}
