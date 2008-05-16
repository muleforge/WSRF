/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.wsdl.wsrf.instance;

import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.umo.UMOEvent;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.message.addressing.AddressingHeaders;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.message.addressing.ReferencePropertiesType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.SerializationException;
import org.globus.wsrf.impl.SimpleResourceKey;

/**
 * The Class GenericServiceAddressingLocator.
 */
public class GenericServiceAddressingLocator extends GenericServiceLocator
    implements GenericServiceAddressing
{
    
    public GenericServiceAddressingLocator(EngineConfiguration config)
    {
        super(config);
        
    }
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Gets the math port type port.
     * 
     * @param reference the reference
     * @return the math port type port
     */
    public GenericPortType getPortTypePort(org.apache.axis.message.addressing.EndpointReferenceType reference , UMOEvent event)
        throws javax.xml.rpc.ServiceException
    {
        org.apache.axis.message.addressing.AttributedURI address = reference.getAddress();
        if (address == null)
        {
            throw new javax.xml.rpc.ServiceException("No address in EndpointReference");
        }
        java.net.URL endpoint;
        try
        {
            endpoint = new java.net.URL(address.toString());
        }
        catch (java.net.MalformedURLException e)
        {
            throw new javax.xml.rpc.ServiceException(e);
        }
        GenericPortType stub = getPortTypePort(endpoint);
        if (stub != null)
        {
            org.apache.axis.message.addressing.AddressingHeaders headers = new org.apache.axis.message.addressing.AddressingHeaders();
            headers.setTo(address);
            String serviceNamespace = (String) event.getMessage().getProperty(WSRFParameter.SERVICE_NAMESPACE);
            String resourceKeyName =  (String) event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY_NAME);
            QName keyName = new QName(serviceNamespace , resourceKeyName);
            String keyValue = (String) event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY);
            SimpleResourceKey key = new SimpleResourceKey(keyName, keyValue);
            
            ReferencePropertiesType props = headers.getReferenceProperties();
            if (props == null ) 
            {
                props = new ReferencePropertiesType();
            }
            try
            {
                if (keyValue ==  null )
                {
                    Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : ReferencesProperties IGNORED . Resource key not found..");
                }
                else 
                {
                props.add(key.toSOAPElement());
                }
            }
            catch (SerializationException e)
            {
                e.printStackTrace();
            } 
            Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : ReferencesProperties injected..");
            headers.setReferenceProperties(props);
            
            ((javax.xml.rpc.Stub) stub)._setProperty(
                org.apache.axis.message.addressing.Constants.ENV_ADDRESSING_SHARED_HEADERS, headers);
        }
        return stub;
        
    }
/**
 * GenericPortType
 * @param EndpointReferenceType reference
 * @return GenericPortType
 */
    public GenericPortType getPortTypePort(EndpointReferenceType reference) throws ServiceException
    {

        return getPortTypePort(reference);
    }
    
   
}
