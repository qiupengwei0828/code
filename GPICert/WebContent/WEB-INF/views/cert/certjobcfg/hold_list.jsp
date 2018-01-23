<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位持证要求配置</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function check() {
		var posV = $.trim($("#posCodeV").val());
		$("#sForm").submit();
	}
</script>


</head>
<body>

	<form:form id="pageForm" modelAttribute="dic" action="${ctx}/cert/certjobcfg/hold_list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th>岗位</th>
			<th>要求持有</th>
			<th>自选持有</th>
			<th>鼓励持有</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="certJobCfg">
			<tr>
				<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
				<td>${certJobCfg.hold1}</td>
				<td>${certJobCfg.hold2}</td>
				<td>${certJobCfg.hold3}</td>
				<td><a href="${ctx}/cert/certjobcfg/form?posCode=${certJobCfg.posCode}">配置</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>