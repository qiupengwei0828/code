<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已归档计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}

	$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#rotateNo").val("");
			$("#rotUserName").val("");
			$("#rotPos").val("");
			$("#repUserName").val("");
			$("#sForm").submit();
		});
	});
</script>
</head>
<body>


	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/posrot/recManage/archives">已归档计划</a></li>
	</ul>


	<form:form id="pageForm" modelAttribute="recess" action="${ctx}/posrot/recManage/archives" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>



	<form:form id="sForm" modelAttribute="recess" action="${ctx}/posrot/recManage/archives" method="post" class="breadcrumb form-search">

		<label>强修员工姓名：</label>
		<form:input id="recUserName" path="recUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>顶岗员工姓名：</label>
		<form:input id="repUserName" path="repUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<input id="btnQuery" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
	</form:form>


	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
			<th>强修员工姓名</th>
			<th>强修员工机构</th>
			<th>强修员工岗位</th>
			<th>强修开始日期</th>
			<th>强修结束日期</th>
			<th>顶岗人姓名</th>
			<th>顶岗人机构</th>
			<th>顶岗人岗位</th>
			<th>交接日期</th>
			<th>状态</th>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list}" var="recess">
			<tr>
				<td>${recess.recUserName}</td>
				<td>${recess.recOrgName}</td>
				<td>${recess.recPosName}</td>
				<td>${recess.recBeginDate}</td>
				<td>${recess.recEndDate}</td>
				<td>${recess.repUserName}</td>
				<td>${recess.repOrgName}</td>
				<td>${recess.repPosName}</td>
				<td>${recess.hanDate}</td>
				<td>${fns:getDicLabel(recess.status,"DIC_POS_ROTATION_STATUS", "")}</td>
				<td><a href="${ctx}/posrot/recManage/all_page_info?id=${recess.id}">详情</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>