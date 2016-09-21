<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String contextPath = request.getContextPath();
%>
        <!-- BEGIN HEADER 顶部导航 -->
        <div class="header navbar navbar-inverse navbar-fixed-top">
            <!-- BEGIN TOP NAVIGATION BAR -->
            <div class="navbar-inner">
                <div class="container-fluid" style="background-color: #acbff3">
                    <!-- BEGIN LOGO -->
                    <a class="brand" href="javascript:void(0);">
                        <img src="<%=contextPath%>/assets/images/logo_a.png" style="height: 25px;"/>
                    </a>
                    <!-- END LOGO -->
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                        <img src="<%=contextPath%>/assets/images/menu-toggler.png" alt="" />
                    </a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <ul class="nav pull-right">

                        <li class="dropdown user">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img alt="" width="29" height="29" src="<%=contextPath%>/assets/images/avatar.png" />
                                <span class="username"><shiro:principal property="realname"></shiro:principal></span>
                                <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                            	<li id ="btnLogin"><a href="#"><i class="icon-key"></i>修改密码</a></li>
                                <li><a href="/logout"><i class="icon-key"></i> 退出</a></li>
                            </ul>
                        </li>

                    </ul>

                </div>
            </div>
            <!-- END TOP NAVIGATION BAR -->
        </div>
        
<script>
    $("#btnLogin").click(function () {
    BootstrapDialog.show({
        title: '修改密码',
        closable: false,
        message: $(
        '<div>用户名：&nbsp;&nbsp;&nbsp;<input type="text" id="name" value = "<shiro:principal property='login'></shiro:principal>" disabled="disabled"/></div>'
        +'<input type="hidden" id="id" name="id" value = "<shiro:principal property='id'></shiro:principal>"/>'		
        +'<div>原密码：&nbsp;&nbsp;&nbsp;<input type="password" id="oldpassword" value="" name="oldpassword"/></div>'
        +'<div>新密码：&nbsp;&nbsp;&nbsp;<input type="password" id="password"/></div>'
        +'<div>确认密码：<input type="password" value="" id="repassword" name="repassword"/></div>'
        +' <span id = "errmsg" style="color:red"></span>'
        ),
        buttons: [{
            label: '确定',
            cssClass: 'btn blue',
            hotkey: 13, // Enter.
            action: function(dialog) {
            	var id = $('#id').val();
            	var oldpassword = $('#oldpassword').val();
	    		var password = $('#password').val();
	    		var repassword = $('#repassword').val();
	    		if(oldpassword == "" || oldpassword == null){
	    			$("#errmsg").html("原密码不能为空！");
	    			return false;
	    		}else{
	    			$("#errmsg").html("");
	    		}
	    		if(password == "" || password == null){
	    			$("#errmsg").html("密码不能为空！");
	    			return false;
	    		}else{
	    			$("#errmsg").html("");
	    		}
	    		if(repassword == "" || repassword == null){
	    			$("#errmsg").html("确认密码不能为空！");
	    			return false;
	    		}else{
	    			$("#errmsg").html("");
	    		}
	    		if(password != repassword){
	    			$("#errmsg").html("两次密码不一致！");
	    			return false;
	    		}else{
	    			$("#errmsg").html("");
	    		}
	    		$.ajax({
	                type: "GET",
	                url: "<%=contextPath%>/system/admin/checkPassword",
	                dataType: "json",
	                data: { "id":id, "oldpassword":oldpassword},
	                success: function(data) {
	                   if(!data.children){
	                	   $("#errmsg").html("原密码不正确！");
	   	    				return false;
	                   }else{
	                	   $.ajax({
	       	                type: "GET",
	       	                url: "<%=contextPath%>/system/admin/update",
	       	                dataType: "json",
	       	                data: { "id":id, "password":password},
	       	                success: function(data) {
	       	                   if(data.children){
	       	                	   dialog.close();
	       	                	   BootstrapDialog.alert({
	       	                           title: '提示',
	       	                           message: '修改成功！'
	       	                       });
	       	                   }else{
	       	                	   BootstrapDialog.alert({
	       	                           title: '提示',
	       	                           message: '修改失败，请重试！'
	       	                       });
	       	                	   $("#oldpassword").val("");
	       	                	   $("#password").val("");
	                                  $("#repassword").val("")
	       	                   }
	       	                },
	       	                error: function() {

	       	                }
	       	            });          
	                   }
	                },
	                error: function() {

	                }
	            }); 
	    	   
	    	}},
	    	{	label: '取消',
	    		action: function(dialog){
	    		  	dialog.close();
	    		}
        }]
    });
    });
    
</script>