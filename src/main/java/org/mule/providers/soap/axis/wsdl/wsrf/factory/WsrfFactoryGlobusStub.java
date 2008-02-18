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
 * Factory Soap Binding Stub (Generic).
 */
public class WsrfFactoryGlobusStub extends org.apache.axis.client.Stub implements FactoryPortType
{

    /** The cached ser classes. */
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    
    /** The cached ser q names. */
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    
    /** The cached ser factories. */
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    
    /** The cached deser factories. */
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    /** The operations. */
    private static org.apache.axis.description.OperationDesc[] operations;

    static
    {
        operations = new org.apache.axis.description.OperationDesc[1];
        initOperationDesc1();
    }

    /**
     * Inits the operation desc1.
     */
    private static void initOperationDesc1()
    {

        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName(Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE")); //$NON-NLS-1$
        oper.addParameter(new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE")), //$NON-NLS-1$ //$NON-NLS-2$
            new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), //$NON-NLS-1$
                Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE_2")), CreateResource.class, org.apache.axis.description.ParameterDesc.IN, //$NON-NLS-1$
            false, false);
        oper.setReturnType(new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE_RESPONSE2"))); //$NON-NLS-1$ //$NON-NLS-2$
        oper.setReturnClass(CreateResourceResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE_RESPONSE"))); //$NON-NLS-1$ //$NON-NLS-2$
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        operations[0] = oper;

    }

    /**
     * Instantiates a new wsrf factory globus stub.
     * 
     */
    public WsrfFactoryGlobusStub() throws org.apache.axis.AxisFault
    {
        this(null);
    }

    /**
     * Instantiates a new wsrf factory globus stub.
     * 
     * @param endpointURL the endpoint url
     * @param service the service
     * 
     */
    public WsrfFactoryGlobusStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
        throws org.apache.axis.AxisFault
    {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    /**
     * Instantiates a new wsrf factory globus stub.
     * 
     * @param service the service
     * 
     */
    public WsrfFactoryGlobusStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault
    {
        if (service == null)
        {
            super.service = new org.apache.axis.client.Service();
        }
        else
        {
            super.service = service;
        }
        java.lang.Class cls;
        javax.xml.namespace.QName qName;
        java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
        java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
        java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
        java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
        java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
        java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
        java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
        java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
        java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
        java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.FAULT_SUBCODE")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.FaultSubcodeValues.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.SERVICE_NAME_TYPE")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.ServiceNameType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.RELATION_SHIP_TYPE_VALUES")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.RelationshipTypeValues.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.ATTRIBUTE_URI")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.AttributedURI.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.REPLY_AFTER_TYPE")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.ReplyAfterType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.RELATIONSHIP")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.Relationship.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.REFERENCES_PROPERTIES_TYPE")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.ReferencePropertiesType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE_RESPONSE2")); //$NON-NLS-1$ //$NON-NLS-2$
        cachedSerQNames.add(qName);
        cls = CreateResourceResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.FACTORY_SERVICE"), Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE_2")); //$NON-NLS-1$ //$NON-NLS-2$
        cachedSerQNames.add(qName);
        cls = CreateResource.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.ATTRIBUTED_QNAME")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.AttributedQName.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), //$NON-NLS-1$
            Messages.getString("WsrfFactoryGlobusStub.ENDPOINT_REFERENCE_TYPE")); //$NON-NLS-1$
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.EndpointReferenceType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

    }

    /**
     * Creates the call.
     * 
     * @return the org.apache.axis.client. call
     * 
     */
    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException
    {
        try
        {
            org.apache.axis.client.Call call = (org.apache.axis.client.Call) super.service.createCall();
            if (super.maintainSessionSet)
            {
                call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null)
            {
                call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null)
            {
                call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null)
            {
                call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null)
            {
                call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null)
            {
                call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements())
            {
                java.lang.String key = (java.lang.String) keys.nextElement();
                call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this)
            {
                if (firstCall())
                {
                    // must set encoding style before registering serializers
                    call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i)
                    {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        Object x = cachedSerFactories.get(i);
                        if (x instanceof Class)
                        {
                            java.lang.Class sf = (java.lang.Class) cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class) cachedDeserFactories.get(i);
                            call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory)
                        {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories.get(i);
                            call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return call;
        }
        catch (java.lang.Throwable t)
        {
            throw new org.apache.axis.AxisFault(Messages.getString("WsrfFactoryGlobusStub.FAILED_TRY_CALL_OBJECT"), t); //$NON-NLS-1$
        }
    }

    /**
     * createResource
     * @param request request to create Resource
     * @return CreateResourceResponse create recource information
     */
    public CreateResourceResponse createResource(CreateResource request) throws java.rmi.RemoteException
    {
        if (super.cachedEndpoint == null)
        {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call call = createCall();
        call.setOperation(operations[0]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI(Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE_REQUEST")); //$NON-NLS-1$
        call.setEncodingStyle(null);
        call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new javax.xml.namespace.QName("", Messages.getString("WsrfFactoryGlobusStub.CREATE_RESOURCE"))); //$NON-NLS-1$ //$NON-NLS-2$

        setRequestHeaders(call);
        setAttachments(call);
        java.lang.Object resp = call.invoke(new java.lang.Object[]{request});

        if (resp instanceof java.rmi.RemoteException)
        {
            throw (java.rmi.RemoteException) resp;
        }
        else
        {
            extractAttachments(call);
            try
            {
                return (CreateResourceResponse) resp;
            }
            catch (java.lang.Exception exception)
            {
                return (CreateResourceResponse) org.apache.axis.utils.JavaUtils.convert(resp,
                    CreateResourceResponse.class);
            }
        }
    }

}
