<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>证书管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var certCode = $.trim($("#certCode").val());

		if (certCode !== "") {
			$("#inputForm").attr("action", "${ctx}/cert/cert/update");
			$("#certCode").attr("readonly", "readonly");
		} else {
			$("#inputForm").attr("action", "${ctx}/cert/cert/addCert");
		}

		$("#btnSubmit").click(function() {

			var certCode = document.getElementById("certCode").value;
			var certName = document.getElementById("certName").value;

			if (certCode == "") {
				document.getElementById("certCodeError").innerHTML = "证书编码不能为空";
			}

			if (certName == "") {
				document.getElementById("certNameError").innerHTML = "证书名称不能为空";
			}

			if (certName != "" && certCode != "") {
				$("#inputForm").submit();
			}
		});

		//对有效期限的校验
		$("#usefulLife").blur(function() {
			var usefulLife = $.trim($("#usefulLife").val());
			var num = /^\+?[1-9][0-9]*$/;
			if (num.test(usefulLife)) {
				$("#usefulLifeError").html("*");
			} else {
				$("#usefulLifeError").html("有效期限只能输入正整数！");
				$("##usefulLife").val("");
			}
		});

		/*
		 * ajax
		 * 添加certCode时
		 * 验证当前certCode是否已经添加
		 */
		$("#certCode").blur(function CertCodeajax() {
			//var certCodes = $.trim($("#certCode").val());
			$.ajax({
				data : "certCode=" + $.trim($("#certCode").val()),
				type : "POST",
				dataType : 'json',
				url : "${ctx}/cert/cert/existsCertCode",
				success : function(data) {
					if (data.msg == 'none') {
						appCodeajax();
						$("#certCodeError").text("*");
					} else {
						$("#certCodeError").text(data.msg);
						$("#certCode").val("");
					}
				}
			});
		});
	});

	function clear_certCodeError_msg() {
		document.getElementById("certCodeError").innerHTML = "*";
	}
	function clear_certNameError_msg() {
		document.getElementById("certNameError").innerHTML = "*";
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cert/cert/index">证书列表</a></li>
		<li class="active"><a href="${ctx}/cert/cert/form?id=${cert.certCode}">证书${not empty cert.certCode?'修改':'添加'}</a></li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="cert" action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">证书编码</label>
			<div class="controls">
				<form:input path="certCode" htmlEscape="false" maxlength="50" id="certCode" onblur="CertCodeajax()" onclick="clear_certCodeError_msg()" />
				<span class="help-inline"><font color="red" id="certCodeError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书名称</label>
			<div class="controls">
				<form:input path="certName" htmlEscape="false" maxlength="50" id="certName" onclick="clear_certNameError_msg()" />
				<span class="help-inline"><font color="red" id="certNameError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">行业分类</label>
			<div class="controls">
				<form:select path="industry" class="input-medium" style="width:220px">
					<form:option value="" label="" />
					<form:options items="${fns:getDicList('DIC_CERT_INDUSTRY')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">颁发单位</label>
			<div class="controls">
				<form:input path="certUnit" htmlEscape="false" maxlength="50" />
				<span class="help-inline"><font color="red" id="remarkError"></font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">资格分类</label>
			<div class="controls">
				<form:select path="admClass" class="input-medium" style="width:220px">
					<form:option value="" label="" />
					<form:options items="${fns:getDicList('DIC_CERT_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效期限</label>
			<div class="controls">
				<form:input path="usefulLife" htmlEscape="false" maxlength="50" />
				<span class="help-inline"><font color="red" id="usefulLifeError"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="50" />
				<span class="help-inline"><font color="red" id="remarkError"></font></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

</body>
</html>