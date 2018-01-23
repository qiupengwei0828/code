<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>字典管理</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		//var pName = $("#pName").val().trim();
		var pName = $.trim($("#pName").val());

		$("#typeCode").attr("readonly", "readonly");

		if (pName !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/dic/update");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/dic/insert");
		}

		/*
		 * 当点击(btnSubmit)保存按钮时
		 */
		$("#btnSubmit").click(function() {

			//var pName = $("#pName").val().trim();
			//var pValue = $("#pValue").val().trim();

			var pName = $.trim($("#pName").val());
			var pValue = $.trim($("#pValue").val());

			//判断 pName 是否为空
			if (pName == "") {
				$("#pName").attr("placeholder", "参数名称不能为空！！！");
				$("#pName").mousedown(function() {
					$("#pName").attr("placeholder", "");
				})
			}

			//判断 pValue 是否为空
			if (pValue == "") {
				$("#pValue").attr("placeholder", "参数值不能为空！！！");
				$("#pValue").mousedown(function() {
					$("#pValue").attr("placeholder", "");
				})
			}
			//当appCode、appName都不为空时提交
			if (pName !== "" && pValue !== "") {
				$("#typeCode").removeAttr("readonly");
				changeBtnSubmit();
			}
		});

		function changeBtnSubmit() {
			$("#inputForm").submit();
		}

	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dic/diclist">字典列表</a></li>
		<li class="active"><a href="${ctx}/sys/dic/form?id=${dic.id}">字典${not empty dic.id?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="dic" action="" method="post" class="form-horizontal">

		<form:input path="id" htmlEscape="false" maxlength="50" class="required" type="hidden" />

		<div class="control-group">
			<label class="control-label">字典类型:</label>
			<div class="controls">
				<form:input path="typeCode" htmlEscape="false" maxlength="50" class="required" id="typeCode" />
				<span class="help-inline"><font color="red" id="appCodeError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">参数名称:</label>
			<div class="controls">
				<form:input path="pName" htmlEscape="false" maxlength="50" class="required" id="pName" />
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">参数值:</label>
			<div class="controls">
				<form:input path="pValue" htmlEscape="false" maxlength="50" class="required" id="pValue" />
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="50" />
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


		<form:input path="crtUser" htmlEscape="false" maxlength="50" class="required" type="hidden" />

		<form:input path="crtTime" htmlEscape="false" maxlength="50" class="required" type="hidden" />


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

</body>
</html>