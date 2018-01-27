<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>未发送短信详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("input").attr("readonly", "readonly");
		$("textarea").attr("readonly", "readonly");
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sms/message/list">未发送短信列表</a></li>
		<li class="active"><a>短信详情</a></li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="message" action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">归属系统</label>
			<div class="controls">
				<form:input path="appCode" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短信接收手机</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">短信内容</label>
			<div class="controls">
				<form:textarea path="content" rows="12" htmlEscape="false" class="input-xlarge" style="width:50%;heigth:300px;" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">信息类型</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">创建时间</label>
			<div class="controls">
				<form:input path="crtDate" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<%-- <div class="control-group">
			<label class="control-label">短信是否需要审核</label>
			<div class="controls">
				<form:input path="checkType" htmlEscape="false" maxlength="50" />
			</div>
		</div> --%>

		<div class="control-group">
			<label class="control-label">状态</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<div class="form-actions">
			<input class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>

	</form:form>

</body>
</html>