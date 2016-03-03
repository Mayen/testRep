<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

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

<script type="text/javascript">
	function goToPartner() {
		document.samlform.submit();
	}
	function divHide() {

		document.getElementById('idSamlDiv').style.display = "none";
		document.getElementById('btnHide').style.display = "none";
		document.getElementById('btnShow').style.display = "block";
	}
	function divShow() {
		document.getElementById('idSamlDiv').style.display = "block";
		document.getElementById('btnHide').style.display = "block";
		document.getElementById('btnShow').style.display = "none";
	}
</script>
</head>
<body>
	<h1 style="margin: 66px">User logged in the system</h1>
	<div style="padding: 20px; margin: 50px">
		<div>


			<table>

				<tr>
					<td><b>User ID:</b></td>
					<td><span style='color: blue'><bean:write
								name="bcbsSamlProfileData" property="userId" filter="false" />
					</span></td>

				</tr>
				<tr>
					<td><b>Secret Key:</b></td>
					<td><span style='color: blue'> <textarea cols="70"
								rows="10">
				<bean:write name="bcbsSamlProfileData" property="secretKey"
									filter="false" />
				</textarea>

					</span></td>

				</tr>
			</table>



		</div>
		<a href="javascript:goToPartner()">Click this to post the
			Generated Mock SAML response in order to test <br /> to url: <span
			style='color: red'> <%=java.net.URLDecoder.decode(
					(String) request.getAttribute("SAMLURL"), "UTF-8")%>
		</span>
		</a>

		<form name="samlform"
			action="<%=java.net.URLDecoder.decode(
					(String) request.getAttribute("SAMLURL"), "UTF-8")%>"
			method="post">

			<input type="hidden" name="SAMLResponse" id="SAMLResponse"
				value="<%=request.getAttribute("SAMLResponse")%>"> </input>
		</form>

		<div>
			<h3>
				Generated SAML Response:
				<button id="btnHide" onClick="divHide()" style="display: none">
					Hide</button>
				<button id="btnShow" onClick="divShow()">Show</button>
			</h3>
			<div id="idSamlDiv" style="display: none">
				<p>
					<textarea name="SAMLResponse" cols="90" rows="30">
				<%=request.getAttribute("SAMLResponse")%>
				</textarea>

				</p>
			</div>

		</div>
	</div>

</body>
</html>