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
<div class="form-actions">
    <label><span style="font-size:13px;font-family:宋体;font-weight: bold;" >请求单内容:</span></label>
	<form:form id="inputForm" modelAttribute="courtQueryInfo" action="" method="post" class="form-horizontal">
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>类别：</label>
			</div>
			<div class="span3">
				<form:input path="bankType" htmlEscape="false" maxlength="50"
					style="width:150px" id="bankType" readonly="true" />
					<form:input path="queryId" htmlEscape="false" maxlength="50"
					style="width:150px" id="queryId" type="hidden" />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>性质：</label>
			</div>
			<div class="span3">
				<form:input path="queryQu" htmlEscape="false" maxlength="50"
					style="width:150px" id="queryQu" readonly="true"  />		
			</div>			
		</div>
		
		<div class="row">
		<div class="span1"></div>
		<div class="span2">
				<label>被查询人姓名：</label>
			</div>
			<div class="span3">
				<form:input path="queryName" htmlEscape="false" maxlength="50"
					style="width:150px" id="queryName" readonly="true" />
			
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>国家或地区：</label>
			</div>
			<div class="span3">
				<form:input path="nationArea" htmlEscape="false" maxlength="50"
					style="width:150px" id="nationArea" readonly="true" />
			</div>			
		</div>
		


		<div class="row">
		<div class="span1"></div>
			<div class="span2">
				<label>证件类型：</label>
			</div>
			<div class="span3">
				<form:input path="certType" id="certType" htmlEscape="false" maxlength="50"
					style="width:150px"  readonly="true"  />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>被查询人证件：</label>
			</div>
			<div class="span3">
				<form:input path="certNo" htmlEscape="false" maxlength="50"
					style="width:150px" id="certNo"  readonly="true"  />
					<form:input path="queryId" htmlEscape="false" maxlength="50"
					style="width:150px" id="queryId"  type="hidden" />
			</div>
		</div>
		<div class="row">
		<div class="span1"></div>
			<div class="span2">
				<label>发证机关所在地：</label>
			</div>
			<div class="span3">
				<form:input path="issuctfAhrLo" htmlEscape="false" maxlength="50"
					style="width:150px" id="issuctfAhrLo" readonly="true" />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>申请机构名称：</label>
			</div>
			<div class="span3">
				<form:input path="courtName" id="courtName" htmlEscape="false" maxlength="50"
					style="width:150px"  readonly="true"  />
			</div>
		</div>	
		<div class="row">		
			<div class="span1"></div>
			<div class="span2">
				<label>承办检察官：</label>
			</div>
			<div class="span3">
				<form:input path="judgeName" htmlEscape="false" maxlength="50"
					style="width:150px" id="judgeName"  readonly="true"  />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>执行案号：</label>
			</div>
			<div class="span3">
				<form:input path="caseId" id="caseId" htmlEscape="false" maxlength="50"
					style="width:150px"  readonly="true"  />
			</div>
		</div>	
		<div class="row">		
			<div class="span1"></div>
			<div class="span2">
				<label>往来账查询开始时间：</label>
			</div>
			<div class="span3">
				<form:input path="startDt" htmlEscape="false" maxlength="50"
					style="width:150px" id="startDt"  readonly="true"  />
			</div>
			<div class="span1"></div>
			<div class="span2">
				<label>往来账查询结束时间：</label>
			</div>
			<div class="span3">
				<form:input path="endDt" id="endDt" htmlEscape="false" maxlength="50"
					style="width:150px"  readonly="true"  />
			</div>
		</div>	
		  </form:form>
		  </div>
		  
		  <div class="form-actions" style="margin-bottom:20px;">
		  <table id="contentTable" width="600px" align="center" class="table table-striped table-bordered table-condensed"> 
		  <lable align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >请领单文件：</span></lable>
		  <c:forEach items="${page.list}" var="courtQueryInfo">		     
        <tr>  
            <td width="20"><a
					href="${pageContext.request.contextPath}/../tmp/${courtQueryInfo.wjmc}" target="_blank">${courtQueryInfo.wjmc}</a></td>  
        </tr>       
        </c:forEach> 
        </table>
        </div>
        <div class="form-actions" style="margin-bottom:20px;">
        <div class="row">
        <div class="span1">
			<lable align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >审核意见</span></lable>
			</div>
			<div class="span2">
				<textarea  htmlEscape="false" style="width:900px"
					id="bc" ></textarea>
			</div>
			</div>
        </div>
		  
	<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnSubmitstatus" class="btn btn-primary" type="button" value="同意" onclick="onclickSubmit()" /> 
			<input id="btnBack" class="btn btn-primary" type="button" value="不同意" onclick="onclicksub()" /> 
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
</body>
</html>
<script type="text/javascript">
		function onclickSubmit(){
		   window.location.href=encodeURI(encodeURI("${ctx}/court/check/agree?bdhm="+$("#queryId").val()));
		 }  
			   
        function onclicksub(){
		  if(document.getElementById("bc").value!=""&&document.getElementById("bc").value!=null){
		     window.location.href=encodeURI(encodeURI("${ctx}/court/check/back?bdhm="+$("#queryId").val()+"&remark="+document.getElementById("bc").value));
		  }else{
		    showTips( '请填写退回原因！', 300, 200,5 );
		  } 	  		 
		  }
    function showTips(tips, height, sizeWidth, time) {
    var windowWidth = document.documentElement.clientWidth;
    var tipsDiv = '<div class="tipsClass">' + tips + '</div>';
    $('body').append(tipsDiv);
    $('div.tipsClass').css({
        'top': '460px',
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