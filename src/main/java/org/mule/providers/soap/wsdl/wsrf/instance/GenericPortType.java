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
        
       
    
    
    
        /** The Constant DESTROY_OPERATION. */
       int DESTROY_OPERATION = 5;
        
        /** The Constant SET_TERMINATION_TIME_OPERATION. */
        int SET_TERMINATION_TIME_OPERATION = 6;

        /**
         * Gets the resource property.
         * 
         * @param getResourcePropertyRequest the get resource property request
         * @param event event
         * @param call call created from Axis Wsdl Wsrf Message Dispatcher
         * @return the resource property
         * @throws RemoteException the remote exception
         */
        org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest , UMOEvent event , Call call) throws java.rmi.RemoteException;


        /**
         * Set Resource Property.
         * 
         * @param setResourcePropertiesRequest the set resource properties request
         * @param event the event
         * @param justCreatedCall the just created call
         * @return the sets the resource properties response
         * @throws RemoteException the remote exception
         */
        org.oasis.wsrf.properties.SetResourcePropertiesResponse setResourceProperties(org.oasis.wsrf.properties.SetResourceProperties_Element setResourcePropertiesRequest ,  UMOEvent event , Call justCreatedCall)    throws java.rmi.RemoteException;
        
         /**
             * Sets the soap method.
             * 
             * @param event the new soap method
             */
        void setSoapMethod(UMOEvent event);

        
        /**
         * Destroy.
         * 
         * @param destroyRequest the destroy request
         * @param event the event
         * @param justCreatedCall the just created call
         * @return the org.oasis.wsrf.lifetime. destroy response
         * @throws RemoteException the remote exception
         */
        org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy destroyRequest ,  UMOEvent event , Call justCreatedCall) throws java.rmi.RemoteException ; 
        
        /**
         * Sets the termination time.
         * 
         * @param setTerminationTimeRequest the set termination time request
         * @param event the event
         * @param justCreatedCall the just created call
         * @return the org.oasis.wsrf.lifetime. set termination time response
         * @throws RemoteException the remote exception
         */
        org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime setTerminationTimeRequest  ,  UMOEvent event , Call justCreatedCall) throws java.rmi.RemoteException;
 
        /**
         * Gets the operation.
         * 
         * @param operation the operation . use constant as
         *            GenericPortType.DESTROY_OPERATION
         * @return the operation operation
         */
        org.apache.axis.description.OperationDesc getOperation(int operation);
}



