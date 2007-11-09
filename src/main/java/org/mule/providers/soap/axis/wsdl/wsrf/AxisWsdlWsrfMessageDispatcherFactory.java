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

import org.mule.providers.AbstractMessageDispatcherFactory;
import org.mule.umo.UMOException;
import org.mule.umo.endpoint.UMOImmutableEndpoint;
import org.mule.umo.provider.UMOMessageDispatcher;

/**
 * Creates an WSDL WSRF Message dispatcher using the Axis client
 * @author raffaele.picardi
 */
public class AxisWsdlWsrfMessageDispatcherFactory extends AbstractMessageDispatcherFactory
{
/**
 * @param endpoint endpoint
* @return UMOMessageDispatcher umoMessageDispatcher
* @throws UMOException exception
*/
public final UMOMessageDispatcher create(UMOImmutableEndpoint endpoint) throws UMOException
{
    return new AxisWsdlWsrfMessageDispatcher(endpoint);
}
}
