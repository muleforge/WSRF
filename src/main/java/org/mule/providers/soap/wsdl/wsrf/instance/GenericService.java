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
 * The Interface GenericService.
 */
public interface GenericService extends javax.xml.rpc.Service
{

    /**
     * Gets the math port type port address.
     * 
     * @return the math port type port address
     */
    java.lang.String getPortTypePortAddress();

    /**
     * Gets the math port type port.
     * 
     * @return the math port type port
     */
    GenericPortType getPortTypePort() throws javax.xml.rpc.ServiceException;

    /**
     * Gets the math port type port.
     * 
     * @param portAddress the port address
     * @return the math port type port
     */
    GenericPortType getPortTypePort(java.net.URL portAddress)
        throws javax.xml.rpc.ServiceException;
}
