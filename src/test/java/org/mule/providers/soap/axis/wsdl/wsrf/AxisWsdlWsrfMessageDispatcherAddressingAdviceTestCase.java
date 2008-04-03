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

import org.mule.config.MuleProperties;
import org.mule.extras.client.MuleClient;
import org.mule.providers.soap.NamedParameter;
import org.mule.providers.soap.SoapMethod;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.wsdl.wsrf.instance.Response;

import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOMessage;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;



/*

import org.mule.config.MuleProperties;
import org.mule.extras.client.MuleClient;
import org.mule.providers.soap.NamedParameter;
import org.mule.providers.soap.SoapMethod;
import org.mule.tck.AbstractMuleTestCase;
import org.mule.umo.UMOMessage;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

*/

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
 * 
 * @throws Exception exception
 */
public final void  testCall() throws Exception
    {
 
    
        //TODO raffaele.picardi: fix code to invoke grid service
        MuleClient client = new MuleClient();
        SoapMethod method = new SoapMethod(new QName("", "add"));
        method.addNamedParameter(new QName("add"), new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", "addResponse"), "in");
        method.setReturnType( new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", ">addResponse"));
        method.setReturnClass(Response.class);
        
        Map props = new HashMap();
        props.put("style", "wrapped");
        props.put("use", "literal"); 
        props.put(MuleProperties.MULE_SOAP_METHOD, method);
        props.put("resourceKey", "13829853");
        props.put(WSRFParameter.SERVICE_NAMESPACE , "http://www.globus.org/namespaces/examples/core/MathService_instance");
        props.put(WSRFParameter.RESOURCE_KEY_NAME , "MathResourceKey");
        props.put(WSRFParameter.RETURN_QNAME, "addResponse");
        props.put(WSRFParameter.RETURN_QTYPE,  new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", ">addResponse"));
        props.put(WSRFParameter.RETURN_CLASS, Response.class);
        props.put(WSRFParameter.SOAP_ACTION_URI,"http://www.globus.org/namespaces/examples/core/MathService_instance/MathPortType/addRequest");
        UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);
       


      // result = client.send("vm://vmQueue", new Integer(2), props);
        
       

        assertNotNull(result);
        assertNotNull(result.getPayload()); //return class object 
        System.out.println(result.getPayload());
        
        /*
       result = client.send("vm://vmQueue", new Integer(22), props);
        

        assertNotNull(result);
        assertNotNull(result.getPayload());
        System.out.println(result.getPayload());
        */
        
    }
}

