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

    /**
     * Promotion Engine
     *
     * @param order order to calculate promotions on
     */
    public PromotionEngine( final Order order )
    {
        CatalogueLoader.getCatalogue();

        promotionLoader = new PromotionLoader();

        for( final Promotion promotion : promotionLoader.getPromotionsList() )
        {
            final Map<String, Integer> orderMap = order.getOrderMap();
            final HashMap<String, Integer> factors = promotion.getFactors();

            for( final Map.Entry<String, Integer> factor : factors.entrySet() )
            {
                final String factorSku = factor.getKey();
                final int factorQty = factor.getValue();

                if( orderMap.get( factorSku ) >= factorQty )
                {
                    // Enough of this type in basket to potentially apply discount
                }

            }
                // discountItems
        }
    }

    /**
     *
     * @return
     */
    public Integer calculateValue()
    {
        // TODO
        return 0;
    }
}
