<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位持证要求详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>


<script type="text/javascript">
	function check() {
		$("#sForm").submit();
	}
	//分页
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
		<li class="active"><a href="${ctx}/cert/jobcertinfo/list">岗位持证情况详情</a></li>
		<li><a href="javascript:void(0)">详情</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="certJobCfg" action="${ctx}/cert/jobcertinfo/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />

		<input id="userName" name="userName" type="hidden" value="${certJobCfg.userName}" />
		<input id="pos" name="pos" type="hidden" value="${certJobCfg.pos}" />
		<input id="orgNo" name="orgNo" type="hidden" value="${certJobCfg.orgNo}" />

	</form:form>

	<form:form id="sForm" modelAttribute="certJobCfg" action="${ctx}/cert/jobcertinfo/list" method="post" class="breadcrumb form-search">

		<label>姓名：</label>
		<%-- <form:input id="userNameId" path="userName" htmlEscape="false" maxlength="50" style="width:150px" placeholder="用户姓名" onblur="check()" /> --%>
		<input id="userName" name="userName" type="text" value="${certJobCfg.userName}" style="width:150px" placeholder="用户姓名" onblur="check()" />

		<label>岗位名称：</label>
		<form:select path="pos" class="input-medium" onchange="check()" id="posCodeV">
			<option value="">全部</option>
			<form:options items="${fns:getDicList('DIC_USER_POSITION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

	</form:form>


	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th>姓名</th>
			<th>岗位</th>
			<th>要求持有证书</th>
			<th>已持有证书</th>
			<th>未持有证书</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="certJobCfg">
			<tr>
				<td><a href="${ctx}/cert/jobcertinfo/info?userId=${certJobCfg.userId}">${certJobCfg.userName }</a></td>
				<td><a href="${ctx}/cert/jobcertinfo/info?userId=${certJobCfg.userId}">${fns:getDicLabel(certJobCfg.pos,"DIC_USER_POSITION", "")}</a></td>
				<td>${certJobCfg.yqcyCert}</td>
				<td>${certJobCfg.ycyCert}</td>
				<td>${certJobCfg.wcyCert}</td>
				<td><a href="${ctx}/cert/jobcertinfo/info?userId=${certJobCfg.userId}">详情</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>