<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
    String contextPath = request.getContextPath();
%>
            <div class="page-sidebar nav-collapse collapse">
                <ul>
                    <li>
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                        <div class="sidebar-toggler hidden-phone" style="margin-bottom:5px;"></div>
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    </li>
                    <li class="start active ">
                        <a href="<%=contextPath%>/system/home">
                            <i class="icon-home"></i>
                            <span class="title">Dashboard</span>
                            <span class="selected"></span>
                        </a>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-money"></i>
                            <span class="title">产品管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li class="active">
                                <a href="<%=contextPath%>/business/product/list">产品列表</a>
                            </li>
                            <li>
                                <a href="<%=contextPath%>/business/product/pub">新增产品</a>
                            </li>
                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-flag"></i>
                            <span class="title">活动管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="<%=contextPath%>/business/activity/list">活动列表</a>
                            </li>
                            <li>
                                <a href="<%=contextPath%>/business/activity/pub">新增活动</a>
                            </li>
                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-book"></i>
                            <span class="title">投研报告管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="<%=contextPath%>/business/report/list">报告列表</a>
                            </li>
                            <li>
                                <a href="<%=contextPath%>/business/report/pub">新增报告</a>
                            </li>
                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-credit-card"></i>
                            <span class="title">权益管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="<%=contextPath%>/business/rights/list">权益列表</a>
                            </li>
                            <li>
                                <a href="<%=contextPath%>/business/rights/pub">新增权益</a>
                            </li>

                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-user"></i>
                            <span class="title">客户管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="#">客户列表</a>
                            </li>
                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-group"></i>
                            <span class="title">理财师管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="#">理财师列表</a>
                            </li>
                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-sitemap"></i>
                            <span class="title">机构管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="<%=contextPath%>/organization/department">机构配置</a>
                            </li>
                        </ul>
                    </li>
                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-sitemap"></i>
                            <span class="title">权限管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="<%=contextPath%>/resource/list">权限列表</a>
                            </li>
                            <li>
                                <a href="<%=contextPath%>/resource/pub">权限新增</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- END SIDEBAR MENU -->
            </div>
