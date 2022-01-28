/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
 */

package uk.cjack.promotions


import spock.lang.Specification
import uk.cjack.promotions.model.Order
import uk.cjack.promotions.model.OrderLine

/**
 * Test Class for {@link PromotionEngine}
 *
 * @author chrisjackson
 */
class PromotionEngineTest extends Specification {

    private PromotionEngine promotionEngine

    /**
     * Scenario A
     * 1 * A
     * 1 * B
     * 1 * C
     * Total = 100
     */
    def "Scenario A"() {
        given: "An order contains 1 * A, 1 * B, and 1 * C"
        final Order order = new Order(
                new OrderLine("A"),
                new OrderLine("B"),
                new OrderLine("C"))

        when: "I call the promotion engine with the given order"
        promotionEngine = new PromotionEngine(order)

        then: "Then total order value should be 100"
        promotionEngine.getOrderTotal() == 100
    }

    /**
     * Scenario B
     * 5 * A
     * 5 * B
     * 1 * C
     * Total = 370
     */
    def "Scenario B"() {
        given: "An order contains 5 * A, 5 * B, and 1 * C"
        final Order order = new Order(
                new OrderLine("A"),
                new OrderLine("A"),
                new OrderLine("A"),
                new OrderLine("A"),
                new OrderLine("A"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("C"))

        when: "I call the promotion engine with the given order"
        promotionEngine = new PromotionEngine(order)

        then: "Then total order value should be 370"
        promotionEngine.getOrderTotal() == 370
    }

    /**
     * Scenario C
     * 3 * A
     * 5 * B
     * 1 * C
     * 1 * D
     * Total = 280
     */
    def "Scenario C"() {
        given: "An order contains 3 * A, 5 * B, 1 * C, and 1 * D"
        final Order order = new Order(
                new OrderLine("A"),
                new OrderLine("A"),
                new OrderLine("A"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("B"),
                new OrderLine("C"),
                new OrderLine("D"))

        when: "I call the promotion engine with the given order"
        promotionEngine = new PromotionEngine(order)

        then: "Then total order value should be 280"
        promotionEngine.getOrderTotal() == 280
    }
}
