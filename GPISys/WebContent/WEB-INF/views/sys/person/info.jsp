<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>个人信息</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

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
<form:form id="inputForm" modelAttribute="currentAccount"  class="form-horizontal">
      <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">用户ID:</label>
			   <div class="controls">
                 <form:input  path="userId" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">用户名称:</label>
		   <div class="controls">
                <form:input path="userName" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		   
		   <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">人力资源编号:</label>
			   <div class="controls">
                 <form:input  path="hrNo" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">所属机构:</label>
		   <div class="controls">
                <form:input path="orgNo" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		   
		     <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">业务部门:</label>
			   <div class="controls">
                 <form:input  path="depCode" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">性别:</label>
		   <div class="controls">
                <form:input path="sex" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		   
		   
		     <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">籍贯:</label>
			   <div class="controls">
                 <form:input  path="nativePlace" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">民族:</label>
		   <div class="controls">
                <form:input path="nation" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		
		
		 <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">用工类别:</label>
			   <div class="controls">
                 <form:input  path="userClass" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">工作时间:</label>
		   <div class="controls">
                <form:input path="beginWorkDate" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		   
		   
		    <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">政治面貌:</label>
			   <div class="controls">
                 <form:input  path="politicalStatus" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">学历:</label>
		   <div class="controls">
                <form:input path="stLevel" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		   
		   
		   <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">职务:</label>
			   <div class="controls">
                 <form:input  path="post" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">职级:</label>
		   <div class="controls">
                <form:input path="rank" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>	
		   
		   <div class="row">
         <div class="span6">
			<div class="control-group">
			   <label class="control-label">职称:</label>
			   <div class="controls">
                 <form:input  path="posTitle" readonly="true"/>
			   </div>
		   </div>
		   </div>
		<div class="span6">
		 <div class="control-group">
		   <label class="control-label">岗位:</label>
		   <div class="controls">
                <form:input path="pos" readonly="true" /> 
		   </div>
		   </div>
		    </div>
		   </div>		 
	</form:form>
   
</body>
</html>