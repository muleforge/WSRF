/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.factory;



import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;

/**
 * The Class Messages. Retrieve properties from META-INF/messages_stub.properties
 */
public final class Messages
{
   
    /** The Constant BUNDLE_NAME. */
    private static final String BUNDLE_NAME = "META-INF/messages_stub.properties"; //$NON-NLS-1$




    /** The t. */
    private static InputStream t = Messages.class.getClassLoader().getResourceAsStream(BUNDLE_NAME);
    
    /** The props. */
    private static java.util.Properties props = new java.util.Properties();
    
    /** The is load. */
    private static boolean isLoad = false;
    
    /**
     * Instantiates a new messages.
     */
    private Messages()
    {
        //Empty Constructor
    }

    /**
     * Gets the string.
     * 
     * @param key the key
     * @return the string . return null if key doesn't exist
     * @throws IOException 
     */
    public static String getString(String key) 
    {
        if (!isLoad)
        {
            isLoad = true;
            try
            {
                props.load(t);
            }
            catch (IOException e)
            {
                
                e.printStackTrace();
            }
        }
        try
        {
            return props.getProperty(key);
        }
        catch (MissingResourceException e)
        {
            return null;
        }
     
    }
}
