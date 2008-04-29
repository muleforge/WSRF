/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf.util;

/**
 * Constants Message Mule Class in order to use/manage Message WSRF Mule Provider
 * Specifications.
 */
public class WSRFParameter
{
    
  
    
    /** The Constant SERVICE_NAMESPACE. */
    public static final String SERVICE_NAMESPACE = "serviceNamespace";
    
    /** The Constant RESOURCEKEY_NAME. */
    public static final String RESOURCE_KEY_NAME = "resourceKeyName";

    
    /** The Constant RESOURCEKEY. */
    public static final String RESOURCE_KEY = "resourceKey";

    /** The Constant RESOURCEKEY substring . */
    public static final String RESOURCE_KEY_SUB = "ResourceKey";
    
    /** The Constant RETURNQNAME. */
    public static final String RETURN_QNAME = "returnQName";

    /** The Constant SOAP_METHODS. */
    public static final String SOAP_METHODS = "soapMethods";
    
    /** The Constant RETURNQTYPE. */
    public static final String RETURN_QTYPE = "returnQType";

    /** The Constant RETURNQTYPE. */
    public static final String METHOD = "method";

    /** The Constant RETURN_CLASS. */
    public static final String RETURN_CLASS = "returnClass";

    /** The Constant SOAP_ACTION_URI. */
    public static final String SOAP_ACTION_URI = "soapActionURI";

    /** The Constant WSRF_EXTRA_RESPONSE_MAP . It contains extra wsrf properties set by advice */
    public static final String WSRF_EXTRA_RESPONSE_MAP = "WSRFExtraResponse";
    
    
    /** The Constant WSRF_FACTORY_SERVICE_ADDRESS . It contains extra wsrf property: Service URI of Factory Service to use in order to create resource */
    public static final String WSRF_FACTORY_SERVICE_ADDRESS= "wsrfFactoryServiceAddress";

}


