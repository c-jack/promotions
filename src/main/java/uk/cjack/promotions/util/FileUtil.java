/*
 * Copyright (C) Chris Jackson (github.com/c-jack) - All Rights Reserved
 * Unauthorised copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Chris Jackson <chris@cjack.uk>, 2022
 */
package uk.cjack.promotions.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils class
 *
 * Wrapper class for utility functions
 * @author chrisjackson
 */
public class FileUtil
{

    /**
     * Read a resource file by name convert each line into a String
     *
     * @return list of lines as String value
     */
    public static List<String> loadResource( final String resourceName)
    {
        final ArrayList<String> resources = new ArrayList<>();

        final InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream( resourceName );

        if ( inputStream != null )
        {
            try ( final BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) ) )
            {
                while ( bufferedReader.ready() )
                {
                    resources.add( bufferedReader.readLine() );
                }
            }
            catch ( final Exception e )
            {
                throw new RuntimeException( "Critical: Resource file " + resourceName + " could not be loaded", e );
            }
        }

        return resources;
    }
}
