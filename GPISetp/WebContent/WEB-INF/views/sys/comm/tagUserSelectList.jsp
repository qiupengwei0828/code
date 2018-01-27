<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择用户</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	//分页
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}

	var vla = "";

	function getv() {
		$(":checkbox").each(function(i) {
			if ($(this).attr("checked") == "checked") {
				vla += $(this).val() + ",";
			}
		});
	}
	function cleanV() {
		vla = "";
	}
</script>
</head>
<body>

	<form:form id="pageForm" modelAttribute="user" action="${ctx}/sys/user/tagShowUserList" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />

		<input name="orgNo" id="orgNo" type="hidden" value="${orgNo}">

		<input name="userId" id="userId" type="hidden" value="${user.userId}">
		<input name="userName" id="userName" type="hidden" value="${user.userName}">
		<input name="mobile" id="mobile" type="hidden" value="${user.mobile}">

	</form:form>

	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/tagShowUserList" method="post" class="breadcrumb form-search">

		<label>用户ID：</label>
		<input name="userId" id="userId" type="text" style="width:120px" value="${user.userId}">

		<label>用户姓名：</label>
		<input name="userName" id="userName" type="text" style="width:120px" value="${user.userName}">

		<label>手机号码：</label>
		<input name=mobile id="mobile" type="text" style="width:120px" value="${user.mobile}">

		<input name="orgNo" id="orgNo" type="hidden" value="${orgNo}">

		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th></th>
			<th>用户ID</th>
			<th>用户名称</th>
			<th>机构代码</th>
			<th>机构名称</th>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>
					<center>
						<input type="checkbox" value="${user.userId}" name="checkbox" id="userCheck" />
					</center>
				</td>
				<td>${user.userId}</td>
				<td>${user.userName}</td>
				<td>${user.orgNo}</td>
				<td>${user.orgName}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>