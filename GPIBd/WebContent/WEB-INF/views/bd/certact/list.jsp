<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>凭证列表</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctx}/js/autocomplete/autocomplete.js" type="text/javascript"></script>
<link href="${ctx}/js/autocomplete/jquery.autocomplete.css" rel="stylesheet" />
</head>
  <script type="text/javascript">
/*
   * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
		}
function resetvalue(){
	  $("#certactCode").val("");
	  $("#certactName").val("");
	  $("#certactType").val("");
      document.getElementById("certactType").selectedIndex=0;
      $("#status").val("");
      document.getElementById("status").selectedIndex=0;
	  window.location.href=window.location.href;
	}
  </script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bd/certact/list">凭证列表</a></li>
		<li><a href="${ctx}/bd/certact/form">凭证添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="certact" action="${ctx}/bd/certact/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="certactName" value="${certact.certactName}">
		<input type="hidden" name="certactCode" value="${certact.certactCode}">
		<input type="hidden" name="certactType" value="${certact.certactType}">
		<input type="hidden" name="status" value="${certact.status}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="certact" action="${ctx}/bd/certact/list" method="post" class="breadcrumb form-search">
		<label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:120px" id="certactName" />
		<label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:120px" id="certactCode"  />
		<label>单证类型：</label>
		<form:select path="certactType" class="input-medium">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>状态：</label>
		<form:select path="status" class="input-medium">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_CERTACT_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>单证代码</th>
			<th>单证名称</th>
			<th>单证类型</th>
			<th>状态</th>
			<th>库存期限</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="certact">
			<tr>
				<td>${certact.certactCode}</td>
				<td>${certact.certactName}</td>
				<td>${fns:getDicLabel(certact.certactType,"DIC_BD_CERTACTTYPE", "")}</td>
			    <td>${fns:getDicLabel(certact.status,"DIC_BD_CERTACT_STATUS", "")}</td>
			    <td>${certact.entryDays}</td>
				<td><a
					href="${ctx}/bd/certact/form?certactCode=${certact.certactCode}">修改</a>
					<a href="${ctx}/bd/certact/delete?certactCode=${certact.certactCode}" onclick="return confirmx('确认要删除该凭证吗？', this.href)">删除</a>
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
