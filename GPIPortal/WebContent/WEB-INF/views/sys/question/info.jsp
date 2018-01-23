<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>问题详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
#showContent {
	width: 95%;
	padding: 10px 10px;
	margin: 0 auto;
}

#title {
	width: 80%;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var id = "${que.id}";

		$("#confirm").click(function() {
			var status = "2";
			window.location.href = "${ctx}/sys/question/update?status=" + status + "&id=" + id;
		});

		$("#other").click(function() {
			var status = "4";
			window.location.href = "${ctx}/sys/question/update?status=" + status + "&id=" + id;
		});

		$("#done").click(function() {
			var status = "3";
			window.location.href = "${ctx}/sys/question/update?status=" + status + "&id=" + id;
		});

	})
</script>

</head>
<body>


	<br>


	<form id="inputForm" action="" method="post" class="form-horizontal" enctype="multipart/form-data">


		<div class="control-group">
			<label class="control-label">标题</label>
			<div class="controls">
				<input id="title" type="text" name="title" class="required" readonly="readonly" value="${que.title}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所属应用</label>
			<div class="controls">
				<input id="sysName" type="text" name="sysName" class="required" readonly="readonly" value="${que.sysName}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">状态</label>
			<div class="controls">
				<input id="status" type="text" name="status" class="required" readonly="readonly" value="${fns:getDicLabel(que.status,'DIC_QUE_STATUS', '')}" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">问题详情</label>
			<div class="controls">
				<div id="showContent">${que.content}</div>
			</div>
		</div>

		<div class="form-actions">
			<c:set value="${que.status}" var="status" />
			<c:if test="${status == '1'}">
				<input id="confirm" class="btn btn-primary" type="button" value="确 认" />
				<input id="other" class="btn btn-primary" type="button" value="非代码问题" />
			</c:if>
			<c:if test="${status == '2'}">
				<input id="done" class="btn btn-primary" type="button" value="已处理" />
			</c:if>
		</div>

	</form>

</body>
</html>