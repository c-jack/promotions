/*
 * Copyright 2022 Chris Jackson
 */

package uk.cjack.promotions;

import java.util.logging.Logger;

import uk.cjack.promotions.loader.CatalogueLoader;
import uk.cjack.promotions.loader.PromotionLoader;
import uk.cjack.promotions.model.Order;

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
        catalogueLoader = new CatalogueLoader();
        promotionLoader = new PromotionLoader();
    }
}
