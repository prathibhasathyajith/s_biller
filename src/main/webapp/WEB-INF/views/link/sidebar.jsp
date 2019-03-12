<%@ page import="com.biller.webapp.util.MessageVarList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
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
            <%--<c:forEach items="${page}" var="item">--%>
                <%--<li class="">--%>
                    <%--<a href="/biller${item.key}">--%>
                        <%--<i class="material-icons">dashboard</i>--%>
                        <%--<p>${item.value}</p>--%>
                    <%--</a>--%>
                <%--</li>--%>
            <%--</c:forEach>--%>
            <%
                ServletContext contextSession = request.getSession().getServletContext();

                HashMap<String,String> pages = (HashMap<String,String>) contextSession.getAttribute(MessageVarList.HTTPSESSION_PAGEMAP);

                String ID = "side_";
                int index = 0;

                for(Map.Entry<String, String> pageL : pages.entrySet()) {
                    String key = pageL.getKey();
                    String value = pageL.getValue();

                    index++;

                    out.println("<li class='' id="+ID+index+" >");
                    out.println("<a href="+"/biller"+key+" onclick=saveActive("+index+")>" );
                    if(key.equals("/dashboard")){
                        out.println("<i class='material-icons'>dashboard</i>");
                    }else if(key.equals("/report")){
                        out.println("<i class='material-icons'>timeline</i>");
                    }else if(key.equals("/userManagement")){
                        out.println("<i class='material-icons'>person</i>");
                    }else if(key.equals("/invoice")){
                        out.println("<i class='material-icons'>content_paste</i>");
                    }else if(key.equals("/biller")){
                        out.println("<i class='material-icons'>view_headline</i>");
                    }

                    out.println("<p>"+value+"</p>");
                    out.println("</a>");
                    out.println("</li>");
                }

            %>


        </ul>

        <%--<ul class="nav">--%>
            <%--<li class="active">--%>
                <%--<a href="/biller/dashboard">--%>
                    <%--<i class="material-icons">dashboard</i>--%>
                    <%--<p>Dashboard</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="/biller/invoice">--%>
                    <%--<i class="material-icons">content_paste</i>--%>
                    <%--<p>Invoice</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="/report">--%>
                    <%--<i class="material-icons">timeline</i>--%>
                    <%--<p>Report</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="/user">--%>
                    <%--<i class="material-icons">person</i>--%>
                    <%--<p>User</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="/billing">--%>
                    <%--<i class="material-icons">view_headline</i>--%>
                    <%--<p>Billing</p>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li>--%>
                <%--<a href="/biller/LogoutNow">--%>
                    <%--<i class="material-icons">view_headline</i>--%>
                    <%--<p>Logout</p>--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    </div>
</div>