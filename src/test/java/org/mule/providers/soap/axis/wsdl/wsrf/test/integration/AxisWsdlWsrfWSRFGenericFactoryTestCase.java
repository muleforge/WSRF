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
import org.mule.providers.soap.axis.wsdl.wsrf.test.util.Messages;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;

import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOMessage;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;


/**
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service (use this code into message dispatcher)
 * @author raffaele.picardi
 *
 */
public class AxisWsdlWsrfWSRFGenericFactoryTestCase extends FunctionalTestCase
{
    /**
     * Constructor
     *
     */
    public AxisWsdlWsrfWSRFGenericFactoryTestCase()
    {
        System.out.println("starting...");

    }

    /**
     * 
     * @return string
     */
    protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-factory-addressing-mule-config.xml";
    }

    /**
     * Test Resource key creation using only WSRF_FACTORY_SERVICE_ADDRESS
     * @throws Exception exception
     */
    public final void testCreateServiceInstanceFromFactoryService() throws Exception
    {
        MuleClient client = new MuleClient();
        SoapMethod method = new SoapMethod(new QName("", Messages.getString("SOAP_METHOD_NAME")));
        method.addNamedParameter(new QName(Messages.getString("NAMED_PARAMETER")),
            new javax.xml.namespace.QName(Messages.getString("SERVICE_NAMESPACE_URI"),
                Messages.getString("RETURN_QNAME")), "in");
        method.setReturnType(new javax.xml.namespace.QName(Messages.getString("SERVICE_NAMESPACE_URI"),
            Messages.getString("RETURN_QTYPE_NAME")));
        method.setReturnClass(Class.forName(Messages.getString("RETURN_CLASSNAME")));

        Map props = new HashMap();
        props.put("style", "wrapped");
        props.put("use", "literal");
        props.put(MuleProperties.MULE_SOAP_METHOD, method);

        //props.put(WSRFParameter.SERVICE_NAMESPACE, Messages.getString("RESOURCE_KEY"));
        props.put(WSRFParameter.SERVICE_NAMESPACE, Messages.getString("SERVICE_NAMESPACE_URI"));
        props.put(WSRFParameter.RESOURCE_KEY_NAME, Messages.getString("RESOURCE_KEY_NAME"));
        props.put(WSRFParameter.RETURN_QNAME, Messages.getString("RETURN_QNAME"));
        props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
            Messages.getString("SERVICE_NAMESPACE_URI"), Messages.getString("RETURN_QTYPE_NAME")));
        props.put(WSRFParameter.RETURN_CLASS, Class.forName(Messages.getString("RETURN_CLASSNAME")));
        props.put(WSRFParameter.SOAP_ACTION_URI, Messages.getString("SOAP_ACTION_URI"));

        //factory properties
        props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, Messages.getString("FACTORY_SERVICE_ADDRESS"));

        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);

        assertNotNull(result);
        assertNotNull(result.getPayload());
        assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
        System.out.println(result.getPayload());
        System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));

    }
}
