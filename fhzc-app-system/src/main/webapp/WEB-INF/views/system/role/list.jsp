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

        <div class="alert alert-success" id="delete_success" role="alert" style="display: none">成功</div>
        <div class="alert alert-warning" id="delete_fail" role="alert" style="display: none">失败</div>


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
                            <a href="javascript:void(0);">角色管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">角色列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="modal fade" id="deleteModel">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">提示信息</h4>
                        </div>
                        <div class="modal-body">
                            <p>您确认要删除吗？</p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="url"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a  onclick="urlDelete()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div>
                </div>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">

                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder"></i></h4>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>角色代码</td>
                                    <td>角色名称</td>
                                    <td>描述</td>
                                    <td>状态</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${roles}" var="role">
                                    <tr>
                                        <td>${role.roleCode}</td>
                                        <td>${role.roleName}</td>
                                        <td>${role.description}</td>
                                        <td>
                                            <c:choose>
                                            <c:when test="${role.status == '0'}" >
                                                正常
                                            </c:when>
                                            <c:when test="${role.status == '1'}" >
                                                禁用
                                            </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="<%=contextPath%>/system/role/detail/${role.roleId}" class="btn mini purple"><i class="icon-edit"></i>编辑</a>
                                            <a href="javascript:void(0)" onclick="deleteById('<%=contextPath%>/system/role/delete/${role.roleId}')" class="btn mini purple"><i class="icon-trash"></i>删除</a>
                                            <a href="<%=contextPath%>/system/role/authorization/${role.roleId}" class="btn mini purple"><i class="icon-tag"></i>分配权限</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

<script>

    function deleteById(url){
        $('#url').val(url);
        $('#deleteModel').modal();
    }

    function urlDelete(){
        var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
        $.ajax({
            url: url,
            type: 'GET',
            success: function(result) {
                if(result){
                    $("#delete_success").css("display", "block").hide(3000);
                    window.location.reload();
                }
            },
            error: function(xhr, textStatus, errorThrown){
                $("#delete_fail").css("display", "block").hide(3000);
            }
        });
    }

</script>