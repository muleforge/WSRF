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


import java.lang.reflect.Method;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;



// TODO: Auto-generated Javadoc
/**
 * Main Advice in order to perform Log operations and other ...
 */
public class WsMainAdvice extends BasePriorityAdvice implements MethodBeforeAdvice
{
    
    /* getPriority
     * @see org.mule.providers.soap.axis.wsdl.wsrf.BasePriorityAdvice#getPriority()
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
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        Logger.getLogger(this.getClass()).log(Level.INFO,  this.getClass().getName() + " : advice method started.");
        
    }
 

}


