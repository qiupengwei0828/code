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
	  $("#bdhm").val("");
	  $("#queryUnit").val("");
	  $("#queryName").val("");
	  $("#backer").val("");
	  $("#status").val("");
	   window.location.href=window.location.href;
	}  
</script>  
<body>
 <form:form id="searchForm" modelAttribute="logMessage" action="${ctx}/sfxc/log/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		
		<input type="hidden" name="bdhm" value="${logMessage.bdhm}">
		<input type="hidden" name="queryUnit" value="${logMessage.queryUnit}">
		<input type="hidden" name="queryName" value="${logMessage.queryName}">
		<input type="hidden" name="backer" value="${logMessage.backer}">
		<input type="hidden" name="status" value="${logMessage.status}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="logMessage" action="${ctx}/sfxc/log/list" method="post" class="breadcrumb form-search">
	    <label>请求单号：</label>
		<form:input path="bdhm" htmlEscape="false" maxlength="50" style="width:120px" id="bdhm" />
	    <label>请求单位：</label>
		<form:select path="queryUnit" class="input-medium">
		         <form:option value="" label="全部" />
		         <form:option value="0" label="检察院" />
		         <form:option value="1" label="法院" />
		</form:select>
		<label>被查询人姓名：</label>
		<form:input path="queryName" htmlEscape="false" maxlength="50" style="width:120px" id="queryName" />	
		<label>反馈人：</label>
		<form:input path="backer" htmlEscape="false" maxlength="50" style="width:120px" id="backer" />	
		<label>处理结果：</label>
		<form:select path="status" class="input-medium">
		         <form:option value="" label="全部" />
		         <form:option value="-1" label="审核未通过" />
		         <form:option value="0" label="待审核" />
		         <form:option value="1" label="待处理" />
		         <form:option value="2" label="待核对" />
		         <form:option value="3" label="成功" />
		         <form:option value="4" label="失败" />
		</form:select>	
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />		
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
		    <th>请求单号</th>
		    <th>请求单位</th>
			<th>请求时间</th>
			<th>被查询人姓名</th>
			<th>审核人</th>
			<th>审核时间</th>
			<th>反馈人</th>
			<th>反馈时间</th>
			<th>处理结果</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="logMessage">
			<tr>
			    <td>${logMessage.bdhm}</td>
			    <td>${logMessage.queryUnit}</td>
				<td>${logMessage.receivedtime}</td>
				<td>${logMessage.queryName}</td>
				<td>${logMessage.examiner}</td>
			    <td>${logMessage.examinetime}</td>
			    <td>${logMessage.backer}</td>
			    <td>${logMessage.backtime}</td>
			    <td>${logMessage.status}</td>
			    <td><a href="${ctx}/sfxc/log/queryList?bdhm=${logMessage.bdhm}&queryUnit=${logMessage.queryUnit}">查看详情</a></td>	   
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>	
</body>
</html>
