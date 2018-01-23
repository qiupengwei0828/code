<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位持证率</title>

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
			$("#pos").val("");
			$("#userName").val("");
			$("#userClass").val("");
			$("form:first").submit();
		});

		$("#export").click(function() {
			window.location.href = "${ctx}/cert/reporttable/export";
		});
	});
</script>
<body>
	<form:form id="searchForm" modelAttribute="holdInfo" action="" method="post" class="breadcrumb form-search">

		<label>机构</label>
		<sys:treeselect id="orgNo" name="orgNo" value="" labelName="" labelValue="" title="机构" url="/sys/org/treeData" cssStyle="width:130px;font-size:10px;" />

		<label>岗位</label>
		<form:select path="pos" class="input-medium" style="width:150px" id="pos">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_USER_POSITION')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<label>员工姓名</label>
		<form:input path="userName" htmlEscape="false" maxlength="50" style="width:150px" id="userName" />

		<label>用工分类</label>
		<form:select path="userClass" class="input-medium" style="width:150px" id="userClass">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_USER_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>

		<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" />
		<input id="btnReset" class="btn btn-primary" type="button" value="重置" />
		<input id="export" class="btn btn-primary" type="button" value="导出数据" />

	</form:form>

	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>

	<script type="text/javascript">
		var orgNo = "";
		var pos = "";
		var userName = "";
		var userClass = "";

		$(function() {
			pageInit();
		});

		function pageInit() {
			var gridData = $("#jqGrid").jqGrid({
				url : "${ctx}/cert/reporttable/findUserlist",
				datatype : "json",
				regional : 'cn',//本地化
				colNames : [ '机构', '部门', '姓名', '性别', '出生年月', '用工类别', '岗位', '岗位资格证书', '从业资格证书', '职业资格证书', '技能鉴定证书' ],
				colModel : [ {
					name : 'orgName2nd',//数据名
				//width : 70
				}, {
					name : 'orgName3rd',//数据名
				//width : 90
				}, {
					name : 'userName',
				//width : 25
				}, {
					name : 'sex',
				//width : 25
				}, {
					name : 'birthday',
				//width : 40
				}, {
					name : 'userClass',
				//width : 35
				}, {
					name : 'pos',
				//width : 60
				}, {
					name : 'gwzg',
				//width : 70
				}, {
					name : 'cyzg',
				//width : 70
				}, {
					name : 'zyzg',
				//width : 70
				}, {
					name : 'jnjd',
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
			gridData.jqGrid('setGroupHeaders', {
				useColSpanStyle : true,
				groupHeaders : [ {
					startColumnName : 'gwzg',//开始合并的列的name
					numberOfColumns : 4,//合并多少行
					titleText : '证书分类'//合并行后的title
				} ]
			});

		}

		$("#btnSubmit").click(function() {
			orgNo = $("#orgNoId").val();
			pos = $("#pos").val();
			userName = $("#userName").val();
			userClass = $("#userClass").val();
			//jQuery("#jqGrid").jqGrid("clearGridData");//清空整个jqGrid
			$("#jqGrid").jqGrid('setGridParam', {
				url : "${ctx}/cert/reporttable/findUserlist",
				datatype : 'json',
				//type : "POST",
				//需要传的参数
				postData : {
					"orgNo" : orgNo,
					"pos" : pos,
					"userName" : encodeURI(userName),
					"userClass" : userClass,
				},
				page : 1
			//重新载入
			}).trigger('reloadGrid');
		});
	</script>

</body>
</html>