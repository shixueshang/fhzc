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
                            <a href="javascript:void(0);">订单管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">订单列表</a></li>
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
                                    <td>订单编号</td>
                                    <td>订单状态</td>
                                    <td>客户编号</td>
                                    <td>客户姓名</td>
                                    <td>投资产品</td>
                                    <td>产品编号</td>
                                    <td>投资金额(美元)</td>
                                    <td>投资金额(人民币)</td>
                                    <td>理财师</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${assets}" var="asset">
                                    <tr>
                                        <td>${asset.serial}</td>
                                        <td>
                                            <c:forEach items="${assetsStatus}" var="assetStat">
                                                <c:if test="${asset.type == assetStat.value}">
                                                    ${assetStat.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${customers}" var="customer" >
                                                <c:if test="${asset.customerId == customer.customerId}" >
                                                    <c:forEach items="${users}" var="user">
                                                        <c:if test="${customer.uid == user.uid}">
                                                            ${user.realname}
                                                        </c:if>

                                                    </c:forEach>
                                                </c:if>

                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${customers}" var="customer">
                                                <c:if test="${asset.customerId == customer.customerId}">
                                                    ${customer.cbId}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${products}" var="product">
                                                <c:if test="${asset.productId == product.pid}">
                                                    ${product.name}
                                                </c:if>

                                            </c:forEach>
                                        </td>
                                        <td>
                                            ${asset.amountUsd}
                                        </td>
                                        <td>${asset.amountRmb}</td>
                                        <td>
                                            <c:forEach items="${planners}" var="planner">
                                                <c:if test="${asset.plannerId == planner.id}">
                                                    <c:forEach items="${users}" var="user">
                                                        <c:if test="${planner.uid == user.uid}">
                                                            ${user.realname}
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
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