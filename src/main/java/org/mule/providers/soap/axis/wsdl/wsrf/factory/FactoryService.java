/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.factory;


/**
 * The Interface FactoryService.
 */
public interface FactoryService extends javax.xml.rpc.Service
{
    
    /**
     * Gets the factory port type port address.
     * 
     * @return the factory port type port address
     */
    java.lang.String getFactoryPortTypePortAddress();

    /**
     * Gets the factory port type port.
     * 
     * @return the factory port type port
     */
    FactoryPortType getFactoryPortTypePort() throws javax.xml.rpc.ServiceException;

    /**
     * Gets the factory port type port.
     * 
     * @param portAddress the port address
     * @return the factory port type port
     */
    FactoryPortType getFactoryPortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}



