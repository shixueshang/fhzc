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
                            <a href="javascript:void(0);">权益管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">新增权益预约</a></li>
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
                                        <form action="<%=contextPath%>/business/rights/addReservation" id="form_sample_1" method="POST" class="form-horizontal">
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
                                                            <option value="${right.id}">${right.name}</option>
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
                                                    <input type="text" name="exchangeScore" id="exchangeScore" data-required="1" placeholder="" class="m-wrap large" disabled>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">预约时间</label>
                                                <div class="controls">
                                                    <input class="form-control" id="reservationTime" name="startTime" style="width: 180px">
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <input name="id" type="hidden" value="${right.id}" />
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
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-error', form1);
        var success1 = $('.alert-success', form1);

        form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-inline', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            /*rules: {
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
            },*/

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

        $("#checkPhone").click(function () {
            var phoneNum = $("#phoneNum").val();
            if (phoneNum != null && phoneNum != ''){
                $.ajax({
                    url: "<%=contextPath%>/business/rights/check/phone",
                    type: "get",
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    data: {phoneNum:phoneNum},
                    success: function (data) {
                        if (data == null){
                            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                            $("#rightValid").val("0");
                            return;
                        }



                        if (data.name != null && data.name != ''){
                            $("#cname").val(data.name);
                        } else {
                            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                        }

                        if (data.clevel != null && data.clevel != ''){
                            $("#clevel").val(data.customerLevel);
                        } else {
                            validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                        }

                        if (data.name != null && data.name != '' && data.clevel != null && data.clevel != ''){
                            $("#rightValid").val("1");
                        } else {
                            $("#rightValid").val("0");
                        }

                        $("#csore").val(data.availableScore);
                    },
                    error: function (err) {
                        validatePhoneError('请确认收入的手机号为有效客户的有效手机号');
                    }
                });
            }
        });

        $("#reservationRight").change(function () {
            var rightId = $("#reservationRight").val();
            if (rightId != null && rightId != ''){
                $.ajax({
                    url: "<%=contextPath%>/business/rights/get/rightInfo",
                    type: "get",
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    data: {rightId:rightId},
                    success: function (data) {
                        $("#exchangeScore").val(data.score);
                    },
                    error: function (err) {

                    }
                });
            }
        });
    })

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
</script>
