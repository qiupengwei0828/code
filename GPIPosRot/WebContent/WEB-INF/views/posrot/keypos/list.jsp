<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重要岗位管理</title>
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
	$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#suserName").val("");
			$("#smobile").val("");
			$("form:first").submit();
		});
	});
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

	<form:form id="searchForm" modelAttribute="pos"
		action="${ctx}/posrot/keypos/index" method="post"
		class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
	</form:form>

	<form:form modelAttribute="pos" action="${ctx}/posrot/keypos/index"
		method="post" class="breadcrumb form-search">
		<label>岗位名称：</label>
		<form:input path="posName" htmlEscape="false" maxlength="50"
			id="posName" style="width:150px;" />

		<label>机构等级：</label>
		<form:select id="orgLevel" path="orgLevel" class="input-medium"
			style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_ORG_LEVEL')}"
				itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>状态：</label>
		<form:select path="status" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_COMM_STATUS')}"
				itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<input class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="reset" value="重置" />
	</form:form>


	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<tr>
			<th style="width: 15%">岗位名称</th>
			<th style="width: 10%">机构等级</th>
			<th>岗位职责</th>
			<th style="width: 5%">轮岗期限</th>
			<th style="width: 5%">状态</th>
			<th style="width: 10%">备注</th>
			<th style="width: 5%">操作</th>
		</tr>
		<c:forEach items="${page.list}" var="pos">
			<tr>
				<td>${pos.posName}</td>
				<td>${fns:getDicLabel(pos.orgLevel,"DIC_ORG_LEVEL", "")}</td>
				<td title="${pos.duty}"><font id="duty">${pos.duty}</font></td>
				<td>${pos.limitDate}</td>
				<td>${fns:getDicLabel(pos.status,"DIC_COMM_STATUS", "")}</td>
				<td>${pos.remark}</td>
				<td><a href="${ctx}/sys/pos/form?id=${pos.id}&appCode=posrot">编辑</a>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>