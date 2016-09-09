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
                        <li class="active"><a href="javascript:void(0);">个人客户投资人档案表导入</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN SAMPLE FORM PORTLET-->
                    <div class="portlet box blue tabbable">
                        <div class="portlet-title">
                            <h4>
                                <i class="icon-plus"></i>
                                <span class="hidden-480">导入</span>
                                &nbsp;
                            </h4>
                        </div>
                        <div class="portlet-body form">
                            <div class="tabbable portlet-tabs">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <!-- BEGIN FORM-->
                                        <form action="importpersonal" enctype="multipart/form-data" method="POST" class="form-horizontal">
                                            <div class="control-group">
                                            </div>
                                            <div class="controls">
                                                <div class="fileupload fileupload-new" data-provides="fileupload"><input type="hidden" value="" name="">
                                                    <div class="input-append">
                                                        <div class="uneditable-input">
                                                            <i class="icon-file fileupload-exists"></i>
                                                            <span class="fileupload-preview"></span>
                                                        </div>
                                                       <span class="btn btn-file">
                                                       <span class="fileupload-new">点击选择</span>
                                                       <span class="fileupload-exists">更换</span>
                                                       <input type="file" name="multiFile" class="default" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
                                                       </span>
                                                        <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <button type="submit" id="add" class="btn blue"><i class="icon-ok"></i>导入文档</button>
						                        <button type="button" class="btn blue" id="approvalScorePersonal"><i class="icon-ok"></i> 执行计算待审批积分</button>
                                            </div>
                                        </form>
                                        <!-- END FORM-->
                                    </div>
                                     <c:if test="${success != null}">
										<jsp:include page="../../include/alert.jsp"/>
									 </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->
<!-- 
                    <div class="portlet box yellow">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder">导入列表</i></h4>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td><input type="checkbox" id="checkAll"/>全选</td>
                                    <td>产品名称</td>
                                    <td>状态</td>
                                    <td>起息日</td>
                                    <td>到期日</td>
                                    <td>派息日</td>
                                    <td>添加时间</td>
                                    <td>客户等级</td>
                                    <td>客户风险等级</td>
                                    <td>编辑</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td>${product.name}</td>
                                        <td>${product.status}</td>
                                        <td>${data.website}</td>
                                        <td></td>
                                        <td></td>
                                        <td>${data.title}</td>
                                        <td>${fn:substring(data.createTime, 0, 10)}</td>
                                        <td>${fn:substring(data.expireTime, 0, 10)}</td>
                                        <td><a href="business/product/detail/${data.id}">编辑</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div> -->
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
<script>
	$("#add").click(function() { 
		var filename=$("input[name='multiFile']").val();
		var extStart=filename.lastIndexOf("."); 
		var ext=filename.substring(extStart,filename.length);
		if(filename.trim().length <=1 ){
			 alert("请选择要导入的Excel文件!"); 
		     return false; 
		}
	    if(ext != ".xlsx" && ext != ".xls"){ 
			 alert("添加的文件不是Excel!"); 
		     return false; 
	    }else{
	    	return true; 
	    }
	}); 
	
	$("#approvalScorePersonal").click(function(){
		$.ajax({
			type: "GET",
			contentType: "application/json; charset=utf-8",
			url: "/business/customerdocument/approvalScorePersonal",
			dataType: "json",
			success: function(data) {
				if(data.result){
					BootstrapDialog.show({
			            title: '提示',
			            message: '执行成功！'
			        });
				}else{
					BootstrapDialog.show({
			            title: '提示',
			            message: '执行失败！'
			        });
				}
			}
		});
	});
</script>