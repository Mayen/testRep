package com.bcbs.saml.grcode;

public interface SAMLResponseWriter {
	
	public String getSAMLResponse(SAMLData samlBaseData);
	public String getSAMLAssertion(SAMLData samlData);

}
