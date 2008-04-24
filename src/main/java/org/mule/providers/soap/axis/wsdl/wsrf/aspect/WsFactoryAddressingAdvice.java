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
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;

import org.mule.umo.UMOEvent;


import java.lang.reflect.Method;



import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;





import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.message.addressing.AddressingHeaders;
import org.apache.axis.message.addressing.Constants;
import org.apache.axis.message.addressing.ReferencePropertiesType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.SerializationException;
import org.globus.wsrf.impl.SimpleResourceKey;

import org.springframework.aop.MethodBeforeAdvice;


/**
 * WsAddressing Aspect to inject Ws-Addressing information in SOAP - Header and/or
 * manage WSDL Stub.
 */
public class WsFactoryAddressingAdvice extends BasePriorityAdvice implements MethodBeforeAdvice
{
    



    /**
     * Default Constructor.
     */
    public WsFactoryAddressingAdvice()
    {
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");
    }
    
    /**
     * Inject call informations.
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
       Call call = (Call) arg1[0];
       UMOEvent event = (UMOEvent) arg1[1];
       
       //if (event.getMessage().getProperty(arg0))

    }
    
 


}


