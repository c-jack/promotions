/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
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
