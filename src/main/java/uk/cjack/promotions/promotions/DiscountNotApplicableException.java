/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.promotions;

/**
 * DiscountNotApplicableException is throw when a promotion is attempted and is not applicable
 *
 * @author chrisjackson
 */
public class DiscountNotApplicableException extends Exception
{
    /**
     * Constructor
     */
    public DiscountNotApplicableException()
    {
        super();
    }
}
