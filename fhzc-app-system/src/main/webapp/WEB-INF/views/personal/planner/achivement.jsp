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
                            <a href="javascript:void(0);">理财师管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">理财师业绩</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <div class="row-fluid">
                <form  class="form-inline" id="achive_form">
                    <div class="form-group">
                        <label class="control-label" style="margin-left: 20px">区总</label>
                        <select class="form-control"  id="area"  name="area" style="width:180px;">
                            <option value="0">全部</option>
                        </select>

                        <label class="control-label" style="margin-left: 20px">分公司</label>
                        <select class="form-control"  id="subCompany"  name="subCompany" style="width:180px;">
                            <option value="0">全部</option>
                        </select>

                        <label class="control-label" style="margin-left: 20px">团队</label>
                        <select class="form-control" id="team"  name="team" style="width:180px;">
                            <option value="0">全部</option>
                        </select>

                        <label class="control-label" style="margin-left: 20px">月份</label>
                        <input type="text" class="form-control form_datetime"  data-date-format="yyyy-mm"  id="startDate"  name="startDate" style="width:180px;"/>
                        <script>
                            $("#startDate").datepicker({ dateFormat: 'yy-mm',startView: 3, minView: 3, autoclose: true });
                        </script>
                        <button type="button" id="button_search" class="btn blue" onclick="formSubmit()" ><i class="icon-search"></i> 查询</button>
                    </div>
                </form>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="portlet-body">
                   <div class="span6" style="margin-top: 20px">
                        <div id="contanis" style="height: 600px"></div>
                   </div>

                    <div  class="span6" style="margin-top: 100px">
                        <table class="table table-hover " id="achive_table">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>

    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/static/Echarts/echarts.js"></script>

<script>

$(function(){
        $("#startDate").datepicker({ dateFormat: 'yy-mm',startView: 3, minView: 3, autoclose: true });

        var area = '${area}';
        var json= $.parseJSON(area);
        $.each(json, function(i,val){
            $("#area").append("<option value='"+val.departmentId+"'>"+val.title+"</option>");
        });

        $("#area").change(function(){
            var area = $('#area').val();
            $.ajax({
                type: "GET",
                url: "<%=contextPath%>/personal/planner/achivement/getDepartment",
                dataType: "json",
                data: { "departmentId": area },
                success: function(req) {
                    $("#subCompany").empty();
                    $("#subCompany").append("<option value='0'>全部</option>");
                    $.each(req.children, function(i,val){
                        $("#subCompany").append("<option value='"+val.departmentId+"'>"+val.title+"</option>");
                    });

                    var team = $('#team').val();
                    if(team == null || team == ''){
                        $("#team").prepend("<option value='0'>全部</option>");
                    }
                },
                error: function() {

                }
            });
        });

        $('#subCompany').change(function(){
            var subCompany = $('#subCompany').val();
            $.ajax({
                type: "GET",
                url: "<%=contextPath%>/personal/planner/achivement/getDepartment",
                dataType: "json",
                data: { "departmentId": subCompany },
                success: function(req) {
                    $("#team").empty();
                    $("#team").append("<option value='0'>全部</option>");
                    $.each(req.children, function(i,val){
                        $("#team").append("<option value='"+val.departmentId+"'>"+val.title+"</option>");
                    });

                },
                error: function() {

                }
            });
        });

    });


    function formSubmit(){

        var date = $('#startDate').val();
        if(date == null || date == ''){
            BootstrapDialog.alert({
                title: '提示',
                message: '请选择月份'
            });
            return false;
        }

        $.ajax({
            type: "GET",
            url: "<%=contextPath%>/personal/planner/achivement/find",
            data : $('#achive_form').serialize(),
            success : function(result){
               var option = {
                    title : {
                        text: '理财师业绩',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    series : [
                        {
                            name: '业绩',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:result.data,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };

                var dom = document.getElementById('contanis');
                var mycharts = echarts.init(dom);
                mycharts.setOption(option);

                var company = $('#subCompany').val();
                var team = $('#team').val();
                var tHead;
                if(company == 0){
                    tHead = "<tr> <td>分公司</td> <td>月份</td> <td>年化金额(万元)</td> <td>占比(%)</td></tr>";
                }else if(team == 0){
                    tHead = "<tr> <td>团队</td> <td>月份</td> <td>年化金额(万元)</td> <td>占比(%)</td></tr>";
                }else{
                    tHead = "<tr> <td>理财师</td> <td>月份</td> <td>年化金额(万元)</td> <td>占比(%)</td></tr>";
                }
                $("#achive_table thead").replaceWith(tHead);

                var tBody = '';
                for(var i=0;i<result.data.length;i++){
                    var name = result.data[i].name;
                    var value = result.data[i].value;
                    var percent = result.data[i].percent;
                    var date = new Date(result.data[i].date);
                    tBody += "<tr> <td>"+name+"</td> <td>"+getFormatDate(date)+"</td> <td>"+value+"</td> <td>"+percent+"</td></tr>";
                }
                $("#achive_table tbody").html(tBody);
            }
        })
    }


    function getFormatDate(date) {
        var seperator1 = "-";
        var month = date.getMonth() + 1;
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        return date.getFullYear() + seperator1 + month;
    }
</script>