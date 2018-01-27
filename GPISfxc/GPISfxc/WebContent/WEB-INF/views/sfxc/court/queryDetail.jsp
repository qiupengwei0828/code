<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctxStatic}/jquery-validation/1.11.1/lib/jquery.form.js" type="text/javascript"></script>
<style type="text/css">
.span2 {
	padding-left: 20px;
	width: 100px;
}

.row {
	padding-bottom: 5px;
}
caption {
        text-align:left;
        margin:0 auto;
}
.c1 {
	background-color: #000000;
}
.c2 {
	background-color: #DFDFDF;
}

</style>



</head>
<body>
     <div class="form-actions" style="margin-bottom:20px;">
		  <table id="contentTable" width="600px" align="center" class="table table-striped table-bordered table-condensed"> 
		  <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >法院协查请求单</span></caption>   
        <tr>  
            <td style="text-align:right;">查询请求单号：</td><td>${courtQueryInfo.queryId}</td>  
            <td style="text-align:right;">被查询人姓名：</td><td>${courtQueryInfo.queryName}</td>  
            <td style="text-align:right;">被查询人证件：</td><td>${courtQueryInfo.certNo}</td>  
            <td style="text-align:right;">申请机构名称：</td><td>${courtQueryInfo.courtName}</td>    
        </tr>  
        <tr>  
            <td style="text-align:right;">往来账查询开始时间：</td><td >${courtQueryInfo.startDt}</td>  
            <td style="text-align:right;">国家或地区：</td><td >${courtQueryInfo.nationArea}</td>  
            <td style="text-align:right;">承办法官：</td><td >${courtQueryInfo.judgeName}</td> 
            <td style="text-align:right;">性质：</td><td>${courtQueryInfo.queryQu}</td>     
        </tr> 
        <tr>  
            <td style="text-align:right;">往来账查询结束时间：</td><td >${courtQueryInfo.endDt}</td>  
            <td style="text-align:right;">证件类型：</td><td >${courtQueryInfo.certType}</td>  
            <td style="text-align:right;">发证机关所在地：</td><td>${courtQueryInfo.issuctfAhrLo}</td>  
            <td style="text-align:right;">执行案号：</td><td >${courtQueryInfo.caseId}</td>    
        </tr> 
        <tr>  
            <td style="text-align:right;">类别：</td><td>${courtQueryInfo.bankType}</td>  
        </tr>      
        </table>
        </div>
        <input id="bdhm" value="${courtQueryInfo.queryId}" type="hidden" />
        
<div class="form-actions" >
<label><span style="font-size:13px;font-family:宋体;font-weight: bold;" >反馈结果录入</span></label><br/>
      <ul class="nav nav-tabs">
		<li id="li1" class="active"><a href="javascript:changeForm(1);" >具体账户信息</a></li>
		<li id="li2"><a href="javascript:changeForm(2);" >冻结信息</a></li>
		<li id="li3"><a href="javascript:changeForm(3);" >资金往来信息</a></li>
		<li id="li4"><a href="javascript:changeForm(4);" >关联账户信息</a></li>
	</ul>
		<form:form id="inputForm1" modelAttribute="courtResultZhxx" action=""
		method="post" class="form-horizontal">
		
		<div class="control-group">
			<label class="control-label">账户序号:</label>
			<div class="span3">
				<form:input path="ccxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="ccxh" />
			</div>
			<label class="control-label">开户账号:</label>
			<div class="span3">
				<form:input path="khzh" htmlEscape="false" maxlength="50"
					style="width:150px" id="khzh" />
			</div>			
		</div>
		
		<div class="control-group">
			<label class="control-label">余额:</label>
			<div class="span3">
				<form:input path="ye" htmlEscape="false" maxlength="50"
					style="width:150px" id="ye" />
			</div>
			<label class="control-label">数据提供者:</label>
			<div class="span3">
				<form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:150px" id="provider" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈结果时间:</label>
			<div class="span3">
				<input id="fksj" name="fksj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">提供时间:</label>
			<div class="span3">
				<input id="providetime" name="providetime"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">账户类别:</label>
			<div class="span3">
				<form:input path="cclb" htmlEscape="false" maxlength="50"
					style="width:150px" id="cclb" />
			</div>
			<label class="control-label">是否透支:</label>
			<div class="span3">
				<form:input path="sftz" htmlEscape="false" maxlength="50"
					style="width:150px" id="sftz" />
			</div>
		</div>
         <div class="control-group">
			<label class="control-label">账户状态:</label>
			<div class="span3">
				<form:input path="zhzt" htmlEscape="false" maxlength="50"
					style="width:150px" id="zhzt" />
			</div>
			<label class="control-label">开户网点:</label>
			<div class="span3">
				<form:input path="khwd" htmlEscape="false" maxlength="50"
					style="width:200px" id="khwd"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">币种:</label>
			<div class="span3">	
			      <form:input path="bz" htmlEscape="false" maxlength="50"
					style="width:150px" id="bz" />			
			</div>
			<label class="control-label">通讯地址:</label>
			<div class="span3">
				<form:input path="txdz" htmlEscape="false" maxlength="50"
					style="width:150px" id="txdz" />
			</div>
		</div>			
		<div class="control-group">
			<label class="control-label">邮政编码:</label>
			<div class="span3">
				<form:input path="yzbm" htmlEscape="false" maxlength="50"
					style="width:150px" id="yzbm" />
			</div>
			<label class="control-label">联系电话:</label>
			<div class="span3">
				<form:input path="lxdh" htmlEscape="false" maxlength="50"
					style="width:150px" id="lxdh" />
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="beiz" htmlEscape="false" style="width:600px"
				id="beiz"></form:textarea>
			</div>
		</div>
		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable1" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >账户信息列表</span></caption>  
		<tr>
		    <th>账户序号</th>
		    <th>开户账号</th>
			<th>开户网点</th>
			<th>币种</th>
			<th>账户类别</th>
			<th>账户状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list1}" var="courtResultZhxx">
			<tr>
			    <td>${courtResultZhxx.ccxh}</td>
			    <td>${courtResultZhxx.khzh}</td>
                <td>${courtResultZhxx.khwd}</td>
                <td>${courtResultZhxx.bz}</td>
			    <td>${courtResultZhxx.cclb}</td>
			    <td>${courtResultZhxx.zhzt}</td>
			    <td><a
					href="javascript:updateData(1,${courtResultZhxx.id});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
		
	<form:form id="inputForm3" modelAttribute="courtResultZjwlxx" action=""
		method="post" class="form-horizontal" style="display:none;" >					
		<div class="control-group">
			<label class="control-label">账户序号:</label>
			<div class="span3">
				<form:input path="ccxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="ccxh" />
			</div>
			
			<label class="control-label">资金往来序号:</label>
			<div class="span3">
				<form:input path="wlxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="wlxh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资金流向:</label>
			<div class="span3">
				<input id="zjlx" name="zjlx"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">对方卡/折号:</label>
			<div class="span3">
				<form:input path="zckzh" htmlEscape="false" maxlength="50"
					style="width:150px" id="zckzh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对方卡/折姓名:</label>
			<div class="span3">
				<form:input path="zckzxm" htmlEscape="false" maxlength="80" class="phone-input" autocomplete="off"
					style="width:230px" id="zckzxm" />
			</div>
			<label class="control-label">币种:</label>
			<div class="span3">
					<input id="bz" name="bz"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额:</label>
			<div class="span3">
					<input id="je" name="je"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">交易日期:</label>
			<div class="span3">
				<form:input path="jysj" htmlEscape="false" maxlength="50"
					style="width:150px" id="jysj" />
			</div>
		</div>
         <div class="control-group">			
			<label class="control-label">数据提供者:</label>
			<div class="span3">
					<input id="provider" name="provider"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>	
		<div class="control-group">
		<label class="control-label">提供时间:</label>
			<div class="span3">
				<form:input path="providetime" htmlEscape="false" maxlength="50"
					style="width:150px" id="providetime" />
			</div>
			</div>
			<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable3" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >资金往来信息</span></caption>  
		<tr>
		    <th>账户序号</th>
		    <th>资金往来序号</th>
			<th>资金流向</th>
			<th>交易日期</th>
			<th>数据提供者</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list3}" var="courtResultZjwlxx">
			<tr>
			    <td>${courtResultZjwlxx.ccxh}</td>
			    <td>${courtResultZjwlxx.wlxh}</td>
				<td>${courtResultZjwlxx.zjlx}</td>
				<td>${courtResultZjwlxx.jysj}</td>
			    <td>${courtResultZjwlxx.provider}</td>
			    <td><a
					href="javascript:updateData(3,${courtResultZjwlxx.id});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>	
	</form:form>
	
	
	
	
	
	<form:form id="inputForm4" modelAttribute="courtResultGlzhxx" action=""
		method="post" class="form-horizontal" style="display:none;" >
		
		<div class="control-group">
			<label class="control-label">账户序号:</label>
			<div class="span3">
				<form:input path="ccxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="ccxh" />
			</div>
			<label class="control-label">关联流水序号:</label>
			<div class="span3">
				<form:input path="glxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="glxh" />					
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label">关联账户类别:</label>
			<div class="span3">
				<form:input path="glzhlb" htmlEscape="false" maxlength="50"
					style="width:150px" id="glzhlb" />
			</div>
			
			<label class="control-label">关联账户号码:</label>
			<div class="span3">
				<form:input path="glzhhm" htmlEscape="false" maxlength="50"
					style="width:150px" id="glzhhm" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">证券/期货公司名称:</label>
			<div class="span3">
				<form:input path="glzhmc" htmlEscape="false" maxlength="80" class="phone-input" autocomplete="off"
					style="width:230px" id="glzhmc" />
			</div>
			<label class="control-label">数据提供者:</label>
			<div class="span3">
				<form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:150px" id="provider" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提供时间:</label>
			<div class="span3">
				<form:input path="providetime" htmlEscape="false" maxlength="50"
					style="width:150px" id="providetime" />
			</div>			
		</div>
         
		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable4" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >关联账户信息</span></caption>  
		<tr>
		    <th>账户序号</th>
		    <th>关联流水序号</th>
			<th>关联账户类别</th>
			<th>证券/期货公司名称</th>
			<th>数据提供者</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list4}" var="courtResultGlzhxx">
			<tr>
			    <td>${courtResultGlzhxx.ccxh}</td>
			    <td>${courtResultGlzhxx.glxh}</td>
				<td>${courtResultGlzhxx.glzhlb}</td>
				<td>${courtResultGlzhxx.glzhmc}</td>
			    <td>${courtResultGlzhxx.provider}</td>
			    <td><a
					href="javascript:updateData(4,${courtResultGlzhxx.id});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	<form:form id="inputForm2" modelAttribute="courtResultDjxx" action=""
		method="post" class="form-horizontal" style="display:none;" >
		
		<div class="control-group">
			<label class="control-label">账户序号:</label>
			<div class="span3">
				<form:input path="ccxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="ccxh" />
			</div>
			<label class="control-label">控制措施序号:</label>
			<div class="span3">
				<form:input path="csxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="csxh" />
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label">是否被冻结:</label>
			<div class="span3">
				<form:input path="sfbdj" htmlEscape="false" maxlength="50"
					style="width:150px" id="sfbdj" />
			</div>
			
			<label class="control-label">冻结截止日:</label>
			<div class="span3">
				<form:input path="djjzrq" htmlEscape="false" maxlength="50"
					style="width:150px" id="djjzrq" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">冻结机关:</label>
			<div class="span3">
				<form:input path="djjg" htmlEscape="false" maxlength="50"
					style="width:150px" id="djjg" />
			</div>
			<label class="control-label">冻结文号:</label>
			<div class="span3">
				<form:input path="djwh" htmlEscape="false" maxlength="50"
					style="width:150px" id="djwh" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">冻结金额:</label>
			<div class="span3">
				<input id="djje" name="djje"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">数据提供者:</label>
			<div class="span3">
				<form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:150px" id="provider" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提供时间:</label>
			<div class="span3">
				<form:input path="providetime" htmlEscape="false" maxlength="50"
					style="width:150px" id="providetime" />
			</div
		</div>
		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable2" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >冻结信息</span></caption>  
		<tr>
		    <th>账户序号</th>
		    <th>控制措施序号</th>
			<th>是否被冻结</th>
			<th>冻结机关</th>
			<th>冻结文号</th>
			<th>数据提供者</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list2}" var="courtResultDjxx">
			<tr>
			    <td>${courtResultDjxx.ccxh}</td>
			    <td>${courtResultDjxx.csxh}</td>
				<td>${courtResultDjxx.sfbdj}</td>
				<td>${courtResultDjxx.djjg}</td>
			    <td>${courtResultDjxx.djwh}</td>
			    <td>${courtResultDjxx.provider}</td>
			    <td><a
					href="javascript:updateData(2,${courtResultDjxx.id});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	</div>
	
</body>
</html>
<script type="text/javascript">		 		 
		 function updateData(form,keyid){
		   var bdhm = $("#bdhm").val();
		   if(form==1){ 
			   $.ajax({
                type: "POST",
                url:"${ctx}/court/deal/updateZhxx?bdhm="+bdhm+"&id="+keyid,
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    ClearForm("inputForm1");
                    $("#inputForm1").formEdit(data);
                }
            }); 		  
		   }
		   if(form==2){
		   $.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/court/deal/updateDjxx?bdhm="+bdhm+"&id="+keyid,
                data:$("#inputForm2").serialize(),
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    ClearForm("inputForm2");
                    $("#inputForm2").formEdit(data);
                      
                }
            }); 
            }
		   if(form==3){
		   $.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/court/deal/updateZjwlxx?bdhm="+bdhm+"&id="+keyid,
                data:$("#inputForm3").serialize(),
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
					ClearForm("inputForm3");
                    $("#inputForm3").formEdit(data);
                      
                }
            }); 
		   }
		   if(form==4){
		   $.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/court/deal/updateGlzhxx?bdhm="+bdhm+"&id="+keyid,
                data:$("#inputForm4").serialize(),
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
					ClearForm("inputForm4");	
                    $("#inputForm4").formEdit(data);
                      
                }
            }); 
		   }		   
		 }
		 function changeForm(form){
		     $("#li1").removeClass("active");
		     $("#li2").removeClass("active");
		     $("#li3").removeClass("active");
		     $("#li4").removeClass("active");
		     $("#li"+form+"").addClass("active");
		     document.getElementById("inputForm1").style.display = "none";
		     document.getElementById("inputForm2").style.display = "none";
		     document.getElementById("inputForm3").style.display = "none";
		     document.getElementById("inputForm4").style.display = "none";		     
		     document.getElementById("inputForm"+form+"").style.display = "";
		 } 
		
    function showTips(tips, height, sizeWidth, time) {
    var windowWidth = document.documentElement.clientWidth;
    var tipsDiv = '<div class="tipsClass">' + tips + '</div>';
    $('body').append(tipsDiv);
    $('div.tipsClass').css({
        'top': '200px',
        'right': 450+ 'px',
        'position': 'absolute',
        'padding': '3px 5px',
        'background': '#8FBC8F',
        'font-size': 12 + 'px',
        'margin': '0 auto',
        'text-align': 'center',
        'width': sizeWidth + 'px',
        'color': '#fff',
        'opacity': '0.8'
    }).show();
    setTimeout(function () { $('div.tipsClass').fadeOut(); }, (time * 1000));
}

	function ClearForm(id) {
    var objId = document.getElementById(id);
    if (objId == undefined) {
        return;
    }
    for (var i = 0; i < objId.elements.length; i++) {
        if (objId.elements[i].type == "text") {
            objId.elements[i].value = "";
        }
        else if (objId.elements[i].type == "radio") {
            objId.elements[i].checked = false;
        }
        else if (objId.elements[i].type == "checkbox") {
            objId.elements[i].checked = false;
        }
        else if (objId.elements[i].type == "select-one") {
            objId.elements[i].options[0].selected = true;
        }
        else if (objId.elements[i].type == "select-multiple") {
            for (var j = 0; j < objId.elements[i].options.length; j++) {
                objId.elements[i].options[j].selected = false;
            }
        }
        else if (objId.elements[i].type == "textarea") {
            objId.elements[i].value = "";
        }
    }
} 
function sakujyo(tableId){
 var tb = document.getElementById(tableId);
     var rowNum=tb.rows.length;
     for (i=1;i<rowNum;i++)
     {
         tb.deleteRow(i);
         rowNum=rowNum-1;
         i=i-1;
     }
}

$.fn.formEdit = function(data){
    return this.each(function(){
        var input, name;
        if(data == null){this.reset(); return; }
        for(var i = 0; i < this.length; i++){  
            input = this.elements[i];
            //checkbox的name可能是name[]数组形式
            name = (input.type == "checkbox")? input.name.replace(/(.+)\[\]$/, "$1") : input.name;
            if(data[name] == undefined) continue;
            switch(input.type){
                case "checkbox":
                    if(data[name] == ""){
                        input.checked = false;
                    }else{
                        //数组查找元素
                        if(data[name].indexOf(input.value) > -1){
                            input.checked = true;
                        }else{
                            input.checked = false;
                        }
                    }
                break;
                case "radio":
                    if(data[name] == ""){
                        input.checked = false;
                    }else if(input.value == data[name]){
                        input.checked = true;
                    }
                break;
                case "button": break;
                default: input.value = data[name];
            }
        }
    });
};
function disableOcx() {
for(var j=0;j<4;j++){ 
var form = document.forms[j]; 
for ( var i = 0; i < form.length; i++) { 
var element = form.elements[i]; 
//部分元素可以编号 element.name 是元素自定义 name 
if (element.type != "button" ) { 
element.disabled = "true"; 
} 
} 
} 
}
window.onload = disableOcx();
</script>