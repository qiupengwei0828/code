<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#suserName").val("");
			$("#smobile").val("");
		})

		$("#trueBtn").click(function() {
			var userId = $("#resetPW").attr("title");
			$.ajax({
				url : "${ctx}/sys/user/reset",
				data : {
					'userId' : userId
				},
				type : "POST",
				dataType : 'json',
				success : function(data) {
					$("#resetPasswordTitle").modal('hide');
					$("#resetPasswordSuccess").modal('show');
				}
			});
		});
	});

	function resetPassword() {
		$("#resetPasswordTitle").modal('show');
	}

	//分页
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/list">员工列表</a></li>
		<li><a href="${ctx}/sys/user/form">员工添加</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="dic" action="${ctx}/sys/user/list?orgNo=${user.orgNo}" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form id="sForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="breadcrumb form-search">
		<label>员工姓名：</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:120px" id="suserName" />
		<label>员工ID：</label>
		<form:input path="userId" htmlEscape="false" maxlength="50" style="width:120px" id="userId" />
		<label>人力资源编号：</label>
		<form:input path="hrNo" htmlEscape="false" maxlength="50" style="width:120px" id="hrNo" />
		<label>身份证号：</label>
		<form:input path="certNo" htmlEscape="false" maxlength="50" style="width:120px" id="certNo" />
		<label>电话号码：</label>
		<form:input path="mobile" htmlEscape="false" maxlength="50" style="width:120px" id="smobile" />
		<label>用户状态:</label>
		<form:select path="status" class="input-medium">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>姓名</th>
			<th>身份证号</th>
			<th>机构</th>
			<th>部门</th>
			<th>性别</th>
			<th>手机</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td>${user.userName}</td>
				<td>${user.certNo}</td>
				<td>${user.orgName2nd}</td>
				<td>${user.orgName}</td>
				<td>${fns:getDicLabel(user.sex,"DIC_USER_SEX", "")}</td>
				<td>${user.mobile}</td>
				<td><a href="${ctx}/sys/user/form?userId=${user.userId}">修改</a> <a href="${ctx}/sys/user/delete?userId=${user.userId}" onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a> <a
					id="resetPW" href="#" title="${user.userId}" onclick="resetPassword()">密码重置</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>


	<div id="resetPasswordTitle" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">密码重置</h3>
		</div>
		<div class="modal-body">
			<h5>确认重置该账户密码为 123456 ？</h5>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn btn-primary" id="trueBtn">确认</button>
		</div>
	</div>


	<div id="resetPasswordSuccess" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">重置成功</h3>
		</div>
		<div class="modal-body">
			<h5>该账号密码重置成功！</h5>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">确认</button>
		</div>
	</div>

</body>
</html>