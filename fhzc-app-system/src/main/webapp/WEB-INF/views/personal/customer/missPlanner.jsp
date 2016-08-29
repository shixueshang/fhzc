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
                            <a href="javascript:void(0);">客户管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">缺位管理</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" action="/personal/customer/find/missPlanner" method="GET">
                    <div class="form-group">
                        <input class="form-control" id="name" placeholder="输入客户姓名" name="name" >

                        <button type="submit" class="btn blue"><i class="icon-search"></i> 查询</button>
                    </div>
                </form>
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
                                    <td>客户编号</td>
                                    <td>会员等级</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${customers}" var="customer">
                                    <tr>
                                        <td>${customer.cbId}</td>
                                        <td>
                                            <c:forEach items="${users}" var="user">
                                                <c:if test="${customer.uid == user.uid}">
                                                    <c:forEach items="${customerLevel}" var="level">
                                                        <c:if test="${customer.levelId == level.value}">
                                                            ${level.key}
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </td>

                                        <td><a href="#modal_edit" role="button" class="btn mini purple mod_planner" data-toggle="modal" data-id="${customer.customerId}"><i class="icon-edit"></i>指定理财师</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->
            <div id="modal_edit" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="my_modal_edit" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3 id="myModalLabel2">指定理财师</h3>
                </div>
                <div class="modal-body">
                    <p><select  id="change_planner" value="" class="m-wrap large">

                    </select></p>
                    <input type="hidden"  id="customer_id" />
                    <input type="hidden"  id="planner_id" />
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn blue" id="do_mod_planner">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

<script>
    $(function() {
        $(".mod_planner").click(function () {
            $('#customer_id').val($(this).data('id'));
            $('#change_planner').empty();
            $('#change_planner').append("<option value=''>--请选择理财师--</option>");
            $.ajax({
                url: "<%=contextPath%>/personal/planner/getPlanner",
                type: "get",
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                    $.each(data.children, function (i, val) {
                        $("#change_planner").append("<option value='" + val.id + "'>" + val.plannerName + "</option>");
                    });
                },
                error: function (err) {

                }
            });

        });

        $("#change_planner").change(function () {
            $('#planner_id').val($('#change_planner').val());
        });

        $("#do_mod_planner").click(function () {
            var plannerId = $("#planner_id").val();
            if (plannerId == null || plannerId == '') {
                BootstrapDialog.alert({
                    title: '提示',
                    message: '请选择理财师'
                });
                return false;
            }
            $.post("<%=contextPath%>/personal/customer/assignPlanner", {
                'customerId': $("#customer_id").val(),
                'plannerId': plannerId
            }, function (data) {
                window.location.reload();
            })
        });

    });
</script>