<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<!-- jqGrid -->
<script src="${ctxStatic}/jqGrid/4.8/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctxStatic}/jqGrid/4.8/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jqGrid/4.8/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
<!-- jqGrid -->

</head>

<body>


	<form id="searchForm" action="${ctx}/ormc/lxqk/list" method="post" class="breadcrumb form-search">
		<label>信贷员ID</label>
		<!--  -->
		<input id="creditUserID" name="creditUserID" type="text" value="${creditUserID}" style="width: 145px;" />
		<!--  -->
		<label>贷款种类名称</label>
		<!--  -->
		<input id="loanTypeName" name="loanTypeName" type="text" value="${loanTypeName}" style="width: 145px;" />
		<!--  -->
		<label>转出账号</label>
		<!--  -->
		<input id="transferAcc" name="transferAcc" type="text" value="${transferAcc}" style="width: 145px;" />
		<!--  -->
		<label>贷款起/止日期</label>
		<!--  -->
		<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width: 100px;" />
		<!--  -->
		<label>---</label>
		<!--  -->
		<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width: 100px;" />

		<!--  -->
		<input id="btnFind" class="btn btn-primary" type="button" value="查询" />

		<!--  -->
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
	</form>

	<table id="jqGrid"></table>

	<div id="jqGridPager"></div>

	<script>
		var dates = ${jsonDate};
		$(document).ready(function() {

			function formatEdits(cellValue, options, rowObject) {
				return "<a href='" + "${ctx}/ormc/lxqk/info?id=" + cellValue + "'>" + "详情" + "</a>";
			}

			$("#jqGrid").jqGrid({
				datatype : "local",
				data : dates,
				height : 1000,
				colModel : [ {
					label : '贷款机构名称',
					name : '机构名称'
				}, {
					label : '所属二分',
					name : '所属二分'
				}, {
					label : '贷款起止日期',
					name : '贷款起期',
				}, {
					label : '贷款截止日期',
					name : '贷款止期',
				}, {
					label : '贷款金额',
					name : '贷款金额',
				}, {
					label : '贷款余额',
					name : '贷款余额',
				}, {
					label : '贷款种类名称',
					name : '贷款种类名称',
				}, {
					label : '信贷员ID',
					name : '信贷员ID',
				}, {
					label : '交易柜员号',
					name : '交易柜员号',
				}, {
					label : '摘要',
					name : '摘要',
				}, {
					label : '转入姓名',
					name : '转入姓名',
				}, {
					label : '转出姓名',
					name : '转出姓名',
				}, {
					label : '转出账号',
					name : '转出账号',
				}, {
					label : '交易金额',
					name : '交易金额',
				}, {
					label : '交易类型',
					name : '交易类型',
				//	}, {
				//	label : '操作',
				//	name : '贷款ID',
				//	formatter : formatEdits,
				//	align : 'center'//文本显示位置
				} ],
				//本地化
				regional : 'cn',
				//自动列宽   
				autowidth : true,
				//设置斑马色表格
				altRows : true,
				//显示序号
				rownumbers : true,
				//显示数据条数
				viewrecords : true,
				//表格宽度
				width : $(top.document).width() - 210,
				//表格高度
				height : $(top.document).height() - 280,
				//每页条数
				rowNum : 20,
				loadonce : true,
				//分页DIV的ID
				pager : "#jqGridPager",
				viewrecords : true
			// 显示工具栏上的当前页、数据范围和总记录
			//caption : "Load jqGrid through Javascript Array"

			});

		});
	</script>
	<script type="text/javascript">
		$("#btnFind").click(function() {
			$("#searchForm").submit();
		});
		$("#btnReset").click(function() {
			$("#creditUserID").val("");
			$("#loanTypeName").val("");
			$("#transferAcc").val("");
			$("#beginDate").val("");
			$("#endDate").val("");

			$("#searchForm").submit();
		});
	</script>
</body>
</html>