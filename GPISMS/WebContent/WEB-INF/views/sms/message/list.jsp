<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待发送短信</title>

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

	});
</script>
<body>
	<form:form id="searchForm" modelAttribute="message" action="" method="post" class="breadcrumb form-search">

		<label>所属应用</label>
		<form:select path="appCode" class="input-medium" style="width:200px" id="appCode">
			<form:option value="" label="全部" />
			<form:options items="${applist}" itemLabel="appName" itemValue="appCode" htmlEscape="false" />
		</form:select>

		<label>操作时间：</label>
		<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>&nbsp;--&nbsp;&nbsp;&nbsp;</label>
		<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

		<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" />

		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />

		<!-- <input id="export" class="btn btn-primary" type="button" value="导出数据" /> -->

	</form:form>

	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>

	<script type="text/javascript">
		var appCode = "";
		var beginDate = "";
		var endsDate = "";

		$(function() {
			pageInit();
		});

		function pageInit() {
			var gridData = $("#jqGrid").jqGrid({
				url : "${ctx}/sms/message/msglist",
				datatype : "json",
				regional : 'cn',//本地化
				//viewrecords : true,
				colModel : [ {
					label : '所属系统',
					name : 'appCode',
					width : 200
				}, {
					label : '接收手机',
					name : 'mobile',//数据名
					width : 150
				}, {
					label : '短信内容',
					name : 'content',
					formatter : formatContent
				//width : 25
				}, {
					label : '信息类型',
					name : 'type',
					align : 'center'//文本显示位置
				//width : 25
				}, {
					label : '创建时间',
					name : 'crtDate',
					align : 'center'//文本显示位置
				//	}, {
				//	label : '是否审核',
				//	name : 'checkType',
				//	align : 'center'//文本显示位置
				}, {
					label : '状态',
					name : 'status',
					align : 'center'//文本显示位置
				//width : 60
				}, {
					label : '备注',
					name : 'remark',
				//width : 70
				}, {
					label : '操作',
					name : 'id',
					formatter : formatEdits,
					align : 'center'//文本显示位置
				//width : 70
				} ],
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
				height : $(top.document).height() - 300,
				//每页条数
				rowNum : 20,
				loadonce : true,
				//分页DIV的ID
				pager : "#jqGridPager"

			});

			//二级表头
			//	gridData.jqGrid('setGroupHeaders', {
			//	useColSpanStyle : true,
			//	groupHeaders : [ {
			//	startColumnName : 'gwzg',//开始合并的列的name
			//	numberOfColumns : 4,//合并多少行
			//	titleText : '证书分类'//合并行后的title
			//	} ]
			//	});

			function formatContent(cellValue, options, rowObject) {
				return cellValue.substring(0, 8) + "...";
			}

			function formatEdits(cellValue, options, rowObject) {
				return "<a href='" + "${ctx}/sms/message/info?id=" + cellValue + "'>" + "详情" + "</a>";
			}

		}

		$("#btnSubmit").click(function() {
			appCode = $("#appCode").val();
			beginDate = $("#beginDate").val();
			endDate = $("#endDate").val();
			//jQuery("#jqGrid").jqGrid("clearGridData");//清空整个jqGrid
			$("#jqGrid").jqGrid('setGridParam', {
				url : "${ctx}/sms/message/msglist",
				datatype : 'json',
				//type : "POST",
				//需要传的参数
				postData : {
					"appCode" : appCode,
					"beginDate" : beginDate.toString(),
					"endDate" : endDate.toString(),
				},
				page : 1
			//重新载入
			}).trigger('reloadGrid');
		});
	</script>

</body>
</html>