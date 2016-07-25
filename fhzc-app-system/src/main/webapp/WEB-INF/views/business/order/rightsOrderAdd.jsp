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
            <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
            <div class="span12">
            <!-- BEGIN STYLE CUSTOMIZER -->
            <div class="color-panel hidden-phone">
            <div class="color-mode-icons icon-color"></div>
            <div class="color-mode-icons icon-color-close"></div>
            <div class="color-mode">
            <p>THEME COLOR</p>
            <ul class="inline">
            <li class="color-black current color-default" data-style="default"></li>
            <li class="color-blue" data-style="blue"></li>
            <li class="color-brown" data-style="brown"></li>
            <li class="color-purple" data-style="purple"></li>
            <li class="color-white color-light" data-style="light"></li>
            </ul>
            <label class="hidden-phone">
            <div class="checker" id="uniform-undefined"><span class="checked"><input type="checkbox" class="header" checked="" value="" style="opacity: 0;"></span></div>
            <span class="color-mode-label">Fixed Header</span>
            </label>
            </div>
            </div>
            <!-- END BEGIN STYLE CUSTOMIZER -->

            <ul class="breadcrumb">
            <li>
            <i class="icon-home"></i>
            <a href="index.html">admin</a>
            <i class="icon-angle-right"></i>
            </li>
            <li>
            <a href="#">业务管理</a>
            <i class="icon-angle-right"></i>
            </li>
            <li>
            <a href="#">权益管理</a>
            <i class="icon-angle-right"></i>
            </li>
            <li>
            <a href="#">新增预约</a>
            </li>
            </ul>
            </div>
            </div>
            <!-- END PAGE HEADER-->

            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
            <div class="span12">
            <!-- BEGIN SAMPLE FORM PORTLET-->
            <div class="portlet box blue tabbable">
            <div class="portlet-title">
            <h4>
            <i class="icon-reorder"></i>
            <span class="hidden-480">新增预约</span>
            &nbsp;
            </h4>
            </div>
            <div class="portlet-body form">
            <div class="tabbable portlet-tabs">
            <div class="tab-content">
            <div class="tab-pane active" id="portlet_tab1" style="margin-top:15px;">
            <!-- BEGIN FORM-->
            <form action="#" class="form-horizontal">
            <div class="control-group">
            <label class="control-label">客户身份证号</label>
            <div class="controls">
            <input type="text" placeholder="输入预约地点" class="m-wrap medium">
            <span class="help-inline"></span>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">客户姓名</label>
            <div class="controls">
            <span class="help-inline">姓名</span>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">客户等级</label>
            <div class="controls">
            <span class="help-inline">投资人</span>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">客户可用积分</label>
            <div class="controls">
            <span class="help-inline">668</span>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">客户兑换权益</label>
            <div class="controls">
            <select class="medium m-wrap" tabindex="1">
            <option value="Category 1">高端洗牙权益</option>
            <option value="Category 2">Category 2</option>
            <option value="Category 3">Category 5</option>
            <option value="Category 4">Category 4</option>
            </select>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">等级要求</label>
            <div class="controls">
            <span class="help-inline">不低于潜在客户</span>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">兑换所需积分</label>
            <div class="controls">
            <span class="help-inline">668</span>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">预约时间</label>
            <div class="controls">
            <select class="medium m-wrap" tabindex="1">
            <option value="Category 1">选择时间</option>
            <option value="Category 2">Category 2</option>
            <option value="Category 3">Category 5</option>
            <option value="Category 4">Category 4</option>
            </select>
            </div>
            </div>
            <div class="control-group">
            <label class="control-label">预约地点</label>
            <div class="controls">
            <input type="text" placeholder="输入预约地点" class="m-wrap medium">
            <span class="help-inline"></span>
            </div>
            </div>
            <div class="form-actions">
            <button type="submit" class="btn blue">保存</button>
            </div>
            </form>
            <!-- END FORM-->
            </div>
            </div>
            </div>
            </div>
            </div>
            <!-- END SAMPLE FORM PORTLET-->
            </div>
            </div>
            <!-- END PAGE CONTENT-->
            </div>
            </div>

        </div>

        <jsp:include page="../../include/footer.jsp"/>
        <script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>

