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





import org.mule.providers.soap.axis.wsdl.AxisWsdlMessageDispatcher;
import org.mule.umo.UMOEvent;
import org.mule.umo.UMOMessage;
import org.mule.umo.endpoint.UMOImmutableEndpoint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;

/**
 * Creates and Axis client services from WSDL and invokes it.
 * @author raffaele.picardi
 */
public class AxisWsdlWsrfMessageDispatcher extends AxisWsdlMessageDispatcher
{
    
/**
 * 
 * extenderCall to use AOP Spring Framework in order to manage WSRF SOAP Extension
 */
private ExtenderCall extenderCall = null;


 /**
 * 
 * @param endpoint endpoint
 */
public AxisWsdlWsrfMessageDispatcher(UMOImmutableEndpoint endpoint)
{
  super(endpoint);
  //TODO MULE-WSRF-5: Using SPRING AOP 
  BeanFactory factory = null;
try
{
    factory = new XmlBeanFactory(new FileInputStream("application.xml"));
   
}
catch (BeansException e)
{
    // TODO Auto-generated catch block
    e.printStackTrace();
}
catch (FileNotFoundException e)
{
    // TODO Auto-generated catch block
    e.printStackTrace();
}
  extenderCall = (ExtenderCall) factory.getBean("extendCallTarget");
  
}
/**
 * @param event event
 * @throws Exception exception
 * @return UMOMessage
 */
protected UMOMessage doSend(UMOEvent event) throws Exception
{
this.extenderCall.extendCall(null, event);
return super.doSend(event);
}

}
