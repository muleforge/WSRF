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

import org.mule.providers.soap.axis.wsdl.wsrf.factory.Messages;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Base Priority Advice Class contains Priority Constant Value used from AdviceAdderHelper in order to create an ordered list of Advice
 */
public class BasePriorityAdvice implements org.aopalliance.aop.Advice, IPriorityAdvice
{
    /**
     * Default priority (Lowest priority)
     * 
     */
    public static final int DEFAULT_PRIORITY = Integer.MIN_VALUE;

    /**
     * Critical priority (highest priority)
     * 
     */
    public static final int CRITICAL_PRIORITY = Integer.MAX_VALUE;    
    
    /**
     * Priority code of this Advice
     */
    private int priorityCode;
    
    /**
     * Constructor defines priority code of Advice
     * @param priority priority code
     */
    public  BasePriorityAdvice (int priority )
    {
        this.priorityCode = priority;
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started.");    
    }
  
    /**
     * Constructor defines priority code of Advice to DEFAULT_PRIORITY 
     * It gets priority using Messages resource bundle class :
     * Messages.getString(this.getClass().getName() + "_Priority");
     * If priority is not found or not valid integer  , DEFAULT_PRIORITY is set.
     *  
     */
    public  BasePriorityAdvice ()
    {
        String res = Messages.getString(this.getClass().getName() + "_Priority");
        this.priorityCode = DEFAULT_PRIORITY;
        if (res != null)
        {
           try
           {
            this.priorityCode = Integer.parseInt(res.trim());
           }
           catch (NumberFormatException e ) 
           {
               this.priorityCode = DEFAULT_PRIORITY;
               Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : IGNORED resorce property : Not valid integer");    
           }
        }
        Logger.getLogger(this.getClass()).log(Level.INFO, this.getClass().getName() + " : started with ." + this.priorityCode + " priority");    
    }
    /**
     * return priority code
     * @return priority code
     */
    public int getPriority()
    {
        return this.priorityCode;
    }

}


