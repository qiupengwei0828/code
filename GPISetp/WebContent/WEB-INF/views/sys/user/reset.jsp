<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户密码重置</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
.body_title {
	margin-left: 50px;
	margin-right: 50px;
	padding: 30px 30px 30px 30px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").click(function() {
			$('#alertTitle').modal('show');
		});
		$("#trueBtn").click(function() {
			$.ajax({
				type : "POST",
				dataType : 'json',
				url : '${ctx}/sys/user/resetAllIsNull',
				success : function(data) {
					if (data.success == "success") {
						$('#alertTitle').modal('hide');
						window.location.href = "${ctx}/sys/user/list"
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="body_title">
		<h3>提示：</h3>
		<h4>1.此操作不可逆！</h4>
		<h4>2.重置所有未设置密码的员工密码为默认密码！</h4>
		<h4>3.默认密码为：123456！</h4>
		<h4>4.重置密码可能会持续几分钟，期间不可关闭页面！</h4>

		<div class="form-actions">
			<img alt="" src="${ctxStatic}/images/alert.png" style="width: 32px;height: 32px;">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-danger" type="button" value="重置密码" onclick="onclickSubmit()" />
		</div>
	</div>

	<div id="alertTitle" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">重置密码</h3>
		</div>
		<div class="modal-body">
			<h5>此操作不可逆，确认要执行重置密码?</h5>
			<h5>重置密码时请耐心等待！</h5>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button id="trueBtn" class="btn btn-primary">确认</button>
		</div>
	</div>


</body>
</html>