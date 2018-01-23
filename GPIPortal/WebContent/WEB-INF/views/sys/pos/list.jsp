<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<style type="text/css">
#duty {
	display: block;
	width: 150px;
	height: 24px;
	overflow: hidden;
	white-space: nowrap;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
</style>

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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/pos/index">岗位列表</a></li>
		<li><a href="${ctx}/sys/pos/form">岗位添加</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="pos" action="${ctx}/sys/pos/index" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form modelAttribute="pos" action="${ctx}/sys/pos/index" method="post" class="breadcrumb form-search">
		<label>岗位名称：</label>
		<form:input path="posName" htmlEscape="false" maxlength="50" id="posName" style="width:150px;" />

		<label>机构等级：</label>
		<form:select id="orgLevel" path="orgLevel" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_ORG_LEVEL')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>是否为关键岗位：</label>
		<form:select id="primary" path="primary" class="input-medium" style="width:150px">
			<form:options items="${fns:getDicList('DIC_POS_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>状态：</label>
		<form:select path="status" class="input-medium" style="width:150px">
			<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<input class="btn btn-primary" type="submit" value="查询" />
	</form:form>


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th style="display: none;">ID</th>
			<th>岗位编码</th>
			<th>岗位名称</th>
			<th>机构等级</th>
			<th>岗位职责</th>
			<th>关键岗位</th>
			<th>轮岗期限</th>
			<th>状态</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="pos">
			<tr>
				<td style="display: none;">${pos.id}</td>
				<td>${pos.posCode}</td>
				<td>${pos.posName}</td>
				<td>${fns:getDicLabel(pos.orgLevel,"DIC_ORG_LEVEL", "")}</td>
				<td title="${pos.duty}"><font id="duty">${pos.duty}</font></td>
				<td>${fns:getDicLabel(pos.primary,"DIC_POS_STATUS", "")}</td>
				<td>${pos.limitDate}</td>
				<td>${fns:getDicLabel(pos.status,"DIC_COMM_STATUS", "")}</td>
				<td>${pos.remark}</td>
				<td><a href="${ctx}/sys/pos/form?id=${pos.id}">修改</a> <a href="${ctx}/sys/pos/delete?id=${pos.id}" onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>