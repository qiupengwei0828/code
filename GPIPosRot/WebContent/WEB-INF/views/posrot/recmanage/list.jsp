<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统员工管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#suserName").val("");
			$("#shrNo").val("");
			$("#sposName").val("");
			$("form:first").submit();
		});
	});

	//分页
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
<body>

	<form:form id="searchForm" modelAttribute="user" action="${ctx}/posrot/recManage/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form id="sForm" modelAttribute="user" action="${ctx}/posrot/recManage/list" method="post" class="breadcrumb form-search">

		<label>人力资源编号：</label>
		<form:input path="hrNo" htmlEscape="false" maxlength="50" style="width:120px" id="shrNo" />

		<label>员工名称：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:120px" id="suserName" />

		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="reset" value="重置" />

	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>人力资源编号</th>
			<th>姓名</th>
			<th>岗位</th>
			<th>性别</th>
			<th>身份证号码</th>
			<th>用工类别</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.hrNo}</td>
				<td>${user.userName}</td>
				<td>${user.posName}</td>
				<td>${fns:getDicLabel(user.sex,"DIC_USER_SEX", "")}</td>
				<td>${user.certNo}</td>
				<td>${fns:getDicLabel(user.userClass,"DIC_USER_CLASS", "")}</td>
				<td><a href="${ctx}/posrot/recManage/form?userId=${user.userId}">制定计划</a></td>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>