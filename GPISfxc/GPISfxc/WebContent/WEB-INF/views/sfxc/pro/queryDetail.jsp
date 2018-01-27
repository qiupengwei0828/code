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
		  <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >检察院协查请求单</span></caption>   
        <tr>  
            <td style="text-align:right;">查询请求单号：</td><td>${proQueryInfo.queryId}</td>  
            <td style="text-align:right;">被查询人姓名：</td><td>${proQueryInfo.queryName}</td>  
            <td style="text-align:right;">被查询人证件：</td><td>${proQueryInfo.certNo}</td>  
            <td style="text-align:right;">申请机构名称：</td><td>${proQueryInfo.applyName}</td>    
        </tr>  
        <tr>  
            <td style="text-align:right;">往来账查询开始时间：</td><td >${proQueryInfo.startDt}</td>  
            <td style="text-align:right;">国家或地区：</td><td >${proQueryInfo.nationArea}</td>  
            <td style="text-align:right;">组织机构号码：</td><td >${proQueryInfo.orgNo}</td>  
            <td style="text-align:right;">承办检察官：</td><td >${proQueryInfo.procuratorName}</td>    
        </tr> 
        <tr>  
            <td style="text-align:right;">往来账查询结束时间：</td><td >${proQueryInfo.endDt}</td>  
            <td style="text-align:right;">证件类型：</td><td >${proQueryInfo.certType}</td>  
            <td style="text-align:right;">发证机关所在地：</td><td>${proQueryInfo.ssuctfAhrLo}</td>  
            <td style="text-align:right;">执行案号：</td><td >${proQueryInfo.caseId}</td>    
        </tr> 
        <tr>  
            <td style="text-align:right;">要求最晚反馈时间：</td><td>${proQueryInfo.lateBackDt}</td>  
            <td style="text-align:right;">类别：</td><td>${proQueryInfo.bankType}</td>  
            <td style="text-align:right;">性质：</td><td>${proQueryInfo.queryQu}</td>  
            <td style="text-align:right;">申请日期：</td><td>${proQueryInfo.requestDt}</td>    
        </tr>      
        </table>
        </div>
        <input id="bdhm" value="${proQueryInfo.queryId}" type="hidden" />
<div class="form-actions" >
<label><span style="font-size:13px;font-family:宋体;font-weight: bold;" >反馈结果录入</span></label><br/>
      <ul class="nav nav-tabs">
		<li id="li1" class="active"><a href="javascript:changeForm(1);" >账户信息</a></li>
		<li id="li2"><a href="javascript:changeForm(2);" >交易信息</a></li>
		<li id="li3"><a href="javascript:changeForm(3);" >保险柜信息</a></li>
		<li id="li4"><a href="javascript:changeForm(4);" >POS机商户信息</a></li>
		<li id="li5"><a href="javascript:changeForm(5);" >网银登录日志信息</a></li>
		<li id="li6"><a href="javascript:changeForm(6);" >自动机具信息</a></li>
	</ul>
	<div id="ajax1">
		<form:form id="inputForm1" modelAttribute="proResultZhxx" action=""
		method="post" class="form-horizontal">
		
		<div class="control-group">
			<label class="control-label">账户序号:</label>
			<div class="span3">
				<form:input path="zhxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="zhxh" />
			</div>
			<label class="control-label">开户账号:</label>
			<div class="span3">
				<form:input path="khzh" htmlEscape="false" maxlength="50"
					style="width:150px" id="khzh" />
			</div>			
		</div>
				
		<div class="control-group">			
			<label class="control-label">开户日期:</label>
			<div class="span3">
				<input id="khrq" name="khrq"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">销户日期:</label>
			<div class="span3">
				<input id="xhrq" name="xhrq"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
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
				<form:input path="zhlb" htmlEscape="false" maxlength="50"
					style="width:150px" id="zhlb" />
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
			<th>开户日期</th>
			<th>销户日期</th>
			<th>账户类别</th>
			<th>账户状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list1}" var="proResultZhxx">
			<tr>
			    <td>${proResultZhxx.zhxh}</td>
			    <td>${proResultZhxx.khzh}</td>
				<td>${proResultZhxx.khrq}</td>
				<td>${proResultZhxx.xhrq}</td>
			    <td>${proResultZhxx.zhlb}</td>
			    <td>${proResultZhxx.zhzt}</td>
			    <td><a
					href="javascript:updateData(1,${proResultZhxx.keyid});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	</div>
	
	
	
	
	
	
	
	<form:form id="inputForm2" modelAttribute="proResultJyxx" action=""
		method="post" class="form-horizontal" style="display:none;">
		
		<div class="control-group">
			<label class="control-label">开户账号:</label>
			<div class="span3">
				<form:input path="khzh" htmlEscape="false" maxlength="50"
					style="width:150px" id="khzh" />
			</div>
			<label class="control-label">交易流水号:</label>
			<div class="span3">
				<form:input path="jylsh" htmlEscape="false" maxlength="50"
					style="width:150px" id="jylsh" />
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label">交易类型:</label>
			<div class="span3">
				<form:input path="jylx" htmlEscape="false" maxlength="50"
					style="width:150px" id="jylx" />
			</div>
			
			<label class="control-label">交易方式:</label>
			<div class="span3">
				<form:input path="jyfs" htmlEscape="false" maxlength="50"
					style="width:150px" id="jyfs" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易网点:</label>
			<div class="span3">
				<form:input path="jywd" htmlEscape="false" maxlength="50"
					style="width:150px" id="jywd" />
			</div>
			<label class="control-label">机具编号:</label>
			<div class="span3">
				<form:input path="jjbh" htmlEscape="false" maxlength="50"
					style="width:150px" id="jjbh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易时间:</label>
			<div class="span3">
				<input id="jysj" name="jysj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">贷方金额:</label>
			<div class="span3">
				<form:input path="dfje" htmlEscape="false" maxlength="50"
					style="width:150px" id="dfje" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借方金额:</label>
			<div class="span3">
				<form:input path="jfje" htmlEscape="false" maxlength="50"
					style="width:150px" id="jfje" />
			</div>
			<label class="control-label">币种:</label>
			<div class="span3">
				<form:input path="bz" htmlEscape="false" maxlength="50"
					style="width:150px" id="bz" />
			</div>
		</div>
         <div class="control-group">
			<label class="control-label">收付方名称:</label>
			<div class="span3">
				<form:input path="sffmc" htmlEscape="false" maxlength="50"
					style="width:150px" id="sffmc" />
			</div>
			<label class="control-label">收付方账号:</label>
			<div class="span3">
				<form:input path="sffzh" htmlEscape="false" maxlength="50"
					style="width:200px" id="sffzh"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收付方类型:</label>
			<div class="span3">	
			    <form:input path="sfflx" htmlEscape="false" maxlength="50"
					style="width:150px" id="sfflx" />			
			</div>
			<label class="control-label">收付方单位:</label>
			<div class="span3">
				<form:input path="sffdw" htmlEscape="false" maxlength="50"
					style="width:150px" id="sffdw" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">余额:</label>
			<div class="span3">	
			    <form:input path="ye" htmlEscape="false" maxlength="50"
					style="width:150px" id="ye" />			
			</div>
			<label class="control-label">反馈结果时间:</label>
			<div class="span3">				
					<input id="fksj" name="fksj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>	
		
		<div class="control-group">
			
			<label class="control-label">数据提供者:</label>
			<div class="span3">
				<form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:150px" id="provider" />
			</div>
			<label class="control-label">提供时间:</label>
			<div class="span3">
				<input id="providetime" name="providetime"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>			
			
		<div class="control-group">
			<label class="control-label">摘要信息:</label>
			<div class="controls">
				<form:textarea path="zyxx" htmlEscape="false" style="width:600px"
				id="zyxx"></form:textarea>
			</div>
		</div>
		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable2" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >交易信息</span></caption>  
		<tr>
		    <th>开户账号</th>
		    <th>交易流水号</th>
			<th>交易类型</th>
			<th>交易方式</th>
			<th>交易网点</th>
			<th>机具编号</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list2}" var="proResultJyxx">
			<tr>
			    <td>${proResultJyxx.khzh}</td>
			    <td>${proResultJyxx.jylsh}</td>
				<td>${proResultJyxx.jylx}</td>
				<td>${proResultJyxx.jyfs}</td>
			    <td>${proResultJyxx.jywd}</td>
			    <td>${proResultJyxx.jjbh}</td>
			    <td><a
					href="javascript:updateData(2,${proResultJyxx.keyid});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	
	
	
	
	
	
	<form:form id="inputForm3" modelAttribute="proResultBxgxx" action=""
		method="post" class="form-horizontal" style="display:none;">					
		<div class="control-group">
			<label class="control-label">保险柜序号:</label>
			<div class="span3">
				<form:input path="bxgxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="bxgxh" />
			</div>
			
			<label class="control-label">保险柜号:</label>
			<div class="span3">
				<form:input path="bxgh" htmlEscape="false" maxlength="50"
					style="width:150px" id="bxgh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反馈结果时间:</label>
			<div class="span3">
				<input id="fksj" name="fksj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">保险柜状态:</label>
			<div class="span3">
				<form:input path="bxgzt" htmlEscape="false" maxlength="50"
					style="width:150px" id="bxgzt" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在网点:</label>
			<div class="span3">
				<form:input path="szwd" htmlEscape="false" maxlength="80" class="phone-input" autocomplete="off"
					style="width:230px" id="szwd" />
			</div>
			<label class="control-label">租用开始时间:</label>
			<div class="span3">
					<input id="zykssj" name="zykssj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">租用结束时间:</label>
			<div class="span3">
					<input id="zyjssj" name="zyjssj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
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
					<input id="providetime" name="providetime"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
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
		<table id="contentTable3" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >保险柜信息</span></caption>  
		<tr>
		    <th>保险柜序号</th>
		    <th>保险柜号</th>
			<th>反馈结果时间</th>
			<th>所在网点</th>
			<th>保险柜状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list3}" var="proResultBxgxx">
			<tr>
			    <td>${proResultBxgxx.bxgxh}</td>
			    <td>${proResultBxgxx.bxgh}</td>
				<td>${proResultBxgxx.fksj}</td>
				<td>${proResultBxgxx.szwd}</td>
			    <td>${proResultBxgxx.bxgzt}</td>
			    <td><a
					href="javascript:updateData(3,${proResultBxgxx.keyid});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	
	
	
	
	<form:form id="inputForm4" modelAttribute="proResultPosxx" action=""
		method="post" class="form-horizontal" style="display:none;">
		
		<div class="control-group">
			<label class="control-label">POS机序号:</label>
			<div class="span3">
				<form:input path="posxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="posxh" />
			</div>
			<label class="control-label">商户:</label>
			<div class="span3">
				<form:input path="sh" htmlEscape="false" maxlength="50"
					style="width:150px" id="sh" />					
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label">开户账号:</label>
			<div class="span3">
				<form:input path="khzh" htmlEscape="false" maxlength="50"
					style="width:150px" id="khzh" />
			</div>
			
			<label class="control-label">办理地址:</label>
			<div class="span3">
				<form:input path="dldz" htmlEscape="false" maxlength="50"
					style="width:150px" id="dldz" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">通讯方式:</label>
			<div class="span3">
				<form:input path="txfs" htmlEscape="false" maxlength="80" class="phone-input" autocomplete="off"
					style="width:230px" id="txfs" />
			</div>
			<label class="control-label">通讯商家:</label>
			<div class="span3">
				<form:input path="txsj" htmlEscape="false" maxlength="50"
					style="width:150px" id="txsj" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绑定号码:</label>
			<div class="span3">
				<form:input path="hm" htmlEscape="false" maxlength="50"
					style="width:150px" id="hm" />
			</div>
			<label class="control-label">反馈时间:</label>
			<div class="span3">
					<input id="fksj" name="fksj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
         <div class="control-group">
			<label class="control-label">数据提供者:</label>
			<div class="span3">
				<form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:150px" id="provider" />
			</div>
			<label class="control-label">提供时间:</label>
			<div class="span3">
					<input id="providetime" name="providetime"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable4" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >保险柜信息</span></caption>  
		<tr>
		    <th>POS机序号</th>
		    <th>商户</th>
			<th>开户账号</th>
			<th>办理地址</th>
			<th>通讯方式</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list6}" var="proResultPosxx">
			<tr>
			    <td>${proResultPosxx.posxh}</td>
			    <td>${proResultPosxx.sh}</td>
				<td>${proResultPosxx.khzh}</td>
				<td>${proResultPosxx.dldz}</td>
			    <td>${proResultPosxx.txfs}</td>
			    <td><a
					href="javascript:updateData(4,${proResultPosxx.keyid});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	
	
	
	
	
	
	<form:form id="inputForm5" modelAttribute="proResultDlrzxx" action=""
		method="post" class="form-horizontal" style="display:none;">
		
		<div class="control-group">
			<label class="control-label">登陆日志序号:</label>
			<div class="span3">
				<form:input path="rzxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="rzxh" />
			</div>
			<label class="control-label">登陆时间:</label>
			<div class="span3">
				<input id="dlsj" name="dlsj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label">开户帐户:</label>
			<div class="span3">
				<form:input path="khzh" htmlEscape="false" maxlength="50"
					style="width:150px" id="khzh" />
			</div>
			
			<label class="control-label">登陆地址:</label>
			<div class="span3">
				<form:input path="dldz" htmlEscape="false" maxlength="50"
					style="width:150px" id="dldz" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登陆操作:</label>
			<div class="span3">
				<form:input path="dlcz" htmlEscape="false" maxlength="50"
					style="width:150px" id="dlcz" />
			</div>
			<label class="control-label">反馈结果时间:</label>
			<div class="span3">
				<input id="fksj" name="fksj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">			
			<label class="control-label">数据提供者:</label>
			<div class="span3">
				<form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:150px" id="provider" />
			</div>
			<label class="control-label">提供时间:</label>
			<div class="span3">
				<input id="providetime" name="providetime"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登陆描述:</label>
			<div class="controls">
				<form:textarea path="dlms" htmlEscape="false" style="width:600px"
				id="dlms"></form:textarea>
			</div>	
		</div>
        <div class="form-actions" align="center" style="margin-bottom:20px;">
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>
		<table id="contentTable5" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >网银登录日志信息</span></caption>  
		<tr>
		    <th>登陆日志序号</th>
		    <th>登陆时间</th>
			<th>登陆地址</th>
			<th>登陆操作</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list4}" var="proResultDlrzxx">
			<tr>
			    <td>${proResultDlrzxx.rzxh}</td>
			    <td>${proResultDlrzxx.dlsj}</td>
				<td>${proResultDlrzxx.dldz}</td>
				<td>${proResultDlrzxx.dlcz}</td>
			    <td><a
					href="javascript:updateData(5,${proResultDlrzxx.keyid});">查看详情</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
	
	
	
	
	
	
	
	<form:form id="inputForm6" modelAttribute="proResultJjxx" action=""
		method="post" class="form-horizontal" style="display:none;">
		
		<div class="control-group">
			<label class="control-label">自动机具序号:</label>
			<div class="span3">
				<form:input path="jjxh" htmlEscape="false" maxlength="50"
					style="width:150px" id="jjxh" />
			</div>
			<label class="control-label">机具地址:</label>
			<div class="span3">
				<form:input path="jjdz" htmlEscape="false" maxlength="50"
					style="width:150px" id="jjdz" />
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label">经度:</label>
			<div class="span3">
				<form:input path="jd" htmlEscape="false" maxlength="50"
					style="width:150px" id="jd" />
			</div>
			
			<label class="control-label">纬度:</label>
			<div class="span3">
				<form:input path="wd" htmlEscape="false" maxlength="50"
					style="width:150px" id="wd" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网点号:</label>
			<div class="span3">
				<form:input path="wdh" htmlEscape="false" maxlength="50"
					style="width:150px" id="wdh" />
			</div>
			<label class="control-label">机构号:</label>
			<div class="span3">
				<form:input path="jgh" htmlEscape="false" maxlength="50"
					style="width:150px" id="jgh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机具编号:</label>
			<div class="span3">
				<form:input path="jjbh" htmlEscape="false" maxlength="80" class="phone-input" autocomplete="off"
					style="width:230px" id="jjbh" />
			</div>
			<label class="control-label">网点名称:</label>
			<div class="span3">
				<form:input path="jjbh" htmlEscape="false" maxlength="50"
					style="width:150px" id="jjbh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="span3">
				<form:input path="lxdh" htmlEscape="false" maxlength="50"
					style="width:150px" id="lxdh" />
			</div>
			<label class="control-label">机具类型:</label>
			<div class="span3">
				<form:input path="jjlx" htmlEscape="false" maxlength="50"
					style="width:150px" id="jjlx" />
			</div>
		</div>
         <div class="control-group">
			<label class="control-label">反馈结果时间:</label>
			<div class="span3">
				<input id="fksj" name="fksj"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
			<label class="control-label">开户账号:</label>
			<div class="span3">
				<form:input path="khzh" htmlEscape="false" maxlength="50"
					style="width:200px" id="khzh" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据提供者:</label>
			<div class="span3">	
			  <form:input path="provider" htmlEscape="false" maxlength="50"
					style="width:200px" id="provider" />			
			</div>
			<label class="control-label">提供时间:</label>
			<div class="span3">
				<input id="providetime" name="providetime"  type="text" readonly="readonly" maxlength="50" class="input-mini Wdate" style="width:180px" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="form-actions" align="center" style="margin-bottom:20px;">			
			<input id="btnCancel1" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)"  />
		</div>	
		<table id="contentTable6" class="table table-striped table-bordered table-condensed">
	 <caption align="left"><span style="font-size:13px;font-family:宋体;font-weight: bold;" >自动机具信息</span></caption>  
		<tr>
		    <th>自动机具序号</th>
		    <th>机具地址</th>
			<th>经度</th>
			<th>纬度</th>
			<th>机具编号</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list5}" var="proResultJjxx">
			<tr>
			    <td>${proResultJjxx.jjxh}</td>
			    <td>${proResultJjxx.jjdz}</td>
				<td>${proResultJjxx.jd}</td>
				<td>${proResultJjxx.wd}</td>
			    <td>${proResultJjxx.jjbh}</td>
			    <td><a
					href="javascript:updateData(6,${proResultJjxx.keyid});">查看详情</a></td>		   
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
                url:"${ctx}/procuratorate/deal/updateZhxx?bdhm="+bdhm+"&keyid="+keyid,
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
                url:"${ctx}/procuratorate/deal/updateJyxx?bdhm="+bdhm+"&keyid="+keyid,
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
                url:"${ctx}/procuratorate/deal/updateBxgxx?bdhm="+bdhm+"&keyid="+keyid,
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
                url:"${ctx}/procuratorate/deal/updatePosxx?bdhm="+bdhm+"&keyid="+keyid,
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
		   if(form==5){
		   $.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/procuratorate/deal/updateDlrzxx?bdhm="+bdhm+"&keyid="+keyid,
                data:$("#inputForm5").serialize(),
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
					ClearForm("inputForm5");
                    $("#inputForm5").formEdit(data);
                      
                }
            }); 
		   }
		   if(form==6){
		   $.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/procuratorate/deal/updateJjxx?bdhm="+bdhm+"&keyid="+keyid,
                data:$("#inputForm6").serialize(),
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
         			ClearForm("inputForm6");
                    $("#inputForm6").formEdit(data);
                }
            }); 
		   }
		 }
		 function changeForm(form){
		     $("#li1").removeClass("active");
		     $("#li2").removeClass("active");
		     $("#li3").removeClass("active");
		     $("#li4").removeClass("active");
		     $("#li5").removeClass("active");
		     $("#li6").removeClass("active");
		     $("#li"+form+"").addClass("active");
		     document.getElementById("inputForm1").style.display = "none";
		     document.getElementById("inputForm2").style.display = "none";
		     document.getElementById("inputForm3").style.display = "none";
		     document.getElementById("inputForm4").style.display = "none";		     
		     document.getElementById("inputForm5").style.display = "none";
		     document.getElementById("inputForm6").style.display = "none";
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
for(var j=0;j<6;j++){ 
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