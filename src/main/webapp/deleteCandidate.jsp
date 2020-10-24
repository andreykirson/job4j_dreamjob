<%--
  Created by IntelliJ IDEA.
  User: fruit
  Date: 10/24/2020
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<br>
<form action="<%=request.getContextPath()%>/deleteCandidate.do?id=<%=request.getParameter("id")%>" method="post">
    <h3>
        Are you sure delete candidate <%=request.getParameter("name")%>
    </h3>

    <button type="submit" class="btn btn-primary">Delete Candidate</button>
</form>
</body>
</html>
