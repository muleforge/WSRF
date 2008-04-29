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
 * The Interface FactoryPortType.
 */
public interface FactoryPortType extends java.rmi.Remote
{
    
    /**
     * Creates the resource.
     * 
     * @param request the request
     * @return the created resource key
     */
    String createResource(Object request) throws java.rmi.RemoteException;
}

