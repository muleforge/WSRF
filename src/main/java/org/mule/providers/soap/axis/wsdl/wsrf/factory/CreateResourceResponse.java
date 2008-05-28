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
 * Class to receive Resource Information from Create request in Factory WSRF Pattern.
 */
public class CreateResourceResponse implements java.io.Serializable
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The endpoint reference. */
    private org.apache.axis.message.addressing.EndpointReferenceType endpointReference;

    /**
     * Instantiates a new creates the resource response.
     */
    public CreateResourceResponse()
    {
        // Empty Constructor
    }

    /**
     * Instantiates a new creates the resource response.
     * 
     * @param endpointReferenceP the endpoint reference p
     */
    public CreateResourceResponse(org.apache.axis.message.addressing.EndpointReferenceType endpointReferenceP)
    {
        this.endpointReference = endpointReferenceP;
    }

    /**
     * Gets the endpointReference value for this CreateResourceResponse.
     * 
     * @return endpointReference
     */
    public org.apache.axis.message.addressing.EndpointReferenceType getEndpointReference()
    {
        return endpointReference;
    }

    /**
     * Sets the endpointReference value for this CreateResourceResponse.
     * 
     * @param endpointReferenceP the endpoint reference p
     */
    public void setEndpointReference(org.apache.axis.message.addressing.EndpointReferenceType endpointReferenceP)
    {
        this.endpointReference = endpointReferenceP;
    }

    /** The equals calc. */
    private java.lang.Object equalsCalc = null;

   
    /**
     *  equals
     * @param obj o
     * @return result
     */
    public synchronized boolean equals(java.lang.Object obj)
    {
        if (obj == null) 
        {
            return false;
        }
        
        if (!(obj instanceof CreateResourceResponse)) 
        { 
            return false;
        }
        CreateResourceResponse other = (CreateResourceResponse) obj;
      
        if (this == obj) 
        {
            return true;
        }
        if (equalsCalc != null)
        {
            return (equalsCalc == obj);
        }
        equalsCalc = obj;
        boolean equals;
        boolean res = ((this.endpointReference == null && other.getEndpointReference() == null) || (this.endpointReference != null && this.endpointReference.equals(other.getEndpointReference())));
        equals = res;
        equalsCalc = null;
        return equals;
    }

    /** The hash code calc. */
    private boolean hashCodeCalc = false;


   

    /**
     * hashCode
     * @return result
     **/
    public synchronized int hashCode()
    {
        if (hashCodeCalc)
        {
            return 0;
        }
        hashCodeCalc = true;
        int hashCode = 1;
        if (getEndpointReference() != null)
        {
            hashCode += getEndpointReference().hashCode();
        }
        hashCodeCalc = false;
        return hashCode;
    }

    // Type metadata
    /** The type desc. */
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
        CreateResourceResponse.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName(
            Messages.getString(WSRFParameter.WSRF_FACTORY_SERVICE_NS), ">" + Messages.getString(WSRFParameter.WSRF_FACTORY_CREATE_RESOURCE_RESPONSE_NAME))); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
   
        elemField.setFieldName(Messages.getString("CreateResourceResponse.ENDPOINT_REFERENCE")); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), Messages.getString("CreateResourceResponse.ENDPOINT_REFERENCE2"))); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName(
            Messages.getString("WsrfFactoryGlobusStub.WS_ADDRESSING"), Messages.getString("WsrfFactoryGlobusStub.ENDPOINT_REFERENCE_TYPE"))); //$NON-NLS-1$ //$NON-NLS-2$
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object.
     * 
     * @return the type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc()
    {
        return typeDesc;
    }

    /**
     * Get Custom Serializer.
     * 
     * @param mechType the mech type
     * @param javaType the java type
     * @param xmlType the xml type
     * @return the serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
                                                                    java.lang.Class javaType,
                                                                    javax.xml.namespace.QName xmlType)
    {
        return new org.apache.axis.encoding.ser.BeanSerializer(javaType, xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer.
     * 
     * @param mechType the mech type
     * @param javaType the java type
     * @param xmlType the xml type
     * @return the deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
                                                                        java.lang.Class javaType,
                                                                        javax.xml.namespace.QName xmlType)
    {
        return new org.apache.axis.encoding.ser.BeanDeserializer(javaType, xmlType, typeDesc);
    }

}
