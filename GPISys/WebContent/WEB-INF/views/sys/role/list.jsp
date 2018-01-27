<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/index">角色列表</a></li>
		<li><a href="${ctx}/sys/role/form">角色添加</a></li>
	</ul>
	<!--  
	<sys:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="role" action="${ctx}/sys/role/index" method="post" class="breadcrumb form-search">
		<label>应用系统：</label>
		<form:select id="appCode" path="appCode" class="input-medium" style="width: 200px;">
			<form:option value="" label="全部" />
			<form:options items="${applist}" itemLabel="appName" itemValue="appCode" htmlEscape="false" />
		</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>所属应用</th>
			<th>角色编码</th>
			<th>角色名称</th>
			<th>备注</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="role">
			<tr>
				<td>${role.appCode}</td>
				<td>${role.roleCode}</td>
				<td>${role.roleName}</td>
				<td>${role.remark}</td>
				<td>${fns:getDicLabel(role.status,"DIC_COMM_STATUS", "")}</td>
				<td><a href="${ctx}/sys/role/form?roleCode=${role.roleCode}">修改</a> <a href="${ctx}/sys/role/delete?roleCode=${role.roleCode}" onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>