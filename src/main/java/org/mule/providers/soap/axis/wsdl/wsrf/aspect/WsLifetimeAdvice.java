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


import org.mule.providers.soap.axis.wsdl.wsrf.StubPriorityAdvice;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.axis.wsdl.wsrf.wsrfcommand.IWsrfCommand;
import org.mule.providers.soap.axis.wsdl.wsrf.wsrfcommand.WsrfCommandFactory;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSLTOperationNotFound;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFException;
import org.mule.umo.UMOEvent;

import java.lang.reflect.Method;

import org.apache.axis.client.Call;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;
/**
 * This Stub Advice perform WS Lifetime request using WSDL2JAVA stub
 */
public class WsLifetimeAdvice extends StubPriorityAdvice implements MethodBeforeAdvice
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
        //TODO raffaele.picardi: manage standalone property of WsLifetimeAdvice
        UMOEvent event = (UMOEvent) arg1[1];
        Call call = (Call) arg1[0];
        
        String standaloneStr =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_STANDALONE_MODE);

        boolean isStandalone = false;
            
        if (standaloneStr != null)  
        {
            isStandalone = standaloneStr.equals(WSRFParameter.STANDALONE_YES);
        }
        if (call == null && !isStandalone)
        {
            return;
        }
        String operationLT =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCE_LIFETIME_OPERATION);
        if (operationLT == null)
        {
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Skipped WS-LT operation : no operation defined " + WSRFParameter.WSRF_RESOURCE_LIFETIME_OPERATION);
            return;
        }
        
      
        
        IWsrfCommand commandOperation;
        try 
        {
            commandOperation = WsrfCommandFactory.createLTCommand(operationLT);
        }
        catch (WSLTOperationNotFound e)
        {
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Skipped WS-LT operation : no operation defined " + WSRFParameter.WSRF_RESOURCE_LIFETIME_OPERATION);
            return;
        }
        
        commandOperation.setIsStandaloneMode(isStandalone);
        commandOperation.setEvent(event);
        commandOperation.setCall(call);
        try 
        {
            commandOperation.execute();
        }
        catch (WSRFException e)
        {
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " WS-LT operation error " + WSRFParameter.WSRF_RESOURCE_LIFETIME_OPERATION);
            return;
            
        }


        
    }

}


