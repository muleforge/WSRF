/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.wsdl.wsrf.instance;

import org.mule.providers.soap.axis.wsdl.wsrf.factory.Messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * The Class RPMessages.
 */
public final class RPMessages
{



    /** The Constant BUNDLE_NAME. */
    private static final String BUNDLE_NAME = "META-INF/rp_messages.properties"; //$NON-NLS-1$

 /**
  * stream to resource
  */
    private static InputStream t = Messages.class.getClassLoader().getResourceAsStream(BUNDLE_NAME);

    /**
     * Instantiates a new rP messages.
     */
    private RPMessages()
    {
        //TODO raffaele.picardi:todo
    }

    /** The props. */
    private static java.util.Properties props = new java.util.Properties();
    
    /** The is load. */
    private static boolean isLoad = false;
    
 
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
            Logger.getLogger(Messages.class).log(Level.ERROR,
                Messages.class.getName() + " : " + " Resource property  missing");
            return null;
            
        }
     
    }
    
}
