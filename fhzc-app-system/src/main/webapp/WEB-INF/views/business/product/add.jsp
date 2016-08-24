<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
    String basePath  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>

<jsp:include page="../../include/header.jsp"/>
<!-- BEGIN BODY -->
<body class="fixed-top">
<jsp:include page="../../include/nav.jsp"/>

<!--扩展样式-->
<link href="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/data-tables/DT_bootstrap.css" />
<link href="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.css" />
<link rel="stylesheet" href="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/bootstrap-datepicker/css/datepicker.css">

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/zTree/css/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/static/zTree/css/demo.css">

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
                        <li class="active"><a href="javascript:void(0);" id="product_title">新增产品</a></li>
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
                                <span class="hidden-480"></span>
                                &nbsp;
                            </h4>
                        </div>
                        <div class="portlet-body form" style="height: 730px; overflow-y: scroll">
                            <div class="tabbable portlet-tabs">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="portlet_tab1">
                                        <!-- BEGIN FORM-->
                                        <form action="<%=contextPath%>/business/product/add" id="form_sample_1" enctype="multipart/form-data" method="POST" class="form-horizontal">
                                            <div class="alert alert-error hide">
                                                <button class="close" data-dismiss="alert"></button>
                                                您的表单有未完成的必填项,请检查.
                                            </div>
                                            <div class="alert alert-success hide">
                                                <button class="close" data-dismiss="alert"></button>
                                                表单内容验证成功!
                                            </div>

                                            <div class="control-group">
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品名<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="name" value="${product.name}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline hide">该名称已存在,请换一个名字</span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品代码</label>
                                                <div class="controls">
                                                    <input type="text" name="code" value="${product.code}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline hide">该代码已存在,请换一个代码</span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">产品类型</label>
                                                <div class="controls">
                                                    <select class="large m-wrap" name="productType" id="productType" tabindex="1">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">基金状态</label>
                                                <div class="controls">
                                                    <select  class="large m-wrap" name="status" id="status" tabindex="1">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">参考业绩标准(%)</label>
                                                <div class="controls">
                                                    <input type="text" name="annualYield" value="${product.annualYield}" placeholder="" class="large m-wrap">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投资期限(月)<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="number" name="investTermMin" value="${product.renewDeadline}" data-required="1" placeholder="" class="m-wrap small">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">募集期的开始和结束</label>
                                                <div class="controls">
                                                    <input type="text" name="collectStart"  placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${product.collectStart}" pattern="yyyy-MM-dd"/>"> ~
                                                    <input type="text" name="collectEnd"  placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${product.collectEnd}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">起投金额(万元)<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="number" name="investThreshold" value="${product.investThreshold / 10000}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">存续期限(月)</label>
                                                <div class="controls">
                                                    <input type="text" name="renewDeadline" value="${product.renewDeadline}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">成立日</label>
                                                <div class="controls">
                                                    <input type="text" name="foundDay"  placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${product.foundDay}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">派息日</label>
                                                <div class="controls">
                                                    <input id="dividend_day_tags" name="dividendDay" type="text" data-default="添加一个派息日" class="m-wra tags medium" value="${product.dividendDay}" />
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">开放申购日</label>
                                                <div class="controls">
                                                    <input id="buy_day_tags" name="buyDay" type="text" data-default="添加一个开放申购日" class="m-wra tags medium" value="${product.buyDay}" />
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">赎回日</label>
                                                <div class="controls">
                                                    <input id="redeem_day_tags" name="redeemDay" type="text" data-default="添加一个赎回日" class="m-wra tags medium" value="${product.redeemDay}" />
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">到期日</label>
                                                <div class="controls">
                                                    <input type="text" name="expiryDay" placeholder="" size="16" class="m-wrap m-ctrl-medium date-picker" value="<fmt:formatDate value="${product.expiryDay}" pattern="yyyy-MM-dd"/>">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">发行模式</label>
                                                <div class="controls">
                                                    <select  class="large m-wrap" name="issueType" id="issueType"  tabindex="1">
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">基金管理费(%)<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="fundManagementFee" value="${product.fundManagementFee}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">基金认购费(%)<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="fundSubscriptionFee" value="${product.fundSubscriptionFee}"  data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">基金管理人<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="fundManager"  value="${product.fundManager}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">基金托管人<span class="required">*</span></label>
                                                <div class="controls">
                                                    <input type="text" name="custodian" value="${product.custodian}" data-required="1" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投资方向</label>
                                                <div class="controls">
                                                    <input type="text" name="investmentOrientation" value="${product.investmentOrientation}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">收益分配方式</label>
                                                <div class="controls">
                                                    <input type="text" name="incomeDistributionType" value="${product.incomeDistributionType}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">增信措施</label>
                                                <div class="controls">
                                                    <input type="text" name="credit" value="${product.credit}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">产品亮点</label>
                                                <div class="controls">
                                                    <input type="text" name="highlights" value="${product.highlights}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">投放分公司</label>
                                                <div class="controls">
                                                    <input type="text" id="department" readonly onclick="showTreeData(); return false;" class="large m-wrap"/>
                                                    <input type="hidden" name="throwDepartment" id="department_value" />
                                                </div>

                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">客户等级要求</label>
                                                <div class="controls">
                                                    <select name="level" id="customerLevel" class="large m-wrap"  tabindex="1">
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">风险评级要求</label>
                                                <div class="controls">
                                                    <select name="risk" id="riskLevel" class="large m-wrap" tabindex="1">
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">积分系数(%)</label>
                                                <div class="controls">
                                                    <input type="text" name="scoreFactor" value="${product.scoreFactor}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">产品简介</label>
                                                <div class="controls">
                                                    <textarea name="desc"  class="span12 ckeditor m-wrap" rows="3">${product.desc}</textarea>
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">产品封面</label>
                                                <div class="controls">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;">
                                                            <c:choose>
                                                                <c:when test="${product.cover == null}" >
                                                                    <img src="/static/image/no-image.png" alt="" id="default_img" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="<%=basePath%>${product.cover}" alt="" id="default_img" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                                        <div>
                                                       <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                                                       <span class="fileupload-exists">更换</span>
                                                       <input type="file" name="coverFile" id="cover_img" class="default" /></span>
                                                       <input type="hidden" name="cover" value="${procuct.cover}" />
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
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <div class="input-append">
                                                            <div class="uneditable-input">
                                                                <i class="icon-file fileupload-exists"></i>
                                                                <span class="fileupload-preview"></span>
                                                            </div>
                                                           <span class="btn btn-file">
                                                           <span class="fileupload-new">选择文件</span>
                                                           <span class="fileupload-exists">更换</span>
                                                           <input type="file" name="noticeFile" class="default" />
                                                           </span>
                                                            <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">移除</a>
                                                        </div>
                                                    </div>
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
                                                    <textarea name="detailContent" class="span12 ckeditor m-wrap" rows="3">${product.detailContent}</textarea>
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">详情链接</label>
                                                <div class="controls">
                                                    <input type="text" name="detailUrl" value="${product.detailUrl}" placeholder="" class="m-wrap large">
                                                    <span class="help-inline"></span>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">是否显示</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="isDisplay" value="1" />
                                                        是
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="isDisplay" value="0" checked />
                                                        否
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">是否推荐(精选)</label>
                                                <div class="controls">
                                                    <label class="radio">
                                                        <input type="radio" name="isRecommend" value="1" />
                                                        是
                                                    </label>
                                                    <label class="radio">
                                                        <input type="radio" name="isRecommend" value="0" checked />
                                                        否
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-actions">
                                                <input name="pid" type="hidden" value="${product.pid}" />
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
                    <div id="treeContent" class="treeContent" style="display:none; position: absolute;">
                    <ul id="treeDemo" class="ztree" style="margin-top:0;"></ul>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->

        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/assets/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/zTree/js/tree.js"></script>
<script>
$(function(){

    var productId =  '${product.pid}';
    if(productId != null && productId != ''){
        $('#product_title').text('产品编辑');
    }

    var productTypesVal = '${product.productType}';
    var productTypes = '${productTypes}';
    var pTypeJson= $.parseJSON(productTypes);
    $.each(pTypeJson, function(i,val){
        $("#productType").append("<option value='"+val.value+"'>"+val.key+"</option>");
        if(productTypesVal == val.value){
            $("#productType").val(productTypesVal);
        }
    });

    var productStatusVal = '${product.status}';
    var productStatus = '${productStatus}';
    var statusJson= $.parseJSON(productStatus);
    $.each(statusJson, function(i,val){
        $("#status").append("<option value='"+val.value+"'>"+val.key+"</option>");
        if(productStatusVal == val.value){
            $("#status").val(productStatusVal);
        }
    });


    var throwDepartment = '${product.throwDepartment}';
    var treeNodes = '${departments}';
    treeNodes = $.parseJSON(treeNodes);
    setOrganValue(throwDepartment, treeNodes);
    $.fn.zTree.init($("#treeDemo"), setting, treeNodes);

    var issueTypeVal = '${product.issueType}';
    var issueType = '${productIssueType}';
    var issueTypeJson= $.parseJSON(issueType);
    $.each(issueTypeJson, function(i,val){
        $("#issueType").append("<option value='"+val.value+"'>"+val.key+"</option>");
        if(issueTypeVal == val.value){
            $("#issueType").val(issueTypeVal);
        }
    });

    var level = '${product.level}';
    var customerLevel = '${customerLevel}';
    var customerLevelJson= $.parseJSON(customerLevel);
    $.each(customerLevelJson, function(i,val){
        $("#customerLevel").append("<option value='"+val.value+"'>"+val.key+"</option>");
        if(level == val.value){
            $("#customerLevel").val(level);
        }
    });

    var risk = '${product.risk}';
    var riskLevel = '${riskLevel}';
    var riskLevelJson= $.parseJSON(riskLevel);
    $.each(riskLevelJson, function(i,val){
        $("#riskLevel").append("<option value='"+val.value+"'>"+val.key+"</option>");
        if(risk == val.value){
            $("#riskLevel").val(risk);
        }
    });

    var isDisplay = '${product.isDisplay}';
    if(isDisplay == 1){
        $.uniform.update($("input[name='isDisplay'][value='1']").attr("checked", true));
        $.uniform.update($("input[name='isDisplay'][value='0']").attr("checked", false));
    }else{
        $.uniform.update($("input[name='isDisplay'][value='1']").attr("checked", false));
        $.uniform.update($("input[name='isDisplay'][value='0']").attr("checked", true));
    }

    var isRecommend = '${product.isRecommend}';
    if(isRecommend == 1){
        $.uniform.update($("input[name='isRecommend'][value='1']").attr("checked", true));
        $.uniform.update($("input[name='isRecommend'][value='0']").attr("checked", false));
    }else{
        $.uniform.update($("input[name='isRecommend'][value='1']").attr("checked", false));
        $.uniform.update($("input[name='isRecommend'][value='0']").attr("checked", true));
    }

    var form1 = $('#form_sample_1');
    var error1 = $('.alert-error', form1);
    var success1 = $('.alert-success', form1);

    form1.validate({
        errorElement: 'span', //default input error message container
        errorClass: 'help-inline', // default input error message class
        focusInvalid: false, // do not focus the last invalid input
        ignore: "",
        messages: {
            name:{required:"产品名不能为空！",remote:jQuery.format("产品名已经存在")},
            code:{required:"",remote:jQuery.format("产品代码已经存在")}
        },
        rules: {
            name: {
                required: true,
                remote: {
                    url: "/business/product/isNameExists",
                    type: "get",
                    data: {
                        name: function() {
                            var old_name = '${product.name}';
                            var new_name = $("input[name='name']").val();
                            if(old_name == new_name){
                                return old_name + 'no check';
                            }else {
                                return new_name;
                            }
                        }
                    }
                }
            },
            code: {
                required: false,
                remote: {
                    url: "/business/product/isCodeExists",
                    type: "get",
                    data: {
                        code: function() {
                            var old_code = '${product.code}';
                            var new_code = $("input[name='code']").val();
                            if(old_code == new_code){
                                return old_code + 'no check';
                            }else {
                                return new_code;
                            }
                        }
                    }
                }
            },
            expectedMin: {
                number: true,
                min:0
            },
            expectedMax: {
                number: true,
                min:0
            },
            investTermMin: {
                required: true,
                number: true,
                min:0
            },
            investTermMax: {
                required: true,
                number: true,
                min:1
            },
            investThreshold: {
                required: true,
                number: true,
                min:0
            },
            renewDeadline: {
                number: true,
                min:0
            },
            scoreFactor: {
                number: true,
                min:0
            },
            detailUrl: {
                url: true
            },
            fundManagementFee: {
                required: true,
                number: true,
                min:0
            },
            fundSubscriptionFee: {
                required: true,
                number: true,
                min:0
            },
            fundManager: {
                required: true
            },
            custodian: {
                required: true
            }
        },

        invalidHandler: function (event, validator) { //display error alert on form submit
            success1.hide();
            error1.show();
            App.scrollTo(error1, -200);
        },

        highlight: function (element) { // hightlight error inputs
            $(element)
                    .closest('.help-inline').removeClass('ok'); // display OK icon
            $(element)
                    .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
        },

        unhighlight: function (element) { // revert the change dony by hightlight
            $(element)
                    .closest('.control-group').removeClass('error'); // set error class to the control group
        },

        success: function (label) {
            label
                    .addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                    .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
        },

        submitHandler: function (form) {
            success1.show();
            error1.hide();
            form.submit();
        }
    });

    $('#dividend_day_tags,#buy_day_tags,#redeem_day_tags').tagsInput({
        width: 300,
        defaultText: "请添加日期",
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