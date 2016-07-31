<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
    String contextPath = request.getContextPath();
%>
            <div class="page-sidebar nav-collapse collapse">
                <ul>
                    <li>
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                        <div class="sidebar-toggler hidden-phone" style="margin-bottom:5px;"></div>
                        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    </li>
                    <li class="start active ">
                        <a href="<%=contextPath%>/system/home">
                            <i class="icon-home"></i>
                            <span class="title">Dashboard</span>
                            <span class="selected"></span>
                        </a>
                    </li>

                    <shiro:hasPermission name="/business/product"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-money"></i>
                            <span class="title">产品管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>
                        <ul class="sub">
                        <shiro:hasPermission name="/business/product/list"><li class="active">
                                <a href="<%=contextPath%>/business/product/list">产品列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/product/pub"><li>
                                <a href="<%=contextPath%>/business/product/pub">新增产品</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/product/order/list">
                            <li>
                                <a href="<%=contextPath%>/business/product/order/list">产品预约列表</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/business/activity"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-flag"></i>
                            <span class="title">活动管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                        <shiro:hasPermission name="/business/activity/list"><li>
                                <a href="<%=contextPath%>/business/activity/list">活动列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/activity/pub">   <li>
                                <a href="<%=contextPath%>/business/activity/pub">新增活动</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/activity/registers">   <li>
                                <a href="<%=contextPath%>/business/activity/registers">报名管理</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/business/report"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-book"></i>
                            <span class="title">投研报告管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>
                        <ul class="sub">
                        <shiro:hasPermission name="/business/report/list"><li>
                                <a href="<%=contextPath%>/business/report/list">报告列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/report/pub"><li>
                                <a href="<%=contextPath%>/business/report/pub">新增报告</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/business/rights"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-credit-card"></i>
                            <span class="title">权益管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                        <shiro:hasPermission name="/business/rights/list"><li>
                                <a href="<%=contextPath%>/business/rights/list">权益列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/rights/pub"><li>
                                <a href="<%=contextPath%>/business/rights/pub">新增权益</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/rights/list"><li>
                            <a href="<%=contextPath%>/business/rights/list">新增预约</a>
                        </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/rights/pub"><li>
                            <a href="<%=contextPath%>/business/rights/pub">处理预约</a>
                        </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-user"></i>
                            <span class="title">客户管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="#">客户列表</a>
                            </li>
                        </ul>
                    </li>

                    <li class="has-sub">
                        <a href="javascript:">
                            <i class="icon-group"></i>
                            <span class="title">理财师管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <li>
                                <a href="#">理财师列表</a>
                            </li>
                        </ul>
                    </li>

                    <shiro:hasPermission name="/organization/department"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-sitemap"></i>
                            <span class="title">机构管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                        <shiro:hasPermission name="/organization/department/department"><li>
                                <a href="<%=contextPath%>/organization/department/department">机构配置</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/system"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-wrench"></i>
                            <span class="title">系统管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                        <shiro:hasPermission name="/system/admin/list"><li>
                                <a href="<%=contextPath%>/system/admin/list">管理员列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/admin/pub"><li>
                                <a href="<%=contextPath%>/system/admin/pub">管理员新增</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/role/list"><li>
                                <a href="<%=contextPath%>/system/role/list">角色列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/role/pub"><li>
                                <a href="<%=contextPath%>/system/role/pub">角色新增</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>
                </ul>
                <!-- END SIDEBAR MENU -->
            </div>

<script>

    var url = '${url}';
    var domain = '';
    var obj = $('a[href*="'+url+'"]');
    $.each(obj, function(i, v){
        var _this = $(v), _parent = _this.closest('ul'), _p_parent = _parent.closest('li');
        console.log(_parent.html());
        console.log(_p_parent.html());
        _p_parent.addClass('active').addClass('open');
        _parent.show();

        return false;
    });

</script>
