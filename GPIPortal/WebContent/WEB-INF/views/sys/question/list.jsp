<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>问题列表</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}
</script>

</head>
<body>

	<form:form id="pageForm" modelAttribute="que" action="${ctx}/sys/question/list" method="post" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>标题</th>
			<th>所属系统</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="que">
			<tr>
				<td>${que.title}</td>
				<td>${que.sysName}</td>
				<td>${fns:getDicLabel(que.status,'DIC_QUE_STATUS', '')}</td>
				<td><a href="${ctx}/sys/question/info?id=${que.id}">详情</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>