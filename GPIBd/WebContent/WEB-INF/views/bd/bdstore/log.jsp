<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志查询</title>
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
		
		function exportData(){
		    var url=encodeURI(encodeURI("${ctx}/bd/bdstore/export"));
		    window.location.href=url;
		}
		function resetvalue(){
	  $("#certactCode").val("");
	  $("#certactName").val("");
	  $("#orgNoId").val("");
	  $("#orgNoName").val("");
     
	}
  </script>
<body>
		<form:form id="searchForm" modelAttribute="storeLog" action="${ctx}/bd/bdstorelog/log" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="orgNo" value="${storeLog.orgNo}">
		<input type="hidden" name="certactName" value="${storeLog.certactName}">
		<input type="hidden" name="certactCode" value="${storeLog.certactCode}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="storeLog" action="${ctx}/bd/bdstorelog/log" method="post" class="breadcrumb form-search">		
		<label>机构代码：</label>
		<sys:treeselect id="orgNo" name="orgNo" value="${store.orgNo}" labelName="orgName" labelValue="${store.orgName}" title="机构" url="/bd/bdstore/treeData" cssStyle="width:150px;font-size:10px;" />
		<label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:120px" id="certactName" />
		<label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:120px" id="certactCode"  />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />
	</form:form>
	

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>机构代码</th>
			<th>机构名称</th>
			<th>单证代码</th>
			<th>单证名称</th>
			<th>出库数量</th>
			<th>入库数量</th>
			<th>操作时间</th>
			<th>操作人员</th>
		</tr>
		<c:forEach items="${page.list}" var="storeLog">
			<tr>
				<td>${storeLog.orgNo}</td>	
				<td>${storeLog.orgName}</td>		
				<%-- <td>${fns:getDicLabel(store.repType,"DIC_BD_REP_TYPE", "")}</td> --%>
				<td>${storeLog.certactCode}</td>
				<td>${storeLog.certactName}</td>
				<td>${storeLog.outNum}</td>
				<td>${storeLog.inNum}</td>
				<td>${storeLog.changeTime}</td>
			    <td>${storeLog.userId}</td>
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
