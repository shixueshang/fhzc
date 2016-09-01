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
                            <a href="javascript:void(0);">产品管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">产品预约</a></li>
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
                                        <form id="res_form" name="res_form" action="<%=contextPath%>/business/product/reservationSave" id="form_sample_1" method="get" class="form-horizontal">
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
                                            <input type="hidden" name="customerId" id="customerId" value="0">
                                            <div class="control-group">
                                                <label class="control-label">产品名称</label>
                                                <div class="controls">
                                                    <input type="text" name="productName" id="productName" value="${product.name}" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">开放申购日</label>
                                                <div class="controls">
                                                    <input type="text" name="buyDate" id="buyDate" value="${product.buyDay}" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">接受的客户等级</label>
                                                <div class="controls">
                                                    <input type="text" name="allowedLevel" id="allowedLevel" value="${customer_level.key };${risk_level.key}" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">年化收益</label>
                                                <div class="controls">
                                                    <input type="text" name="annualYield" id="annualYield" value="${product.annualYield}" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">起投金额</label>
                                                <div class="controls">
                                                    <input type="text" name="investThreshold" id="investThreshold" value="${product.investThreshold}" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">用户手机号<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="phoneNum" id="phoneNum" data-required="1" placeholder="" class="m-wrap large">
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
                                                <label class="control-label">预约金额</label>
                                                <div class="controls">
                                                    <input type="number" name="reservationAmount" id="reservationAmount">
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">预约时间</label>
                                                <div class="controls">
                                                    <input class="form-control date-picker"  id="reservationTime" name="reservationTime" style="width: 180px">
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">理财师工号</label>
                                                <div class="controls">
                                                    <input class="form-control" id="workNum" name="workNum" style="width: 180px">
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <input name="productId" type="hidden" value="${product.pid}" />
                                                <button type="button" id="submit_btn" class="btn blue"><i class="icon-ok"></i> 预约</button>
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
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-error', form1);
        var success1 = $('.alert-success', form1);

        $("#checkPhone").click(function () {
            var phoneNum = $("#phoneNum").val();
            if (phoneNum != null && phoneNum != ''){
                clearPhoneError();
                $.ajax({
                    url: "<%=contextPath%>/business/rights/check/phone",
                    type: "get",
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    data: {phoneNum:phoneNum},
                    success: function (data) {
                        if (data == null){
                            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                            $("#userValid").val("0");
                            return;
                        }



                        if (data.name != null && data.name != ''){
                            $("#cname").val(data.name);
                        } else {
                            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                        }

                        if (data.customerLevel != null && data.customerLevel != ''){
                            $("#clevel").val(data.customerLevel);
                        } else {
                            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                        }

                        if (data.name != null && data.name != '' && data.customerLevel != null && data.customerLevel != ''){
                            $("#customerId").val(data.customerId);
                            $("#userValid").val("1");
                        } else {
                            $("#userValid").val("0");
                        }
                    },
                    error: function (err) {
                        validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                    }
                });
            }
        });

        $("#reservationTime").datepicker({
            dateFormat: "yy-mm-dd"
        });

        $("#reservationTime").change(function () {
            clearDateError();
        });

        $("#reservationAmount").focus(function () {
            clearAmountError();
        });

        $("#workNum").focus(function () {
            clearInputError('workNum');
        });

        $("#submit_btn").click(function () {
            if (checkInputs()){
                var workNum = $("#workNum").val();
                $.ajax({
                    url: "<%=contextPath%>/business/product/validateWorkNum",
                    type: "get",
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    data: {workNum:workNum},
                    success: function (data) {
                        if (data == true) {
                            //document.forms[0].submit();
                            $.ajax({
                                url: "<%=contextPath%>/business/product/reservationSave",
                                type: "get",
                                dataType: "json",
                                data: $("#res_form").serialize(),
                                success: function (data) {
                                    window.history.back();
                                }
                            });
                        } else {
                            validataInputError('workNum',"请输入有效的理财师工号");
                        }

                    },
                    error: function (err) {
                        validataInputError('workNum',"请输入有效的理财师工号");
                    }
                });
            }
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

    function validateAmountError(text) {
        clearDateError();
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>";
        $("#reservationAmount").parent().append(error);
    }

    function clearAmountError() {
        if ($("#reservationAmount").parent().find("p").size() > 0){
            $("#reservationAmount").parent().find("p").remove();
        }
    }

    function validataInputError(id, text) {
        clearInputError(id);
        var error =  "<p style='color:red; margin: 0'>"+text+"</p>"
        $("#"+id).parent().append(error);
    }

    function clearInputError(id) {
        if ($("#"+id).parent().find("p").size() > 0){
            $("#"+id).parent().find("p").remove();
        }
    }

    function checkInputs() {
        if ($("#userValid").val() == '0'){
            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
            $("html,body").animate({scrollTop:0});
            return false;
        }

        if ($("#reservationTime").val() == ''){
            validateDateError("请选择输入日期");
            return false;
        }

        if ($("#reservationAmount").val() == ''){
            validateAmountError("请输入预约金额");
            return false;
        }

        var ra = parseFloat($("#reservationAmount").val());
        if ($("#investThreshold").val() != ''){
            var leastA = parseFloat($("#investThreshold").val());
            if (ra < leastA){
                validateAmountError("预约金额应大于起投金额");
                return false;
            }
        }

        if ($("#workNum").val() == ''){
            validataInputError('workNum',"请输入理财师工号");
            return false;
        }

        return true;
        //$("#submit_btn").attr("disabled",true);
    }
</script>
