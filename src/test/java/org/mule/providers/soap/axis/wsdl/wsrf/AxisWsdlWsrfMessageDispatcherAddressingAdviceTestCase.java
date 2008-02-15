/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.providers.soap.axis.wsdl.wsrf;

import org.mule.extras.client.MuleClient;
import org.mule.providers.soap.NamedParameter;
import org.mule.providers.soap.SoapMethod;
import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOMessage;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;



/*import org.mule.config.MuleProperties;
import org.mule.extras.client.MuleClient;
import org.mule.providers.soap.NamedParameter;
import org.mule.providers.soap.SoapMethod;
import org.mule.tck.AbstractMuleTestCase;



import org.mule.umo.UMOMessage;


import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;*/


/**
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service
 * @author raffaele.picardi
 *
 */
public class AxisWsdlWsrfMessageDispatcherAddressingAdviceTestCase extends FunctionalTestCase
{
    /**
     * Constructor
     *
     */
    public AxisWsdlWsrfMessageDispatcherAddressingAdviceTestCase () 
    {
        System.out.println("starting...");
        
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
 * 
 * @throws Exception exception
 */
public final void  testCall() throws Exception
    {
        //TODO raffaele.picardi: fix code to invoke grid service
        MuleClient client = new MuleClient();
        SoapMethod method = new SoapMethod(new QName("", "add"));
        method.addNamedParameter(new QName("add"), NamedParameter.XSD_INT, "in");

        Map props = new HashMap();
        props.put("style", "wrapped");
        props.put("use", "literal"); 
        props.put("method", method);

        
        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);
       

        assertNotNull(result);
        assertNotNull(result.getPayload());
        System.out.println(result.getPayload());
        
        /*
       result = client.send("vm://vmQueue", new Integer(22), props);
        

        assertNotNull(result);
        assertNotNull(result.getPayload());
        System.out.println(result.getPayload());
        */
        
    }
}

