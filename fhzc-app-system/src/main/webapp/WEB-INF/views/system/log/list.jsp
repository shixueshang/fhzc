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
                            <a href="javascript:void(0);">系统管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">操作日志</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" action="<%=contextPath%>/system/log/find" method="GET">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">操作日期</label>
                        <input type="text" class="form-control date-picker"  id="operationDate"  name="operationDate" style="width:180px;"/>
                        <input type="submit"  value="查询"/>
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
                                    <td>操作</td>
                                    <td>操作人</td>
                                    <td style="width:20%">调用方法</td>
                                    <td>操作类型</td>
                                    <td>日志级别</td>
                                    <td>请求地址</td>
                                    <td>操作时间</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${logs}" var="log">
                                    <tr>
                                        <td>${log.description}</td>
                                        <td>${log.userName}</td>
                                        <td>${log.method}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${log.type == 'controller'}">
                                                    控制层
                                                </c:when>
                                                <c:when test="${log.type == 'service'}">
                                                    服务层
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${log.level == 'normal'}">
                                                    <span class="label label-success">信息</span>
                                                </c:when>
                                                <c:when test="${log.level == 'error'}">
                                                    <span class="label label-important">错误</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>${log.requestIp}</td>
                                        <td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
<script>
    $(function(){
        $("#operationDate").datepicker({ format: 'yyyy-mm-dd', startView: 'month', autoclose: true, defaultDate:new Date() });
    })

</script>