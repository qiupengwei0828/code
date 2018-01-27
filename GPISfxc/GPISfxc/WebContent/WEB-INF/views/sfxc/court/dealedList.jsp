<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
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
	
    function resetvalue(){
	  $("#judgeName").val("");
	  $("#queryName").val("");
	}  
</script>  
<body>
 <form:form id="searchForm" modelAttribute="courtQueryInfo" action="${ctx}/court/deal/undealList" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="judgeName" value="${courtQueryInfo.judgeName}">
		<input type="hidden" name="queryName" value="${courtQueryInfo.queryName}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="courtQueryInfo" action="${ctx}/court/deal/undealList" method="post" class="breadcrumb form-search">
	   <label>承办检察官：</label>
		<form:input path="judgeName" htmlEscape="false" maxlength="50" style="width:120px" id="procuratorName" />
	    <label>被查询人姓名：</label>
		<form:input path="queryName" htmlEscape="false" maxlength="50" style="width:120px" id="queryName" />		
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />		
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
		    <th>查询请求单号</th>
		    <th>被查询人姓名</th>
			<th>证件类型</th>
			<th>被查询人证件</th>
			<th>承办法官</th>
			<th>往来账查询开始时间</th>
			<th>往来账查询结束时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="courtQueryInfo">
			<tr>
			    <td>${courtQueryInfo.queryId}</td>
			    <td>${courtQueryInfo.queryName}</td>
				<td>${courtQueryInfo.certType}</td>
				<td>${courtQueryInfo.certNo}</td>
			    <td>${courtQueryInfo.judgeName}</td>
			    <td>${courtQueryInfo.startDt}</td>
			    <td>${courtQueryInfo.endDt}</td>	
			    <td><a
					href="${ctx}/court/deal/queryDetail?queryId=${courtQueryInfo.queryId}" >查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>	
</body>
</html>
