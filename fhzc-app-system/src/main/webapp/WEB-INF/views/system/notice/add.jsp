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
                        <li class="active"><a href="javascript:void(0);" id="notice_title">消息新增</a></li>
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
                                        <form id="notice_form" class="form-horizontal">
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
                                                <label class="control-label">消息标题</label>
                                                <div class="controls">
                                                    <input type="text" name="title" value="${notice.title}" class="m-wrap large"/>
                                                </div>
                                            </div>

                                            <div class="control-group" >
                                                <label class="control-label">消息内容</label>
                                                <div class="controls">
                                                    <textarea name="content" maxlength="500" class="span4 m-wrap" rows="6">${notice.content}</textarea>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">是否已推送</label>
                                                <div class="controls">
                                                    <select name="hasPushed"  class="m-wrap large" >
                                                        <option value="0">否</option>
                                                        <option value="1">是</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">推送渠道</label>
                                                <div class="controls" style="margin-top:8px;">
                                                    <input type="checkbox" id="channel_system" value="1"/>系统&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="checkbox" id="channel_sms" value="2"/>短信&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="checkbox" id="channel_push" value="3"/>消息&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input type="checkbox" id="channel_email" value="4"/>邮件&nbsp;&nbsp;&nbsp;&nbsp;
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <input name="id" type="hidden" value="${notice.id}" id="noticeId"/>
                                                <input name="pushChannel" type="hidden"  id="pushChannel"/>
                                                <button type="button" onclick="submitForm()" class="btn blue"><i class="icon-ok"></i> 保存</button>
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

        var noticeId =  '${notice.id}';
        if(noticeId != null && noticeId != ''){
            $('#notice_title').text('编辑消息');
        }


    })

    function submitForm(){
        if($('input[type="checkbox"]:checked').length == 0){
            BootstrapDialog.alert({
                title: '提示',
                message: '请至少选择一个推送渠道!'
            });
        }

        var channels = new Array();

        $('input[type="checkbox"]:checked').each(function(){
            channels.push($(this).val());
        });

        $('#pushChannel').val(channels);

        var form = $('#notice_form').serialize()
        $.ajax({
            url:"<%=contextPath%>/system/notice/add",
            type:"POST",
            data:JSON.stringify(form),
            dataType:"json",
            contentType:"application/json",
            success:function(data){
                window.location.href = "<%=contextPath%>/system/notice/list";
            },error:function(data){

            }
        });
    }
</script>