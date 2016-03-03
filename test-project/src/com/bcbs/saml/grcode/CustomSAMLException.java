package com.bcbs.saml.grcode;

public class CustomSAMLException extends Exception{

	public CustomSAMLException (String description)
	{
		super(description);
	}
	
	public CustomSAMLException(String description, Throwable exception)
	{
		super(description, exception);
	}

}
