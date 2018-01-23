<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
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
	var userIdValues;
	function detail(planId){
		top.$.jBox.open("iframe:${ctx}/bd/bdplan/detail?planId="+planId, "请领计划详情", $(top.document).width() - 300, $(top.document).height() - 100, {
			buttons : {
				
			},
			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	}
  </script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bd/bdplan/index">请领计划列表</a></li>
		<li><a href="${ctx}/bd/bdplan/add">请领申请</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="dic" action="${ctx}/bd/bdplan/index" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="bdPlan" action="${ctx}/bd/bdplan/index" method="post" class="breadcrumb form-search">		
		<label>请领机构：</label>
		<sys:treeselect id="orgNo" name="orgNo" value="${store.orgNo}" labelName="orgName" labelValue="${store.orgName}" title="机构" url="/bd/bdstore/treeData" cssStyle="width:150px;font-size:10px;" />
		<label>请领人员：</label>
		<form:input path="userId" htmlEscape="false" maxlength="50" style="width:120px" id="orgName" />
		<label>状态：</label>
		<form:select path="status" class="input-medium">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_PLAN_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>请领机构</th>
			<th>请领人员</th>
			<th>季度</th>
			<th>日期</th>
			<th>状态</th>
			<th>说明</th>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list}" var="bdPlan">
			<tr>
				<td>${bdPlan.orgName}</td>	
				<td>${bdPlan.userId}</td>			
				<td>${bdPlan.quarter}</td>
				<td>${bdPlan.planDate}</td>
			    <td>${fns:getDicLabel(bdPlan.status,"DIC_BD_PLAN_STATUS", "")}</td>
			    <td>${bdPlan.remark}</td>
				<td>
				<input
					id="showDetail" class="btn btn-primary" onclick="detail(${bdPlan.id});" type="button" value="详情">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <c:if test="${bdPlan.status==02}">
					<a href="${ctx}/bd/bdplan/check?id=${bdPlan.id}">审批</a>
			    </c:if>
			     <c:if test="${bdPlan.status==01}">
					<a href="${ctx}/bd/bdplan/check?id=${bdPlan.id}">提交</a>
			    </c:if>
			    </td>
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
