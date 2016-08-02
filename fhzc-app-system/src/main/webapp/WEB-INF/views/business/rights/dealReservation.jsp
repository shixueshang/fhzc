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
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-ui/jquery-ui-1.10.1.custom.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/custom_datepicker/jquery-ui-timepicker-addon.css">
<script src="<%=contextPath%>/assets/jquery-ui/jquery-ui-1.10.1.custom.min.js"></script>
<script src="<%=contextPath%>/assets/custom_datepicker/jquery-ui-timepicker-addon.js"></script>


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
                        <li class="active"><a href="javascript:void(0);">处理预约</a></li>
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
                                                <label class="control-label">客户名称</label>
                                                <div class="controls">
                                                    <input type="text" name="cname" id="cname" value="${customer.name}" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户等级</label>
                                                <div class="controls">
                                                    <input type="text" name="clevel" id="clevel" value="${customer.customerLevel}" disabled>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户可用积分</label>
                                                <div class="controls">
                                                    <input type="text" name="csore" id="csore" value="${customer.availableScore}" disabled>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户预约权益</label>
                                                <div class="controls">
                                                    <select name="reservationRight" id="reservationRight" class="large m-wrap"  tabindex="1">
                                                        <c:forEach items="${rights}" var="right">
                                                            <option value="${right.id}">${right.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">预约供应商</label>
                                                <div class="controls">
                                                    <input type="text" name="provider" id="provider" value="${providerInfo.providerName}" readonly>
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
                                                    <input type="text" name="exchangeScore" id="exchangeScore" value="${providerInfo.score}" readonly>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">积分状态</label>
                                                <div class="controls">
                                                    <input type="text" name="scoreStatus" id="scoreStatus" value="冻结中" readonly>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">预约状态</label>
                                                <div class="controls">
                                                    <select name="reservationStatus" id="reservationStatus" class="large m-wrap"  tabindex="1">
                                                            <option value="0">预约中</option>
                                                            <option value="1">预约成功</option>
                                                            <option value="2">预约失败</option>
                                                            <option value="3">客户取消预约</option>
                                                            <option value="4">客户消费</option>
                                                            <option value="5">客户缺席</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">预约时间</label>
                                                <div class="controls">
                                                    <input class="form-control" id="reservationTime" name="reservationTime" value="${reservation.markDate}" style="width: 180px">
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <input name="id" type="hidden" value="${reservation.id}" />
                                                <button type="submit" id="submit_btn" class="btn blue"><i class="icon-ok"></i> 保存</button>
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

<script>
    $(function(){
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-error', form1);
        var success1 = $('.alert-success', form1);

        /*form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-inline', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            /!*rules: {
                name: {
                    required: true
                },
                url: {
                    url: true
                },
                spendScore: {
                    number: true,
                    min:0
                }
            },*!/

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
        });*/

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
                    },
                    error: function (err) {

                    }
                });
            }
        });

        $("#reservationTime").datetimepicker({
            timeFormat: 'HH:mm:ss',
            dateFormat: "yy-mm-dd"
        });

        $("#reservationTime").change(function () {
            clearDateError();
        });

        $("#reservationRight").val('${reservation.rightsId}');
        $("#reservationStatus").val('${reservation.status}');
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
        $("#reservationTime").parent().append(error);
    }

    function clearDateError() {
        if ($("#reservationTime").parent().find("p").size() > 0){
            $("#reservationTime").parent().find("p").remove();
        }
    }
    
    function checkInputs() {
        if ($("#rightValid").val() == '0'){
            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
            $("html,body").animate({scrollTop:0});
            return false;
        }

        if ($("#reservationRight").val() == ''){
            validateRightsError("请选择权益");
            return false;
        }

        if ($("#reservationTime").val() == ''){
            validateDateError("请选择输入日期");
            return false;
        }

        $("#submit_btn").attr("disabled",true);
    }
</script>
