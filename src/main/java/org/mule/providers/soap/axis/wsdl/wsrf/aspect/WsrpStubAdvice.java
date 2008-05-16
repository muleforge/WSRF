/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.aspect;


import org.mule.providers.soap.axis.wsdl.AxisWsdlConnector;
import org.mule.providers.soap.axis.wsdl.wsrf.StubPriorityAdvice;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryPortType;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryServiceAddressingLocator;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortType;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericServiceAddressingLocator;
import org.mule.umo.UMOEvent;
import org.mule.umo.endpoint.UMOEndpointURI;
import org.mule.util.BeanUtils;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.EngineConfigurationFactory;
import org.apache.axis.client.Call;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.springframework.aop.MethodBeforeAdvice;
/**
 * This Stub Advice perform Wsrp request using WSDL2JAVA stub
 */
public class WsrpStubAdvice extends StubPriorityAdvice implements MethodBeforeAdvice
{
   


    /**
    * Advice perform WS-RP operation . and append its response as string property WSRF_XML_GETSET_SOAP_RESPONSE in WSRFExtraResponse map in Event Message source 
    * @param arg0 method name
    * @param arg1 args
    * @param arg2 target object
    * @throws Throwable exception
    */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {

        // TODO raffaele.picardi: implement advice Wsrp
        
        UMOEvent event = (UMOEvent) arg1[1];
        Call call = (Call) arg1[0];
        GetResourcePropertyResponse response = null;
        GenericPortTypeSoapBindingsStub stub = null;
        String operationRP =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION);
        
        if  (!(operationRP != null && (operationRP.equals(WSRFParameter.GET_RESOURCE_PROPERTY) || !operationRP.equals(WSRFParameter.SET_RESOURCE_PROPERTY)))) 
        {
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Skipped WS-RP operation : no operation defined " + WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION);
            return;
        }
        //operationRP defined
        String propertyName =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_NAME);
        if (propertyName == null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Skipped WS-RP operation : no resource property name defined  " + WSRFParameter.WSRF_RESOURCEPROPERTY_NAME);
            return; 
            
        }
        //name RP defined
        String nsProperty =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_NS);
        if (nsProperty == null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Skipped WS-RP operation : no namespace resource property  defined  " + WSRFParameter.WSRF_RESOURCEPROPERTY_NS);
            return; 
            
        }
        //namespace RP defined
        
        
        
        try
        {
            UMOEndpointURI endpointUri = event.getEndpoint().getEndpointURI();
            String endPointURI = endpointUri.getAddress();
            int indexOfInitOfParameter =  endPointURI.indexOf("?");
            endPointURI = endPointURI.substring(0, indexOfInitOfParameter);

  
            //TODO raffaele.picardi: check if event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY_NAME) does not exist

            GenericServiceAddressingLocator serviceLocator = new GenericServiceAddressingLocator(new FileProvider(AxisWsdlConnector.DEFAULT_MULE_AXIS_CLIENT_CONFIG));
            EndpointReferenceType serviceEPR;
            GenericPortType service = null;
            try
            {
         
                serviceEPR = new EndpointReferenceType();
                serviceEPR.setAddress(new Address(endPointURI));
                
                service = serviceLocator.getPortTypePort(serviceEPR , event);
              
                      Logger.getLogger(this.getClass()).log(Level.DEBUG,
                    this.getClass().getName() + " : " + " service port type port load.");
            }
            catch (Exception e)
            {
                Logger.getLogger(this.getClass()).log(Level.ERROR,
                    this.getClass().getName() + " : " + " Error during port type port loading. WS-RP operation skipped ");
                e.printStackTrace();
                return;
            }
       
            
            //TODO raffaele.picardi: TOP set service URI no endpoint exception
            response = service.getResourceProperty(new QName(nsProperty, propertyName));
        }
        catch (Exception e)
        {

            Logger.getLogger(this.getClass()).log(
                Level.ERROR,
                this.getClass().getName() + " : " + " ERROR in getting Resourse Property: "
                                + e.getMessage());
            
            e.printStackTrace();
            return;
        }

        
        Map map = (Map) event.getMessage().getProperty(WSRFParameter.WSRF_EXTRA_RESPONSE_MAP);
        if (map == null) 
        {
            map = new HashMap();
            Logger.getLogger(this.getClass()).log(
                Level.INFO,
                this.getClass().getName() + " : " + " Map of :"  
                                + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + "  created.");
        }
     
        map.put(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE, response.get_any());
        Logger.getLogger(this.getClass()).log(
            Level.INFO,
            this.getClass().getName() + " : " + " WS-RP operation successfull. Response added in    "
                            + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + "  map as [key = "
                            + WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE + " , value= "
                            + response.get_any().toString());

   
    }

}


