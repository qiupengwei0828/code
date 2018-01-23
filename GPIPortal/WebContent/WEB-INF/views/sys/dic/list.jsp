<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	/*
	 * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function insertDic() {
		if (parent.typeCode != undefined) {
			var url = "${ctx}/sys/dic/form?typeCode=" + parent.typeCode;
			window.location.href = url;
		} else {
			$("#nullDicTypeId").modal("show");
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>字典列表</a></li>
		<li>
			<%-- <a href="${ctx}/sys/dic/form">字典添加</a> --%> <a onclick="insertDic()">字典添加</a>
		</li>
	</ul>

	<form:form id="searchForm" modelAttribute="dic" action="${ctx}/sys/dic/diclist" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th style="display:none;">字典ID</th>
			<th>类型名称</th>
			<th>类型编码</th>
			<th>参数名称</th>
			<th>参数值</th>
			<th>备注</th>
			<th>状态</th>
			<th style="display:none;">创建人</th>
			<th style="display:none;">创建时间</th>
			<th>操作</th>
		</tr>

		<tbody>
			<c:forEach items="${page.list}" var="dic">
				<tr>
					<td style="display:none;">${dic.id}</td>
					<td>${dic.typeName}</td>
					<td>${dic.typeCode}</td>
					<td>${dic.pName}</td>
					<td>${dic.pValue}</td>
					<td>${dic.remark}</td>
					<td>${dic.status}</td>
					<td style="display:none;">${dic.crtUser}</td>
					<td style="display:none;">${dic.crtTime}</td>
					<td><a href="${ctx}/sys/dic/form?id=${dic.id}">修改</a> <a href="${ctx}/sys/dic/delete?id=${dic.id}" onclick="return confirmx('确认要删除该字典吗？', this.href)">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>


	<div id="nullDicTypeId" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">错误提示</h3>
		</div>
		<div class="modal-body">
			<p>
			<h4>未选择字典类型</h4>
			</br>
			<h5>请先选择字典类型后重试！</h5>
			</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


</body>
</html>