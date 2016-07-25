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

        <!--À©Õ¹ÑùÊ½-->
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
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="index.html">Home</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">Data Tables</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">Basic Tables</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->

            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet box purple">
                        <div class="portlet-title">
                            <h4><i class="icon-comments"></i>用户列表</h4>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">

                            <div class="row-fluid">

                                <form id="form" action="/Admin/News/index" method="post">
                                    <div class="controls">

                                        <div class="input-prepend">
                                            <span class="add-on">用户名</span>
                                            <input class="m-wrap large" name="title" type="text" value="">
                                        </div>
                                        &nbsp;&nbsp;
                                        <div class="input-prepend">
                                            <span class="add-on">所属角色</span>
                                            <select class="medium m-wrap" name="type_id" tabindex="1">
                                                <option value="">-请选择-</option>
                                                <option value="2">公司新闻（Company News）</option><option value="4">媒体报道（Media News）</option><option value="6">会凌观点（viewpoint）</option>                            </select>
                                        </div>
                                        &nbsp;

                                        <div class="input-prepend">
                                            <span class="add-on">状态</span>
                                            <select class="medium m-wrap" name="type_id" tabindex="1">
                                                <option value="">-请选择-</option>
                                                <option value="2">公司新闻（Company News）</option><option value="4">媒体报道（Media News）</option><option value="6">会凌观点（viewpoint）</option>                            </select>
                                        </div>
                                        &nbsp;

                                        <div class="input-prepend">
                                            <input class="btn blue" type="submit" name="sub" value="搜索">
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>用户名称</th>
                                    <th>用户真实姓名</th>
                                    <th>所属角色名称</th>
                                    <th>状态</th>
                                    <th>邮箱</th>
                                    <th>电话</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Mark</td>
                                    <td>Otto</td>
                                    <td class="hidden-480">makr124</td>
                                    <td><span class="label label-success">Approved</span></td>
                                    <td>Otto</td>
                                    <td class="hidden-480">makr124</td>
                                    <td><span class="label label-success">Approved</span></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacob</td>
                                    <td>Nilson</td>
                                    <td class="hidden-480">jac123</td>
                                    <td><span class="label label-info">Pending</span></td>
                                    <td>Nilson</td>
                                    <td class="hidden-480">jac123</td>
                                    <td><span class="label label-info">Pending</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- END SAMPLE TABLE PORTLET-->
                </div>
            </div>

            <!-- END PAGE CONTENT-->
        </div>


        </div>

        </div>

        <jsp:include page="../../include/footer.jsp"/>
        <script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>

