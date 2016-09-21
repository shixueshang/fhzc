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
                                    <td style="width:60px">排序</td>
                                    <td style="width:122px">操作</td>
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
                                            <!--  
                                            <a href="<%=contextPath%>/business/product/order/${product.pid}" class="btn mini purple"><i class="icon-share"></i> 预约</a>
                                            -->
                                            <a href="#modal_edit" role="button" data-toggle="modal" data-id="${product.pid}" class="btn mini purple product_push"><i class="icon-signin"></i> 推送</a>
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
            <div id="modal_edit" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="my_modal_edit" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3 id="myModalLabel2">消息推送</h3>
                </div>
                <form id="push_form">
                <div class="modal-body" style="height: 300px;width: 320px;">

                    <div class="control-group">
                        <label class="control-label" style="display: inline-block">消息标题</label>
                        <div class="controls" style="display: inline-block;margin-top: 10px;margin-left: 20px;">
                            <select name="title"  class="m-wrap medium">
                                <option value="1">产品成立</option>
                                <option value="2">产品派息</option>
                                <option value="3">产品兑付</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                    	<div>
                        	<label class="control-label" style="display: inline-block">消息内容</label>
                        </div>
                        <div class="controls" style="width:250px;display: inline-block;margin-top: 10px;margin-left: 10px;">
                            <textarea  name="content" maxlength="500" style="width: 300px; height: 100px;"  data-required="1" ></textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" style="display: inline-block">推送渠道</label>
                        <div class="controls" style="display: inline-block;margin-top: 10px;margin-left: 20px;">
                            <input type="checkbox" id="channel_system" value="1"/>系统&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="checkbox" id="channel_push" value="3"/>推送&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="checkbox" id="channel_email" value="4"/>邮件&nbsp;&nbsp;&nbsp;&nbsp;
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <input name="pushChannel" type="hidden"  id="pushChannel"/>
                    <input name="id" type="hidden"  id="dataId"/>
                    <button data-dismiss="modal" class="btn blue" id="do_mod_push">推送</button>
                    <button data-dismiss="modal" class="btn blue" aria-hidden="true">取消</button>
                </div>
                </form>
            </div>
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
	/*$('#example').DataTable({
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
        
    });*/

    $(function(){

        $(".product_push").click(function () {
            $('#dataId').val($(this).data("id"));
        });

        $("#do_mod_push").click(function () {
            if($('input[type="checkbox"]:checked').length == 0){
                BootstrapDialog.alert({
                    title: '提示',
                    message: '请至少选择一个推送渠道!'
                });
                return false;
            }

            var channels = new Array();

            $('input[type="checkbox"]:checked').each(function(){
                channels.push($(this).val());
            });

            $('#pushChannel').val(channels);

            $.ajax({
                url:"<%=contextPath%>/business/product/push",
                type:"POST",
                data:$('#push_form').serialize(),
                dataType:"json",
                success:function(data){
                    window.location.reload();
                },error:function(data){

                }
            });
        });
    });

</script>