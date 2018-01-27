<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>机构管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate({
			submitHandler : function(form) {
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/menu/index">菜单列表</a></li>
		<li class="active"><a href="${ctx}/sys/menu/form?menuId=${menu.menuId}">菜单${not empty menu.menuId?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save" method="post" class="form-horizontal">
		<form:hidden path="menuId" />
		<sys:message content="${message}" />

		<div class="control-group">
			<label class="control-label">应用系统:</label>
			<div class="controls">
				<form:select id="appCode" path="appCode" class="input-medium" style="width:220px">
					<form:options items="${appCodeList}" itemLabel="appName" itemValue="appCode" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">上级菜单:</label>
			<div class="controls">
				<sys:treeselect id="pMenuId" name="pMenuId" value="${menu.pMenuId}" labelName="pMenuName" labelValue="${menu.pMenuName}" title="菜单" url="/sys/menu/treeData" extId="" cssClass="" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">菜单名称:</label>
			<div class="controls">
				<form:input path="menuName" htmlEscape="false" maxlength="50" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">URL:</label>
			<div class="controls">
				<form:input path="actUrl" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="dispNo" htmlEscape="false" maxlength="5" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提示信息:</label>
			<div class="controls">
				<form:input path="tip" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图标:</label>
			<div class="controls">
				<sys:iconselect id="icon" name="icon" value="${menu.icon}" />
			</div>
		</div>


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp; <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>