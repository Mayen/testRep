<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page | BCBS Portal</title>
    </head>
    <body>
    <h1>Portal Login</h1>
    <!-- <h3>User ID:  admin   ,   Shared Key:  admin123</h3> -->
    <html:form  action="loginSubmit">
    <table>
			<tr>
				<td><bean:message key="label.username" /></td>
				<td><html:text property="userName"></html:text></td>
				<td><html:errors property="userName" /></td>
			</tr>
			<tr>
				<td><bean:message key="label.password" /></td>
				<td><html:text property="password"></html:text></td>
				<td><html:errors property="password" /></td>
			</tr>
			<tr><td></td>
				<td ><html:submit />
				<html:reset /></td>
			</tr>

		</table>
		
       <%--   <bean:message key="label.username" />
         <html:text property="userName" ></html:text>
         <html:errors property="userName" />
         <br/>
         <bean:message key="label.password"/>
        <html:password property="password"></html:password>
         <html:text property="password"></html:text>
         <html:errors property="password"/>
        <html:submit/>
        <html:reset/>
        
         --%>
        
    </html:form>
    <span style='color:red'><bean:write name="LoginForm" property="message" filter="false"/> <span> 
    </body>
</html>