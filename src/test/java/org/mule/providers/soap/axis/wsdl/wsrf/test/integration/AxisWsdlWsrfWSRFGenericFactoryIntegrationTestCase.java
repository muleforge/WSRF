/*
 * $Id: AxisWsdlWsrfWSRFGenericFactoryTestCase.java 293 2008-05-09 14:30:01Z raffaele.picardi $
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

import org.mule.impl.endpoint.MuleEndpoint;
import org.mule.impl.endpoint.MuleEndpointURI;

import org.mule.providers.soap.SoapMethod;
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.MessagesTest;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;


import org.mule.tck.FunctionalTestCase;
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
public class AxisWsdlWsrfWSRFGenericFactoryIntegrationTestCase extends FunctionalTestCase
{
    

    
    /**
     * Constructor.
     */
    public AxisWsdlWsrfWSRFGenericFactoryIntegrationTestCase()
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
        return "axis-wsdl-wsrf-globus-grid-service-factory-addressing-mule-config.xml";
    }

    /**
     * Test Resource key creation using only WSRF_FACTORY_SERVICE_ADDRESS.
     * 
     * @throws Exception exception
     */
    public final void testCreateServiceInstanceFromFactoryService() throws Exception
    {
        MuleClient client = new MuleClient();
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

        //props.put(WSRFParameter.SERVICE_NAMESPACE, Messages.getString("RESOURCE_KEY"));
        props.put(WSRFParameter.SERVICE_NAMESPACE, MessagesTest.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME, MessagesTest.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
            MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));

        //factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));

        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);

        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));

        props.put(WSRFParameter.RESOURCE_KEY, result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        result = client.send("vm://vmQueue", new Integer(2000) , props);
        
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
    }
    
    /**
     * Test Resource key creation using only WSRF_FACTORY_SERVICE_ADDRESS and
     * WSRF_MULE_SESSION_RESOURCE_KEY_MAPPING=yes. 
     * TODO raffaele.picardi: develop test  using session 
     * @throws Exception exception
     */
    
    /*
    public final void testCreateServiceInstanceFromFactoryServiceUsingSession() throws Exception
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

        //factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));
        props.put(WSRFParameter.WSRF_MULE_SESSION_RESOURCE_KEY_MAPPING, "yes");
        
        UMOMessage firstMessage = new MuleMessage(new Integer(WSRFParameter.FIRST_VALUE_IN) , props);
        
       
        UMOMessage result = client.send("vm://vmQueue" , firstMessage);
        
        
    
        

        
        String firstResourceKey = (String) result.getProperty(WSRFParameter.RESOURCE_KEY);
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        
      
       UMOMessage secondMessage = new MuleMessage(new Integer(WSRFParameter.SECOND_VALUE_IN), props);
  
       result = client.send("vm://vmQueue" , secondMessage);

        
  
        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        
        String secondResourceKey = (String) result.getProperty(WSRFParameter.RESOURCE_KEY);
        
  
        
        assertEquals(firstResourceKey , secondResourceKey);

        
        System.out.println(result.getPayload());
        System.out.println("Resource Key just defined in session : " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        

        
       
    }*/
    
    /**
     * Test Resource key creation using only WSRF_FACTORY_SERVICE_ADDRESS and
     * WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING=yes. 
     * 
     * @throws Exception exception
     */
    public final void testCreateServiceInstanceFromFactoryServiceUsingCorrelation() throws Exception
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

        //factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));
        props.put(WSRFParameter.WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING, "yes");
        
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
        System.out.println("Resource Key just defined in session : " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        

        
       
    }
    
    
    
    /**
     * Test Resource key creation using  WSRF_FACTORY_SERVICE_ADDRESS and
     * WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING=yes.  
     * Test use all wsrfOption customizated in endpoint configuration
     * 
     * @throws Exception exception
     */
    public final void testCreateServiceInstanceFromFactoryServiceUsingAllwsrfOptionCustomizated() throws Exception
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

        //factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));
        props.put(WSRFParameter.WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING, "yes");
        props.put(WSRFParameter.WSRF_FACTORY_PORT_TYPE_PORT_ADDRESS, MessagesTest.getString("FACTORY_PORT_TYPE_PORT_ADDRESS"));
        props.put(WSRFParameter.WSRF_FACTORY_PORT_TYPE, MessagesTest.getString("FACTORY_PORT_TYPE"));
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_NAME, MessagesTest.getString("FACTORY_SERVICE_NAME"));
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_NS, MessagesTest.getString("FACTORY_SERVICE_NS"));
        props.put(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_OPERATION_NAME, MessagesTest.getString("FACTORY_CREATE_RESOURCE_OPERATION_NAME"));
        props.put(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_REQUEST_NS, MessagesTest.getString("FACTORY_CREATE_RESOURCE_REQUEST_NS"));
        props.put(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_RESPONSE_NAME, MessagesTest.getString("FACTORY_CREATE_RESOURCE_RESPONSE_NAME"));

        
        UMOMessage firstMessage = new MuleMessage(new Integer(WSRFParameter.FIRST_VALUE_IN) , props);
        
        String corID = "12312355";
        
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
        System.out.println("Resource Key just defined in session : " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        

        
       
    }
    
    
    /**
     * Test Resource key creation using  WSRF_FACTORY_SERVICE_ADDRESS and
     * WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING=yes.  changing Create Resource Response name
     * Test use all wsrfOption customizated in endpoint configuration
     * 
     * @throws Exception exception
     */
    public final void testCreateServiceInstanceFromFactoryServiceChanginDefaultFactoryValues() throws Exception
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

        //factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));
        props.put(WSRFParameter.WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING, "yes");
        props.put(WSRFParameter.WSRF_FACTORY_PORT_TYPE_PORT_ADDRESS, MessagesTest.getString("FACTORY_PORT_TYPE_PORT_ADDRESS"));
        props.put(WSRFParameter.WSRF_FACTORY_PORT_TYPE, MessagesTest.getString("FACTORY_PORT_TYPE"));
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_NAME, MessagesTest.getString("FACTORY_SERVICE_NAME"));
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_NS, MessagesTest.getString("FACTORY_SERVICE_NS"));
        props.put(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_OPERATION_NAME, MessagesTest.getString("FACTORY_CREATE_RESOURCE_OPERATION_NAME"));
        props.put(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_REQUEST_NS, MessagesTest.getString("FACTORY_CREATE_RESOURCE_REQUEST_NS"));
        props.put(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_RESPONSE_NAME, MessagesTest.getString("FACTORY_CREATE_RESOURCE_RESPONSE_NAME2"));

        
        UMOMessage firstMessage = new MuleMessage(new Integer(WSRFParameter.FIRST_VALUE_IN) , props);
        
        String corID = "12398123";
        
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
        System.out.println("Resource Key just defined in session : " + result.getProperty(WSRFParameter.RESOURCE_KEY));
        

        
       
    }
    
    
    
}
