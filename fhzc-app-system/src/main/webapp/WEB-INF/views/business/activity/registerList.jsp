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
                        <li class="active"><a href="javascript:void(0);">报名管理</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form name="searchForm" id="form_sample_1" class="form-inline" action="/business/activity/registers">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">活动名称:</label>
                        <input class="form-control" id="activityName" name="activityName" value="${param.activityName}">

                        <label class="col-sm-2 control-label">报名时间:</label>
                        <input class="form-control date-picker" id="startTime" name="startTime" style="width: 180px" value="${param.startTime}">
                        ~
                        <input class="form-control date-picker" id="endTime" name="endTime" style="width: 180px" value="${param.endTime}">
                        <button type="submit" onclick="validate();">查找</button>
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
                                    <td>报名时间</td>
                                    <td>活动名称</td>
                                    <td>客户姓名</td>
                                    <td>客户等级</td>
                                    <td>客户手机号码</td>
                                    <td>报名人数</td>
                                    <td>是否参加</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${activityApplies}" var="activityApply">
                                    <tr>
                                        <td><fmt:formatDate value="${activityApply.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${activityApply.activityName}</td>
                                        <td>${activityApply.personName}</td>
                                        <td>${activityApply.level}</td>
                                        <td>${activityApply.mobile}</td>
                                        <td>${activityApply.personNum}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${activityApply.result == '1'}">
                                                    <span class="label label-success">参加</span>
                                                </c:when>
                                                <c:when test="${activityApply.result == '0'}">
                                                    <span class="label label-warning">不参加</span>
                                                </c:when>
                                            </c:choose>
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
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>

		function validate(){
			var form1 = $('#form_sample_1');
			var start = $("#startTime").val();
			var end = $("#endTime").val();
			var date1 = new Date(start);
			var date2 = new Date(end);
			if(date1 != null && date2 != null){
				if(date2.getTime()<date1.getTime()){
					alert("请输入正确的时间范围");
					window.event.returnValue = false;
				}
			}else{
				form1.submit();
			}
		    
		}
	
</script>
