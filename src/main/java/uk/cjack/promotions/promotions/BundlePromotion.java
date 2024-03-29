/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
 */
package uk.cjack.promotions.promotions;

import java.util.HashMap;

import uk.cjack.promotions.model.Promotion;

/**
 * Applies a 'bundle discount' promotion.
 * <p>
 * This is defined as a discount where a combination of items have a discounted 'bundle' price
 *
 * @author chrisjackson
 */
public class BundlePromotion implements Promotion
{
    private final HashMap<String, Integer> factors = new HashMap<>();
    private final int discountValue;

    private static final String EQUALS = "=";
    private static final String LOOKAHEAD_NUMBER_LETTER_REGEX = "(?<=[0-9]?[A-Z])";
    private static final String LOOKAHEAD_UPPERCASE_A_Z_REGEX = "(?=[A-Z])";
    private static final String NUMBER_UPPERCASE_A_Z_REGEX = "([0-9]+[A-Z])";

    /**
     * Constructor
     *
     * @param discountLogic the discount logic for this bundle promotion
     */
    @SuppressWarnings( "unused" ) // used via reflection
    public BundlePromotion( final String discountLogic )
    {
        final String[] split = discountLogic.split( EQUALS );
        final String calculation = split[ 0 ];
        final String action = split[ 1 ];

        final String[] calculationFactors = calculation.split( LOOKAHEAD_NUMBER_LETTER_REGEX );

        determineCalculationFactors( calculationFactors );

        discountValue = Integer.parseInt( action );
    }

    /**
     * Calculates the qualifying factors for this promotion.
     * Each factor should bean optional multiplier followed by a character (A-Z) representing a SKU
     *
     * @param calculationFactors a String array of the factors
     */
    private void determineCalculationFactors( final String[] calculationFactors )
    {
        for ( final String factor : calculationFactors )
        {
            int factorQuantity = 1;

            String sku = factor;
            if ( factor.length() > 1 && factor.matches( NUMBER_UPPERCASE_A_Z_REGEX ) )
            {
                final String[] split = factor.split( LOOKAHEAD_UPPERCASE_A_Z_REGEX );
                factorQuantity = Integer.parseInt( split[ 0 ] );
                sku = split[ 1 ];
            }

            factors.put( sku, factorQuantity );
        }
    }

    /**
     * @return The factors.
     */
    @Override
    public HashMap<String, Integer> getFactors()
    {
        return factors;
    }

    /**
     * @return The discountValue.
     */
    @Override
    public int getDiscountValue()
    {
        return discountValue;
    }
}
