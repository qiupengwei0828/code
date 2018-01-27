$(document).ready(function() {
	// 初始化页签
	$.fn.initJerichoTab({
		renderTo : '#right',
		uniqueId : 'jerichotab',
		contentCss : {
			'height' : $('#right').height() - tabTitleHeight
		},
		tabs : [],
		loadOnce : true,
		tabWidth : 110,
		titleHeight : tabTitleHeight
	});//

	// 加载菜单
	function loadLeftMenu() {
		// 左侧区域显示
		$("#left,#openClose").show();
		if (!$("#openClose").hasClass("close")) {
			$("#openClose").click();
		}

		// 一级菜单ID
		var menuId = "#menu-0";

		// 获取菜单数据
		$.get(ctx + "/sys/menu/getLeftMenu?parentId=0", function(data) {
			if (data.indexOf("id=\"loginForm\"") != -1) {
				alert('未登录或登录超时。请重新登录，谢谢！');
				top.location = ctx;
				return false;
			}
			$("#left .accordion").hide();
			$("#left").append(data);

			// 链接去掉虚框
			$(menuId + " a").bind("focus", function() {
				if (this.blur) {
					this.blur()
				}
				;
			});

			// 二级标题
			$(menuId + " .accordion-heading a").click(function() {
				$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
				if (!$($(this).attr('data-href')).hasClass('in')) {
					$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
				}
			});
			// 二级内容
			$(menuId + " .accordion-body a").click(function() {
				$(menuId + " li").removeClass("active");
				$(menuId + " li i").removeClass("icon-white");
				$(this).parent().addClass("active");
				$(this).children("i").addClass("icon-white");
			});
			// 展现三级
			$(menuId + " .accordion-inner a").click(function() {
				var href = $(this).attr("data-href");
				if ($(href).length > 0) {
					$(href).toggle().parent().toggle();
					return false;
				}
				// 
				return addTab($(this)); //
			});
			// 默认选中第一个菜单
			$(menuId + " .accordion-body a:first i").click();
			$(menuId + " .accordion-body li:first li:first a:first i").click();
		});
		// 大小宽度调整
		wSizeWidth();
	}

	// 下拉菜单以选项卡方式打开
	$("#userInfo .dropdown-menu a").mouseup(function() {
		return addTab($(this), true);
	});//

	// 下拉菜单以选项卡方式打开
	$("#helps .dropdown-menu a").mouseup(function() {
		return addTab($(this), true);
	});//

	// 鼠标移动到边界自动弹出左侧菜单
	$("#openClose").mouseover(function() {
		if ($(this).hasClass("open")) {
			$(this).click();
		}
	});

	// 获取通知数目 <c:set var="oaNotifyRemindInterval"
	// value="${fns:getConfig('oa.notify.remind.interval')}"/>
	function getNotifyNum() {
		$.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t=" + new Date().getTime(), function(data) {
			var num = parseFloat(data);
			if (num > 0) {
				$("#notifyNum,#notifyNum2").show().html("(" + num + ")");
			} else {
				$("#notifyNum,#notifyNum2").hide()
			}
		});
	}

	// getNotifyNum(); // <c:if test="${oaNotifyRemindInterval ne '' &&
	// oaNotifyRemindInterval ne '0'}">
	// setInterval(getNotifyNum, ${oaNotifyRemindInterval}); // </c:if>

	function addTab($this, refresh) {
		$(".jericho_tab").show();
		$("#mainFrame").hide();
		$.fn.jerichoTab.addTab({
			tabFirer : $this,
			title : $this.text(),
			closeable : true,
			data : {
				dataType : 'iframe',
				dataLink : $this.attr('href')
			}
		}).loadData(refresh);
		return false;
	}

	// 初始化菜单
	loadLeftMenu();
});
