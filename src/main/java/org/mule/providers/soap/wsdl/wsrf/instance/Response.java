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
 * The Class Response. A generic class containing response from wsrf service
 * invocations.
 */
public class Response implements java.io.Serializable
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // TODO raffaele.picardi: design Response Class
    /**
     * Instantiates a new response.
     */
    public Response()
    {
        // empty constructor
    }

    /** The equals calc. */
    private java.lang.Object equalsCalc = null;

    /**
     * Equals.
     * 
     * @param obj the obj
     * @return boolean result
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj)
    {
        if (obj == null) 
        {
            return false;
        }
        if (!(obj instanceof Response)) 
        {
            return false;
        }
       
       
       
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
        equals = true;
        equalsCalc = null;
        return equals;
    }

    /** The hash code calc. */
    private boolean hashCodeCalc = false;

    /** 
     * @see java.lang.Object#hashCode()
     * @return int hash code
     */
    public synchronized int hashCode()
    {
        if (hashCodeCalc)
        {
            return 0;
        }
        hashCodeCalc = true;
        int hashCode = 1;
        hashCodeCalc = false;
        return hashCode;
    }

    // Type metadata
    /** The type desc. */
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
        Response.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">addResponse"));
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
