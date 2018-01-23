<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig("gpi.plat.name")}-${fns:getConfig("gpi.sys.name")}</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

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
	/* margin: 8px 0 0 0; */
	padding: 3px 0 0 0;
	font-size: 11px;
	text-align: center;
	border-top: 2px solid #0663A2;
	bottom: 1px;
	position: fixed;
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

#userControl>li>a {
	text-shadow: none;
}

#userControl>li>a:hover,#user #userControl>li.open>a {
	background: transparent;
}

.productName {
	width: 400px;
	font-size: 20;
}

.parstyle {
	width: 150px;
	margin-left: 20px;
	margin-top: 20px;
	float: left;
	/* margin-top: 15px; */
}

.sysicon {
	width: 50px;
	height: 50px;
	margin: 0 auto;
}

#dytable {
	　　table-layout: fixed;
}

#dytd {
	　 white-space: nowrap;
	　　overflow: hidden;
	　　text-overflow: ellipsis;
}
</style>

</head>

<body>
	<div id="header" class="navbar navbar-fixed-top">
		<div class="navbar-inner" style="background-color: #f1f1f1;">
			<div class="brand" style="width: 650px;">
				<img src="${ctxStatic}/images/psbclog.png" style="height: 36px;margin: -5px;padding-left: 10px;"> <span>|</span> <span id="productName" class="productName">${fns:getConfig("gpi.sys.name")}</span>
			</div>
			<ul id="userControl" class="nav pull-right">
				<li id="userInfo" class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息"><i class="icon-user"></i>&nbsp;您好, ${currentAccount.userName } &nbsp;<span id="notifyNum" class="label label-info hide"></span></a>
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


				<li><a href="#" onclick="out_sys()" title="退出登录"><i class="icon-off"></i>退出</a></li>
				<li>&nbsp;</li>
			</ul>
		</div>
	</div>

	<div id="main" style="width:100%;overflow:auto;">
		<div class="container-fluid">
			<div id="content" class="row-fluid">
				<div class="span3" id="protalCal1">
					<div style="width:100%;">
						<div class="row" style="margin-left: 0px;">
							<div class="accordion">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/user_info.png" style="width: 18px;height: 18px;">&nbsp;&nbsp;个人信息</a>
									</div>
									<div class="accordion-body collapse in">
										<table class="table table-condensed" style="font-size: 13px;">
											<tr>
												<td rowspan="2"><img src="${ctxStatic}/images/portal_icon/user_icon.png" style="width: 46px;height: 46px;"></td>
												<td>姓名：</td>
												<td>${userinfo.userName}</td>
											</tr>
											<tr>
												<td>人力编号：</td>
												<td>${userinfo.hrNo}</td>
											</tr>
											<tr>
												<td>联系电话：</td>
												<td>${userinfo.mobile}</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>所属机构：</td>
												<td>${userinfo.orgName}</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td>所在岗位：</td>
												<td>${fns:getDicLabel(userinfo.pos,"DIC_USER_POSITION", "")}</td>
												<td>&nbsp;</td>
											</tr>

										</table>
									</div>
								</div>
							</div>
						</div>

						<div class="row" style="margin-left: 0px;">
							<div class="accordion">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/calendar.png" style="width: 18px;height: 18px;margin-bottom: 2px;">&nbsp;&nbsp;日历</a>
									</div>
									<div class="accordion-body collapse in">
										<iframe src="${ctx}/echarts/calendar" width="100%" height="310px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
									</div>
								</div>
							</div>
						</div>


						<div class="row" style="margin-left: 0px;margin-bottom: 20px;">
							<div class="accordion" id="Portlet_1">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/active.png" style="width: 18px;height: 18px;">平台应用活跃度分析</a>
									</div>
									<div class="accordion-body collapse in">
										<iframe src="${ctx}/echarts/countLogin" width="100%" height="300" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>


				<div class="span9">
					<div class="row">
						<div class="span6">
							<div class="accordion">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/bulletin.png" style="width: 18px;height: 18px;">&nbsp;&nbsp;公告<i class="icon-refresh pull-right"></i></a>
									</div>
									<div class="accordion-body collapse in">
										<div class="accordion-inner">
											<div style="width:100%;float:left; overflow:auto;height: 180px;">
												<table class="table table-condensed" style="font-size: 13px;">
													<tr>
														<td>标题</td>
														<td>时间</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="span6">
							<div class="accordion">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/backlog.png" style="width: 18px;height: 18px;margin-bottom: 2px;">&nbsp;&nbsp;待办事项<i class="icon-refresh pull-right"></i></a>
									</div>
									<div class="accordion-body collapse in">
										<div class="accordion-inner">
											<div style="width:100%;float:left; overflow:auto;height: 180px;">
												<table class="table table-condensed" style="font-size: 13px;">
													<tr>
														<td>事项名称</td>
														<td>时间</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- ------------------------------------------------------ -->
					<div class="row">
						<div class="accordion">
							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1; "><img alt="" src="${ctxStatic}/images/title_icon/app.png" style="width: 18px;height: 18px;margin-bottom: 2px;">&nbsp;应用系统</a>
								</div>

								<div class="accordion-body collapse in">
									<div class="accordion-inner">
										<div class="row">
											<div class="accordion" id="Portlet_1">

												<c:forEach items="${list}" var="list">
													<center>
														<div class="parstyle">
															<c:set var="imgSrc" scope="page" value="${list.hasRole}" />
															<c:if test="${imgSrc > 0}">

																<c:set var="imgSrcStatus" scope="page" value="${list.status}" />
																<c:if test="${imgSrcStatus == 2}">
																	<a title="${list.remark}"> <img alt="" src="${ctxStatic}/images/portal_icon/<c:out value="${list.disIcon}" />" class="sysicon">
																	</a>
																</c:if>
																<c:if test="${imgSrcStatus != 2}">
																	<a title="${list.remark}" target="_blank" href="${list.accUrl}/<%=session.getAttribute("username")%>/<%=session.getAttribute("userToken")%>/?url=${list.openPage}"> <img alt="" src="${ctxStatic}/images/portal_icon/<c:out value="${list.icon}" />" class="sysicon">
																	</a>
																</c:if>

															</c:if>
															<c:if test="${imgSrc == 0}">
																<img alt="" src="${ctxStatic}/images/portal_icon/<c:out value="${list.disIcon}" />" class="sysicon">
															</c:if>
															<p>${list.appName}</p>
														</div>
													</center>
												</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="span4" id="protalCal2">
							<div class="accordion" id="Portlet_2">
								<div class="accordion-group">
									<div class="accordion-heading"background-color: #F0F0F1;>
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: #F0F0F1;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;"><img alt="" src="${ctxStatic}/images/title_icon/data1_icon.png" style="width: 18px;height: 18px;margin-bottom: 2px;">&nbsp;甘肃金融机构存款占比(单位：万元)<i
											class="icon-refresh pull-right"></i></a>
									</div>

									<div class="accordion-body collapse in">
										<div class="accordion-inner">
											<div style="width:100%;float:left; overflow:auto;">
												<iframe src="${ctx}/echarts/data/data1" width="100%" height="350px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="span8" id="protalCal2">
							<div class="accordion" id="Portlet_2">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/data2_icon.png" style="width: 18px;height: 18px;margin-bottom: 2px;">&nbsp;&nbsp;甘肃省金融机构境内存款趋势分析(单位：亿元)<i class="icon-refresh pull-right"
											onclick="refreshTree();"></i></a>
									</div>
									<div class="accordion-body collapse in">
										<div class="accordion-inner">
											<div style="width:100%;float:left; overflow:auto;height: 350px;">
												<iframe src="${ctx}/echarts/data/data2" width="100%" height="350px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="span12">
							<div class="accordion">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: F0F0F1;"><img alt="" src="${ctxStatic}/images/title_icon/map_icon.png" style="width: 18px;height: 18px;">甘肃省分行信贷担保关系总数<i class="icon-refresh pull-right"></i></a>
									</div>
									<div class="accordion-body collapse in">
										<div class="accordion-inner">
											<iframe src="${ctx}/echarts/gsMap" width="100%" height="500px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="footer" class="row-fluid">Copyright &copy; 2016 甘肃省分行综合管理平台 -中国邮政储蓄银行甘肃省分行</div>

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
		var leftWidth = 180; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		//var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize() {
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : strs[1] < minWidth ? "auto" : "hidden",
				"overflow-y" : strs[0] < minHeight ? "auto" : "hidden"
			});
			mainObj.css("width", strs[1] < minWidth ? minWidth - 10 : "auto");
			mainObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));

			//当页面改变时，重新加载日历
			CalendarHandler.initialize();

			//wSizeWidth();
		}
		function wSizeWidth() {
			if (!$("#openClose").is(":hidden")) {
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width() - leftWidth - $("#openClose").width() - 5);
			} else {
				$("#right").width("100%");
			}
		}
		wSize();
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