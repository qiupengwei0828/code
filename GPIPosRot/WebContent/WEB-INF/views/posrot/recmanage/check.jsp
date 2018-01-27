<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>强修计划审批</title>
<style type="text/css">
.cen {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}

	var status = $("#status").val();
	var actionCode = "";
	var actionCode = "";
	var ids = "";
	var idArr = "";

	$(document).ready(function() {

		$("#btnSubmit").click(function() {
			$("input[name='checkID']:checkbox").each(function() {
				if ($(this).attr("checked") == "checked") {
					ids += $(this).val() + ",";
				}
			});

			if (ids != "") {
				$('#psaa_title').modal('show');
			} else {
				alert("未选择强修计划，请先选择后再试！！！");
			}
		});

		$("#yesPass").click(function() {
			actionCode = "1";
			getCheckID();
		});

		$("#noPass").click(function() {
			actionCode = "0";
			getCheckID();
		});

		function getCheckID() {
			ids = ids.substring(0, ids.length - 1);
			idArr = ids.split(",");
			if (idArr != "") {
				runchange();
			}
			ids = "";
		}
	});

	function runchange() {
		for ( var i in idArr) {
			$.ajax({
				data : {
					'id' : idArr[i],
					'status' : $("#status").val(),
					'opeOpinion' : $("#opeOpinion").val(),
					'actionCode' : actionCode
				},
				type : "POST",
				dataType : 'json',
				url : "${ctx}/posrot/recManage/pass",
				success : function(data) {
					window.location.href = "${ctx}/posrot/recManage/check";
				}
			});
		}

		idArr = "";

	}
</script>
</head>
<body>

	<input id="status" name="status" type="hidden" value="${status}">


	<form:form id="pageForm" modelAttribute="recess" action="${ctx}/posrot/recManage/check" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>



	<form:form id="sForm" modelAttribute="recess" action="${ctx}/posrot/recManage/check" method="post" class="breadcrumb form-search">

		<label>强修员工姓名：</label>
		<form:input id="recUserName" path="recUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<label>顶岗员工姓名：</label>
		<form:input id="repUserName" path="repUserName" htmlEscape="false" maxlength="50" style="width:150px;" />

		<input id="btnQuery" class="btn btn-primary" type="submit" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
		<input id="btnSubmit" class="btn btn-primary" type="button" value="审批" />

	</form:form>


	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
			<th><input id="selAll" name="selAll" type="checkbox"></th>
			<th>强修员工姓名</th>
			<th>强修员工机构</th>
			<th>强修员工岗位</th>
			<th>强修开始日期</th>
			<th>强修结束日期</th>
			<th>顶岗人姓名</th>
			<th>顶岗人机构</th>
			<th>顶岗人岗位</th>
			<th>交接日期</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="recess">
			<tr>
				<td><input type="checkbox" value="${recess.id}" name="checkID"></td>
				<td>${recess.recUserName}</td>
				<td>${recess.recOrgName}</td>
				<td>${recess.recPosName}</td>
				<td>${recess.recBeginDate}</td>
				<td>${recess.recEndDate}</td>
				<td>${recess.repUserName}</td>
				<td>${recess.repOrgName}</td>
				<td>${recess.repPosName}</td>
				<td>${recess.hanDate}</td>
				<td>${fns:getDicLabel(recess.status,"DIC_POS_ROTATION_STATUS", "")}</td>
				<td><a href="${ctx}/posrot/recManage/info?id=${recess.id}">审批</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>

	<div id="psaa_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">确认审批</h3>
		</div>
		<div class="modal-body">
			<h5>确认审批强修计划？</h5>
			<br>
			<div class="cen">
				<textarea id="opeOpinion" name="opeOpinion" rows="6" style="width: 500px;"></textarea>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="yesPass">通过</button>
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