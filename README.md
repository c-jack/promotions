# promotions

The SKU catalogue and Promotions are loaded when a new instance of the `PromotionEngine` is created.

## Catalogue

The solution only supports 
These are loaded in from the `catalogue` resource, and are essentially a lightweight way of managing the availiable SKUs and their values.
Each line should be added in the format of:
`{SKU},{Price}`

Where 'SKU' is a single-character, uppercase A-Z value, as per the brief, and 'Price' is a integer value with only the restrictions of the `Integer` class.

## Adding Promotions
Promotions can be added as a line in the 'resources/promotions' file.

The format of each line should be:
`{PromotionType}:{PromotionLogic}`

### Promotion Class

The `PromotionType` value should match a Class in the `uk.cjack.promotions.promotions` package.
The Class should implement `uk.cjack.promotions.model.Promotion`, and should have the `Promotion` suffix in the class name.
e.g. "Bundle" is implemented by `uk.cjack.promotions.promotions.BundlePromotion`

### Promotion Logic

The Promotion Class should process the given logic in its constructor.

The logic should be constructed as: `{Factors}={Value}`, and the class is required to satisfy the implementation by providing methods for `getFactors()` and `getDiscountValue()`
Due to constraints of time and effort, the solution only currently supports implied factoring in the Factors, but each factor can also have its own numerical multiplier
e.g. 
- `AB` = `A * B`
- `A2B` = `A * 2B`
- `2A2B` = `2A * 2B`
- `2AB` = `2A * B`
etc.

The value is currently only designed to support a numerical outcome for the combination of factors.  Although this could be modified in future to expand the abilities, it isn't currently part of the MVP and so Agile wins in the battle versus SOLID.
The class still otherwise has compatibility for Open/Close principle by allowing new promotions to be added as either new lines in the `promotions` file, or new Promotions as new Classes.


## Unit Testing

Test coverage is provided to approximately 93% line coverage.  This is not exhaustive or complete due to time constraints.
The main `PromotionEngineTest` class provides a good level of TDD-style requirement testing, rather than being limited to a 'Unit' as such.
Test classes are otherwise provided for `BundlePromotion` and `PromotionLoader` to demonstrate some Unit Testing that satisify some of the design decisions.

Spock is used as the Test Engine, due to being unrivalled for TDD implementations of unit testing.

