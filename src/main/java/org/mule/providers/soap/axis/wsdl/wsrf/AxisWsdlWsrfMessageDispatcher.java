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

import org.mule.providers.soap.axis.wsdl.AxisWsdlMessageDispatcher;
import org.mule.umo.UMOEvent;
import org.mule.umo.UMOMessage;
import org.mule.umo.endpoint.UMOImmutableEndpoint;

import org.apache.axis.client.Call;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Dispatch  WSRF WSDL Soap Axis request
 * 
 * @author raffaele.picardi
 */
public class AxisWsdlWsrfMessageDispatcher extends AxisWsdlMessageDispatcher
{

    /**
     * target : extenderCall to use AOP Spring Framework in order to manage WSRF SOAP
     * Extension
     */
    private IExtendCall extenderCall = null;
    
    /**
     * proxy : extenderProxyCall to use AOP Spring Framework in order to manage WSRF SOAP
     * Extension
     */
    private IExtendCall extenderProxyCall = null;
    
    /**
     * Factory bean
     */
    private ApplicationContext aopSpringContext = null;

    /**
     * @param endpoint endpoint
     */
    public AxisWsdlWsrfMessageDispatcher(UMOImmutableEndpoint endpoint)
    {
        super(endpoint);
        // TODO MULE-WSRF-5: Using SPRING AOP

        try
        {
            // TODO raffaele.picardi : move application.xml into meta_inf dir
            aopSpringContext = new FileSystemXmlApplicationContext("application.xml");
            
        }
        catch (BeansException e)
        {
            e.printStackTrace();
        }
        if (aopSpringContext != null)
        {
           extenderCall = (IExtendCall) aopSpringContext.getBean("extendCallTarget");
           extenderProxyCall = AdviceAdderHelper.addAdvisorsTo(extenderCall);
          
        }
        else
        {
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : factory spring is null.");

        }

    }

    /**
     * @param event event
     * @throws Exception exception
     * @return UMOMessage
     */
    protected UMOMessage doSend(UMOEvent event) throws Exception
    {
        // TODO MULE-WSRF-5: Using SPRING AOP and how to manage Call axis object used by  super.doSend() method
        // TODO MULE-WSRF-10: WSDL2JAVA extension ?
        return super.doSend(event);
        // TODO MULE-WSRF-14: how to append response frow WSRF Stub Aspects ?
    }
    /**
     * Override Call method of Axis Provider in order to inject Aspects using target object extendCall (that is adviced)
     * WSRF information are indipendently processed by some Aspect (using Stub gnerated from Wsdl2Java at runtime)
     * @param arg0 event see axis getCall method
     * @param arg1 see axis getCall method
     * @return Call call axis object created
     * @throws Exception general
     */
    protected Call getCall(UMOEvent arg0, Object[] arg1) throws Exception
    {
       Call call = super.getCall(arg0, arg1);
       this.extenderProxyCall.extendCall(call, arg0);
       return call;
    }
    
     
}
