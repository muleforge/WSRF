/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.providers.soap.axis.wsdl.wsrf.test.integration;

import org.mule.config.MuleProperties;
import org.mule.extras.client.MuleClient;
import org.mule.providers.soap.SoapMethod;
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.MessagesTest;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOMessage;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service
 * @author raffaele.picardi
 *
 */
public class AxisWsdlWsrfMessageDispatcherAddressingAdviceIntegrationTestCase extends FunctionalTestCase
{
    /**
     * Constructor
     *
     */
    public AxisWsdlWsrfMessageDispatcherAddressingAdviceIntegrationTestCase () 
    {
        System.out.println("start...");
        
    }
    /**
     * 
     * @return string
     */
protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-addressing-mule-config.xml";
    }
/**
 * Test a single Call invocation method of Globus Grid Service using a Mule Message  with ResourceKey
 * @throws Exception exception
 */
public final void  testCallSingleInstanceGlobusServiceByMessageWithResourceKey() throws Exception
    {
 
        
      
        MuleClient client = new MuleClient();
        SoapMethod method = new SoapMethod(new QName("", MessagesTest.getString("SOAP_METHOD_NAME")));
        method.addNamedParameter(new QName( MessagesTest.getString("NAMED_PARAMETER")), new javax.xml.namespace.QName( MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QNAME")), "in");
        method.setReturnType( new javax.xml.namespace.QName(MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
        method.setReturnClass(Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        
        
        Map props = new HashMap();
        props.put("style", "wrapped");
        props.put("use", "literal"); 
        props.put(MuleProperties.MULE_SOAP_METHOD, method);
       
        props.put("resourceKey", MessagesTest.getString("RESOURCE_KEY"));
        props.put(WSRFParameter.SERVICE_NAMESPACE , MessagesTest.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME , MessagesTest.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE,  new javax.xml.namespace.QName(MessagesTest.getString("SERVICE_NAMESPACE_URI"),  MessagesTest.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));
        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);
       

        assertNotNull(result);
        assertNotNull(result.getPayload()); 
        System.out.println(result.getPayload());
        
   
        
    }




}

