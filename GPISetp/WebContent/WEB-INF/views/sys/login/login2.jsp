<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig("gpi.plat.name")} 登录</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>


<style type="text/css">
html,body,table {
	background-color: #f5f5f5;
	width: 100%;
	text-align: center;
}

.form-signin-heading {
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 36px;
	margin-bottom: 20px;
	color: #0663a2;
}

.form-signin {
	position: relative;
	text-align: left;
	width: 300px;
	padding: 25px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	
	filter:alpha(Opacity=60);
	-moz-opacity:0.6;
	opacity: 0.6
}

.form-signin .checkbox {
	margin-bottom: 10px;
	color: #0663a2;
}

.form-signin .input-label {
	font-size: 16px;
	line-height: 23px;
	color: #999;
}

.form-signin .input-block-level {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px;
	*width: 283px;
	*padding-bottom: 0;
	_padding: 7px 7px 9px 7px;
}

.form-signin .btn.btn-large {
	font-size: 16px;
}

.form-signin #themeSwitch {
	position: absolute;
	right: 15px;
	bottom: 10px;
}

.form-signin div.validateCode {
	padding-bottom: 15px;
}

.mid {
	vertical-align: middle;
}

.header {
	height: 80px;
	padding-top: 5px;
}

.alert {
	position: relative;
	width: 300px;
	margin: 0 auto;
	*padding-bottom: 0px;
}

label.error {
	background: none;
	width: 270px;
	font-weight: normal;
	color: inherit;
	margin: 0;
}
</style>

</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="header">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
			<button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>

	<h1 class="form-signin-heading">${fns:getConfig("gpi.sys.name")}</h1>
	<form id="loginForm" class="form-signin" action="${ctx}/system_login" method="post">
		<label class="input-label" for="username">登录名</label> 
		<input type="text" id="username" name="username" class="input-block-level required"> 
		
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required">
		
		<input class="btn btn-large btn-primary" type="submit" value="登 录" /> 
		<!--<button id="loginBtn" class="btn btn-large btn-primary">登录</button>-->
	</form>

	<div class="footer">Copyright &copy; 2016 甘肃省分行综合管理平台 -中国邮政储蓄银行甘肃省分行</div>
	<script src="${ctx}/static/supersized/supersized.3.2.7.min.js"></script>
	<script src="${ctx}/static/supersized/supersized-init.js"></script>
	<script src="${ctx}/static/jquery-plugin/jquery.md5.js"></script>
	<script src="${ctx}/app/sys/login/login.js"></script>
</body>
</html>