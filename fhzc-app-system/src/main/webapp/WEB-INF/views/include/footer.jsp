<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>

<!-- BEGIN FOOTER -->
<div class="footer">
    <a href="http://www.foriseassets.com">
        复华资产
    </a> Copyright.
    <div class="span pull-right">
        <span class="go-top"><i class="icon-angle-up"></i></span>
    </div>
</div>
<!-- END FOOTER -->

<script src="<%=contextPath%>/assets/js/jquery-1.8.3.min.js"></script>

<script src="<%=contextPath%>/assets/breakpoints/breakpoints.js"></script>
<script src="<%=contextPath%>/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/assets/js/jquery.blockui.js"></script>
<script src="<%=contextPath%>/assets/js/jquery.cookie.js"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="<%=contextPath%>/assets/js/excanvas.js"></script>
<script src="<%=contextPath%>/assets/js/respond.js"></script>
<![endif]-->


<script type="text/javascript" src="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/data-tables/DT_bootstrap.js"></script>


<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/jquery-tags-input/jquery.tagsinput.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/bootstrap-toggle-buttons/static/js/jquery.toggle.buttons.js"></script>
<script type="text/javascript" src="<%=contextPath%>/assets/fancybox/source/jquery.fancybox.pack.js"></script>
<script src="<%=contextPath%>/assets/js/app.js"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
    });
</script>


</body>
</html>
