<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#suserName").val("");
			$("#smobile").val("");
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list">用户列表</a></li>
		<li><a href="${ctx}/sys/user/form">用户添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dic" action="${ctx}/sys/user/list?orgNo=${user.orgNo}" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form id="sForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search">
		<label>用户名称：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:120px" id="suserName" />
		<label>电话号码：</label>
		<form:input path="mobile" htmlEscape="false" maxlength="50" style="width:120px" id="smobile" />
		<label>用户状态:</label>
		<form:select path="status" class="input-medium">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>用户ID</th>
			<th>用户名称</th>
			<th>所属机构</th>
			<th>机构名称</th>
			<th>业务线条</th>
			<th>用工类别</th>
			<th>学历</th>
			<th>职务</th>
			<th>职级</th>
			<th>手机</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.userId}</td>
				<td>${user.userName}</td>
				<td>${user.orgNo}</td>
				<td>${user.orgName}</td>
				<td>${fns:getDicLabel(user.depCode,"DIC_USER_DEP_CODE", "")}</td>
				<td>${fns:getDicLabel(user.userClass,"DIC_USER_CLASS", "")}</td>
				<td>${fns:getDicLabel(user.stLevel,"DIC_USER_ST_LEVEL", "")}</td>
				<td>${fns:getDicLabel(user.post,"DIC_USER_POST", "")}</td>
				<td>${fns:getDicLabel(user.rank,"DIC_USER_RANK", "")}</td>
				<td>${user.mobile}</td>
				<td><a href="${ctx}/sys/user/form?userId=${user.userId}">修改</a> <a href="${ctx}/sys/user/delete?userId=${user.userId}" onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a> <a href="${ctx}/sys/user/reset?userId=${user.userId}"
					onclick="return confirmx('确认要重置密码吗？', this.href)">重置</a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>