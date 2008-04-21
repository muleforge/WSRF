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

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;



/**
 * The Class GenericPortTypeSoapBindingsStub.
 */
public class GenericPortTypeSoapBindingsStub extends org.apache.axis.client.Stub implements GenericPortType
{
    
    /** The cached ser classes. */
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    
    /** The cached ser q names. */
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    
    /** The cached ser factories. */
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    
    /** The cached deser factories. */
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    /** The _operations. */
    private static org.apache.axis.description.OperationDesc[] operations;

    static
    {
        operations = new org.apache.axis.description.OperationDesc[4];
        initOperationDesc1();
    }

    /**
     * _init operation desc1.
     */
    private static void initOperationDesc1()
    {
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("add");
        oper.addParameter(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "add"),
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class,
            org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">addResponse"));
        
        oper.setReturnClass(Response.class);
        oper.setReturnQName(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "addResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("subtract");
        oper.addParameter(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "subtract"),
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class,
            org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">subtractResponse"));
        oper.setReturnClass(Response.class);
        oper.setReturnQName(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "subtractResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        operations[1] = oper;

        /*
         * TODO raffaele.picardi: load runtime operation about method to invoke
         * 
         * oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getValueRP");
        oper.addParameter(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "getValueRP"),
            new javax.xml.namespace.QName(
                "http://www.globus.org/namespaces/examples/core/MathService_instance", ">getValueRP"),
            GetValueRP.class,
            org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "getValueRPResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        operations[2] = oper;
        
        */

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetResourceProperty");
        oper.addParameter(new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "GetResourceProperty"),
            new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "QName"),
            javax.xml.namespace.QName.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">GetResourcePropertyResponse"));
        oper.setReturnClass(org.oasis.wsrf.properties.GetResourcePropertyResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "GetResourcePropertyResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "InvalidResourcePropertyQNameFault"),
            "org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType", new javax.xml.namespace.QName(
                "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
                "InvalidResourcePropertyQNameFaultType"), true));
        oper.addFault(new org.apache.axis.description.FaultDesc(new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "ResourceUnknownFault"), "org.oasis.wsrf.properties.ResourceUnknownFaultType",
            new javax.xml.namespace.QName(
                "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
                "ResourceUnknownFaultType"), true));
        operations[3] = oper;

    }

    /**
     * Instantiates a new generic port type soap bindings stub.
     * 
     */
    public GenericPortTypeSoapBindingsStub() throws org.apache.axis.AxisFault
    {
        this(null);
    }

    /**
     * Instantiates a new generic port type soap bindings stub.
     * 
     * @param endpointURL the endpoint url
     * @param service the service

     */
    public GenericPortTypeSoapBindingsStub(java.net.URL endpointURL, javax.xml.rpc.Service service)
        throws org.apache.axis.AxisFault
    {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    /**
     * Instantiates a new generic port type soap bindings stub.
     * 
     * @param service the service
     */
    public GenericPortTypeSoapBindingsStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault
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
        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "ServiceNameType");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.ServiceNameType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

     /* 
      * TODO raffaele.picardi: loading qname entity related to object of invoked methods
      * 
      *   qName = new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">getValueRP");
        cachedSerQNames.add(qName);
        cls = GetValueRP.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);
        */

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "DeleteType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.DeleteType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "Relationship");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.Relationship.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">GetMultipleResourcePropertiesResponse");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "ReferencePropertiesType");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.ReferencePropertiesType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "ResourcePropertyValueChangeNotificationType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.ResourcePropertyValueChangeNotificationType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">ResourcePropertyValueChangeNotificationType>NewValue");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.ResourcePropertyValueChangeNotificationTypeNewValue.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-BaseFaults-1.2-draft-01.xsd",
            ">BaseFaultType>ErrorCode");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.faults.BaseFaultTypeErrorCode.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">SetResourcePropertiesResponse");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.SetResourcePropertiesResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">GetResourcePropertyResponse");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.GetResourcePropertyResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        // TODO raffaele.picardi: ResourceProperties auto serialize
        qName = new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">MathResourceProperties");
        cachedSerQNames.add(qName);
        cls = GenericResourceProperties.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-BaseFaults-1.2-draft-01.xsd",
            ">BaseFaultType>Description");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.faults.BaseFaultTypeDescription.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "UpdateType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.UpdateType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "InvalidQueryExpressionFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.InvalidQueryExpressionFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">QueryResourcePropertiesResponse");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.QueryResourcePropertiesResponse.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "FaultSubcodeValues");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.FaultSubcodeValues.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "ReplyAfterType");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.ReplyAfterType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "InvalidSetResourcePropertiesRequestContentFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.InvalidSetResourcePropertiesRequestContentFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "QueryEvaluationErrorFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.QueryEvaluationErrorFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "SetResourcePropertyRequestFailedFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.SetResourcePropertyRequestFailedFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "AttributedURI");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.AttributedURI.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        //TODO raffaele.picardi: addResponse
        qName = new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">addResponse");
        cachedSerQNames.add(qName);
        cls = Response.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "UnableToModifyResourcePropertyFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.UnableToModifyResourcePropertyFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">GetMultipleResourceProperties");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.GetMultipleResourceProperties_Element.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "EndpointReferenceType");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.EndpointReferenceType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "UnknownQueryExpressionDialectFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.UnknownQueryExpressionDialectFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">QueryResourceProperties");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.QueryResourceProperties_Element.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">ResourcePropertyValueChangeNotificationType>OldValue");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.ResourcePropertyValueChangeNotificationTypeOldValue.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-BaseFaults-1.2-draft-01.xsd", "BaseFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.faults.BaseFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "ResourceUnknownFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.ResourceUnknownFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "AttributedQName");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.AttributedQName.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(simplesf);
        cachedDeserFactories.add(simpledf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "QueryExpressionType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.QueryExpressionType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            ">SetResourceProperties");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.SetResourceProperties_Element.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "InsertType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.InsertType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName(
            "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties-1.2-draft-01.xsd",
            "InvalidResourcePropertyQNameFaultType");
        cachedSerQNames.add(qName);
        cls = org.oasis.wsrf.properties.InvalidResourcePropertyQNameFaultType.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(beansf);
        cachedDeserFactories.add(beandf);

        qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/ws/2004/03/addressing",
            "RelationshipTypeValues");
        cachedSerQNames.add(qName);
        cls = org.apache.axis.message.addressing.RelationshipTypeValues.class;
        cachedSerClasses.add(cls);
        cachedSerFactories.add(enumsf);
        cachedDeserFactories.add(enumdf);

    }
    
    /**
     * Creates the call.
     * 
     * @return the call
     * 
     */
    public Call  createCall() throws java.rmi.RemoteException
    {
        org.apache.axis.client.Call call = null;
        try
        {
            call = (org.apache.axis.client.Call) super.service.createCall();
        }
        catch (ServiceException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        configureCall(call);
        return call;
        
    }

    /**
     * Configure call.
     * 
     * @param call the call
     * @throws RemoteException the remote exception
     */
    protected void configureCall(Call call) throws java.rmi.RemoteException
    {
        try
        {
           
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
    
        }
        catch (java.lang.Throwable t)
        {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
        }
    }

   
   

    /**
     * Gets the resource property.
     * 
     * @param getResourcePropertyRequest the get resource property request
     * @return org.oasis.wsrf.properties.GetResourcePropertyResponse Get Resource
     *         Property Response
     * @throws RemoteException the remote exception
     * @see org.mule.providers.soap.wsdl.wsrf.instance.GenenericPortType#getResourceProperty(javax.xml.namespace.QName)
     */
    public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(javax.xml.namespace.QName getResourcePropertyRequest)
        throws java.rmi.RemoteException
    {
        if (super.cachedEndpoint == null)
        {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call call = createCall();
        call.setOperation(operations[3]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-ResourceProperties/GetResourceProperty");
        call.setEncodingStyle(null);
        call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new javax.xml.namespace.QName("", "GetResourceProperty"));

        setRequestHeaders(call);
        setAttachments(call);
        java.lang.Object resp = call.invoke(new java.lang.Object[]{getResourcePropertyRequest});

        if (resp instanceof java.rmi.RemoteException)
        {
            throw (java.rmi.RemoteException) resp;
        }
        else
        {
            extractAttachments(call);
            try
            {
                return (org.oasis.wsrf.properties.GetResourcePropertyResponse) resp;
            }
            catch (java.lang.Exception exception)
            {
                return (org.oasis.wsrf.properties.GetResourcePropertyResponse) org.apache.axis.utils.JavaUtils.convert(
                    resp, org.oasis.wsrf.properties.GetResourcePropertyResponse.class);
            }
        }
    }

   

}
