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
                            <a href="javascript:void(0);">资产管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li class="active"><a href="javascript:void(0);">推荐配置</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <!--页面操作详细内容 开始-->
            <div class="row-fluid">
                <div class="span12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
                 
                    <!-- END SAMPLE FORM PORTLET-->
                    <div class="portlet box blue">
                        <div class="portlet-title">
                            <h4><i class="icon-reorder"></i></h4>
                        </div>
                        
                        <div class="portlet-body" style="height: 630px; overflow: scroll">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td>序号</td>
                                    <td>推荐配置类别</td>
                                    <td>推荐配置比例(%)</td>
                               		<td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${assetsRecommends}" var="assetsRecommend"   begin="0" end="${listSize}" step="1" varStatus="status">
                                        <td>${status.index+1}</td>
                                        <td>${assetsRecommend.recommendType}</td>
                                        <td>${assetsRecommend.proportion}</td>
                                        <td>
                                            <a href="#modal_edit" role="button" class="btn mini purple mod_ptype" data-toggle="modal" data-id="${assetsRecommend.id}" data-type="${assetsRecommend.recommendType}" data-proportion="${assetsRecommend.proportion}" data-status="${assetsRecommend.status}"><i class="icon-edit"></i> 编辑</a>
                                           <!--  
                                            <a href="#modal_del" role="button" class="btn mini black del_ptype" data-toggle="modal" data-id="${assetsRecommend.id}" ><i class="icon-trash"></i> 删除</a>
                                            -->
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--页面操作详细内容 开始-->
 			<div id="modal_edit" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="my_modal_edit" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3 id="myModalLabel2">编辑</h3>
                </div>
                <div class="modal-body">
                    <p><label class="control-label">配置比例</label><input type="number" id="assetsRecommend_proportion" value="" class="m-wrap large"></p>
                    <input type="hidden" id="assetsRecommend_id" />
                    <input type="hidden" id="assetsRecommend_status"/>
                    <input type="hidden" id="assetsRecommend_recommendType"/>
                    
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn green" id="do_mod_productType">确定</button>
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </div>
            <div id="modal_del" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="my_modal_del" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h3 id="myModalLabel3">确定删除</h3>
                </div>
                <div class="modal-body">
                    <p>请确认删除操作,删除后不可恢复</p>
                    <input type="hidden" id="ptype_del_id" />
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                    <button data-dismiss="modal" class="btn blue" id="do_del_productType">删除</button>
                </div>
            </div>
        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script>
    $(function(){
        $(".mod_ptype").click(function(){
            $("#assetsRecommend_recommendType").val($(this).data('type'));
            $("#assetsRecommend_proportion").val($(this).data('proportion'));
            $("#assetsRecommend_id").val($(this).data('id'));
            $("#assetsRecommend_status").val($(this).data('status'));
        });

        $(".del_ptype").click(function(){
            $("#ptype_del_id").val($(this).data('id'))
        });

        $("#do_mod_productType").click(function () {
   			$.post("<%=contextPath%>/business/assets/recommend/add",{'id':$("#assetsRecommend_id").val(),'recommendType':$("#assetsRecommend_recommendType").val(),'proportion':$("#assetsRecommend_proportion").val(),'status':$("#assetsRecommend_status").val()},function (data) {window.location.reload();})
        	
        });

    //以下内容备用    
        $("#do_del_productType").click(function () {
        	alert($("#assetsRecommend_id").val());
            $.get("<%=contextPath%>/business/assets/recommend/delete/" + $("#ptype_del_id").val(),function (data) {
                if(!data.success){
                    BootstrapDialog.alert({
                        title: '提示',
                        message: data.message
                    });
                    return false;
                }
                window.location.reload();
            })
        });
        
        var form1 = $('#form_sample_1');
        var error1 = $('.alert-error', form1);
        var success1 = $('.alert-success', form1);

        form1.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-inline', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            messages: {
                value:{required:"产品类型值不能为空！",remote:jQuery.format("产品类型值已经存在")},
                key:{required:"产品类型不能为空",remote:jQuery.format("产品类型已经存在")},
            },
            rules: {
                value: {
                    required: true,
                    remote: {
                        url: "/business/product/isValueExists",
                        type: "get",
                        data: {
                            value: function() {
                                var old_value = '${productType.value}';
                                var new_value = $("input[name='value']").val();
                                if(old_value == new_value){
                                    return old_value + 'no check';
                                }else {
                                    return new_value;
                                }
                            }
                        }
                    }
                },
                key: {
                    required: true,
                    remote: {
                        url: "/business/product/isKeyExists",
                        type: "get",
                        data: {
                            key: function() {
                                var old_key = '${productType.key}';
                                var new_key = $("input[name='key']").val();
                                if(old_key == new_key){
                                    return old_key + 'no check';
                                }else {
                                    return new_key;
                                }
                            }
                        }
                    }
                },
                proportion: {
                    number: true,
                    min:0
                },
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
        
    });


</script>