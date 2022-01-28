/*
 * Copyright 2022 Chris Jackson
 */
package uk.cjack.promotions.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import uk.cjack.promotions.util.FileUtil;

/**
 * Catalogue Loader
 *
 * @author chrisjackson
 */
public class CatalogueLoader
{
    private static final Logger LOGGER = Logger.getLogger( CatalogueLoader.class.getName() );
    private static final String CATALOGUE_FILE_NAME = "catalogue";
    private static Map<String, Integer> catalogue;

    /**
     *
     */
    public static void loadCatalogue()
    {
        catalogue = new HashMap<>();

        final List<String> catalogueItems = FileUtil.loadResource( CATALOGUE_FILE_NAME );
        for ( final String catalogueItem : catalogueItems )
        {
            try
            {
                load( catalogueItem );
            }
            catch ( final Exception e )
            {
                LOGGER.log( Level.SEVERE, "Could not load catalogue item {}", catalogueItem );
            }
        }
    }

    /**
     * Loads the catalogue into a Map
     */
    private static void load( final String catalogueEntry )
    {
        final String[] split = catalogueEntry.split( "," );

        final String sku = split[ 0 ];
        final Integer unitCost = Integer.parseInt( split[ 1 ] );

        if ( sku.length() > 1 )
        {
            throw new RuntimeException( "SKU invalid" );
        }
        catalogue.put( sku, unitCost );

    }

    /**
     * @return The catalogue.
     */
    public static Map<String, Integer> getCatalogue()
    {
        if( catalogue == null )
        {
            loadCatalogue();
        }
        return catalogue;
    }
}
