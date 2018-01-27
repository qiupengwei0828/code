<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>强修计划详情(所有)</title>
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
	});
</script>
</head>
<body>


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

					<c:if test="${recess.status == 8}">

						<div class="span10">
							<form class="form-horizontal">

								<div class="control-group">
									<label class="control-label">强修计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%"> <input class="btn btn-primary" type="button" value="查&nbsp;&nbsp;看" onclick="down_notify()">
									</div>
								</div>

								<div class="control-group">
									<label class="control-label">强修计划交接清单：</label>
									<div class="controls">
										<input id="done_atta1" name="done_atta1" type="text" value="${done_atta1}" style="width: 70%"> <input class="btn btn-primary" type="button" value="查&nbsp;&nbsp;看"
											onclick="down_done_atta1()">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<input id="done_atta2" name="done_atta2" type="text" value="${done_atta2}" style="width: 70%"> <input class="btn btn-primary" type="button" value="查&nbsp;&nbsp;看"
											onclick="down_done_atta2()">
									</div>
								</div>


							</form>
						</div>

					</c:if>

				</div>
			</div>

		</div>

		<div class="cen">
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

	<script type="text/javascript">
		function down_notify() {
			var action = "rec";
			var fileName = $("#notify").val();
			var type = "notify";
			if (fileName != "") {
				window.location.href = "${ctx}/posrot/jjqd/down_notify?fileName=" + fileName + "&action=" + action + "&type=" + type;
			}
		}

		function down_done_atta1() {
			var fileName = $("#done_atta1").val();
			var action = "rec";
			var type = "done";
			if (fileName != "") {
				window.location.href = "${ctx}/posrot/jjqd/down_notify?fileName=" + fileName + "&action=" + action + "&type=" + type;
			}
		}

		function down_done_atta2() {
			var fileName = $("#done_atta2").val();
			var action = "rec";
			var type = "done";
			if (fileName != "") {
				window.location.href = "${ctx}/posrot/jjqd/down_notify?fileName=" + fileName + "&action=" + action + "&type=" + type;
			}
		}
	</script>

</body>
</html>