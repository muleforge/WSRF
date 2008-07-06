/*
 * $Id: AxisWsdlWsrfMessageDispatcherResourcePropertyAdviceTestCase.java 309 2008-06-05 17:15:50Z raffaele.picardi $
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

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Test Message Dispatcher synchr for Wsdl Wsrf Web Service
 * @author raffaele.picardi
 *
 */
public class AxisWsdlWsrfMessageDispatcherResourcePropertyAdviceTestCase extends FunctionalTestCase
{
    /**
     * Constructor
     *
     */
    public AxisWsdlWsrfMessageDispatcherResourcePropertyAdviceTestCase () 
    {
        System.out.println("start...");
        
    }
    /**
     * 
     * @return string
     */
protected final String getConfigResources()
    {
        return "axis-wsdl-wsrf-globus-grid-service-resource-property-mule-config.xml";
    }
/**
 * Test a single Call invocation method of Globus Grid Service using a Mule Message  with ResourceKey created from factory service and perform a GetResourceProperty Operation
 * @throws Exception exception
 */
public final void  testCallSingleInstanceGlobusServiceByMessageFactoryAndGetResourceProperty() throws Exception
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
    props.put(MuleProperties.MULE_METHOD_PROPERTY , "add");
    UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);

    assertNotNull(result);
    assertNotNull(result.getPayload());
    assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
    System.out.println(result.getPayload());
    System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));

    props.put(WSRFParameter.RESOURCE_KEY, result.getProperty(WSRFParameter.RESOURCE_KEY));
    
    //Add WsResourceProperty  property
    
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_PROPERTY_OPERATION"));
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_NAME, MessagesTest.getString("RESOURCE_PROPERTY_NAME"));
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_NS , MessagesTest.getString("RESOURCE_PROPERTY_NS"));
    
    
    
    result = client.send("vm://vmQueue", new Integer(2000) , props);
    
    assertNotNull(result);
    assertNotNull(result.getPayload());
    assertNotNull(result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
    
    Logger.getLogger(this.getClass()).log(Level.INFO,
        this.getClass().getName() + " : " + "Result from GetResourceProperty: " + MessagesTest.getString("RESOURCE_PROPERTY_NAME")  + "=  " + result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
   
        
    }


/*
 * Test a single Call invocation method of Globus Grid Service using a Mule Message  with ResourceKey created from factory service and perform a GetResourceProperty Operation in standalone mode without Axis method invocation
 * @throws Exception exception
 * TODO WSRF-24: error in standalone mode
 */
/*public final void  testCallSingleInstanceGlobusServiceByMessageFactoryAndGetResourcePropertyStandaloneMode() throws Exception
    {
   MuleClient client = new MuleClient();


   Map props = new HashMap();
    props.put("style", "wrapped");
    props.put("use", "literal");
    //props.put(MuleProperties.MULE_SOAP_METHOD, method);

    //props.put(WSRFParameter.SERVICE_NAMESPACE, Messages.getString("RESOURCE_KEY"));
    props.put(WSRFParameter.SERVICE_NAMESPACE, MessagesTest.getString("SERVICE_NAMESPACE_URI"));
    props.put(WSRFParameter.RESOURCE_KEY_NAME, MessagesTest.getString("RESOURCE_KEY_NAME"));
    props.put(WSRFParameter.RETURN_QNAME, MessagesTest.getString("RETURN_QNAME"));
    props.put(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(
        MessagesTest.getString("SERVICE_NAMESPACE_URI"), MessagesTest.getString("RETURN_QTYPE_NAME")));
    //props.put(WSRFParameter.RETURN_CLASS, Class.forName(MessagesTest.getString("RETURN_CLASSNAME")));
    //props.put(WSRFParameter.SOAP_ACTION_URI, MessagesTest.getString("SOAP_ACTION_URI"));

    //factory properties
    props.put(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS, MessagesTest.getString("FACTORY_SERVICE_ADDRESS"));

    
    //Add WsResourceProperty  property
    
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_PROPERTY_OPERATION"));
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_NAME, MessagesTest.getString("RESOURCE_PROPERTY_NAME"));
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_NS , MessagesTest.getString("RESOURCE_PROPERTY_NS"));
    
    
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_STANDALONE_MODE, "yes");
    UMOMessage result = client.send("vm://vmQueue", new Object[] {null} , props);

    assertNotNull(result);
    assertNotNull(result.getPayload());
    assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
    assertNotNull(result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
    System.out.println(result.getPayload());
    System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));
    
    Logger.getLogger(this.getClass()).log(Level.INFO,
        this.getClass().getName() + " : " +"Result from GetResourceProperty: " + MessagesTest.getString("RESOURCE_PROPERTY_NAME")  + "=  " + result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
   
        
    }*
    
    
    
    /**
 * Test a single Call invocation method of Globus Grid Service using a Mule Message  with ResourceKey created from factory service and perform on sequence : an Update, Delete , Insert of SetResourceProperty Operation
 * Test on String Resource Property type
 * @throws Exception exception
 */
public final void  testCallSingleInstanceGlobusServiceByMessageFactoryAndSetResourcePropertyUpdateDeleteInsert() throws Exception
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
    props.put(MuleProperties.MULE_METHOD_PROPERTY , "add");
    UMOMessage result = client.send("vm://vmQueue", new Integer(2), props);

    assertNotNull(result);
    assertNotNull(result.getPayload());
    assertNotNull(result.getProperty(WSRFParameter.RESOURCE_KEY));
    System.out.println(result.getPayload());
    System.out.println("New resource Key: " + result.getProperty(WSRFParameter.RESOURCE_KEY));

   props.put(WSRFParameter.RESOURCE_KEY, result.getProperty(WSRFParameter.RESOURCE_KEY));
    //props.put(WSRFParameter.RESOURCE_KEY, "3559661");
    //Add WsResourceProperty  property
    
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_SET_PROPERTY_OPERATION"));
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_NAME, MessagesTest.getString("RESOURCE_PROPERTY_NAME_TO_SET"));
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_NS , MessagesTest.getString("RESOURCE_PROPERTY_NS"));
    props.put(WSRFParameter.RP_SET_OPERATION_TYPE ,  "update");
    props.put(WSRFParameter.RESOURCE_SET_PROPERTY_VALUE , MessagesTest.getString("RESOURCE_PROPERTY_NEW_VALUE"));
    //TODO raffaele.picardi: test with a MessageElement[] object
    result = client.send("vm://vmQueue", new Integer(2000) , props);
    
    assertNotNull(result);
    assertNotNull(result.getPayload());
    assertNotNull(result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    

    Logger.getLogger(this.getClass()).log(Level.INFO,
        this.getClass().getName() + " : " + "Result from Update SetResourceProperty: " + MessagesTest.getString("RESOURCE_PROPERTY_NAME_TO_SET")  + "=  " + result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
    assertTrue(((Boolean)result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE)).booleanValue());

    //Test GetResourceProperty after set
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_PROPERTY_OPERATION"));
    UMOMessage resultAfterSet = client.send("vm://vmQueue", new Integer(2000) , props);
    
    
    assertNotNull(resultAfterSet);
    assertNotNull(resultAfterSet.getPayload());
    assertNotNull(resultAfterSet.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
    Logger.getLogger(this.getClass()).log(Level.INFO,
        this.getClass().getName() + " : " + "Result from GetResourceProperty: " + MessagesTest.getString("RESOURCE_PROPERTY_NAME_TO_SET")  + "=  " + result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
   // TODO raffaele.picardi: add assertTrue on new value of resource property
        MessageElement resp = ((MessageElement[]) resultAfterSet.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE))[0];
  
    assertEquals(resp.getChildren().get(0).toString(), MessagesTest.getString("RESOURCE_PROPERTY_NEW_VALUE"));
   
 //Now try to delete property:
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_SET_PROPERTY_OPERATION"));
    props.put(WSRFParameter.RP_SET_OPERATION_TYPE ,  "delete");
    UMOMessage resultOfDelete = client.send("vm://vmQueue", new Integer(2000) , props);  
    
    assertNotNull(resultOfDelete);
    assertNotNull(resultOfDelete.getPayload());
    assertNotNull(resultOfDelete.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
    assertTrue(((Boolean) resultOfDelete.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE)).booleanValue());

    Logger.getLogger(this.getClass()).log(Level.INFO,
        this.getClass().getName() + " : " + "Result from Delete SetResourceProperty: " + MessagesTest.getString("RESOURCE_PROPERTY_NAME_TO_SET")  + "=  " + result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
 //check that property is deleted
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_PROPERTY_OPERATION"));
    UMOMessage resultAfterDelete = client.send("vm://vmQueue", new Integer(2000) , props);
    
    
    assertNotNull(resultAfterDelete);
    assertNotNull(resultAfterDelete.getPayload());
 
    
 //Now try to reinsert property
    
    props.put(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION, MessagesTest.getString("RESOURCE_SET_PROPERTY_OPERATION"));
    props.put(WSRFParameter.RP_SET_OPERATION_TYPE ,  "insert");
    UMOMessage resultOfInsert = client.send("vm://vmQueue", new Integer(2000) , props);  
    
    assertNotNull(resultOfInsert);
    assertNotNull(resultOfInsert.getPayload());
    assertNotNull(resultOfInsert.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
    
    assertTrue(((Boolean) resultOfInsert.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE)).booleanValue());

    Logger.getLogger(this.getClass()).log(Level.INFO,
        this.getClass().getName() + " : " + "Result from Insert SetResourceProperty: " + MessagesTest.getString("RESOURCE_PROPERTY_NAME_TO_SET")  + "=  " + result.getProperty(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE));
  //Now try to
    
        
    }



}

