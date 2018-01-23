<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证书管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	/**
	 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	$(document).ready(function() {

		var orgNoV = "${holdInfo.orgNo}";
		$("#orgNo").val(orgNoV);

		$("#btnSubmit").click(function() {
			$("#sForm").submit();
		});

		$("#btnRest").click(function() {
			$("#userClass").val("");
			$("#userClassP").val("");
			$("#certCode").val("");
			$("#certCodeP").val("");
			$("#admClass").val("");
			$("#admClassP").val("");
			$("#pos").val("");
			$("#posP").val("");
			$("#userName").val("");
			$("#userNameP").val("");
			$("#issueDate").val("");
			$("#issueDateP").val("");
			$("#sForm").submit();

		});
	});
</script>

</head>

<body>

	<form:form id="searchForm" modelAttribute="holdInfo" action="${ctx}/cert/certStaffManage/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />

		<input id="userClassP" name="userClass" type="hidden" value="${holdInfo.userClass}" />
		<input id="certCodeP" name="certCode" type="hidden" value="${holdInfo.certCode}" />
		<input id="admClassP" name="admClass" type="hidden" value="${holdInfo.admClass}" />
		<input id="posP" name="pos" type="hidden" value="${holdInfo.pos}" />
		<input id="userNameP" name="userName" type="hidden" value="${holdInfo.userName}" />
		<input id="issueDateP" name="issueDate" type="hidden" value="${holdInfo.issueDate}" />

	</form:form>


	<form:form id="sForm" modelAttribute="holdInfo" action="${ctx}/cert/certStaffManage/list" method="post" class="breadcrumb form-search">

		<input id="orgNo" name="orgNo" type="hidden">

		<!--  -->

		<label>用工性质</label>
		<form:select id="userClass" path="userClass" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_USER_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>证书</label>
		<form:select id="certCode" path="certCode" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${certList}" itemLabel="codeName" itemValue="certCode" htmlEscape="false" />
		</form:select>

		<label>证书分类</label>
		<form:select id="admClass" path="admClass" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_CERT_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<br>

		<!--  -->

		<label>所在岗位</label>
		<form:select id="pos" path="pos" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_USER_POSITION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>姓名</label>
		<form:input id="userName" path="userName" htmlEscape="false" maxlength="50" style="width:135px" />

		<label>发证日期</label>
		<input id="issueDate" name="issueDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:135px" value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

		<!--  -->

		<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" style="margin-left: 10px;" />
		<!--  -->
		<input id="btnRest" class="btn btn-primary" type="submit" value="重置" />

	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<!-- <th>序号</th> -->
			<th>姓名</th>
			<th>所属机构</th>
			<th>工作岗位</th>
			<th>用工性质</th>
			<!-- <th>持有要求</th> -->
			<th>证书编码</th>
			<th>证书编号</th>
			<th>证书名称</th>
			<th>发证日期</th>
			<th>证书状态</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="holdInfo">
			<tr>
				<%-- <td>${holdInfo.rownum}</td> --%>
				<td>${holdInfo.userName}</td>
				<td>${holdInfo.orgName}</td>
				<td>${fns:getDicLabel(holdInfo.pos,"DIC_USER_POSITION", "")}</td>
				<td>${fns:getDicLabel(holdInfo.userClass,"DIC_USER_CLASS", "")}</td>
				<%-- <td>${fns:getDicLabel(holdInfo.holdReq,"DIC_CERT_HOLD", "")}</td> --%>
				<td>${holdInfo.certCode}</td>
				<td>${holdInfo.certNo}</td>
				<td>${holdInfo.certName}</td>
				<td>${holdInfo.issueDate}</td>
				<td>${fns:getDicLabel(holdInfo.status,"DIC_CERT_STATUS", "")}</td>
				<td>${holdInfo.remark}</td>
				<td><a href="${ctx}/cert/certStaffManage/info?id=${holdInfo.id}">详情</a>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>


	<div id=ErrorMessage class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<span><a style="text-decoration: none;"><img alt="" src="${ctxStatic}/images/alert_icon.png" style="float: left;width: 30px;height: 30px;">
					<h3 id="myModalLabel">错误提示</h3></a></span>
		</div>
		<div class="modal-body">
			<p>
			<h4>未选择机构</h4>
			</br>
			<h5>请先选择左侧机构后再试！</h5>
			</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</body>
</html>