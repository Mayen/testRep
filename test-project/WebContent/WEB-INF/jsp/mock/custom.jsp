 <%@ taglib prefix="rs" uri="http://www.businessobjects.com/resource/rs" %>
<rs:doctype />
 
<!--
�2010 - 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 
SAP and other SAP products and services mentioned herein as well as their respective logos are trademarks or 
registered trademarks of SAP AG in Germany and other countries.  Please see http://www.sap.com/corporate-en/legal/copyright/index.epx#trademark for additional 
trademark information and notices.
-->


 <%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ page import="com.businessobjects.bip.core.web.appcontext.RequestInfo" %> 
 <%
    //custom Java code
    String MySecret=(String)request.getParameter("MySecret");
	String MyUser=(String)request.getParameter("MyUser");
	
	request.getSession().setAttribute("MySecret",MySecret);
    request.getSession().setAttribute("MyUser", MyUser);
%>
<html>
    <head>
        <title></title>
		<script type="text/javascript">
		function goToLogonPage() {
            window.location = "logon.faces"; }           
        </script>
    </head>
    <body onLoad="javascript:goToLogonPage()">
       <%--  <a href="javascript:goToLogonPage()">Click this to go to the logon page of BI launch pad</a>
        MySecret :  <%= MySecret %><br/>
        MyUser:  <%= MyUser %> --%>
    </body>
</html>

