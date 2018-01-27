<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

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

	/* 	function test() {
	 $.ajax({
	 type : "GET",
	 dataType : 'json',
	 url : "${ctx}/sys/log/testHashMap",
	 success : function(data) {
	 alert("1");
	 alert(data);
	 }
	 });
	 }	 */
</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="opLog" action="${ctx}/sys/log/index" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form id="searchForm" modelAttribute="opLog" action="${ctx}/sys/log/index" method="post" class="breadcrumb form-search">
		<label>应用系统：</label>
		<form:select id="appCode" path="appCode" class="input-medium">
			<form:option value="" label="全部" />
			<form:options items="${appList}" itemLabel="appName" itemValue="appCode" htmlEscape="false" />
		</form:select>
		<label>用户ID：</label>
		<form:input path="userId" htmlEscape="false" maxlength="50" style="width:120px" id="userId" />

		<label>分类：</label>
		<form:select id="opClass" path="opClass" class="input-medium">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_SYS_LOG_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>操作类型：</label>
		<form:select id="opType" path="opType" class="input-medium">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_SYS_LOG_OP_TYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		</br>
		
		<label>操作时间：</label>
		<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
		<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<div style="float: right;margin-right: 20px;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value=" 查询 " /> <input id="btnReset" class="btn btn-primary" type="reset" value=" 重置 " />
		</div>
		<!-- <input id="btnTest" class="btn btn-primary" type="button" value=" 测试  " onclick="test()" /> -->
	</form:form>



	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>应用ID</th>
			<th>用户ID</th>
			<th>IP地址</th>
			<th>日志分类</th>
			<th>操作类型</th>
			<th>操作内容</th>
			<th>操作路径</th>
			<th>操作对象</th>
			<th>操作时间</th>
		</tr>
		<c:forEach items="${page.list}" var="oplog">
			<tr>
				<td>${oplog.appCode}</td>
				<td>${oplog.userId}</td>
				<td>${oplog.opIp}</td>
				<td>${fns:getDicLabel(oplog.opClass,"DIC_SYS_LOG_CLASS", "")}</td>
				<td>${fns:getDicLabel(oplog.opType, "DIC_SYS_LOG_OP_TYPE", "")}</td>
				<td>${oplog.opCnt}</td>
				<td>${oplog.servletPath}</td>
				<td>${oplog.opObj}</td>
				<td>${oplog.opTime}</td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>