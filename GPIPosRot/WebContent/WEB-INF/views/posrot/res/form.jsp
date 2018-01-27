<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>员工履历管理</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

		$("#userName").attr("readonly", "readonly");

		var id = $("#id").val();
		if (id !== "") {
			$("#inputForm").attr("action", "${ctx}/posrot/res/update");
		} else {
			$("#inputForm").attr("action", "${ctx}/posrot/res/insert");
		}

		$("#btnSubmit").click(function() {
			var posCode = $("#posCode").val();
			var orgNo = $("#orgNo").val();
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var status = $("#status").val();
			if (posCode != "" && orgNo != "" && beginDate != "" && endDate != "" && status != "") {
				$("#inputForm").submit();
			} else {
				$("#alertTitle").modal('show');
			}

		});

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/posrot/res/reslist?userId=${resInfo.userId}">履历列表</a></li>
		<li class="active"><a href="${ctx}/posrot/res/form?userId=${resInfo.userId}">履历${not empty resInfo.id?'修改':'添加'}</a></li>
	</ul>


	<form:form id="inputForm" modelAttribute="resInfo" action="" method="post" class="form-horizontal">

		<input id="id" name="id" value="${resInfo.id}" type="hidden" />

		<input id="userId" name="userId" value="${resInfo.userId}" type="hidden" />


		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input id="userName" path="userName" htmlEscape="false" maxlength="50" class="required" style="width:185px" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所在机构:</label>
			<div class="controls">
				<sys:treeselect id="orgNo" name="orgNo" value="${user.orgNo}" labelName="orgName" labelValue="${user.orgName}" title="机构" url="/sys/org/treeData" cssStyle="width:150px;font-size:10px;" />
				<span class="help-inline"><font color="red" id="posCodeError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">岗位:</label>
			<div class="controls">
				<form:select id="posCode" path="posCode" class="input-medium" style="width:200px">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_POSITION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red" id="posCodeError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">到岗时间:</label>
			<div class="controls">
				<input id="beginDate" name="beginDate" type="text" maxlength="50" class="input-mini Wdate" style="width:180px" readonly="readonly"
					value="<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span class="help-inline"><font color="red"
					id="beginDateError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">离岗时间:</label>
			<div class="controls">
				<input id="endDate" name="endDate" type="text" maxlength="50" class="input-mini Wdate" style="width:180px" readonly="readonly" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span class="help-inline"><font color="red" id="endDateError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="50" class="required" style="width:185px" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="status" class="input-medium" style="width:200px" id="status">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_POS_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red" id="statusError">*</font></span>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

	<div id="alertTitle" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">提示</h3>
		</div>
		<div class="modal-body">
			<h5>
				带<font color="red" id="statusError">&nbsp;&nbsp;*&nbsp;&nbsp;</font>号的为必填项，请检查后重试！
			</h5>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">确认</button>
		</div>
	</div>

</body>
</html>

