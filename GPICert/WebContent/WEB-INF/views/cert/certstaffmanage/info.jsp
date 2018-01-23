<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>证书审核</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>



<!--[if gte IE 9]>
<%-- <link href="${ctxStatic}/jquery-powerful-album-view/css/normalize.css" type="text/css" rel="stylesheet" /> --%>
<%-- <link href="${ctxStatic}/jquery-powerful-album-view/css/default.css" type="text/css" rel="stylesheet" /> --%>
<link href="${ctxStatic}/jquery-powerful-album-view/css/view_main.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/jquery-powerful-album-view/dist/viewer.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-powerful-album-view/dist/viewer.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-powerful-album-view/assets/js/view_main.js" type="text/javascript"></script>
<![endif]-->




<!--[if !IE 8]><!-->
<%-- <link href="${ctxStatic}/jquery-powerful-album-view/css/normalize.css" type="text/css" rel="stylesheet" /> --%>
<%-- <link href="${ctxStatic}/jquery-powerful-album-view/css/default.css" type="text/css" rel="stylesheet" /> --%>
<link href="${ctxStatic}/jquery-powerful-album-view/css/view_main.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/jquery-powerful-album-view/dist/viewer.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-powerful-album-view/dist/viewer.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-powerful-album-view/assets/js/view_main.js" type="text/javascript"></script>
<!--<![endif]-->

<!--[if IE 8]>
<link href="${ctxStatic}/jquery-powerful-album-view/css/view_main.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/jquery-powerful-album-view/dist/viewer.css" type="text/css" rel="stylesheet" />
<style type="text/css">
img {
	height: 200px;
	width: 300px;
}
li {
	list-style: none;
}
</style>
		
<script type="text/javascript">
	$(window).load(function() {
		$("a").click(function() {
			var path = $(this).children("img").attr("src");
			window.open(path);
		});
	});
</script>
<![endif]-->




<script type="text/javascript">
	$(document).ready(function() {
		$("input").attr("readonly", "readonly");
		$("textarea").attr("readonly", "readonly");
	});
</script>

</head>
<body>

	<form:form class="form-horizontal" enctype="multipart/form-data">
		<%-- <c:forEach items="${list}" var="holdInfo"> --%>
		<input id="orgNo" type="hidden" value="${holdInfo.orgNo}">
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<input id="userName" type="text" name="userName" value="${holdInfo.userName}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">当前岗位：</label>
			<div class="controls">
				<input id="pos" type="text" name="pos" value="${fns:getDicLabel(holdInfo.pos,'DIC_USER_POSITION', '')}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">资格分类：</label>
			<div class="controls">
				<input id="admClass" type="text" name="admClass" value="${fns:getDicLabel(holdInfo.admClass,'DIC_CERT_CLASS', '')}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">行业分类：</label>
			<div class="controls">
				<input id="industry" type="text" name="industry" value="${fns:getDicLabel(holdInfo.industry,'DIC_CERT_INDUSTRY', '')}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">证书名称：</label>
			<div class="controls">
				<input id="certName" type="text" name="certName" value="${holdInfo.certName}" style="width: 255px;" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">证书编号：</label>
			<div class="controls">
				<input id="certCode" type="text" name="certCode" value="${holdInfo.certCode}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">发证日期：</label>
			<div class="controls">
				<input id="issueDate" type="text" name="issueDate" value="${holdInfo.issueDate}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">发证机构：</label>
			<div class="controls">
				<input id="issueOrg" type="text" name="issueOrg" value="${holdInfo.issueOrg}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">等级：</label>
			<div class="controls">
				<input id="certLevel" type="text" name="certLevel" value="${holdInfo.certLevel}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">评定成绩：</label>
			<div class="controls">
				<input id="certScore" type="text" name="certScore" value="${holdInfo.certScore}" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">证书扫描件：</label>
			<div class="controls">


				<div class="col-sm-8 col-md-6">
					<div class="docs-galley">
						<ul class="docs-pictures clearfix">
							<c:forEach items="${imglist}" var="imglist">
								<li><a><img data-original="/certfilepath/${imglist.fileSaveName}" src="/certfilepath/${imglist.fileSaveName}"></a></li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</div>

		</div>

		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<textarea rows="4" style="width: 290px;">${holdInfo.remark}</textarea>
			</div>
		</div>
		<%-- </c:forEach> --%>

		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>

	</form:form>


</body>
</html>