<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
		$(document).ready(function() {
		$("#inputForm").attr("action", "${ctx}/bd/bdplan/addDetail");

		$("#btnSubmit").click(function() {
			$("#inputForm").submit();
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
		<li class="active"><a href="${ctx}/bd/bdplan/detail?planId=${page.list.get(0).planId}">请领详情列表</a></li>
		<li><a href="${ctx}/bd/bdplan/detailAdd?planId=${page.list.get(0).planId}">请领详情添加</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bdPlanDetail" action="" method="post" class="form-horizontal">
		<div class="row">
			<div class="span2">
				<label>单证代码</label>
			</div>
			<div class="span3">
				<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:150px" id="certactCode"  />
				<form:input path="planId" htmlEscape="false" type="hidden" maxlength="50" style="width:150px" id="planId" value="${bdPlanDetail.planId}" />
			</div>
			<div class="span2">
				<label>单证名称</label>
			</div>
			<div class="span3">
				<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:150px" id="certactName" />
			</div>
		</div>


	     <div class="row">
			<!-- <div class="span2">
				<label>用户密码</label>
			</div>  -->
			<%-- <div class="span3">
				<form:input path="pwd" htmlEscape="false" maxlength="50" style="width:150px" id="pwd" />
			</div> --%>
			<div class="span2">
				<label>凭证类型</label>
			</div>
			<div class="span3">
				<form:select path="certactType" class="input-medium">
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
			</div>
		

		
			<div class="span2">
				<label>库存量</label>
			</div>
			<div class="span3">
				<form:input path="storeNum" htmlEscape="false" maxlength="50" style="width:150px" id="storeNum" />
			</div>
			</div>
			<div class="row">
			<div class="span2">
				<label>请领量</label>
			</div>
			<div class="span3">
				<form:input path="planNum" htmlEscape="false" maxlength="50" style="width:150px" id="planNum" />
			</div>
			</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保存" onclick="onclickSubmit()" /> 
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>