/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrfcommand;

import org.mule.config.MuleProperties;
import org.mule.providers.soap.axis.wsdl.AxisWsdlConnector;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.Messages;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFException;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFOperationException;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortType;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericServiceAddressingLocator;
import org.mule.providers.soap.wsdl.wsrf.instance.RPMessages;
import org.mule.umo.endpoint.UMOEndpointURI;

import javax.xml.namespace.QName;

import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;

/**
 * Command Object for GetResourceProperty operation
 */
public class GetResourcePropertyWsrfCommand extends AbstractWsrfCommand 
{

    /**
     * Handle execute method
     * @throws WSRFException wsrf exception
     */
    protected void handleExecute() throws WSRFException
    {
        GetResourcePropertyResponse response = null;
        GenericPortTypeSoapBindingsStub stub = null;
        String operationRP = WSRFParameter.GET_RESOURCE_PROPERTY.toString(); 
        //operationRP defined
        String propertyName =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_NAME);
        if (propertyName == null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no resource property name defined  " + WSRFParameter.WSRF_RESOURCEPROPERTY_NAME);
            throw new WSRFException(RPMessages.getString(WSRFParameter.RESOURCE_PROPERTY_NAME_NOT_FOUND));
          
        }
        //name RP defined
        String nsProperty =  (String) event.getMessage().getProperty(WSRFParameter.WSRF_RESOURCEPROPERTY_NS);
        if (nsProperty == null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no namespace resource property  defined  " + WSRFParameter.WSRF_RESOURCEPROPERTY_NS);
            throw new WSRFException(RPMessages.getString(WSRFParameter.RESOURCE_PROPERTY_NS_NOT_FOUND));
            
        }
        //namespace RP defined
        
    
        String resourceKey =  (String) event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY);
        if (resourceKey == null && call != null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no resourceKey  property  defined  " + WSRFParameter.RESOURCE_KEY);
            throw new WSRFException(RPMessages.getString(WSRFParameter.RESOURCE_KEY_NOT_FOUND));
        }
      
        
        try
        {
            UMOEndpointURI endpointUri = event.getEndpoint().getEndpointURI();
            String endPointURI = endpointUri.getAddress();
            int indexOfInitOfParameter =  endPointURI.indexOf("?");
            if (indexOfInitOfParameter != -1)
            {  
            endPointURI = endPointURI.substring(0, indexOfInitOfParameter);
            }
            

          
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
                    this.getClass().getName() + " : " + " Error during port type port loading. WS-RP operation skipped");
                e.printStackTrace();
                return;
            }
            
            if (isStandalone)
            {
                event.getMessage().setProperty(MuleProperties.MULE_METHOD_PROPERTY , operationRP);
                if (call == null)
                {
                    //call is not created yet.: advice can add property to event message . so next time , when call is created before method continues perform its operations
                    Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Pre  WS-RP operation : add required initial properties message");
                  
                    if (operationRP.equals(WSRFParameter.GET_RESOURCE_PROPERTY))
                    {
                        if (!(event.getMessage().getPayload() instanceof Object[]))
                        {
                            Logger.getLogger(this.getClass())
                                .log(
                                    Level.ERROR,
                                    this.getClass().getName()
                                                    + " : "
                                                    + " Pre  WS-RP operation :  It is not possible to set getResourcePropertyRequest in Object[0] payload array. Paylod is not istance of Object[] ");
                            return;
                        }
                        // enrich message
                        service.setSoapMethod(event);
                        //TODO raffaele.picardi: payload of message needs to be Object[]  {} of 1 - size
                        ((Object[]) (event.getMessage().getPayload()))[0] = new QName(nsProperty,
                            propertyName);
                    }
                    else
                    {
                        Logger.getLogger(this.getClass())
                            .log(
                                Level.ERROR,
                                this.getClass().getName()
                                                + " : "
                                                + " Pre  WS-RP operation :  It is not possible to fine this WS-RP operation:"
                                                + operationRP);
                        return;
                    }
                }
                return;
            }
       
            
            response = service.getResourceProperty(new QName(nsProperty, propertyName) , event , call);
            
            

            if (isStandalone && response == null)
            {
                Logger.getLogger(this.getClass()).log(
                    Level.DEBUG,
                    this.getClass().getName() + " : " + " Getting Resource Property in standalone mode:  "  + " response invocation will be add in payload message of wsdl provider invocation");
                
                return;
            }
        }
        catch (Exception e)
        {
            Logger.getLogger(this.getClass()).log(
                Level.ERROR,
                this.getClass().getName() + " : " + " response null");
       
            throw new WSRFException(e.getMessage());
            
        }

           
        this.wsrfExtraResponseMap.put(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE, response.get_any());
        Logger.getLogger(this.getClass()).log(
            Level.INFO,
            this.getClass().getName() + " : " + " WS-RP operation successfull. Response added in    "
                            + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + "  map as [key = "
                            + WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE + " , value= "
                            + response.get_any().toString());

        
    }

}


