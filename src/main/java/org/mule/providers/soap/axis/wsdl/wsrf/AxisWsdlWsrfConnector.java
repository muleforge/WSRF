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
 * 
 * @author raffaele.picardi
 *
 */
class AxisWsdlWsrfConnector extends AxisWsdlConnector 
{
/**
 * @return String
 */
public final String getProtocol()
{
return "wsrf-wsdl-axis";
}
}
