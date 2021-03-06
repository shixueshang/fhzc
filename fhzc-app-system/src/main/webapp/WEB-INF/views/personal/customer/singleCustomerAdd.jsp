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
                            <a href="javascript:void(0);">客户管理管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);" id="report_title">编辑个人客户</a></li>
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
                                        <form action="<%=contextPath%>/personal/customer/single/update" id="form_sample_1"  method="POST" class="form-horizontal">
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
                                                <label class="control-label">客户编号</label>
                                                <div class="controls">
                                                    <input type="text" name="cbId" value="${customer.cbId}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">客户姓名</label>
                                                <div class="controls">
                                                    <input type="text" name="realname" value="${user.realname}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户登陆名</label>
                                                <div class="controls">
                                                    <input type="text" name="login" value="${user.login}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">手机号码</label>
                                                <div class="controls">
                                                    <input type="text" name="mobile" value="${user.mobile}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">固定电话</label>
                                                <div class="controls">
                                                    <input type="text" name="phone" value="${user.phone}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">性别</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="gender" value="male" checked/>
                                                        男
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="gender" value="female"  />
                                                        女
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">出生日期</label>
                                                <div class="controls">
                                                    <input type="text" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>" data-required="1" placeholder="" class="m-wrap m-ctrl-medium date-picker">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">证件类型</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="passportTypeId" id="passportTypeId" tabindex="1"></select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">证件号码</label>
                                                <div class="controls">
                                                    <input type="text" name="passportCode" value="${user.passportCode}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">会员等级</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="levelId" id="levelId" tabindex="1"></select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">可用积分</label>
                                                <div class="controls">
                                                    <input type="text" name="availableScore" value="${availableScore}" readonly data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">冻结积分</label>
                                                <div class="controls">
                                                    <input type="text" name="frozenScore" value="${frozenScore}" readonly data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
      										<div class="control-group">
                                                <label class="control-label">风险等级</label>
                                                <div class="controls">
                                                    <select name="risk" id="risk" class="large m-wrap"  tabindex="1">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">理财师工号</label>
                                                <div class="controls">
                                                    <input type="text" name="plannerNum" value="${planner.workNum}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">理财师姓名</label>
                                                <div class="controls">
                                                    <input type="text" name="plannerName" value="${plannerUser.realname}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <input name="customerId" type="hidden" value="${customer.customerId}" />
                                                <input name="uid" type="hidden" value="${user.uid}" />
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

<script>
    $(function(){

        var passportType = '${user.passportTypeId}';
        var passports = '${passports}';
        var typeJson= $.parseJSON(passports);
        $.each(typeJson, function(i,val){
            $("#passportTypeId").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(passportType == val.value){
                $("#passportTypeId").val(passportType);
            }
        });

        var level = '${customer.levelId}';
        var customerLevels = '${customerLevels}';
        var levelJson= $.parseJSON(customerLevels);
        $.each(levelJson, function(i,val){
            $("#levelId").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(level == val.value){
                $("#levelId").val(level);
            }
        });
        
        var level = '${customer.risk}';
        var riskLevels = '${riskLevels}';
        var levelJson= $.parseJSON(riskLevels);
        $.each(levelJson, function(i,val){
            $("#risk").append("<option value='"+val.value+"'>"+val.key+"</option>");
            if(level == val.value){
                $("#risk").val(level);
            }
        });


        var gender = '${user.gender}';
        if(gender == 'male' || gender == '' || gender == null){
            $.uniform.update($("input[name='gender'][value='male']").attr("checked", true));
            $.uniform.update($("input[name='gender'][value='female']").attr("checked", false));
        }else{
            $.uniform.update($("input[name='gender'][value='male']").attr("checked", false));
            $.uniform.update($("input[name='gender'][value='female']").attr("checked", true));
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