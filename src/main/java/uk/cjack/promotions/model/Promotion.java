/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.model;

import java.util.HashMap;

/**
 * Interface for a 'Promotion'
 *
 * @author chrisjackson
 */
public interface Promotion
{

    /**
     * The 'factors' (left side of the calculation) are the order combinations/quantities that make
     * the promotion applicable
     *
     * @return a Map of skus and qualifying quantities per promo
     */
    HashMap<String, Integer> getFactors();

    /**
     * The 'value' (right side of the calculation) is used to determine the effec the promotion will
     * have on the order
     *
     * @return the value of the discount
     */
    int getDiscountValue();
}
