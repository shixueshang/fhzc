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
                            <a href="javascript:void(0);">系统管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">管理员列表</a></li>
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
                                    <td>用户名</td>
                                    <td>用户真实姓名</td>
                                    <td>所属角色</td>
                                    <td>状态</td>
                                    <td>所属公司</td>
                                    <td>所属地区</td>
                                    <td>电话</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${admins}" var="admin">
                                    <tr>
                                        <td>${admin.login}</td>
                                        <td>${admin.realname}</td>
                                        <td>
                                            <c:forEach items="${roles}" var="role" >
                                                <c:if test="${admin.role == role.roleId}" >
                                                    ${role.roleName}
                                                </c:if>
                                                
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${admin.status == '1'}" >
                                                    正常
                                                </c:when>
                                                <c:when test="${admin.status == '0'}" >
                                                    禁用
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:forEach items="${departments}" var="department">
                                                <c:if test="${admin.organ == department['id']}">
                                                    ${department['name']}
                                                </c:if>
                                            </c:forEach>

                                        </td>
                                        <td>
                                            <c:forEach items="${areas}" var="area" >
                                                <c:if test="${admin.area == area.areaId}" >
                                                    ${area.areaName}
                                                </c:if>

                                            </c:forEach>

                                        </td>
                                        <td>${admin.mobile}</td>
                                        <td>
                                            <a href="<%=contextPath%>/system/admin/detail/${admin.id}" class="btn mini purple"><i class="icon-edit"></i> 编辑</a>
                                            <a href="<%=contextPath%>/system/admin/delete/${admin.id}" class="btn mini purple"><i class="icon-edit"></i> 删除</a>
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