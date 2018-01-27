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
			$("#shrNo").val("");
			$("#suserName").val("");
			$("#sposName").val("");
			$("#searchForm").submit();
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

	<form:form id="searchForm" modelAttribute="user" action="${ctx}/posrot/res/list?orgNo=${user.orgNo}" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form id="sForm" modelAttribute="user" action="${ctx}/posrot/res/list" method="post" class="breadcrumb form-search">

		<lable>人力资源编号：</lable>
		<form:input path="hrNo" htmlEscape="false" maxlength="50" style="width:120px" id="shrNo" />

		<label>员工姓名：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:120px" id="suserName" />

		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
		
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>姓名</th>
			<th>人力资源编号</th>
			<th>性别</th>
			<th>身份证号</th>
			<th>机构</th>
			<th>部门</th>
			<th>岗位</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.userName}</td>
				<td>${user.hrNo}</td>
				<td>${fns:getDicLabel(user.sex,"DIC_USER_SEX", "")}</td>
				<td>${user.certNo}</td>
				<td>${user.orgName}</td>
				<td>${user.orgName2nd}</td>
				<td>${user.posName}</td>
				<td><a href="${ctx}/posrot/res/reslist?userId=${user.userId}">员工履历</a></td>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>