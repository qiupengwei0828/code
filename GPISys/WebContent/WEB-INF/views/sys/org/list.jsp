<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>机构管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g, "");
		var data = ${fns:toJson(list)}, rootId = "${not empty org.pOrgNo ? org.pOrgNo : '0'}";
		addRow("#treeTableList", tpl, data, rootId, true);
		$("#treeTable").treeTable({
			expandLevel : 5
		});
	});

	function addRow(list, tpl, data, pid, root) {
		for (var i = 0; i < data.length; i++) {
			var row = data[i];
			if ((${fns:jsGetVal('row.pOrgNo')}) == pid) {
				$(list).append(Mustache.render(tpl, {
					pid : (root ? 0 : pid),
					row : row
				}));
				addRow(list, tpl, data, row.orgNo);
			}
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/org/findAllTree?orgNo=${org.orgNo}">机构列表</a></li>
		<li><a href="${ctx}/sys/org/form">机构添加</a></li>
	</ul>


	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机构名称</th>
				<th>机构编码</th>
				<th>机构分类</th>
				<th>机构状态</th>
				<th>级别</th>
				<th>重证库位标识</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>

	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.orgNo}}" pId="{{row.pOrgNo}}">
			<td>{{row.orgName}}</td>
            <td>{{row.orgNo}}</td>
			<td>{{row.orgType}}</td>
			<td>{{row.status}}</td>
			<td>{{row.orgLevel}}</td>
			<td>{{row.bdFlag}}</td>
			<td>{{row.dispNo}}</td>
			<td>
				<a href="${ctx}/sys/org/form?orgNo={{row.orgNo}}">修改</a>
				<a href="${ctx}/sys/org/delete?orgNo={{row.orgNo}}" onclick="return confirmx('要删除该机构项吗？', this.href)">删除</a>
				<a href="${ctx}/sys/org/form?pOrgNo={{row.orgNo}}">添加下级机构</a> 
			</td>
		</tr>
	</script>
</body>
</html>