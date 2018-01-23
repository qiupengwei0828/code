<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>凭证管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var certactCode = $("#certactCode").val();
		if (certactCode != "") {
			$("#inputForm").attr("action", "${ctx}/bd/certact/update");
			$("#certactCode").attr("disabled", "true");
		} else {
			$("#inputForm").attr("action", "${ctx}/bd/certact/add");
		}

	
		$("#btnSubmit").click(function() {
			var certactCode = $("#certactCode").val().trim();
			var certactName = $("#certactName").val().trim();

			if (certactName == "") {
				$("#certactNameError").html("输入不能为空！");
			}

			if (certactCode == "") {
				$("#certactCodeError").html("输入不能为空！");
			}
			if (certactCode !== "" && certactName !== "") {
				changeBtnSubmit();
			}
		});

		function changeBtnSubmit() {
			$("#certactCode").removeAttr("disabled");
			$("#inputForm").submit();
		}

		/*
		 * ajax
		 * 添加appCode时
		 * 验证当前appCode是否已经添加
		 */
		$("#certactCode").blur(function appCodeajax() {
			$.ajax({
				data : "certactCode=" + $("#certactCode").val(),
				type : "GET",
				dataType : 'json',
				url : "${ctx}/bd/certact/exists",
				success : function(data) {
					if (data.msg == 'none') {
						//回调
						appCodeajax();
						$("#certactCode").text("*");
					} else {
						$("#certactCode").html(data.msg);
						$("#certactCode").val("");
					}
				}
			});
		});
	});
</script>
<style type="text/css">
.span2 {
	padding-left: 20px;
	width: 100px;
}

.row {
	padding-bottom: 5px;
}
</style>



</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bd/certact/list">凭证列表</a></li>
		<li class="active"><a href="${ctx}/bd/certact/form?id=${certact.certactCode}">凭证${not empty
				certact.certactCode?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="certact" action="" method="post" class="form-horizontal">
        <div class="control-group">
			<label class="control-label">单证代码:</label>
			<div class="controls">
				<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:150px" id="certactCode" onblur="roleCodeajax()" /><span style="font-size:14px;font-family:宋体;color:red;" >*</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单证名称:</label>
			<div class="controls">
				<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:200px" id="certactName" /><span style="font-size:14px;font-family:宋体;color:red;" >*</span>
			</div>
		</div>

			<div class="control-group">
			<label class="control-label">单证类型:</label>
			<div class="controls">
				<form:select path="certactType" class="input-medium">
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select><span style="font-size:14px;font-family:宋体;color:red;" >*</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:select path="status" class="input-medium">
		         <form:option value="" label="全部" />
			     <form:options items="${fns:getDicList('DIC_BD_CERTACT_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		      </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存期限:</label>
			<div class="controls">
				<form:input path="entryDays" htmlEscape="false" maxlength="50" style="width:150px" id="entryDays" />
			</div>
		</div>
		<br/>
        <font size="2">注：</font>
	    &nbsp;&nbsp;&nbsp;<font color=red>*</font>代表必填项
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> 
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>