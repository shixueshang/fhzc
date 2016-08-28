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

            <div class="row-fluid">
                <form  class="form-inline" action="<%=contextPath%>/business/assets/find" method="GET">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">产品名称</label>
                        <input class="form-control" id="productName" placeholder="输入产品名称" name="productName" >

                        <label class="control-label" style="margin-left: 20px">客户姓名</label>
                        <input class="form-control" id="customerName" placeholder="输入客户姓名" name="customerName" >

                        <button type="submit" class="btn blue"><i class="icon-search"></i> 查询</button>
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
                        <div class="portlet-body" style="height: 630px; overflow: scroll">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>订单编号</td>
                                    <td>订单状态</td>
                                    <td>客户编号</td>
                                    <td>客户姓名</td>
                                    <td style="width:18%">投资产品</td>
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
                                        <td>${asset.customerNum}</td>
                                        <td>${asset.customerName}</td>
                                        <td>${asset.productName}</td>
                                        <td>${asset.productCode}</td>
                                        <td>${asset.amountUsd}</td>
                                        <td>${asset.amountRmb}</td>
                                        <td>${asset.planner}</td>
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