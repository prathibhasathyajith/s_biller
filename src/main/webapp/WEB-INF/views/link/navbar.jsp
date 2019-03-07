<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<div class="collapse navbar-collapse">
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="material-icons">notifications</i>
                <span class="notification">5</span>
                <p class="hidden-lg hidden-md">
                    Notifications
                    <b class="caret"></b>
                </p>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#">Mike John responded to your email</a>
                </li>
                <li>
                    <a href="#">You have 5 new tasks</a>
                </li>
                <li>
                    <a href="#">You're now friend with Andrew</a>
                </li>
                <li>
                    <a href="#">Another Notification</a>
                </li>
                <li>
                    <a href="#">Another One</a>
                </li>
            </ul>
        </li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="material-icons">person</i>
                <p class="hidden-lg hidden-md">
                    Profile
                    <b class="caret"></b>
                </p>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#">Profile</a>
                </li>
                <li>
                    <a href="#">Settings</a>
                </li>
                <li>
                    <div class="dropdown-divider"></div>
                </li>

                <li>
                    <a href="${context}/LogoutNow">Log out</a>
                </li>
            </ul>
        </li>
        <li class="separator hidden-lg hidden-md"></li>
    </ul>
    <form class="navbar-form navbar-right" role="search">
        <div class="form-group form-search is-empty">
            <input type="text" class="form-control" placeholder="Search">
            <span class="material-input"></span>
        </div>
        <button type="submit" class="btn btn-white btn-round btn-just-icon">
            <i class="material-icons">search</i>
            <div class="ripple-container"></div>
        </button>
    </form>
</div>
