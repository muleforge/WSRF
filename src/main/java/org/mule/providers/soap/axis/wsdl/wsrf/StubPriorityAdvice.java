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

/**
 * Stub Priority Advice.
 */
public class StubPriorityAdvice extends BasePriorityAdvice
{
    /**
     * Priority Code
     * @return priority code
     */
    public int getPriority()
    {
        return BasePriorityAdvice.DEFAULT_PRIORITY;
    }
}


