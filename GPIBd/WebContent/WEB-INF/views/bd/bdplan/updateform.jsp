<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctx}/js/autocomplete/autocomplete.js"
	type="text/javascript"></script>
<link href="${ctx}/js/autocomplete/jquery.autocomplete.css"
	rel="stylesheet" />
<script type="text/javascript">
	
	$(document).ready(function() {
	$.ajax({
			url:"${ctx}/bd/bdstore/treeDataType",
			type : "GET",
			dataType : "json",
			success : function(data) {
				$("#planOrgname").focus();
				$("#planOrgname").autocomplete(data, {
					cacheLength : 1,
					minChars : 0,
					width : 210,
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
						$("#planOrgno")
								.val(item.id);
					   var myDate = new Date();
					  var year=myDate.getYear(); 
					  var year = year < 2000 ? year + 1900 : year
				      var yy = year.toString().substr(2, 2); 
					  var month = myDate.getMonth() + 1;     
					  var day = myDate.getDate();      
					  var hour = myDate.getHours();
                      var minte = myDate.getMinutes(); 
                      var second =  myDate.getSeconds();     
					   var clock = yy;     
				        if(month < 10){
				            clock += "0"; 
				            }     
				        clock += month;       
				        if(day < 10){
				            clock += "0"; 
				            }          
				        clock += day;
				        if(hour < 10){
				            clock += "0"; 
				            }
				        clock+=	hour;			            
				        if(minte < 10){
				          clock += "0"; 
				           }
				          clock+=	minte;  
				         if(second < 10){
				          clock += "0"; 
				         }
				        clock+=	second;
					  $("#planNumber").val(item.id+clock);
					});
			}
		});
	for(var f=1;f<6;f++){
	  if($("#increases" + f + "").val()>0.2){
	   document.getElementById("increases" + f + "").style.color = "red";
	  }
	}
	var id=$("#id").val();
	  $.ajax({
              type:"POST",
              url:"${ctx}/bd/common/findUser",
              success: function(data){
                if(data!=""&&data!=null){
                   $("#orgNo").val(data.orgNo);
                   $("#orgName").val(data.orgName);
                   document.getElementById("orgName").setAttribute("readonly",true);
                   $("#userId").val(data.userName);
                   document.getElementById("userId").setAttribute("readonly",true);
                }
              }
          });
          var myDate = new Date();
		var year=myDate.getFullYear(); 
		  var month = myDate.getMonth() + 1;     
		  var day = myDate.getDate();      
		   var clock = year+"-";     
	        if(month < 10){
	            clock += "0"; 
	            }     
	        clock += month+"-";       
	        if(day < 10){
	            clock += "0"; 
	            }          
	        clock += day;
		$("#planDate").val(clock);
		document.getElementById("planDate")
							.setAttribute("readonly", true);
	});
function onclickbtnsub1(){
		var flag=1;
		for(var k=1;k<6;k++){
		var str = $("#increases" + k + "").val();
		var incres = parseInt(str.substring(0,str.indexOf(".")+1));
		  if(incres>20||str.indexOf("大于")==0){
		     flag=0;
		     break;
		  }
		}
		   if(flag==0){
			var r = confirm('有请领单证已超限，确定要继续吗？');
			if (r == true) {										
				$.ajax({
					cache: true,
					type: "POST",
					url:  "${ctx}/bd/bdplan/saveform?remark=" + $("#remark").val(),
					data:$('#inputFormsub').serialize(),
					async: false
				});
				for( var i=1;i<6;i++){
				if($("#certactName"+i+"").val()!=""&&$("#certactName"+i+"").val()!=null){
				   $("#inputFormDetail"+i+"").attr(
						"action",
						"${ctx}/bd/bdplan/addDetailForm?planId="
								+ $("#id")
										.val());
				   $("#inputFormDetail"+i+"").submit();
				  }
				}
			}
			}else{				
				$.ajax({
					cache: true,
					type: "POST",
					url:  "${ctx}/bd/bdplan/saveform?remark=" + $("#remark").val(),
					data:$('#inputFormsub').serialize(),
					async: false
				}); 
				for( var i=1;i<6;i++){
				if($("#certactName"+i+"").val()!=""&&$("#certactName"+i+"").val()!=null){
				   $("#inputFormDetail"+i+"").attr(
						"action",
						"${ctx}/bd/bdplan/addDetailForm?planId="
								+ $("#id")
										.val());
				   $("#inputFormDetail"+i+"").submit();
				  }
				}
			}
		}
		function  onclickbtnSubmit1(){
				$.ajax({
					cache: true,
					type: "POST",
					url:  "${ctx}/bd/bdplan/form?remark=" + $("#remark").val(),
					data:$('#inputFormsub').serialize(),
					async: false
				}); 				
				for( var i=1;i<6;i++){
				if($("#certactName"+i+"").val()!=""&&$("#certactName"+i+"").val()!=null){
				   $("#inputFormDetail"+i+"").attr(
						"action",
						"${ctx}/bd/bdplan/addDetailForm?planId="
								+ $("#id")
										.val());
				   $("#inputFormDetail"+i+"").submit();
				  }
				}
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
		<li><a href="${ctx}/bd/bdplan/index">请领计划列表</a></li>
		<li class="active"><a href="${ctx}/bd/bdplan/add">请领申请</a></li>
	</ul>
	<br />
	
	<form:form id="inputFormsub" modelAttribute="bdPlan" action="${ctx}/bd/bdplan/saveform"
		method="post" class="form-horizontal">
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>请领单编号：</label>
			</div>
			<div class="span3">
				<form:input path="planNumber" htmlEscape="false" maxlength="50"
					style="width:200px" id="planNumber"  />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>分行名称：</label>
			</div>
			<div class="span3">
				<form:input path="orgNo" htmlEscape="false" maxlength="50"
					style="width:200px" id="orgNo" type="hidden" />
				<form:input path="orgName" htmlEscape="false" maxlength="50"
					style="width:200px" id="orgName" />
				<form:input path="id" htmlEscape="false" maxlength="50"
					style="width:200px" id="id" type="hidden" />
			</div>			
		</div>
		
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>请领机构名称：</label>
			</div>
			<div class="span3">
			<form:input path="planOrgname" htmlEscape="false" maxlength="50"
					style="width:200px" id="planOrgname" class="phone-input" />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>请领机构编码：</label>
			</div>
			<div class="span3">
				<form:input path="planOrgno" htmlEscape="false" maxlength="50"
					style="width:200px" id="planOrgno" />
			</div>			
		</div>
		


		<div class="row">
		<div class="span1"></div>
			<div class="span2">
				<label>季度：</label>
			</div>
			<div class="span3">
				<form:select path="quarter" class="input-medium" id="quarter" style="width:215px" >
					<form:option value="${list.get(1)}" label="${list.get(1)}" />
					<c:forEach items="${list}" var="item">
						<form:option value="${item}">${item}</form:option>
					</c:forEach>
				</form:select>
				<form:input path="quarter" id="quarter" type="hidden" />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>日期：</label>
			</div>
			<div class="span3">
				<form:input path="planDate" htmlEscape="false" maxlength="50"
					style="width:200px" id="planDate" />
			</div>
		</div>
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>请领人员：</label>
			</div>
			<div class="span3">
				<form:input path="userId" htmlEscape="false" maxlength="50"
					style="width:200px" id="userId" />
			</div>
			</div>				
	</form:form>
	
	<div class="form-actions">
		<table id="each1"  class="table table-striped table-bordered table-condensed">
			<tr>
				<td>凭证类型</td>
				<td>单证名称</td>
				<td>单证代码</td>
				<td>库存量</td>
				<td>请领量</td>
				<td>请领增幅</td>
			</tr>
			<form:form id="inputFormDetail1" modelAttribute="bdPlanDetail0" action=""
		method="post" class="form-horizontal"> 			
			<tr>
				<td><form:select path="certactType" class="input-medium" id="certactType1"
					onchange="getConfirm(1)">
						<form:option value="0">请选择：</form:option>
						<c:forEach var="obj"
							items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" varStatus="i">
							<form:option value="${obj.pValue }">${obj.pName}</form:option>
						</c:forEach>
						</form:select></td>
				<td><form:input path="certactName" maxlength="50" style="width:150px" id="certactName1" />
					<span class='Validform_checktip'></span></td>
				<td><form:input  path="certactCode" maxlength="50" style="width:150px" id="certactCode1" />
				</td>
				<td><form:input path="storeNum" maxlength="50" style="width:150px" id="storeNum1" />
				</td>
				<td><form:input  path="planNum" maxlength="50" style="width:150px" id="planNum1"
					onblur="roleCodeajax(1)" /></td>
				<td><form:input  path="increases" maxlength="50" style="width:150px" id="increases1" />
				</td>
				
			</tr>
			</form:form>
			<form:form id="inputFormDetail2" modelAttribute="bdPlanDetail1" action=""
		method="post" class="form-horizontal"> 			
			<tr>
				<td><form:select path="certactType" class="input-medium" id="certactType2"
					onchange="getConfirm(2)">
						<form:option value="0">请选择：</form:option>
						<c:forEach var="obj"
							items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" varStatus="i">
							<form:option value="${obj.pValue }">${obj.pName}</form:option>
						</c:forEach>
						</form:select></td>
				<td><form:input path="certactName" maxlength="50" style="width:150px" id="certactName2" />
					<span class='Validform_checktip'></span></td>
				<td><form:input  path="certactCode" maxlength="50" style="width:150px" id="certactCode2" />
				</td>
				<td><form:input path="storeNum" maxlength="50" style="width:150px" id="storeNum2" />
				</td>
				<td><form:input  path="planNum" maxlength="50" style="width:150px" id="planNum2"
					onblur="roleCodeajax(2)" /></td>
				<td><form:input  path="increases" maxlength="50" style="width:150px" id="increases2" />
				</td>
				
			</tr>
			</form:form>
			<form:form id="inputFormDetail3" modelAttribute="bdPlanDetail2" action=""
		method="post" class="form-horizontal"> 			
			<tr>
				<td><form:select path="certactType" class="input-medium" id="certactType3"
					onchange="getConfirm(3)">
						<form:option value="0">请选择：</form:option>
						<c:forEach var="obj"
							items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" varStatus="i">
							<form:option value="${obj.pValue }">${obj.pName}</form:option>
						</c:forEach>
						</form:select></td>
				<td><form:input path="certactName" maxlength="50" style="width:150px" id="certactName3" />
					<span class='Validform_checktip'></span></td>
				<td><form:input  path="certactCode" maxlength="50" style="width:150px" id="certactCode3" />
				</td>
				<td><form:input path="storeNum" maxlength="50" style="width:150px" id="storeNum3" />
				</td>
				<td><form:input  path="planNum" maxlength="50" style="width:150px" id="planNum3"
					onblur="roleCodeajax(3)" /></td>
				<td><form:input  path="increases" maxlength="50" style="width:150px" id="increases3" />
				</td>
				
			</tr>
			</form:form>
			<form:form id="inputFormDetail4" modelAttribute="bdPlanDetail3" action=""
		method="post" class="form-horizontal"> 			
			<tr>
				<td><form:select path="certactType" class="input-medium" id="certactType4"
					onchange="getConfirm(4)">
						<form:option value="0">请选择：</form:option>
						<c:forEach var="obj"
							items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" varStatus="i">
							<form:option value="${obj.pValue }">${obj.pName}</form:option>
						</c:forEach>
						</form:select></td>
				<td><form:input path="certactName" maxlength="50" style="width:150px" id="certactName4" />
					<span class='Validform_checktip'></span></td>
				<td><form:input  path="certactCode" maxlength="50" style="width:150px" id="certactCode4" />
				</td>
				<td><form:input path="storeNum" maxlength="50" style="width:150px" id="storeNum4" />
				</td>
				<td><form:input  path="planNum" maxlength="50" style="width:150px" id="planNum4"
					onblur="roleCodeajax(4)" /></td>
				<td><form:input  path="increases" maxlength="50" style="width:150px" id="increases4" />
				</td>
				
			</tr>
			</form:form>
			<form:form id="inputFormDetail5" modelAttribute="bdPlanDetail4" action=""
		method="post" class="form-horizontal"> 			
			<tr>
				<td><form:select path="certactType" class="input-medium" id="certactType5"
					onchange="getConfirm(5)">
						<form:option value="0">请选择：</form:option>
						<c:forEach var="obj"
							items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" varStatus="i">
							<form:option value="${obj.pValue }">${obj.pName}</form:option>
						</c:forEach>
						</form:select></td>
				<td><form:input path="certactName" maxlength="50" style="width:150px" id="certactName5" />
					<span class='Validform_checktip'></span></td>
				<td><form:input  path="certactCode" maxlength="50" style="width:150px" id="certactCode5" />
				</td>
				<td><form:input path="storeNum" maxlength="50" style="width:150px" id="storeNum5" />
				</td>
				<td><form:input  path="planNum" maxlength="50" style="width:150px" id="planNum5"
					onblur="roleCodeajax(5)" /></td>
				<td><form:input  path="increases" maxlength="50" style="width:150px" id="increases5" />
				</td>
				
			</tr>
			</form:form>	
		</table>
		</div>
     <div class="row">
			<div class="span2">
				<label>说明</label>
			</div>
			<div class="span3">
				<textarea  style="width:900px"
					id="remark"></textarea>
			</div>
		</div>

        <font size="2">注：</font>:<br/>
	    &nbsp;&nbsp;&nbsp;1.某凭证下季度请领增幅=（本季度凭证结余量+下季度凭证请领量-本季度凭证使用量）/本季度凭证使用量 *100%<br/>
	    &nbsp;&nbsp;&nbsp;2.结余量或者使用量为0时，不存在增幅!<br/>
	<div class="form-actions" align="center" style="margin-bottom:20px;">
		<input id="btnSubmit1" class="btn btn-primary" type="button" value="保存"
			onclick="onclickbtnSubmit1()" /> 
			<input id="btnsub1" class="btn btn-primary" type="button" value="提交"
			onclick="onclickbtnsub1()" /> 
		<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
	</div>
</body>
</html>
<script type="text/javascript">
	function roleCodeajax(colums) {
		var useNum;
		var orgNumber = $("#orgNo").val();
		var planOrgnumber = $("#planOrgno").val();
		var certactCode = $("#certactCode" + colums + "").val();
         var certactName = $("#certactName" + colums + "").val();
		var planNumber = parseInt($("#planNum" + colums + "").val());
		var storeNum = parseInt($("#storeNum" + colums + "").val());
		var planDate = $("#quarter").val();
		$.ajax({
			type : "POST",
			async : false,
			dataType : 'json',
			url : "${ctx}/bd/use/findNum?orgNo=" + planOrgnumber + "&certactCode="
					+ certactCode+"&useDate="+planDate,
			success : function(data) {
				useNum = parseInt(data);
			}
		});
		var num;
		if (useNum == 0 || useNum == null) {
			num = 0;
			this.$("#increases" + colums + "").val("--");
		} else {
			num = ((storeNum + planNumber - useNum) / useNum).toFixed(4);
			this.$("#increases" + colums + "").val(num * 100 + "%");
		}
		document.getElementById("increases" + colums + "").setAttribute(
				"readonly", true);
		if (num > 20) {
			document.getElementById("increases" + colums + "").style.color = "red";
		}
	}
	function getConfirm(colums){
		  var data = $("#certactType" + colums + "").val();
		  $("#certactName" + colums + "").val("");
		  $("#certactCode" + colums + "").val("");
	      $("#storeNum" + colums + "").val("");	
		  $.ajax({
			url : "${ctx}/bd/certact/getConfirm?certactType=" + data,
			type : "GET",
			dataType : "json",
			success : function(data) {	
			      $("#certactName" + colums + "").focus();	
					$("#certactName" + colums + "").autocomplete(				
	                  data,
						{
						    cacheLength:1,
							minChars : 0,
							width : 170,
							formatItem : function(item) {
								return item.id + "--"
										+ item.name;
							},
							formatResult : function(item) {
								return item.name;
							}
						}).result(function(event,item) {
			               	$("#certactCode" + colums + "").val(item.id);
			               	document.getElementById("certactCode" + colums + "").setAttribute("readonly",true);
			               	$("#storeNum" + colums + "").val(item.num);	
			               	document.getElementById("storeNum" + colums + "").setAttribute("readonly",true);		
			            });
			}
		});
	}
</script>