<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>凭证管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var id = $("#id").val();
		if (id != "") {
			$("#inputForm").attr("action", "${ctx}/bd/bdstore/update");
			/* $("#id").attr("disabled", "true"); */
		} else {
			$("#inputForm").attr("action", "${ctx}/bd/bdstore/add");
		}
		
		$("#btnSubmit").click(function() {
			$("#inputForm").submit();
		});
});
	function getConfirm(){
		  var data = $("#certactType").val();
		   var t = document.getElementById("certactCode");
		   t.options.length = 0;
		  $.ajax({		  
			url : "${ctx}/bd/certact/getConfirm?certactType=" + data,
			type : "GET",
			dataType : "json",
			success : function(data) { 
			t.add(new Option("全部","0"));                       
               for(var i=0;i<data.length;i++){           
                 t.add(new Option(data[i].name,data[i].id));
			}
			}
		});	
		}
 function setName(){
    var name = $("#certactCode option:selected").text(); 
    $("#certactName").val(name);
 }
</script>
<style type="text/css">
.span2 {
	padding-left: 20px;
	width: 100px;
}

.row {
	padding-bottom: 5px;
}
</style>



</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bd/bdstore/info">库存列表</a></li>
		<li class="active"><a href="${ctx}/bd/bdstore/form?id=${store.id}">库存${not empty
				store.id?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="store" action="" method="post" class="form-horizontal">
        <div class="row">
			<div class="span2">
				<label>所属机构</label>
			</div>
			<div class="span3">
			<sys:treeselect id="orgNo" name="orgNo" value="${store.orgNo}" labelName="orgName" labelValue="${store.orgName}" title="机构" url="/bd/bdstore/treeData" cssStyle="width:150px;font-size:10px;" />
			</div>
			<div class="span2">
				<label>库存类型</label>
			</div>
			<div class="span3">
				<form:select path="repType" class="input-medium">
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_REP_TYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
                <form:input path="id" htmlEscape="false" type="hidden" maxlength="50" style="width:150px" id="id" />
			</div>
		</div>

	  <div class="row">
			<div class="span2">
				<label>单证类型</label>
			</div>
			<div class="span3">
			<form:select path="certactType" class="input-medium" onchange="getConfirm()">
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
			   </div>
			   <div class="span2">
				<label>单证选择</label>
			</div>
			<div class="span3">
			<c:if test="${store.id==null}">			
                <form:select path="certactCode" class="input-medium" id="certactCode" onchange="setName()">		
                <form:option value="" label="全部" />		
			   </form:select>	
			   <form:input path="certactName" htmlEscape="false" maxlength="50" style="width:150px" id="certactName" type="hidden" />		   			  
			 </c:if>
			 <c:if test="${store.id!=null}">
			 <form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:150px" id="certactCode" type="hidden" />	
			 <form:input path="certactName" htmlEscape="false" maxlength="50" style="width:150px" id="certactName"  />	
			 </c:if>
			  </div>
			   </div>
		  <div class="row">
			<div class="span2">
				<label>库存数量</label>
			</div>
			<div class="span3">
				<form:input path="storeNum" htmlEscape="false" maxlength="50" style="width:150px" id="storeNum" />
			</div>
			


           
			<div class="span2">
				<label>凭证库</label>
			</div>
			<div class="span3">
			    <form:select path="certactStore" class="input-medium" >
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_CERTACT_STORE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
			</div>
			</div>
			 <div class="row">
			<div class="span2">
				<label>凭证分户</label>
			</div>
			<div class="span3">
			<form:select path="certactSplit" class="input-medium" >
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_CERTACT_STORE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
			</div>
			

           
			<div class="span2">
				<label>业务范围</label>
			</div>
			<div class="span3">
			<form:select path="businessScope" class="input-medium" >
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_SCOPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
			</div>
			</div>
			<div class="row">
			<div class="span2">
				<label>在库状态</label>
			</div>
			<div class="span3">
			<form:select path="status" class="input-medium">
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_STORE_STATUS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select>
			</div>
			
			
			
			<div class="span2">
				<label>备注</label>
			</div>
			<div class="span3">
				<form:input path="remark" htmlEscape="false" maxlength="50" style="width:150px" id="remark" />
			</div>
			</div>
			<br/>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> 
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>