package uk.cjack.promotions

import spock.lang.Specification
import uk.cjack.promotions.PromotionEngine 

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

        when: "I call the promotion engine with the given order"
        promotionEngine = new PromotionEngine(order)

        then: "Then total order value should be 100"
        promotionEngine.calculateValue() == 100
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

        when: "I call the promotion engine with the given order"
        promotionEngine = new PromotionEngine(order)

        then: "Then total order value should be 370"
        promotionEngine.calculateValue() == 370
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

        when: "I call the promotion engine with the given order"
        promotionEngine = new PromotionEngine(order)

        then: "Then total order value should be 100"
        promotionEngine.calculateValue() == 280
    }
}
