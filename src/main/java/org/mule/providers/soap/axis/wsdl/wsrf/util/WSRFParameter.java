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

    /**
     * The Constant WSRF_EXTRA_RESPONSE_MAP . It contains extra wsrf properties set
     * by advice
     */
    public static final String WSRF_EXTRA_RESPONSE_MAP = "WSRFExtraResponse";
    
    
    /**
     * The Constant WSRF_FACTORY_SERVICE_ADDRESS . It contains extra wsrf property:
     * Service URI of Factory Service to use in order to create resource
     */
    public static final String WSRF_FACTORY_SERVICE_ADDRESS = "wsrfFactoryServiceAddress";

    /**
     * The Constant WSRF_MULE_SESSION_RESOURCE_KEY_MAPPING . It specifies if
     * ResourceKey must be added to Session Mule Message
     * SESSION_MAPPING_YES|SESSION_MAPPING_NO
     */
    public static final String WSRF_MULE_SESSION_RESOURCE_KEY_MAPPING = "wsrfMuleSessionResourceKeyMapping";

    /** The Constant SESSION_MAPPING_YES. */
    public static final String SESSION_MAPPING_YES = "yes";
    
    /** The Constant SESSION_MAPPING_NO. */
    public static final String SESSION_MAPPING_NO = "no";

    /**
     * The Constant PREFIX_FOR_RESOURCE_KEY_IN_SESSION specifies the prefix for all
     * resource key related to SOAP ACTION URIs for each session.
     */
    public static final String PREFIX_FOR_RESOURCE_KEY_IN_SESSION = "WSRF_RK_SESSION_";

    /** The Constant FIRST_VALUE_IN. */
    public static final int FIRST_VALUE_IN = 2000;
    
    /** The Constant SECOND_VALUE_IN. */
    public static final int SECOND_VALUE_IN = 4000;

    /**
     * The Constant WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING . It specifies if
     * ResourceKey must be persisted by WSRF Provider in order to lookup it using
     * correlationID and Service URI.
     */
    public static final String WSRF_MULE_CORRELATIONID_RESOURCE_KEY_MAPPING = "wsrfMuleCorrelationIDResourceKeyMapping";
    
    /**
     * The Constant WSRF_ENDPOINT_PROPERTY_MAP . It specifies the property name of
     * map object thath encapsulates all wsrf properties-.
     */
    public static final String WSRF_ENDPOINT_PROPERTY_MAP = "wsrfOption";
    
    /**
     * The Constant WSRF_FACTORY_PORT_TYPE_PORT_ADDRESS specifies port type address of
     * globus service.
     */
    public static final String WSRF_FACTORY_PORT_TYPE_PORT_ADDRESS = "wsrfFactoryPortTypePortAddress";
    
    /** The Constant wsrfPrefix. */
    public static final String wsrfPrefix = "wsrf";
}


