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
                            <a href="javascript:void(0);">权益管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">权益预约</a></li>
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
                                        <form action="<%=contextPath%>/business/rights/reservation/add" id="form_sample_1" method="get" class="form-horizontal" onsubmit=" return checkInputs();">
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
                                            <input type="hidden" name="userValid" id="userValid" value="0">
                                            <input type="hidden" name="rightValid" id="rightValid" value="0">
                                            <input type="hidden" name="customerId" id="customerId" value="0">
                                            <div class="control-group">
                                                <label class="control-label">用户手机号<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="phoneNum" maxlength="11" id="phoneNum" data-required="1" placeholder="输入客户的手机号" >
                                                    <button type="button" name="checkPhone" id="checkPhone">验证</button>
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">姓名</label>
                                                <div class="controls">
                                                    <input type="text" name="cname" id="cname" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户等级</label>
                                                <div class="controls">
                                                    <input type="text" name="clevel" id="clevel" disabled>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户可用积分</label>
                                                <div class="controls">
                                                    <input type="text" name="csore" id="csore" disabled>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户预约权益</label>
                                                <div class="controls">
                                                    <select name="reservationRight" id="reservationRight" class="large m-wrap"  tabindex="1">
                                                        <option value="">--请选择客户权益--</option>
                                                        <c:forEach items="${rights}" var="right">
                                                            <option value="${right.id}">${right.rightsNum}、${right.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">预约供应商</label>
                                                <div class="controls">
                                                    <input type="text" name="provider" id="provider" readonly>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">供应商联系方式</label>
                                                <div class="controls">
                                                    <input type="text" name="providerPhone" id="providerPhone" readonly>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">兑换所需积分</label>
                                                <div class="controls">
                                                    <input type="text" name="exchangeScore" id="exchangeScore" readonly>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">预约须知</label>
                                                <div class="controls">
                                                    <input type="text" name="notice" id="notice" readonly>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">预订时间</label>
                                                <div class="controls">
                                                    <input type="text" name="markDate" id="markDate"  >
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <input name="id" type="hidden" value="${right.id}" />
                                                <button type="submit" id="submit_btn" class="btn blue"><i class="icon-ok"></i> 添加</button>
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
<script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/custom_datepicker/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/custom_datepicker/i18n/jquery-ui-timepicker-addon-i18n.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/custom_datepicker/jquery-ui-sliderAccess.js"></script>

<script>
    $(function(){

        $('#markDate').datetimepicker({
            timeFormat: "HH:mm",
            dateFormat: "yy-mm-dd"
        });

        $("#checkPhone").click(function () {
            var phoneNum = $("#phoneNum").val();
            if(phoneNum == null || phoneNum == ''){
                BootstrapDialog.show({
                    title: '提示',
                    message: '请输入手机号'
                });
                return false;
            }
                clearPhoneError();
                $.ajax({
                    url: "<%=contextPath%>/business/rights/check/phone",
                    type: "get",
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    data: {phoneNum:phoneNum},
                    success: function (data) {
                        if (data == null){
                            validatePhoneError('客户不存在');
                            $("#rightValid").val("0");
                            return;
                        }
                        if (data.customerName != null && data.customerName != ''){
                            $("#cname").val(data.customerName);
                        } else {
                            validatePhoneError('请确认输入的为客户的有效手机号');
                        }

                        if (data.levelId != null && data.levelId != ''){
                            $("#clevel").val(data.levelName);
                        } else {
                            validatePhoneError('请确认输入的为客户的有效手机号');
                        }

                        if (data.customerName != null && data.customerName != '' && data.levelId != null && data.levelId != ''){
                            $("#customerId").val(data.customerId);
                            $("#rightValid").val("1");
                        } else {
                            $("#rightValid").val("0");
                        }

                        $("#csore").val(data.availableScore);
                    },
                    error: function (err) {
                        validatePhoneError('请确认输入的为客户的有效手机号');
                    }
                });
        });

        $("#reservationRight").change(function () {
            var rightId = $("#reservationRight").val();
            if (rightId != null && rightId != ''){
                clearRightsError();
                $.ajax({
                    url: "<%=contextPath%>/business/rights/get/rightInfo",
                    type: "get",
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    data: {rightId:rightId},
                    success: function (data) {
                        $("#exchangeScore").val(data.score);
                        $("#provider").val(data.providerName);
                        $("#providerPhone").val(data.providerPhone);
                        $("#notice").val(data.notice);
                    },
                    error: function (err) {

                    }
                });
            }
        });

        $("#reservationTime").change(function () {
            clearDateError();
        });

    });

    function validatePhoneError(text) {
        clearPhoneError();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#phoneNum").parent().append(error);
    }

    function clearPhoneError() {
        if ($("#phoneNum").parent().find("p").size() > 0){
            $("#phoneNum").parent().find("p").remove();
        }
    }

    function validateRightsError(text) {
        clearRightsError();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#reservationRight").parent().append(error);
    }

    function clearRightsError() {
        if ($("#reservationRight").parent().find("p").size() > 0){
            $("#reservationRight").parent().find("p").remove();
        }
    }

    function validateDateError(text) {
        clearDateError();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#markDate").parent().append(error);
    }

    function clearDateError() {
        if ($("#markDate").parent().find("p").size() > 0){
            $("#markDate").parent().find("p").remove();
        }
    }

    function checkInputs() {
        if ($("#rightValid").val() == '0'){
            validatePhoneError('请确认输入的为客户的有效手机号');
            $("html,body").animate({scrollTop:0});
            return false;
        }

        if ($("#reservationRight").val() == ''){
            validateRightsError("请选择权益");
            return false;
        }

        if($('#csore').val() < $('#exchangeScore').val()){
            validateRightsError("您的积分不够");
            return false;
        }

       
     	var markDate = $("#markDate").val();
        if(markDate == null || markDate == ''){
        	validateDateError('请输入预订时间');
               return false;
        }else{
         	var str = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])(\\s+)(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})";
        	if(!(markDate.match(str))){
				validateDateError('请确认输入合格的时间格式');
                return false;
          	} 
        }
        $("#submit_btn").attr("disabled",true);
    }
</script>
