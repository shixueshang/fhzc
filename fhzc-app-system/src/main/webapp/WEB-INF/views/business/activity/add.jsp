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
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css"  />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.css"  />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />


<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/custom_datepicker/jquery-ui-1.11.min.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/custom_datepicker/jquery-ui-timepicker-addon.css" />


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
                        <li class="active"><a href="javascript:void(0);" id="activity_title">新增活动</a></li>
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
                        <div class="portlet-body form" style="height: 730px; overflow-y: scroll">
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
                                                    <input type="text" id="name" name="name" value="${activity.name}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline hide">该名称已存在,请换一个名字</span>
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
                                                <label class="control-label">活动地点 <span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" id="address" name="address" value="${activity.address}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">报名开始日期<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="applyBeginTime" data-required="1" class="m-wrap m-ctrl-medium"  size="16" id="applyBeginTime" value="<fmt:formatDate value="${activity.applyBeginTime}" pattern="yyyy-MM-dd HH:mm"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">报名结束日期<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" id="applyEndTime" name="applyEndTime" data-required="1" class="m-wrap m-ctrl-medium"  size="16" id="applyEndTime" value="<fmt:formatDate value="${activity.applyEndTime}" pattern="yyyy-MM-dd HH:mm"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动开始日期<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" id="beginTime" name="beginTime" data-required="1" class="m-wrap m-ctrl-medium"  size="16" id="beginTime" value="<fmt:formatDate value="${activity.beginTime}" pattern="yyyy-MM-dd HH:mm"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动结束日期<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" id="endTime" name="endTime" data-required="1" class="m-wrap m-ctrl-medium"  size="16" id="endTime"  value="<fmt:formatDate value="${activity.endTime}" pattern="yyyy-MM-dd HH:mm"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投放分公司<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" id="department" readonly  class="large m-wrap"/>
                                                    <input type="hidden" name="departmentId" id="department_value" />
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动主办方</label>
                                                <div class="controls">
                                                    <input type="text" id="sponsor" name="sponsor"  class="large m-wrap" value="${activity.sponsor}"/>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">活动封面<span class="required">*</span></label>
                                                <div class="controls">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                                            <c:choose>
                                                                <c:when test="${activity.cover == null}" >
                                                                    <img src="/static/image/no-image.png" alt="" id="default_img" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="<%=basePath%>${activity.cover}" alt="" id="default_img" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                                        <div>
                                                       <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                                                       <span class="fileupload-exists">更换</span>
                                                       <input type="file" name="coverFile"  id = "coverFile" class="default" /></span>
                                                       <input type="hidden" name="cover" id="cover" value="${activity.cover}" />
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
                                                    <textarea id="content" name="content"  class="span12 ckeditor m-wrap" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.content}</textarea>
                                                </div>
                                            </div>
<!--  
                                            <div class="control-group">
                                                <label class="control-label">注意事项</label>
                                                <div class="controls">
                                                    <textarea name="memo" class="span6 m-wrap" maxlength="250" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.memo}</textarea>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">活动主办方</label>
                                                <div class="controls">
                                                    <textarea id="sponsor" name="sponsor" class="span6 m-wrap" maxlength="250" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.sponsor}</textarea>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">活动摘要</label>
                                                <div class="controls">
                                                    <textarea name="summary" class="span6 m-wrap" maxlength="250" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 298px;">${activity.summary}</textarea>
                                                </div>
                                            </div>
-->
                                            <div class="control-group">
                                                <label class="control-label">详情链接</label>
                                                <div class="controls">
                                                    <input type="text" name="url" data-required="1" placeholder="" class="m-wrap large" value="${activity.url}">
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
<script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/custom_datepicker/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/custom_datepicker/i18n/jquery-ui-timepicker-addon-i18n.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/custom_datepicker/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/ckeditor/ckeditor.js"></script>




<script>
    $(function(){

        $('#applyBeginTime, #applyEndTime, #beginTime, #endTime').datetimepicker({
            timeFormat: "HH:mm",
            dateFormat: "yy-mm-dd"
        });

        var activityId =  '${activity.id}';

        if(activityId != null && activityId != ''){
            $('#activity_title').text('活动编辑');
        }
        var department = '${activity.departmentId}';
        var dept = '${department}';
        var deptJson= $.parseJSON(dept);
        $('#department').val(deptJson.title);
        $('#department_value').val(deptJson.departmentId);

        var activityTypesVal = '${activity.cid}';
        var activityTypes = '${activityTypes}';
        var aTypeJson= $.parseJSON(activityTypes);
        $.each(aTypeJson, function(i,val){
            $("#activityType").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(activityTypesVal == val.value){
                $("#activityType").val(activityTypesVal);
            }
        });

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
        
        var coverFile = '${activity.cover}';
        if(coverFile != null && coverFile !=''){
        	 $('#coverFile').val(coverFile);
        }
        
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-error', form1);
        var success1 = $('.alert-success', form1);

        form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-inline', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            messages: {
                
            },
            rules: {
                name: {
                    required: true
                },
                url: {
                    url: true
                },
                address:{
                	required: true
                },
                applyBeginTime:{
                	required: true
                },
                applyEndTime:{
                	required: true
                },
                beginTime:{
                	required: true
                },
                endTime:{
                	required: true
                },
                coverFile:{
                	required: true
                }
                
            },
          
            invalidHandler: function (event, validator) { //display error alert on form submit
                success1.hide();
                error1.show();
                App.scrollTo(error1, -200);
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
       //     	checkApplyDate();
         //   	checkDate();
            	if(!checkApplyDate()){
            		return false;
        		}
       			if(!checkDate()){
           			return false;
           		}
       			if(!checkTwoDate()){
           			return false;
           		}
            	success1.show();
                error1.hide();
                form.submit();
            }
        });
        
        function checkApplyDate() {
        	var start = $("#applyBeginTime").val();
        	var end = $("#applyEndTime").val();
        	var date1 = new Date(start);
        	var date2 = new Date(end);
        	if(date1 != null && date2 != null){
        		if(date2.getTime()<date1.getTime()){
        			alert("请输入正确的报名时间范围");
        			return false;
        		}else{
        			return true;
        		}
        	}
        };
        function checkDate() {
        	var start = $("#beginTime").val();
        	var end = $("#endTime").val();
        	var date1 = new Date(start);
        	var date2 = new Date(end);
        	if(date1 != null && date2 != null){
        		if(date2.getTime()<date1.getTime()){
        			alert("请输入正确的开始结束时间范围");
        			return false;
        		}else{
        			return true;
        		}
        	}
        };
        function checkTwoDate() {
        	var start = $("#applyEndTime").val();
        	var end = $("#beginTime").val();
        	var date1 = new Date(start);
        	var date2 = new Date(end);
        	if(date1 != null && date2 != null){
        		if(date2.getTime()<date1.getTime()){
        			alert("请确认活动开始时间晚于报名结束时间");
        			return false;
        		}else{
        			return true;
        		}
        	}
        };
        
    })
    
    $("#applyBeginTime").change(function(){
    	var applyBeginTime = $("#applyBeginTime").val();
    	var str = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s+)(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})";
    	if(!(applyBeginTime.match(str))){
			validateDateError('请确认输入正确的时间格式');
			$("#applyBeginTime").focus();
		    if(applyBeginTime != null){
		    	$("#applyBeginTime").blur(function(){
		    		var applyBeginTime = $("#applyBeginTime").val();
					if(!(applyBeginTime.match(str))){
						$("#applyBeginTime").focus();
					}
				})
		    }
            return false;
      	}else{
      		 clearDateError();
      	}
    })

    
    $("#applyEndTime").change(function(){
    	var applyEndTime = $("#applyEndTime").val();
    	var str = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s+)(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})";
    	if(!(applyEndTime.match(str))){
			validateDateError1('请确认输入正确的时间格式');
			$("#applyEndTime").focus();
			if(applyEndTime != null){
		    	$("#applyEndTime").blur(function(){
		    		var applyEndTime = $("#applyEndTime").val();
					if(!(applyEndTime.match(str))){
						$("#applyEndTime").focus();
					}
				})
		    }
            return false;
      	}else{
      		 clearDateError1();
      	}
    })
    $("#beginTime").change(function(){
    	var beginTime = $("#beginTime").val();
    	var str = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s+)(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})";
    	if(!(beginTime.match(str))){
			validateDateError2('请确认输入正确的时间格式');
			$("#beginTime").focus();
			if(beginTime != null){
		    	$("#beginTime").blur(function(){
		    		var beginTime = $("#beginTime").val();
					if(!(beginTime.match(str))){
						$("#beginTime").focus();
					}
				})
		    }
            return false;
      	}else{
      		 clearDateError2();
      	}
    })
    $("#endTime").change(function(){
    	var endTime = $("#endTime").val();
    	var str = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s+)(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})";
    	if(!(endTime.match(str))){
			validateDateError3('请确认输入正确的时间格式');
			$("#endTime").focus();
			if(endTime != null){
		    	$("#endTime").blur(function(){
		    		var endTime = $("#endTime").val();
					if(!(endTime.match(str))){
						$("#endTime").focus();
					}
				})
		    }
            return false;
      	}else{
      		 clearDateError3();
      	}
    })
    function validateDateError(text) {
        clearDateError();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#applyBeginTime").parent().append(error);
    }
    function clearDateError() {
        if ($("#applyBeginTime").parent().find("p").size() > 0){
            $("#applyBeginTime").parent().find("p").remove();
        }
    }
    function validateDateError1(text) {
        clearDateError1();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#applyEndTime").parent().append(error);
    }
    function clearDateError1() {
        if ($("#applyEndTime").parent().find("p").size() > 0){
            $("#applyEndTime").parent().find("p").remove();
        }
    }
    function validateDateError2(text) {
        clearDateError2();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#beginTime").parent().append(error);
    }
    function clearDateError2() {
        if ($("#beginTime").parent().find("p").size() > 0){
            $("#beginTime").parent().find("p").remove();
        }
    }
    function validateDateError3(text) {
        clearDateError3();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#endTime").parent().append(error);
    }
    function clearDateError3() {
        if ($("#endTime").parent().find("p").size() > 0){
            $("#endTime").parent().find("p").remove();
        }
    }
    
</script>