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


import org.mule.tck.FunctionalTestCase;

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
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service
 * @author raffaele.picardi
 *
 */
public class AxisWsdlWsrfMessageDispatcherRemClientObjectArrayTestCase extends FunctionalTestCase
{
    /**
     * Constructor
     *
     */
    public AxisWsdlWsrfMessageDispatcherRemClientObjectArrayTestCase () 
    {
        System.out.println("starting...");
        
    }
    /**
     * 
     * @return string
     */
protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-remote-client-mule-config.xml";
    }
/**
 * Use MuleClient to test Remote Server by RemoteDispatcher  tcp://ip:port on vm://vmQueue sending an Object Array 
 *   
 * @throws Exception exception
 */
public final void  testCall() throws Exception
    {
        /* remote client code:
         * client = new MuleClient(true);
         * RemoteDispatcher rd = client.getRemoteDispatcher("tcp://192.168.4.64:60504");
         * result = rd.sendRemote("vm://vmQueue",new Object[] {"string1","strin2"}, null);
         */
        Logger.getLogger(this.getClass()).info("waiting for Remote message...");
        Monitor.waitOn(999999);
        
    }
}

