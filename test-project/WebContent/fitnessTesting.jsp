<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Fitness testing page | BCBS Portal</title>
    </head>
    <body>
    <h1>Fitness testing </h1>
	<h3>Enter Client application[IDP] provided Encoded SAMLResponse : </h3>
    
    <form  action="<%=request.getContextPath()%>/loginSeamless2.jspx" method="post">


		<div id="idSamlDiv" >
			<p>
				<textarea name="SAMLResponse"  cols="90" rows="30"></textarea>				
			</p>
		</div>
		
		<b>Redirect to url: [<%=request.getContextPath()%>/loginSeamless2.jspx]</b>
     	<input type="submit" value ="Test"></input>
        
    </form>
   
    </body>
</html>