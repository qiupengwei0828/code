<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位持证查询</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>


<script type="text/javascript">
	/*
	 * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}

	function check() {
		var posV = $.trim($("#posCodeV").val());
		$("#searchForm").submit();
	}
</script>


</head>
<body>

	<form:form id="pageForm" modelAttribute="dic" action="${ctx}/cert/certjobcfg/find" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />

		<input id="posCode" name="posCode" type="hidden" value="${certJobCfg.posCode}" />

	</form:form>


	<form:form id="searchForm" modelAttribute="certJobCfg" action="${ctx}/cert/certjobcfg/find" method="post" class="breadcrumb form-search">
		<label>岗位名称：</label>
		<form:select path="posCode" class="input-medium" onchange="check()" id="posCodeV">
			<option value="">全部</option>
			<form:options items="${fns:getDicList('DIC_USER_POSITION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed  table-hover">
		<tr>
			<th>岗位</th>
			<th>要求持有</th>
			<th>自选持有</th>
			<th>鼓励持有</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="certJobCfg" varStatus="status">
			<tr>
				<td><a href="${ctx}/cert/certjobcfg/jobCertHoldInfo?posCode=${certJobCfg.posCode}">${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</a></td>
				<td>${certJobCfg.hold1}</td>
				<td>${certJobCfg.hold2}</td>
				<td>${certJobCfg.hold3}</td>
				<td><a href="${ctx}/cert/certjobcfg/jobCertHoldInfo?posCode=${certJobCfg.posCode}">详情</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>