<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>修改密码</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
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
<form:form id="inputFormPwd" modelAttribute="user"  method="post" class="form-horizontal">
      <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">用户ID:</label>
			   <div class="controls">
                 <form:input  path="userId" htmlEscape="false" maxlength="50"  />
			   </div>
		   </div>
		   </div>
		   </div>
		 <div class="row">
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">原來密码:</label>
		   <div class="controls">
                <form:input path="userName" htmlEscape="false" maxlength="50"  /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		    <div class="row">
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">新密码:</label>
		   <div class="controls">
                <form:input path="pwd" htmlEscape="false" maxlength="50" id="pwd"/> 
		   </div>
		   </div>
		    </div>
		   </div>
  </form:form>
			
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
<script type="text/javascript">
$(document).ready(function() {
		
		$("#btnSubmit").click(function() {
		var pwd = $("#pwd").val();
			$.ajax({
				type : "GET",
				dataType : 'json',
				url : "${ctx}/sys/person/modifyPwd?pwd="+pwd,
				success : function(data) {
					if (data==1) {
					   history.go(-1);
					}
				}
			});
		});
	});
		
</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>