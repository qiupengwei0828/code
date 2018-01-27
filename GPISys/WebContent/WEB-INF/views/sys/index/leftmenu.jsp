<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%--
<html>
<head>
	<title>菜单导航</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".accordion-heading a").click(function(){
				$('.accordion-toggle i').removeClass('icon-chevron-down');
				$('.accordion-toggle i').addClass('icon-chevron-right');
				if(!$($(this).attr('href')).hasClass('in')){
					$(this).children('i').removeClass('icon-chevron-right');
					$(this).children('i').addClass('icon-chevron-down');
				}
			});
			$(".accordion-body a").click(function(){
				$("#menu-${param.parentId} li").removeClass("active");
				$("#menu-${param.parentId} li i").removeClass("icon-white");
				$(this).parent().addClass("active");
				$(this).children("i").addClass("icon-white");
				//loading('正在执行，请稍等...');
			});
			//$(".accordion-body a:first i").click();
			//$(".accordion-body li:first li:first a:first i").click();
		});
	</script>
</head>
<body> --%>
	<div class="accordion" id="menu-0">
	<c:set var="menuList" value="${menuList}"/>
	<c:set var="firstMenu" value="true"/>
	
	<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
	    <c:if test="${menu.pMenuId eq '1'}">
		<div class="accordion-group" >
		    <div class="accordion-heading">
		    	<a class="accordion-toggle"  data-toggle="collapse" data-parent="#menu-0" data-href="#collapse-${menu.menuId}" href="#collapse-${menu.menuId}" title="${menu.tip}" style="color:#555;">
		    	    <i class="${menu.icon}"></i>&nbsp;${menu.menuName}</a>
		    </div>
		    <div id="collapse-${menu.menuId}" class="accordion-body collapse ${not empty firstMenu && firstMenu ? 'in' : ''}">
				<div class="accordion-inner">
					<ul class="nav nav-list">
					
					<c:forEach items="${menuList}" var="menu2">
					   <c:if test="${menu2.pMenuId eq menu.menuId}">
						<li><a data-href=".menu3-${menu2.menuId}" href="${fn:indexOf(menu2.actUrl, '://') eq -1 ? ctx : ''}${not empty menu2.actUrl ? menu2.actUrl : '/404'}" target="mainFrame" ><i class="${menu2.icon}"/>&nbsp;${menu2.menuName} </a>
							<ul class="nav nav-list hide" style="margin:0;padding-right:0; padding-left:25px">
							
							<c:forEach items="${menuList}" var="menu3">
							    <c:if test="${menu3.pMenuId eq menu2.menuId}">
								<li class="menu3-${menu2.menuId}  hide"><a href="${fn:indexOf(menu3.actUrl, '://') eq -1 ? ctx : ''}${not empty menu3.actUrl ? menu3.actUrl : '/404'}" target="mainFrame" ><i class="${menu3.icon}"/>&nbsp;${menu3.menuName}</a></li>
								</c:if>
							</c:forEach></ul></li>
						<c:set var="firstMenu" value="false"/></c:if>
					</c:forEach>
					</ul>
				</div>
		    </div>
		</div>
		</c:if>
	</c:forEach></div><%--
</body>
</html> --%>