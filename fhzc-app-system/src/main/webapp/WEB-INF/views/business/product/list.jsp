<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String contextPath = request.getContextPath();
%>

<jsp:include page="../../include/header.jsp"/>
<jsp:include page="../../include/left.jsp"/>


<div id="page-wrapper">

    <!-- 导入结果提示 -->
    <div class="modal fade in" id="import_result" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" style="display: none;top:20%">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">导入结果</h4>
                </div>
                <div class="modal-body">
                    <ul>
                        <li>本次导入表格共<span id="totalRows"></span>条</li>
                        <li>创建deal成功数量<span id="successRows"></span></li>
                        <li>创建失败数量<span id="failRows"></span></li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button id="confirm_button" type="button" class="btn btn-primary">确定</button>
                </div>
            </div>
        </div>
    </div>


    <div class="col-lg-12" style="margin: 5px"></div>

    <div class="row">
        <form action="business/product/import" enctype="multipart/form-data" method="post" id="form">
            <div class="col-lg-12" >
                <span class="modal-title">请选择Excel文件:</span>
                <input type="file" name="multiFile" id="multiFile" class="file-loading" style="display: inline;"/>
            </div>
        </form>
    </div>

    <div class="col-lg-12" style="margin: 10px"></div>

    <div class="row">
        <div class="col-lg-12">
            <table class="table table-bordered table-hover table-condensed" style="font-size:12px;">
                <thead>
                <tr>
                    <td><input type="checkbox" id="checkAll"/>全选</td>
                    <td>产品名称</td>
                    <td>状态</td>
                    <td>起息日</td>
                    <td>到期日</td>
                    <td>派息日</td>
                    <td>添加时间</td>
                    <td>客户等级</td>
                    <td>客户风险等级</td>
                    <td>编辑</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.status}</td>
                        <td>${data.website}</td>
                        <td></td>
                        <td></td>
                        <td>${data.title}</td>
                        <td>${fn:substring(data.createTime, 0, 10)}</td>
                        <td>${fn:substring(data.expireTime, 0, 10)}</td>
                        <td><a href="business/product/detail/${data.id}">编辑</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="../../include/page.jsp"/>
    </div>

    <script>
        $(function(){
            $('#multiFile').change(function(){
                $("#form").ajaxSubmit({
                    //定义返回JSON数据，还包括xml和script格式
                    dataType:'json',
                    beforeSend: function() {
                        //表单提交前做表单验证
                    },
                    success: function(data) {
                        if(data.success){
                            $("#totalRows").html(data.totalRows);
                            $("#successRows").html(data.successRows);
                            $("#failRows").html(data.failRows);
                            $('#import_result').modal('show');
                            $("#confirm_button").click(function(){
                                $('#import_result').modal('hide');
                                window.location.reload();
                            });
                        }else{
                            BootstrapDialog.show({
                                title: '导入失败',
                                message: '请检查Excel格式，重新导入!'
                            });
                        }


                    }
                });
            });

        });


    </script>
    <jsp:include page="../../include/footer.jsp"/>
