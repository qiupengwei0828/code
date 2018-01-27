<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	/*
	 * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/usergrant/userlist" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<input id="roleCode" name="roleCode" type="hidden" value="${user.roleCode}" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width: 100%;height: 100%">
		<tr>
			<td>角色</td>
			<td>用户编号</td>
			<td style="display: none">角色CODE</td>
			<td>用户姓名</td>
			<td>性别</td>
			<td>所属机构</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.roleCode}</td>
				<td>${user.userId}</td>
				<td style="display: none">${user.roleCode}</td>
				<td>${user.userName}</td>
				<td>${fns:getDicLabel(user.sex,"DIC_USER_SEX", "")}</td>
				<td>${user.orgName}</td>
				<td><a href="${ctx}/sys/usergrant/delete?userId=${user.userId}&roleCode=${user.roleCode}" onclick="return confirmx('确认要移除该用户吗？', this.href)">移除</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>