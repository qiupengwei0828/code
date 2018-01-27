<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>

<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig("gpi.plat.name")}登录</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<link rel="stylesheet" href="${ctx}/static/supersized/supersized.css">

<style type="text/css">
html,body,table {
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
	/* position: relative; */
	text-align: left;
	width: 320px;
	padding: 25px 10px;
	margin: 0 auto 10px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
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
	width: 100%;
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

/*垂直居中*/
.Vcenter {
	height: 50%;
	width: 50%;
	position: absolute;
	margin: auto;
	left: 0;
	right: 0;
	bottom: 0;
	top: 0;
}

/* Sticky footer styles
/*-------------------------------------------------- */
#footer {
	height: 60px;
	background-color: #E9E9E9;
	width: 100%;
	position: fixed;
	bottom: 0px;
	left: 0px;
}

.bottom_strict {
	width: 100%;
	position: fixed;
	bottom: 0px;
	left: 0px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		(function chg() {
			var heg = $(document.body).height() - 140;
			document.getElementById("centent").style.height = heg;
		})();
	});
</script>

</head>

<body style="overflow: hidden;">
	<!-- <body style="background-image: url(/GPISys/static/images/login/22.png);background-repeat: inherit;"> -->

	<div style="height: 80px;width: 100%;position: fixed; left: 0px; background-image: url(${ctxStatic}/static/images/login/head-bg.png);z-index: 2;">
		<div style="float: left;margin: auto;position: absolute;left: 10px;top: 10px">
			<img alt="中国邮政储蓄银行" src="${ctxStatic}/images/LOGO.png" style="float: left;">
		</div>

		<!-- 登陆错误提示 -->
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
			<button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div>
	</div>

	<div class="Vcenter" style="float: right;margin-right: 10px; z-index: 9;">
		<form id="loginForm" class="form-signin" action="${ctx}/system_login" method="post">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="row-fluid">
							<div class="span12">
								<h1 class="form-signin-heading" style="text-align: center;">
									<font style="color: #111;font-size: 24px;font-family: Microsoft YaHei;font-weight: normal;margin: auto -20px;">${fns:getConfig("gpi.sys.name")} </font>
								</h1>
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<br>
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="span2" style="padding: 7px 0;">
										<label><font style="font-size: 14;font-family: Microsoft YaHei;"><b>账号:</b></font></label>
									</div>
									<div class="span10">
										<input type="text" id="username" name="username" class="input-block-level required">
									</div>
								</div>
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<br>
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<div class="row-fluid">
									<div class="span2" style="padding: 7px 0;">
										<label><font style="font-size: 14;font-family: Microsoft YaHei;"><b>密码:</b></font></label>
									</div>
									<div class="span10">
										<input type="password" id="password" name="password" class="input-block-level required">
									</div>
								</div>
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<br>
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<input class="btn btn-large btn-primary" type="submit" value="登 录" style="width: 100%;font-family: Microsoft YaHei;" />
							</div>
						</div>

						<div class="row-fluid">
							<div class="span12">
								<br>
							</div>
						</div>

					</div>
				</div>
			</div>

		</form>

	</div>
	<div id="centent" style="background-image: url(${ctxStatic}/static/images/login/bg.jpg);position:relative;z-index:1;top: 80px;background-size:cover; "></div>


	<div id="footer" style="background-image: url(${ctxStatic}/static/images/login/head-bg.png);z-index: 1;">
		<div class="container bottom_strict" style="font-family: Microsoft YaHei;">
			Copyright &copy; 2016 中国邮政储蓄银行甘肃省分行 <br> 本系统支持 IE8+、谷歌浏览器等。推荐使用谷歌浏览器。
		</div>
	</div>




</body>
</html>