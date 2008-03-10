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

import org.mule.umo.UMOEvent;

import org.apache.axis.client.Call;
/**
 * 
 * @author raffaele.picardi
 *
 */
public interface IExtendCall 
{
/**
 * 
 * @param call call
 * @param event event
 * @param dispatcher dispatcher
 */
void extendCall(Call call , UMOEvent event, AxisWsdlWsrfMessageDispatcher dispatcher);


}
