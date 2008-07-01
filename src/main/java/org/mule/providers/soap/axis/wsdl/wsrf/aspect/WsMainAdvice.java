/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.aspect;



import org.mule.providers.soap.axis.wsdl.wsrf.BasePriorityAdvice;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.Messages;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.umo.UMOEvent;
import org.mule.umo.UMOMessage;


import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.axis.client.Call;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;




/**
 * Main Advice: Advice copies wsrfOption map porperties of Endpoint configuration in Message properties (not overwrite just properties of message)
 */
public class WsMainAdvice extends BasePriorityAdvice implements MethodBeforeAdvice
{
    
    /* 
     * getPriority
     * @return priority
     */
    public int getPriority()
    {
        return BasePriorityAdvice.CRITICAL_PRIORITY;
    }

    /**
     * Default Constructor.
     */
    public WsMainAdvice()
    {
        
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");
    }
    
    /**
     * Before.
     * Copies wsrfOption map porperties of Endpoint configuration in current Message properties and updates or adds Messages properties class 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        
        Logger.getLogger(this.getClass()).log(Level.INFO,  this.getClass().getName() + " : advice method started.");
      
        UMOEvent event = (UMOEvent) arg1[1];
        UMOMessage msg = event.getMessage();
        
        Call call = (Call) arg1[0];
        if (call == null) 
        {
            //TODO raffaele.picardi:test and add WSRFParameter fields
            if (event.getMessage().getProperty(WSRFParameter.RETURN_QTYPE) == null )
            {
                String serviceNamespace =  (String) event.getMessage().getProperty(WSRFParameter.SERVICE_NAMESPACE);
                String qtypeName =  (String)  ">" + event.getMessage().getProperty( WSRFParameter.RETURN_QNAME);
                event.getMessage().setProperty(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(serviceNamespace,qtypeName));
                
            }
            return;
        }
        
        //MANAGE Property RETURNQTYPE
        
        
        
        //end manage
       
        Map p = (Map) event.getEndpoint().getProperty(WSRFParameter.WSRF_ENDPOINT_PROPERTY_MAP);
        Iterator it  = null;
        String key = null;
        Object value = null;
        if (p ==  null )
        {
            Logger.getLogger(this.getClass()).log(Level.DEBUG,  this.getClass().getName() + " : wsrf option not found . Copy IGNORED for msg:" + msg.getUniqueId());
            
        }
        else 
        {
        it = p.keySet().iterator();

        
        while (it.hasNext())
        {
            key = (String) it.next();
            if (msg.getProperty(key) == null)
            {
                value = p.get(key);
                msg.setProperty(key, value);
                Logger.getLogger(this.getClass()).log(Level.DEBUG,  this.getClass().getName() + " : " + key + " exported in message id: " + msg.getUniqueId());
            }
        }
        }
        
        //Updated Messages properties using Message properties (only properties begins with WSRFParamter.wsrfPrefix)
        
        it = msg.getPropertyNames().iterator();
        key = null;
        value = null;
        
        while (it.hasNext())
        {
            key = (String) it.next();
            if (key.indexOf(WSRFParameter.wsrfPrefix) != -1)
            {
            value = msg.getProperty(key);
            Messages.setProperty(key, value);
            Logger.getLogger(this.getClass()).log(Level.DEBUG,
                this.getClass().getName() + " : " + key + " set in Messages class");
            }
        }
        
        
        
        
        
    }
 

}


