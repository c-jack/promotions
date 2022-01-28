/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
 */
package uk.cjack.promotions.model;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO representing an 'Order'
 *
 * @author chrisjackson
 */
public class Order
{
    private final Map<String, Integer> orderMap = new HashMap<>();
    private Integer discountTotal = 0;

    /**
     * Constructor
     *
     * @param orderLines one or more {@link OrderLine}
     */
    public Order( final OrderLine... orderLines )
    {
        for ( final OrderLine orderLine : orderLines )
        {
            final String sku = orderLine.getSku();
            int qty = 0;
            if ( orderMap.containsKey( sku ) )
            {
                qty = orderMap.get( sku );
            }
            orderMap.put( sku, ++qty );
        }
    }

    /**
     * @return The Map of order lines
     */
    public Map<String, Integer> getOrderMap()
    {
        return orderMap;
    }

    /**
     * @return The total of the discounted lines on the order
     */
    public Integer getDiscountTotal()
    {
        return discountTotal;
    }

    /**
     * @param discountAmount The amount to add to discount total
     */
    public void addDiscountedAmount( final int discountAmount )
    {
        this.discountTotal += discountAmount;
    }
}
