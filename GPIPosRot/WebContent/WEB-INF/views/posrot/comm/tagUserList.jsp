<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择用户</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	$(document).ready(function() {
		$("[href='#'], #getUserId").click(function() {
			$.ajax({
				data : "userId=" + $(this).attr("title"),
				type : "POST",
				dataType : 'json',
				url : "${ctx}/sys/user/queryUserInfo",
				success : function(data) {
					parent.getUser(data.userId, data.userName, data.orgNo, data.orgName, data.pos, data.posName);
				}
			});
		});
	})
</script>
</head>
<body>

	<form:form id="sForm" modelAttribute="user" action="${ctx}/posrot/tags/tagShowUserList" method="post" class="breadcrumb form-search">

		<label>用户名称：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:120px" id="userName" />

		<label>人力资源编号：</label>
		<form:input path="hrNo" htmlEscape="false" maxlength="50" style="width:120px" id="hrNo" />

		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>


	<form:form id="searchForm" modelAttribute="user" action="${ctx}/posrot/tags/tagShowUserList" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th></th>
			<th>人力资源编号</th>
			<th>员工姓名</th>
			<th>性别</th>
			<th>所在岗位</th>
			<th>用工类别</th>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td><input id="getUserId" name="userId" type="radio" title="${user.userId}"></td>
				<td>${user.hrNo}</td>
				<td>${user.userName}</td>
				<td>${fns:getDicLabel(user.sex,"DIC_USER_SEX", "")}</td>
				<td>${fns:getDicLabel(user.pos,"DIC_USER_POSITION", "")}</td>
				<td>${fns:getDicLabel(user.userClass,"DIC_USER_CLASS", "")}</td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

</body>
</html>