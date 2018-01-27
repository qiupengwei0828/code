<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的轮岗计划</title>
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

		$("#btnGet").click(function() {

			var id_temp = "";

			$("input[name='checkID']:checkbox").each(function() {
				if ($(this).attr("checked") == "checked") {
					id_temp += $(this).val() + ",";
				}
			});
			/////////////////////////////////
			if (id_temp != "") {
				$('#psaa_title').modal('show');
			} else {
				alert("未选择轮岗计划，请先选择后再试！！！");
			}
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
					window.location.href = "${ctx}/posrot/rotManage/mypos_list";
				}
			});
		}
		idArr = "";
	}
</script>
</head>
<body>

	<input id="statusValue" type="hidden" value="${sta}">

	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
			<!-- <th><input id="selAllID" name="selAll" type="checkbox"></th> -->
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
		<c:forEach items="${poslist}" var="rotation">
			<tr>
				<%-- <td><input type="checkbox" value="${rotation.id}" name="checkID"></td> --%>
				<td>${rotation.rotUserName}</td>
				<td>${rotation.rotOrgName}</td>
				<td>${fns:getDicLabel(rotation.rotPos,"DIC_USER_POSITION", "")}</td>
				<td>${fns:getDicLabel(rotation.toPos,"DIC_USER_POSITION", "")}</td>
				<td>${rotation.rotOrgName}</td>
				<td>${rotation.toDate}</td>
				<td>${rotation.repUserName}</td>
				<td>${rotation.repOrgName}</td>
				<td>${fns:getDicLabel(rotation.repPos,"DIC_USER_POSITION", "")}</td>
				<td>${rotation.hanDate}</td>
				<td>${fns:getDicLabel(rotation.status,"DIC_POS_ROTATION_STATUS", "")}</td>

				<c:if test="${rotation.status == 4}">
					<td><a href="${ctx}/posrot/rotManage/info?id=${rotation.id}&status=${rotation.status}">接收</a></td>
				</c:if>
				<c:if test="${rotation.status == 5}">
					<td><a href="${ctx}/posrot/rotManage/info?id=${rotation.id}&status=${rotation.status}">交接</a></td>
				</c:if>

			</tr>
		</c:forEach>
	</table>


	<div id="psaa_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">接收轮岗计划</h3>
		</div>
		<div class="modal-body">
			<h5>确认接收当前轮岗计划？</h5>
			<div class="cen">
				<textarea id="opeOpinion" name="opeOpinion" rows="6" style="width: 500px;" placeholder="意见..."></textarea>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="okPass">接收</button>
			<button class="btn btn-danger" id="noPass">不接收</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>



	<script type="text/javascript">
		$("#selAllID").click(function() {
			if (this.checked) {
				$("input[name='checkID']").attr('checked', true)
			} else {
				$("input[name='checkID']").attr('checked', false)
			}
		});
	</script>

</body>
</html>