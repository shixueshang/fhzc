<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
%>

<jsp:include page="../include/header.jsp"/>
<!-- BEGIN BODY -->
<body class="fixed-top">
<jsp:include page="../include/nav.jsp"/>

    <!--扩展样式-->
    <link href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
    <link rel="stylesheet" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />
    <link href="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
    <link rel="stylesheet" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />

    <link rel="stylesheet" media="all" type="text/css" href="<%=contextPath%>/assets/custom_datepicker/jquery-ui-1.11.min.css" />
    <link rel="stylesheet" media="all" type="text/css" href="<%=contextPath%>/assets/custom_datepicker/jquery-ui-timepicker-addon.css" />


        <!-- BEGIN CONTAINER -->
        <div class="page-container row-fluid">
        <jsp:include page="../include/left.jsp"/>
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
                                    <a href="javascript:void(0);">综合素质能力评价</a> 
                                    <i class="icon-angle-right"></i>
                                </li>
                                <li class="active"><a href="javascript:void(0);">添加题目</a></li>
                            </ul>
                            <!-- END PAGE TITLE & BREADCRUMB-->
                        </div>
                    </div>

                    <!--页面操作详细内容 开始-->
                    <div class="row-fluid">
                        <div class="portlet-body form">
                            <div class="tabbable portlet-tabs">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <form action="" method='post' class="form-horizontal">

                                            <div class="control-group">
                                                <label class="control-label">问题编号</label>
                                                <div class="controls">
                                                    <input type="text"  name='title' placeholder="" class="span6 m-wrap">
                                                    <span class="help-inline">请输入6位编号</span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">问题描述</label>
                                                <div class="controls">
                                                    <textarea class="large m-wrap" rows="3"></textarea>
                                                    <span class="help-inline">请输入问题描述</span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">所属分类</label>
                                                <div class="controls">
                                                    <select class="span6 chosen" name="cat_id" data-placeholder="请选择所属分类名称" tabindex="1">
                                                        <option selected="selected" value="0" class="padding_0">顶级分类</option>
                                                        <option value="1" class="padding_0">版型</option>
                                                        <option value="2" class="padding_10">合体</option>
                                                        <option value="3" class="padding_10">修身</option>
                                                        <option value="4" class="padding_0">领型</option>
                                                        <option value="5" class="padding_10">平戗驳领</option>
                                                        <option value="6" class="padding_10">大平驳领</option>
                                                        <option value="7" class="padding_10">小平驳领</option>
                                                        <option value="8" class="padding_10">小戗驳领</option>
                                                        <option value="9" class="padding_10">大戗驳领</option>
                                                        <option value="10" class="padding_0">扣子</option>
                                                        <option value="11" class="padding_10">单排一粒扣</option>
                                                        <option value="12" class="padding_10">单排两粒扣</option>
                                                        <option value="13" class="padding_10">双排1+1</option>
                                                        <option value="14" class="padding_10">双排1+3</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">评分标准</label>
                                                <div class="controls">
                                                    <input type="text"  name='author' placeholder="" class="span6 m-wrap">
                                                    <span class="help-inline">请输入评分标准</span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">备注</label>
                                                <div class="controls">
                                                    <textarea class="large m-wrap" rows="3"></textarea>
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <button type="submit" class="btn blue"><i class="icon-ok"></i> 提交</button>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <!--页面操作详细内容 开始-->

                </div>
            </div>
        </div>
        <jsp:include page="../include/footer.jsp"/>