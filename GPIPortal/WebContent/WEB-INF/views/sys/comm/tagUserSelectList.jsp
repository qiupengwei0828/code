<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择用户</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	//var mycars = new Array();
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
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search">
		<label>用户名称：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:120px" id="userName" />
		<label>电话号码：</label>
		<form:input path="tel" htmlEscape="false" maxlength="50" style="width:120px" id="tel" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th></th>
			<th style="display:none">用户ID</th>
			<th>用户名称</th>
			<th>所属机构</th>
			<th>用工类别</th>
			<th>职务</th>
			<th>手机</th>
		</tr>
		<c:forEach items="${list}" var="user">
			<tr>
				<td>
					<center>
						<input type="checkbox" value="${user.userId}" name="checkbox" id="userCheck" />
					</center>
				</td>
				<td style="display:none">${user.userId}</td>
				<td>${user.userName}</td>
				<td>${user.orgNo}</td>
				<td>${user.userClass}</td>
				<td>${user.post}</td>
				<td>${user.mobile}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>