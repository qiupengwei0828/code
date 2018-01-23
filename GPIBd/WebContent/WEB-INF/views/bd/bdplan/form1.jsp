<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctx}/js/autocomplete/autocomplete.js" type="text/javascript"></script>
<link href="${ctx}/js/autocomplete/jquery.autocomplete.css" rel="stylesheet" />
<script type="text/javascript">
	
	$(document).ready(function() {	
	  $.ajax({
              type:"POST",
              url:"${ctx}/bd/common/findUser",
              success: function(data){
                if(data!=""&&data!=null){
                   $("#orgNo").val(data.orgNo);
                   $("#orgName").val(data.orgName);
                   document.getElementById("orgNo").setAttribute("readonly",true);
                   $("#userId").val(data.userName);
                   document.getElementById("userId").setAttribute("readonly",true);
                }
              }
          });
          var myDate = new Date();
          $("#planDate").val(myDate.toLocaleDateString());
		var id;
		var str = RndNum();
		id = str;
		$("#inputForm").attr("action",
				"${ctx}/bd/bdplan/form?id=" + id);

		$("#btnSubmit").click(function() {
				$("#inputForm").submit();
				var table = document
						.getElementById("each");
				var tableCount = table.rows.length;
				var colums = table.rows[0].cells.length;
				for (var i = 1; i < tableCount; i++) {
					var arr = new Array();
					for (var j = 1; j <= colums - 1; j++) {
						var code = table.rows[i].cells[j]
								.getElementsByTagName("INPUT")[0].value;
						arr[j] = code;						
					}
					var select = document.getElementById("certactType"+i+"").value;
					var url = encodeURI(encodeURI("${ctx}/bd/bdplan/addDetail?certactCode="
							+ arr[2]
							+ "&certactName="
							+ arr[1]
							+ "&certactType="
							+ select
							+ "&storeNum="
							+ arr[3]
							+ "&planNum="
							+ arr[4]
							+ "&planId="
							+ id
							+ "&increases="
							+ arr[5]));
					onAjax(url);
				}
			});
  $("#deleteTable").click(function() {
     var colums = document.getElementById("each").rows.length;
     var table = document.getElementById("each");
     var arr=table.rows;
     if(arr.length>2){
     table.deleteRow(arr.length-1);
     }
  });
		$("#addTable")
				.click(
						function() {
							var fns;
							$
									.ajax({
										type : "POST",
										async : false,
										dataType : "json",
										url : "${ctx}/bd/bdplan/getDicList",
										success : function(data) {
											fns = data;
										}
									});
							var colums = document
									.getElementById("each").rows.length;
							var tr = "<tr><td><div id=\"s2id_certactType1\" class=\"select2-container input-medium\"> "+
                                   "<a class=\"select2-choice\" tabindex=\"-1\" onclick=\"return false;\" href=\"javascript:void(0)\">"+
                                   " <span class=\"select2-chosen\">请选择：</span> <abbr class=\"select2-search-choice-close\"></abbr> "+
                                   "<span class=\"select2-arrow\"><b></b></span></a>"+
                                   " <input id=\"s2id_autogen2\" class=\"select2-focusser select2-offscreen\" type=\"text\">"+
                                    "</div><select id=\"certactType"+colums+"\" class=\"input-medium select2-offscreen\" onchange=\"getConfirm("
									+ colums
									+ ")\" >"
									+ "<option value=\"0\" selected>请选择：</option>";
							for (var i = 0; i < fns.length; i++) {
								var val = fns[i].pValue;
								var nam = fns[i].pName;
								tr += "<option value=\""+val+"\">"
										+ nam + "</option>";
							}
							tr += "</select></td><td><input  maxlength=\"50\" style=\"width:150px\" id=\"certactName"
									+ colums
									+ "\"  /></td>"
									+ "<td><input  maxlength=\"50\" style=\"width:150px\" id=\"certactCode"+colums+"\" /></td>";

							tr += "<td><input  maxlength=\"50\" style=\"width:150px\" id=\"storeNum"+colums+"\" /></td>"
									+ "<td><input  maxlength=\"50\" style=\"width:150px\" id=\"planNum"
									+ colums
									+ "\" onblur=\"roleCodeajax("
									+ colums
									+ ")\" /></td>"
									+ "<td><input  maxlength=\"50\" style=\"width:150px\" id=\"increases"+colums+"\" /></td>"
									+ "</tr>";
							$("#each").append(tr);
						});

		function RndNum(n) {
			var myDate = new Date();
			var rnd = myDate.getTime();
			return rnd;
		}	
		function onAjax(url) {
			$.ajax({
				type : "POST",
				async : false,
				dataType : "json",
				url : url,
				success : function(data) {
				}
			});

		}	
	});
	
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
		<li><a href="${ctx}/bd/bdplan/index">请领计划列表</a></li>
		<li class="active"><a href="${ctx}/bd/bdplan/add">请领申请</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="bdPlanEntity" action=""
		method="post" class="form-horizontal">
		<div class="row">
			<div class="span2">
				<label>请领机构</label>
			</div>
			<div class="span3">
				<form:input path="orgNo" htmlEscape="false" maxlength="50"
					style="width:150px" id="orgNo" />
					<form:input path="orgName" htmlEscape="false" maxlength="50"
					style="width:150px" id="orgName" type="hidden" />
			</div>
			<div class="span2">
				<label>请领人员</label>
			</div>
			<div class="span3">
				<form:input path="userId" htmlEscape="false" maxlength="50"
					style="width:150px" id="userId" />
			</div>
		</div>


		<div class="row">
			<div class="span2">
				<label>季度</label>
			</div>
			<div class="span3">										
			 <form:select path="quarter" class="input-medium">
			     <form:option value="" label="${list.get(1)}" />
				<form:options items="${list}" htmlEscape="false" />
			   </form:select>
			</div>
			<div class="span2">
				<label>日期</label>
			</div>
			<div class="span3">
				<form:input path="planDate" htmlEscape="false" maxlength="50"
					style="width:150px" id="planDate" />
			</div>
		</div>
		<div class="row">
			<div class="span2">
				<label>说明</label>
			</div>
			<div class="span3">
				<form:textarea path="remark" htmlEscape="false" style="width:530px"
					id="remark"></form:textarea>
			</div>
		</div>
		<div class="form-actions"></div>
	</form:form>

	<table id="each" class="table table-striped table-bordered table-condensed">
		<tr>
			<td>凭证类型</td>
			<td>单证名称</td>
			<td>单证代码</td>
			<td>库存量</td>
			<td>请领量</td>
			<td>请领增幅</td>
		</tr>
		<tr>
			<td><select id="certactType1" class="input-medium"
				onchange="getConfirm(1)">
					<option value="0">请选择：</option>
					<c:forEach var="obj"
						items="${fns:getDicList('DIC_BD_CERTACTTYPE')}" varStatus="i">
						<option value="${obj.pValue }">${obj.pName}</option>
					</c:forEach>
					<select></td>
			<td><input maxlength="50" style="width:150px" id="certactName1" />
				<span class='Validform_checktip'></span></td>
			<td><input maxlength="50" style="width:150px" id="certactCode1" />
			</td>
			<td><input maxlength="50" style="width:150px" id="storeNum1" />
			</td>
			<td><input maxlength="50" style="width:150px" id="planNum1"
				onblur="roleCodeajax(1)" /></td>
			<td><input maxlength="50" style="width:150px" id="increases1" />
			</td>
			<td><input type="button" value="增加" id="addTable" /></td>
			<td><input type="button" value="删除" id="deleteTable" /></td>
		</tr>
	</table>


	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保存"
			onclick="onclickSubmit()" style="align:center" /> <input
			id="btnCancel" class="btn" type="button" value="返 回"
			onclick="history.go(-1)" style="align:center" />
	</div>

</body>
</html>
<script type="text/javascript">
	function roleCodeajax(colums) {
		var useNum;
		var orgNumber = $("#orgNo").val();
		var certactCode = $("#certactCode" + colums + "").val();

		var planNumber = parseInt($("#planNum" + colums + "").val());
		var storeNum = parseInt($("#storeNum" + colums + "").val());
		$.ajax({
			type : "POST",
			async : false,
			dataType : 'json',
			url : "${ctx}/bd/use/findNum?orgNo=" + orgNumber + "&certactCode="
					+ certactCode,
			success : function(data) {
				useNum = parseInt(data);
			}
		});
		var num;
		if (useNum == 0||useNum==null) {
		   num = storeNum + planNumber;
		}else{
		   num = (storeNum + planNumber - useNum) / useNum;
		}
		this.$("#increases" + colums + "").val(num);
		document.getElementById("increases" + colums + "").setAttribute("readonly",true);	
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
			var certactCode = $(
														"#certactCode" + colums
																+ "").val();
												var certactName = $(
														"#certactName" + colums
																+ "").val();
												var urlinsert = encodeURI(encodeURI("${ctx}/bd/bdplanlog/insert?certactCode="
														+ certactCode
														+ "&certactName="
														+ certactName));
												$.ajax({
													url : urlinsert,
													type : "GET",
													dataType : "json",
													success : function(data) {
													}
												});
	}
</script>