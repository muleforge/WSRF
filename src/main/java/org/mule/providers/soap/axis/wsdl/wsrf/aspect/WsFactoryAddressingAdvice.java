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







import org.mule.providers.soap.axis.wsdl.wsrf.BasePriorityAdvice;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryPortType;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryServiceAddressingLocator;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;

import org.mule.umo.UMOEvent;


import java.lang.reflect.Method;



import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;





import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.AddressingHeaders;
import org.apache.axis.message.addressing.Constants;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.message.addressing.ReferencePropertiesType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.SerializationException;
import org.globus.wsrf.impl.SimpleResourceKey;

import org.springframework.aop.MethodBeforeAdvice;


// TODO: Auto-generated Javadoc
/**
 * WsAddressing Aspect to inject Ws-Addressing information in SOAP - Header and/or
 * manage WSDL Stub.
 */
public class WsFactoryAddressingAdvice extends BasePriorityAdvice implements MethodBeforeAdvice
{
    


    

    /**
     * Default Constructor.
     */
    public WsFactoryAddressingAdvice()
    {
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");
    }
    
    /**
     * Inject call informations.
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        
       // Get params
        

       UMOEvent event = (UMOEvent) arg1[1];
       String factoryServiceURI =null;
       
           try
        {
            factoryServiceURI = (String) event.getMessage().getProperty(WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS);
               if (factoryServiceURI == null) 
               {
                   Logger.getLogger(this.getClass()).log(
                       Level.DEBUG,
                       this.getClass().getName() + " : " + WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS
                                       + " property is null. IGNORED Factory Resource creation process");
                   
                   return;
               }
                 
        }
        catch (Exception e)
        {
            Logger.getLogger(this.getClass()).log(
                Level.ERROR,
                this.getClass().getName() + " : " + " ERROR in getting Factory Service URI params: " + e.getMessage());
           e.printStackTrace();
        }
       

 
       if (event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY) != null)
       {
           Logger.getLogger(this.getClass()).log(
               Level.DEBUG,
               this.getClass().getName() + " : " + " ResourceKey creation IGNORED! Just included in Mule Message");
           return;
       }
        
        event.getMessage().setProperty(WSRFParameter.RESOURCE_KEY, createResource(factoryServiceURI));
        //TODO raffaele.picardi: if resourceKey just included in session ?
    }

    /**
     * Creates the resource.
     * 
     * @param factoryServiceURI the factory service uri
     */
    private String createResource(String factoryServiceURI)
    {
       
        FactoryServiceAddressingLocator factoryLocator = new FactoryServiceAddressingLocator();
       
        String resourceKey = null ; 
        try 
        {
          
            EndpointReferenceType factoryEPR;
            FactoryPortType factory;
         
            factoryEPR = new EndpointReferenceType();
            factoryEPR.setAddress(new Address(factoryServiceURI));
            factory = factoryLocator.getFactoryPortTypePort(factoryEPR);

          
            // TODO raffaele.picardi:1 invoke factory grid service using generic stub 
          
            
            // TODO raffaele.picardi: implements create resource operation with params in and out specificated from client message
            // TODO MULE-WSRF-22: Manage input create resource request param
            resourceKey =  factory.createResource(null);
            
            // From specification it needs to define a response object that contains , mapping during generation from wsdl2java ,
            // the  xsd type : <xsd:element ref="wsa:EndpointReference"/>
            // <xsd:import namespace="http://schemas.xmlsoap.org/ws/2004/03/addressing" schemaLocation="../../ws/addressing/WS-Addressing.xsd" />
          /*  
            instanceEPR = createResponse.getEndpointReference();
            
            createResponse = mathFactory
            .createResource(new CreateResource());
            createResponse = mathFactory
            .createResource(new CreateResource());
           
            assertNotNull(instanceEPR);

            Logger.getLogger(this.getClass()).info("instance EPR: " + instanceEPR  + '\n');
            
            GenericServiceAddressingLocator instanceLocator = new GenericServiceAddressingLocator();
            GenericPortType serviceInstance = instanceLocator.getMathPortTypePort(instanceEPR);
     
            String NS = "http://www.globus.org/namespaces/examples/core/MathService_instance";
            Logger.getLogger(this.getClass()).info("response of getResourceProperty(value) operation : " + serviceInstance.getResourceProperty(new QName(NS, "Value"))  + '\n');
*/        
            } 
        catch (Exception e) 
        {
            e.printStackTrace();
            
        }
        return resourceKey;
    }
    
 


}


