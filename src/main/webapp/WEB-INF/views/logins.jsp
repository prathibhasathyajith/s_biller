<%--
  Created by IntelliJ IDEA.
  User: prathibha_w
  Date: 3/4/2019
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${context}/app/web/login" method="post">
    <input type="text" name="userName"/>
    <input type="text" name="password"/>
    <input type="submit" value="submit" />
</form>
</body>
</html>
