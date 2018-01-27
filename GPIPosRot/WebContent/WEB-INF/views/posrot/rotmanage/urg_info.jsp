<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>督办详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
.cen {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(":text").attr("readonly", "readonly");
		$("#btnSubmit").click(function() {
			$('#yes_title').modal('show');
			$("#run_yes").click(function() {
				test();
			});
		});
	});
	function test() {
		$.ajax({
			data : {
				'id' : $("#ids").val(),
				'status' : $("#statusValue").val(),
				'opeOpinion' : $("#opeOpinion").val(),
			},
			type : "POST",
			dataType : 'json',
			url : "${ctx}/posrot/rotManage/urg",
			success : function(data) {
				window.location.href = "${ctx}/posrot/rotManage/listall";
			}
		});
	}
</script>
</head>
<body>

	<input id="statusValue" type="hidden" value="${rotation.status}">
	<input id="ids" type="hidden" value="${rotation.id}">

	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span12">

				<div class="cen">
					<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
				</div>

				<div class="row-fluid">
					<div class="span6">
						<form class="form-horizontal">

							<div class="control-group">
								<label class="control-label">轮岗人员信息</label>
								<div class="controls"></div>
							</div>

							<div class="control-group">
								<label class="control-label">机构:</label>
								<div class="controls">
									<input name="rotOrgName" type="text" value="${rotation.rotOrgName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">轮岗员工姓名:</label>
								<div class="controls">
									<input name="rotUserName" type="text" value="${rotation.rotUserName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">岗位:</label>
								<div class="controls">
									<%-- <input name="rotPosName" type="text" value="${rotation.rotPosName}"> --%>
									<input name="rotPosName" type="text" value="${fns:getDicLabel(rotation.rotPos,'DIC_USER_POSITION', '')}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">轮换机构:</label>
								<div class="controls">
									<input name="toOrgName" type="text" value="${rotation.toOrgName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">轮换岗位:</label>
								<div class="controls">
									<input name="toPosName" type="text" value="${fns:getDicLabel(rotation.toPos,'DIC_USER_POSITION', '')}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">到岗日期:</label>
								<div class="controls">
									<input name="toDate" type="text" value="${rotation.toDate}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">意见:</label>
								<div class="controls">
									<textarea id="opeOpinion" name="opeOpinion" rows="2" style="width: 90%;"></textarea>
								</div>
							</div>

						</form>
					</div>

					<div class="span6">
						<form class="form-horizontal">

							<div class="control-group">
								<label class="control-label">顶岗人员信息</label>
								<div class="controls"></div>
							</div>

							<div class="control-group">
								<label class="control-label">机构:</label>
								<div class="controls">
									<input name="repOrgName" type="text" value="${rotation.repOrgName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">顶岗员工姓名:</label>
								<div class="controls">
									<input name="repUserName" type="text" value="${rotation.repUserName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">岗位:</label>
								<div class="controls">
									<input name="repPosName" type="text" value="${fns:getDicLabel(rotation.repPos,'DIC_USER_POSITION', '')}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">顶岗机构:</label>
								<div class="controls">
									<input name="toOrgName" type="text" value="${rotation.toOrgName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">顶岗岗位:</label>
								<div class="controls">
									<input name="rotPosName" type="text" value="${fns:getDicLabel(rotation.rotPos,'DIC_USER_POSITION', '')}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">交接日期:</label>
								<div class="controls">
									<input name="creDate" type="text" value="${rotation.creDate}">
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>

		</div>

		<div class="cen">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="督办" /> <input class="btn" type="button" value="返回" onclick="history.go(-1)" />
		</div>

		<h5>流程列表</h5>

		<table class="table table-striped table-bordered table-condensed">
			<tr>
				<th>业务名称</th>
				<th>环节名称</th>
				<th>操作意见</th>
				<th>操作内容</th>
				<th>操作结果</th>
				<th>操作人姓名</th>
				<th>操作时间</th>
			</tr>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.proName}</td>
					<td>${list.actionName}</td>
					<td>${list.opeOpinion}</td>
					<td>${list.opeContent}</td>
					<td>${list.opeResult}</td>
					<td>${list.opeUserName}</td>
					<td>${list.opeDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>




	<!-- titleManage -->
	<div id="yes_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<div>
				<h3 id="myModalLabel">确认督办</h3>
			</div>
		</div>
		<div class="modal-body">
			<div>
				<h5>确认督办轮岗计划？</h5>
			</div>
		</div>
		<div class="modal-footer">
			<button id="run_yes" class="btn btn-primary">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>



</body>
</html>