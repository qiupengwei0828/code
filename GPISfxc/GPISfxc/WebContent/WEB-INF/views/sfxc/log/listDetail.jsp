<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctxStatic}/jquery-validation/1.11.1/lib/jquery.form.js" type="text/javascript"></script>
<style type="text/css">
.span2 {
	padding-left: 20px;
	width: 100px;
}

.row {
	padding-bottom: 5px;
}
caption {
        text-align:left;
        margin:0 auto;
}
.c1 {
	background-color: #000000;
}
.c2 {
	background-color: #DFDFDF;
}
.sss{
float:right;
}
</style>



</head>
<body>
<div class="form-actions" >
   <form:form id="inputForm1" modelAttribute="logMessage" action=""
		method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">请求单号:</label>
			<div class="span3">
				<form:input path="bdhm" htmlEscape="false" maxlength="50"
					style="width:150px" id="bdhm" />
			</div>
			<label class="control-label">请求单位:</label>
			<div class="span3">
				<form:input path="queryUnit" htmlEscape="false" maxlength="50"
					style="width:150px" id="queryUnit" />
			</div>			
		</div>
				
		<div class="control-group">			
			<label class="control-label">请求时间:</label>
			<div class="span3">
				<form:input path="receivedtime" htmlEscape="false" maxlength="50"
					style="width:150px" id="receivedtime" />
			</div>
			<label class="control-label">被查询人姓名:</label>
			<div class="span3">
				<form:input path="queryName" htmlEscape="false" maxlength="50"
					style="width:150px" id="queryName" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人:</label>
			<div class="span3">
				<form:input path="examiner" htmlEscape="false" maxlength="50"
					style="width:150px" id="examiner" />
			</div>
			<label class="control-label">审核时间:</label>
			<div class="span3">
				<form:input path="examinetime" htmlEscape="false" maxlength="50"
					style="width:150px" id="examinetime" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈人:</label>
			<div class="span3">
				<form:input path="backer" htmlEscape="false" maxlength="50"
					style="width:150px" id="backer" />
			</div>
			<label class="control-label">反馈时间:</label>
			<div class="span3">
				<form:input path="backtime" htmlEscape="false" maxlength="50"
					style="width:150px" id="backtime" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理结果:</label>
			<div class="span3">
				<form:input path="status" htmlEscape="false" maxlength="50"
					style="width:150px" id="status" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="msg" htmlEscape="false" style="width:600px"
				id="msg"></form:textarea>
			</div>
		</div>
		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
	</form:form>
	

</body>
</html>
<script type="text/javascript">
		
</script>