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
                            <a href="javascript:void(0);">业务管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">新增产品</a></li>
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
                                <i class="icon-reorder"></i>
                                <span class="hidden-480">新增产品</span>
                                &nbsp;
                            </h4>
                        </div>
                        <div class="portlet-body form">
                            <div class="tabbable portlet-tabs">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <!-- BEGIN FORM-->
                                        <form action="add" enctype="multipart/form-data"  method="POST" class="form-horizontal">
                                            <div class="control-group">
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品名</label>
                                                <div class="controls">
                                                    <input type="text" name="name" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品代码</label>
                                                <div class="controls">
                                                    <input type="text" name="code" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品类型</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="productType" tabindex="1">
                                                        <option  value="1">鑫丰母基金(开放式契约型)</option>
                                                        <option  value="2">封闭式有限合伙私募基金</option>
                                                        <option  value="3">封闭式契约型私募基金</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">基金状态</label>
                                                <div class="controls">
                                                    <select  class="large m-wrap" name="status" tabindex="1">
                                                        <option value="-1">未知</option>
                                                        <option value="0">产品预热</option>
                                                        <option value="1">募集中</option>
                                                        <option value="2">募集结束</option>
                                                        <option value="3">募集失败</option>
                                                        <option value="4">产品成立</option>
                                                        <option value="5">产品到期</option>
                                                        <option value="6">提前结束</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">*预期最低年化收益率（单位：%）</label>
                                                <div class="controls">
                                                    <input type="text" placeholder="" class="m-wrap small"> ~ <input type="text" placeholder="" class="m-wrap small">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投资期限（月）</label>
                                                <div class="controls">
                                                    <input type="number" name="investTermMin" placeholder="" class="m-wrap small"> ~ <input type="number" name="investTermMax" placeholder="" class="m-wrap small">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">起投金额（万元）</label>
                                                <div class="controls">
                                                    <input type="number" name="investThreshold" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">存续期限</label>
                                                <div class="controls">
                                                    <input type="text" name="renewDeadline" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">成立日</label>
                                                <div class="controls">
                                                    <input type="text" name="foundDay" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="2016-08-01">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">起息日</label>
                                                <div class="controls">
                                                    <input type="text" name="valueDay" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="2016-08-01">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">派息日</label>
                                                <div class="controls">
                                                    <input id="dividend_day_tags" name="dividendDay" type="text" data-default="添加一个派息日" class="m-wra tags medium" value="2016-08-01,2016-09-01" />
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">开放申购日</label>
                                                <div class="controls">
                                                    <input type="text" name="buyDay" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="2016-08-01">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">赎回日</label>
                                                <div class="controls">
                                                    <input type="text" name="redeemDay" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="2016-08-01">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">到期日</label>
                                                <div class="controls">
                                                    <input type="text" name="expiryDay" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="2016-08-01">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">发行模式</label>
                                                <div class="controls">
                                                    <input type="text" name="issueType" placeholder="契约型基金" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">基金管理费</label>
                                                <div class="controls">
                                                    <input type="text" name="fundManagementFee" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">基金认购费</label>
                                                <div class="controls">
                                                    <input type="text" name="fundSubscriptionFee"  placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">基金管理人</label>
                                                <div class="controls">
                                                    <input type="text" name="fundManager" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">基金托管人</label>
                                                <div class="controls">
                                                    <input type="text" name="custodian" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投资方向</label>
                                                <div class="controls">
                                                    <input type="text" name="investmentOrientation" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">收益分配方式</label>
                                                <div class="controls">
                                                    <input type="text" name="incomeDistributionType" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">增信措施</label>
                                                <div class="controls">
                                                    <input type="text" name="credit" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">产品亮点</label>
                                                <div class="controls">
                                                    <input type="text" name="highlisghts" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投放分公司</label>
                                                <div class="controls">
                                                    <select name="departments"  data-placeholder="分公司信息" class="chosen span6" multiple="multiple" tabindex="6">
                                                        <option value=""></option>
                                                        <optgroup label="NFC EAST">
                                                            <option>Dallas Cowboys</option>
                                                            <option>New York Giants</option>
                                                            <option>Philadelphia Eagles</option>
                                                            <option>Washington Redskins</option>
                                                        </optgroup>
                                                        <optgroup label="NFC NORTH">
                                                            <option selected>Chicago Bears</option>
                                                            <option>Detroit Lions</option>
                                                            <option>Green Bay Packers</option>
                                                            <option>Minnesota Vikings</option>
                                                        </optgroup>
                                                    </select>
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户等级要求</label>
                                                <div class="controls">
                                                    <select name="level" class="large m-wrap" tabindex="1">
                                                        <option  value="3">金卡客户</option>
                                                        <option  value="2">客户</option>
                                                        <option  value="1">准客户</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">风险评级要求</label>
                                                <div class="controls">
                                                    <select name="risk" class="large m-wrap" tabindex="1">
                                                        <option  value="3">A</option>
                                                        <option  value="2">B</option>
                                                        <option  value="1">C</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">积分系数</label>
                                                <div class="controls">
                                                    <input type="text" name="scoreFactor" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">产品简介</label>
                                                <div class="controls">
                                                    <textarea name="desc" class="span6 m-wrap" rows="3"></textarea>
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">产品封面</label>
                                                <div class="controls">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                                            <img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt="" />
                                                        </div>
                                                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                                        <div>
                                                       <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                                                       <span class="fileupload-exists">更换</span>
                                                       <input type="file" name="coverFile" class="default" /></span>
                                                            <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>
                                                        </div>
                                                    </div>
                                                    <span class="label label-important">注意!</span>
                                                 <span>
                                                 上传图片预览仅支持Firefox, Chrome, Opera,
                                                 Safari ,Internet Explorer 10
                                                 </span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">成立公告</label>
                                                <div class="controls">
                                                    <textarea name="notice" class="span6 m-wrap" rows="3"></textarea>
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>


                                            <div class="control-group">
                                                <label class="control-label">备案与否</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="isRecord" tabindex="1">
                                                        <option  value="0">否</option>
                                                        <option  value="1">是</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">备案证明</label>
                                                <div class="controls">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="input-append">
                                                            <div class="uneditable-input">
                                                                <i class="icon-file fileupload-exists"></i>
                                                                <span class="fileupload-preview"></span>
                                                            </div>
                                                           <span class="btn btn-file">
                                                           <span class="fileupload-new">选择文件</span>
                                                           <span class="fileupload-exists">更换</span>
                                                           <input type="file" name="proveFile" class="default" />
                                                           </span>
                                                            <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">详细内容</label>
                                                <div class="controls">
                                                    <textarea name="detailContent" class="span6 m-wrap" rows="3"></textarea>
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">详情链接</label>
                                                <div class="controls">
                                                    <input type="text" name="detailUrl" placeholder="" class="m-wrap large">
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
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
$(function(){
    $('#dividend_day_tags').tagsInput({
        width: 300,
        defaultText: "添加派息日",
        'onAddTag': function (tag) {
            var patterns = /^\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
            if(!patterns.test(tag)){
                alert("请输入 2008-01-31 这种格式的日期 ");
                $(this).removeTag(tag);
            }

        }
    });
})
</script>