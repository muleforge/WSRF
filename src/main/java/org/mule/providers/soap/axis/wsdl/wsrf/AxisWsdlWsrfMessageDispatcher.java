/*
 * $Id: AxisWsdlWsrfMessageDispatcher.java 283 2008-05-06 10:02:17Z raffaele.picardi $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf;

import org.mule.impl.MuleMessage;
import org.mule.providers.soap.axis.wsdl.AxisWsdlMessageDispatcher;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.Messages;
import org.mule.providers.soap.axis.wsdl.wsrf.util.AdviceAdderHelper;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.umo.UMOEvent;
import org.mule.umo.UMOMessage;
import org.mule.umo.endpoint.UMOImmutableEndpoint;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.axis.client.Call;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Dispatch WSRF WSDL Soap Axis request.
 * 
 * @author raffaele.picardi
 */
public class AxisWsdlWsrfMessageDispatcher extends AxisWsdlMessageDispatcher
{

    /**
     * target : extenderCall to use AOP Spring Framework in order to manage WSRF SOAP
     * Extension.
     */
    private IExtendCall extenderCall = null;
    
    /**
     * proxy : extenderProxyCall to use AOP Spring Framework in order to manage WSRF
     * SOAP Extension.
     */
    private IExtendCall extenderProxyCall = null;
    
    /** Factory bean. */
    private ApplicationContext aopSpringContext = null;

    /**
     * The Constructor.
     * 
     * @param endpoint endpoint
     */
    public AxisWsdlWsrfMessageDispatcher(UMOImmutableEndpoint endpoint)
    {
        super(endpoint);
        

        try
        {
                       
            aopSpringContext = new ClassPathXmlApplicationContext(Messages.getString("AxisWsdlWsrfMessageDispatcher.APPLICATION_CONTEXT_PATH"));

        }
        catch (BeansException e)
        {
            e.printStackTrace();
        }
        if (aopSpringContext != null)
        {
           extenderCall = (IExtendCall) aopSpringContext.getBean(Messages.getString("AxisWsdlWsrfMessageDispatcher.BEAN_SPRING_EXTENDER_TARGET"));
           
           extenderProxyCall = AdviceAdderHelper.addAdvisorsTo(extenderCall);
         }
        else
        {
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : factory spring context is null.");
        }
    }

    /**
     * Do send.
     * 
     * @param event event
     * @return UMOMessage
     * @throws Exception exception
     */
    protected UMOMessage doSend(UMOEvent event) throws Exception
    {
 

        UMOMessage messageResponse;
      
         messageResponse =  super.doSend(event);
      
        
        
        try
        {
            Map map = (Map) event.getMessage().getProperty(WSRFParameter.WSRF_EXTRA_RESPONSE_MAP);
            Set keySet = map.keySet();
            Iterator it = keySet.iterator();
            String propertyName = "";

            while (it.hasNext())
            {
                propertyName = (String) it.next();
                messageResponse.setProperty(propertyName, map.get(propertyName));
            }

        }
        catch (Exception e)
        {
            Logger.getLogger(this.getClass()).log(
                Level.DEBUG,
                this.getClass().getName() + " : " + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP
                                + " property is null. IGNORED Advice extra reponse properties");
        }
        return messageResponse;

    }
    
    /**
     * Override Call method of Axis Provider in order to inject Aspects using target
     * object extendCall (that is adviced) WSRF information are indipendently
     * processed by some Aspect (using Stub gnerated from Wsdl2Java at runtime).
     * 
     * @param arg0 event see axis getCall method
     * @param arg1 see axis getCall method
     * @return Call call axis object created
     * @throws Exception general
     */
    protected Call getCall(UMOEvent arg0, Object[] arg1) throws Exception
    {
        //TODO raffaele.picardi: manage standalone property of Advice - try to define a pattern in order to set SoapMethod object into Message before super.doSend in WSRF Message Dispatcher
        //test so: if it's need before advice can be add initial Message property before creating of Call during super.doSend(event) process
        this.extenderProxyCall.extendCall(null , arg0 , this);
        Call call = super.getCall(arg0, arg1);
        this.extenderProxyCall.extendCall(call, arg0, this);
        return call;
    }

   

     
}
