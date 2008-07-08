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
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.InfoEntryCatalogType;
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.MessagesTest;
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.OsdEntry;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOMessage;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service
 * 
 * @author raffaele.picardi
 */
public class AxisWsdlWsrfMessageDispatcherLifeTimeAdviceTestCase extends FunctionalTestCase
{
    /**
     * Constructor
     */
    public AxisWsdlWsrfMessageDispatcherLifeTimeAdviceTestCase()
    {
        System.out.println("start...");

    }

    /**
     * @return string
     */
    protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-lifetime-mule-config.xml";
    }

    /**
     * Test a single Call invocation method of Globus Grid Service using a Mule
     * Message with ResourceKey created from factory service and perform a
     * GetResourceProperty Operation
     * 
     * @throws Exception exception
     */
    public final void testCallSingleInstanceGlobusServiceByMessageFactoryAndDestroy()
        throws Exception
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

        // props.put(WSRFParameter.SERVICE_NAMESPACE,
        // Messages.getString("RESOURCE_KEY"));
        props.put(WSRFParameter.SERVICE_NAMESPACE, MessagesTest.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME, MessagesTest.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
            MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));

        // factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS,
            MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));
        props.put(MuleProperties.MULE_METHOD_PROPERTY, "add");
        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);

        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));

        props.put(WSRFParameter.RESOURCE_KEY, result.getProperty(WSRFParameter.RESOURCE_KEY));

       

        props.put(WSRFParameter.WSRF_RESOURCE_LIFETIME_OPERATION,
            MessagesTest.getString("RESOURCE_LIFETIME_DESTROY_OPERATION"));
       
        
        result = client.send("vm://vmQueue", new Integer(2000) , props);

        assertNotNull(result);
        assertNotNull(result.getPayload());
        
        assertNotNull(result.getProperty(WSRFParameter.WSRF_LIFETIME_RESPONSE));


    }

     /** Test a single Call invocation method of Globus Grid Service using a Mule
     * Message with ResourceKey created from factory service and perform set termination time 
     * @throws Exception exception
     */
    public final void testCallSingleInstanceGlobusServiceByMessageFactoryAndSetTerminationTime()
        throws Exception
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

        // props.put(WSRFParameter.SERVICE_NAMESPACE,
        // Messages.getString("RESOURCE_KEY"));
        props.put(WSRFParameter.SERVICE_NAMESPACE, MessagesTest.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME, MessagesTest.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
            MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));

        // factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS,
            MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));
        props.put(MuleProperties.MULE_METHOD_PROPERTY, "add");
        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);

        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));

        props.put(WSRFParameter.RESOURCE_KEY, result.getProperty(WSRFParameter.RESOURCE_KEY));

        // TODO raffaele.picardi: this test failed why this following line needs to be uncommented when standlone mode is fix. 
        //props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_STANDALONE_MODE, WSRFParameter.STANDALONE_YES);

        props.put(WSRFParameter.WSRF_RESOURCE_LIFETIME_OPERATION,
            MessagesTest.getString("RESOURCE_LIFETIME_SCHEDULED_OPERATION"));
       
        props.put(WSRFParameter.WS_LT_SCHEDULED_SECONDS_TIME,
            MessagesTest.getString("RESOURCE_LIFETIME_SECONDS_TIME_TO_SCHEDULED_DESTRUCTION"));
       
        result = client.send("vm://vmQueue", new Integer(2000) , props);

        assertNotNull(result);
        assertNotNull(result.getPayload());
        
        assertNotNull(result.getProperty(WSRFParameter.WSRF_LIFETIME_RESPONSE));
        
        
        
    }

}
