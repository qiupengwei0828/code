<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>条线管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#depCode").val("");
			$("#depName").val("");
		});
	});


</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/sys/dep/index">条线列表</a>
		</li>
		<li>
			<a href="${ctx}/sys/dep/form">条线添加</a>
		</li>
	</ul>

	 <form:form id="searchForm" modelAttribute="dep" action="${ctx}/sys/dep/index" method="post" class="breadcrumb form-search">
		<label>条线编码：</label>
		<form:select id="depCode" path="depCode" class="input-medium">
			<form:option value="" label="全部" />
			<form:options items="${depCodeList}" itemLabel="depCode" itemValue="depCode" htmlEscape="false" />
		</form:select>
		<label>条线名称：</label>
		<form:input path="depName" htmlEscape="false" maxlength="50" style="width:120px" id="depName" />
			<input id="btnSubmit" class="btn btn-primary" type="submit" value=" 查询 " /> 
			<input id="btnReset" class="btn btn-primary" type="reset" value=" 重置 " />
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>条线编码</th>
			<th>条线名称</th>
			<th>备注</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="dep">
			<tr>
				<td>${dep.depCode}</td>
				<td>${dep.depName}</td>
				<td>${dep.remark}</td>
		        <td>${fns:getDicLabel(dep.status,"DIC_COMM_STATUS", "")}</td>
			   <td>
		      <a href="${ctx}/sys/dep/form?depCode=${dep.depCode}">修改</a>
			  <a href="${ctx}/sys/dep/delete?depCode=${dep.depCode}" onclick="return confirmx('确认要删除该条线吗？', this.href)">删除</a>
			  </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>