/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Order
 *
 * @author chrisjackson
 */
public class Order
{
    private List<OrderLine> orderLines = new ArrayList<>();
    private Map<String, Integer> orderMap = new HashMap<>();
    private Integer originalOrderValue = 0;


    /**
     *
     * @param orderLines
     */
    public Order( final OrderLine... orderLines )
    {
        this.orderLines.addAll( List.of( orderLines ) );

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
     * @return The orderLines.
     */
    public List<OrderLine> getOrderLines()
    {
        return orderLines;
    }

    /**
     * @return The orderMap.
     */
    public Map<String, Integer> getOrderMap()
    {
        return orderMap;
    }
}
