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
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactoryBean;


/**
 * Class to use AOP Spring Framework in order to manage WSRF soap extension
 */
public class ExtenderCall implements IExtendCall
{
    /**
     * Extend call constructor default
     *
     */
    public ExtenderCall () 
    {
        System.out.println("init...Extender Call");
             
    }
    
    /**
     * Add dinamically advices class localized into .aspect package
     *
     */
    public void addAdvice()
    {
        AdviceAdderHelper.addAdvisorsTo((ProxyFactoryBean) AopContext.currentProxy());
        
    }
    /**
     * @param call call
     * @param event event
     * 
     */
    public void extendCall(Call call, UMOEvent event)
    {
        
        System.out.println("extendCall");
        System.out.println("Property Long :" + event.getMessage().getProperty("testLongProperty"));
    }

}


