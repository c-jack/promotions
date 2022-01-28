/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.model;

import java.util.Map;

import uk.cjack.promotions.loader.CatalogueLoader;

/**
 * POJO representing an Order Line
 *
 * @author chrisjackson
 */
public class OrderLine
{
    private final String sku;
    private final Integer cost;
    private boolean discounted;

    /**
     *
     * @param sku
     */
    public OrderLine( final String sku )
    {
        final Map<String, Integer> catalogue = CatalogueLoader.getCatalogue();

        final Integer cost = catalogue.get( sku );
        if( cost != null )
        {
            this.sku = sku;
            this.cost = cost;
        }
        else
        {
            throw new RuntimeException("SKU " + sku + " not found in Catalogue");
        }

    }

    /**
     * @return The sku.
     */
    public String getSku()
    {
        return sku;
    }

    /**
     * @return The cost.
     */
    public Integer getCost()
    {
        return cost;
    }

    /**
     * @return The discounted.
     */
    public boolean isDiscounted()
    {
        return discounted;
    }
}
