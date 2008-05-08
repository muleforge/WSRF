/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.aspect;

import org.mule.providers.soap.axis.wsdl.wsrf.BasePriorityAdvice;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryPortType;
import org.mule.providers.soap.axis.wsdl.wsrf.factory.FactoryServiceAddressingLocator;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;

import org.mule.umo.UMOEvent;
import org.mule.umo.UMOMessage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.message.addressing.Address;

import org.apache.axis.message.addressing.EndpointReferenceType;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.springframework.aop.MethodBeforeAdvice;


/**
 * WsFactoryAddressing Aspect to perform Factory create resource request in order to
 * obtain and set in mule message resource Key.
 */
public class WsFactoryAddressingAdvice extends BasePriorityAdvice implements MethodBeforeAdvice
{
    /**
     * Map message correlationID and Service with ResourceKey in order to retrieve
     * resource key when session id is not persisted across Mule Events.
     */
    private Map correlationIDAndServiceToResourceKey;

    /**
     * Default Constructor.
     */
    public WsFactoryAddressingAdvice()
    {
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");
        correlationIDAndServiceToResourceKey = new HashMap();
    }

    /**
     * Inject call informations.
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
        UMOEvent event = (UMOEvent) arg1[1];
        String factoryServiceURI = null;
        
        try
        {
            
            factoryServiceURI = (String) event.getMessage().getProperty(
                WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS);
            if (factoryServiceURI == null)
            {
                Logger.getLogger(this.getClass()).log(
                    Level.DEBUG,
                    this.getClass().getName() + " : " + WSRFParameter.WSRF_FACTORY_SERVICE_ADDRESS
                                    + " property is null. IGNORED Factory Resource creation process");

                return;
            }

        }
        catch (Exception e)
        {
            Logger.getLogger(this.getClass()).log(
                Level.ERROR,
                this.getClass().getName() + " : " + " ERROR in getting Factory Service URI params: "
                                + e.getMessage());
            e.printStackTrace();
        }

        // MANAGE defined Resource Key
        if (event.getMessage().getProperty(WSRFParameter.RESOURCE_KEY) != null)
        {
            Logger.getLogger(this.getClass()).log(
                Level.DEBUG,
                this.getClass().getName() + " : "
                                + " ResourceKey creation IGNORED! Just included in Mule Message");
            return;
        }

        // MANAGE Session ID mapping for Resource key
        if (event.getMessage().getProperty(WSRFParameter.WSRF_MULE_SESSION_RESOURCE_KEY_MAPPING) != null)
        {

            if (event.getMessage().getProperty(WSRFParameter.WSRF_MULE_SESSION_RESOURCE_KEY_MAPPING).equals(
                WSRFParameter.SESSION_MAPPING_YES))
            {

                int uriHashCode = event.getMessage().getProperty(WSRFParameter.SOAP_ACTION_URI).hashCode();
                String entry = WSRFParameter.PREFIX_FOR_RESOURCE_KEY_IN_SESSION + uriHashCode;

                String resourceKey = (String) event.getSession().getProperty(entry);

                // TODO MULE-WSRF-23: manage list of entries in overlapping in
                // session mapping and session lock during operation
                if (resourceKey == null)
                {

                    resourceKey = createResource(factoryServiceURI);
                    event.getSession().setProperty(entry, resourceKey);
                    Logger.getLogger(this.getClass()).log(
                        Level.DEBUG,
                        this.getClass().getName() + " : "
                                        + " ResourceKey Created and included in Mule Session with entry : "
                                        + entry);
                }

                event.getMessage().setProperty(WSRFParameter.RESOURCE_KEY, resourceKey);
                Logger.getLogger(this.getClass()).log(
                    Level.DEBUG,
                    this.getClass().getName() + " : "
                                    + " ResourceKey creation IGNORED! Just included in Mule Session");
                return;
            }

        }

        // MANAGE Correlation ID mapping for Resource key
        if (event.getMessage().getProperty(WSRFParameter.WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING) != null)
        {

            if (event.getMessage()
                .getProperty(WSRFParameter.WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING)
                .equals(WSRFParameter.SESSION_MAPPING_YES))
            {

                int uriHashCode = event.getMessage().getProperty(WSRFParameter.SOAP_ACTION_URI).hashCode();
                String entry = WSRFParameter.PREFIX_FOR_RESOURCE_KEY_IN_SESSION + uriHashCode;
                String entryC = event.getMessage().getCorrelationId() + "_" + entry;

                String resourceKey = (String) correlationIDAndServiceToResourceKey.get(entryC);

                if (resourceKey == null)
                {

                    resourceKey = createResource(factoryServiceURI);
                    // TODO raffaele.picardi: manage auto garbage of map
                    correlationIDAndServiceToResourceKey.put(entryC, resourceKey);
                    Logger.getLogger(this.getClass())
                        .log(
                            Level.DEBUG,
                            this.getClass().getName()
                                            + " : "
                                            + " ResourceKey Created and included in Map CorrelationID Service to Resource with entry : "
                                            + entryC);
                }

                event.getMessage().setProperty(WSRFParameter.RESOURCE_KEY, resourceKey);

                return;
            }

        }

        event.getMessage().setProperty(WSRFParameter.RESOURCE_KEY, createResource(factoryServiceURI));
        Logger.getLogger(this.getClass()).log(Level.DEBUG,
            this.getClass().getName() + " : " + " ResourceKey created and set in message ");

    }



    /**
     * Creates the resource.
     * 
     * @param factoryServiceURI the factory service uri
     * @return string Resource key created
     */
    private String createResource(String factoryServiceURI)
    {
        FactoryServiceAddressingLocator factoryLocator = new FactoryServiceAddressingLocator();
        String resourceKey = null;
        try
        {
            EndpointReferenceType factoryEPR;
            FactoryPortType factory;
            factoryEPR = new EndpointReferenceType();
            factoryEPR.setAddress(new Address(factoryServiceURI));
            factory = factoryLocator.getFactoryPortTypePort(factoryEPR);

            // TODO MULE-WSRF-22: Manage input/output create resource request param
            resourceKey = factory.createResource(null);
            Logger.getLogger(this.getClass()).log(Level.DEBUG,
                this.getClass().getName() + " : " + " ResourceKey created");
        }
        catch (Exception e)
        {
            Logger.getLogger(this.getClass()).log(Level.ERROR,
                this.getClass().getName() + " : " + " Error during resource key creation");
            e.printStackTrace();
        }
        return resourceKey;
    }
}
