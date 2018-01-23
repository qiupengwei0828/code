<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证书管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

		var certCV;

		$("#btnRest").click(function() {
			$("#certCodeP").val("");
			$("#certCode").val("");
			$("#certNameP").val("");
			$("#certName").val("");
			$("#industryP").val("");
			$("#industry").val("");
			$("#admClassP").val("");
			$("#admClass").val("");
			$("#sForm").submit();
		});

		$("font").click(function() {
			certCV = $(this).attr("title");
			$('#deleteTitle').modal('show');
		});

		$("#confirm").click(function() {
			$('#deleteTitle').modal('hide');
			$.ajax({
				data : "certCode=" + certCV,
				type : "POST",
				dataType : 'json',
				url : "${ctx}/cert/cert/deleteCheck",
				success : function(data) {
					if (data.msg == 'error') {
						$('#errorTitle').modal('show');
					} else {
						window.location.href = '${ctx}/cert/cert/delete?certCode=' + certCV;
					}
				}
			});
		});
	});
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
		<li class="active"><a href="${ctx}/cert/cert/index">证书列表</a></li>
		<li><a href="${ctx}/cert/cert/form">证书添加</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="cert" action="${ctx}/cert/cert/index" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />

		<input id="certCodeP" type="hidden" name="certCode" value="${cert.certCode}">
		<input id="industryP" type="hidden" name="industry" value="${cert.industry}">
		<input id="admClassP" type="hidden" name="admClass" value="${cert.admClass}">
		<input id="certNameP" type="hidden" name="certName" value="${cert.certName}">

	</form:form>

	<form:form id="sForm" modelAttribute="cert" action="${ctx}/cert/cert/index" method="post" class="breadcrumb form-search">
		<label>证书编码</label>
		<form:select path="certCode" class="input-medium" style="width:140px" id="certCode">
			<form:option value="" label="全部" />
			<form:options items="${certList}" itemLabel="certCode" itemValue="certCode" htmlEscape="false" />
		</form:select>
		<label>行业分类</label>
		<form:select path="industry" class="input-medium" style="width:140px" id="industry">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_CERT_INDUSTRY')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>资格分类</label>
		<form:select path="admClass" class="input-medium" style="width:140px" id="admClass">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_CERT_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>证书名称</label>
		<form:input path="certName" htmlEscape="false" maxlength="50" style="width:120px" id="certName" />

		<input id="btnRest" class="btn btn-primary" type="button" value="重置" style="float: right;margin-right: 10px;" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="float: right; margin-right: 10px;" />

	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th>证书编码</th>
			<th>证书名称</th>
			<th>行业分类</th>
			<th>颁发单位</th>
			<th>资格分类</th>
			<th>有效期限</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="cert">
			<tr>
				<td>${cert.certCode}</td>
				<td>${cert.certName}</td>
				<td>${fns:getDicLabel(cert.industry,"DIC_CERT_INDUSTRY", "")}</td>
				<td>${cert.certUnit}</td>
				<td>${fns:getDicLabel(cert.admClass,"DIC_CERT_CLASS", "")}</td>
				<td>${cert.usefulLife}</td>
				<td>${cert.remark}</td>
				<td><a href="${ctx}/cert/cert/form?certCode=${cert.certCode}">修改</a> <font style="color: #2FA4E7;cursor:pointer;" title="${cert.certCode}">删除</font> <%-- <a href="${ctx}/cert/cert/delete?certCode=${cert.certCode}" onclick="return confirmx('确认要删除该证书吗？', this.href)">删除</a> --%></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>


	<div id="deleteTitle" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">确认删除</h3>
		</div>
		<div class="modal-body">
			<p>确认要删除此信息么？</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<input class="btn btn-primary" type="button" value="确认" id="confirm" />
			<!-- <button class="btn btn-primary" id="confirm">确认</button> -->
		</div>
	</div>


	<div id="errorTitle" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">无法删除</h3>
		</div>
		<div class="modal-body">
			<p>当前证书信息已在‘岗位持证’，‘员工持证’中被占用，请先解除占用后再试！</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


</body>
</html>