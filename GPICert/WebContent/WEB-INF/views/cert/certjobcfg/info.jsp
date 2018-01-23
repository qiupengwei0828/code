<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位持证查询详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

</head>
<body>

	<table class="table">
		<c:forEach items="${list}" var="certJobCfg" begin="0" end="0">
			<tr>
				<td><h4>岗位：${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}持证信息</h4></td>
				<td><input class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" style="float: right;" /></td>
			</tr>
		</c:forEach>
	</table>


	<c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.holdReq == '1'}">
			<c:set var="hold1" scope="page" value="1" />
		</c:if>
	</c:forEach>

	<c:if test="${hold1 == 1}">
		<h6 style="margin: 10px 15px;">要求持有：</h6>
		<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
			<tr>
				<th style="width: 20%">岗位</th>
				<th style="width: 20%">证书编码</th>
				<th style="width: 30%">证书名称</th>
				<th style="width: 30%">备注</th>
			</tr>
			<c:forEach items="${list}" var="certJobCfg">
				<c:if test="${certJobCfg.holdReq == '1'}">
					<tr>
						<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
						<td>${certJobCfg.certCode}</td>
						<td>${certJobCfg.certName}</td>
						<td>${certJobCfg.remark}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>


	<c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.holdReq == '2'}">
			<c:set var="hold2" scope="page" value="2" />
		</c:if>
	</c:forEach>
	<c:if test="${hold2 == 2}">
		<h6 style="margin: 10px 15px;">自选持有：</h6>
		<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
			<tr>
				<th style="width: 20%">岗位</th>
				<th style="width: 20%">证书编码</th>
				<th style="width: 30%">证书名称</th>
				<th style="width: 30%">备注</th>
			</tr>
			<c:forEach items="${list}" var="certJobCfg">
				<c:if test="${certJobCfg.holdReq == '2'}">
					<tr>
						<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
						<td>${certJobCfg.certCode}</td>
						<td>${certJobCfg.certName}</td>
						<td>${certJobCfg.remark}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>


	<c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.holdReq == '3'}">
			<c:set var="hold3" scope="page" value="3" />
		</c:if>
	</c:forEach>
	<c:if test="${hold3 == 3}">
		<h6 style="margin: 10px 15px;">鼓励持有：</h6>
		<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
			<tr>
				<th style="width: 20%">岗位</th>
				<th style="width: 20%">证书编码</th>
				<th style="width: 30%">证书名称</th>
				<th style="width: 30%">备注</th>
			</tr>
			<c:forEach items="${list}" var="certJobCfg">
				<c:if test="${certJobCfg.holdReq == '3'}">
					<tr>
						<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
						<td>${certJobCfg.certCode}</td>
						<td>${certJobCfg.certName}</td>
						<td>${certJobCfg.remark}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>