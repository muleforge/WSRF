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
     * extenderCall to use AOP Spring Framework in order to manage WSRF SOAP
     * Extension
     */
    private IExtendCall extenderCall = null;
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
            aopSpringContext = new FileSystemXmlApplicationContext("application.xml");
           
        }
        catch (BeansException e)
        {
            e.printStackTrace();
        }
        if (aopSpringContext != null)
        {
            extenderCall = (IExtendCall) aopSpringContext.getBean("extendCallProxyBean");
            extenderCall.addAdvice();

        }
        else
        {
            System.out.println("factory is null!");
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
        this.extenderCall.extendCall(null, event);
        return super.doSend(event);
    }

}
