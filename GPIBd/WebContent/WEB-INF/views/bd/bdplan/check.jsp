<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>请领计划</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
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
</style>



</head>
<body>
<br />
	<form:form id="inputForm" modelAttribute="bdPlan" action="" method="post" class="form-horizontal">
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>请领单编号：</label>
			</div>
			<div class="span3">
				<form:input path="planNumber" htmlEscape="false" maxlength="50"
					style="width:150px" id="planNumber" readonly="true" />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>分行名称：</label>
			</div>
			<div class="span3">
				<form:input path="orgNo" htmlEscape="false" maxlength="50"
					style="width:150px" id="orgNo" type="hidden" />
				<form:input path="orgName" htmlEscape="false" maxlength="50"
					style="width:150px" id="orgName" readonly="true" />
				<form:input path="id" htmlEscape="false" maxlength="50"
					style="width:150px" id="id" type="hidden" />
			</div>			
		</div>
		
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>请领机构名称：</label>
			</div>
			<div class="span3">
				<form:input path="planOrgname" htmlEscape="false" maxlength="50"
					style="width:150px" id="planOrgname" readonly="true" />
			
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>请领机构编码：</label>
			</div>
			<div class="span3">
				<form:input path="planOrgno" htmlEscape="false" maxlength="50"
					style="width:150px" id="planOrgno" readonly="true" />
			</div>			
		</div>
		


		<div class="row">
		<div class="span1"></div>
			<div class="span2">
				<label>季度：</label>
			</div>
			<div class="span3">
				<form:input path="quarter" id="quarter" htmlEscape="false" maxlength="50"
					style="width:150px"  readonly="true"  />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>日期：</label>
			</div>
			<div class="span3">
				<form:input path="planDate" htmlEscape="false" maxlength="50"
					style="width:150px" id="planDate"  readonly="true"  />
			</div>
		</div>
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>请领人员：</label>
			</div>
			<div class="span3">
				<form:input path="userId" htmlEscape="false" maxlength="50"
					style="width:150px" id="userId" readonly="true" />
			</div>
			</div>
		<div class="row">
		<div class="span1"></div>
			<div class="span2">
				<label>说明</label>
			</div>
			<div class="span3">
				<form:textarea path="remark" htmlEscape="false" style="width:600px"
					id="remark" readonly="true"></form:textarea>
			</div>
		</div>		
		  </form:form>
		  
		<div class="form-actions" style="margin-bottom:20px;">
		  <table id="contentTable" width="600px" align="center" class="table table-striped table-bordered table-condensed"> 
		  <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >请领单明细：</span></caption>   
        <tr >  
            <th width="20">单证代码</th>  
            <th width="20">单证名称</th>  
            <th width="20">凭证类型</th>  
            <th width="20">库存量</th> 
            <th width="20">请领量  </th> 
            <th width="20">请领增幅</th>
        </tr>
        <c:forEach items="${page.list}" var="bdPlanDetail">  
        <tr >   
            <td >  
            ${bdPlanDetail.certactCode}
            </td>  
            <td >  
            ${bdPlanDetail.certactName}
            </td>  
            <td > 
            ${fns:getDicLabel(bdPlanDetail.certactType,"DIC_BD_CERTACTTYPE", "")}
            </td> 
            <td>
           ${bdPlanDetail.storeNum}
            </td>
            <td >
           ${bdPlanDetail.planNum}
            </td>
            <td >
            ${bdPlanDetail.increases}
            </td>
        </tr> 
        </c:forEach> 
        </table>
        </div>
        <c:if test="${bdPlan.status!=03}">
        <table id="each" >  
        <div class="row">
			<div class="span2">
				<label>说明</label>
			</div>
			<div class="span3">
				<textarea  htmlEscape="false" style="width:900px"
					id="bc" ></textarea>
			</div>
		</div>
        <input id="btnsub" class="btn" style="display:none" type="button" value="提交" onclick="onclicksub()" />    
        </table>

		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnSubmitstatus" class="btn btn-primary" type="button" value="同意" onclick="onclickSubmit()" /> 
			<input id="btnBack" class="btn btn-primary" type="button" value="退回" onclick="onclicksub()" /> 
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		</c:if>	
	 <div class="form-actions" style="margin-bottom:20px;">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >请领单流程：</span></caption>  
		<tr>
            <th>请领单编号</th>
			<th>请领机构代码</th>
			<th>请领机构名称</th>
			<th>节点名称</th>
			<th>操作意见</th>			
			<th>操作结果</th>
			<th>操作人员</th>
			<th>操作时间</th>
			
		</tr>
		<c:forEach items="${page1.list}" var="bdProcess">
			<tr>
			    <td>${bdProcess.planNumber}</td>	
				<td>${bdProcess.orgNo}</td>	
				<td>${bdProcess.orgName}</td>			
				<td>${bdProcess.nodeName}</td>
				<td>${bdProcess.operatRemark}</td>				
			    <td>${bdProcess.operatResu}</td>
			    <td>${bdProcess.userId}</td>
				<td>${bdProcess.operatTime}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<c:if test="${bdPlan.status==03}">
	<div class="form-actions" align="center" style="margin-bottom:20px;">
		   <input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		   </div>
	</c:if>
</body>
</html>
<script type="text/javascript">
		function onclickSubmit(){
		   window.location.href=encodeURI(encodeURI("${ctx}/bd/bdplan/agree?id="+$("#id").val()+"&remark="+document.getElementById("bc").value));
		 }  
			   
        function onclicksub(){
          if(document.getElementById("bc").value!=""&&document.getElementById("bc").value!=null){
		  window.location.href=encodeURI(encodeURI("${ctx}/bd/bdplan/back?id="+$("#id").val()+"&remark="+document.getElementById("bc").value));	  
		  }else{
		    showTips( '请填写退回原因！', 100, 100,5 );
		  }
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
</script>