<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>角色管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var posCode = $("#posCode").val();
		if (posCode != "") {
			$("#inputForm").attr("action", "${ctx}/sys/pos/update");
			$("#posCode").attr("readonly", "readonly");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/pos/insert");
		}

		$("#btnSubmit").click(function() {
			var posCode = $("#posCode").val();
			var posName = $("#posName").val();
			if (posCode == "" || posCode == null) {
				document.getElementById("posCodeError").innerHTML = "岗位编码不能为空";
			}
			if (posName == "" || posName == null) {
				document.getElementById("posNameError").innerHTML = "岗位名称不能为空";
			}

			if (posCode != "" && posName != "") {
				$("#inputForm").submit();
			}

		});
	});

	function clear_posCode_Msg() {
		document.getElementById("posCodeError").innerHTML = "*";
	}

	function clear_posName_Msg() {
		document.getElementById("posNameError").innerHTML = "*";
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/pos/index">岗位列表</a></li>
		<li class="active"><a href="${ctx}/sys/pos/form?id=${pos.id}">岗位${not empty pos.id?'修改':'添加'}</a></li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="pos" action="" method="post" class="form-horizontal">

		<input name="id" type="hidden" value="${pos.id}" />

		<div class="control-group">
			<label class="control-label">岗位编码:</label>
			<div class="controls">
				<form:input path="posCode" htmlEscape="false" maxlength="50" id="posCode" onclick="clear_posCode_Msg()" />
				<span class="help-inline"><font color="red" id="posCodeError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">岗位名称:</label>
			<div class="controls">
				<form:input path="posName" htmlEscape="false" maxlength="50" id="posName" onclick="clear_posName_Msg()" />
				<span class="help-inline"><font color="red" id="posNameError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">岗位级别:</label>
			<div class="controls">
				<form:select id="orgLevel" path="orgLevel" class="input-medium" style="width:220px">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_ORG_LEVEL')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">是否为关键岗位:</label>
			<div class="controls">
				<form:select id="primary" path="primary" class="input-medium" style="width:220px">
					<form:options items="${fns:getDicList('DIC_POS_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">轮岗期限:</label>
			<div class="controls">
				<form:input path="limitDate" htmlEscape="false" maxlength="50" id="limitDate" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">岗位职责:</label>
			<div class="controls">
				<form:textarea path="duty" htmlEscape="false" rows="4" maxlength="500" class="input-xlarge" style="width:400px" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="50" />
				<span class="help-inline"><font color="red" id="remarkError"></font></span>
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

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>

	</form:form>

</body>
</html>