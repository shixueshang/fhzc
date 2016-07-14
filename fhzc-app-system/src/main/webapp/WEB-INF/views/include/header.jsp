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
    <link rel="shortcut icon" href="/static/image/favicon.ico" type="image/x-icon">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <link href="<%=contextPath%>/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" /> <!-- bootstrap默认css -->
    <link href="<%=contextPath%>/assets/css/metro.css" rel="stylesheet" /> <!-- 控制button的显示样式，如果不引入的话，则为bootstrap默认样式 -->
    <link href="<%=contextPath%>/assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" /> <!-- bootstrap导航菜单样式 -->
    <link href="<%=contextPath%>/assets/font-awesome/css/admin-font-awesome.css" rel="stylesheet" />  <!-- 导航菜单图标 -->
    <link href="<%=contextPath%>/assets/css/admin-style.css" rel="stylesheet" /> <!-- 整体样式控制，网页字体 -->
    <link href="<%=contextPath%>/assets/css/style_responsive.css" rel="stylesheet" /> <!-- 本框架导航菜单样式 -->
    <link href="<%=contextPath%>/assets/css/style_blue.css" rel="stylesheet" id="style_color" /> <!-- 本框架皮肤样式 -->
</head>


