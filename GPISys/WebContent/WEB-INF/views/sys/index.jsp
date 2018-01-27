<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig("gpi.plat.name")}-${fns:getConfig("gpi.sys.name")}</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

<link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
<script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script>

<style type="text/css">
#main {
	padding: 0;
	margin: 0;
}

#main .container-fluid {
	padding: 0 4px 0 6px;
}

#header {
	margin: 0 0 8px;
	position: static;
}

#header li {
	font-size: 14px;
	_font-size: 12px;
}

#header .brand {
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 26px;
	width: 450px;
	padding-left: 25px;
}

#footer {
	margin: 8px 0 0 0;
	padding: 3px 0 0 0;
	font-size: 11px;
	text-align: center;
	border-top: 2px solid #0663A2;
}

#footer,#footer a {
	color: #999;
}

#left {
	overflow-x: hidden;
	overflow-y: auto;
}

#left .collapse {
	position: static;
}

#userControl>li>a { /*color:#fff;*/
	text-shadow: none;
}

#userControl>li>a:hover,#user #userControl>li.open>a {
	background: transparent;
}

.productName {
	width: 400px;
	font-size: 22;
	color: #333;
}
</style>

<script src="${ctx}/app/sys/index/index.js" type="text/javascript"></script>
</head>
<body>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner" style="background-color: #f1f1f1;">

				<div class="brand" style="width: 650px;">
					<%-- <img src="${ctxStatic}/images/psbc.png" style="height: 38px;width: 38px;margin-bottom: -14;margin-top: -12;"> --%>
					<img src="${ctxStatic}/images/psbclog.png" style="height: 36px;margin: -5px;padding-left: 10px;"> <span>|</span> <span id="productName" class="productName">${fns:getConfig("gpi.sys.name")}</span>
				</div>
				<ul id="userControl" class="nav pull-right">
					<li id="userInfo" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息"><i class="icon-user"></i>&nbsp;您好, ${currentAccount.userName }<span id="notifyNum" class="label label-info hide"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp; 修改密码</a></li>
						</ul></li>

					<li id="helps" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" title="帮助"><i class="icon-fire"></i>&nbsp;帮助<span id="helps" class="label label-info hide"></span></a>
						<ul class="dropdown-menu">
							<li id="helpPage"><a href="${ctx}/sys/question/reload"> <i class="icon-pencil"></i>&nbsp; 操作手册
							</a></li>
							<li><a href="${ctx}/sys/question/form" target="mainFrame"><i class="icon-comments-alt"></i>&nbsp; 问题反馈</a></li>
						</ul></li>
					<li><a href="#" title="退出登录" onclick="out_sys()"><i class="icon-off"></i>退出</a></li>
					<!--href="${ctx}/logout";return confirmx('确认退出系统吗？', this.href) -->
					<li>&nbsp;</li>
				</ul>
			</div>
		</div>
		<div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left">
					<%-- 
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
			<div id="footer" class="row-fluid">Copyright &copy; 2016 甘肃省分行综合管理平台 -中国邮政储蓄银行甘肃省分行</div>
		</div>
	</div>



	<!-- Modal -->
	<div id="out_title" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">退出系统</h3>
		</div>
		<div class="modal-body">
			<h4>您确认要退出系统吗？</h4>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
			<button class="btn btn-primary" id="true_out">确认</button>
		</div>
	</div>

	<script type="text/javascript">
		var leftWidth = 220; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize() {
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : strs[1] < minWidth ? "auto" : "hidden",
				"overflow-y" : strs[0] < minHeight ? "auto" : "hidden"
			});
			mainObj.css("width", strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// <c:if test="${tabmode eq '1'}"> 
			$(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
			wSizeWidth();
		}
		function wSizeWidth() {
			if (!$("#openClose").is(":hidden")) {
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width() - leftWidth - $("#openClose").width() - 5);
			} else {
				$("#right").width("100%");
			}
		}// <c:if test="${tabmode eq '1'}"> 
		function openCloseClickCallBack(b) {
			$.fn.jerichoTab.resize();
		} // </c:if>
	</script>


	<script type="text/javascript">
		$("#helpPage").click(function() {
			window.open("${ctx}/help/help.html");
		});

		function out_sys() {
			$('#out_title').modal('show');
		}
		$("#true_out").click(function() {
			var userAgent = navigator.userAgent;
			if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") != -1) {
				window.location.href = "about:blank";
				window.close();
			} else {
				window.opener = null;
				window.open("", "_self");
				window.close();
			}
		});
	</script>

	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>

</body>
</html>