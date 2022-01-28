/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
 */

package uk.cjack.promotions.promotions

import spock.lang.Specification

/**
 * Test Class for {@link BundlePromotion}
 *
 * A 'bundle' is defined as a promotion whereby multiple combinations of item result in a single discounted price
 * @author chrisjackson
 */
class BundlePromotionTest extends Specification {

    /**
     * Tests that the Bundle Promotion logic is successfully transformed into factors and a discount value
     */
    def "Promotion #promotionLogic should have factors of #factors and discount value of #discountValue"() {
        when: "A ComboDiscount promotion is applied with some promotion logic"
        BundlePromotion comboDiscount = new BundlePromotion(promotionLogic)

        then: "The discount value should equal the expected amount"
        comboDiscount.factors == factors

        and: "The discount value should equal the expected amount"
        comboDiscount.discountValue == discountValue

        where: "Discounts are either not applicable, applicable, or applicable twice"
        promotionLogic | factors                  | discountValue
        "CD=30"        | [C: 1, D: 1]             | 30
        "2B=45"        | [B: 2]                   | 45
        "3A=130"       | [A: 3]                   | 130
        "ABCD=999"     | [A: 1, B: 1, C: 1, D: 1] | 999
    }

}
