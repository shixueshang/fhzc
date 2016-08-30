<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
    String contextPath = request.getContextPath();
%>

<jsp:include page="../../include/header.jsp"/>
<!-- BEGIN BODY -->
<body class="fixed-top">
<jsp:include page="../../include/nav.jsp"/>

<!--扩展样式-->
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css"  />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />
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
                            <a href="javascript:void(0);">产品管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">产品列表</a></li>
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
                            <table id = "example" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>产品代码</td>
                                    <td style="width:18%">产品名称</td>
                                    <td>产品类型</td>
                                    <td>产品状态</td>
                                    <td>投放分公司</td>
                                    <td>成立日</td>
                                    <td>是否推荐</td>
                                    <td>是否展示</td>
                                    <td>关注人数</td>
                                    <td>预约人数</td>
                                    <td>预约金额(万元)</td>
                                    <td>排序</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td>${product.code}</td>
                                        <td>${product.name}</td>
                                        <td>
                                            <c:forEach items="${productTypes}" var="pType">
                                                <c:if test="${product.productType == pType.value}">
                                                        ${pType.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${productStatus}" var="pStatus">
                                                <c:if test="${product.status == pStatus.value}">
                                                    ${pStatus.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${departments}" var="department">
                                                <c:if test="${product.throwDepartment == department['id']}">
                                                    ${department['name']}
                                                </c:if>
                                            </c:forEach>

                                        </td>
                                        <td><fmt:formatDate value="${product.foundDay}" pattern="yyyy-MM-dd"/></td>
                                        <td>
                                            <c:forEach items="${yesNo}" var="yesNo">
                                                <c:if test="${product.isRecommend == yesNo.value}">
                                                    ${yesNo.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${yesNo}" var="yesNo">
                                                <c:if test="${product.isDisplay == yesNo.value}">
                                                    ${yesNo.key}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>${product.focusNum}</td>
                                        <td>${product.orderNum}</td>
                                        <td>${product.orderAmount}</td>
                                        <td style="margin:auto;vertical-align: middle;">
                                        <a href="#" id="up" class="up" onclick="moveUp(${product.pid})">上移</a>&nbsp&nbsp<a href="#" id="down" class="down" onclick="moveDown(${product.pid})">下移</a></br>
                                        </td>
                                        <td>
                                            <a href="<%=contextPath%>/business/product/detail/${product.pid}" class="btn mini purple"><i class="icon-edit"></i> 编辑</a>
                                            <a href="<%=contextPath%>/business/product/order/${product.pid}" class="btn mini purple"><i class="icon-share"></i> 预约</a>
                                            <a href="#modal_edit" role="button" class="btn mini purple product_push"><i class="icon-signin"></i> 推送</a>
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
<script type="text/javascript">
	function moveUp(id){
		 $.ajax({
		    type:"get",
		    cache:false,
		    url:"<%=contextPath%>/business/product/upDisplayOrder?pid="+id, 
		    dataType:"text",
		    success: function(data) {
		    	if("top" == data){
		    		alert("该条记录已是最顶端");
		    	}else{
		    		window.location.href="<%=contextPath%>/business/product/list"; 
		    	}
		     }
		   });
	}
	function moveDown(id){
		 $.ajax({
			    type:"get",
			    cache:false,
			    url:"<%=contextPath%>/business/product/downDisplayOrder?pid="+id, 
			    dataType:"text",
			    success: function(data) { 
			      	if("bottom" == data){
			    		alert("该条记录已是最底端");
			    	}else{
			    		window.location.href="<%=contextPath%>/business/product/list"; 
			    	}
			     }
			   });
	}
	$('#example').DataTable({
    	"bAutoWidth" : false,
    	"bFilter": true, //过滤功能
    	"bSort": false, //排序功能
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