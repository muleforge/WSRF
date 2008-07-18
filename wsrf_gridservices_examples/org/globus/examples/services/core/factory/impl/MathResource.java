package org.globus.examples.services.core.factory.impl;

import java.util.Calendar;

import org.globus.examples.stubs.MathService_instance.InfoEntryCatalogType;
import org.globus.examples.stubs.MathService_instance.OsdEntry;
import org.globus.wsrf.Resource;
import org.globus.wsrf.ResourceIdentifier;
import org.globus.wsrf.ResourceLifetime;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.impl.SimpleResourcePropertyMetaData;
import org.globus.wsrf.impl.SimpleResourcePropertySet;
import org.globus.wsrf.impl.ReflectionResourceProperty;

public class MathResource implements Resource, ResourceIdentifier,
		ResourceProperties , ResourceLifetime {
	
	/* Resource property for lifetime */
	private Calendar terminationTime;
	
	/* Resource Property set */
	private ResourcePropertySet propSet;

	/* Resource key. This uniquely identifies this resource. */
	private Object key;

	/* Resource properties */
	private int value;

	private String lastOp;

	private int newIntValue;

	private String newStringValue;

	
	private InfoEntryCatalogType infoEntryCatalogType;
	
	/* Initializes RPs and returns a unique identifier for this resource */
	public Object initialize() throws Exception {
		this.key = new Integer(hashCode());
		this.propSet = new SimpleResourcePropertySet(
				MathQNames.RESOURCE_PROPERTIES);

		try {
			ResourceProperty valueRP = new ReflectionResourceProperty(
					MathQNames.RP_VALUE, "Value", this);
			this.propSet.add(valueRP);
			setValue(0);

			ResourceProperty lastOpRP = new ReflectionResourceProperty(
					MathQNames.RP_LASTOP, "LastOp", this);
			this.propSet.add(lastOpRP);
			setLastOp("NONE");
			
			ResourceProperty newIntValueRP = new ReflectionResourceProperty(
					MathQNames.RP_NEW_INT_VALUE, "NewIntValue", this);
			this.propSet.add(newIntValueRP);
			setNewIntValue(10);
			
			ResourceProperty newStringValueRP = new ReflectionResourceProperty(
					MathQNames.RP_NEW_STRING_VALUE, "NewStringValue", this);
			this.propSet.add(newStringValueRP);
			setNewStringValue("first value");
			
			ResourceProperty infoEntryCatalogTypeRP = new ReflectionResourceProperty(
					MathQNames.RP_INFO_ENTRY_CATALOG_TYPE, "InfoEntryCatalogType", this);
			this.propSet.add(infoEntryCatalogTypeRP);
			setInfoEntryCatalogType(defaultInfoEntryCatalogType());
			
			ResourceProperty termTimeRP = new ReflectionResourceProperty(
					SimpleResourcePropertyMetaData.TERMINATION_TIME, this);
			this.propSet.add(termTimeRP);
			ResourceProperty currTimeRP = new ReflectionResourceProperty(
					SimpleResourcePropertyMetaData.CURRENT_TIME, this);
			this.propSet.add(currTimeRP);
					
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return key;
	}

	private InfoEntryCatalogType defaultInfoEntryCatalogType() {
		InfoEntryCatalogType d = new InfoEntryCatalogType();
		d.setIsSuccess(true);
		d.setIDFile("id0");
		OsdEntry[] osdEntries =new OsdEntry[2];
		
		OsdEntry osd = new OsdEntry();
		osd.setAddressEPR("http://www.epr.it/address");
		osd.setSubLengthByte(23);
		osdEntries[0] = osd;
		OsdEntry osd1 = new OsdEntry();
		osd1.setAddressEPR("http://www.epr.it/address2");
		osd1.setSubLengthByte(223);
		osdEntries[1] = osd1;
		d.setOsd(osdEntries);
		return d;
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
	
	public synchronized void setNewIntValue(int newIntValue) {
		this.newIntValue = newIntValue;
	}
	
	public synchronized void setNewStringValue(String newStringValue) {
		this.newStringValue = newStringValue;
	}

	

	/* Required by interface ResourceProperties */
	public ResourcePropertySet getResourcePropertySet() {
		return this.propSet;
	}

	/* Required by interface ResourceIdentifier */
	public Object getID() {
		return this.key;
	}

	public int getNewIntValue() {
		return newIntValue;
	}

	public String getNewStringValue() {
		return newStringValue;
	}

	public InfoEntryCatalogType getInfoEntryCatalogType() {
		return infoEntryCatalogType;
	}

	public void setInfoEntryCatalogType(InfoEntryCatalogType infoEntryCatalogType) {
		this.infoEntryCatalogType = infoEntryCatalogType;
	}

	public Calendar getCurrentTime() { return Calendar.getInstance(); }
	public Calendar getTerminationTime() { return this.terminationTime; }
	public void setTerminationTime(Calendar terminationTime) { this.terminationTime=terminationTime; }
}