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


import org.mule.providers.soap.axis.wsdl.wsrf.factory.CreateResource;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.CreateResourceResponse;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryPortType;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryServiceAddressingLocator;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortType;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericServiceAddressingLocator;

import org.mule.tck.FunctionalTestCase;


import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.log4j.Logger;



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
    public AxisWsdlWsrfWSRFGenericFactoryTestCase () 
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
    //TODO raffaele.picardi: complete test with instance locator service
    FactoryServiceAddressingLocator factoryLocator = new FactoryServiceAddressingLocator();
    //MathServiceAddressingLocator instanceLocator = new MathServiceAddressingLocator();
    //GetResourcePropertyResponse valueRP;


    try 
    {
        String factoryURI = "http://127.0.0.1:8080/wsrf/services/examples/core/factory/MathFactoryService";
        EndpointReferenceType factoryEPR, instanceEPR;
        FactoryPortType mathFactory;
        //MathPortType math;

        // Get factory portType
        factoryEPR = new EndpointReferenceType();
        factoryEPR.setAddress(new Address(factoryURI));
        mathFactory = factoryLocator.getFactoryPortTypePort(factoryEPR);

        // Create resource and get endpoint reference of WS-Resource.
        // This resource is our "instance".
        CreateResourceResponse createResponse = mathFactory
                .createResource(new CreateResource());
        instanceEPR = createResponse.getEndpointReference();
        
        createResponse = mathFactory
        .createResource(new CreateResource());
        createResponse = mathFactory
        .createResource(new CreateResource());
       
        assertNotNull(instanceEPR);

        Logger.getLogger(this.getClass()).info("instance EPR: " + instanceEPR  + '\n');
        
        GenericServiceAddressingLocator instanceLocator = new GenericServiceAddressingLocator();
        GenericPortType serviceInstance = instanceLocator.getMathPortTypePort(instanceEPR);
        assertNotNull(serviceInstance);
        Object o = serviceInstance.add(5);
        
        assertNotNull(o);
        Logger.getLogger(this.getClass()).info("response of operation : " + o  + '\n');
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
        fail("Should not have been thrown"); 
    }
}
}

