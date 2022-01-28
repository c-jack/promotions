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


    HashMap<String, Integer> getFactors();

    int getDiscountValue();
}
