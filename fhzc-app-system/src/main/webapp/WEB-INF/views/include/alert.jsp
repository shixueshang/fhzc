<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String contextPath = request.getContextPath();
%>
	<!-- 按钮触发模态框 -->
	<button id="modalclick" class="btn btn-primary btn-lg" data-toggle="modal" 
	   data-target="#myModal" style="display:none">
	   	开始演示模态框
	</button>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <h4 class="modal-title" id="myModalLabel">
	              	 导入结果
	            </h4>
	         </div>
	         <c:if test="${success == true}">
	         <div class="modal-body">
	            	本次供操作记录${totalRows}条，其中成功${successRows}条，失败${failRows}条
	         </div>
	         </c:if>
			 <c:if test="${success == false}">
			 <div class="modal-body">
			  <c:if test="${error_message == ''}">
				操作失败，请确认Excel中的数据正确且符合要求后重新导入！
			  </c:if>
			  <c:if test="${error_message != ''}">
				操作失败，${error_message}
			  </c:if>			  
			 </div>
			 </c:if>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">关闭
	            </button>
	         </div>
	      </div>
		</div><!-- /.modal -->
	</div>
	<script type="text/javascript">
         $("#modalclick").click();
	</script>