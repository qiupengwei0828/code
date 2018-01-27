<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>机构管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var orgNo = $.trim($("#orgNo").val());
		if (orgNo !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/org/update");
			$("#orgNo").attr("disabled", "true");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/org/insert");
		}
	});
	function btnSubmit() {
		$("#orgNo").removeAttr("disabled");
		$("#inputForm").submit();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/org/findAllTree?orgNo=${org.orgNo}&pOrgNo=${org.pOrgNo}">机构列表</a></li>
		<li class="active"><a>机构${not empty org.orgNo?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="org" action="" method="post" class="form-horizontal">


		<div class="control-group">
			<label class="control-label">机构编码:</label>
			<div class="controls">
				<form:input path="orgNo" htmlEscape="false" maxlength="50" class="required" id="orgNo" />
				<span class="help-inline"><font color="red" id="appCodeError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">上级机构:</label>
			<div class="controls">
				<sys:treeselect id="pOrgNo" name="pOrgNo" value="${org.pOrgNo}" labelName="" labelValue="${org.pOrgName}" title="机构代码" url="/sys/org/treeData" cssClass="required" />
				<span class="help-inline"><font color="red" id="appCodeError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="orgName" htmlEscape="false" maxlength="50" class="required" id="orgName" />
				<span class="help-inline"><font color="red" id="appCodeError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">机构等级:</label>
			<div class="controls">
				<form:select path="orgLevel" class="input-medium" style="width:220px">
					<form:options items="${fns:getDicList('DIC_ORG_LEVEL')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="status" class="input-medium" style="width:220px">
					<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<form:input path="crtTime" type="hidden" htmlEscape="false" maxlength="50" class="required" id="crtTime" />

		<form:input path="crtUser" type="hidden" htmlEscape="false" maxlength="50" class="required" id="crtUser" />


		<div class="control-group">
			<label class="control-label">机构类别:</label>
			<div class="controls">
				<form:select path="orgType" class="input-medium" style="width:220px">
					<form:options items="${fns:getDicList('DIC_ORG_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">重证库位标识:</label>
			<div class="controls">
				<form:select path="bdFlag" class="input-medium" style="width:220px">
					<form:options items="${fns:getDicList('DIC_BD_FLAG')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="dispNo" htmlEscape="false" maxlength="50" class="required" id="dispNo" />
			</div>
		</div>


		<div class="form-actions">
			<input class="btn btn-primary" type="button" value="保 存" onclick="btnSubmit()" />&nbsp; <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>