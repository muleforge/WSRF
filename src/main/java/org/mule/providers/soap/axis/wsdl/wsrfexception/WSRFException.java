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
 * The Class WSRFException.
 */
public class WSRFException extends Exception
{

    /** (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     * @return string error
     */
    public String getMessage()
    {
        return "Original message exception: " + super.getMessage() + " -- WSRF Message: " + this.wsrfError;
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The wsrf error. */
    private String wsrfError;
    
    /**
     * Instantiates a new wSRF exception.
     * 
     * @param error the wsrf error
     */
    public WSRFException(String error)
    {
        this.wsrfError  = error;
    }
    

}


