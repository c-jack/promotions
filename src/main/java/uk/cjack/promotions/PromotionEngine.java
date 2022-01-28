/*
 * Copyright 2022 Chris Jackson
 */

package uk.cjack.promotions;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

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

    private CatalogueLoader catalogueLoader;
    private PromotionLoader promotionLoader;
    private static final Logger LOGGER = Logger.getLogger( PromotionEngine.class.getName() );
    private Integer orderTotal;

    /**
     * Promotion Engine
     *
     * @param order order to calculate promotions on
     */
    public PromotionEngine( final Order order )
    {
        CatalogueLoader.getCatalogue();

        promotionLoader = new PromotionLoader();

        applyPromotions( order );

        orderTotal = calculateValue( order );


    }

    /**
     * @param order
     */
    private void applyPromotions( final Order order )
    {
        for ( final Promotion promotion : promotionLoader.getPromotionsList() )
        {
            final Map<String, Integer> orderMap = order.getOrderMap();
            final HashMap<String, Integer> factors = promotion.getFactors();

            final int discounts = checkDiscount( factors, orderMap );

            if ( discounts > 0 )
            {
                final int totalDiscountForPromotion = discounts * promotion.getDiscountValue();

                order.addDiscountAmount( totalDiscountForPromotion );
            }

        }
    }

    /**
     * @param factors
     * @param orderMap
     * @return
     * @throws DiscountNotApplicableException
     */
    private int checkDiscount( final HashMap<String, Integer> factors,
                               final Map<String, Integer> orderMap )
    {
        final Map<String, Integer> updatedOrderMap = new HashMap<>();
        int totalDiscounts = 0;

        while ( true ) // Check this discount until a DiscountNotApplicableException is thrown
        {
            try
            {
                for ( final Map.Entry<String, Integer> factor : factors.entrySet() )
                {
                    final String factorSku = factor.getKey();
                    final int factorQty = factor.getValue();
                    final Integer orderQty = orderMap.get( factorSku );

                    if ( orderQty != null && orderQty >= factorQty )
                    {
                        // Enough of this type in basket to potentially apply discount
                        // Reduce the qty in the orderMap
                        updatedOrderMap.put( factorSku, orderQty - factorQty );
                        continue;
                    }
                    throw new DiscountNotApplicableException();
                }


                // Promo is complete
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
     * @param order
     * @return
     */
    private Integer calculateValue( final Order order)
    {
        final Map<String, Integer> orderMap = order.getOrderMap();
        final Map<String, Integer> catalogue = CatalogueLoader.getCatalogue();
        int nonDiscountedOrderValue = 0;
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
