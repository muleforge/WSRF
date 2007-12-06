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


import org.mule.providers.soap.axis.wsdl.wsrf.util.StubPriorityAdvice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
/**
 * This Stub Advice perform WS Lifetime request using WSDL2JAVA stub
 */
public class WsltStubAdvice extends StubPriorityAdvice implements MethodBeforeAdvice
{
   /**
    * Extend Call object intercept injecting WS Lifetime information in order to prepare invocation
    * @param arg0 method name
    * @param arg1 args
    * @param arg2 target object
    * @throws Throwable exception
    */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        // TODO raffaele.picardi: implement advice Wslt
        
    }

}


