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

public class InfoEntryCatalogType  implements java.io.Serializable {
    private java.lang.String IDFile;
    private java.lang.String returnCode;
    private boolean isSuccess;
    private OsdEntry[] osd;
    private java.lang.String sunt;

    public InfoEntryCatalogType() {
    }

    public InfoEntryCatalogType(
           java.lang.String IDFile,
           boolean isSuccess,
           OsdEntry[] osd,
           java.lang.String returnCode,
           java.lang.String sunt) {
           this.IDFile = IDFile;
           this.returnCode = returnCode;
           this.isSuccess = isSuccess;
           this.osd = osd;
           this.sunt = sunt;
    }


    /**
     * Gets the IDFile value for this InfoEntryCatalogType.
     * 
     * @return IDFile
     */
    public java.lang.String getIDFile() {
        return IDFile;
    }


    /**
     * Sets the IDFile value for this InfoEntryCatalogType.
     * 
     * @param IDFile
     */
    public void setIDFile(java.lang.String IDFile) {
        this.IDFile = IDFile;
    }


    /**
     * Gets the returnCode value for this InfoEntryCatalogType.
     * 
     * @return returnCode
     */
    public java.lang.String getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this InfoEntryCatalogType.
     * 
     * @param returnCode
     */
    public void setReturnCode(java.lang.String returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the isSuccess value for this InfoEntryCatalogType.
     * 
     * @return isSuccess
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }


    /**
     * Sets the isSuccess value for this InfoEntryCatalogType.
     * 
     * @param isSuccess
     */
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }


    /**
     * Gets the osd value for this InfoEntryCatalogType.
     * 
     * @return osd
     */
    public OsdEntry[] getOsd() {
        return osd;
    }


    /**
     * Sets the osd value for this InfoEntryCatalogType.
     * 
     * @param osd
     */
    public void setOsd(OsdEntry[] osd) {
        this.osd = osd;
    }

    public OsdEntry getOsd(int i) {
        return this.osd[i];
    }

    public void setOsd(int i, OsdEntry _value) {
        this.osd[i] = _value;
    }


    /**
     * Gets the sunt value for this InfoEntryCatalogType.
     * 
     * @return sunt
     */
    public java.lang.String getSunt() {
        return sunt;
    }


    /**
     * Sets the sunt value for this InfoEntryCatalogType.
     * 
     * @param sunt
     */
    public void setSunt(java.lang.String sunt) {
        this.sunt = sunt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InfoEntryCatalogType))
        {
            return false;
        }
        InfoEntryCatalogType other = (InfoEntryCatalogType) obj;
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
            ((this.IDFile==null && other.getIDFile()==null) || 
             (this.IDFile!=null &&
              this.IDFile.equals(other.getIDFile()))) &&
            ((this.returnCode==null && other.getReturnCode()==null) || 
             (this.returnCode!=null &&
              this.returnCode.equals(other.getReturnCode()))) &&
            this.isSuccess == other.isIsSuccess() &&
            ((this.osd==null && other.getOsd()==null) || 
             (this.osd!=null &&
              java.util.Arrays.equals(this.osd, other.getOsd()))) &&
            ((this.sunt==null && other.getSunt()==null) || 
             (this.sunt!=null &&
              this.sunt.equals(other.getSunt())));
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
        if (getIDFile() != null) {
            _hashCode += getIDFile().hashCode();
        }
        if (getReturnCode() != null) {
            _hashCode += getReturnCode().hashCode();
        }
        _hashCode += (isIsSuccess() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOsd() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOsd());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOsd(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSunt() != null) {
            _hashCode += getSunt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }
/*
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InfoEntryCatalogType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", "InfoEntryCatalogType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDFile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IDFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSuccess");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isSuccess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("osd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "osd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.globus.org/namespaces/examples/core/MathService_instance", "osdEntry"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sunt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sunt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
    }*/

}


