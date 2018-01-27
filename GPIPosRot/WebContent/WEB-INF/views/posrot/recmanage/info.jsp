<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>强修计划详情</title>
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

		$("#noPass").click(function() {
			actionCode = "0";
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
				url : "${ctx}/posrot/recManage/pass",
				success : function(data) {
					var url_status = $("#status").val();
					if (url_status == "1") {
						window.location.href = "${ctx}/posrot/recManage/list_items?status=1";
					} else if (url_status == "2") {
						window.location.href = "${ctx}/posrot/recManage/check";
					} else if (url_status == "3") {
						window.location.href = "${ctx}/posrot/recManage/list_items?status=3";
					} else if (url_status == "4") {
						if (data.title != undefined) {
							alert(data.title);
						}
						window.location.href = "${ctx}/posrot/recManage/mypos_list";
					} else if (url_status == "5") {
						if (data.title != undefined) {
							alert(data.title);
						}
						window.location.href = "${ctx}/posrot/recManage/mypos_list";
					} else if (url_status == "6") {
						window.location.href = "${ctx}/posrot/recManage/list_items?status=6";
					} else if (url_status == "7") {
						window.location.href = "${ctx}/posrot/recManage/list_items?status=7";
					}
				}
			});
		});
	});
</script>
</head>
<body>

	<input id="id" name="id" value="${recess.id}" type="hidden">

	<input id="ids" name="id" value="${recess.id}" type="hidden">

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

							<c:if test="${status == 1}">
								<input name="opeOpinion" id="opeOpinion" type="hidden">
							</c:if>

							<c:if test="${status == 2}">
								<div class="cen">
									<div class="control-group">
										<label class="control-label">意见:</label>
										<div class="controls">
											<textarea id="opeOpinion" name="opeOpinion" rows="2" style="width: 90%;"></textarea>
										</div>
									</div>
								</div>
							</c:if>

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

					<!-- -------------------------------------------------------------- -->
					<c:if test="${status == 3}">
						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">强修计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%"> <input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>
							</form>
						</div>
					</c:if>

					<c:if test="${status == 4}">
						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">强修计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>
							</form>
						</div>
					</c:if>

					<c:if test="${status == 5}">

						<div class="span10">
							<form class="form-horizontal">

								<div class="control-group">
									<label class="control-label">强修计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>

								<div class="control-group">
									<label class="control-label">强修计划交接清单模板：</label>
									<div class="controls">
										<input id="jjqd_fileName" name="jjqd_fileName" type="text" value="${fns:getConfig('gpi.posrot.jjqd_templet_name')}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_JJQD()">
										<!--  -->
										<input class="btn btn-primary" type="button" value="上传清单" onclick="up_JJQD()">
									</div>
								</div>

							</form>
						</div>

					</c:if>

					<c:if test="${status == 6 || status == 7}">
						<div class="span10">
							<form class="form-horizontal">

								<div class="control-group">
									<label class="control-label">强修计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>

								<div class="control-group">
									<label class="control-label">强修计划交接清单模板：</label>
									<div class="controls">
										<input id="jjqd_fileName" name="jjqd_fileName" type="text" value="${fns:getConfig('gpi.posrot.jjqd_templet_name')}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_JJQD()">
										<!--  -->
										<c:if test="${status == 5}">
											<input class="btn btn-primary" type="button" value="上传清单" onclick="up_JJQD()">
										</c:if>
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
					<!-- -------------------------------------------------------------- -->

				</div>
			</div>

		</div>

		<div class="cen">
			<!-- 待提交 -->
			<c:if test="${status == 1}">
				<button class="btn btn-primary" id="yesPass">提交</button>
			</c:if>
			<!-- 待审批 -->
			<c:if test="${status == 2}">
				<button class="btn btn-primary" id="yesPass">通过</button>
				<button class="btn btn-danger" id="noPass">不通过</button>
			</c:if>
			<!-- 待通知 -->
			<c:if test="${status == 3}">
				<button class="btn btn-primary" id="yesPass">通知</button>
			</c:if>
			<!-- 待接收 -->
			<c:if test="${status == 4}">
				<button class="btn btn-primary" id="yesPass">接收</button>
			</c:if>
			<!-- 待交接 -->
			<c:if test="${status == 5}">
				<button class="btn btn-primary" id="yesPass">交接</button>
			</c:if>
			<!-- 待执行 -->
			<c:if test="${status == 6}">
				<button class="btn btn-primary" id="yesPass">确认执行</button>
			</c:if>
			<!-- 待交接 -->
			<c:if test="${status == 7}">
				<button class="btn btn-primary" id="yesPass">确认归档</button>
			</c:if>
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
			<c:if test="${status == 1}">
				<h3 id="myModalLabel">确认提交</h3>
			</c:if>
			<c:if test="${status == 2}">
				<h3 id="myModalLabel">确认审批</h3>
			</c:if>
			<c:if test="${status == 3}">
				<h3 id="myModalLabel">确认通知</h3>
			</c:if>
			<c:if test="${status == 4}">
				<h3 id="myModalLabel">确认接收</h3>
			</c:if>
			<c:if test="${status == 5}">
				<h3 id="myModalLabel">确认交接</h3>
			</c:if>
			<c:if test="${status == 6}">
				<h3 id="myModalLabel">确认执行</h3>
			</c:if>
			<c:if test="${status == 7}">
				<h3 id="myModalLabel">确认归档</h3>
			</c:if>
		</div>
		<div class="modal-body">
			<c:if test="${status == 1}">
				<h5>确认提交强修计划？</h5>
			</c:if>
			<c:if test="${status == 2}">
				<h5>确认审批强修计划？</h5>
			</c:if>
			<c:if test="${status == 3}">
				<h5>确认通知强修计划？</h5>
			</c:if>
			<c:if test="${status == 4}">
				<h5>确认接收强修计划？</h5>
			</c:if>
			<c:if test="${status == 5}">
				<h5>确认交接强修计划？</h5>
			</c:if>
			<c:if test="${status == 6}">
				<h5>确认强修计划已执行？</h5>
			</c:if>
			<c:if test="${status == 7}">
				<h5>确认归档强修计划？</h5>
			</c:if>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" id="btnSubmit">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


	<div id="up_jjqd_tool" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">上传交接清单</h3>
		</div>
		<div class="modal-body">
			<sys:up id="jjqdFile" name="jjqdFile" url="${ctx}/posrot/jjqd/up_JJQD" />
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


	<script type="text/javascript">
		//生成通知书
		(function() {
			$.ajax({
				data : {},
				type : "POST",
				dataType : 'json',
				url : "${ctx}/posrot/recManage/downPlan",
				success : function(data) {
					$("#notify").val(data);
				}
			})
		})()
		//下载通知书
		function down_e_notify() {
			var fileName = $("#notify").val();
			window.location.href = "${ctx}/posrot/recManage/download?fileName=" + fileName;
		}
		//下载交接清单模板
		function down_JJQD() {
			var fileName = $("#jjqd_fileName").val();
			window.location.href = "${ctx}/posrot/jjqd/down_JJQD?fileName=" + fileName;
		}
		//上传交接清单
		function up_JJQD() {
			$("#up_jjqd_tool").modal('show');
			var tabKey = $("#ids").val();
			$("#jjqdFileData").attr("value", tabKey);
			$("#jjqdFileType").attr("value", "rec");
			var temp = $("#jjqdFileData").val();
		}
		//查看交接清单
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