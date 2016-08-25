<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
    String basePath  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
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
                            <a href="javascript:void(0);">权益管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">权益列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
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
                            <table id="example" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td style="width:18%">权益名</td>
                                    <td>权益类型</td>
                                    <td>权益图片</td>
                                    <td>权益供应商</td>
                                    <td>客户等级要求</td>
                                    <td>兑换积分</td>
                                    <td>是否精选</td>
                                    <td>关注人数</td>
                                    <td>预约人数</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${rights}" var="right">
                                    <tr>
                                        <td>${right.name}</td>
                                        <td>
                                            <c:forEach items="${rightsCategory}" var="category">
                                                <c:if test="${right.cid == category.value}">
                                                    ${category.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <img width="120px" src="<%=contextPath%>${right.cover}" />
                                        </td>
                                        <td>${right.supply}</td>
                                        <td>
                                            <c:forEach items="${customerLevel}" var="level">
                                                <c:if test="${right.level == level.value}">
                                                    ${level.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${right.spendScore}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${right.isRecommend == '0'}">
                                                    	否
                                                </c:when>
                                                <c:when test="${right.isRecommend == '1'}">
                                                   		 是
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>${right.focusNum}</td>
                                        <td>${right.orderNum}</td>
                                        <td><a href="<%=contextPath%>/business/rights/detail/${right.id}" class="btn mini purple"><i class="icon-edit"></i> 编辑</a>
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
            
            			<script type="text/javascript" charset="utf-8">
        	
        	
            $('#example').DataTable({
            	"bAutoWidth" : false,
            	"bFilter": true, //过滤功能
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
                
            });
            

     </script>    

        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>