<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>轮岗计划制定</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("#pos_id").val() != "") {
			$("#iForm").attr("action", "${ctx}/posrot/rotManage/update");
		} else {
			$("#iForm").attr("action", "${ctx}/posrot/rotManage/insert");
		}

		$("#rotUserName").attr("readonly", "readonly");
		$("#rotOrgName").attr("readonly", "readonly");
		$("#rotPosName").attr("readonly", "readonly");

		$("#repUserName").attr("readonly", "readonly");
		$("#repOrgName").attr("readonly", "readonly");
		$("#repPosName").attr("readonly", "readonly");

		function test() {
			var isOk = true;

			if ($("#toPos").val() == "") {
				$("#toPos_title").html("轮换岗位不能为空!");
				isOk = false;
			}
			$("#toPos").click(function() {
				$("#toPos_title").html("*");
				isOk = true;
			});

			if ($("#toOrgNoId").val() == "") {
				$("#toOrgNo_title").html("轮岗机构不能为空!");
				isOk = false;
			}
			$("#toOrgNoButton, #toOrgNoName").click(function() {
				$("#toOrgNo_title").html("*");
				isOk = true;
			});

			if ($("#repUserName").val() == "") {
				$("#repUserName_title").html("顶岗员工不能为空!");
				isOk = false;
			}
			$("#repUserIdshowUser").click(function() {
				$("#repUserName_title").html("*");
				isOk = true;
			});

			if ($("#hanDate").val() == "") {
				$("#hanDate_title").html("交接日期不能为空!");
				isOk = false;
			}
			$("#hanDate").click(function() {
				$("#hanDate_title").html("*");
				isOk = true;
			});

			if ($("#toDate").val() == "") {
				$("#toDate_title").html("到岗日期不能为空!");
				isOk = false;
			}
			$("#toDate").click(function() {
				$("#toDate_title").html("*");
				isOk = true;
			});

			//提交表单
			if (isOk == true) {
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
	 * 设置轮岗人信息 
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
		<li class="active"><a>轮岗计划${not empty rotation.rotateNo?'修改':'制定'}</a></li>
	</ul>

	<form:form id="iForm" modelAttribute="rotation" action="" method="post" class="form-horizontal">

		<input id="pos_id" type="hidden" name="id" value="${rotation.id}" />

		<!-- 轮岗员工ID -->
		<input id="rotUserId" type="hidden" name="rotUserId" value="${rotation.rotUserId}" />

		<div class="control-group">
			<label class="control-label">轮岗员工姓名:</label>
			<div class="controls">
				<form:input path="rotUserName" htmlEscape="false" maxlength="50" class="required" id="rotUserName" />
			</div>
		</div>

		<!-- 轮岗员工所在机构 -->
		<input id="rotOrgNo" type="hidden" name="rotOrgNo" value="${rotation.rotOrgNo}" />

		<div class="control-group">
			<label class="control-label">轮岗员工机构:</label>
			<div class="controls">
				<form:input path="rotOrgName" htmlEscape="false" maxlength="50" class="required" id="rotOrgName" />
			</div>
		</div>

		<!-- 轮岗员工所在岗位 -->
		<input id="rotPos" type="hidden" name="rotPos" value="${rotation.rotPos}" />

		<div class="control-group">
			<label class="control-label">轮岗员工岗位:</label>
			<div class="controls">
				<form:input path="rotPosName" htmlEscape="false" maxlength="50" class="required" id="rotPosName" value="${fns:getDicLabel(rotation.rotPos, 'DIC_USER_POSITION', '')}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">轮换岗位:</label>
			<div class="controls">
				<form:select id="toPos" path="toPos" class="input-medium" style="width:220px">
					<form:option value="" label="全部" />
					<form:options items="${poslist}" itemLabel="posName" itemValue="posCode" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red" id="toPos_title">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">轮换机构:</label>
			<div class="controls">
				<sys:treeselect id="toOrgNo" name="toOrgNo" value="${rotation.toOrgNo}" labelName="toOrgName" labelValue="${rotation.toOrgName}" title="机构" url="/sys/org/treeData" cssStyle="width:150px;font-size:10px;" />
				<span class="help-inline"><font color="red" id="toOrgNo_title">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">到岗日期:</label>
			<div class="controls">
				<input id="toDate" name="toDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${toDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span class="help-inline"><font
					color="red" id="toDate_title">*</font></span>
			</div>
		</div>


		<input id="repUserId" name="repUserId" type="hidden" value="${rotation.repUserId}" />

		<div class="control-group">
			<label class="control-label">顶岗人姓名:</label>
			<div class="controls">
				<form:input path="repUserName" htmlEscape="false" maxlength="50" class="required" id="repUserName" />
				<sys:userList id="repUserId" name="userId" labelValue="选择用户" />
				<span class="help-inline"><font color="red" id="repUserName_title">*</font></span>
			</div>
		</div>

		<input id="repOrgNo" name="repOrgNo" type="hidden" value="${rotation.repOrgNo}" />
		<div class="control-group">
			<label class="control-label">顶岗人机构:</label>
			<div class="controls">
				<form:input path="repOrgName" htmlEscape="false" maxlength="50" class="required" id="repOrgName" />
			</div>
		</div>

		<input id="repPos" name="repPos" type="hidden" value="${rotation.repPos}" />
		<div class="control-group">
			<label class="control-label">顶岗人当前岗位:</label>
			<div class="controls">
				<form:input path="repPosName" htmlEscape="false" maxlength="50" class="required" id="repPosName" value="${fns:getDicLabel(rotation.repPos, 'DIC_USER_POSITION', '')}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">交接日期:</label>
			<div class="controls">
				<input id="hanDate" name="hanDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${hanDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> <span class="help-inline"><font
					color="red" id="hanDate_title">*</font></span>
			</div>
		</div>

		<input id="status" type="hidden" name="status" />

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="提交" /> <input id="btnSave" class="btn btn-primary" type="button" value="保存" /> <input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)" />
		</div>

	</form:form>
</body>
</html>