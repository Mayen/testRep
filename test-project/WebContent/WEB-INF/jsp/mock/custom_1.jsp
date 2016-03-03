<%-- <%@ taglib prefix="rs" uri="http://www.businessobjects.com/resource/rs" %>
<rs:doctype /> --%>

<!--
�2010 - 2013 SAP AG or an SAP affiliate company.  All rights reserved.
 
SAP and other SAP products and services mentioned herein as well as their respective logos are trademarks or 
registered trademarks of SAP AG in Germany and other countries.  Please see http://www.sap.com/corporate-en/legal/copyright/index.epx#trademark for additional 
trademark information and notices.
-->


<%@ page language="java" contentType="text/html;charset=utf-8" %>
 <%@ page import="com.bcbs.common.model.BcbsSamlProfileData" %>
<%-- <%@ page import="com.businessobjects.bip.core.web.appcontext.RequestInfo" %> --%>
<%
    //custom Java code
	/* request.getSession().setAttribute("MySecret","4909efbfbdefbfbdefbfbdefbfbd5fefbfbdefbfbd417100efbfbdefbfbd6cefbfbd4aefbfbdefbfbd2cefbfbdefbfbd1768e8869860efbfbd10efbfbdefbfbd1fefbfbd077508efbfbdefbfbdefbfbd6defbfbd6e7ceb859befbfbdefbfbd23efbfbd1157efbfbd2b06efbfbd5b7defbfbd");
    request.getSession().setAttribute("MyUser", "JohnDoe");  */
    
    BcbsSamlProfileData bcbsSamlProfileData = (BcbsSamlProfileData)request.getSession().getAttribute("bcbsSamlProfileData");
    request.getSession().setAttribute("MySecret", bcbsSamlProfileData.getSecretKey());
    request.getSession().setAttribute("MyUser", bcbsSamlProfileData.getUserId()); 
%>
<html>
    <head>
        <title></title>
		<script type="text/javascript">
		function goToLogonPage() {
            //window.location = "logon.faces";
           
            window.location = "http://dmawbicobo01.bico.edm:8080/BOE/portal/1411031301/InfoView/logon.faces";
		}   
		
		
        </script>
    </head>
    <body>
        <a href="javascript:goToLogonPage()">Click this to go to the logon page of BI launch pad</a>
    </body>
</html>
