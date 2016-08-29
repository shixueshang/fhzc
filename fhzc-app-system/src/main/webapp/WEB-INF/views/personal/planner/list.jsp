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
                            <a href="javascript:void(0);">理财师管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">理财师列表</a></li>
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
                                    <td>理财师工号</td>
                                    <td>身份证号</td>
                                    <td>姓名</td>
                                    <td>手机号</td>
                                    <td style="width:16%">所属公司</td>
                                    <td>所属城市</td>
                                    <td>在职状态</td>
                                    <td>角色</td>
                                    <td>入职时间</td>
                                    <td>离职时间</td>
                                    <td>岗位名称</td>
                                    <td>岗位序列</td>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${planners}" var="planner">
                                        <tr>
                                            <td>${planner.workNum}</td>
                                            <td>
                                                <c:forEach items="${users}" var="user">
                                                <c:if test="${planner.uid == user.uid}">
                                                    ${user.passportCode}
                                                </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach items="${users}" var="user">
                                                <c:if test="${planner.uid == user.uid}">
                                                    ${user.realname}
                                                </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach items="${users}" var="user">
                                                <c:if test="${planner.uid == user.uid}">
                                                    ${user.mobile}
                                                </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach items="${departments}" var="department">
                                                    <c:if test="${planner.departmentId == department['id']}">
                                                        ${department['name']}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                 <c:forEach items="${users}" var="user">
                                                     <c:if test="${planner.uid == user.uid}">
                                                         <c:forEach items="${areas}" var="area">
                                                             <c:if test="${user.areaId == area.areaId}">
                                                                 ${area.areaName}
                                                             </c:if>
                                                         </c:forEach>
                                                     </c:if>
                                                 </c:forEach>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${planner.status == 'on'}">
                                                        <span class="label label-success">在职</span>
                                                    </c:when>
                                                    <c:when test="${planner.status == 'off'}">
                                                        <span class="label label-important">离职</span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:forEach items="${users}" var="user">
                                                    <c:if test="${planner.uid == user.uid}">
                                                        <c:if test="${user.loginRole == 'planner'}">
                                                            理财师
                                                        </c:if>
                                                        <c:if test="${user.loginRole == 'customer'}">
                                                            客户
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td><fmt:formatDate value="${planner.entryTime}" pattern="yyyy-MM-dd"/></td>
                                            <td><fmt:formatDate value="${planner.leaveTime}" pattern="yyyy-MM-dd"/></td>
                                            <td>${planner.jobTitleCn}</td>
                                            <td>${planner.position}</td>
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