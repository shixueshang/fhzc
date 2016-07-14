<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
    String contextPath = request.getContextPath();
%>

<jsp:include page="include/header.jsp"/>
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="<%=contextPath%>/assets/images/logo_a.png" alt="" style="height:85px;"/>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->

    <form role="form" action="/login" method="post">
    <h3 class="form-title"></h3>
    <div class="alert alert-error hide">
        <button class="close" data-dismiss="alert"></button>
        <span>请填写用户名和密码</span>
    </div>
    <div class="control-group">
        <label class="control-label visible-ie8 visible-ie9">用户名</label>
        <div class="controls">
            <div class="input-icon left">
                <i class="icon-user"></i>
                <input class="m-wrap placeholder-no-fix" type="text" name="username" placeholder="用户名" name="username"/>
            </div>
            <label for="username" class="help-inline help-small no-left-padding" style="display:none;">请填写用户名</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label visible-ie8 visible-ie9">密码</label>
        <div class="controls">
            <div class="input-icon left">
                <i class="icon-lock"></i>
                <input class="m-wrap placeholder-no-fix" type="password" name="password" placeholder="密码" name="password"/>
            </div>
            <label for="password" class="help-inline help-small no-left-padding" style="display:none;">请填写密码</label>
        </div>
    </div>
    

    <div class="form-actions">
        <button type="submit" class="btn blue btn-block">
            登录 <i class="m-icon-swapright m-icon-white"></i>
        </button>
    </div>
    </form>
    <div style="color: red">
        ${error}
    </div>
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS -->
<script src="<%=contextPath%>/assets/js/jquery-1.8.3.min.js"></script>
<script src="<%=contextPath%>/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/assets/uniform/jquery.uniform.min.js"></script>
<script src="<%=contextPath%>/assets/js/jquery.blockui.js"></script>
<script src="<%=contextPath%>/assets/js/app.js"></script>
<!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->

</html>

