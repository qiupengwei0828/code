<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的强修/顶岗</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
.cen {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>

<script type="text/javascript">
	
</script>
</head>
<body>

	<table id="contentTable" class="table table-hover table-bordered table-condensed">
		<tr>
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
		<c:forEach items="${list}" var="recess">
			<tr>
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

				<c:if test="${recess.status == 4}">
					<td><a href="${ctx}/posrot/recManage/info?id=${recess.id}&status=${recess.status}">接收</a></td>
				</c:if>
				<c:if test="${recess.status == 5}">
					<td><a href="${ctx}/posrot/recManage/info?id=${recess.id}&status=${recess.status}">交接</a></td>
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