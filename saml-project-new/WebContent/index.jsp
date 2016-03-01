
 
<html>
     <head>
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="1;url=<%=request.getContextPath()%>/login.jspx">
        <script type="text/javascript">
            window.location.href = "<%=request.getContextPath()%>/login.jspx"
        </script>
        <title>Redirecting to BCBS Portal Login, please wait...</title>
    </head>
    <body>
        <!-- Note: don't tell people to `click` the link, just tell them that it is a link. -->
        If you are not redirected automatically, follow the <a href='<%=request.getContextPath()%>/login.jspx'>link to mock  test.</a>
    </body>
</html>