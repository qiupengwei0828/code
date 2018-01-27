<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>系统员工管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var userId = $("#userId").val();

		if (userId !== "") {
			$("#inputForm").attr("action", "${ctx}/sys/user/update");
			$("#userId").attr("disabled", "true");
		} else {
			$("#inputForm").attr("action", "${ctx}/sys/user/addUser");
		}

		$("#btnSubmit").click(function() {
			var userId = $("#userId").val();
			var userName = $("#userName").val();
			var hrNo = $("#hrNo").val();
			var orgNo = $("#orgNo").val();
			//当userId、userName都不为空时提交
			if (userId != "" && userName != "" && hrNo != "" && orgNo != "") {
				changeBtnSubmit();
			} else {
				alert("带星号为必填项，请检查后重试!!!");
			}
		});

		function changeBtnSubmit() {
			$("#userId").removeAttr("disabled");
			$("#inputForm").submit();
		}
		//对电子邮件的校验
		$("#email").blur(function() {
			var email = $("#email").val().trim();
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if (!myreg.test(email)) {
				$("#emailError").html("输入的电子邮件不正确！");
				$("#email").val("");
			} else {
				$("#emailError").html("");
			}
		});
		//用于对手机号码进行校验
		$("#mobile").blur(function() {
			var mobile = $("#mobile").val().trim();
			//var myreg = /^1[3|4|5|8][0-9]\d{11}$/;
			var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
			if (!myreg.test(mobile)) {
				$("#mobileError").html("输入的电话号码格式不正确！");
				$("#mobile").val("");
			} else {
				$("#mobileError").html("");
			}
		});

		$("#userId").blur(function() {
			var userId = $("#userId").val().trim();
			if (userId !== "") {
				$("#userIdError").html("*");
			} else {
				$("#userIdError").html("员工ID不能为空！");
			}
		});

		$("#userName").blur(function() {
			var userName = $("#userName").val().trim();
			if (userName !== "") {
				$("#userNameError").html("*");
			} else {
				$("#userNameError").html("员工姓名不能为空！");
			}
		});

		/*
		 * ajax
		 * 添加userId时
		 * 验证当前userId是否已经添加
		 */
		$("#userId").blur(function userIdajax() {
			$.ajax({
				data : "userId=" + $("#userId").val(),
				type : "POST",
				dataType : 'json',
				url : "${ctx}/sys/user/existsUserId",
				success : function(data) {
					if (data.msg == 'none') {
						//回调
						appCodeajax();
						$("#userId").text("*");
					} else {
						$("#userIdError").html(data.msg);
						$("#userId").val("");
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
		<li><a href="${ctx}/sys/user/list">员工列表</a></li>
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.userId}">员工${not empty
				user.userId?'修改':'添加'}</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="user" action="" method="post" class="form-horizontal">
		<div class="row">
			<div class="span2">
				<label>员工ID</label>
			</div>
			<div class="span3">
				<form:input path="userId" htmlEscape="false" maxlength="50" style="width:150px" id="userId" onblur="userIdajax()" />
				<span class="help-inline"><font color="red" id="userIdError">*</font></span>
			</div>

			<div class="span2">
				<label>所属机构</label>
			</div>
			<div class="span4">
				<sys:treeselect id="orgNo" name="orgNo" value="${user.orgNo}" labelName="orgName" labelValue="${user.orgName}" title="机构" url="/sys/org/treeData" cssStyle="width:150px;font-size:10px;" />
				<span class="help-inline"><font color="red" id="orgNoError">*</font></span>
			</div>

		</div>
		<div class="row">

			<div class="span2">
				<label>员工姓名</label>
			</div>
			<div class="span3" padding-left=20px;>
				<form:input path="userName" htmlEscape="false" maxlength="50" style="width:150px" id="userName" />
				<span class="help-inline"><font color="red" id="userNameError">*</font></span>
			</div>


			<div class="span2">
				<label>业务线条</label>
			</div>
			<div class="span3">
				<form:select path="depCode" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_DEP_CODE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>

		</div>
		<div class="row">

			<div class="span2">
				<label>人力资源编号</label>
			</div>
			<div class="span3">
				<form:input path="hrNo" htmlEscape="false" maxlength="50" style="width:150px" id="hrNo" />
				<span class="help-inline"><font color="red" id="hrNoError">*</font></span>
			</div>

			<div class="span2">
				<label>性别</label>
			</div>
			<div class="span3">
				<form:select path="sex" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_SEX')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>

		</div>
		<div class="row">
			<div class="span2">
				<label>证件类型</label>
			</div>
			<div class="span3">
				<form:select path="certTyp" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_CERT_TYP')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>

			<div class="span2">
				<label>证件号码</label>
			</div>
			<div class="span3">
				<form:input path="certNo" htmlEscape="false" maxlength="50" id="certNo" style="width:150px" />
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>籍 贯</label>
			</div>
			<div class="span3">
				<form:input path="nativePlace" htmlEscape="false" maxlength="50" style="width:150px" id="nativePlace" />
			</div>
			<div class="span2">
				<label>民族</label>
			</div>

			<div class="span3">
				<form:select path="nation" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_NATION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>用工类别</label>
			</div>
			<div class="span3">
				<form:select path="userClass" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>


			<div class="span2">
				<label>参加工作时间:</label>
			</div>
			<div class="span3">
				<input id="beginWorkDate" name="beginWorkDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>政治面貌</label>
			</div>
			<div class="span3">
				<form:select path="politicalStatus" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_POL_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>


			<div class="span2">
				<label>学历</label>
			</div>
			<div class="span3">
				<form:select path="stLevel" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_ST_LEVEL')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>职务</label>
			</div>
			<div class="span3">
				<form:select path="post" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_POST')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>


			<div class="span2">
				<label>职级</label>
			</div>
			<div class="span3">
				<form:select path="rank" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_RANK')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>职称</label>

			</div>
			<div class="span3">
				<form:select path="posTitle" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_POS_TITLE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
			<div class="span2">
				<label>岗位</label>
			</div>
			<div class="span3">
				<form:select path="pos" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_USER_POSITION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>到岗时间</label>
			</div>
			<div class="span3">
				<input id="posDate" name="posDate" type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
			<div class="span2">
				<label>办公电话</label>
			</div>
			<div class="span3">
				<form:input path="tel" htmlEscape="false" maxlength="50" id="tel" style="width:150px" />
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>手机</label>
			</div>
			<div class="span3">
				<form:input path="mobile" htmlEscape="false" maxlength="50" style="width:150px" id="mobile" />
				<span class="help-inline"><font color="red" id="mobileError"></font></span>
			</div>
			<div class="span2">
				<label>邮箱</label>
			</div>
			<div class="span3">
				<form:input path="email" htmlEscape="false" maxlength="50" style="width:150px" id="email" />
				<span class="help-inline"><font color="red" id="emailError"></font></span>
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>备用联系方式</label>
			</div>
			<div class="span3">
				<form:input path="mobile2" htmlEscape="false" maxlength="50" style="width:150px" id="mobile2" />
			</div>
			<div class="span2">
				<label>员工状态</label>
			</div>
			<div class="span3">
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
			<div class="span2"></div>
			<div class="span3"></div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>