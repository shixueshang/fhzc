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
                            <a href="javascript:void(0);">活动管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">活动列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
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
                                    <td>活动id</td>
                                    <td>活动名</td>
                                    <td>活动类型</td>
                                    <td>活动状态</td>
                                    <td>活动地点</td>
                                    <td>活动开始时间</td>
                                    <td>活动结束时间</td>
                                    <td>活动主办方</td>
                                    <td>投放分公司</td>
                                    <td>关注人数</td>
                                    <td>预约人数</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${activities}" var="activity">
                                    <tr>
                                        <td>${activity.id}</td>
                                        <td>${activity.name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${activity.cid == '1'}">
                                                    生命管理俱乐部
                                                </c:when>
                                                <c:when test="${activity.cid == '2'}">
                                                    高尔夫俱乐部
                                                </c:when>
                                                <c:when test="${activity.cid == '3'}">
                                                    教育传承俱乐部
                                                </c:when>
                                                <c:when test="${activity.cid == '4'}">
                                                    商旅俱乐部
                                                </c:when>
                                                <c:when test="${activity.cid == '5'}">
                                                    投资者俱乐部
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${activity.status == '0'}">
                                                    预约中
                                                </c:when>
                                                <c:when test="${activity.cid == '1'}">
                                                    已完成
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>${activity.address}</td>
                                        <td><fmt:formatDate value="${activity.beginTime}" pattern="yyyy-MM-dd"/></td>
                                        <td><fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd"/></td>
                                        <td>${activity.sponsor}</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><a href="detail/${activity.id}">编辑</a>
                                        </td>
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
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>