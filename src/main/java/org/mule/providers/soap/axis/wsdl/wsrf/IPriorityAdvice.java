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
 * Marker interface
 * Advice that implements this interface informs AdviceAdderHelper about their Priority code
 */
public interface IPriorityAdvice
{

 /**
  * Return Priority code of Advice
  * @return It return a code priority . see PriorityAdvice Class for the list of Constant Values of Priority
  */
 int getPriority();
 
}


