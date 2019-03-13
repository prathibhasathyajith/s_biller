<%--
  Created by IntelliJ IDEA.
  User: prathibha_w
  Date: 3/12/2019
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="${context}/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="${context}/img/favicon.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Biller</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <%@ include file="../../link/links_css.jspf" %>
</head>

<body class="sidebar-mini">
<div class="wrapper">
        <tiles:insertAttribute name="sidebar" />
    <div class="main-panel">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
    </div>



</div>

</body>
<%@ include file="../../link/links_js.jspf" %>
</html>
