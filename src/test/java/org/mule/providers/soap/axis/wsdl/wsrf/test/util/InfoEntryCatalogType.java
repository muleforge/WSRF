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


/**
 * The Class InfoEntryCatalogType.
 */
public class InfoEntryCatalogType  implements java.io.Serializable {
    
    /** The ID file. */
    private java.lang.String IDFile;
    
    /** The return code. */
    private java.lang.String returnCode;
    
    /** The is success. */
    private boolean isSuccess;
    
    /** The osd. */
    private OsdEntry[] osd;
    
    /** The sunt. */
    private java.lang.String sunt;

    /**
     * Instantiates a new info entry catalog type.
     */
    public InfoEntryCatalogType() {
    }

    /**
     * Instantiates a new info entry catalog type.
     * 
     * @param IDFile the iD file
     * @param isSuccess the is success
     * @param osd the osd
     * @param returnCode the return code
     * @param sunt the sunt
     */
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
     * @param IDFile the iD file
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
     * @param returnCode the return code
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
     * @param isSuccess the is success
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
     * @param osd the osd
     */
    public void setOsd(OsdEntry[] osd) {
        this.osd = osd;
    }

    /**
     * Gets the osd.
     * 
     * @param i the i
     * @return the osd
     */
    public OsdEntry getOsd(int i) {
        return this.osd[i];
    }

    /**
     * Sets the osd.
     * 
     * @param i the i
     * @param _value the _value
     */
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
     * @param sunt the sunt
     */
    public void setSunt(java.lang.String sunt) {
        this.sunt = sunt;
    }

    /** The __equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        _equals = true &&   ((this.IDFile == null && other.getIDFile() == null) ||  
             (this.IDFile != null &&
              this.IDFile.equals(other.getIDFile()))) &&
            ((this.returnCode == null && other.getReturnCode() == null) || 
             (this.returnCode != null &&
              this.returnCode.equals(other.getReturnCode()))) &&
            this.isSuccess == other.isIsSuccess() &&
            ((this.osd == null && other.getOsd() == null) || 
             (this.osd != null &&
              java.util.Arrays.equals(this.osd, other.getOsd()))) &&
            ((this.sunt == null && other.getSunt() == null) || 
             (this.sunt != null &&
              this.sunt.equals(other.getSunt())));
        __equalsCalc = null;
        return _equals;
    }

    /** The __hash code calc. */
    private boolean __hashCodeCalc = false;
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
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


}


