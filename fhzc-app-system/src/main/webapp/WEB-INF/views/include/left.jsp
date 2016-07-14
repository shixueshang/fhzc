<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
            <div class="page-sidebar nav-collapse collapse">
                <ul>
                    <li>
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                        <div class="sidebar-toggler hidden-phone" style="margin-bottom:5px;"></div>
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    </li>
                    <li class="has-sub active">
                        <a href="javascript:;">
                            <i class="icon-file"></i>
                            <span class="title">业务管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <%--<li class="active">--%>
                                <%--<a href="/business/product/list">产品列表</a>--%>
                            <%--</li>--%>
                            <li>
                                <a href="/business/product/add">新增产品</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <!-- END SIDEBAR MENU -->
            </div>
