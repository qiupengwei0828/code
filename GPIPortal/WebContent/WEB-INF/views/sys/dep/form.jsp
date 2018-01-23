<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>条线管理</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		
		var depCode = $.trim($("#depCode").val());
		if (depCode !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/dep/update");
			$("#depCode").attr("disabled", "true");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/dep/insert");
		}

		/*
		 * 当点击(btnSubmit)保存按钮时
		 * 移除输入框 id = "appCode" 的 disabled 属性
		 */
		$("#btnSubmit").click(function() {
			//var appCode = $("#appCode").val().trim();
			//var appName = $("#appName").val().trim();
			var depCode = $.trim($("#depCode").val());
			var depName = $.trim($("#depName").val());

			//当appCode、appName都不为空时提交
			if (depCode !== "" && depName !== "") {
				changeBtnSubmit();
			}
		});

		function changeBtnSubmit() {
			$("#depCode").removeAttr("disabled");
			$("#inputForm").submit();
		}
	    
	    
	    $("#depCode").blur(function(){
	     var depCode = $.trim($("#depCode").val());
	      if (depCode == "") {
				$("#depCodeError").html("业务编码不能为空！！！");
			}else{
			    $("#depCodeError").html("*");
			}
	      });
		 $("#depName").blur(function(){
	     var depName = $.trim($("#depName").val());
	      if (depName == "") {
				$("#depNameError").html("业务名称不能为空！！！");
			}else{
			    $("#depNameError").html("*");
			}
	      });

		
		$("#depCode").blur(function depCodeajax() {
			//var appCodes = $("#appCode").val();
			var depCodes = $.trim($("#depCode").val());
			$.ajax({
				data : "depCode=" + $("#depCode").val(),
				type : "GET",
				dataType : 'json',
				url : "${ctx}/sys/dep/existsDepCode",
				success : function(data) {
					if (data.msg == 'none') {
						//回调
						depCodeajax();
						$("#depCodeError").text("*");
					} else {
						$("#depCodeError").text(data.msg);
						$("#depCode").val("");
					}
				}
			});
		});
	});
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
				<form:input path="depCode" htmlEscape="false" maxlength="50" class="required" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="输入数字、字母或数字字母组合" id="depCode" onblur="depCodeajax()" />
				<span class="help-inline"><font color="red" id="depCodeError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条线名称:</label>
			<div class="controls">
				<form:input path="depName" htmlEscape="false" maxlength="50" class="required" id="depName" />
				<span class="help-inline"><font color="red" id="depNameError">*</font></span>
			</div>
		</div>
	
		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false"  maxlength="50" class="required" style="width:200px" />
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
