<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工履历列表</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	function check() {
		$("#searchForm").submit();
	}
</script>


</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/posrot/res/reslist?userId=${userinfo.userId}">履历列表</a></li>
		<li><a href="${ctx}/posrot/res/form?userId=${userinfo.userId}">添加履历</a></li>
	</ul>

	<table class="table table-bordered">
		<tr>
			<td>姓名：${userinfo.userName}</td>
			<td>当前岗位：${fns:getDicLabel(userinfo.pos,"DIC_USER_POSITION", "")}</td>
			<td>所在机构：${userinfo.orgName}
		</tr>
	</table>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>到岗时间</th>
			<th>离岗时间</th>
			<th>姓名</th>
			<th>机构</th>
			<th>岗位</th>
			<th>状态</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${reslist}" var="reslist" varStatus="status">
			<tr>
				<td>${reslist.beginDate}</td>
				<td>${reslist.endDate}</td>
				<td>${reslist.userName}</td>
				<td>${reslist.orgName}</td>
				<td>${fns:getDicLabel(reslist.posCode,"DIC_USER_POSITION", "")}</td>
				<td>${fns:getDicLabel(reslist.status,"DIC_POS_COMM_STATUS", "")}</td>
				<td>${reslist.remark}</td>
				<td><a href="${ctx}/posrot/res/form?id=${reslist.id}">修改</a> <a href="${ctx}/posrot/res/delete?id=${reslist.id}&userId=${reslist.userId}" onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>