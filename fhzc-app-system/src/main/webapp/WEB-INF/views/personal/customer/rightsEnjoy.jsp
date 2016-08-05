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
                            <a href="javascript:void(0);">客户管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">机构客户</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">权益享用人</a>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN SAMPLE FORM PORTLET-->
                    <div class="portlet box blue tabbable">
                        <div class="portlet-title">
                            <h4>
                                <i class="icon-plus"></i>
                                <span class="hidden-480"></span>
                                &nbsp;
                            </h4>
                        </div>
                        <div class="portlet-body form">
                            <div class="tabbable portlet-tabs">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <!-- BEGIN FORM-->
                                        <form action="<%=contextPath%>/personal/customer/organ/enjoy/add" method="POST" class="form-horizontal">
                                            <div class="control-group">
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">权益受益人</label>
                                                <div class="controls">
                                                    <input type="text" name="rightsEnjoyPerson" value="${enjoy.rightsEnjoyPerson}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">证件类型</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="passportTypeId" id="passportTypeId" tabindex="1">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">证件号码</label>
                                                <div class="controls">
                                                    <input type="text" name="passportCode" value="${enjoy.passportCode}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">手机号</label>
                                                <div class="controls">
                                                    <input type="text" name="mobile" maxlength="11" value="${enjoy.mobile}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <input type="hidden" name="id" id="customerOrganId">
                                                <input type="hidden" name="customerId" id="customerId">
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 保存</button>
                                            </div>
                                        </form>
                                        <!-- END FORM-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->

                    <div class="portlet box yellow">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder"></i></h4>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>权益受益人</td>
                                    <td>证件类型</td>
                                    <td>证件号码</td>
                                    <td>手机号</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${enjoyPersons}" var="enjoy">
                                    <tr>
                                        <td>${enjoy.rightsEnjoyPerson}</td>
                                        <td>
                                            <c:forEach items="${passportTypes}" var="passport">
                                                <c:if test="${enjoy.passportTypeId == passport.value}">
                                                    ${passport.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${enjoy.passportCode}</td>
                                        <td>${enjoy.mobile}</td>
                                        <td>
                                            <a href="<%=contextPath%>/personal/customer/organ/enjoy/detail/${enjoy.id}" class="btn mini purple"><i class="icon-edit"></i> 修改</a>
                                            <a href="<%=contextPath%>/personal/customer/organ/enjoy/delete/${enjoy.id}" class="btn mini purple" ><i class="icon-trash"></i> 删除</a>
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

<script>

    $(function(){

        var customerId = '${customerId}';
        $("#customerId").val(customerId);

        $("#customerOrganId").val(${enjoy.id});

        var passportType = '${enjoy.passportTypeId}';
        var passports = '${passportTypes}';
        var typeJson= $.parseJSON(passports);
        $.each(typeJson, function(i,val){
            $("#passportTypeId").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(passportType == val.value){
                $("#passportTypeId").val(passportType);
            }
        });

    })

</script>

<jsp:include page="../../include/footer.jsp"/>