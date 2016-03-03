<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page | BCBS Portal</title>
    </head>
    <body>
	
	<div style="padding:20px;margin:50px">
	
    <h1>BO.SAML Mockup</h1>
   <h3>Please Enter following fields.</h3> 
    <html:form  action="loginSubmit">
    <table>
			<tr>
				<td><b><bean:message key="label.username" /></b></td>
				<td><html:text property="userName"></html:text></td>
				<td><html:errors property="userName" /></td>
			</tr>
			<tr>
				<td><b><bean:message key="label.password" /> </b></td>
				<td><html:textarea property="password" cols="70" rows="10"></html:textarea></td>
				<td><html:errors property="password" /></td>
			</tr>
			<tr><td></td>
				<td ><html:submit  value="Bo SSO Login" />	&nbsp;&nbsp;&nbsp;		
				<b><a href="fitnessTesting.jsp">BO Fitness Test</a></b>
				</td>
			</tr>

		</table>
		
        
    </html:form>
    <span style='color:red'><bean:write name="LoginForm" property="message" filter="false"/> <span> 
	</div>
	
    </body>
</html>