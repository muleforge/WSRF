package org.globus.examples.services.core.factory.impl;

import java.util.Iterator;


import org.apache.log4j.Logger;
import org.globus.wsrf.Resource;
import org.globus.wsrf.ResourceException;
import org.globus.wsrf.ResourceKey;
import org.globus.wsrf.encoding.SerializationException;
import org.globus.wsrf.impl.ResourceHomeImpl;
import org.globus.wsrf.impl.SimpleResourceKey;

public class MathResourceHome extends ResourceHomeImpl {

	public ResourceKey create() throws Exception {
		// Create a resource and initialize it
		MathResource mathResource = (MathResource) createNewInstance();
		mathResource.initialize();
		// Get key
		ResourceKey key = new SimpleResourceKey(keyTypeName, mathResource
				.getID());
		System.out.println("--------------------");
		System.out.println("resource key created:" + key.toString());
		System.out.println("--------------------");
		// Add the resource to the list of resources in this home
		add(key, mathResource);
		
		return key;
	}

	public Resource find(ResourceKey arg0) throws ResourceException {
		// TODO Auto-generated method stub
		
		Logger.getLogger(this.getClass()).debug("-------------------------RISORSE IN MAP: " );
		Iterator it = this.resources.values().iterator();
		while (it.hasNext()) {
			Logger.getLogger(this.getClass()).debug(((MathResource)it.next()).getID().toString()+'\n');
				}
		try {
			if (arg0 !=null)
			Logger.getLogger(this.getClass()).debug("RISORSA DA CERCARE: " + arg0.toSOAPElement().toString() );
		} catch (SerializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Logger.getLogger(this.getClass()).debug("-------------------------" );
		
		
		Resource result = super.find(arg0);
		if (result== null ) {
			Logger.getLogger(this.getClass()).debug("-------------------------" );
			Logger.getLogger(this.getClass()).debug("Resource key not found!" + arg0);
			Logger.getLogger(this.getClass()).debug("-------------------------" );
			return null;
		}
		return result;
		
	}
	
	

}