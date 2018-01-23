<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择证书</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	var values = "";
	function getv() {
		$(":checkbox").each(function(i) {
			if ($(this).attr("checked") == "checked") {
				values += $(this).val() + ",";
			}
		});
	}
	function cleanV() {
		values = "";
	}

	/*
	 * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="certJobCfg" action="${ctx}/cert/cert/certSelect" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th></th>
			<th>证书编码</th>
			<th>证书名称</th>
			<th>行业分类</th>
			<th>颁发单位</th>
			<th>资格分类</th>
			<th>有效期限</th>
			<th>备注</th>
		</tr>
		<c:forEach items="${page.list}" var="cert">
			<tr>
				<td>
					<center>
						<input type="checkbox" value="${cert.certCode}" name="checkbox" id="userCheck" />
					</center>
				</td>
				<td>${cert.certCode}</td>
				<td>${cert.certName}</td>
				<td>${fns:getDicLabel(cert.industry,"DIC_CERT_INDUSTRY", "")}</td>
				<td>${cert.certUnit}</td>
				<td>${fns:getDicLabel(cert.admClass,"DIC_CERT_CLASS", "")}</td>
				<td>${cert.usefulLife}</td>
				<td>${cert.remark}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>