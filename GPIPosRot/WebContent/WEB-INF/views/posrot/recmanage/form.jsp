<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>强修计划制定/修改</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("#id").val() != "") {
			$("#iForm").attr("action", "${ctx}/posrot/recManage/update");
		} else {
			$("#iForm").attr("action", "${ctx}/posrot/recManage/insert");
		}

		$(":text").attr("readonly", "readonly");

		function test() {

			var isok = true;

			if ($("#recUserId").val() == "") {
				$("#recUserName_title").text("顶岗人不能为空");
				isok = false;
			}

			$("#recUserIdshowUser").click(function() {
				$("#recUserName_title").text("*");
				isok = true
			});

			if ($("#recBeginDate").val() == "") {
				$("#recBeginDate_title").text("强修开始时间不能为空");
				isok = false;
			}

			$("#recBeginDate").click(function() {
				$("#recBeginDate_title").text("*");
				isok = true;
			});

			if ($("#recEndDate").val() == "") {
				$("#recEndDate_title").text("强修结束时间不能为空");
				isok = false;
			}

			$("#recEndDate").click(function() {
				$("#recEndDate_title").text("*");
				isok = true
			});

			if ($("#repUserId").val() == "") {
				$("#repUserName_title").text("顶岗人不能为空");
				isok = false;
			}

			$("#recUserIdshowUser").click(function() {
				$("#repUserName_title").text("*");
				isok = true
			});

			if ($("#hanDate").val() == "") {
				$("#hanDate_title").text("交接日期不能为空");
				isok = false;
			}

			$("#hanDate").click(function() {
				$("#hanDate_title").text("*");
				isok = true
			});

			if (isok == true) {
				$("#iForm").submit();
			}
		}

		$("#btnSubmit").click(function() {
			$("#status").val("2");
			test();
		});

		$("#btnSave").click(function() {
			$("#status").val("1");
			test();
		});
	});

	/*
	 * 设置强修员工信息
	 */
	function setRec() {
		$("#recUserName").val(user.userName);
		$("#recUserId").val(user.userId);
		$("#recOrgNo").val(user.orgNo);
		$("#recOrgName").val(user.orgName);
		$("#recPos").val(user.pos);
		$("#recPosName").val(user.posName);
	}

	/*
	 * 设置顶岗员工信息
	 */
	function setRep() {
		$("#repUserName").val(user.userName);
		$("#repUserId").val(user.userId);
		$("#repOrgNo").val(user.orgNo);
		$("#repOrgName").val(user.orgName);
		$("#repPos").val(user.pos);
		$("#repPosName").val(user.posName);
	}
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a>强修计划${not empty recess.id?'修改':'制定'}</a></li>
	</ul>

	<form:form id="iForm" modelAttribute="recess" action="" method="post" class="form-horizontal">

		<input id="id" type="hidden" name="id" value="${recess.id}" />

		<input id="recessNo" type="hidden" name="recessNo" value="${recess.recessNo}" />

		<!-- 强修员工ID -->
		<input id="recUserId" type="hidden" name="recUserId" value="${recess.recUserId}" />

		<div class="control-group">
			<label class="control-label">强修员工姓名:</label>
			<div class="controls">
				<form:input path="recUserName" htmlEscape="false" maxlength="50" class="required" id="recUserName" />
				<sys:userList id="recUserId" name="userId" labelValue="选择用户" />
				<span class="help-inline"><font color="red" id="recUserName_title">*</font></span>
			</div>
		</div>

		<!-- 强修员工所在机构 -->
		<input id="recOrgNo" type="hidden" name="recOrgNo" value="${recess.recOrgNo}" />

		<div class="control-group">
			<label class="control-label">强修员工机构:</label>
			<div class="controls">
				<form:input path="recOrgName" htmlEscape="false" maxlength="50" class="required" id="recOrgName" />
			</div>
		</div>

		<!-- 强修员工所在岗位 -->
		<input id="recPos" type="hidden" name="recPos" value="${recess.recPos}" />

		<div class="control-group">
			<label class="control-label">强修员工岗位:</label>
			<div class="controls">
				<form:input path="recPosName" htmlEscape="false" maxlength="50" class="required" id="recPosName" value="${recess.recPosName}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">强修开始日期:</label>
			<div class="controls">
				<input id="recBeginDate" name="recBeginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${recBeginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span
					class="help-inline"><font color="red" id="recBeginDate_title">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">强修结束日期:</label>
			<div class="controls">
				<input id="recEndDate" name="recEndDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${recEndDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span class="help-inline"><font
					color="red" id="recEndDate_title">*</font></span>
			</div>
		</div>

		<!-- 顶岗人 -->
		<input id="repUserId" name="repUserId" type="hidden" value="${recess.repUserId}" />

		<div class="control-group">
			<label class="control-label">顶岗人姓名:</label>
			<div class="controls">
				<form:input path="repUserName" htmlEscape="false" maxlength="50" class="required" id="repUserName" />
				<sys:userList id="repUserId" name="userId" labelValue="选择用户" />
				<span class="help-inline"><font color="red" id="repUserName_title">*</font></span>
			</div>
		</div>

		<!-- 顶岗人机构 -->
		<input id="repOrgNo" name="repOrgNo" type="hidden" value="${recess.repOrgNo}" />

		<div class="control-group">
			<label class="control-label">顶岗人机构:</label>
			<div class="controls">
				<form:input path="repOrgName" htmlEscape="false" maxlength="50" class="required" id="repOrgName" />
			</div>
		</div>

		<!-- 顶岗人岗位 -->
		<input id="repPos" name="repPos" type="hidden" value="${recess.repPos}" />

		<div class="control-group">
			<label class="control-label">顶岗人当前岗位:</label>
			<div class="controls">
				<form:input path="repPosName" htmlEscape="false" maxlength="50" class="required" id="repPosName" value="${recess.repPosName}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">交接日期:</label>
			<div class="controls">
				<input id="hanDate" name="hanDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${hanDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span class="help-inline"><font
					color="red" id="hanDate_title">*</font></span>
			</div>
		</div>

		<!-- 状态 -->
		<input type="hidden" name="status" id="status" value="${recess.status}" />

		<div class="form-actions">
<<<<<<< .mine
			<input id="btnSubmit" class="btn btn-primary" type="button" value="提交" />
			<input id="btnSave" class="btn btn-primary" type="button" value="保存" />
			<input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)" />
||||||| .r1240
			<input id="btnSubmit" class="btn btn-primary" type="button" value="提交" /> <input id="btnSave" class="btn btn-primary" type="button" value="保存" /> <input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)" />
=======
			<c:choose>
				<c:when test="${status != ''}">
					<input id="btnSave" class="btn btn-primary" type="button" value="保存" />
					<input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)" />
				</c:when>
				<c:otherwise>
					<input id="btnSubmit" class="btn btn-primary" type="button" value="提交" />
					<input id="btnSave" class="btn btn-primary" type="button" value="保存" />
					<input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)" />
				</c:otherwise>
			</c:choose>
>>>>>>> .r1280
		</div>
	</form:form>

</body>
</html>