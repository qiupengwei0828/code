<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>数据1</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctxStatic}/echarts/2.2.7/build/dist/echarts-all.js" type="text/javascript"></script>
</head>

<body>
	<div id="data1" style="width: 100%;height:100%;">
		<p></p>
		<table class="table table-striped table-bordered table-condensed">
			<tr>
				<td style="width: 50%;">系统名称</td>
				<td style="width: 50%;">发送量</td>
			</tr>
			<c:forEach items="${applist}" var="list">
				<tr>
					<td style="width: 50%;">${list.appName}</td>
					<td style="width: 50%;">${list.msgCount}</td>
				</tr>
			</c:forEach>
			<tr>
				<td style="width: 50%;">总计</td>
				<td style="width: 50%;">${msgCountNum}</td>
			</tr>
		</table>

		<p></p>

		<table class="table table-striped table-bordered table-condensed">
			<tr>
				<td style="width: 50%;">发送失败</td>
				<td style="width: 50%;">${fail}</td>
			</tr>
		</table>

	</div>


</body>

</html>