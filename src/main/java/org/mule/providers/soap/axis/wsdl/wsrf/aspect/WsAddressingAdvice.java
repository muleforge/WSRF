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
import org.mule.providers.soap.wsdl.wsrf.instance.GenericPortTypeSoapBindingsStub;
import org.mule.providers.soap.wsdl.wsrf.instance.Response;
import org.mule.umo.UMOEvent;

import java.lang.reflect.Method;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;





import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.message.addressing.AddressingHeaders;
import org.apache.axis.message.addressing.Constants;
import org.apache.axis.message.addressing.ReferencePropertiesType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.SerializationException;
import org.globus.wsrf.impl.SimpleResourceKey;

import org.springframework.aop.MethodBeforeAdvice;



/**
 * WsAddressing Aspect to inject Ws-Addressing information in SOAP - Header and/or manage WSDL Stub
 */
public class WsAddressingAdvice extends BasePriorityAdvice implements MethodBeforeAdvice
{
    /**
     * Default Constructor
     *
     */
    public WsAddressingAdvice()
    {
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");
    }
    
    /**
     * 
     * @param arg0 .
     * @param arg1 .
     * @param arg2 .
     * @throws Throwable .
     */
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable
    {
       Call call = (Call) arg1[0];
       setSerializableClasses(call);
       UMOEvent event = (UMOEvent) arg1[1];
       setOperationsDesc(call , event);
       call.setUseSOAPAction(true);
       call.setSOAPActionURI("http://www.globus.org/namespaces/examples/core/MathService_instance/MathPortType/addRequest");
       call.setEncodingStyle(null);
       call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
       call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
       call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
       setHeader(call , event);
       //  TODO raffaele.picardi: setAttachments(call);
    }
    
    
    private void setSerializableClasses(Call call) throws AxisFault, ServiceException
    {
        GenericPortTypeSoapBindingsStub stub = new GenericPortTypeSoapBindingsStub(call.getService());
        stub._createCall();
        
    }

    /**
     * set Operations Desc
     * @param call call
     * @param event event
     */
    private void setOperationsDesc(Call call , UMOEvent event)
    {
        OperationDesc oper;
        if (call.getOperation()  != null)
        {
            oper = call.getOperation();
        }
        else 
        {
            
           oper = new org.apache.axis.description.OperationDesc();
        
        }
        //TODO raffaele.picardi: generic process from Mule Message properties
        oper.setName("add");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", ">addResponse"));
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", "addResponse"));
        
        
        oper.setReturnClass(Response.class);
     
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.getParameter(0).setName("add");
        call.setOperation(oper);
        Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " OperationDesc :  injected..");
        
    }

    /**
     * set header Informations
     * @param call call
     * @param event event
     */
    private void setHeader(Call call , UMOEvent event)
    {
        AddressingHeaders headers;
        if (call.getProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS) == null )
        {
            headers = new AddressingHeaders();
        }
        else 
        {
            headers = (AddressingHeaders) call.getProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS);
        }
        
        setReferenceProperties(headers , event);
        

        
        call.setProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS, headers);
       
        Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " ENV_ADDRESSING_REQUEST_HEADERS :  injected..");
        
    }

 
    /**
     * set Reference Properties
     * @param headers headers
     * @param event event
     */
    private void setReferenceProperties(AddressingHeaders headers , UMOEvent event)
    {
        //TODO raffaele.picardi: extract Resource Key from Mule Message Property data model
        //18093512
        QName keyName = new QName("http://www.globus.org/namespaces/examples/core/MathService_instance", "MathResourceKey");
        String keyValue = "1541717";
         
        SimpleResourceKey key = new SimpleResourceKey(keyName, keyValue);

        ReferencePropertiesType props = headers.getReferenceProperties();
        
        if (props == null ) 
        {
            props = new ReferencePropertiesType();
        }
        try
        {
            props.add(key.toSOAPElement());
        }
        catch (SerializationException e)
        {
            e.printStackTrace();
        } 
        Logger.getLogger(this.getClass()).log(Level.DEBUG, this.getClass().getName() + " : ReferencesProperties injected..");
        headers.setReferenceProperties(props);
    }

}


