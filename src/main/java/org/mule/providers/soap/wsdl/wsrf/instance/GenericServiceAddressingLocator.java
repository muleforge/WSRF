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

/**
 * The Class GenericServiceAddressingLocator.
 */
public class GenericServiceAddressingLocator extends GenericServiceLocator
    implements GenericServiceAddressing
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Gets the math port type port.
     * 
     * @param reference the reference
     * @return the math port type port
     * @see org.mule.providers.soap.wsdl.wsrf.instance.GenericServiceAddressing#getMathPortTypePort(org.apache.axis.message.addressing.EndpointReferenceType)
     */
    public GenericPortType getMathPortTypePort(org.apache.axis.message.addressing.EndpointReferenceType reference)
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
        GenericPortType stub = getMathPortTypePort(endpoint);
        if (stub != null)
        {
            org.apache.axis.message.addressing.AddressingHeaders headers = new org.apache.axis.message.addressing.AddressingHeaders();
            headers.setTo(address);
            headers.setReferenceProperties(reference.getProperties());
            ((javax.xml.rpc.Stub) stub)._setProperty(
                org.apache.axis.message.addressing.Constants.ENV_ADDRESSING_SHARED_HEADERS, headers);
        }
        return stub;
    }
}
