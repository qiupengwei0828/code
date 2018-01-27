<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
	<title>菜单管理</title>
	<%@include file="/WEB-INF/views/include/head.jsp"%>
	
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			
			var data = ${fns:toJson(list)}, rootId = "${not empty menu.menuId ? menu.menuId : '0'}";
			
			addRow("#treeTableList", tpl, data, rootId, true);
			
			$("#treeTable").treeTable({expandLevel : 2});
		});
		
		function addRow(list, tpl, data, pid, root){
			//alert(pid);
			for (var i=0; i<data.length; i++){
				var row = data[i];
				//alert(row.menuId+'  ----  '+pid);
				if (row.pMenuId == pid){					
					$(list).append(Mustache.render(tpl, {pid: (root?0:pid), row: row}));
					addRow(list, tpl, data, row.menuId);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/index">菜单列表</a></li>
		<li><a href="${ctx}/sys/menu/form">菜单添加</a></li>
	</ul>
	<sys:message content="${message}"/>
	<!-- 
	 <form:form id="searchForm" modelAttribute="menu" action="${ctx}/sys/menu/index" method="post" class="breadcrumb form-search">
		<label>类型：</label>
		<form:select id="appCode" path="appCode" class="input-medium">
			<form:option value="" label="全部"/>
			<form:options items="${appCodeList}" itemLabel="appName" itemValue="appCode" htmlEscape="false"/>
		</form:select>&nbsp;&nbsp;
	    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>	
	 -->
	
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>菜单名称</th><th>URL</th><th>顺序</th><th>提示信息</th><th>所属系统</th><th>操作</th></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.menuId}}" pId="{{row.pMenuId}}">
            <td><i class="{{row.icon}}"></i><a href="${ctx}/sys/menu/form?menuId={{row.menuId}}">{{row.menuName}}</a></td>
			<td>{{row.actUrl}}</td>
			<td>{{row.dispNo}}</td>
			<td>{{row.tip}}</td>
			<td>{{row.appCode}}</td>
			<td>
				<a href="${ctx}/sys/menu/form?menuId={{row.menuId}}">修改</a>
				<a href="${ctx}/sys/menu/delete?menuId={{row.menuId}}" onclick="return confirmx('要删除该机构及所有子菜单项吗？', this.href)">删除</a>

			</td>
		</tr>
	</script>
</body>
</html>