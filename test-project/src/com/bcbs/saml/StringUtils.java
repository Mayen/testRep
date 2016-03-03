package com.bcbs.saml;

public class StringUtils {

	public static boolean isEmpty(String str) {

		if (str != null)
		{
			return str.isEmpty()?true:false;
			
		}
		else
			return true;
		
	}
}
