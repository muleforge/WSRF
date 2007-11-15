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




import java.lang.reflect.Method;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;



import org.apache.axis.client.Call;
import org.apache.axis.message.addressing.AddressingHeaders;
import org.apache.axis.message.addressing.Constants;
import org.apache.axis.message.addressing.ReferencePropertiesType;
import org.apache.axis.message.addressing.To;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.SerializationException;
import org.globus.wsrf.impl.SimpleResourceKey;
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
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");
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
        Logger.getLogger(this.getClass()).log(Level.INFO,  this.getClass().getName() + " : advice method started.");
        Call call = (Call) arg1[0];

        String url =  call.getTargetEndpointAddress();

        AddressingHeaders headers = new AddressingHeaders();
        //create a reference property
        QName keyName = new QName("http://axis.org", "VersionKey");
        String keyValue = "123";
         
        SimpleResourceKey key = new SimpleResourceKey(keyName, keyValue);

        ReferencePropertiesType props = new ReferencePropertiesType();
        
        //convert to SOAPElement and add to the list
        SOAPElement key2 = null;
            try
            {
                key2 = key.toSOAPElement();
                props.add(key2);
            }
            catch (SerializationException ex)
            {
             ex.printStackTrace();
            }
      
        headers.setTo(new To(url));
        headers.setReferenceProperties(props);
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : addressing header set to: " + url);

        //pass the addressing info to the addressing handler
        call.setProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS, headers);
        
        call.setTargetEndpointAddress(new URL(url));
            
    }

}


