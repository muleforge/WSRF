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
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        System.out.println("first to extendCall...event message payload: " + ((UMOEvent) arg1[0]).getMessage().getPayloadAsString());

    }

}


