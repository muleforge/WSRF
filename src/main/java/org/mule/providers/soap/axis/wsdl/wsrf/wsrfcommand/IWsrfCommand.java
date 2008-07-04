/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.wsrfcommand;

import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFException;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFOperationException;
import org.mule.umo.UMOEvent;

import org.apache.axis.client.Call;

/**
 * Command Interface for WSRF Operation 
 */
public interface IWsrfCommand
{
    /**
     * Set standalone mode
     * @param isStandalone is standalone mode
     */
    void setIsStandaloneMode(boolean isStandalone);
    /**
     * set event umo 
     * @param event umo message event
     */
    void setEvent(UMOEvent event);
    
    /**
     * set call object
     * @param call call axis object
     */
    void setCall(Call call);
    
    /**
     * Execute command method
     *@throws WSRFException wsrf operation error
     */
    void execute() throws WSRFException;

}


