<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String contextPath = request.getContextPath();
%>
        <!-- BEGIN HEADER 顶部导航 -->
        <div class="header navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="navbar-inner">
                <div class="container-fluid">
                    <!-- BEGIN LOGO -->
                    <a class="brand" href="javascript:void(0);">
                        <img src="<%=contextPath%>/assets/images/logo_a.png" style="height: 25px;"/>
                    </a>
                    <!-- END LOGO -->
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                        <img src="<%=contextPath%>/assets/images/menu-toggler.png" alt="" />
                    </a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <ul class="nav pull-right">

                        <li class="dropdown user">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img alt="" width="29" height="29" src="<%=contextPath%>/assets/images/avatar.png" />
                                <span class="username"><shiro:principal property="realname"></shiro:principal></span>
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#" ><i class="icon-user"></i> 个人信息</a></li>
                                <li class="divider"></li>
                                <li><a href="/logout"><i class="icon-key"></i> 退出</a></li>
                            </ul>
                        </li>

                    </ul>

                </div>
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>