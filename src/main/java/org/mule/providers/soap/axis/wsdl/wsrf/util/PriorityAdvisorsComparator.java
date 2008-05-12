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

import org.mule.providers.soap.axis.wsdl.wsrf.BasePriorityAdvice;
import org.mule.providers.soap.axis.wsdl.wsrf.IPriorityAdvice;

import java.util.Comparator;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;


/**
 * Compare two Advisors using Priority 
 */
public class PriorityAdvisorsComparator implements Comparator
{

    /* Compare Advisors  : o1 < o2 if o1 priority is >=  o2 priority. Priority number gets from .getAdvice().getPriority() method of IPriorityAdvice. if o1 or / and o2 are not IPriorityAdivece default prioirty is BasePriorityAdvice.DEFAULT_PRIORITY; 
     * @param o1 expected Advisor
     * @param o2 expected Advisor
     * @return result returns 1 if o1 > 02
     */
    public int compare(Object o1, Object o2)
    {
        int first = BasePriorityAdvice.DEFAULT_PRIORITY;
        int second = BasePriorityAdvice.DEFAULT_PRIORITY;

        if (o1 instanceof Advisor && ( ((Advisor) o1).getAdvice()) instanceof IPriorityAdvice )
        {
            o1 = ((Advisor) o1).getAdvice();
            first = ((IPriorityAdvice) o1).getPriority();
        }
        if (o2 instanceof Advisor && ( ((Advisor) o2).getAdvice()) instanceof IPriorityAdvice )
        {
            o2 = ((Advisor) o2).getAdvice();
            second = ((IPriorityAdvice) o2).getPriority();
        }

        if (first >= second)
        {
            return -1;
        }
    
        return 1;
    }

}
