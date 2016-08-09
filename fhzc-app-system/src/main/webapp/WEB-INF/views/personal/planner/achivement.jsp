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
                            <a href="javascript:void(0);">理财师管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">理财师业绩</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" action="<%=contextPath%>/personal/planner/achivement/find" method="GET">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">区总</label>
                        <select class="form-control"  id="area"  name="area" style="width:180px;"></select>

                        <label class="control-label" style="margin-left: 20px">分公司</label>
                        <select class="form-control"  id="subCompany"  name="subCompany" style="width:180px;"></select>

                        <label class="control-label" style="margin-left: 20px">团队</label>
                        <select class="form-control" id="team"  name="team" style="width:180px;"></select>

                        <label class="control-label" style="margin-left: 20px">月份</label>
                        <input type="text" class="form-control form_datetime"  data-date-format="yyyy-mm"  id="startDate"  name="startDate" style="width:180px;"/>
                        <script>
                            $("#startDate").datepicker({ dateFormat: 'yy-mm',startView: 3, minView: 3, autoclose: true });
                        </script>
                        <button type="submit">查询</button>
                    </div>
                </form>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="portlet-body">

                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>

    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

<script>

$(function(){
        $("#startDate").datepicker({ dateFormat: 'yy-mm',startView: 3, minView: 3, autoclose: true });

        var area = '${area}';
        var json= $.parseJSON(area);
        $.each(json, function(i,val){
            $("#area").append("<option value='"+val.departmentId+"'>"+val.title+"</option>");
        });


        $('#area').change(function(){
            var area = $('#area').val();
            $.ajax({
                type: "GET",
                url: "<%=contextPath%>/personal/planner/achivement/getDepartment",
                dataType: "json",
                data: { "departmentId": area },
                success: function(req) {
                    $("#subCompany").empty();
                    $.each(req.children, function(i,val){
                        $("#subCompany").append("<option value='"+val.departmentId+"'>"+val.title+"</option>");
                    });

                    var team = $('#team').val();
                    if(team == null || team == ''){
                        $("#team").prepend("<option value='0'>全部</option>");
                    }
                },
                error: function() {

                }
            });
        });

        $('#subCompany').change(function(){
            var subCompany = $('#subCompany').val();
            $.ajax({
                type: "GET",
                url: "<%=contextPath%>/personal/planner/achivement/getDepartment",
                dataType: "json",
                data: { "departmentId": subCompany },
                success: function(req) {
                    $("#team").empty();
                    $.each(req.children, function(i,val){
                        $("#team").append("<option value='"+val.departmentId+"'>"+val.title+"</option>");
                    });

                    var team = $('#team').val();
                    if(team == null || team == ''){
                        $("#team").prepend("<option value='0'>全部</option>");
                    }
                },
                error: function() {

                }
            });
        });

        var team = $('#team').val();
        if(team == null || team == ''){
            $("#team").prepend("<option value='0'>全部</option>");
        }

    });

</script>