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
	function resetvalue(){
	  $("#orgNoId").val("");
	  $("#orgNoName").val("");
	  $("#planOrgnoId").val("");
	  $("#planOrgnoName").val("");
	  $("#status").val("");
      document.getElementById("status").selectedIndex=0;
	  $("#planDate").val("");	
	}
  </script>
<body>
		<form:form id="searchForm" modelAttribute="store" action="${ctx}/bd/bdplanlog/log" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="orgNo" value="${store.orgNo}">
		<input type="hidden" name="planOrgno" value="${store.planOrgno}">
		<input type="hidden" name="planDate" value="${store.planDate}">
		<input type="hidden" name="status" value="${store.status}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="bdPlan" action="${ctx}/bd/bdplanlog/log" method="post" class="breadcrumb form-search">		
		<label>分行名称：</label>
		<sys:treeselect id="orgNo" name="orgNo" value="${store.orgNo}" labelName="orgName" labelValue="${store.orgName}" title="机构" url="/bd/bdstore/treeData" cssStyle="width:150px;font-size:10px;" />
		<label>请领机构：</label>
		<sys:treeselect id="planOrgno" name="planOrgno" value="${store.planOrgno}" labelName="planOrgname" labelValue="${store.planOrgname}" title="机构" url="/bd/bdstore/treeDataType" cssStyle="width:150px;font-size:10px;" />
		<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：</label>
		<form:select path="status" class="input-medium">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_PLAN_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label >日期:</label>			
					<form:input path="planDate" id="planDate" name="planDate"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="resetValue" class="btn btn-primary" type="button" value="重置" onclick="resetvalue()" />
	</form:form>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>		
		    <th>请领单编号</th>
			<th>分行名称</th>
			<th>请领机构代码</th>
			<th>请领机构名称</th>
			<th>季度</th>
			<th>日期</th>
			<th>状态</th>
			<th>请领人员</th>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list}" var="bdPlan">
			<tr>
			    <td>${bdPlan.planNumber}</td>
				<td>${bdPlan.orgName}</td>	
				<td>${bdPlan.planOrgno}</td>
				<td>${bdPlan.planOrgname}</td>			
				<td>${bdPlan.quarter}</td>
				<td>${bdPlan.planDate}</td>
			    <td>${fns:getDicLabel(bdPlan.status,"DIC_BD_PLAN_STATUS", "")}</td>
			    <td>${bdPlan.userId}</td>
				<td>				
			    <a href="${ctx}/bd/bdplan/detail?id=${bdPlan.id}">详情</a>

			    </td>
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
