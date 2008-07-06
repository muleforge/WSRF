/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.test.util;

public class OsdEntry  implements java.io.Serializable {
    private java.lang.String pathFileID;
    private java.lang.String addressEPR;
    private int subLengthByte;

    public OsdEntry() {
    }

    public OsdEntry(
           java.lang.String addressEPR,
           java.lang.String pathFileID,
           int subLengthByte) {
           this.pathFileID = pathFileID;
           this.addressEPR = addressEPR;
           this.subLengthByte = subLengthByte;
    }


    /**
     * Gets the pathFileID value for this OsdEntry.
     * 
     * @return pathFileID
     */
    public java.lang.String getPathFileID() {
        return pathFileID;
    }


    /**
     * Sets the pathFileID value for this OsdEntry.
     * 
     * @param pathFileID
     */
    public void setPathFileID(java.lang.String pathFileID) {
        this.pathFileID = pathFileID;
    }


    /**
     * Gets the addressEPR value for this OsdEntry.
     * 
     * @return addressEPR
     */
    public java.lang.String getAddressEPR() {
        return addressEPR;
    }


    /**
     * Sets the addressEPR value for this OsdEntry.
     * 
     * @param addressEPR
     */
    public void setAddressEPR(java.lang.String addressEPR) {
        this.addressEPR = addressEPR;
    }


    /**
     * Gets the subLengthByte value for this OsdEntry.
     * 
     * @return subLengthByte
     */
    public int getSubLengthByte() {
        return subLengthByte;
    }


    /**
     * Sets the subLengthByte value for this OsdEntry.
     * 
     * @param subLengthByte
     */
    public void setSubLengthByte(int subLengthByte) {
        this.subLengthByte = subLengthByte;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OsdEntry))
        {
            return false;
        }
        OsdEntry other = (OsdEntry) obj;
        if (obj == null)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pathFileID==null && other.getPathFileID()==null) || 
             (this.pathFileID!=null &&
              this.pathFileID.equals(other.getPathFileID()))) &&
            ((this.addressEPR==null && other.getAddressEPR()==null) || 
             (this.addressEPR!=null &&
              this.addressEPR.equals(other.getAddressEPR()))) &&
            this.subLengthByte == other.getSubLengthByte();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPathFileID() != null) {
            _hashCode += getPathFileID().hashCode();
        }
        if (getAddressEPR() != null) {
            _hashCode += getAddressEPR().hashCode();
        }
        _hashCode += getSubLengthByte();
        __hashCodeCalc = false;
        return _hashCode;
    }

/*    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OsdEntry.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", "osdEntry"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pathFileID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pathFileID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressEPR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressEPR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subLengthByte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subLengthByte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
    }

    *//**
     * Return type metadata object
     *//*
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    *//**
     * Get Custom Serializer
     *//*
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    *//**
     * Get Custom Deserializer
     *//*
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }
*/
}


