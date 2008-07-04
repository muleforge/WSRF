/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrfcommand;

import org.mule.providers.soap.axis.wsdl.wsrf.factory.Messages;
import org.mule.providers.soap.axis.wsdl.wsrf.util.WSRFParameter;
import org.mule.providers.soap.axis.wsdl.wsrfexception.WSRPOperationNotFound;
import org.mule.providers.soap.wsdl.wsrf.instance.RPMessages;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Factory for IWsrfCommand
 */
public class WsrfCommandFactory
{
    /**
     * Create a command object for WSRP operations
     * @param operationRP operation RP
     * @return command object
     * @throws WSRPOperationNotFound operation rp not found
     */
    public static IWsrfCommand createRPCommand(String operationRP) throws WSRPOperationNotFound
    {
        IWsrfCommand command = null;
        try
        {
            command = (IWsrfCommand) Class.forName(RPMessages.getString(WSRFParameter.COMMAND_PREFIX_CLASS) +operationRP + RPMessages.getString(WSRFParameter.COMMAND_SUFFIX_CLASSES) ).newInstance();
        }
        catch (Exception e)
        {
          Logger.getLogger(WsrfCommandFactory.class.getClass()).log(Level.ERROR, WsrfCommandFactory.class.getClass().getName() + " : " + " Skipped WS-RP operation : no operation defined " + WSRFParameter.WSRF_RESOURCEPROPERTY_OPERATION);
          WSRPOperationNotFound ex  = new WSRPOperationNotFound( RPMessages.getString(WSRFParameter.WSRP_OPERATION_NOT_FOUND) );
          ex.setStackTrace(e.getStackTrace());
        }
       
        return command;
    }

}
