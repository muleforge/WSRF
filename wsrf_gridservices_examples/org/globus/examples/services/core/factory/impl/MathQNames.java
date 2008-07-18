package org.globus.examples.services.core.factory.impl;

import javax.xml.namespace.QName;

public interface MathQNames
{
	public static final String NS = "http://www.globus.org/namespaces/examples/core/MathService_instance";

	public static final QName RP_VALUE = new QName(NS, "Value");

	public static final QName RP_LASTOP = new QName(NS, "LastOp");
	
	public static final QName RP_NEW_STRING_VALUE = new QName(NS, "NewStringValue");
	
	public static final QName RP_NEW_INT_VALUE = new QName(NS, "NewIntValue");
	
	public static final QName RP_INFO_ENTRY_CATALOG_TYPE = new QName(NS, "InfoEntryCatalogType");


	public static final QName RESOURCE_PROPERTIES = new QName(NS,
			"MathResourceProperties");

	public static final QName RESOURCE_REFERENCE = new QName(NS,
			"MathResourceReference");
}