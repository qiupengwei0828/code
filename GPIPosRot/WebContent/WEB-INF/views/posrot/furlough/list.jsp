<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>强休员工列表</title>
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
  //重置
  $(document).ready(function() {
	$("#btnReset").click(function() {
		$("#suserName").val("");
		$("#smobile").val("");
		$("form:first").submit();
	});
  });
  
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
		<!-- <li class="active"><a href="${ctx}/posrot/furlough/list">轮岗员工列表</a></li> -->
	</ul>
	<form:form id="searchForm" modelAttribute="staff" action="${ctx}/posrot/furlough/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form modelAttribute="staff" action="${ctx}/posrot/furlough/list" method="post" class="breadcrumb form-search">
		<label>岗位名称：</label>
		<form:input path="posName" htmlEscape="false" maxlength="50" id="posName" style="width:150px;" />
		<label>机构等级：</label>
		<form:select id="orgLevel" path="orgLevel" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_ORG_LEVEL')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>状态：</label>
		<form:select path="status" class="input-medium" style="width:150px">
		    <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<input class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="reset" value="重置" />
	</form:form>


<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>员工ID</th>
			<th>员工名称</th>
			<th>岗位编码</th>
			<th>岗位名称</th>
			<th>到岗时间</th>
			<th>轮岗期限</th>
		</tr>
		<c:forEach items="${page.list}" var="staff">
			<tr>
				<td>${staff.userId}</td>
				<td>${staff.userName}</td>
				<td>${staff.posCode}</td>
				<td>${staff.posName}</td>
				<td>${staff.posDate}</td>
				<td>${staff.limitDate}</td>	 
		 </c:forEach>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>