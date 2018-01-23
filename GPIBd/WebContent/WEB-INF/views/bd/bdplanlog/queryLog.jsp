<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
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
	
  </script>
<body>
<ul class="nav nav-tabs">
		<li><a href="${ctx}/bd/bdplanlog/log">请领列表查询</a></li>
		<li class="active"><a href="${ctx}/bd/bdplanlog/queryLog">日志查询</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="dic" action="${ctx}/bd/bdplanlog/queryLog" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="bdProcess" action="${ctx}/bd/bdplanlog/queryLog" method="post" class="breadcrumb form-search">		
		<label>请领机构：</label>
		<sys:treeselect id="orgId" name="orgId" value="${store.orgNo}" labelName="orgName" labelValue="${store.orgName}" title="机构" url="/bd/bdstore/treeData" cssStyle="width:150px;font-size:10px;" />
		<label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:120px" id="certactName" />
		<label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:120px" id="certactCode"  />
		<label>操作人员：</label>
		<form:input path="userId" htmlEscape="false" maxlength="50" style="width:120px" id="orgName" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>分行代码</th>
			<th>分行名称</th>
			<th>请领机构代码</th>
			<th>请领机构名称</th>
			<th>单证代码</th>
			<th>单证名称</th>
			<th>节点名称</th>
			<th>开始时间</th>
			<th>结束时间</th>
			<th>操作人员</th>
		</tr>
		<c:forEach items="${page.list}" var="bdProcess">
			<tr>
				<td>${bdProcess.orgNo}</td>	
				<td>${bdProcess.orgName}</td>
				<td>${bdProcess.planOrgno}</td>	
				<td>${bdProcess.planOrgname}</td>	
				<td>${bdProcess.certactCode}</td>	
				<td>${bdProcess.certactName}</td>			
				<td>${bdProcess.nodeName}</td>
				<td>${bdProcess.startTime}</td>
			    <td>${bdProcess.cofirmTime}</td>
			    <td>${bdProcess.userId}</td>
				
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
