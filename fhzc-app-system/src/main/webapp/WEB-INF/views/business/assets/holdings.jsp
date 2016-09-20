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
                            <a href="javascript:void(0);">资产管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">资产持仓</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" action="<%=contextPath%>/business/assets/holdings/find" method="GET">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">客户姓名</label>
                        <input class="form-control" name="name" placeholder="输入客户姓名"  >

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
                        </div>
                        <div class="portlet-body" style="height: 630px; overflow: scroll">
                            <table class="table table-striped table-bordered table-hover" >
                                <thead>
                                <tr>
                                    <td>客户姓名</td>
                                    <td>身份证号</td>
                                    <td>手机号</td>
                                    <td>会员等级</td>
                                    <td>客户积分</td>
                                    <td>积分详情</td>
                                    <td>所属理财师</td>
                                    <td>所属部门</td>
                                    <td style="width:174px">操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${customers}" var="customer">
                                    <tr>
                                        <td>${customer.customerName}</td>
                                        <td>${customer.identity}</td>
                                        <td>${customer.mobile}</td>
                                        <td>${customer.level}</td>
                                        <td>${customer.score}</td>
                                        <td><a style="cursor: pointer" href="javascript:void(0)" onclick="viewScoreDetail('${customer.customerName}')">查看</a></td>
                                        <td>${customer.planner}</td>
                                        <td>${customer.department}</td>
                                        <td>
                                            <a href="#modal_current" role="button" class="btn mini purple current_holding" data-toggle="modal" data-id="${customer.customerId}" ><i class="icon-edit"></i> 当前持仓</a>
                                            <a href="#modal_history" role="button" class="btn mini purple history_holding" data-toggle="modal" data-id="${customer.customerId}" ><i class="icon-edit"></i> 历史持仓</a>

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
            <div id="modal_current" class="modal hide fade" style="width: 1020px;height: 500px;margin-left:-400px;" tabindex="-1" role="dialog" aria-labelledby="my_modal_edit" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3>当前持仓产品</h3>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <table class="table table-striped table-bordered table-hover" id="current_holdings">
                        <thead>
                        <tr>
                            <td width="15%">持有产品名称</td>
                            <td>持仓金额(万元)</td>
                            <td>成立日</td>
                            <td>派息日</td>
                            <td>开放日</td>
                            <td>兑付日</td>
                            <td>合同编号</td>
                            <td>开户行</td>
                            <td>银行账号</td>
                            <td>基金份额</td>
                            <td>投资期限(月)</td>
                            <td>收益率(%)</td>
                        </tr>
                        </thead>
                        <tbody >

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>


            <div id="modal_history" class="modal hide fade" style="width: 1020px;height: 500px;margin-left:-400px;" tabindex="-1" role="dialog" aria-labelledby="my_modal_edit" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3>历史持仓产品</h3>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <table class="table table-striped table-bordered table-hover" id="history_holdings">
                        <thead>
                        <tr>
                            <td width="15%">持有产品名称</td>
                            <td>持仓金额(万元)</td>
                            <td>成立日</td>
                            <td>派息日</td>
                            <td>开放日</td>
                            <td>兑付日</td>
                            <td>本息合计</td>
                            <td>开户行</td>
                            <td>银行账号</td>
                            <td>基金份额</td>
                            <td>投资期限(月)</td>
                            <td>收益率(%)</td>
                        </tr>
                        </thead>
                        <tbody >

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                </div>
            </div>

        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

<script>

    $(function(){
        $(".current_holding").click(function(){
            var customerId = $(this).data('id');
            $.ajax({
                url: "<%=contextPath%>/business/assets/holdings/current",
                type: "get",
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                data : {"customerId" : customerId},
                success: function (data) {
                    var tBody = '';
                    $.each(data.children, function(i,val){
                        var buyDay = val.buyDay;
                        if(buyDay == null){
                            buyDay = '';
                        }
                        var earningRate = val.earningRate;
                        if(earningRate == null){
                            earningRate = '';
                        }
                        var paymentDay = val.paymentDay;
                        if(paymentDay == null){
                        	paymentDay = '';
                        }else{
                        	paymentDay = getFormatDate(new Date(paymentDay));
                        }
                        tBody += "<tr> <td>"+val.productName+"</td> <td>"+val.amount / 10000+"</td> <td>"+getFormatDate(new Date(val.foundDay))+"</td> <td>"+val.dividendDay+"</td> <td>"+buyDay+"</td> " +
                        "<td>"+paymentDay+"</td> <td>"+val.serial+"</td> <td>"+val.bank+"</td> <td>"+val.bankAccount+"</td> <td>"+val.lot+"</td> <td>"+val.investTerm+"</td> <td>"+earningRate+"</td></tr>";
                    });

                    $("#current_holdings tbody").html(tBody);
                },
                error: function (err) {

                }
            });
        });

        $(".history_holding").click(function(){
            var customerId = $(this).data('id');
            $.ajax({
                url: "<%=contextPath%>/business/assets/holdings/history",
                type: "get",
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                data : {"customerId" : customerId},
                success: function (data) {
                    var tBody = '';

                    $.each(data.children, function(i,val){
                        var buyDay = val.buyDay;
                        if(buyDay == null){
                            buyDay = '';
                        }
                        var earningRate = val.earningRate;
                        if(earningRate == null){
                            earningRate = '';
                        }
                        var paymentDay = val.paymentDay;
                        if(paymentDay == null){
                        	paymentDay = '';
                        }else{
                        	paymentDay = getFormatDate(new Date(paymentDay));
                        }
                        tBody += "<tr> <td>"+val.productName+"</td> <td>"+val.amount / 10000+"</td> <td>"+getFormatDate(new Date(val.foundDay))+"</td> <td>"+val.dividendDay+"</td> <td>"+buyDay+"</td> " +
                        "<td>"+paymentDay+"</td> <td>"+val.payment+"</td> <td>"+val.bank+"</td> <td>"+val.bankAccount+"</td> <td>"+val.lot+"</td> <td>"+val.investTerm+"</td> <td>"+earningRate+"</td></tr>";

                    });

                    $("#history_holdings tbody").html(tBody);
                },
                error: function (err) {

                }
            });
        });

    });


    function viewScoreDetail(customerName){
        window.location.href = '<%=contextPath%>/business/score/query?name=' + customerName;
    }

    function getFormatDate(date) {
        var seperator = "-";
        var month = date.getMonth() + 1;
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }

        var day = date.getDay();
        if(day >=1 && day <=9){
            day = "0" + day;
        }
        return date.getFullYear() + seperator + month + seperator + day;
    }

</script>