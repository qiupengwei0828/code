<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统应用管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/app/index">应用列表</a></li>
		<li><a href="${ctx}/sys/app/form">应用添加</a></li>
	</ul>
	<!--  
	<sys:message content="${message}"/>-->

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>应用编码</th>
			<th>应用名称</th>
			<th>归属机构</th>
			<th>地址</th>
			<th>状态</th>
			<th>备注</th>
			<th>启用图标</th>
			<th>打开页面</th>
			<th>禁用图标</th>
			<th>排序</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="app">
			<tr>
				<td><a href="form?appCode=${app.appCode}">${app.appCode}</a></td>
				<td><a href="form?appCode=${app.appCode}">${app.appName}</a></td>
				<td>${app.orgName}</td>
				<td>${app.accUrl}</td>
				<td>${app.status}</td>
				<td>${app.remark}</td>
				<td>${app.icon}</td>
				<td>${app.openPage}</td>
				<td>${app.disIcon}</td>
				<td>${app.sort}</td>
				<td><a href="${ctx}/sys/app/form?appCode=${app.appCode}">修改</a> <a href="${ctx}/sys/app/delete?appCode=${app.appCode}" onclick="return confirmx('确认要删除该应用系统吗？', this.href)">删除</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>