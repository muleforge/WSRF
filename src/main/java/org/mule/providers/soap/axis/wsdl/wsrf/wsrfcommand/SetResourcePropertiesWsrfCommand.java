/*
 * $Id: SetResourcePropertyWsrfCommand.java 329 2008-07-04 16:34:08Z raffaele.picardi $
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.wsrfcommand;

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
import org.mule.util.file.DeleteException;

import javax.xml.namespace.QName;

import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.oasis.wsrf.properties.DeleteType;
import org.oasis.wsrf.properties.GetResourcePropertyResponse;
import org.oasis.wsrf.properties.InsertType;
import org.oasis.wsrf.properties.SetResourcePropertiesResponse;
import org.oasis.wsrf.properties.SetResourceProperties_Element;
import org.oasis.wsrf.properties.UpdateType;


/**
 * Command Object for SetResourceProperty operation.
 * Command manage single SetResourceProperties operation type on message 
 */
public class SetResourcePropertiesWsrfCommand extends AbstractWsrfCommand 
{

    /**
     * Handle execute method.
     * 
     * @throws WSRFException wsrf exception
     */
    protected void handleExecute() throws WSRFException
    {
        SetResourcePropertiesResponse response = null;
        GenericPortTypeSoapBindingsStub stub = null;
        String operationRP = WSRFParameter.SET_RESOURCE_PROPERTY.toString(); 
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
      
        String setOperationType =  (String) event.getMessage().getProperty(WSRFParameter.RP_SET_OPERATION_TYPE);
        if (setOperationType == null && call != null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no set operation type  property  defined  " + WSRFParameter.RP_SET_OPERATION_TYPE);
            throw new WSRFException(RPMessages.getString(WSRFParameter.RP_SET_OPERATION_TYPE_MISSING));
        }
        
        Object propertyValue =  event.getMessage().getProperty(WSRFParameter.RESOURCE_SET_PROPERTY_VALUE);
        if (!setOperationType.equals(WSRFParameter.DELETE_SET_OPERATION) && propertyValue == null && call != null) 
        {
            
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no value for  property  defined  " + WSRFParameter.RESOURCE_SET_PROPERTY_VALUE);
            throw new WSRFException(RPMessages.getString(WSRFParameter.RP_SET_PROPERTY_VALUE_MISSING));
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
            //TODO WSRF-24: standalone mode to fix
            if (isStandalone)
            {
                event.getMessage().setProperty(MuleProperties.MULE_METHOD_PROPERTY , operationRP);
                if (call == null)
                {
                    //call is not created yet.: advice can add property to event message . so next time , when call is created before method continues perform its operations
                    Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Pre  WS-RP operation : add required initial properties message");
                  
                    if (operationRP.equals(WSRFParameter.SET_RESOURCE_PROPERTY))
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
                        //TODO raffaele.picardi: add property value in payload (SetResourceProperty operation)
                        ((Object[]) (event.getMessage().getPayload()))[0] = new QName(nsProperty,
                            propertyName );
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
            
            SetResourceProperties_Element request = createRequest (setOperationType , propertyName , nsProperty , propertyValue); 
            response = service.setResourceProperties(request, event, call);
            

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
       
            throw new WSRFException(e.getClass().getName()+":"+ e.getMessage() + " caused by:" + e.getCause());
            
        }

           
        this.wsrfExtraResponseMap.put(WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE, new Boolean(true));
        Logger.getLogger(this.getClass()).log(
            Level.INFO,
            this.getClass().getName() + " : " + " WS-RP operation successfull. Response added in    "
                            + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + "  map as [key = "
                            + WSRFParameter.WSRF_MESSAGE_ELEMENT_ARRAY_SOAP_RESPONSE + " , value= "
                            + "true");

        
    }
    
    /**
     * Creates the request.
     * 
     * @param setOperationType the set operation type
     * @param propertyName the property name
     * @param nsProperty the ns property
     * @param propertyValue the property value
     * @return the sets the resource properties_ element
     * @throws WSRFException  wsrf exception
     */
    private SetResourceProperties_Element createRequest (String setOperationType, String propertyName, String nsProperty, Object propertyValue) throws WSRFException 
    {
        SetResourceProperties_Element request = null;
        QName rpQName = new QName(nsProperty, propertyName);
        
        if (setOperationType.equals(WSRFParameter.UPDATE_SET_OPERATION))
        {
            UpdateType update = new UpdateType();
            
            if (propertyValue instanceof MessageElement[]) 
            {
            update.set_any((MessageElement[]) propertyValue);
            }
            else if (propertyValue instanceof MessageElement) 
            {
                update.set_any(new MessageElement[] {(MessageElement) propertyValue});
            }
            else 
            {
                //TODO raffaele.picardi:here propertyValue needs to be registered type and serializable
                MessageElement msg = new MessageElement(rpQName, propertyValue);
                update.set_any(new MessageElement[] {msg});
            }
            /* Create request object */
            request = new SetResourceProperties_Element();
            request.setUpdate(update);
        }
        else if (setOperationType.equals(WSRFParameter.DELETE_SET_OPERATION))
        {
            DeleteType deleteType = new DeleteType();
            deleteType.setResourceProperty(rpQName);
            request = new SetResourceProperties_Element();
            request.setDelete(deleteType);
                
        }
        else  if (setOperationType.equals(WSRFParameter.INSERT_SET_OPERATION))
        {
            InsertType insertType = new InsertType();
            
            if (propertyValue instanceof MessageElement[]) 
            {
                insertType.set_any((MessageElement[]) propertyValue);
            }
            else if (propertyValue instanceof MessageElement) 
            {
                insertType.set_any(new MessageElement[] {(MessageElement) propertyValue});
            }
            else 
            {
                //TODO raffaele.picardi:here propertyValue needs to be registered type and serializable
                MessageElement msg = new MessageElement(rpQName, propertyValue);
                insertType.set_any(new MessageElement[] {msg});
            }
            /* Create request object */
            request = new SetResourceProperties_Element();
            request.setInsert(insertType);
        }
        else
        {
           
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no valid operation  " + setOperationType);
            throw new WSRFException(RPMessages.getString(WSRFParameter.RP_SET_OPERATION_TYPE_MISSING));

        }
        return request;
        
    }

}


