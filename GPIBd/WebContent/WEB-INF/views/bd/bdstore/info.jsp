<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存查询</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctx}/js/autocomplete/autocomplete.js" type="text/javascript"></script>
<link href="${ctx}/js/autocomplete/jquery.autocomplete.css" rel="stylesheet" />
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
		
		function exportData(){
		    var url="${ctx}/bd/bdstore/export?certactName="+$("#certactName").val()+"&certactCode="
		    +$("#certactCode").val()+"&repType="+$("#repType").val()+"&certactType="+$("#certactType").val()+"&storeNum="
		    +$("#storeNum").val()+"&storeNum="+$("#storeNum").val()+"&certactStore="+$("#certactStore").val()+"&businessScope="
		    +$("#businessScope").val()+"&orgNo="+$("#orgNo").val();
		    window.location.href=url;
		}
		function importUse(){
	   top.$.jBox.open("iframe:${ctx}/bd/bdstore/importUse", "导入使用情况", $(top.document).width() - 200, $(top.document).height() - 100, {
			buttons : {
				
			},
			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	}
	//下载模板
    function downModel(){
       var url="${ctx}/sys/importData/down?file=model.xls";
 	   url=encodeURI(encodeURI(url));
 	   window.location.href=url;
    }
    function resetvalue(){
	  $("#certactName").val("");
	  $("#certactCode").val("");
	  $("#storeNum").val("");
	  $("#storenumber").val("");
	  $("#repType").val("");
      document.getElementById("repType").selectedIndex=0;
      $("#certactType").val("");
      document.getElementById("certactType").selectedIndex=0;
      $("#certactStore").val("");
      document.getElementById("certactStore").selectedIndex=0;
      $("#businessScope").val("");
      document.getElementById("businessScope").selectedIndex=0;
	  window.location.href=window.location.href;
	}
  </script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bd/bdstore/info">库存列表</a></li>
		<li><a href="${ctx}/bd/bdstore/form">库存单个添加</a></li>
	</ul>
		<form:form id="searchForm" modelAttribute="store" action="${ctx}/bd/bdstore/info" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="certactName" value="${store.certactName}">
		<input type="hidden" name="certactCode" value="${store.certactCode}">
		<input type="hidden" name="repType" value="${store.repType}">
		<input type="hidden" name="certactType" value="${store.certactType}">
		<input type="hidden" name="storeNum" value="${store.storeNum}">
		<input type="hidden" name="storenumber" value="${store.storenumber}">
		<input type="hidden" name="certactStore" value="${store.certactStore}">
		<input type="hidden" name="businessScope" value="${store.businessScope}">
		<input type="hidden" id="orgNo" name="orgNo" value="${store.orgNo}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="store" action="${ctx}/bd/bdstore/info" method="post" class="breadcrumb form-search">				
		<label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:150px;align:center" id="certactName" />
		<label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:150px;align:center" id="certactCode" />
		<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;库存类型：</label>
		<form:select path="repType" class="input-medium" id="repType" style="align:center">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_REP_TYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select><br/>
		<label>单证类型：</label>
		<form:select path="certactType" class="input-medium" style="align:center">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>库存数量：</label>
		<form:input path="storeNum" htmlEscape="false" maxlength="50" style="width:120px;align:center" id="storeNum" />--
		<form:input path="storenumber" htmlEscape="false" maxlength="50" style="width:120px;align:center" id="storenumber" />
		<br/>
		<label>&nbsp;&nbsp;&nbsp;凭证库：</label>
		<form:select path="certactStore" class="input-medium" style="align:center">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_CERTACT_STORE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>业务范围：</label>
		<form:select path="businessScope" class="input-medium" style="align:center">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_BD_SCOPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />
		<input id="export" style="float:right" class="btn btn-primary" type="button" value="导出" onclick="javascript:exportData();" />
	    <input id="import" style="float:right" class="btn btn-primary" type="button" value="批量导入" onclick="importUse();" />&nbsp;&nbsp;
        <input id="down" style="float:right" class="btn btn-primary" type="button" value="下载模板" onclick="downModel();" />		
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<th>机构代码</th>
			<th>机构名称</th>
			<th>库存类型</th>
			<th>单证类型</th>
			<th>单证代码</th>
			<th>单证名称</th>
			<th>库存数量</th>
			<th>凭证库</th>
			<th>凭证分户</th>
			<th>业务范围</th>
			<th>在库状态</th>
			<th>备注</th>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.list}" var="store">
			<tr>
				<td>${store.orgNo}</td>	
				<td>${store.orgName}</td>				
				<td>${fns:getDicLabel(store.repType,"DIC_BD_REP_TYPE", "")}</td>
				<td>${fns:getDicLabel(store.certactType,"DIC_BD_CERTACTTYPE", "")}</td>
				<td>${store.certactCode}</td>
				<td>${store.certactName}</td>
				<td>${store.storeNum}</td>
				<td>${fns:getDicLabel(store.certactStore,"DIC_BD_CERTACT_STORE", "")}</td>
				<td>${store.certactSplit}</td>
			    <td>${fns:getDicLabel(store.businessScope,"DIC_BD_SCOPE", "")}</td>
			    <td>${fns:getDicLabel(store.status,"DIC_BD_STORE_STATUS", "")}</td>
			    <td>${store.remark}</td>
				<td><a
					href="${ctx}/bd/bdstore/form?id=${store.id}">修改</a>
					<a href="${ctx}/bd/bdstore/delete?id=${store.id}&orgNo=${store.orgNo}" onclick="return confirmx('确认要删除该库存信息吗？', this.href)">删除</a>
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
