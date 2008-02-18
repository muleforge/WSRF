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

import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * The Class Messages.
 */
public final class Messages
{
    //TODO raffaele.picardi: change bundle_name value after package migrating
    
    /** The Constant BUNDLE_NAME. */
    private static final String BUNDLE_NAME = "org.mule.providers.soap.axis.wsdl.wsrf.factory.messages_stub"; //$NON-NLS-1$

    /** The Constant RESOURCE_BUNDLE. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

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
     * @return the string
     */
    public static String getString(String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }
}
