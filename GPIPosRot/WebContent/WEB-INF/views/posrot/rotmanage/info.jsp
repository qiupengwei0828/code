<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轮岗计划详情</title>
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

		var actionCode = "";

		$("#btnSubmit").click(function() {
			if ($("#statusValue").val() == "2") {
				actionCode = "1";//审批轮岗计划不通过
			} else if ($("#statusValue").val() == "4") {
				actionCode = "1";//同意接受轮岗计划
			} else if ($("#statusValue").val() == "6") {
				actionCode = "1";//已完成计划
			}
			$('#yes_title').modal('show');
			$('#run_yes').click(function() {
				test(actionCode);
				$('#yes_title').modal('hide')
			});

		});

		$("#btnNo").click(function() {
			if ($("#statusValue").val() == "2") {
				actionCode = "0";//审批轮岗计划不通过
			} else if ($("#statusValue").val() == "4") {
				actionCode = "0";//不同意接受轮岗计划
			} else if ($("#statusValue").val() == "6") {
				actionCode = "0";//未完成计划
			}
			$('#no_title').modal('show');
			$("#run_no").click(function() {
				var s = ${rotation.status};
				test(actionCode);
				$('#yes_title').modal('hide')
			});
		});
	});

	function test(actionCode) {
		$.ajax({
			data : {
				'id' : $("#ids").val(),
				'status' : $("#statusValue").val(),
				'opeOpinion' : $("#opeOpinion").val(),
				'actionCode' : actionCode
			},
			type : "POST",
			dataType : 'json',
			url : "${ctx}/posrot/rotManage/pass",
			success : function(data) {

				if (data.title != undefined) {
					alert(data.title);
				}

				if ($("#statusValue").val() == "2") {
					window.location.href = "${ctx}/posrot/rotManage/check";
				} else if ($("#statusValue").val() == "4") {
					window.location.href = "${ctx}/posrot/rotManage/mypos_list";
				} else if ($("#statusValue").val() == "5") {
					window.location.href = "${ctx}/posrot/rotManage/mypos_list";
				} else {
					window.location.href = "${ctx}/posrot/rotManage/listall";
				}
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
					<c:if test="${sta == 1}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
					<c:if test="${sta == 2}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
					<c:if test="${sta == 3}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
					<c:if test="${sta == 4}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
					<c:if test="${sta == 5}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
					<c:if test="${sta == 6}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
					<c:if test="${sta == 7}">
						<p style="font-size: 20px;">${rotation.rotUserName}轮岗计划</p>
					</c:if>
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



							<c:if test="${sta == 2}">
								<div class="cen">
									<div class="control-group">
										<label class="control-label">意见:</label>
										<div class="controls">
											<textarea id="opeOpinion" name="opeOpinion" rows="2" style="width: 90%;"></textarea>
										</div>
									</div>
								</div>
							</c:if>

							<c:if test="${sta == 4}">
								<div class="cen">
									<div class="control-group">
										<label class="control-label">意见:</label>
										<div class="controls">
											<textarea id="opeOpinion" name="opeOpinion" rows="2" style="width: 90%;"></textarea>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${sta == 6}">
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

					<c:if test="${rotation.status == 3 || rotation.status == 4}">
						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">轮岗计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%"> <input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>
							</form>
						</div>
					</c:if>


					<c:if test="${rotation.status == 5}">

						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">轮岗计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%"> <input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>
							</form>
						</div>

						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">轮岗计划交接清单模板：</label>
									<div class="controls">
										<input id="jjqd_fileName" name="jjqd_fileName" type="text" value="${fns:getConfig('gpi.posrot.jjqd_templet_name')}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_JJQD()">
										<c:if test="${rotation.status == 5}">
											<input class="btn btn-primary" type="button" value="上传清单" onclick="up_JJQD()">
										</c:if>
									</div>
								</div>
							</form>
						</div>

					</c:if>

					<c:if test="${rotation.status == 6 || rotation.status == 7}">

						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">轮岗计划通知书：</label>
									<div class="controls">
										<input id="notify" name="notify" type="text" value="${notify}" style="width: 70%"> <input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_e_notify()">
									</div>
								</div>
							</form>
						</div>


						<div class="span10">
							<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">轮岗计划交接清单模板：</label>
									<div class="controls">
										<input id="jjqd_fileName" name="jjqd_fileName" type="text" value="${fns:getConfig('gpi.posrot.jjqd_templet_name')}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="下&nbsp;&nbsp;载" onclick="down_JJQD()">
										<c:if test="${rotation.status == 5}">
											<input class="btn btn-primary" type="button" value="上传清单" onclick="up_JJQD()">
										</c:if>
									</div>
								</div>
							</form>
						</div>

						<div class="span10">
							<form class="form-horizontal">

								<div class="control-group">
									<label class="control-label">轮岗计划交接清单：</label>
									<div class="controls">
										<input id="done_atta1" name="done_atta1" type="text" value="${done_atta1}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="查&nbsp;&nbsp;看" onclick="down_done_atta1()">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<input id="done_atta2" name="done_atta2" type="text" value="${done_atta2}" style="width: 70%">
										<!--  -->
										<input class="btn btn-primary" type="button" value="查&nbsp;&nbsp;看" onclick="down_done_atta2()">
									</div>
								</div>

							</form>
						</div>
					</c:if>

				</div>

			</div>

		</div>

		<div class="cen">

			<c:if test="${sta == 1}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="提交" />
			</c:if>

			<c:if test="${sta == 2}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="通过" />
				<input id="btnNo" class="btn btn-danger" type="button" value="不通过" />
			</c:if>

			<c:if test="${sta == 3}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="通知" />
			</c:if>

			<c:if test="${sta == 4}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="接收" />
			</c:if>

			<c:if test="${sta == 5}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="交接" />
			</c:if>

			<c:if test="${sta == 6}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="完成" />
				<input id="btnNo" class="btn btn-danger" type="button" value="未完成" />
			</c:if>

			<c:if test="${sta == 7}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="归档" />
			</c:if>

			<input class="btn" type="button" value="返回" onclick="history.go(-1)" />

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
				<c:if test="${sta == 1}">
					<h3 id="myModalLabel">确认提交</h3>
				</c:if>
				<c:if test="${sta == 2}">
					<h3 id="myModalLabel">确认通过</h3>
				</c:if>
				<c:if test="${sta == 3}">
					<h3 id="myModalLabel">确认通知</h3>
				</c:if>

				<c:if test="${sta == 4}">
					<h3 id="myModalLabel">确认接收</h3>
				</c:if>
				<c:if test="${sta == 5}">
					<h3 id="myModalLabel">确认交接</h3>
				</c:if>

				<c:if test="${sta == 6}">
					<h3 id="myModalLabel">确认完成</h3>
				</c:if>
				<c:if test="${sta == 7}">
					<h3 id="myModalLabel">确认归档</h3>
				</c:if>
			</div>
		</div>
		<div class="modal-body">
			<div>
				<c:if test="${sta == 1}">
					<h5>确认提交轮岗计划？</h5>
				</c:if>
				<c:if test="${sta == 2}">
					<h5>确认通过轮岗计划？</h5>
				</c:if>
				<c:if test="${sta == 3}">
					<h5>确认通知轮岗计划？</h5>
				</c:if>

				<c:if test="${sta == 4}">
					<h5>确认接收轮岗计划？</h5>
				</c:if>
				<c:if test="${sta == 5}">
					<h5>确认交接轮岗计划？</h5>
				</c:if>

				<c:if test="${sta == 6}">
					<h5>确认轮岗计划已完成？</h5>
				</c:if>

				<c:if test="${sta == 7}">
					<h5>确认归档轮岗计划？</h5>
				</c:if>
			</div>
		</div>
		<div class="modal-footer">
			<button id="run_yes" class="btn btn-primary">确认</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


	<div id="no_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<div>
				<c:if test="${sta == 2}">
					<h3 id="myModalLabel">不通过</h3>
				</c:if>
				<c:if test="${sta == 6}">
					<h3 id="myModalLabel">未完成</h3>
				</c:if>
			</div>
		</div>
		<div class="modal-body">
			<div>
				<c:if test="${sta == 2}">
					<h5>不通过轮岗计划？</h5>
				</c:if>
				<c:if test="${sta == 6}">
					<h5>当前轮岗计划未完成？</h5>
				</c:if>
			</div>
		</div>
		<div class="modal-footer">
			<button id="run_no" class="btn btn-primary">确认</button>
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
				url : "${ctx}/posrot/rotManage/downPlan",
				success : function(data) {
					$("#notify").val(data);
				}
			})
		})()

		//下载通知书
		function down_e_notify() {
			var fileName = $("#notify").val();
			window.location.href = "${ctx}/posrot/rotManage/download?fileName=" + fileName;
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
			$("#jjqdFileType").attr("value", "rot");
			var temp = $("#jjqdFileData").val();
		}
		//查看交接清单
		function down_done_atta1() {
			var fileName = $("#done_atta1").val();
			var action = "rot";
			var type = "done";
			if (fileName != "") {
				window.location.href = "${ctx}/posrot/jjqd/down_notify?fileName=" + fileName + "&action=" + action + "&type=" + type;
			}
		}

		function down_done_atta2() {
			var fileName = $("#done_atta2").val();
			var action = "rot";
			var type = "done";
			if (fileName != "") {
				window.location.href = "${ctx}/posrot/jjqd/down_notify?fileName=" + fileName + "&action=" + action + "&type=" + type;
			}
		}
	</script>


	<script type="text/javascript">
		//下载清单
		/* $("#down_JJQD").click(function() {
			var fileName = "${fns:getConfig('gpi.posrot.jjqd_templet_name')}";
			window.location.href = "${ctx}/posrot/jjqd/down_JJQD?fileName=" + fileName;
		}); */

		//上传清单
		/* $("#up_JJQD").click(function() {
			$("#up_jjqd_tool").modal('show');
			var tabKey = $("#ids").val();
			$("#jjqdFileData").attr("value", tabKey);
			$("#jjqdFileType").attr("value", "rot");
			var temp = $("#jjqdFileData").val();
		}); */
	</script>

</body>
</html>