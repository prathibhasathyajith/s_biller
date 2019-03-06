<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

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

    <%@ include file="link/links_css.jspf" %>
</head>

<body>
<div class="wrapper">

    <!-- side bar-->
    <jsp:include page="link/sidebar.jsp" ></jsp:include>

    <div class="main-panel">
        <nav class="navbar navbar-transparent navbar-absolute">
            <div class="container-fluid">
                <div class="navbar-minimize">
                    <button id="minimizeSidebar" class="btn btn-round btn-white btn-fill btn-just-icon">
                        <i class="material-icons visible-on-sidebar-regular">more_vert</i>
                        <i class="material-icons visible-on-sidebar-mini">view_list</i>
                    </button>
                </div>
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"> Dashboard </a>
                </div>
                <!--     nav bar-->
                <jsp:include page="link/navbar.jsp" ></jsp:include>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <h1>Report</h1>
            </div>
        </div>
        <!--   footer -->
        <jsp:include page="link/footer.jsp" ></jsp:include>
    </div>
</div>

</body>
<%@ include file="link/links_js.jspf" %>
</html>
