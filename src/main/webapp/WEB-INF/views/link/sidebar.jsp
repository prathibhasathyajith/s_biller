<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<div class="sidebar" data-active-color="orange" data-background-color="white" data-image="../assets/img/sidebar-1.jpg">
    <!--
Tip 1: You can change the color of active element of the sidebar using: data-active-color="purple | blue | green | orange | red | rose"
Tip 2: you can also add an image using data-image tag
Tip 3: you can change the color of the sidebar with data-background-color="white | black"
-->
    <div class="logo">
        <a href="#" class="simple-text">
            Creative Tim
        </a>
    </div>
    <div class="logo logo-mini">
        <a href="#" class="simple-text">
            Ct
        </a>
    </div>
    <div class="sidebar-wrapper">
        <div class="user">
            <div class="photo">
                <img src="${context}/img/faces/avatar.jpg"/>
            </div>
            <div class="info">
                <a data-toggle="collapse" href="#collapseExample" class="collapsed">
                    Tania Andrew
                    <b class="caret"></b>
                </a>
                <div class="collapse" id="collapseExample">
                    <ul class="nav">
                        <li>
                            <a href="#">My Profile</a>
                        </li>
                        <li>
                            <a href="#">Edit Profile</a>
                        </li>
                        <li>
                            <a href="#">Settings</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <ul class="nav">
            <li class="active">
                <a href="/biller/dashboard">
                    <i class="material-icons">dashboard</i>
                    <p>Dashboard</p>
                </a>
            </li>
            <li>
                <a href="/biller/invoice">
                    <i class="material-icons">content_paste</i>
                    <p>Invoice</p>
                </a>
            </li>
            <li>
                <a href="/report">
                    <i class="material-icons">timeline</i>
                    <p>Report</p>
                </a>
            </li>
            <li>
                <a href="/user">
                    <i class="material-icons">person</i>
                    <p>User</p>
                </a>
            </li>
            <li>
                <a href="/billing">
                    <i class="material-icons">view_headline</i>
                    <p>Billing</p>
                </a>
            </li>
            <li>
                <a href="/biller/LogoutNow">
                    <i class="material-icons">view_headline</i>
                    <p>Logout</p>
                </a>
            </li>
        </ul>
    </div>
</div>