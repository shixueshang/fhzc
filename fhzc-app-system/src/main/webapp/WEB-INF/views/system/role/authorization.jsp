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
                            <a href="javascript:void(0);">权限管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">分配权限</a></li>
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
                                        <form  id="form_sample" class="form-horizontal">

                                        <c:forEach items="${resources}" var="resource">
                                            <c:if test="${resource.level != 3}">

                                            <div class="control-group padding_${resource.level_px}">

                                                <c:choose>
                                                    <c:when test="${resource.level == 1}">
                                                        <div class="controls">
                                                            <label class="checkbox">
                                                                <div class="checker">
                                                                    <c:choose>
                                                                        <c:when test="${resource.checkbox == 'checked'}">
                                                                            <span class="checked">
                                                                            <input type="checkbox" checked="checked" name="resource" value="${resource.id}" data-pid="${resource.parent_id}" data-id="${resource.id}" data-level="${resource.parent_id}_${resource.level}" data-l="${resource.level}" style="opacity: 0;">
                                                                            </span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span>
                                                                            <input type="checkbox" name="resource" value="${resource.id}" data-pid="${resource.parent_id}" data-id="${resource.id}" data-level="${resource.parent_id}_${resource.level}" data-l="${resource.level}" style="opacity: 0;">
                                                                            </span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                                <span class="label label-info label-mini">${resource.name}</span>
                                                            </label>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${resource.level == 2}" >
                                                        <div class="controls">
                                                            <label class="checkbox">
                                                                <div class="checker">
                                                                    <c:choose>
                                                                    <c:when test="${resource.checkbox == 'checked'}">
                                                                            <span class="checked">
                                                                            <input type="checkbox" checked="checked" name="resource" value="${resource.id}" data-pid="${resource.parent_id}" data-id="${resource.id}" data-level="${resource.parent_id}_${resource.level}" data-l="${resource.level}" style="opacity: 0;">
                                                                            </span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                            <span>
                                                                            <input type="checkbox" name="resource" value="${resource.id}" data-pid="${resource.parent_id}" data-id="${resource.id}" data-level="${resource.parent_id}_${resource.level}" data-l="${resource.level}" style="opacity: 0;">
                                                                            </span>
                                                                    </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                                <span class="label label-info label-mini">${resource.name}</span>
                                                            </label>
                                                        </div>

                                                        <!-- 3级资源 -->
                                                        <div class="controls padding_20">
                                                            <c:forEach items="${children}" var="child" >
                                                                <c:choose>
                                                                    <c:when test="${child.parent_id == resource.id}">
                                                                        <label class="checkbox">
                                                                        <div class="checker">
                                                                            <c:choose>
                                                                            <c:when test="${child.checkbox == 'checked'}">
                                                                                <span class="checked">
                                                                                <input type="checkbox" checked="checked" name="resource" value="${child.id}" data-pid="${child.parent_id}" data-id="${child.id}" data-level="${child.parent_id}_${child.level}" data-l="${child.level}" style="opacity: 0;">
                                                                                </span>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <span>
                                                                                <input type="checkbox" name="resource" value="${child.id}" data-pid="${child.parent_id}" data-id="${child.id}" data-level="${child.parent_id}_${child.level}" data-l="${child.level}" style="opacity: 0;">
                                                                                </span>
                                                                            </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <span class="msg-dark-blue" style="font-size: 13px;">${child.name}</span>
                                                                        </label>
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                            </c:if>
                                            
                                        </c:forEach>

                                            <div class="form-actions">
                                                <button  onclick="submitForm()" class="btn blue"><i class="icon-ok"></i> 添加</button>
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
        </div>
    </div>
</div>

<script>

    jQuery(document).ready(function () {

        /**
         * 操作checkbox
         * @param {type} obj
         * @param {type} status
         * @returns {undefined}
         */
        function checkHandler(obj, status) {
            if (status) {
                obj.closest('span').addClass('checked');
                obj.prop('checked', true);
            } else {
                obj.closest('span').removeClass('checked');
                obj.prop('checked', false);
            }
        }

        /**
         * 子元素选中
         * @param {type} id
         * @param {type} level
         * @param {type} status
         * @returns {undefined}
         */
        function checkChildHandle(id, level, status) {
            var childObj = $('input[data-level="' + id + '_' + level + '"]'), c_childObj = [];
            $.each(childObj, function (i, v) {
                var c_id = $(v).data('id'), c_s_level = parseInt($(v).data('l')) + 1 * 1;
                c_childObj[i] = $('input[data-level="' + c_id + '_' + c_s_level + '"]');
                checkHandler($(v), status);
                checkHandler(c_childObj[i], status);
            });
        }

        $('input[type="checkbox"]').click(function () {
            var _this = $(this), pid = _this.data('pid'), id = _this.data('id'), s_level = parseInt(_this.data('l')) + 1 * 1, level = _this.data('level'), parentObj = $('input[data-id="' + pid + '"]');
            var p_pid = parentObj.data('pid'), p_level = parentObj.data('level'), p_parentObj = $('input[data-id="' + p_pid + '"]');
            var broLength = $('input[data-level="' + level + '"]:checked').length;

            if (_this.is(":checked")) {
                checkHandler(parentObj, true);
                checkHandler(p_parentObj, true);
                checkChildHandle(id, s_level, true);

            } else {
                (broLength <= 0) ? checkHandler(parentObj, false) : "";

                var pBroLength = $('input[data-level="' + p_level + '"]:checked').length;
                (pBroLength <= 0) ? checkHandler(p_parentObj, false) : "";

                checkChildHandle(id, s_level, false);
            }
        });

    });

    function submitForm(){

        if($('input[type="checkbox"]:checked').length == 0){
            showMsg("请至少选中一个权限", false);
            return false;
        }

        var roleModules = new Array();
        var roleModule = {};

        $('input[type="checkbox"]:checked').each(function(){
            roleModule.adminRoleId = '${role.roleId}';
            roleModule.moduleId = $(this).val();
            roleModules.push(roleModule);
        });

        console.info(roleModules);

        $.ajax({
            url:"<%=contextPath%>/system/role/authorization/confirm",
            type:"POST",
            data:JSON.stringify(roleModules),
            dataType:"json",
            contentType:"application/json",
            success:function(data){

            },error:function(data){

            }
        });
    }

</script>

<jsp:include page="../../include/footer.jsp"/>