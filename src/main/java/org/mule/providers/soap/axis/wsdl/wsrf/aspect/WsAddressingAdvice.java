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

import org.mule.umo.UMOEvent;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * WsAddressing Aspect to inject Ws-Addressing information in SOAP - Header
 */
public class WsAddressingAdvice implements MethodBeforeAdvice
{
    /**
     * Default Constructor
     *
     */
    public WsAddressingAdvice()
    {
        //Default Constructor
        System.out.println("init WsAddressingAdvice");
    }
    
    /**
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        ((UMOEvent) arg1[1]).getMessage().setProperty("testLongProperty", new Integer(1));
        System.out.println("first to extendCall...event message payload: " + ((UMOEvent) arg1[1]).getMessage().getPayloadAsString());
    }

}


