<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>使用列表</title>
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
	function importUse(){
	   top.$.jBox.open("iframe:${ctx}/bd/use/importUse", "导入使用情况", $(top.document).width() - 700, $(top.document).height() - 300, {
			buttons : {
				
			},
			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	}
	//下载模板
    function downModel(){
       var url="${ctx}/bd/use/down";
 	   url=encodeURI(encodeURI(url));
 	   window.location.href=url;
    }
    function resetvalue(){
	  $("#certactCode").val("");
	  $("#certactName").val("");
	  $("#useType").val("");
      document.getElementById("useType").selectedIndex=0;
	  window.location.href=window.location.href;
	}
  </script>
<body>

	<form:form id="searchForm" modelAttribute="dic" action="${ctx}/bd/use/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="bdUseDetail" action="${ctx}/bd/use/list" method="post" class="breadcrumb form-search">
	   <label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:120px" id="certactCode" />
	    <label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:120px" id="certactName" />
		<label>使用类型：</label>
		<form:select path="useType" class="input-medium">
		     <form:option value="" label="全部" />
			<form:options items="${fns:getDicList('DIC_USE_TYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
		</form:select>
		<label>使用日期：</label>
		<input id="useDate" name="useDate"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px;align:center" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />--
	    <input id="useDate" name="useDate"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px;align:center" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" type="reset" value="重置" onclick="resetvalue()" />
	    <input id="import" style="float:right" class="btn btn-primary" type="button" value="批量导入" onclick="importUse()" />&nbsp;&nbsp;
        <input id="down" style="float:right" class="btn btn-primary" type="button" value="下载模板" onclick="downModel()" />
		
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
		    <th>机构号</th>
		    <th>机构名称</th>
			<th>单证代码</th>
			<th>单证名称</th>
			<th>使用类型</th>
			<th>用量</th>
			<th>使用日期</th>
			<th>操作人员</th>
		</tr>
		<c:forEach items="${page.list}" var="bdUseDetail">
			<tr>
			    <td>${bdUseDetail.orgNo}</td>
			    <td>${bdUseDetail.orgName}</td>
				<td>${bdUseDetail.certactCode}</td>
				<td>${bdUseDetail.certactName}</td>
			    <td>${fns:getDicLabel(bdUseDetail.useType,"DIC_USE_TYPE", "")}</td>
			    <td>${bdUseDetail.useNum}</td>
			    <td>${bdUseDetail.useDate}</td>
			    <td>${bdUseDetail.userId}</td>			   
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>

