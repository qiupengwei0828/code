<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证书管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cert/holdinfo/index">证书列表</a></li>
		<li><a href="${ctx}/cert/holdinfo/form">证书添加</a></li>
	</ul>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th>证书编码</th>
			<th>证书名称</th>
			<th>资格分类</th>
			<th>行业分类</th>
			<th>证书编号</th>
			<th>发证日期</th>
			<th>有效期</th>
			<th>发证机构</th>
			<th>等级</th>
			<th>分类</th>
			<th>证书状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="holdInfo">
			<tr>
				<td>${holdInfo.certCode}</td>
				<td>${holdInfo.certName}</td>
				<td>${fns:getDicLabel(holdInfo.admClass,"DIC_CERT_CLASS", "")}</td>
				<td>${fns:getDicLabel(holdInfo.industry,"DIC_CERT_INDUSTRY", "")}</td>
				<td>${holdInfo.certNo}</td>
				<td>${holdInfo.issueDate}</td>
				<td>${holdInfo.usefulLife}</td>
				<td>${holdInfo.issueOrg}</td>
				<td>${holdInfo.certLevel}</td>
				<td>${fns:getDicLabel(holdInfo.industry,"DIC_CERT_INDUSTRY", "")}</td>
				<td>${fns:getDicLabel(holdInfo.status,"DIC_CERT_STATUS", "")}</td>
				
				<td>
					<c:if test="${holdInfo.status != '1'}">
						<a href="${ctx}/cert/holdinfo/form?id=${holdInfo.id}">修改</a>
						<a href="${ctx}/cert/holdinfo/delete?id=${holdInfo.id}" onclick="return confirmx('确认要删除该证书吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${holdInfo.status == '1'}">
						<a href="${ctx}/cert/holdinfo/form?id=${holdInfo.id}">查看</a>
					</c:if>
				</td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>