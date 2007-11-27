/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.samples.WsrfComponent;

import org.mule.umo.UMOEventContext;
import org.mule.umo.lifecycle.Callable;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 */
public class WsrfBridgeComponent implements Callable
{
    /**
     * 
     *
     */
    public WsrfBridgeComponent()
    {
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " created.");
        
    }
    
    /**
     * Log event and dispatch it without changes 
     * @param arg0 event received
     * @return event dispatched
     * @throws Exception possibile exception
     */
    public Object onCall(UMOEventContext arg0) throws Exception
    {
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : payload class name:" + arg0.getMessage().getPayload().getClass().getName());
        return arg0;
    }
}
