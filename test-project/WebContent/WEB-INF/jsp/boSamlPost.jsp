<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html>
<head>
<title>BCBS Portal - Please wait...</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<style>
html {
	display: none;
}
</style>

<script type="text/javascript">
	if (self == top) {
		document.documentElement.style.display = 'block';
	} else {
		top.location = self.location;
	}
</script>

<script language="JavaScript">
	function goToPartner() {
		document.samlform.submit();
		
	}
</script>
</head>

 <body onLoad="javascript:goToPartner()"> 

	<%--  <p>&nbsp;</p>
	<h1>
	User ID:	<span style='color: red'><bean:write name="bcbsSamlProfileData"
				property="userId" filter="false" /> <span>
	</h1>
	<h1>
		Secret Key: <span style='color: red'><bean:write name="bcbsSamlProfileData"
				property="secretKey" filter="false" /> <span>
	</h1>
	<h1>
	Destination URL:	<span style='color: red'><bean:write name="bcbsSamlProfileData" property="destinationUrl" filter="false" /> <span>
	</h1>  --%>

	<form name="samlform"
		action="<%=java.net.URLDecoder.decode(
					(String) request.getAttribute("SAMLURL"), "UTF-8")%>"
		method="post">
		<%-- <form name="samlform"
		action="<bean:write name="bcbsSamlProfileData" property="destinationUrl" filter="false" />"
		method="post"> --%>
		<input type="hidden" name="MyUser" id="MyUser"
			value="<bean:write name="bcbsSamlProfileData"  property="userId" filter="false"/>">
		</input> <input type="hidden" name="MySecret" id="MySecret"
			value="<bean:write name="bcbsSamlProfileData" property="secretKey" filter="false"/>">
		</input>
	</form>
</body>
</html>