<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从业人员强制休假花名册</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<!-- jqGrid -->
<script src="${ctxStatic}/jqGrid/4.8/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${ctxStatic}/jqGrid/4.8/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/jqGrid/4.8/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
<!-- jqGrid -->

</head>
<script type="text/javascript">
	//重置选择内容
	$(document).ready(function() {
		$("#btnReset").click(function() {

			$("#searchForm").submit();

		});

		$("#export").click(function() {
			window.location.href = "${ctx}/posrot/reporttable/recess_all_export";
		});
	});
</script>
<body>
	<form id="searchForm" action="" method="post" class="breadcrumb form-search">

		<label>所在单位</label>
		<sys:treeselect id="orgNo" name="orgNo" value="" labelName="" labelValue="" title="机构" url="/sys/org/treeData" cssStyle="width:150px;font-size:10px;" />

		<label>休假人姓名</label>
		<!--  -->
		<input id="recUserName" name="recUserName" type="text" style="width:150px">
		<!--  -->
		<label>代职人姓名</label>
		<!--  -->
		<input id="repUserName" name="repUserName" type="text" style="width:150px">
		<!--  -->
		<!-- <label>原岗位</label> -->
		<!--  -->
		<%-- <select path="pos" class="input-medium" style="width:220px" id="pos">
			<option value="">全部</option>
			<c:forEach items="${poslist}" var="list">
				<option value="${list.posCode }">${list.posName}</option>
			</c:forEach>
		</select> --%>
		<!--  -->
		<input id="btnSubmit" class="btn btn-primary" type="button" value="查询">
		<!--  -->
		<input id="btnReset" class="btn btn-primary" type="button" value="重置">
		<!--  -->
		<input id="export" class="btn btn-primary" type="button" value="导出数据">

	</form>

	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>

	<script type="text/javascript">
		var orgNo = "";
		var recUserName = "";
		var repUserName = "";

		$(function() {
			pageInit();
		});

		function pageInit() {
			var gridData = $("#jqGrid").jqGrid({
				url : "${ctx}/posrot/reporttable/recesslist_all_list",
				datatype : "json",
				colModel : [ {
					label : '所在单位',
					//name : 'recOrgName'
					name : 'orgName2nd'
				//formatter : formatEdits,
				}, {
					label : '姓名',
					name : 'recUserName',
					align : 'center'
				}, {
					label : '岗位',
					name : 'recOrgName'
				}, {
					label : '姓名',
					name : 'repUserName',
					align : 'center'
				}, {
					label : '岗位',
					name : 'repOrgName'
				}, {
					label : '离岗日期',
					name : 'recBeginDate',
					align : 'center'
				}, {
					label : '到岗日期',
					name : 'hanDate',
					align : 'center'
				}, {
					label : '休假天数',
					name : 'vacationDay',
					align : 'center'
				}, {
					label : '备注',
					name : 'remark'
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
				pager : "#jqGridPager"

			});

			jQuery("#jqGrid").jqGrid('setGroupHeaders', {
				useColSpanStyle : false,
				groupHeaders : [ {
					//开始合并的列的name
					startColumnName : 'recUserName',
					//合并多少行
					numberOfColumns : 2,
					//合并行后的title
					titleText : '休假人员',
				}, {
					startColumnName : 'repUserName',
					numberOfColumns : 2,
					titleText : '代职人员'
				} ]
			});
		}

		$("#btnSubmit").click(function() {
			orgNo = $("#orgNoId").val();
			recUserName = $("#recUserName").val();
			repUserName = $("#repUserName").val();

			alert(recUserName)
			alert(repUserName)

			//jQuery("#jqGrid").jqGrid("clearGridData");//清空整个jqGrid

			$("#jqGrid").jqGrid('setGridParam', {
				url : "${ctx}/posrot/reporttable/recesslist_all_list",
				datatype : 'json',
				//type : "POST",
				//需要传的参数
				postData : {
					"orgNo" : orgNo,
					"recUserName" : encodeURI(recUserName),
					"repUserName" : encodeURI(repUserName)
				},
				page : 1
			//重新载入
			}).trigger('reloadGrid');
		});
	</script>
</body>
</html>