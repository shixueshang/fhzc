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
                    <li class="start">
                        <a href="<%=contextPath%>/system/home">
                            <i class="icon-home"></i>
                            <span class="title">Dashboard</span>
                            <span class="selected"></span>
                        </a>
                    </li>

                    <shiro:hasPermission name="/business/product"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-reorder"></i>
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
                            <shiro:hasPermission name="/business/product/type">
                                <li>
                                    <a href="<%=contextPath%>/business/product/type">产品分类维护</a>
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
                                <a href="<%=contextPath%>/business/activity/pub">活动新增</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/activity/registers">   <li>
                                <a href="<%=contextPath%>/business/activity/registers">活动报名</a>
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
                                <a href="<%=contextPath%>/business/rights/pub">权益新增</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/rights/reservation/pub"><li>
                            <a href="<%=contextPath%>/business/rights/reservation/pub">添加权益预约</a>
                        </li></shiro:hasPermission>
                        <shiro:hasPermission name="/business/rights/reservations"><li>
                            <a href="<%=contextPath%>/business/rights/reservations">权益预约列表</a>
                        </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/personal/customer"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-user"></i>
                            <span class="title">客户管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                        <shiro:hasPermission name="/personal/customer/single/list"><li>
                                <a href="<%=contextPath%>/personal/customer/single/list">个人客户列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/personal/customer/organ/list"><li>
                                <a href="<%=contextPath%>/personal/customer/organ/list">机构客户列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/personal/user/focus/list"><li>
                                <a href="<%=contextPath%>/personal/user/focus/list">关注列表</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/personal/planner"><li class="has-sub">
                        <a href="javascript:">
                            <i class="icon-group"></i>
                            <span class="title">理财师管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                        <shiro:hasPermission name="/personal/planner/list"><li>
                                <a href="<%=contextPath%>/personal/planner/list">理财师列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/personal/planner/achivement"><li>
                                <a href="<%=contextPath%>/personal/planner/achivement">理财师业绩</a>
                            </li></shiro:hasPermission>

                        </ul>
                    </li></shiro:hasPermission>

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

                    <shiro:hasPermission name="/business/assets"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-shopping-cart"></i>
                            <span class="title">订单管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <shiro:hasPermission name="/business/assets/list"><li>
                                <a href="<%=contextPath%>/business/assets/list">订单列表</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/contract/list"><li>
                                <a href="<%=contextPath%>/business/contract/list">财务日报列表</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/business/score"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-money"></i>
                            <span class="title">积分管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <shiro:hasPermission name="/business/score/list"><li>
                                <a href="<%=contextPath%>/business/score/list">积分列表</a>
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
                        <shiro:hasPermission name="/system/banner/list"><li>
                                <a href="<%=contextPath%>/system/banner/list">banner列表</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/banner/pub"><li>
                                <a href="<%=contextPath%>/system/banner/pub">banner新增</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/about/app/pub"><li>
                                <a href="<%=contextPath%>/system/about/app/pub">关于App</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/about/contact/pub"><li>
                                <a href="<%=contextPath%>/system/about/contact/pub">联系我们</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/notice/pub"><li>
                                <a href="<%=contextPath%>/system/notice/pub">消息新增</a>
                            </li></shiro:hasPermission>
                        <shiro:hasPermission name="/system/notice/list"><li>
                                <a href="<%=contextPath%>/system/notice/list">消息列表</a>
                            </li></shiro:hasPermission>
                        </ul>
                    </li></shiro:hasPermission>

                    <shiro:hasPermission name="/import"><li class="has-sub">
                        <a href="javascript:;">
                            <i class="icon-tasks"></i>
                            <span class="title">导入管理</span>
                            <span class="selected"></span>
                            <span class="arrow open"></span>
                        </a>

                        <ul class="sub">
                            <shiro:hasPermission name="/business/product/importor">
                                <li>
                                    <a href="<%=contextPath%>/business/product/importor">产品导入</a>
                                </li></shiro:hasPermission>
                            <shiro:hasPermission name="/personal/planner/importor"><li>
                                <a href="<%=contextPath%>/personal/planner/importor">在职理财师导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/personal/planner/importoroff"><li>
                                <a href="<%=contextPath%>/personal/planner/importoroff">离职理财师导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/plannerachivementsdaily/importor"><li>
                                <a href="<%=contextPath%>/business/plannerachivementsdaily/importor">理财师日业绩导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/plannerachivementsmonthly/importor"><li>
                                <a href="<%=contextPath%>/business/plannerachivementsmonthly/importor">理财师月业绩导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/contract/importor"><li>
                                <a href="<%=contextPath%>/business/contract/importor">财务日表导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/payment/importor"><li>
                                <a href="<%=contextPath%>/business/payment/importor">兑付记录导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/payment/importorspecial"><li>
                                <a href="<%=contextPath%>/business/payment/importorspecial">鑫丰母兑付导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/customerdocument/importorpersonal"><li>
                                <a href="<%=contextPath%>/business/customerdocument/importorpersonal">个人投资人档案表导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/customerdocument/importoragent"><li>
                                <a href="<%=contextPath%>/business/customerdocument/importoragent">机构投资人档案表导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/score/importoradd"><li>
                                <a href="<%=contextPath%>/business/score/importoradd">积分历史导入</a>
                            </li></shiro:hasPermission>
                            <shiro:hasPermission name="/business/score/importorconsume"><li>
                                <a href="<%=contextPath%>/business/score/importorconsume">权益消费导入</a>
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
        _p_parent.addClass('active').addClass('open');
        _parent.show();

        return false;
    });

</script>
