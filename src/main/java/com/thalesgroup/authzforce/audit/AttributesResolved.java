package com.thalesgroup.authzforce.audit;

import java.net.URI;

public class AttributesResolved {
	
	
	private URI attributeId;
	
	private String attributeValue;

	public URI getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(URI attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}