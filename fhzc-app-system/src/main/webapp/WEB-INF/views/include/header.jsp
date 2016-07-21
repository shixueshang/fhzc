<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>复华资产</title>
    <link rel="shortcut icon" href="<%=contextPath%>/static/image/favicon.ico" type="image/x-icon">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <script src="<%=contextPath%>/assets/js/jquery-1.8.3.min.js"></script>
    <script src="<%=contextPath%>/assets/js/excanvas.js"></script>
    <script src="<%=contextPath%>/assets/js/respond.js"></script>

    <script src="<%=contextPath%>/assets/breakpoints/breakpoints.js"></script>
    <script src="<%=contextPath%>/assets/jquery-ui/jquery-ui-1.10.1.custom.min.js"></script>
    <script src="<%=contextPath%>/assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=contextPath%>/assets/fullcalendar/fullcalendar/fullcalendar.min.js"></script>
    <script src="<%=contextPath%>/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/assets/js/jquery.blockui.js"></script>
    <script src="<%=contextPath%>/assets/js/jquery.cookie.js"></script>


    <script src="<%=contextPath%>/assets/flot/jquery.flot.js"></script>
    <script src="<%=contextPath%>/assets/flot/jquery.flot.resize.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/js/jquery.pulsate.min.js"></script>

    <!-- ie8 fixes -->
    <!--[if lt IE 9]>

    <![endif]-->


    <script type="text/javascript" src="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/uniform/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/data-tables/DT_bootstrap.js"></script>


    <script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.pack.js"></script>
    <script src="<%=contextPath%>/assets/js/app.js"></script>
    <script>
        jQuery(document).ready(function () {
            App.init();
        });
    </script>
    <script type="text/javascript" src="<%=contextPath%>/assets/js/jquery.pulsate.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/assets/jquery-validation/dist/jquery.validate.min.js"></script>

    <link href="<%=contextPath%>/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" /> <!-- bootstrap默认css -->
    <link href="<%=contextPath%>/assets/css/metro.css" rel="stylesheet" /> <!-- 控制button的显示样式，如果不引入的话，则为bootstrap默认样式 -->
    <link href="<%=contextPath%>/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" /> <!-- bootstrap导航菜单样式 -->
    <link href="<%=contextPath%>/assets/font-awesome/css/admin-font-awesome.css" rel="stylesheet" />  <!-- 导航菜单图标 -->
    <link href="<%=contextPath%>/assets/css/admin-style.css" rel="stylesheet" /> <!-- 整体样式控制，网页字体 -->
    <link href="<%=contextPath%>/assets/css/style_responsive.css" rel="stylesheet" /> <!-- 本框架导航菜单样式 -->
    <link href="<%=contextPath%>/assets/css/style_blue.css" rel="stylesheet" id="style_color" /> <!-- 本框架皮肤样式 -->
</head>


