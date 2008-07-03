/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrfcommand;

import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFException;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFOperationException;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRPOperationNotFound;
import org.mule.umo.UMOEvent;

import java.util.HashMap;
import java.util.Map;

import org.apache.axis.client.Call;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * The Class AbstractWsrfCommand.
 */
public abstract  class AbstractWsrfCommand implements IWsrfCommand
{

    /** The call. */
    protected Call call;
    
    /** The event. */
    protected UMOEvent event;
    
    /** The is standalone. */
    protected boolean isStandalone;

    /** The wsrf extra response map. */
    protected Map wsrfExtraResponseMap;

    /**
     * execute command method.
     * 
     * @throws WSRFOperationException operation error
     */
    public void execute() throws WSRFException
    {
        
        wsrfExtraResponseMap = (Map) event.getMessage().getProperty(WSRFParameter.WSRF_EXTRA_RESPONSE_MAP);
        if (wsrfExtraResponseMap == null) 
        {
            wsrfExtraResponseMap = new HashMap();
            event.getMessage().setProperty(WSRFParameter.WSRF_EXTRA_RESPONSE_MAP , wsrfExtraResponseMap);
            Logger.getLogger(this.getClass()).log(
                Level.INFO,
                this.getClass().getName() + " : " + " Map of :"  
                                + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + "  created.");
        
       }
        
      try 
      {
         handleExecute(); 
      }
     catch (Exception e)
     {
         Logger.getLogger(this.getClass()).log(
             Level.ERROR,
             this.getClass().getName() + " : " + " ERROR in getting Resource Property: . Error added  into "  + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + " as " +WSRFParameter.WSRF_RP_ERROR_RESPONSE  + " key"
                             + e.getMessage());
         
         e.printStackTrace();
         wsrfExtraResponseMap.put(WSRFParameter.WSRF_RP_ERROR_RESPONSE, e.getMessage());
         return;
     }
     
    }

/**
 * Handle method to override for execute logic.
 * 
 * @throws WSRFOperationException operation error
 */
 protected abstract void handleExecute() throws WSRFException;

/**
 * set call object.
 * 
 * @param call call object
 */
    public void setCall(Call call)
    {
        this.call =  call;
    }

    /**
     * (non-Javadoc).
     * 
     * @param event umo event
     * @see org.mule.providers.soap.axis.wsdl.wsrfcommand.IWsrfCommand#setEvent(org.mule.umo.UMOEvent)
     */
    public void setEvent(UMOEvent event)
    {
       this.event = event;
    }

    /**
     * (non-Javadoc).
     * 
     * @param isStandalone stand alone mode
     * @see org.mule.providers.soap.axis.wsdl.wsrfcommand.IWsrfCommand#setIsStandaloneMode(boolean)
     */
    public void setIsStandaloneMode(boolean isStandalone)
    {
        this.isStandalone = isStandalone;
        
    }

}


