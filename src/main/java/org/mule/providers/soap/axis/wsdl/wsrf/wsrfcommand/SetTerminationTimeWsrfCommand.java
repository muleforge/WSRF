/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.wsrfcommand;

import org.mule.config.MuleProperties;
import org.mule.providers.soap.SoapMethod;
import org.mule.providers.soap.axis.wsdl.AxisWsdlConnector;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRFException;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortType;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;
import org.mule.providers.soap.wsdl.wsrf.instance.GenericServiceAddressingLocator;
import org.mule.providers.soap.wsdl.wsrf.instance.RPMessages;
import org.mule.umo.endpoint.UMOEndpointURI;

import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;


import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.oasis.wsrf.lifetime.Destroy;
import org.oasis.wsrf.lifetime.SetTerminationTime;
import org.oasis.wsrf.lifetime.SetTerminationTimeResponse;

/**
 * Command Object for SetTerminationTime operation
 */
public class SetTerminationTimeWsrfCommand extends AbstractWsrfCommand 
{

    /**
     * Handle execute method
     * @throws WSRFException wsrf exception
     */
    protected void handleExecute() throws WSRFException
    {  
     SetTerminationTimeResponse response = null;
    GenericPortTypeSoapBindingsStub stub = null;
    String operationLT = WSRFParameter.WS_LT_SET_TERMINATION_TIME_OPERATION.toString(); 
    //operationRP defined

    

    String resourceKey =  (String) event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY);
    if (resourceKey == null && call != null) 
    {
        
        Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " Skipped WS-RP operation : no resourceKey  property  defined  " + WSRFParameter.RESOURCE_KEY);
        throw new WSRFException(RPMessages.getString(WSRFParameter.RESOURCE_KEY_NOT_FOUND));
    }
    int seconds = 0;
    String secondsStr =  (String) event.getMessage().getProperty(WSRFParameter.WS_LT_SCHEDULED_SECONDS_TIME);
    if (secondsStr == null && call != null) 
    {
        
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : " + " set 0 to deafault value of not specified property:  " + WSRFParameter.WS_LT_SCHEDULED_SECONDS_TIME);
        
      
    }
    else 
    {
        try
        {
            seconds = Integer.parseInt(secondsStr);
        }
        catch (Exception e) 
        {
            Logger.getLogger(this.getClass()).log(Level.ERROR, this.getClass().getName() + " : " + " invalid specified property:  " + WSRFParameter.WS_LT_SCHEDULED_SECONDS_TIME);
            throw new WSRFException(RPMessages.getString(WSRFParameter.WS_LT_SCHEDULED_SECONDS_TIME_NOT_VALID));
            
        }
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
                this.getClass().getName() + " : " + " Error during port type port loading. WS-LT operation skipped");
            e.printStackTrace();
            return;
        }
        
        SetTerminationTime setTerminationTimeRequest = new SetTerminationTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        setTerminationTimeRequest.setRequestedTerminationTime(calendar);
        //TODO WSRF-24: standalone mode to fix
        if (isStandalone)
        {
            event.getMessage().setProperty(MuleProperties.MULE_METHOD_PROPERTY , operationLT);
            if (call == null)
            {
                //call is not created yet.: advice can add property to event message . so next time , when call is created before method continues perform its operations
                Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : " + " Pre  WS-LT operation : add required initial properties message");
              
                if (operationLT.equals(WSRFParameter.WS_LT_SET_TERMINATION_TIME_OPERATION))
                {
                    if (!(event.getMessage().getPayload() instanceof Object[]))
                    {
                        Logger.getLogger(this.getClass())
                            .log(
                                Level.ERROR,
                                this.getClass().getName()
                                                + " : "
                                                + " Pre  WS-LT  operation :  It is not possible to set SetTerminationTime request  in Object[0] payload array. Paylod is not istance of Object[] ");
                        return;
                    }
                   
                    //TODO raffaele.picardi: payload of message needs to be Object[]  {} of 1 - size
                    //TODO raffaele.picardi:test standalone mode
                    ((Object[]) (event.getMessage().getPayload()))[0] = setTerminationTimeRequest;
                }
                
                else
                {
                    Logger.getLogger(this.getClass())
                        .log(
                            Level.ERROR,
                            this.getClass().getName()
                                            + " : "
                                            + " Pre  WS-LT  operation :  It is not possible to fine this WS-LT operation:"
                                            + operationLT);
                    return;
                }
            }
            else
            { //call != null
                // enrich message
                setSoapMethod();
                setOperation(service);
 
            }
            return;
        }
   
        
        response = service.setTerminationTime(setTerminationTimeRequest , event , call);
        
        

        if (isStandalone && response == null)
        {
            Logger.getLogger(this.getClass()).log(
                Level.DEBUG,
                this.getClass().getName() + " : " + " SetTerminationTime in standalone mode:  "  + " response invocation will be add in payload message of wsdl provider invocation");
            
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

       
    this.wsrfExtraResponseMap.put(WSRFParameter.WSRF_LIFETIME_RESPONSE, new Boolean(true));
    Logger.getLogger(this.getClass()).log(
        Level.INFO,
        this.getClass().getName() + " : " + " WS-LT operation successfull. Response added in    "
                        + WSRFParameter.WSRF_EXTRA_RESPONSE_MAP + "  map as [key = "
                        + WSRFParameter.WSRF_LIFETIME_RESPONSE + " , value= "  + "true");

    }
    
    /**
     * getWsrfErrorReponse
     * @return string error
     */
        protected String getWsrfErrorReponse()
        {
           return WSRFParameter.WSRF_LT_ERROR_RESPONSE;
        }
        
        
        
        /**
         * Sets the operation.
         * 
         * @param service the new operation
         */
        private void setOperation(GenericPortType service)
        {
            call.setOperation(service.getOperation(GenericPortType.SET_TERMINATION_TIME_OPERATION));
        }



        /**
         * Sets the soap method.
         */
        private void setSoapMethod() 
        {
            
           SoapMethod method = new SoapMethod(new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd", "SetTerminationTime"));
            String serviceNamespaceURI =     (String) event.getMessage().getProperty(WSRFParameter.SERVICE_NAMESPACE);
                
                method.addNamedParameter(
                    new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd" , "SetTerminationTime"),
                    new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "QName"),
                    "in"
                    );
                method.setReturnType(new javax.xml.namespace.QName(
                    "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd",
                ">SetTerminationTimeResponse"));
             
            
                    method.setReturnClass(org.oasis.wsrf.lifetime.SetTerminationTimeResponse.class);
                    
                    event.getMessage().setProperty("style", "wrapped");
                    event.getMessage().setProperty("use", "literal");
                    event.getMessage().setProperty(MuleProperties.MULE_SOAP_METHOD, method);
                      
                    event.getMessage().setProperty(WSRFParameter.RETURN_QNAME, "SetTerminationTimeResponse" );
                    event.getMessage().setProperty(WSRFParameter.RETURN_QTYPE, new javax.xml.namespace.QName(serviceNamespaceURI, ">SetTerminationTimeResponse"));
            
                    call.removeAllParameters();
                    call.addParameter(new javax.xml.namespace.QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceLifetime-1.2-draft-01.xsd" , "SetTerminationTime"),
                    new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "QName"),
                    ParameterMode.IN);
                  
                
        }

        

}


