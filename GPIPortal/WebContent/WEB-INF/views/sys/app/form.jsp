<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>应用管理</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		/* 判断当前appCode是否有值
		 * 如果没有则是添加页面，去除appCode的只读属性
		 * 设置form的action为(${ctx}/sys/app/addApp)添加URL
		 * 如果有
		 * 设置form的action为(${ctx}/sys/app/update)修改URL
		 */
		//var appCode = $("#appCode").val().trim();
		var appCode = $.trim($("#appCode").val());
		if (appCode !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/app/update");
			$("#appCode").attr("disabled", "true");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/app/addApp");
		}

		/*
		 * 当点击(btnSubmit)保存按钮时
		 * 移除输入框 id = "appCode" 的 disabled 属性
		 */
		$("#btnSubmit").click(function() {
			//var appCode = $("#appCode").val().trim();
			//var appName = $("#appName").val().trim();
			var appCode = $.trim($("#appCode").val());
			var appName = $.trim($("#appName").val());

			//判断 appCode 是否为空
			if (appCode == "") {
				$("#appCode").attr("placeholder", "应用编码不能为空！！！");
				$("#appCode").mousedown(function() {
					$("#appCode").attr("placeholder", "输入数字、字母或数字字母组合");
				})
			}
			//判断 appName 是否为空
			if (appName == "") {
				$("#appName").attr("placeholder", "应用名称不能为空！！！");
				$("#appName").mousedown(function() {
					$("#appName").attr("placeholder", "");
				})
			}
			//当appCode、appName都不为空时提交
			if (appCode !== "" && appName !== "") {
				changeBtnSubmit();
			}
		});

		function changeBtnSubmit() {
			$("#appCode").removeAttr("disabled");
			$("#inputForm").submit();
		}

		/*
		 * ajax
		 * 添加appCode时
		 * 验证当前appCode是否已经添加
		 */
		$("#appCode").blur(function appCodeajax() {
			//var appCodes = $("#appCode").val();
			var appCodes = $.trim($("#appCode").val());
			$.ajax({
				data : "appCode=" + $("#appCode").val(),
				type : "GET",
				dataType : 'json',
				url : "${ctx}/sys/app/existsAppCode",
				success : function(data) {
					if (data.msg == 'none') {
						//回调
						appCodeajax();
						$("#appCodeError").text("*");
					} else {
						$("#appCodeError").text(data.msg);
						$("#appCode").val("");
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/app/index">应用列表</a></li>
		<li class="active"><a href="${ctx}/sys/app/form?id=${app.appCode}">应用${not empty app.appCode?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="app" action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">应用编码:</label>
			<div class="controls">
				<form:input path="appCode" htmlEscape="false" maxlength="50" class="required" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="输入数字、字母或数字字母组合" id="appCode" onblur="appCodeajax()" />
				<span class="help-inline"><font color="red" id="appCodeError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应用名称:</label>
			<div class="controls">
				<form:input path="appName" htmlEscape="false" maxlength="50" class="required" id="appName" placeholder="" />
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属机构:</label>
			<div class="controls">
				<!--<form:input path="depCode" htmlEscape="false" maxlength="50"/>  -->
				<sys:treeselect id="orgNo" name="orgNo" value="${app.orgNo}" labelName="orgName" labelValue="${app.orgName}" title="机构" url="/sys/org/treeData" cssClass="" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问URL:</label>
			<div class="controls">
				<form:input path="accUrl" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge" style="width:200px" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">启用图标:</label>
			<div class="controls">
				<form:input path="icon" htmlEscape="false" maxlength="50" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">打开页面:</label>
			<div class="controls">
				<form:input path="openPage" htmlEscape="false" maxlength="50" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">禁用图标:</label>
			<div class="controls">
				<form:input path="disIcon" htmlEscape="false" maxlength="50" />
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