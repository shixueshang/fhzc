<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
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
                            <a href="javascript:void(0);">投研报告管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">报告列表</a></li>
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
                                    <td>报告id</td>
                                    <td style="width:18%">报告名</td>
                                    <td>封面图</td>
                                    <td>报告类型</td>
                                   <!--   <td>生效状态</td>-->
                                    <td>是否显示</td>
                                    <td>是否推荐</td>
                                    <td>关注人数</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${reports}" var="report">
                                    <tr>
                                        <td>${report.id}</td>
                                        <td>${report.name}</td>
                                        <td>
                                             <img width="120px" src="<%=basePath%>${report.cover}">
                                        </td>
                                        <td>
                                            <c:forEach items="${reportTypes}" var="reportType">
                                                <c:if test="${report.cid == reportType.value}">
                                                    ${reportType.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${report.isDisplay == '0'}">
                                                    	否
                                                </c:when>
                                                <c:when test="${report.isDisplay == '1'}">
                                                   		 是
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${report.isRecommend == '0'}">
                                                    	否
                                                </c:when>
                                                <c:when test="${report.isRecommend == '1'}">
                                                   		 是
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>${report.focusNum}</td>
                                        <td><a href="<%=contextPath%>/business/report/detail/${report.id}" class="btn mini purple"><i class="icon-edit"></i>编辑</a>
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
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>