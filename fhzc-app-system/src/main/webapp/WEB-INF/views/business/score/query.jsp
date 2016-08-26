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
                            <a href="javascript:void(0);">积分管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">积分查询</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>



            <div class="row-fluid">
                <form  class="form-inline" action="<%=contextPath%>/business/score/query" method="GET">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">客户姓名</label>
                        <input class="form-control" name="name" placeholder="输入客户姓名"  >

                        <button type="submit">查询</button>
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
                        </div>
                        <div class="portlet-body" style="height: 630px; overflow: scroll">
                            <table class="table table-striped table-bordered table-hover" id="score_table" >
                                <thead>
                                <tr>
                                    <td>客户姓名</td>
                                    <td>可用积分</td>
                                    <td>冻结积分</td>
                                    <td>即将过期积分</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${customers}" var="customer">
                                    <tr>
                                        <td>${customer.customerName}</td>
                                        <td></td>
                                        <td>

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

<script>
    $(function(){

        var isApprove = '${isApprove}';
        $('#isApprove').val(isApprove);
        if(isApprove == 1){
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