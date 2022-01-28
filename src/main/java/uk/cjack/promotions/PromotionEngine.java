/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
 */

package uk.cjack.promotions;

import java.util.HashMap;
import java.util.Map;

import uk.cjack.promotions.loader.CatalogueLoader;
import uk.cjack.promotions.loader.PromotionLoader;
import uk.cjack.promotions.model.Order;
import uk.cjack.promotions.model.Promotion;
import uk.cjack.promotions.promotions.DiscountNotApplicableException;

/**
 * Promotions Engine
 *
 * @author chrisjackson
 */
public class PromotionEngine
{
    private final PromotionLoader promotionLoader;
    private final Integer orderTotal;

    /**
     * Promotion Engine Constructor
     *
     * @param order order for processing
     */
    public PromotionEngine( final Order order )
    {
        CatalogueLoader.getCatalogue();

        promotionLoader = new PromotionLoader();

        applyPromotions( order );

        orderTotal = calculateValue( order );
    }

    /**
     * Applies the promotions to the provided order
     *
     * @param order {@link Order} to apply promotions to
     */
    private void applyPromotions( final Order order )
    {
        for ( final Promotion promotion : promotionLoader.getPromotionsList() )
        {
            final Map<String, Integer> orderMap = order.getOrderMap();
            final HashMap<String, Integer> factors = promotion.getFactors();

            final int discountedTotal = checkPromotion( factors, orderMap );

            if ( discountedTotal > 0 )
            {
                final int totalDiscountForPromotion = discountedTotal * promotion.getDiscountValue();
                order.addDiscountedAmount( totalDiscountForPromotion );
            }

        }
    }

    /**
     * Checks if a given discount is applicable to the order
     *
     * @param factors the discount factors to apply
     * @param orderMap the cumulative order
     *
     * @return the total discounted amount for this promotion
     */
    private int checkPromotion( final HashMap<String, Integer> factors,
                                final Map<String, Integer> orderMap )
    {
        final Map<String, Integer> updatedOrderMap = new HashMap<>();
        int totalDiscounts = 0;

        while ( true ) // Check this discount until a DiscountNotApplicableException is thrown
        {
            try
            {
                applyPromotion( factors, orderMap, updatedOrderMap );

                // Promo is complete. Add discounted SKUs to the updated map and increment the counter
                orderMap.putAll( updatedOrderMap );
                totalDiscounts++;
            }
            catch ( final DiscountNotApplicableException e )
            {
                break;
            }
        }

        return totalDiscounts;
    }

    /**
     * Attempt to apply the promotion to the order
     * @param factors the factors involved in the promotion
     * @param orderMap the cumulative order
     * @param updatedOrderMap the order as it will be if the promotion can be applied
     *
     * @throws DiscountNotApplicableException if the order doesn't qualify for the promotion
     */
    private void applyPromotion( final HashMap<String, Integer> factors, final Map<String, Integer> orderMap,
                                 final Map<String, Integer> updatedOrderMap ) throws DiscountNotApplicableException
    {
        for ( final Map.Entry<String, Integer> factor : factors.entrySet() )
        {
            final String factorSku = factor.getKey();
            final int factorQty = factor.getValue();
            final Integer orderQty = orderMap.get( factorSku );

            if ( orderQty != null && orderQty >= factorQty )
            {
                // Enough of this type in basket to potentially apply discount. Reduce the qty in the temporary orderMap
                updatedOrderMap.put( factorSku, orderQty - factorQty );
                continue;
            }
            throw new DiscountNotApplicableException();
        }
    }

    /**
     * Calculate the value of the order
     *
     * @param order the order to calculate vaue on
     * @return total order value, including discounts
     */
    private Integer calculateValue( final Order order)
    {
        final Map<String, Integer> orderMap = order.getOrderMap();
        final Map<String, Integer> catalogue = CatalogueLoader.getCatalogue();
        int nonDiscountedOrderValue = 0;

        // check remaining undiscounted SKUs
        for ( final Map.Entry<String, Integer> orderLine : orderMap.entrySet() )
        {
            final String sku = orderLine.getKey();
            final Integer qty = orderLine.getValue();
            final Integer unitValue = catalogue.get( sku );

            nonDiscountedOrderValue += qty * unitValue;
        }

        return nonDiscountedOrderValue + order.getDiscountTotal();
    }

    /**
     * @return The orderTotal.
     */
    public Integer getOrderTotal()
    {
        return orderTotal;
    }
}
