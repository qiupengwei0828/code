<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轮岗计划管理</title>
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
		<li class="active"><a href="${ctx}/posrot/rotManage/listall">全部(${statusAll})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list1">待提交(${status1})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list2">待审批(${status2})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list3">待通知(${status3})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list4">待接收(${status4})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list5">待交接(${status5})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list6">待执行(${status6})</a></li>
		<li><a href="${ctx}/posrot/rotManage/list7">待归档(${status7})</a></li>
	</ul>

	<form:form id="pageForm" modelAttribute="rotation" action="${ctx}/posrot/rotManage/listall" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>


	<form:form id="sForm" modelAttribute="rotation" action="${ctx}/posrot/rotManage/listall" method="post" class="breadcrumb form-search">
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
			<c:if test="${rotation.status != 8 }">
				<tr>
					<td>${rotation.rotUserName}</td>
					<td>${rotation.rotOrgName}</td>
					<td>${fns:getDicLabel(rotation.rotPos,"DIC_USER_POSITION", "")}</td>
					<td>${fns:getDicLabel(rotation.toPos,"DIC_USER_POSITION", "")}</td>
					<td>${rotation.rotOrgName}</td>
					<td>${rotation.toDate}</td>
					<td>${rotation.repUserName}</td>
					<td>${rotation.repOrgName}</td>
					<td>${fns:getDicLabel(rotation.repPos,"DIC_USER_POSITION", "")}</td>
					<td>${rotation.hanDate}</td>
					<td>${fns:getDicLabel(rotation.status,"DIC_POS_ROTATION_STATUS", "")}</td>

					<c:choose>
						<c:when test="${rotation.status == 9}">
							<td><a href="${ctx}/posrot/rotManage/form?id=${rotation.id}">修改</a> <a href="${ctx}/posrot/rotManage/delete?id=${rotation.id}" onclick="return confirmx('确认要删除该轮岗计划吗？', this.href)">删除</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="${ctx}/posrot/rotManage/all_page_info?id=${rotation.id}">详情</a></td>
						</c:otherwise>
					</c:choose>

				</tr>
			</c:if>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>