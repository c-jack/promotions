/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.cjack.promotions.model.Promotion;
import uk.cjack.promotions.util.FileUtil;

/**
 * Promotion Loader
 * <p>
 * Class to load promotions file
 *
 * @author chrisjackson
 */
public class PromotionLoader
{
    private static final Logger LOGGER = Logger.getLogger( PromotionLoader.class.getName() );
    private static final String PROMOTIONS_FILE_NAME = "promotions";
    private List<Promotion> promotionsList = new ArrayList<>();

    /**
     *
     */
    public PromotionLoader()
    {
        final List<String> promotions = FileUtil.loadResource( PROMOTIONS_FILE_NAME );
        for ( final String promotion : promotions )
        {
            try
            {
                promotionsList.add( load( promotion ) );
            }
            catch ( final Exception e )
            {
                LOGGER.log( Level.SEVERE, "Could not load promotion logic {}", promotion );
            }
        }
    }

    /**
     * Loads a {@link Promotion} from the provided rule
     *
     * @param promotionRule a text-based rule, with a promotion reference and promotion logic, separated by a colon
     */
    public Promotion load( final String promotionRule ) throws Exception
    {
        final String[] split = promotionRule.split( ":" );

        final String promotionName = split[ 0 ];
        final String promotionLogic = split[ 1 ];


        final String fullyQualifiedClassName = "uk.cjack.promotionengine.promotions.%sPromotion";
        final String classReference = String.format( fullyQualifiedClassName, promotionName );

        return ( Promotion ) Class.forName( classReference )
                .getDeclaredConstructor( String.class )
                .newInstance( promotionLogic );

    }
}
