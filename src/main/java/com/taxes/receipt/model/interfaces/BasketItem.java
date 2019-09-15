package com.taxes.receipt.model.interfaces;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface BasketItem {

	public static final double DEFAULT_ROUND = 0.05;

	/**
	 * Returns the quantity
	 * 
	 * @return quantity
	 */
	int getQuantity();

	/**
	 * Returns the price of all the items bought (taking quantity into account)
	 * 
	 * @return price of the items
	 */
	BigDecimal getPrice();

	/**
	 * Returns the name of the item
	 * 
	 * @return name of the item
	 */
	String getName();

	/**
	 * Gets the tax percent applied to the item
	 * 
	 * @return tax percent applied
	 */
	BigDecimal getTaxPercent();

	/**
	 * Gets the tax applied to the item (taking quantity into account)
	 * 
	 * @return tax applied (taking quantity into account)
	 */
	BigDecimal getTax();

	/**
	 * Gets the full price (including taxes) of the item (taking quantity into
	 * account)
	 * 
	 * @return full price (including taxes) (taking quantity into account)
	 */
	BigDecimal getFullPrice();

	/**
	 * Scales a {@link java.math.BigDecimal BigDecimal} value
	 * 
	 * @param value
	 *            {@link java.math.BigDecimal BigDecimal} to scale
	 * @return scaled {@link java.math.BigDecimal BigDecimal}
	 */
	default BigDecimal scale(BigDecimal value) {
		return value.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Rounds a {@link java.math.BigDecimal BigDecimal} value
	 * 
	 * @param value
	 *            {@link java.math.BigDecimal BigDecimal} to round
	 * @return rounded {@link java.math.BigDecimal BigDecimal}
	 */
	default BigDecimal round(BigDecimal value) {
		BigDecimal rounding = new BigDecimal(DEFAULT_ROUND);
		return rounding.signum() == 0 ? value : (value.divide(rounding, 0, RoundingMode.UP)).multiply(rounding);
	}
}
