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
import org.mule.impl.MuleMessage;
import org.mule.impl.RequestContext;
import org.mule.impl.endpoint.MuleEndpoint;
import org.mule.impl.endpoint.MuleEndpointURI;

import org.mule.providers.soap.SoapMethod;
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.MessagesTest;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;


import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOEventContext;
import org.mule.umo.UMOMessage;


import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service (use this code into
 * message dispatcher).
 * 
 * @author raffaele.picardi
 */
public class AxisWsdlWsrfWSRFMainAdviceTestCase extends FunctionalTestCase
{
    

    
    /**
     * Constructor.
     */
    public AxisWsdlWsrfWSRFMainAdviceTestCase()
    {
        System.out.println("starting...");

    }

    /**
     * Gets the config resources.
     * 
     * @return string
     */
    protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-main-mule-config.xml";
    }
    /**
     * test copy of from wsrfOption to message properties with overwright
     * 
     * @throws Exception exception
     */
    public final void testCopyWithOverwright() throws Exception
    {
      
        MuleClient client = new MuleClient();
        
        

        String muleEndpointStrURI = "vm://vmQueue";
        MuleEndpointURI muleEndpointURI = new MuleEndpointURI(muleEndpointStrURI);
      
        MuleEndpoint immutableEndpoint = new MuleEndpoint();
        immutableEndpoint.setEndpointURI(muleEndpointURI);
        
        SoapMethod method = new SoapMethod(new QName("", MessagesTest.getString("SOAP_METHOD_NAME")));
        method.addNamedParameter(new QName(MessagesTest.getString("NAMED_PARAMETER")),
            new javax.xml.namespace.QName(MessagesTest.getString("SERVICE_NAMESPACE_URI"),
                MessagesTest.getString("RETURN_QNAME")), "in");
        method.setReturnType(new javax.xml.namespace.QName(MessagesTest.getString("SERVICE_NAMESPACE_URI"),
            MessagesTest.getString("RETURN_QTYPE_NAME")));
        method.setReturnClass(Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));

        Map props = new HashMap();
        props.put("style", "wrapped");
        props.put("use", "literal");
        props.put(MuleProperties.MULE_SOAP_METHOD, method);


        props.put(WSRFParameter.SERVICE_NAMESPACE, MessagesTest.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME, MessagesTest.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
            MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));
        
        //force overwright
        props.put(WSRFParameter.WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING, "no");
       
        
        UMOMessage firstMessage = new MuleMessage(new Integer(WSRFParameter.FIRST_VALUE_IN) , props);
        
        String corID = "123123";
        
        firstMessage.setCorrelationId(corID);
        UMOMessage result = client.send("vm://vmQueue" , firstMessage);
        
        
    
        

        
        String firstResourceKey = (String) result.getProperty(WSRFParameter.RESOURCE_KEY);
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        
      
       UMOMessage secondMessage = new MuleMessage(new Integer(WSRFParameter.SECOND_VALUE_IN), props);

       secondMessage.setCorrelationId(corID);
        
       result = client.send("vm://vmQueue" , secondMessage);

        
  
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        String secondResourceKey = (String) result.getProperty(WSRFParameter.RESOURCE_KEY);
        
  
        
        assertNotSame(firstResourceKey , secondResourceKey);

        
        System.out.println(result.getPayload());

        

    }
    
    /**
     * test copy of from wsrfOption to message properties 
     * 
     * @throws Exception exception
     */
    public final void testCopy() throws Exception
    {
      
        MuleClient client = new MuleClient();
        
        

        String muleEndpointStrURI = "vm://vmQueue";
        MuleEndpointURI muleEndpointURI = new MuleEndpointURI(muleEndpointStrURI);
      
        MuleEndpoint immutableEndpoint = new MuleEndpoint();
        immutableEndpoint.setEndpointURI(muleEndpointURI);
        
        SoapMethod method = new SoapMethod(new QName("", MessagesTest.getString("SOAP_METHOD_NAME")));
        method.addNamedParameter(new QName(MessagesTest.getString("NAMED_PARAMETER")),
            new javax.xml.namespace.QName(MessagesTest.getString("SERVICE_NAMESPACE_URI"),
                MessagesTest.getString("RETURN_QNAME")), "in");
        method.setReturnType(new javax.xml.namespace.QName(MessagesTest.getString("SERVICE_NAMESPACE_URI"),
            MessagesTest.getString("RETURN_QTYPE_NAME")));
        method.setReturnClass(Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));

        Map props = new HashMap();
        props.put("style", "wrapped");
        props.put("use", "literal");
        props.put(MuleProperties.MULE_SOAP_METHOD, method);


        props.put(WSRFParameter.SERVICE_NAMESPACE, MessagesTest.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME, MessagesTest.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
            MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));

       
        
        UMOMessage firstMessage = new MuleMessage(new Integer(WSRFParameter.FIRST_VALUE_IN) , props);
        
        String corID = "123123";
        
        firstMessage.setCorrelationId(corID);
        UMOMessage result = client.send("vm://vmQueue" , firstMessage);
        
        
    
        

        
        String firstResourceKey = (String) result.getProperty(WSRFParameter.RESOURCE_KEY);
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        
      
       UMOMessage secondMessage = new MuleMessage(new Integer(WSRFParameter.SECOND_VALUE_IN), props);

       secondMessage.setCorrelationId(corID);
        
       result = client.send("vm://vmQueue" , secondMessage);

        
  
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        String secondResourceKey = (String) result.getProperty(WSRFParameter.RESOURCE_KEY);
        
  
        
        assertEquals(firstResourceKey , secondResourceKey);

        
        System.out.println(result.getPayload());
      
    }
    
   
}
