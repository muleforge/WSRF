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


import org.mule.extras.client.MuleClient;
import org.mule.extras.client.RemoteDispatcher;

import org.mule.providers.soap.axis.wsdl.wsrf.test.util.MessagesTest;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.tck.FunctionalTestCase;
import org.mule.umo.UMOException;
import org.mule.umo.UMOMessage;

import java.util.HashMap;
import java.util.Map;

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
public class AxisWsdlWsrfMessageDispatcherRemClientAddressAdviceIntegrationTestCase extends FunctionalTestCase
{
    /**
     * server time live
     */
    private static final int WAIT = 300000;
    /**
     * Constructor
     *
     */
    public AxisWsdlWsrfMessageDispatcherRemClientAddressAdviceIntegrationTestCase () 
    {
        System.out.println("starting...");
        
    }
    /**
     * 
     * @return string
     */
protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-remclient-addressadvice-mule-config.xml";
    }
/**
 * Use MuleClient to test Remote Server by RemoteDispatcher  tcp://ip:port on vm://vmQueueObjectArray 
 *   
 * @throws Exception exception
 */
public final void  testCall() throws Exception
    {
       
        Logger.getLogger(this.getClass()).info("waiting for Remote message...");
        
        Logger.getLogger(this.getClass()).info("press enter to shutdown server");
    
        //TODO raffaele.picardi: report this class out of this test and remain only Monitor.onWait for itnegration test
     //   Monitor.waitOn(WAIT);
    //Init section to transfer in External system
        UMOMessage result=null;
     MuleClient client;
        while (true) {
     try {
            
            client = new MuleClient();
            RemoteDispatcher rd = client.getRemoteDispatcher(MessagesTest.getString("EHelloServiceObjectArrayTest.10")); //$NON-NLS-1$
     
/*      SoapMethod method = new SoapMethod(new QName("", Messages.getString("SOAP_METHOD_NAME")));
       method.addNamedParameter(new QName( Messages.getString("NAMED_PARAMETER")), new javax.xml.namespace.QName( Messages.getString("SERVICE_NAMESPACE_URI"), Messages.getString("RETURN_QNAME")), "in");
       method.setReturnType( new javax.xml.namespace.QName(Messages.getString("SERVICE_NAMESPACE_URI"), Messages.getString("RETURN_QTYPE_NAME")));
       method.setReturnClass(Class.forName(Messages.getString("RETURN_CLASSNAME")));
       */
       
       Map props = new HashMap();
       props.put("style", "wrapped");
       props.put("use", "literal"); 
       //props.put(MuleProperties.MULE_SOAP_METHOD, method);
      
       props.put("resourceKey", MessagesTest.getString("RESOURCE_KEY"));
       props.put(WSRFParameter.SERVICE_NAMESPACE , MessagesTest.getString("SERVICE_NAMESPACE_URI"));
       props.put(WSRFParameter.RESOURCE_KEY_NAME , MessagesTest.getString("RESOURCE_KEY_NAME"));
       props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
       
      /* props.put(WSRFParameter.RETURN_QTYPE,  new javax.xml.namespace.QName(Messages.getString("SERVICE_NAMESPACE_URI"),  Messages.getString("RETURN_QTYPE_NAME")));
       
       props.put(WSRFParameter.RETURN_QTYPE,  new javax.xml.namespace.QName(Messages.getString("SERVICE_NAMESPACE_URI"),  Messages.getString("RETURN_QTYPE_NAME")));
       props.put(WSRFParameter.RETURN_QTYPE,  new javax.xml.namespace.QName(Messages.getString("SERVICE_NAMESPACE_URI"),  Messages.getString("RETURN_QTYPE_NAME")));
       */
       
       props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
       props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));
       result = rd.sendRemote("vm://vmQueue", new Integer(2), props);
    
            //result = rd.sendRemote(Messages.getString("EHelloServiceObjectArrayTest.11"),"", null); //$NON-NLS-1$
             //logger.info(this, "invoke done.",""); //$NON-NLS-1$
       System.out.println("invoke done.");
             Thread.sleep(5000);
        } catch (UMOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (result!=null)
                 System.out.println(result.getPayload().toString()); //$NON-NLS-1$
                    else  System.out.println("result is null");
            
        }
        //end section to transfer in External system
        }
        
    }
}

