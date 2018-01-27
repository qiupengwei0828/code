<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计划审批</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
.cen {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}

	$(document).ready(function() {
		$("#btnReset").click(function() {
			$("#rotateNo").val("");
			$("#rotUserName").val("");
			$("#rotPos").val("");
			$("#repUserName").val("");
			$("#sForm").submit();
		});

		var actionCode = "";

		$("#btnSubmit").click(function() {
			var id_temp = "";
			$("input[name='checkID']:checkbox").each(function() {
				if ($(this).attr("checked") == "checked") {
					id_temp += $(this).val() + ",";
				}
			});

			/////////////////////////////////
			$("#okPass").click(function() {
				actionCode = "1";
				getCheckID(actionCode);
			});
			$("#noPass").click(function() {
				actionCode = "0";
				getCheckID(actionCode);
			});
			/////////////////////////////////

			if (id_temp != "") {
				$('#psaa_title').modal('show');
			} else {
				alert("未选择轮岗计划，请先选择后再试！！！");
			}
		});

	});

	///////////////////////////////////

	var ids = "";
	var idArr = "";
	function getCheckID(actionCode) {
		$("input[name='checkID']:checkbox").each(function() {
			if ($(this).attr("checked") == "checked") {
				ids += $(this).val() + ",";
			}
		});
		ids = ids.substring(0, ids.length - 1);
		idArr = ids.split(",");
		if (ids != "") {
			getID(actionCode);
		}
		ids = "";
	}
	function getID(actionCode) {
		for ( var i in idArr) {
			$.ajax({
				data : {
					'id' : idArr[i],
					'status' : $("#statusValue").val(),
					'opeOpinion' : $("#opeOpinion").val(),
					'actionCode' : actionCode
				},
				type : "POST",
				dataType : 'json',
				url : "${ctx}/posrot/rotManage/pass",
				success : function(data) {
					window.location.href = "${ctx}/posrot/rotManage/check";
				}
			});
		}
		idArr = "";
	}
</script>
</head>
<body>

	<form:form id="pageForm" modelAttribute="rotation" action="${ctx}/posrot/rotManage/check" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form id="sForm" modelAttribute="rotation" action="${ctx}/posrot/rotManage/check" method="post" class="breadcrumb form-search">
		<label>计划编号：</label>
		<form:input id="rotateNo" path="rotateNo" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>轮岗员工姓名：</label>
		<form:input id="rotUserName" path="rotUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>轮岗岗位名称：</label>
		<form:select id="rotPos" path="rotPos" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${posList}" itemLabel="posName" itemValue="posCode" htmlEscape="false" />
		</form:select>

		<label>顶岗员工姓名：</label>
		<form:input id="repUserName" path="repUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>状态：</label>
		<form:select id="status" path="status" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_POS_ROTATION_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<input id="btnQuery" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />

		<input id="statusValue" type="hidden" value="${sta}">

		<input id="btnSubmit" class="btn btn-primary" type="button" value="审批" />
	</form:form>


	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
			<th><input id="selAll" name="selAll" type="checkbox"></th>
			<th>轮岗员工姓名</th>
			<th>轮岗员工机构</th>
			<th>轮岗员工岗位</th>
			<th>轮换岗位</th>
			<th>轮换机构</th>
			<th>到岗日期</th>
			<th>顶岗人姓名</th>
			<th>顶岗人机构</th>
			<th>顶岗人岗位</th>
			<th>交接日期</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="rotation">
			<tr>
				<td><input type="checkbox" value="${rotation.id}" name="checkID"></td>
				<td>${rotation.rotUserName}</td>
				<td>${rotation.rotOrgName}</td>
				<td>${fns:getDicLabel(rotation.rotPos,"DIC_USER_POSITION", "")}</td>
				<td>${fns:getDicLabel(rotation.toPos,"DIC_USER_POSITION", "")}</td>
				<td>${rotation.toOrgName}</td>
				<td>${rotation.toDate}</td>
				<td>${rotation.repUserName}</td>
				<td>${rotation.repOrgName}</td>
				<td>${fns:getDicLabel(rotation.repPos,"DIC_USER_POSITION", "")}</td>
				<td>${rotation.hanDate}</td>
				<td>${fns:getDicLabel(rotation.status,"DIC_POS_ROTATION_STATUS", "")}</td>
				<td><a href="${ctx}/posrot/rotManage/info?id=${rotation.id}">审批</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>



	<div id="psaa_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">审批</h3>
		</div>
		<div class="modal-body">
			<h5 style="margin-left: 20px;">审批意见</h5>
			<br>
			<div class="cen">
				<textarea id="opeOpinion" name="opeOpinion" rows="6" style="width: 500px;"></textarea>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="okPass">通过</button>
			<button class="btn btn-danger" id="noPass">不通过</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>

	<script type="text/javascript">
		$("#selAll").click(function() {
			if (this.checked) {
				$("input[name='checkID']").attr('checked', true)
			} else {
				$("input[name='checkID']").attr('checked', false)
			}
		});
	</script>


</body>
</html>