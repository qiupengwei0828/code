<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>凭证用量</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctx}/js/autocomplete/autocomplete.js" type="text/javascript"></script>
<link href="${ctx}/js/autocomplete/jquery.autocomplete.css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(function() {
	           var myDate = new Date();
				var date = new Date();
				var month = date.getMonth() + 1;

				var strDate = date.getDate();
				if (month >= 1 && month <= 9) {
					month = '0' + month;
				}
				if (strDate >= 0 && strDate <= 9) {
					strDate = '0' + strDate;
				}
				var currentdate = date.getFullYear()+ "-" + month+ "-" + strDate;
				$("#useDate").val(currentdate);
				$.ajax({
					type : "POST",
					url : "${ctx}/bd/common/findUser",
					success : function(data) {
						if (data != "" && data != null) {
							$("#orgNo").val(data.orgNo);
							document.getElementById("orgNo").setAttribute(
									"readonly", true);
							$("#orgName").val(data.orgName);
							document.getElementById("orgName").setAttribute(
									"readonly", true);
							$("#userId").val(data.userName);
							document.getElementById("userId").setAttribute(
									"readonly", true);
						}
					}
				});	
		$.ajax({
			url : "${ctx}/bd/bdstore/getConfirm",
			type : "GET",
			dataType : "json",
			success : function(data) {
				$("#certactName").focus();
				$("#certactName").autocomplete(data, {
					cacheLength : 1,
					minChars : 0,
					width : 220,
					max :40,
					autoFocus: false,
					formatItem : function(item) {
						return item.id + "--" + item.name;
					},
					formatResult : function(item) {
						return item.name;
					}
				}).result(
					function(event, item) {
						$("#certactCode")
								.val(item.id);
						document.getElementById(
								"certactCode")
								.setAttribute("readonly", true);
						$("#storeNum").val(item.num);
						document.getElementById(
								"storeNum")
								.setAttribute("readonly", true);
					});
			}
		});			
			});
	function queryStoreNum() {
		var orgNumber = $("#orgNo").val();
		var certactCode = $("#certactCode").val();
		$.ajax({
			type : "POST",
			async : false,
			dataType : 'json',
			url : "${ctx}/bd/bdstore/findNum?orgNo=" + orgNumber
					+ "&certactCode=" + certactCode,
			success : function(data) {
			  var nn =parseInt(data.num);
			  var mm =parseInt($("#useNum").val());
			  if(data.num!=null&&nn<mm){			      
			     document.getElementById("blok").style.display = "block"; 
			     $("#blok").val("库存不足，实际库存为："+data.num); 
			  } else if(data.num==null){
                 document.getElementById("blok").style.display = "block"; 
			     $("#blok").val("库存不足，实际库存为：0");
              }else{
			   document.getElementById("blok").style.display = "none"; 
			   }
			}
		});
	}
	function onclickSubmit() { 
	    var flag = true;
	    if($("#certactName").val()==""||$("#certactName").val()==null){	    
	      flag = false;
	      document.getElementById("certactName").style.borderColor = "red";
	    }
	    if($("#useType").val()==""){	    
	      flag = false;
	      document.getElementById("useType").style.borderColor = "red";
	    }
	    if($("#useDate").val()==""||$("#certactName").val()==null){	    
	      flag = false;
	      document.getElementById("useDate").style.borderColor = "red";
	    }
	    if($("#useNum").val()==""||$("#certactName").val()==null){	    
	      flag = false;
	      document.getElementById("useNum").style.borderColor = "red";
	    }
		$("#inputForm").attr("action", "${ctx}/bd/use/add");
		if(flag){
		  $("#inputForm").submit();
		}else{
		  confirm("请将信息填写完整！");
		}
	}
	function getConfirm() {
	$("#certactName").flushCache();
		var data = $("#certactType").val();
		$("#certactName").val("");
		$("#certactCode").val("");
		$("#storeNum").val("");
		$.ajax({
			url : "${ctx}/bd/bdstore/getConfirm?certactType=" + data,
			type : "GET",
			dataType : "json",
			success : function(data) {
				$("#certactName").focus();
				$("#certactName").autocomplete(data, {
					cacheLength : 1,
					minChars : 0,
					width : 220,
					max :40,
					autoFocus: false,
					formatItem : function(item) {
						return item.id + "--" + item.name;
					},
					formatResult : function(item) {
						return item.name;
					}
				}).result(
					function(event, item) {
						$("#certactCode")
								.val(item.id);
						document.getElementById(
								"certactCode")
								.setAttribute("readonly", true);
						$("#storeNum").val(item.num);
						document.getElementById(
								"storeNum")
								.setAttribute("readonly", true);
					});
			}
		});
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
.phone-input{ 
padding-right:20px; 
background:url("${ctxStatic}/images/3333.png") no-repeat scroll right center transparent; 
}
</style>



</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bd/use/list">使用列表</a></li>
		<li class="active"><a href="${ctx}/bd/use/form">用量登记</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bdUseDetail" action=""
		method="post" class="form-horizontal">
		
		<div class="control-group">
			<label class="control-label">机构名称:</label>
			<div class="controls">
				<form:input path="orgName" htmlEscape="false" maxlength="50"
					style="width:230px" id="orgName" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构代码:</label>
			<div class="controls">
				<form:input path="orgNo" htmlEscape="false" maxlength="50"
					style="width:150px" id="orgNo" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单证类型:</label>
			<div class="controls">
				<form:select path="certactType" class="input-medium" onchange="getConfirm()">
			     <form:option value="" label="全部" />
				<form:options items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
			   </form:select><span id="spanid" style="font-size:14px;font-family:宋体;color:red;">*1.优先选择单证类型 2.双击选择单证</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单证名称:</label>
			<div class="controls">
				<form:input path="certactName" htmlEscape="false" maxlength="80" class="phone-input" autocomplete="off"
					style="width:230px" id="certactName" /><span style="font-size:14px;font-family:宋体;color:red;">*</span>
				<form:input path="certactCode" htmlEscape="false" maxlength="50"
					style="width:150px" id="certactCode" type="hidden" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存量:</label>
			<div class="controls">
				<form:input path="storeNum" htmlEscape="false" maxlength="50"
					style="width:150px" id="storeNum" />
			</div>
		</div>
         <div class="control-group">
			<label class="control-label">使用类型:</label>
			<div class="controls">
				<form:select path="useType" class="input-medium">
					<form:options items="${fns:getDicList('DIC_USE_TYPE')}"
						itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select><span style="font-size:14px;font-family:宋体;color:red;" >*</span>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">使用量:</label>
			<div class="controls">
				<form:input path="useNum" htmlEscape="false" maxlength="50"
					style="width:200px" id="useNum" onblur="queryStoreNum()" /><span style="font-size:14px;font-family:宋体;color:red;" >*</span>
				<input  id="blok" name="blok"  style="border:0px;background-color:transparent;color:red;display:none" readonly="readonly">	
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">使用日期:</label>
			<div class="controls">				
					<input id="useDate" name="useDate"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:150px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>	
			<div class="control-group">
			<label class="control-label">操作人员:</label>
			<div class="controls">
				<form:input path="userId" htmlEscape="false" maxlength="50"
					style="width:150px" id="userId" />
			</div>
		</div>
		<br/>
        <font size="2">注：</font>
	    &nbsp;&nbsp;&nbsp;1.<font color=red>*</font>代表必填项</br>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.单证类型不能为全部，否则无法选择单证
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button"
				value="保 存" onclick="onclickSubmit()" /> <input id="btnCancel"
				class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>