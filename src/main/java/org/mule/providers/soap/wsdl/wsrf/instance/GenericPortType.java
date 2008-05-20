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

import org.mule.umo.UMOEvent;

import org.apache.axis.client.Call;

/**
 * The Interface GenenericPortType.
 */
public interface GenericPortType extends java.rmi.Remote
{
        
       
    
          
              
        /**
         * Gets the resource property.
         * 
         * @param getResourcePropertyRequest the get resource property request
         * @param event event
         * @param call call created from Axis Wsdl Wsrf Message Dispatcher
         * @return the resource property
         */
        org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest ,UMOEvent event, Call call) throws java.rmi.RemoteException;
        //TODO raffaele.picardi: add other standard WSRF operations as setResourceProperty or setMultipleResourceProperty
        
        
}


