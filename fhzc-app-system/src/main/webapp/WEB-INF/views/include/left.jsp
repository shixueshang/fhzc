<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">欢迎 - <b><shiro:principal property="login"></shiro:principal></b></a>
        </div>

        <ul class="nav navbar-top-links navbar-right">

            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
            </li>
        </ul>

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <shiro:hasPermission name="/business/manage"><li>
                            <a href="#"><i class="fa fa-th-list fa-fw"></i> 业务管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <shiro:hasPermission name="/business/product"><li>
                                    <a href="#"> 产品管理<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <shiro:hasPermission name="/business/product/list"><li class="active">
                                            <a href="/business/product/list">产品列表</a>
                                        </li></shiro:hasPermission>
                                    </ul>
                                </li></shiro:hasPermission>
                            </ul>
                        </li></shiro:hasPermission>
                    <shiro:hasPermission name="/performance/manage"><li>
                        <a href="#"><i class="fa fa-search fa-fw"></i> 业绩管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="/performance/rank"><li class="active">
                                <a href="/performance/rank"> 业绩排行</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>
                    <shiro:hasPermission name="/order/manage"><li>
                            <a href="#"><i class="fa fa-android fa-fw"></i> 订单管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <shiro:hasPermission name="/order/list"><li class="active">
                                    <a href="/order/list"> 订单列表</a>
                                </li></shiro:hasPermission>
                            </ul>
                        </li></shiro:hasPermission>
                    <shiro:hasPermission name="/financial/manage"><li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> 理财师管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <shiro:hasPermission name="/financial/list"><li class="active">
                                    <a href="/financial/list"> 理财师列表</a>
                                </li></shiro:hasPermission>
                            </ul>
                        </li></shiro:hasPermission>
                    <shiro:hasPermission name="/organization/manage"><li>
                        <a href="#"><i class="fa fa-wrench fa-fw"></i> 组织架构管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="/organization/areaDept"><li class="active">
                                <a href="/organization/areaDept"> 区总管理</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/organization/resource"><li class="active">
                                <a href="/organization/resource"> 权限管理</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>
                </ul>
            </div>
        </div>
    </nav>
</div>