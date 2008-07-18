package org.globus.examples.services.core.factory.impl;

import java.rmi.RemoteException;
import java.net.URL;

import org.globus.wsrf.ResourceContext;
import org.globus.wsrf.ResourceKey;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.MessageContext;
import org.globus.wsrf.utils.AddressingUtils;
import org.globus.wsrf.container.ServiceHost;
import org.globus.examples.stubs.Factory.CreateResourceResponse;
import org.globus.examples.stubs.Factory.CreateResource;

public class MathFactoryService {

	/* Implementation of createResource Operation */
	public CreateResourceResponse createResource(CreateResource request)
			throws RemoteException {
		ResourceContext ctx = null;
		MathResourceHome home = null;
		ResourceKey key = null;
		
		/* First, we create a new MathResource through the MathResourceHome */
		try {
			ctx = ResourceContext.getResourceContext();
			home = (MathResourceHome) ctx.getResourceHome();
			key = home.create();
		} catch (Exception e) {
			throw new RemoteException("", e);
		}
		EndpointReferenceType epr = null;

		/* We construct the instance's endpoint reference. The instance's service
		 * path can be found in the WSDD file as a parameter. */
		try {
			URL baseURL = ServiceHost.getBaseURL();
			String instanceService = (String) MessageContext
					.getCurrentContext().getService().getOption("instance");
			String instanceURI = baseURL.toString() + instanceService;
			// The endpoint reference includes the instance's URI and the resource key
			epr = AddressingUtils.createEndpointReference(instanceURI, key);
		} catch (Exception e) {
			throw new RemoteException("", e);
		}
		
		/* Finally, return the endpoint reference in a CreateResourceResponse */
		CreateResourceResponse response = new CreateResourceResponse();
		response.setEndpointReference(epr);
		return response;
	}
}