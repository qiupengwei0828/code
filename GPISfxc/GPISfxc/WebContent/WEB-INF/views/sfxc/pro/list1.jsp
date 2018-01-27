<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
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
			<input id="btnSubmitstatus" class="btn btn-primary" type="button" value="保存" onclick="onclickSubmit(1)" /> 
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
					href="javascript:updateData(1);">修改</a></td>		   
			</tr>
		</c:forEach>
	</table>
	</form:form>
</body>
</html>
