<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
%>

<jsp:include page="../../include/header.jsp"/>
<!-- BEGIN BODY -->
<body class="fixed-top">
<jsp:include page="../../include/nav.jsp"/>

<!--扩展样式-->
<link href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />
<link href="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-datepicker/css/datepicker.css">

<!-- BEGIN CONTAINER -->
<div class="page-container row-fluid">
    <jsp:include page="../../include/left.jsp"/>
    <!-- BEGIN PAGE -->
    <div class="page-content">

        <!-- BEGIN PAGE CONTAINER 主体内容 -->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER 面包屑导航 -->
            <div class="row-fluid">
                <div class="span12">

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h4 class="page-title"></h4>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="javascript:void(0);">客户管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">缺位管理</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" action="/personal/customer/missPlanner" method="GET">
                    <div class="form-group">
                        <input class="form-control" id="name" placeholder="输入客户姓名" name="name" >

                        <button type="submit">查询</button>
                    </div>
                </form>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">

                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder"></i></h4>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>客户编号</td>
                                    <td>会员等级</td>
                                    <td>理财师</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${customers}" var="customer">
                                    <tr>
                                        <td>${customer.cbId}</td>
                                        <td>
                                            <c:forEach items="${users}" var="user">
                                                <c:if test="${customer.uid == user.uid}">
                                                    <c:forEach items="${customerLevel}" var="level">
                                                        <c:if test="${customer.levelId == level.value}">
                                                            ${level.key}
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </td>

                                        <td></td>
                                        <td><a href="<%=contextPath%>/personal/customer/addPlanner/${customer.customerId}" class="btn mini purple"><i class="icon-edit"></i>指定理财师</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>