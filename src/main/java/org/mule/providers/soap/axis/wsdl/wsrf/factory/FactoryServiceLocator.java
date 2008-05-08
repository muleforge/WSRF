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

import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;


/**
 * The Class FactoryServiceLocator.
 */
public class FactoryServiceLocator extends org.apache.axis.client.Service implements FactoryService
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new factory service locator.
     */
    public FactoryServiceLocator()
    {
        // Empty constructor
        refreshProperties();
    }
    /**
     * Refresh other properties using Messages class re-updated from MainAdvice for each new message
     *
     */
    public void refreshProperties()
    {
       //TODO raffaele.picardi:TOP continue to add other properties
        factoryPortTypePortAddress = Messages.getString(WSRFParameter.WSRF_FACTORY_PORT_TYPE_PORT_ADDRESS);
        factoryPortTypePortWSDDServiceName = Messages.getString(WSRFParameter.WSRF_FACTORY_PORT_TYPE);
    }

    /**
     * Instantiates a new factory service locator.
     * 
     * @param config the config
     */
    public FactoryServiceLocator(org.apache.axis.EngineConfiguration config)
    {
        
        super(config);
        refreshProperties();
    }

    /**
     * Instantiates a new factory service locator.
     * 
     * @param wsdlLoc the wsdl loc
     * @param sName the s name
     */
    public FactoryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
        throws javax.xml.rpc.ServiceException
    {
       
        super(wsdlLoc, sName);
        refreshProperties();
    }

    // Use to get a proxy class for FactoryPortTypePort
    /** The factory port type port address. */
    private java.lang.String factoryPortTypePortAddress = Messages.getString(WSRFParameter.WSRF_FACTORY_PORT_TYPE_PORT_ADDRESS); //$NON-NLS-1$

    /**
     * Gets the factory port type port address.
     * 
     * @return the factory port type port address
     */
    public java.lang.String getfactoryPortTypePortAddress()
    {
        return factoryPortTypePortAddress;
    }

    // The WSDD service name defaults to the port name.
    /** The factory port type port wsdd service name. */
    private java.lang.String factoryPortTypePortWSDDServiceName = Messages.getString(WSRFParameter.WSRF_FACTORY_PORT_TYPE); //$NON-NLS-1$

    /**
     * Gets the factory port type port wsdd service name.
     * 
     * @return the factory port type port wsdd service name
     */
    public java.lang.String getFactoryPortTypePortWSDDServiceName()
    {
        return factoryPortTypePortWSDDServiceName;
    }

    /**
     * Sets the factory port type port wsdd service name.
     * 
     * @param name the new factory port type port wsdd service name
     */
    public void setFactoryPortTypePortWSDDServiceName(java.lang.String name)
    {
        factoryPortTypePortWSDDServiceName = name;
    }

    /**
     *  get Factory Port Type Port
     * @return FactoryPortType factory port type
     */
    public FactoryPortType getFactoryPortTypePort() throws javax.xml.rpc.ServiceException
    {
        java.net.URL endpoint;
        try
        {
            endpoint = new java.net.URL(factoryPortTypePortAddress);
        }
        catch (java.net.MalformedURLException e)
        {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFactoryPortTypePort(endpoint);
    }

    /**
     * get factory port type port
     * @param portAddress port address
     * @return FactoryPortType factory port type object
     **/
    public FactoryPortType getFactoryPortTypePort(java.net.URL portAddress)
        throws javax.xml.rpc.ServiceException
    {
        try
        {
            WsrfFactoryGlobusStub stub = new WsrfFactoryGlobusStub(portAddress, this);
            stub.setPortName(getFactoryPortTypePortWSDDServiceName());
            return stub;
        }
        catch (org.apache.axis.AxisFault e)
        {
            return null;
        }
    }

    /**
     * Sets the factory port type port endpoint address.
     * 
     * @param address the new factory port type port endpoint address
     */
    public void setFactoryPortTypePortEndpointAddress(java.lang.String address)
    {
        factoryPortTypePortAddress = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has no
     * port for the given interface, then ServiceException is thrown.
     * 
     * @param serviceEndpointInterface the service endpoint interface
     * @return the port
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException
    {
        try
        {
            if (FactoryPortType.class.isAssignableFrom(serviceEndpointInterface))
            {
                WsrfFactoryGlobusStub stub = new WsrfFactoryGlobusStub(new java.net.URL(
                    factoryPortTypePortAddress), this);
                stub.setPortName(getFactoryPortTypePortWSDDServiceName());
                return stub;
            }
        }
        catch (java.lang.Throwable t)
        {
            throw new javax.xml.rpc.ServiceException(t);
        }
        String tmp = ""; //$NON-NLS-1$
        if (serviceEndpointInterface == null) 
        {
            tmp = "null";  //$NON-NLS-1$
        }
        else 
        {
            tmp = serviceEndpointInterface.getName();
        }
        throw new javax.xml.rpc.ServiceException(
            Messages.getString("FactoryServiceLocator.NO_STUB_IMPLEMENTATION") //$NON-NLS-1$
                            + tmp);
    }

    /**
     * For the given interface, get the stub implementation. If this service has no
     * port for the given interface, then ServiceException is thrown.
     * 
     * @param portName the port name
     * @param serviceEndpointInterface the service endpoint interface
     * @return the port
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
        throws javax.xml.rpc.ServiceException
    {
        if (portName == null)
        {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if (Messages.getString("FactoryServiceLocator.FACTORY_PORT_TYPE_PORT").equals(inputPortName)) //$NON-NLS-1$
        {
            return getFactoryPortTypePort();
        }
        else
        {
            java.rmi.Remote stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) stub).setPortName(portName);
            return stub;
        }
    }

    /**
     * get Service name
     * @return QName qname
     **/
    public javax.xml.namespace.QName getServiceName()
    {
        return new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), Messages.getString("FactoryServiceLocator.FACTORY_SERVICE")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /** The ports. */
    private java.util.HashSet ports = null;

    /**
     * get ports
     * @return Iterator iterator on ports
     */
    public java.util.Iterator getPorts()
    {
        if (ports == null)
        {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(
                Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), //$NON-NLS-1$
                Messages.getString("FactoryServiceLocator.FACTORY_PORT_TYPE_PORT"))); //$NON-NLS-1$
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     * 
     * @param portName the port name
     * @param address the address
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address)
        throws javax.xml.rpc.ServiceException
    {
        if (Messages.getString("FactoryServiceLocator.FACTORY_PORT_TYPE_PORT").equals(portName)) //$NON-NLS-1$
        {
            setFactoryPortTypePortEndpointAddress(address);
        }
        else
        { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(Messages.getString("FactoryServiceLocator.UNKNOWN_ENDPOINT_ADDRESS") //$NON-NLS-1$
                                                     + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     * 
     * @param portName the port name
     * @param address the address
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
        throws javax.xml.rpc.ServiceException
    {
        setEndpointAddress(portName.getLocalPart(), address);
    }

    /**
     * get Factory port type address
     * @return String address string of port
     */
    public java.lang.String getFactoryPortTypePortAddress() 
    {
        return factoryPortTypePortAddress;
    }


}
