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
<link type="text/css" href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-ui/jquery-ui-1.10.1.custom.css">
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
                        <li class="active"><a href="javascript:void(0);">预约列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form name="searchForm" class="form-inline" action="/business/rights/find">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">权益名称:</label>
                        <input class="form-control" id="rightsName" name="rightsName" value="${param.rightsName}">
                        <label class="col-sm-2 control-label">预约时间:</label>
                        <input class="form-control" id="startTime" name="startTime" placeholder="yyyy-MM-dd" style="width: 180px" value="${param.startTime}">
                        ~
                        <input class="form-control" id="endTime" name="endTime" placeholder="yyyy-MM-dd" style="width: 180px" value="${param.endTime}">
                        <button type="submit">查找</button>
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
                            <table id="example" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>预约时间</td>
                                    <td style="width:16%">权益名称</td>
                                    <td style="width:16%">供应商</td>
                                    <td>供应商联系方式</td>
                                    <td>客户姓名</td>
                                    <td>客户电话</td>
                                    <td>消费时间</td>
                                    <td>消耗积分</td>
                                    <td>预约状态</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${reservations}" var="reservation">
                                    <tr>
                                        <td><fmt:formatDate value="${reservation.ctime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${reservation.rightName}</td>
                                        <td>${reservation.supply}</td>
                                        <td>${reservation.supplyPhone}</td>
                                        <td>${reservation.customerName}</td>
                                        <td>${reservation.customerMobile}</td>
                                        <td><fmt:formatDate value="${reservation.markDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>${reservation.scoreCost}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${reservation.status == '0'}">
                                                    <span class="label">预约中</span>
                                                </c:when>
                                                <c:when test="${reservation.status == '1'}">
                                                    <span class="label label-success">预约成功</span>
                                                </c:when>
                                                <c:when test="${reservation.status == '2'}">
                                                    <span class="label label-important">预约失败</span>
                                                </c:when>
                                                <c:when test="${reservation.status == '3'}">
                                                    <span class="label label-warning">客户取消预约</span>
                                                </c:when>
                                                <c:when test="${reservation.status == '4'}">
                                                    <span class="label label-warning">客户消费</span>
                                                </c:when>
                                                <c:when test="${reservation.status == '5'}">
                                                    <span class="label label-warning">客户缺席</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td><a href="#modal_edit" role="button" class="btn mini purple deal_reser" data-toggle="modal" data-id="${reservation.id}"><i class="icon-edit"></i>处理预约</a></td>
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
                    <h3 id="myModalLabel2">处理预约</h3>
                </div>
                <div class="modal-body" style="height: 120px;">
                    <p><select  id="reser_status" value="" class="m-wrap large">

                    </select></p>
                    <input type="hidden"  id="reser_id" />
                    <input type="hidden"  id="status" />
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn blue" id="do_mod_reser">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
        </div>

        <jsp:include page="../../include/page.jsp"/>
    </div>

</div>

<script type="text/javascript">
$(document).ready(function () {
    $(".deal_reser").click(function () {
        $('#reser_id').val($(this).data("id"));
        $('#reser_status').empty();
        $('#reser_status').append("<option value=''>--请选择状态--</option>");
        $.ajax({
            url: '<%=contextPath%>/business/rights/reservation/status',
            type: 'GET',
            success: function(result) {
                $.each(result.children, function (i, val) {
                    $("#reser_status").append("<option value='" + val.value + "'>" + val.key + "</option>");
                });
            },
            error: function(xhr, textStatus, errorThrown){
            }
        });
    });

    $("#reser_status").change(function () {
        $('#status').val($('#reser_status').val());
    });

    $("#do_mod_reser").click(function () {
        var status = $("#reser_status").val();
        if (status == null || status == '') {
            BootstrapDialog.alert({
                title: '提示',
                message: '请选择状态'
            });
            return false;
        }
        $.post("<%=contextPath%>/business/rights/reservation/deal", {
            'reserId': $("#reser_id").val(),
            'status': status
        }, function (data) {
            window.location.reload();
        })
    });
});
</script>
<script type="text/javascript" charset="utf-8">
        	
        	
            /*$('#example').DataTable({
            	"aoColumnDefs": [ { "bSortable": false, "aTargets": [2,3,4,5,6,7,8 ]}] ,
            	"bAutoWidth" : false,
            	"bFilter": false, //过滤功能
                "oLanguage": {
                        "sProcessing":   "处理中...",
                        "sLengthMenu":   "_MENU_ 记录/页",
                        "sZeroRecords":  "没有匹配的记录",
                        "sInfo":         "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                        "sInfoEmpty":    "显示第 0 至 0 项记录，共 0 项",
                        "sInfoFiltered": "(由 _MAX_ 项记录过滤)",
                        "sInfoPostFix":  "",
                        "sSearch":       "搜索:",
                        "sUrl":          "",
                        "oPaginate": {
                            "sFirst":    "首页",
                            "sPrevious": "上页",
                            "sNext":     "下页",
                            "sLast":     "末页"
                        }
                    }
                
            });*/
            

</script>  

<jsp:include page="../../include/footer.jsp"/>
