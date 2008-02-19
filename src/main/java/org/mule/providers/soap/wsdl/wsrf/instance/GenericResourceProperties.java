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

// TODO raffaele.picardi: design GenericResourceProperties Class
/**
 * The Class GenericResourceProperties.
 */
public class GenericResourceProperties
{
    
    /** The value. */
    private int value;
    
    /** The last op. */
    private java.lang.String lastOp;

    /**
     * Instantiates a new generic resource properties.
     */
    public GenericResourceProperties()
    {
        //Empty constructor
    }

    /**
     * Instantiates a new generic resource properties.
     * 
     * @param lastOp1 the last op1
     * @param value1 the value1
     */
    public GenericResourceProperties(java.lang.String lastOp1, int value1)
    {
        this.value = value1;
        this.lastOp = lastOp1;
    }

    /**
     * Gets the value value for this MathResourceProperties.
     * 
     * @return value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Sets the value value for this MathResourceProperties.
     * 
     * @param value1 the value1
     */
    public void setValue(int value1)
    {
        this.value = value1;
    }

    /**
     * Gets the lastOp value for this MathResourceProperties.
     * 
     * @return lastOp
     */
    public java.lang.String getLastOp()
    {
        return lastOp;
    }

    /**
     * Sets the lastOp value for this MathResourceProperties.
     * 
     * @param lastOp1 the last op1
     */
    public void setLastOp(java.lang.String lastOp1)
    {
        this.lastOp = lastOp1;
    }

    /** The equals calc. */
    private java.lang.Object equalsCalc = null;

    /**
     * Equals.
     * 
     * @param obj the obj
     * @return true, if equals
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj)
    {
        if (obj == null) 
        {
            return false;
        }
        if (!(obj instanceof GenericResourceProperties)) 
        {
            return false;
        }
        GenericResourceProperties other = (GenericResourceProperties) obj;
       
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
        boolean eq2 = (this.lastOp != null && this.lastOp.equals(other.getLastOp()));
        boolean eq = ((this.lastOp == null && other.getLastOp() == null) || eq2);
        boolean eq3 = this.value == other.getValue();
        equals = eq3 && eq;
        equalsCalc = null;
        return equals;
    }

    /** The hash code calc. */
    private boolean hashCodeCalc = false;

    /**
     * Hash code.
     * 
     * @return the int
     * @see java.lang.Object#hashCode()
     */
    public synchronized int hashCode()
    {
        if (hashCodeCalc)
        {
            return 0;
        }
        hashCodeCalc = true;
        int hashCode = 1;
        hashCode += getValue();
        if (getLastOp() != null)
        {
            hashCode += getLastOp().hashCode();
        }
        hashCodeCalc = false;
        return hashCode;
    }

    // Type metadata
    /** The type desc. */
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
        GenericResourceProperties.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", ">MathResourceProperties"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastOp");
        elemField.setXmlName(new javax.xml.namespace.QName(
            "http://www.globus.org/namespaces/examples/core/MathService_instance", "LastOp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
