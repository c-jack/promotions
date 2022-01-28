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

    /**
     * Constructor
     *
     * Validates the Order Line SKU by checking if it is in the catalogue.
     *
     * @param sku the SKU of the order line
     */
    public OrderLine( final String sku )
    {
        final Map<String, Integer> catalogue = CatalogueLoader.getCatalogue();

        if( catalogue.get( sku ) != null )
        {
            this.sku = sku;
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
}
