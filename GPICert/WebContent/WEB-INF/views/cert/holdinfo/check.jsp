<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>持证审核</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	/**
	 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	$(document).ready(function() {
		$("#refresh").click(function() {
			window.location.href = "${ctx}/cert/holdinfo/check";
		});
	});
</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="certJobCfg" action="${ctx}/cert/holdinfo/check" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<input id="refresh" class="btn btn-primary" type="button" value="刷新" style="float: right;margin: 5px 5px;" />

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th style="display: none;">HoldID</th>
			<th>姓名</th>
			<th>所在岗位</th>
			<th>证书编码</th>
			<th>证书名称</th>
			<th>证书编号</th>
			<th>发证日期</th>
			<th>发证机构</th>
			<th>状态</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="holdInfo" varStatus="status">
			<tr>
				<th style="display: none;">${holdInfo.holdId}</th>
				<td>${holdInfo.userName}</td>
				<td>${fns:getDicLabel(holdInfo.pos,"DIC_USER_POSITION", "")}</td>
				<td>${holdInfo.certCode}</td>
				<td>${holdInfo.certName}</td>
				<td>${holdInfo.certNo}</td>
				<td>${holdInfo.issueDate}</td>
				<td>${holdInfo.certUnit}</td>
				<td>${fns:getDicLabel(holdInfo.status,"DIC_CERT_STATUS", "")}</td>
				<td>${holdInfo.remark}</td>
				<td><a href="${ctx}/cert/holdinfo/checkinfo?id=${holdInfo.holdId}">审核</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>