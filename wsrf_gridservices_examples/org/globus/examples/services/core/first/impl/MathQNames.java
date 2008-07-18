package org.globus.examples.services.core.first.impl;

import javax.xml.namespace.QName;

public interface MathQNames {
	public static final String NS = "http://www.globus.org/namespaces/examples/core/MathService_instance";

	public static final QName RP_VALUE = new QName(NS, "Value");

	public static final QName RP_LASTOP = new QName(NS, "LastOp");

	public static final QName RESOURCE_PROPERTIES = new QName(NS,
			"MathResourceProperties");
}
