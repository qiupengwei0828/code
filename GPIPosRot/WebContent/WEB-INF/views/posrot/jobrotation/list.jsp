<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轮岗员工列表</title>
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
			$("#posCode").val("");
			$("#userName").val("");
			$("#hrNo").val("");
			$("#posDate").val("");
			//查询条件
			$("#posCodeV").val("");
			$("#userNameV").val("");
			$("#hrNoV").val("");
			$("#posDateV").val("");

			$("#cForm").submit();
		});

		$("#posDate").val($("#posDateV").val());
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

	<form:form id="searchForm" modelAttribute="staff" action="${ctx}/posrot/jobrotation/list?orgNo=${orgNo}" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<!-- 查询条件 -->
		<input id="posCodeV" name="posCode" type="hidden" value="${staff.posCode}" />
		<input id="posDateV" name="posDate" type="hidden" value="${posDate}" />
		<input id="userNameV" name="userName" type="hidden" value="${staff.userName}" />
		<input id="hrNoV" name="hrNo" type="hidden" value="${staff.hrNo}" />
	</form:form>

	<form:form id="cForm" modelAttribute="staff" action="${ctx}/posrot/jobrotation/list?orgNo=${orgNo}" method="post" class="breadcrumb form-search">

		<label>岗位名称：</label>
		<form:select id="posCode" path="posCode" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${posList}" itemLabel="posName" itemValue="posCode" htmlEscape="false" />
		</form:select>

		<label>员工姓名：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" id="userName" style="width:150px;" />

		<label>人力资源编号：</label>
		<form:input path="hrNo" htmlEscape="false" maxlength="50" id="hrNo" style="width:150px;" />

		<label>到岗日期：</label>
		<%-- <input id="posDate" name="posDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:parseDate value="${posDate}" pattern="yyyy-MM-dd" type="date"></fmt:parseDate>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> --%>
		<input id="posDate" name="posDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${posDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

		<input class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="reset" value="重置" />

	</form:form>

	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
			<th>岗位名称</th>
			<th>员工姓名</th>
			<th>人力资源编号</th>
			<th>到岗日期</th>
			<th>到期天数</th>
			<th>轮岗期限</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="staff">
			<tr>
				<td>${staff.posName}</td>
				<td>${staff.userName}</td>
				<td>${staff.hrNo}</td>
				<td>${staff.posDate}</td>

				<c:if test="${staff.limitDay >= 0}">
					<%-- <td style="color:red;">${staff.limitDayDisp}</td> --%>
					<td style="color:red;">${staff.limitDay}</td>
				</c:if>
				<c:if test="${staff.limitDay < 0}">
					<c:if test="${staff.limitDay > -15}">
						<td style="color:blue;">${staff.limitDayDisp}</td>
					</c:if>
					<c:if test="${staff.limitDay < -15}">
						<td>${staff.limitDayDisp}</td>
					</c:if>
				</c:if>

				<td>${staff.limitDate}</td>
				<td><a href="${ctx}/posrot/jobrotation/queryUserInfo?userId=${staff.userId}&orgNo=${staff.orgNo}">制定计划</a></td>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>