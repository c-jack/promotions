package uk.cjack.promotions.loader

import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification
import uk.cjack.promotions.util.FileUtil

/**
 * Test Class for {@link PromotionLoader}
 *
 * Uses Mockito's {@link Mockito#mockStatic} method to demonstrate/justify the {@link FileUtil class}
 *
 * @author chrisjackson
 */
class PromotionLoaderTest extends Specification {

    private MockedStatic<FileUtil> mockStaticFileUtil

    /**
     * Before each test
     */
    void setup() {

        // Mock the Static methods on FileUtil
        mockStaticFileUtil = Mockito.mockStatic(FileUtil)
    }

    /**
     * After each test
     */
    void cleanup() {
        mockStaticFileUtil.close()
    }

    /**
     * Tests {@link PromotionLoader#PromotionLoader} constructor
     *
     * Example test to demonstrate the testability of the FileUtil wrapper class in conjunction with the PromotionLoader
     * This tests demonstrates the happy path, where valid promotions are converted successfully.
     */
    def "PromotionLoader should load the promotions from the promotions file when instantiated"() {
        given: "I have a String list of entries in a Promotions File"
        final List<String> mockFileContents = fileContents

        and: "I stub the static method FileUtil.loadResource() to return the List"
        mockStaticFileUtil.when(() -> FileUtil.loadResource(PromotionLoader.PROMOTIONS_FILE_NAME))
                .thenReturn(mockFileContents)

        when: "I create an instance of PromotionLoader"
        PromotionLoader promotionLoader = new PromotionLoader()

        then: "The promotionsLoader should have loaded a promotion for each valid entry"
        promotionLoader.getPromotionsList().size() == mockFileContents.size()

        where:
        fileContents                                          | _
        ["Bundle:ABC=123", "Bundle:2A2B=23"]                  | _
        ["Bundle:1A1B=321"]                                   | _
        ["Bundle:7A=150", "Bundle:100D=1000", "Bundle:3D=40"] | _
    }

}
