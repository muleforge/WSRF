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


import org.mule.config.MuleProperties;
import org.mule.providers.soap.axis.wsdl.AxisWsdlConnector;
import org.mule.providers.soap.axis.wsdl.wsrf.StubPriorityAdvice;

import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.axis.wsdl.wsrfcommand.IWsrfCommand;
import org.mule.providers.soap.axis.wsdl.wsrfcommand.WsrfCommandFactory;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFException;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFOperationException;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRPOperationNotFound;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortType;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericServiceAddressingLocator;
import org.mule.umo.UMOEvent;
import org.mule.umo.endpoint.UMOEndpointURI;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.SetResourcePropertiesResponse;
import org.springframework.aop.MethodBeforeAdvice;
/**
 * This Stub Advice perform Wsrp request using WSDL2JAVA stub
 */
public class WsrpStubAdvice extends StubPriorityAdvice implements MethodBeforeAdvice
{
   

    /**
    * Advice perform WS-RP operation . and append its response as string property WSRF_XML_GETSET_SOAP_RESPONSE in WSRFExtraResponse map in Event Message source 
    * If standalone mode == yes than will be only injected wsrf information in axis call just created from wsdl provider without perform a new Call invocation.
    * @param arg0 method name
    * @param arg1 args
    * @param arg2 target object
    * @throws Throwable exception
    */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {

        //TODO raffaele.picardi: manage standalone property of WsResourcePropertyAdvice
        // TODO raffaele.picardi: manage setResourceProperty of WsResourcePropertyAdvice
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
        String operationRP =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION);

        
      
        
        IWsrfCommand commandOperation;
        try 
        {
            commandOperation = WsrfCommandFactory.createRPCommand(operationRP);
        }
        catch (WSRPOperationNotFound e)
        {
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Skipped WS-RP operation : no operation defined " + WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION);
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
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " WS-RP operation error " + WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION);
            return;
            
        }


   
    }

}


