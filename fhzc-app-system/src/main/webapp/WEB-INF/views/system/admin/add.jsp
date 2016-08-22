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

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/zTree/css/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/zTree/css/demo.css">

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
                        <li class="active"><a href="javascript:void(0);" id="admin_title">管理员新增</a></li>
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
                                        <form action="<%=contextPath%>/system/admin/add" id="form_sample_1"  method="POST" class="form-horizontal">
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
                                                <label class="control-label">用户名<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="login" id="login"  data-required="1" placeholder="请填写登录名" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">用户密码</label>
                                                <div class="controls">
                                                    <input type="password" name="password" id="password"  placeholder="请填写密码" class="large m-wrap" >
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">用户真实姓名</label>
                                                <div class="controls">
                                                    <input type="text" name="realname" id="realname" placeholder="请填写真实姓名" class="large m-wrap" >
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>


                                            <div class="control-group">
                                                <label class="control-label">所属角色</label>
                                                <div class="controls">
                                                    <select name="role" id="belongRole" class="large m-wrap"  tabindex="1">
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">所属机构</label>
                                                <div class="controls">
                                                    <input type="text" id="department" onclick="showTreeData(); return false;" class="large m-wrap"/>
                                                    <input type="hidden" name="organ" id="department_value" />
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">所属地区</label>
                                                <div class="controls">
                                                    <select name="area" id="areas"  class="large m-wrap"  tabindex="1">
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">手机号</label>
                                                <div class="controls">
                                                    <input type="text" name="mobile" id="mobile" placeholder="请填写手机号" class="large m-wrap" >
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">状态</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="status" value="1" checked />
                                                        正常
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="status" value="0"  />
                                                        禁用
                                                    </label>
                                                </div>
                                            </div>


                                            <div class="form-actions">
                                                <input name="id" type="hidden" id="adminId"/>
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
                    <div id="treeContent" class="treeContent" style="display:none; position: absolute;">
                        <ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/zTree/js/tree.js"></script>
<script>
    $(function(){

        var adminId =  '${adminUser.id}';
        if(adminId != null && adminId != ''){
            $('#admin_title').text('编辑管理员');
            $('#adminId').val(adminId);
            $('#login').val('${adminUser.login}');
            $('#password').val('${adminUser.password}');
            $('#realname').val('${adminUser.realname}');
            $('#mobile').val('${adminUser.mobile}');
        }

        var role = '${adminUser.role}';
        var roles = '${roles}';
        var rolesJson= $.parseJSON(roles);
        $.each(rolesJson, function(i,val){
            $("#belongRole").append("<option value='"+val.roleId+"'>"+val.roleName+"</option>");
            if(role == val.roleId){
                $("#belongRole").val(role);
            }
        });

        var areasVal = '${adminUser.area}';
        var areas = '${areas}';
        var areasJson= $.parseJSON(areas);
        $.each(areasJson, function(i,val){
            $("#areas").append("<option value='"+val.areaId+"'>"+val.areaName+"</option>");
            if(areasVal == val.areaId){
                $("#areas").val(areasVal);
            }
        });

        var organVal = '${adminUser.organ}';
        var treeNodes = '${departments}';
        treeNodes = $.parseJSON(treeNodes);
        setOrganValue(organVal, treeNodes);
        $.fn.zTree.init($("#treeDemo"), setting, treeNodes);

        var status = '${adminUser.status}';
        if(status == 1 || status == null || status == ""){
            $.uniform.update($("input[name='status'][value='1']").attr("checked", true));
            $.uniform.update($("input[name='status'][value='0']").attr("checked", false));
        }else{
            $.uniform.update($("input[name='status'][value='1']").attr("checked", false));
            $.uniform.update($("input[name='status'][value='0']").attr("checked", true));
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
