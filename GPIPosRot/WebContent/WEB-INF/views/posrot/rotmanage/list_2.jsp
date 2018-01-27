<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待审批</title>
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
		<li><a href="${ctx}/posrot/rotManage/listall">全部(${statusAll })</a></li>
		<li><a href="${ctx}/posrot/rotManage/list1">待提交(${status1})</a></li>
		<li class="active"><a href="${ctx}/posrot/rotManage/list2">待审批(${status2})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list3">待通知(${status3})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list4">待接收(${status4})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list5">待交接(${status5})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list6">待执行(${status6})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list7">待归档(${status7})</a></li>
	</ul>

	<form:form id="pageForm" modelAttribute="rotation" action="${ctx}/posrot/rotManage/list2" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>


	<form:form id="sForm" modelAttribute="rotation" action="${ctx}/posrot/rotManage/list2" method="post" class="breadcrumb form-search">
		<label>计划编号：</label>
		<form:input id="rotateNo" path="rotateNo" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>轮岗员工姓名：</label>
		<form:input id="rotUserName" path="rotUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>轮岗岗位名称：</label>
		<form:select id="rotPos" path="rotPos" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${posList}" itemLabel="posName" itemValue="posCode" htmlEscape="false" />
		</form:select>

		<label>顶岗员工姓名：</label>
		<form:input id="repUserName" path="repUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>状态：</label>
		<form:select id="status" path="status" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_POS_ROTATION_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<input id="btnQuery" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />

		<input id="statusValue" type="hidden" value="${sta}">

	</form:form>


	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
			<th>轮岗员工姓名</th>
			<th>轮岗员工机构</th>
			<th>轮岗员工岗位</th>
			<th>轮换岗位</th>
			<th>轮换机构</th>
			<th>到岗日期</th>
			<th>顶岗人姓名</th>
			<th>顶岗人机构</th>
			<th>顶岗人岗位</th>
			<th>交接日期</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="rotation">
			<tr>
				<td>${rotation.rotUserName}</td>
				<td>${rotation.rotOrgName}</td>
				<td>${fns:getDicLabel(rotation.rotPos,"DIC_USER_POSITION", "")}</td>
				<td>${fns:getDicLabel(rotation.toPos,"DIC_USER_POSITION", "")}</td>
				<td>${rotation.toOrgName}</td>
				<td>${rotation.toDate}</td>
				<td>${rotation.repUserName}</td>
				<td>${rotation.repOrgName}</td>
				<td>${fns:getDicLabel(rotation.repPos,"DIC_USER_POSITION", "")}</td>
				<td>${rotation.hanDate}</td>
				<td>${fns:getDicLabel(rotation.status,"DIC_POS_ROTATION_STATUS", "")}</td>
				<td><a href="${ctx}/posrot/rotManage/all_page_info?id=${rotation.id}">详情</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>