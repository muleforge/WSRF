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
 * The Class GenericServiceLocator.
 * TODO raffaele.picardi: render generic string field TOP
 */
public class GenericServiceLocator extends org.apache.axis.client.Service implements GenericService
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new generic service locator.
     */
    public GenericServiceLocator()
    {
        // empty constructor
    }

    /**
     * Instantiates a new generic service locator.
     * 
     * @param config the config
     */
    public GenericServiceLocator(org.apache.axis.EngineConfiguration config)
    {
        super(config);
    }

    /**
     * Instantiates a new generic service locator.
     * 
     * @param wsdlLoc the wsdl loc
     * @param sName the s name
     */
    public GenericServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
        throws javax.xml.rpc.ServiceException
    {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for portTypePort
    /** The Math port type port_address. */
    private java.lang.String portTypePortAddress = "http://localhost:8080/wsrf/services/";

    /**
     * Gets the math port type port address.
     * 
     * @return the math port type port address
     * @see org.mule.providers.soap.wsdl.wsrf.instance.GenericService#getMathPortTypePortAddress()
     */
    public java.lang.String getPortTypePortAddress()
    {
        return portTypePortAddress;
    }

    // The WSDD service name defaults to the port name.
    /** The Math port type port wsdd service name. */
    private java.lang.String portTypePortWSDDServiceName = "PortTypePort";

    /**
     * Gets the math port type port wsdd service name.
     * 
     * @return the math port type port wsdd service name
     */
    public java.lang.String getPortTypePortWSDDServiceName()
    {
        return portTypePortWSDDServiceName;
    }

    /**
     * Sets the math port type port wsdd service name.
     * 
     * @param name the new math port type port wsdd service name
     */
    public void setPortTypePortWSDDServiceName(java.lang.String name)
    {
        portTypePortWSDDServiceName = name;
    }

    /**
     * @return GenericPortType  generic port type
     * @see org.mule.providers.soap.wsdl.wsrf.instance.GenericService#getMathPortTypePort()
     */
    public GenericPortType getPortTypePort() throws javax.xml.rpc.ServiceException
    {
        java.net.URL endpoint;
        try
        {
            endpoint = new java.net.URL(portTypePortAddress);
        }
        catch (java.net.MalformedURLException e)
        {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPortTypePort(endpoint);
    }

    /**
     * Gets the math port type port.
     * 
     * @param portAddress the port address
     * @return GenericPortType generic port type
     * @see org.mule.providers.soap.wsdl.wsrf.instance.GenericService#getMathPortTypePort(java.net.URL)
     */
    public GenericPortType getPortTypePort(java.net.URL portAddress)
        throws javax.xml.rpc.ServiceException
    {
        try
        {
      
            GenericPortTypeSoapBindingsStub stub = new GenericPortTypeSoapBindingsStub(portAddress, this);
            stub.setPortName(getPortTypePortWSDDServiceName());
            return stub;
        }
        catch (org.apache.axis.AxisFault e)
        {
            return null;
        }
    }

    /**
     * Sets the math port type port endpoint address.
     * 
     * @param address the new math port type port endpoint address
     */
    public void setPortTypePortEndpointAddress(java.lang.String address)
    {
        portTypePortAddress = address;
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
            if (GenericPortType.class.isAssignableFrom(serviceEndpointInterface))
            {
                GenericPortTypeSoapBindingsStub stub = new GenericPortTypeSoapBindingsStub(
                    new java.net.URL(portTypePortAddress), this);
                stub.setPortName(getPortTypePortWSDDServiceName());
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
            "There is no stub implementation for the interface:  "
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
        if ("PortTypePort".equals(inputPortName))
        {
            return getPortTypePort();
        }
        else
        {
            java.rmi.Remote stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) stub).setPortName(portName);
            return stub;
        }
    }

    /**
     * @see org.apache.axis.client.Service#getServiceName()
     * @return javax.xml.namespace.QName qname
     */
    public javax.xml.namespace.QName getServiceName()
    {
        return new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance/service", "MathService");
    }

    /** The ports. */
    private java.util.HashSet ports = null;

    /** 
     * @see org.apache.axis.client.Service#getPorts()
     * @return java.util.Iterator itarator
     */
    public java.util.Iterator getPorts()
    {
        if (ports == null)
        {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(
                "http://www.globus.org/namespaces/examples/core/MathService_instance/service",
                "MathPortTypePort"));
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
        if ("MathPortTypePort".equals(portName))
        {
            setPortTypePortEndpointAddress(address);
        }
        else
        { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port"
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

}
