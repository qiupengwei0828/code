<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>督办计划详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<style type="text/css">
.cen {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>
<script type="text/javascript">
	var id = $("#id").val();
	var status = $("#status").val();
	var actionCode = "";
	var opeOpinion = "";

	$(document).ready(function() {
		$(":text").attr("readonly", "readonly");

		$("#yesPass").click(function() {
			actionCode = "1";
			$('#psaa_title').modal('show');
		});

		$("#btnSubmit").click(function() {
			$.ajax({
				data : {
					'id' : $("#id").val(),
					'status' : $("#status").val(),
					'opeOpinion' : $("#opeOpinion").val(),
					'actionCode' : actionCode
				},
				type : "POST",
				dataType : "json",
				url : "${ctx}/posrot/recManage/urg",
				success : function(data) {
					var url_status = $("#status").val();
					if (url_status == "4") {
						window.location.href = "${ctx}/posrot/recManage/list_items?status=4";
					} else if (url_status == "5") {
						window.location.href = "${ctx}/posrot/recManage/list_items?status=5";
					}
				}
			});
		});
	});
</script>
</head>
<body>

	<input id="id" name="id" value="${recess.id}" type="hidden">

	<input id="status" name="status" value="${recess.status}" type="hidden">

	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span12">

				<div class="cen">
					<p style="font-size: 20px;">${recess.recUserName}强修计划</p>
				</div>

				<div class="row-fluid">
					<div class="span6">
						<form class="form-horizontal">

							<div class="control-group">
								<label class="control-label">强修人员信息</label>
								<div class="controls"></div>
							</div>

							<div class="control-group">
								<label class="control-label">姓名:</label>
								<div class="controls">
									<input name="recUserName" type="text" value="${recess.recUserName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">所在机构:</label>
								<div class="controls">
									<input name="recOrgName" type="text" value="${recess.recOrgName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">所在岗位:</label>
								<div class="controls">
									<input name="recPosName" type="text" value="${recess.recPosName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">强修开始时间:</label>
								<div class="controls">
									<input name="recBeginDate" type="text" value="${recess.recBeginDate}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">强修结束时间:</label>
								<div class="controls">
									<input name="recEndDate" type="text" value="${recess.recEndDate}">
								</div>
							</div>

							<div class="cen">
								<div class="control-group">
									<label class="control-label">意见:</label>
									<div class="controls">
										<textarea id="opeOpinion" name="opeOpinion" rows="2" style="width: 80%;"></textarea>
									</div>
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
								<label class="control-label">姓名:</label>
								<div class="controls">
									<input name="repUserName" type="text" value="${recess.repUserName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">所在机构:</label>
								<div class="controls">
									<input name="repOrgName" type="text" value="${recess.repOrgName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">岗位:</label>
								<div class="controls">
									<input name="repPosName" type="text" value="${recess.repPosName}">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">交接日期:</label>
								<div class="controls">
									<input name="hanDate" type="text" value="${recess.hanDate}">
								</div>
							</div>

						</form>

					</div>
				</div>
			</div>

		</div>

		<div class="cen">
			<button class="btn btn-primary" id="yesPass">督办</button>
			<input class="btn btn-primary" type="button" value="返  回" onclick="history.go(-1)" />
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

	<div id="psaa_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">确认督办</h3>
		</div>
		<div class="modal-body">
			<h5>确认督办强修计划？</h5>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="btnSubmit">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</body>
</html>