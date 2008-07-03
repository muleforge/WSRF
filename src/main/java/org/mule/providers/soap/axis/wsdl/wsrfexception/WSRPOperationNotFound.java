/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.providers.soap.axis.wsdl.wsrfexception;

/**
 * Exception thrown id WSRF operation in Umo Message is not valid / found or
 * implemented
 */
public class WSRPOperationNotFound extends WSRFException
{

    /**
     * Instantiates a new wSRP operation not found.
     * 
     * @param error the error
     */
    public WSRPOperationNotFound(String error)
    {
        super(error);

    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

}
