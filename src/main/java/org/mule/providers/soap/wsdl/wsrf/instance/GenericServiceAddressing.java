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
 * The Interface GenericServiceAddressing.
 */
public interface GenericServiceAddressing extends GenericService
{

    /**
     * Gets the math port type port.
     * 
     * @param reference the reference
     * @return the math port type port
     */
    GenericPortType getPortTypePort(org.apache.axis.message.addressing.EndpointReferenceType reference)
        throws javax.xml.rpc.ServiceException;

}
