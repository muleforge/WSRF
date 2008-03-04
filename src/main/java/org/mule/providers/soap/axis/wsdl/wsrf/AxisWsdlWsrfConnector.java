/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrf;

import org.mule.providers.soap.axis.wsdl.AxisWsdlConnector;


/**
 * AxisWsdlWsrfConnector
 * @author raffaele.picardi
 *
 */
public class AxisWsdlWsrfConnector extends AxisWsdlConnector 
{
    /**
     * DEFAULT_GLOBUS_AXIS_CLIENT_CONFIG
     */
    public static final String DEFAULT_GLOBUS_AXIS_CLIENT_CONFIG = "globus-axis-client-config.wsdd";
    
    /**
     * 
     * AxisWsdlWsrfConnector constructor
     * Set Globus Axis Client Config Descriptor
     *
     */
    public AxisWsdlWsrfConnector()
    {
        super();
        this.setClientConfig(DEFAULT_GLOBUS_AXIS_CLIENT_CONFIG);
    }
    
/**
 * @return String
 */
public final String getProtocol()
{
    
return "wsrf-wsdl-axis";
}


}
