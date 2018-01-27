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
	  $("#certactCode").val("");
	  $("#certactName").val("");
	  $("#useType").val("");
      document.getElementById("useType").selectedIndex=0;
	  window.location.href=window.location.href;
	}  
</script>  
<body>
 <form:form id="searchForm" modelAttribute="proQueryInfo" action="${ctx}/sfxc/homePage/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="useType" value="${bdUseDetail.useType}">
		<input type="hidden" name="certactName" value="${bdUseDetail.certactName}">
		<input type="hidden" name="certactCode" value="${bdUseDetail.certactCode}">
		<input type="hidden" id="useDateDate" name="useDate" value="${bdUseDetail.useDate}">
		<input type="hidden" id="dateDate" name="date" value="${bdUseDetail.date}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="bdUseDetail" action="${ctx}/bd/use/list" method="post" class="breadcrumb form-search">
	   <%-- <label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:120px" id="certactCode" />
	    <label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:120px" id="certactName" />
		<label>使用类型：</label>
		<form:select path="useType" class="input-medium">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_USE_TYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>使用日期：</label>
		<input id="useDate" name="useDate"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px;align:center" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />--
	    <input id="date" name="date"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px;align:center" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> --%>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />		
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
		    <th>查询请求单号</th>
		    <th>被查询人姓名</th>
			<th>证件类型</th>
			<th>被查询人证件</th>
			<th>承办检察官</th>
			<th>往来账查询开始时间</th>
			<th>往来账查询结束时间</th>
			<th>申请日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="proQueryInfo">
			<tr>
			    <td>${proQueryInfo.queryId}</td>
			    <td>${proQueryInfo.queryName}</td>
				<td>${proQueryInfo.certType}</td>
				<td>${proQueryInfo.certNo}</td>
			    <td>${proQueryInfo.procuratorName}</td>
			    <td>${proQueryInfo.startDt}</td>
			    <td>${proQueryInfo.endDt}</td>
			    <td>${proQueryInfo.requestDt}</td>	
			    <td><a
					href="javascript:" onclick="return confirmx('确定要购入吗？', this.href)">操作</a></td>		   
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>	
</body>
</html>
