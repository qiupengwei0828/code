<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构计步排名</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<!-- jqGrid -->
<script src="${ctxStatic}/jqGrid/4.8/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctxStatic}/jqGrid/4.8/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jqGrid/4.8/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
<!-- jqGrid -->

</head>

<script type="text/javascript">
	$(document).ready(function() {

		$("#export").click(function() {
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			var orgNo = "${orgNo}"
			if (beginDate != "" && endDate != "") {
				window.location.href = "${ctx}/setp/export/avg_org_setp?beginDate=" + beginDate + "&endDate=" + endDate + "&orgNo=" + orgNo;
			} else {
				$('#error_title').modal('show');
			}
		});
	});
</script>

<body>

	<form:form id="sForm" modelAttribute="setpUser" action="" method="post" class="breadcrumb form-search">
		<label>开始日期：</label>
		<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

		<label>结束日期：</label>
		<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:150px" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

		<input id="orgNo" name="orgNo" type="hidden" value="${setpUser.orgNo}" />

		<input id="submitBtn" class="btn btn-primary" type="button" value="查询" />

		<input id="export" type="button" value="导出数据" class="btn btn-primary" style="margin: 0 5px;float: right">

	</form:form>


	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>

	<script type="text/javascript">
		var beginDate = "";
		var endDate = "";
		var orgNo = "${orgNo}";

		$(function() {
			pageInit();
		});

		function pageInit() {
			var gridData = $("#jqGrid").jqGrid({
				url : "${ctx}/setp/setpCount/get_avg_org_list?orgNo=" + orgNo,
				datatype : "json",
				regional : 'cn',//本地化
				colNames : [ '排名', '机构名称', '平均步数' ],
				colModel : [ {
					name : 'rownum',//数据列名
				//width : 70
				}, {
					name : 'orgName',
				//width : 90
				}, {
					name : 'num',
				//width : 25
				} ],

				//自动列宽   
				autowidth : true,
				//设置斑马色表格
				altRows : true,
				//显示序号
				rownumbers : false,
				//显示数据条数
				viewrecords : true,
				//表格宽度
				width : $("#sForm").width() - 50,
				//表格高度
				height : $(document).height() - 120,
				//每页条数
				rowNum : 20,
				loadonce : true,
				//分页DIV的ID
				pager : "#jqGridPager"

			});

		}

		$("#submitBtn").click(function() {
			beginDate = $("#beginDate").val();
			endDate = $("#endDate").val();
			//jQuery("#jqGrid").jqGrid("clearGridData");//清空整个jqGrid
			$("#jqGrid").jqGrid('setGridParam', {
				url : "${ctx}/setp/setpCount/get_avg_org_list",
				datatype : 'json',
				//type : "POST",
				//需要传的参数
				postData : {
					"beginDate" : beginDate,
					"endDate" : endDate,
					"orgNo" : orgNo,
				//"userName" : encodeURI(userName),//处理中文
				},
				page : 1
			//重新载入
			}).trigger('reloadGrid');
		});
	</script>

	<div id="error_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">未选择时间段</h3>
		</div>
		<div class="modal-body">
			<h3>请先选择时间段后再导出数据！</h3>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">确认</button>
		</div>
	</div>

</body>
</html>