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
                        <li class="active"><a href="javascript:void(0);">积分审批</a></li>
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
                            <p>您确认要审批通过吗？</p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="url"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div>
                </div>
            </div>
       <!-- 审批确认 -->
            <div class="modal fade" id="approveFailedModel">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">提示信息</h4>
                        </div>
                        <div class="modal-body">
                            <p>您确认要打回审批吗？</p>
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
                            <p>您确认要审批通过选中记录吗？</p>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="batchUrl"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <a  onclick="batchUrlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" action="<%=contextPath%>/business/score/findpending" method="GET">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">客户姓名</label>
                        <input class="form-control" name="name" placeholder="输入客户姓名"  >

                        <label class="control-label" style="margin-left: 20px">审批状态</label>
                        <select class="form-control" name="isApprove" id="isApprove" >
                            <option value="0">待审批</option>
                            <option value="1">已审批</option>
                            <option value="2">审批失败</option>
                        </select>

                        <button type="submit" class="btn blue"><i class="icon-search"></i> 查询</button>
                    </div>
                </form>
            </div>


            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">

                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder">
                            </i></h4>
                            <button type="button" id="batchApprove" class="btn blue" onclick="batchApprove('<%=contextPath%>/business/score/batchApprove')" data-toggle="modal" data-target="#confirm-approve">批量审批</button>
                        </div>
                        <div class="portlet-body" style="height: 630px; overflow: scroll">
                            <table class="table table-striped table-bordered table-hover" id="score_table" >
                                <thead>
                                <tr>
                                    <td><input type="checkbox" id="checkAll" name="checkAll" />全选</td>
                                    <td>客户姓名</td>
                                    <td>积分</td>
                                    <td style="width:18%">产品名称</td>
                                    <td>产品类型</td>
                                    <td>购买金额</td>
                                    <td>购买期限</td>
                                    <td>有效时间</td>
                                    <td>审批状态</td>
                                    <td style="width:122px">操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${scores}" var="score">
                                    <tr>
                                        <td><input type="checkbox" name="subBox" value="${score.id}"/></td>
                                        <td>${score.customerName}</td>
                                        <td>${score.score}</td>
                                        <td>${score.productName}</td>
                                        <td>${score.productType}</td>
                                        <td>${score.amount}</td>
                                        <td>${score.period}</td>
                                        <td><fmt:formatDate value="${score.vaildTime}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${score.isApprove == 0}">
                                                    <span class="label label-warning">待审批</span>
                                                </c:when>
                                                <c:when test="${score.isApprove == 1}">
                                                    <span class="label label-success">已审批</span>
                                                </c:when>
                                                 <c:when test="${score.isApprove == 2}">
                                                    <span class="label label-success">审批失败</span>
                                                 </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                        <c:if test = "${score.isApprove == 0 }">
                                        <a href="javascript:void(0)" onclick="approveById('<%=contextPath%>/business/score/approve/${score.id}')" class="btn mini purple button_approve" data-toggle="modal" data-target="#confirm-delete"><i class="icon-edit"></i>审批</a>
                                        <a href="javascript:void(0)" onclick="approveFailedById('<%=contextPath%>/business/score/approveFailed/${score.id}')" class="btn mini purple button_approve" data-toggle="modal" data-target="#confirm-delete"><i class="icon-edit"></i>打回</a>
                                       </c:if>
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
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
    $(function(){

        var isApprove = '${isApprove}';
        $('#isApprove').val(isApprove);
        if(isApprove != '0'){
            $('#batchApprove').css("display", "none");
            $('.button_approve').css("display", "none");
        }

        $('#checkAll').click(function () {
            $('table tr input').prop('checked', $(this).is(":checked"));
            if ($(this).is(":checked")) {
                $('table tr input').closest('span').addClass('checked');
            } else {
                $('table tr input').closest('span').removeClass('checked');
            }
        });
    })

    function approveById(url){
        $('#url').val(url);//给会话中的隐藏属性URL赋值
        $('#approveModel').modal();
    }

    function approveFailedById(url){
        $('#url').val(url);//给会话中的隐藏属性URL赋值
        $('#approveFailedModel').modal();
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