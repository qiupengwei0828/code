<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>角色管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

		var roleCode = $("#roleCode").val();

		var url = "${ctx}/sys/role/existsRoleCode";

		if (roleCode !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/role/update");
			$("#roleCode").attr("readonly", "true");
			url = "";
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/role/addRole");
		}

		/*
		 * 当点击(btnSubmit)保存按钮时
		 * 移除输入框 id = "appCode" 的 disabled 属性
		 */
		$("#btnSubmit").click(function() {

			var roleCode = $("#roleCode").val();
			var roleName = $("#roleName").val();

			//当appCode、appName都不为空时提交
			if (roleCode !== "" && roleName !== "") {
				changeBtnSubmit();
			} else {
				//判断 appCode 是否为空
				if (roleCode == "") {
					$("#roleCodeError").html("编码不能为空！");
				}
				//判断 appName 是否为空
				if (roleName == "") {
					$("#roleNameError").html("名称不能为空！");
				}
			}
		});

		function changeBtnSubmit() {
			$("#inputForm").submit();
		}

		/*
		 * ajax
		 * 添加appCode时
		 * 验证当前appCode是否已经添加
		 */
		$("#roleName").blur(function appCodeajax() {
			$("#roleNameError").html("*");
		});
		$("#roleCode").blur(function appCodeajax() {
			var appCodes = $.trim($("#roleCode").val());
			$.ajax({
				data : "roleCode=" + $("#roleCode").val(),
				type : "POST",
				dataType : 'json',
				url : url,
				success : function(data) {
					if (data.msg == 'none') {
						//回调
						appCodeajax();
						$("#roleCodeError").text("*");
					} else {
						$("#roleCodeError").text(data.msg);
						$("#roleCode").val("");
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/index">应用列表</a></li>
		<li class="active"><a href="${ctx}/sys/role/form?id=${role.roleCode}">应用${not empty role.roleCode?'修改':'添加'}</a></li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="role" action="" method="post" class="form-horizontal">


		<div class="control-group">
			<label class="control-label">角色编码</label>
			<div class="controls">
				<form:input path="roleCode" htmlEscape="false" maxlength="100" id="roleCode" />
				<!-- onblur="roleCodeajax()" -->
				<span class="help-inline"><font color="red" id="roleCodeError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">角色名称</label>
			<div class="controls">
				<form:input path="roleName" htmlEscape="false" maxlength="50" id="roleName" />
				<span class="help-inline"><font color="red" id="roleNameError">*</font></span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">所属应用:</label>
			<div class="controls">

				<form:select id="appCode" path="appCode" class="input-medium" style="width:220px">
					<form:option value="" label="全部" />
					<form:options items="${applist}" itemLabel="appName" itemValue="appCode" htmlEscape="false" />
				</form:select>

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
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>


	</form:form>

</body>
</html>