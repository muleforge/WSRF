package org.globus.examples.services.core.first.impl;

import java.rmi.RemoteException;
import java.util.Calendar;

import org.globus.wsrf.Resource;
import org.globus.wsrf.ResourceLifetime;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.impl.ReflectionResourceProperty;
import org.globus.wsrf.impl.SimpleResourcePropertySet;
import org.globus.examples.stubs.MathService_instance.AddResponse;
import org.globus.examples.stubs.MathService_instance.SubtractResponse;
import org.globus.examples.stubs.MathService_instance.GetValueRP;

public class MathService implements Resource, ResourceProperties , ResourceLifetime {

	/* Resource Property set */
	private ResourcePropertySet propSet;

	/* Resource properties */
	private int value;
	private String lastOp;

	/* Constructor. Initializes RPs */
	public MathService() throws RemoteException {
		/* Create RP set */
		this.propSet = new SimpleResourcePropertySet(
				MathQNames.RESOURCE_PROPERTIES);

		/* Initialize the RP's */
		try {
			ResourceProperty valueRP = new ReflectionResourceProperty(
					MathQNames.RP_VALUE, "Value", this);
			this.propSet.add(valueRP);
			setValue(0);

			ResourceProperty lastOpRP = new ReflectionResourceProperty(
					MathQNames.RP_LASTOP, "LastOp", this);
			this.propSet.add(lastOpRP);
			setLastOp("NONE");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* Get/Setters for the RPs */
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLastOp() {
		return lastOp;
	}

	public void setLastOp(String lastOp) {
		this.lastOp = lastOp;
	}

	/* Remotely-accessible operations */

	public AddResponse add(int a) throws RemoteException {
		value += a;
		lastOp = "ADDITION";

		return new AddResponse();
	}

	public SubtractResponse subtract(int a) throws RemoteException {
		value -= a;
		lastOp = "SUBTRACTION";

		return new SubtractResponse();
	}

	public int getValueRP(GetValueRP params) throws RemoteException {
		return value;
	}

	/* Required by interface ResourceProperties */
	public ResourcePropertySet getResourcePropertySet() {
		return this.propSet;
	}

	public Calendar getCurrentTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public Calendar getTerminationTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTerminationTime(Calendar arg0) {
		// TODO Auto-generated method stub
		
	}


}