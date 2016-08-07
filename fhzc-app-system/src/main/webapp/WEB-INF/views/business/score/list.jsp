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

        <!-- 审批结果提示 -->
        <div class="alert alert-success" id="approve_success" role="alert" style="display: none">成功</div>
        <div class="alert alert-warning" id="approve_fail" role="alert" style="display: none">失败</div>


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
                            <a href="javascript:void(0);">积分管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">积分列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <!-- 审批确认 -->
            <div class="modal fade" id="approveModel">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">提示信息</h4>
                        </div>
                        <div class="modal-body">
                            <p>您确认要审批吗？</p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="url"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 批量审批确认 -->
            <div class="modal fade" id="batchApproveModel">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">提示信息</h4>
                        </div>
                        <div class="modal-body">
                            <p>您确认要审批选中记录吗？</p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="batchUrl"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a  onclick="batchUrlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div>
                </div>
            </div>


            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">

                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder">
                            </i></h4>
                            <button type="button" class="btn blue" onclick="batchApprove('<%=contextPath%>/business/score/batchApprove')" data-toggle="modal" data-target="#confirm-approve">批量审批</button>
                        </div>
                        <div class="portlet-body" style="height: 630px; overflow: scroll">
                            <table class="table table-striped table-bordered table-hover" id="score_table">
                                <thead>
                                <tr>
                                    <td><input type="checkbox" id="checkAll" class="group-checkable" data-set="#score_table.checkboxes" />全选</td>
                                    <td>积分</td>
                                    <td>积分状态</td>
                                    <td>描述</td>
                                    <td>积分变动来源</td>
                                    <td>有效时间</td>
                                    <td>审批状态</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${scores}" var="score">
                                    <tr>
                                        <td><input type="checkbox" name="subBox" class="checkboxes" value="${score.id}"/></td>
                                        <td>${score.score}</td>
                                        <td>
                                            <c:forEach items="${scoreStatus}" var="scoreStat">
                                                <c:if test="${score.status == scoreStat.value}">
                                                    ${scoreStat.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${score.detail}</td>
                                        <td>
                                            <c:forEach items="${fromTypes}" var="fromType">
                                                <c:if test="${score.fromType == fromType.value}">
                                                    ${fromType.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td><fmt:formatDate value="${score.vaildTime}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${score.isApprove == 0}">
                                                    <span class="label label-warning">待审批</span>
                                                </c:when>
                                                <c:when test="${score.isApprove == 0}">
                                                    <span class="label label-warning">已审批</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td><a href="javascript:void(0)" onclick="approveById('<%=contextPath%>/business/score/approve/${score.id}')" class="btn mini purple" data-toggle="modal" data-target="#confirm-delete"><i class="icon-edit"></i>审批</a></td>
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

<script>
    $(function(){

      /*  //全选/全不选
        $("#checkAll").click(function() {
            //$("input[name='subBox']:checkbox").prop("checked", this.checked);
            $("#uniform-undefined").find("span").addClass("checked");
        });
        var $subBox = $("input[name='subBox']");
        $subBox.click(function(){
            $("#checkAll").prop("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
        });
*/
    })

    function approveById(url){
        $('#url').val(url);//给会话中的隐藏属性URL赋值
        $('#approveModel').modal();
    }

    function urlSubmit(){
        var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
        $.ajax({
            url: url,
            type: 'GET',
            success: function(result) {
                if(result){
                    $("#approve_success").css("display", "block").hide(3000);
                    window.location.reload();
                }
            },
            error: function(xhr, textStatus, errorThrown){
                $("#approve_fail").css("display", "block").hide(3000);
            }
        });
    }

    function batchApprove(batchUrl){

        var arr = new Array();
        $("input[name='subBox']:checked").each(function(){
            arr.push($(this).val());
        });

        if(arr.length == 0){
            BootstrapDialog.show({
                title: '提示',
                message: '请选择要审批的记录!'
            });

            return false;
        }

        $('#batchUrl').val(batchUrl);//给会话中的隐藏属性URL赋值
        $('#batchApproveModel').modal();
    }

    function batchUrlSubmit(){
        var url = $.trim($("#batchUrl").val());//获取会话中的隐藏属性URL

        var arr = new Array();
        $("input[name='subBox']:checked").each(function(){
            arr.push($(this).val());
        });

        $.ajax({
            url: url,
            type: 'GET',
            data: {"ids" : arr},
            dataType: "json",
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            success: function(result) {
                if(result){
                    $("#approve_success").css("display", "block").hide(3000);
                    window.location.reload();
                }
            },
            error: function(xhr, textStatus, errorThrown){
                $("#approve_fail").css("display", "block").hide(3000);
            }
        });
    }
</script>


<jsp:include page="../../include/footer.jsp"/>