/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf;
/**
 * Monitor class to deamonize Mule server during test
 */
public final class Monitor
{
    /**
     * Test live time
     */
    private static final long TEST_LIVE_TIME = 500000;
    /**
     * Private constructor
     *
     */
    private Monitor()
    {
        // No operation
    }
    /**
     * Static instance
     */
    private static Monitor monitor = new Monitor();
    /**
     * Lock Invoker on lock object of  its static instance
     *
     */
    public static void waitOn() 
    {
        try
        {
           synchronized (monitor) 
           { 
            monitor.wait(TEST_LIVE_TIME);
           }
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


