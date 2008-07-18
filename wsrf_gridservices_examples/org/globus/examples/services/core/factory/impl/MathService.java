package org.globus.examples.services.core.factory.impl;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.impl.SimpleResourceKey;
import org.globus.examples.stubs.MathService_instance.AddResponse;
import org.globus.examples.stubs.MathService_instance.GetValueRP;
import org.globus.examples.stubs.MathService_instance.SubtractResponse;

public class MathService {

	/*
	 * Private method that gets a reference to the resource specified in the
	 * endpoint reference.
	 */
	private MathResource getResource() throws RemoteException {
		Object resource = null;
		try {
			resource = ResourceContext.getResourceContext().getResource();
		} catch (Exception e) {
			throw new RemoteException("", e);
		}
		MathResource mathResource = (MathResource) resource; 
		return mathResource;
	}

	/* Implementation of add, subtract, and getValue operations */
	public AddResponse add(int a) throws RemoteException {
		MathResource mathResource = getResource();
		mathResource.setLastOp("ADDITION");
		System.out.println("--------------------");
		System.out.println("oldValue: " + mathResource.getValue());
		mathResource.setValue(mathResource.getValue() + a);
		System.out.println("newValue: " + mathResource.getValue());
		System.out.println("--------------------");
		return new AddResponse();
	}

	public SubtractResponse subtract(int a) throws RemoteException {
		MathResource mathResource = getResource();
		mathResource.setValue(mathResource.getValue() - a);
		mathResource.setLastOp("SUBTRACTION");
		return new SubtractResponse();
	}

	public int getValueRP(GetValueRP params) throws RemoteException {
		MathResource mathResource = getResource();
		return mathResource.getValue();
	}

	
}