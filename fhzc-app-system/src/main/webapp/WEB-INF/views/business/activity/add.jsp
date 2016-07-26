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
                            <a href="javascript:void(0);">活动管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">新增活动</a></li>
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
                                        <form action="<%=contextPath%>/business/activity/add" id="form_sample_1" enctype="multipart/form-data" method="POST" class="form-horizontal">
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
                                                <label class="control-label">活动名<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="name" value="${activity.name}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">活动类型</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="cid" id="activityType" data-required="1" tabindex="1">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">活动状态</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" id="status_0" name="status" value="0" checked/>
                                                        预约中
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" id="status_1" name="status" value="1" />
                                                        已完成
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动地点</label>
                                                <div class="controls">
                                                    <input type="text" name="address" value="${activity.address}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">报名开始日期</label>
                                                <div class="controls">
                                                    <input type="text" name="applyBeginTime" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${activity.applyBeginTime}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">报名结束日期</label>
                                                <div class="controls">
                                                    <input type="text" name="applyEndTime" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${activity.applyEndTime}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动开始日期</label>
                                                <div class="controls">
                                                    <input type="text" name="beginTime" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${activity.beginTime}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动结束日期</label>
                                                <div class="controls">
                                                    <input type="text" name="endTime" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动封面</label>
                                                <div class="controls">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                                            <img src="/static/image/no-image.png" alt="" id="default_img" />
                                                        </div>
                                                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                                        <div>
                                                       <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                                                       <span class="fileupload-exists">更换</span>
                                                       <input type="file" name="coverFile" class="default" /></span>
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

                                            <div class="control-group">
                                                <label class="control-label">活动内容</label>
                                                <div class="controls">
                                                    <textarea name="content"  class="span6 m-wrap" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.content}</textarea>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">注意事项</label>
                                                <div class="controls">
                                                    <textarea name="memo" class="span6 m-wrap" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.memo}</textarea>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">活动主办方</label>
                                                <div class="controls">
                                                    <textarea name="sponsor" class="span6 m-wrap" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;"></textarea>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">活动摘要</label>
                                                <div class="controls">
                                                    <textarea name="summary" class="span6 m-wrap" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.summary}</textarea>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">详情链接</label>
                                                <div class="controls">
                                                    <input type="text" name="url" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">是否显示</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="isDisplay" value="1" />
                                                        是
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="isDisplay" value="0" checked />
                                                        否
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">是否推荐(精选)</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="isRecommend" value="1" />
                                                        是
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="isRecommend" value="0" checked />
                                                        否
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <input name="id" type="hidden" value="${activity.id}" />
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 添加</button>
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
<script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
    $(function(){

        var activityTypesVal = '${activity.cid}';
        var activityTypes = '${activityTypes}';
        var aTypeJson= $.parseJSON(activityTypes);
        $.each(aTypeJson, function(i,val){
            $("#activityType").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(activityTypesVal == val.value){
                $("#activityType").val(activityTypesVal);
            }
        });

        var dispalyImg = $("#default_img");
        var imgUrl = "<%=contextPath%>/${activity.cover}";
        if(imgUrl != ""){
            dispalyImg.attr("src", imgUrl);
        }


        var status = '${activity.status}';
        if(status == 1 || status == null || status == ""){
            $.uniform.update($("input[name='status'][value='1']").attr("checked", true));
            $.uniform.update($("input[name='status'][value='0']").attr("checked", false));
        }else{
            $.uniform.update($("input[name='status'][value='1']").attr("checked", false));
            $.uniform.update($("input[name='status'][value='0']").attr("checked", true));
        }

        var isDisplay = '${activity.isDisplay}';
        if(isDisplay == 1 || isDisplay == null || isDisplay == ""){
            $.uniform.update($("input[name='isDisplay'][value='1']").attr("checked", true));
            $.uniform.update($("input[name='isDisplay'][value='0']").attr("checked", false));
        }else{
            $.uniform.update($("input[name='isDisplay'][value='1']").attr("checked", false));
            $.uniform.update($("input[name='isDisplay'][value='0']").attr("checked", true));
        }

        var isRecommend = '${activity.isRecommend}';
        if(isRecommend == 1 || isRecommend == null || isRecommend == ""){
            $.uniform.update($("input[name='isRecommend'][value='1']").attr("checked", true));
            $.uniform.update($("input[name='isRecommend'][value='0']").attr("checked", false));
        }else{
            $.uniform.update($("input[name='isRecommend'][value='1']").attr("checked", false));
            $.uniform.update($("input[name='isRecommend'][value='0']").attr("checked", true));
        }

        var form1 = $('#form_sample_1');
        var error1 = $('.alert-error', form1);
        var success1 = $('.alert-success', form1);

        form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-inline', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                name: {
                    required: true
                },
                url: {
                    required: true,
                    url: true
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                success1.hide();
                error1.show();
                App.scrollTo(error1, -200);
                return false;
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change dony by hightlight
                $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
            },

            success: function (label) {
                label
                        .addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                        .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
            },

            submitHandler: function (form) {
                success1.show();
                error1.hide();
                form.submit();
            }
        });

    })
</script>