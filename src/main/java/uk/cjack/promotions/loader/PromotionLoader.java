/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
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
    protected static final String PROMOTIONS_FILE_NAME = "promotions";
    private final List<Promotion> promotionsList = new ArrayList<>();

    /**
     * Constructor
     *
     * Loads the promotions on instantiation
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


        final String fullyQualifiedClassName = "uk.cjack.promotions.promotions.%sPromotion";
        final String classReference = String.format( fullyQualifiedClassName, promotionName );

        return ( Promotion ) Class.forName( classReference )
                .getDeclaredConstructor( String.class )
                .newInstance( promotionLogic );

    }

    /**
     * @return The promotionsList.
     */
    public List<Promotion> getPromotionsList()
    {
        return promotionsList;
    }
}
