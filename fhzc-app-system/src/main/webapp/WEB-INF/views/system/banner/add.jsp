<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
    String basePath  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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
                        <li class="active"><a href="javascript:void(0);" id="banner_title">banner新增</a></li>
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
                                        <form action="<%=contextPath%>/system/banner/add" enctype="multipart/form-data"  method="POST" class="form-horizontal">
                                            <div class="alert alert-error hide">
                                                <button class="close" data-dismiss="alert"></button>
                                                您的表单有未完成的必填项,请检查.
                                            </div>
                                            <div class="alert alert-success hide">
                                                <button class="close" data-dismiss="alert"></button>
                                                表单内容验证成功!
                                            </div>

                                            <div class="control-group">
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">banner类型</label>
                                                <div class="controls">
                                                    <select name="type" id="banner_type" class="m-wrap large">
                                                        <option value="index_pic">图片</option>
                                                        <option value="index_text">文字</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group" id="banner_pic">
                                                <label class="control-label">banner图片</label>
                                                <div class="controls">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                                            <c:choose>
                                                                <c:when test="${banner.cover == null}" >
                                                                    <img src="/static/image/no-image.png" alt="" id="default_img" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="<%=basePath%>${banner.cover}" alt="" id="default_img" />
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </div>
                                                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                                        <div>
                                                       <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                                                       <span class="fileupload-exists">更换</span>
                                                       <input type="file" name="coverFile" class="default" /></span>
                                                       <input type="hidden" name="cover" value="${banner.cover}" class="default" />
                                                            <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>
                                                        </div>
                                                    </div>
                                                    <span class="label label-important">注意!</span>
                                                 <span>
                                                 上传图片预览仅支持Firefox, Chrome, Opera,
                                                 Safari ,Internet Explorer 10
                                                 </span>
                                                </div>
                                            </div>

                                            <div class="control-group" style="display: none" id="banner_text">
                                                <label class="control-label">banner文字</label>
                                                <div class="controls">
                                                    <input type="text" name="text" value="${banner.text}" placeholder="" class="m-wrap large">
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">跳转页面类型</label>
                                                <div class="controls">
                                                   <select name="fromType" id="from_type" class="m-wrap large">
                                                       <option></option>
                                                   </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">跳转页面</label>
                                                <div class="controls">
                                                    <select name="fromId" id="from_id" class="m-wrap large"></select>
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <input name="id" type="hidden" value="${banner.id}" id="bannerId"/>
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
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

<script>
    $(function(){

        var bannerId =  '${banner.id}';
        if(bannerId != null && bannerId != ''){
            $('#banner_title').text('编辑banner');
            var fromid = '${banner.fromId}';
            var fromIds = '${fromIds}';
            var jsonIds = $.parseJSON(fromIds);
            $.each(jsonIds, function(i,val){
                $("#from_id").append("<option value='"+val.id+"'>"+val.name+"</option>");
                if(fromtype == val.value){
                    $("#from_id").val(fromid);
                }
            });
        }

        var fromtype = '${banner.fromType}';
        var fromTypes = '${fromTypes}';
        var jsonTypes = $.parseJSON(fromTypes);
        $.each(jsonTypes, function(i,val){
            $("#from_type").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(fromtype == val.value){
                $("#from_type").val(fromtype);
            }
        });

        var type = '${banner.type}';
        $('#banner_type').val(type);

        if(type == 'index_text'){
            $('#banner_pic').css("display", "none");
            $('#banner_text').css("display", "block");
        }else{
            $('#banner_text').css("display", "none");
            $('#banner_pic').css("display", "block");
        }

        $('#banner_type').change(function(){
            var type = $('#banner_type').val();
            if(type == 'index_text'){
                $('#banner_pic').css("display", "none");
                $('#banner_text').css("display", "block");
            }else{
                $('#banner_text').css("display", "none");
                $('#banner_pic').css("display", "block");
            }
        });

        $('#from_type').change(function(){
            var fromType = $('#from_type').val();
            $("#from_id").empty();
            if(fromType != null && fromType != ''){
                $.ajax({
                    type: "GET",
                    url: "<%=contextPath%>/system/banner/findFromId",
                    dataType: "json",
                    data: { "fromType": fromType },
                    success: function(req) {
                        $.each(req.data, function(i,val){
                            $("#from_id").append("<option value='"+val.id+"'>"+val.name+"</option>");
                        });

                    },
                    error: function() {

                    }
                });
            }

        });

    })
</script>