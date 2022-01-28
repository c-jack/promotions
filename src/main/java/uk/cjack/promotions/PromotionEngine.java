/*
 * Copyright 2022 Chris Jackson
 */

package uk.cjack.promotions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import uk.cjack.promotionengine.model.Cart;
import uk.cjack.promotionengine.model.PromotionLoader;
import uk.cjack.promotionengine.promotions.Promotion;

/**
 * Promotions Engine
 *
 * @author chrisjackson
 */
public class PromotionEngine
{

    private static final Logger LOGGER = Logger.getLogger( PromotionEngine.class.getName() );

    /**
     * Promotion Engine
     *
     * @param order order to calculate promotions on
     */
    public PromotionEngine( final Order order )
    {

    }
}
