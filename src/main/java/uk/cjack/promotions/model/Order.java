/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Order
 *
 * @author chrisjackson
 */
public class Order
{
    private List<OrderLine> orderLines = new ArrayList<>();
    private Integer originalOrderValue = 0;
}
