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
                            <a href="javascript:void(0);">产品管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">产品列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">

                    <div class="portlet box yellow">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder"></i></h4>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td><input type="checkbox" id="checkAll"/>全选</td>
                                    <td>产品代码</td>
                                    <td>产品名称</td>
                                    <td>产品类型</td>
                                    <td>产品状态</td>
                                    <td>投放分公司</td>
                                    <td>成立日</td>
                                    <td>是否推荐</td>
                                    <td>是否展示</td>
                                    <td>关注人数</td>
                                    <td>预约人数</td>
                                    <td>预约金额</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td><input type="checkbox" id="checkOne"/></td>
                                        <td>${product.code}</td>
                                        <td>${product.name}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${product.productType == '1'}">
                                                    鑫丰母基金(开放式契约型)
                                                </c:when>
                                                <c:when test="${product.productType == '2'}">
                                                    封闭式有限合伙私募基金
                                                </c:when>
                                                <c:when test="${product.productType == '3'}">
                                                    封闭式契约型私募基金
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${product.status == '-1'}">
                                                    未知
                                                </c:when>
                                                <c:when test="${product.status == '0'}">
                                                    产品预热
                                                </c:when>
                                                <c:when test="${product.status == '1'}">
                                                    募集中
                                                </c:when>
                                                <c:when test="${product.status == '2'}">
                                                    募集结束
                                                </c:when>
                                                <c:when test="${product.status == '3'}">
                                                    募集失败
                                                </c:when>
                                                <c:when test="${product.status == '4'}">
                                                    产品成立
                                                </c:when>
                                                <c:when test="${product.status == '5'}">
                                                    产品到期
                                                </c:when>
                                                <c:when test="${product.status == '6'}">
                                                    提前结束
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td></td>
                                        <td><fmt:formatDate value="${product.foundDay}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${product.isRecommend == '1'}">
                                                    是
                                                </c:when>
                                                <c:when test="${data.isRecommend == '0'}">
                                                    否
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${product.isDisplay == '1'}">
                                                    是
                                                </c:when>
                                                <c:when test="${data.isDisplay == '0'}">
                                                    否
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><a href="detail/${product.pid}">编辑</a>
                                            <a href="#">推荐</a>
                                            <a href="#">展示</a>
                                            <a href="#">预约</a>
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