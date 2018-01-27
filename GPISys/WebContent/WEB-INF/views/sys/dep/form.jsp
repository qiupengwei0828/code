<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>条线管理</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		var depCode = $("#depCode").val();
		if (depCode !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/dep/update");
			$("#depCode").attr("readonly", "readonly");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/dep/insert");
		}

		$("#btnSubmit").click(function() {
			var depCode = $("#depCode").val();
			var depName = $("#depName").val();

			if (depCode == "") {
				document.getElementById("depCodeError").innerHTML = "条线编码不能为空";
			}

			if (depName == "") {
				document.getElementById("depNameError").innerHTML = "条线名称不能为空";
			}

			if (depCode != "" && depName != "") {
				$("#inputForm").submit();
			}

		});

		$("#depCode").blur(function() {
			depCode = $("#depCode").val();
			$.ajax({
				url : "${ctx}/sys/dep/existsDepCode",
				data : {
					"depCode" : depCode
				},
				type : "POST",
				dataType : 'json',
				success : function(data) {
					if (data.msg == 'none') {
						$("#depCodeError").text("*");
					} else {
						$("#depCodeError").text("当前编码已存在，请重试！");
					}
				}
			});
		});
	});

	function clear_depNameError_msg() {
		document.getElementById("depNameError").innerHTML = "*";
	}

	function clear_depCodeError_msg() {
		document.getElementById("depCodeError").innerHTML = "*";
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dep/index">条线列表</a></li>
		<li class="active"><a href="${ctx}/sys/dep/form?id=${dep.depCode}">条线${not empty dep.depCode?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="dep" action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">条线编码:</label>
			<div class="controls">
				<form:input path="depCode" htmlEscape="false" maxlength="50" class="required" placeholder="输入数字、字母或数字字母组合" id="depCode" onclick="clear_depCodeError_msg()" />
				<span class="help-inline"><font color="red" id="depCodeError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条线名称:</label>
			<div class="controls">
				<form:input path="depName" htmlEscape="false" maxlength="50" class="required" id="depName" onclick="clear_depNameError_msg()" />
				<span class="help-inline"><font color="red" id="depNameError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="50" class="required" style="width:200px" />
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
