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
<link type="text/css" href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-ui/jquery-ui-1.10.1.custom.css">
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
                            <a href="javascript:void(0);">权益管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">预约列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid" style="display: none">
                <form name="searchForm" class="form-inline" action="/business/activity/registers">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">活动名称:</label>
                        <input class="form-control" id="activityName" name="activityName" value="${param.activityName}">

                        <label class="col-sm-2 control-label">身份证号:</label>
                        <input class="form-control" id="identityId" name="identityId" value="${param.identityId}">
                        <label class="col-sm-2 control-label">预约时间:</label>
                        <input class="form-control" id="startTime" name="startTime" style="width: 180px" value="${param.startTime}">
                        ~
                        <input class="form-control" id="endTime" name="endTime" style="width: 180px" value="${param.endTime}">
                        <button type="submit">查找</button>
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
                                    <td>预约时间</td>
                                    <td>权利名称</td>
                                    <td>客户姓名</td>
                                    <td>客户电话</td>
                                    <td>消耗积分</td>
                                    <td>预约状态</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${reservations}" var="reservation">
                                    <tr>
                                        <td><fmt:formatDate value="${reservation.reservationTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${reservation.rightName}</td>
                                        <td>${reservation.customerName}</td>
                                        <td>${reservation.phoneNum}</td>
                                        <td>${reservation.score}</td>
                                        <td>${reservation.reservationStatus}</td>
                                        <td><a class="btn mini purple Deal_Reser" data-id="${reservation.id}">处理预约</a></td>
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

<script type="text/javascript">
$(document).ready(function () {
    $(".Deal_Reser").click(function () {
        var id = $(this).data("id");
        var url = '<%=contextPath%>/business/rights/reservation/deal?id='+id;
        window.location.href = url;
    });

});
</script>

<jsp:include page="../../include/footer.jsp"/>
