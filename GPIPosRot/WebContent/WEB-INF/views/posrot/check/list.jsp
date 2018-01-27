<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轮岗员工列表</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<style type="text/css">
#duty {
	display: block;
	width: 150px;
	height: 24px;
	overflow: hidden;
	white-space: nowrap;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
</style>

<script type="text/javascript">
   
   
  $(document).ready(function() {
	$("#btnReset").click(function() {
		$("#posName").val("");
		$("#orgLevel").val("");
		$("form:first").submit();
	});
       
     });
  
	/*
	 * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	
	
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<!-- <li class="active"><a href="${ctx}/posrot/jobrotation/list">轮岗员工列表</a></li>-->
	</ul>
	<form:form id="searchForm" modelAttribute="staff" action="${ctx}/posrot/jobrotation/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<form:form modelAttribute="staff" action="${ctx}/posrot/jobrotation/list" method="post" class="breadcrumb form-search">
		<label>岗位名称：</label>
		<form:input path="posName" htmlEscape="false" maxlength="50" id="posName" style="width:150px;" />
		<label>机构等级：</label>
		<form:select id="orgLevel" path="orgLevel" class="input-medium" style="width:150px">
			<form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_ORG_LEVEL')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>状态：</label>
		<form:select path="status" class="input-medium" style="width:150px">
		    <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_COMM_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		  <input class="btn btn-primary" type="submit" value="查询" />
		  <input id="btnReset" class="btn btn-primary" type="reset" value="重置" />
	</form:form>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
		    <th>所属机构</th>
		    <th>岗位名称</th>
		    <th>人力资源编号</th>
			<th>员工姓名</th>
			<th>到岗时间</th>
			<th>轮岗期限</th>
			<th>到期天数</th>
		</tr>
		<c:forEach items="${page.list}" var="staff">
			<tr>
			    <td>${staff.orgName}</td>
			    <td>${staff.posName}</td>
			    <td>${staff.hrNo}</td>
				<td>${staff.userName}</td>
				<td>${staff.posDate}</td>
				<td>${staff.limitDate}</td>
				<!--超期预警 -->	
				<c:if test="${staff.limitDay<0}">
				  <td style="color:red;">${staff.limitDayDisp}</td>
				</c:if>
				<!--到期提醒 -->
			    <c:if test="${staff.limitDay>0&&staff.limitDay<15}">
				  <td style="color:blue">${staff.limitDayDisp}</td>
			    </c:if>
			    <!--正常情况-->
			    <c:if test="${staff.limitDay>15}">
			     <td style="color:#00CC00">${staff.limitDayDisp}</td>
			    </c:if> 
				<!--<td><a href="${ctx}/posrot/jobrotation/rotationplan?userId=${staff.userId}">轮岗计划</a></td>--> 
		 </c:forEach>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>