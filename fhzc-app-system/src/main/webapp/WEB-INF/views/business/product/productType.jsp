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
                            <a href="javascript:void(0);">产品管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">产品分类</a></li>
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
                                <span class="hidden-480"></span>
                                &nbsp;
                            </h4>
                        </div>
                        <div class="portlet-body form">
                            <div class="tabbable portlet-tabs">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <!-- BEGIN FORM-->
                                        <form action="<%=contextPath%>/business/product/type/add" method="POST" class="form-horizontal">
                                            <div class="control-group">
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品类型值</label>
                                                <div class="controls">
                                                    <input type="number" name="value" placeholder="输入值" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品类型</label>
                                                <div class="controls">
                                                    <input type="text" name="key" placeholder="输入键" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 保存</button>
                                            </div>
                                        </form>
                                        <!-- END FORM-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->

                    <div class="portlet box yellow">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder"></i></h4>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>产品类型值</td>
                                    <td>产品类型</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${productTypes}" var="productType">
                                    <tr>
                                        <td>${productType.value}</td>
                                        <td>${productType.key}</td>
                                        <td>

                                            <a href="#modal_edit" role="button" class="btn mini purple mod_ptype" data-toggle="modal" data-id="${productType.id}" data-key="${productType.key}" data-value="${productType.value}"><i class="icon-edit"></i> 修改</a>
                                            <a href="#modal_del" role="button" class="btn mini black del_ptype" data-toggle="modal" data-id="${productType.id}" ><i class="icon-trash"></i> 删除</a>
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
                    <h3 id="myModalLabel2">修改名称</h3>
                </div>
                <div class="modal-body">
                    <p><input type="text" id="productType_key" value="" class="m-wrap large"></p>
                    <input type="hidden" id="dict_id" />
                    <input type="hidden" id="productType_value" />
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn green" id="do_mod_productType">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
            <div id="modal_del" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="my_modal_del" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3 id="myModalLabel3">确定删除</h3>
                </div>
                <div class="modal-body">
                    <p>请确认删除操作,删除后不可恢复</p>
                    <input type="hidden" id="ptype_del_id" />
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                    <button data-dismiss="modal" class="btn blue" id="do_del_productType">删除</button>
                </div>
            </div>

        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<script>

    $(function(){


        $(".mod_ptype").click(function(){
            $("#productType_key").val($(this).data('key'));
            $("#dict_id").val($(this).data('id'));
            $("#productType_value").val($(this).data('value'));
        });

        $(".del_ptype").click(function(){
            $("#ptype_del_id").val($(this).data('id'))
        });

        $("#do_mod_productType").click(function () {
            $.post("<%=contextPath%>/business/product/type/add",{'id':$("#dict_id").val(),'key':$("#productType_key").val(),'value':$("#productType_value").val()},function (data) {window.location.reload();})
        });

        $("#do_del_productType").click(function () {
            $.get("<%=contextPath%>/business/product/type/delete/" + $("#ptype_del_id").val(),function (data) {window.location.reload();})
        });
    });


</script>

<jsp:include page="../../include/footer.jsp"/>