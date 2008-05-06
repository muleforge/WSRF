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


import org.mule.providers.soap.axis.wsdl.wsrf.IExtendCall;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.aopalliance.aop.Advice;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;


/**
 * This Class add at runtime Advisors/Advices instance to a ProxyFactoryBean using
 * reflection to find and instance Advice class localized
 * org.mule.providers.soap.axis.wsdl.wsrf.aspect package.
 */
public final class AdviceAdderHelper
{
    
    /** It refers to suffix leght refer to a class name ( ".class" , 6 chars) */
    private static final int SUFFIX_CLASS_LENGTH = 6;
    
    /**
     * It refers to match method advisor TODO MULE-WSRF-6: Discuss about externalize
     * string.
     */
    private static final String MAPPED_NAME = "extend*";
    
    /** Counter advice. */
    private static int countAdvice = 0;
    
    /** The set to order advice using priority. */
    private static SortedSet order = new TreeSet(new PriorityAdvisorsComparator());
    
    /**
     * Default constructor private.
     */
    private AdviceAdderHelper() 
    {
        //nothing
    }
    
    /**
     * addAdvisors to ProxyfactoryBean given using reflection to find Advices class
     * end with "Advice" suffix. All advisors are mapped on "extend*" pattern string
     * method refer to Target bean.
     * 
     * @param targetImpl Target Object
     * @return new Proxy
     */
    public static IExtendCall addAdvisorsTo(IExtendCall targetImpl)
    {
       ProxyFactory factory = new ProxyFactory(targetImpl);
   
        List l = getListAdvisorClass();
        ListIterator li = l.listIterator();
        Advisor advisor = null;
   

        
        while (li.hasNext()) 
        {
            advisor = (Advisor) li.next();
            order.add(advisor);
        }
        
        Iterator it  = order.iterator();
        
        while (it.hasNext()) 
        {
            advisor = (Advisor) it.next();
            factory.addAdvisor(countAdvice, advisor);
            Logger.getLogger(factory.getClass()).log(Level.DEBUG,  factory.getClass().getName() + " : added " + advisor.getAdvice().getClass().getName() + " at index position " + countAdvice);
            countAdvice++;
        }
        
        countAdvice =  0;
        return (IExtendCall) factory.getProxy();
    }
    

    /**
     * Get Advice from .aspect package and create for each Advice class instance an
     * Advisor with mapped name "*extend".
     * 
     * @return A List of Advisor.
     */
    private static List getListAdvisorClass()
    {
        List advisors = new LinkedList();
        
        try
        {
            Class[] listClassAdvice = getClasses("org.mule.providers.soap.axis.wsdl.wsrf.aspect");
            Class advice = null;
            Advisor advisor = null;
            for (int i = 0; i < listClassAdvice.length; i++)
            {
                advice = listClassAdvice[i];
                try
                {
                    advisor = new NameMatchMethodPointcutAdvisor((Advice) advice.newInstance());
                }
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                   e.printStackTrace();
                }
                if (advisor != null)
                {
                ( (NameMatchMethodPointcut) advisor).setMappedName(MAPPED_NAME);
                advisors.add(advisor);
                
                }
                
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return advisors;
    }
    
    /**
     * list Classes inside a given package.
     * 
     * @param pckgname String name of a Package, EG "java.lang"
     * @return Class[] classes inside the root of the given package
     * @throws ClassNotFoundException if the Package is invalid
     */ 
    private static Class[] getClasses(String pckgname) throws ClassNotFoundException
    {
        
        ArrayList classes = new ArrayList();
        // Get a File object for the package
        File directory = null;
        try
        {
            URL str = Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/'));
            System.out.println("instance advice..." + new File(str.getFile()).getName());
            directory = new File(str.getFile());
            
            
        }
        catch (NullPointerException x)
        {
            throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
        }
        if (directory.exists())
        {
            // Get the list of the files contained in the package
            String[] files = directory.list();
            for (int i = 0; i < files.length; i++)
            {
                // we are only interested in *Advice.class files
                if (files[i].endsWith("Advice.class"))
                {
                    
                    // removes the .class extension
                    classes.add(Class.forName(pckgname + '.' + files[i].substring(0, files[i].length() - SUFFIX_CLASS_LENGTH)));
                }
            }
        }
        else
        {
            throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
        }
        Class[] classesA = new Class[classes.size()];
        classes.toArray(classesA);
        return classesA;
    } 


}


