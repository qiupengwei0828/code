<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<!-- <!DOCTYPE> -->
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
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
		$("select").attr("readonly", "readonly");
		$("#remark").removeAttr("readonly");
		$("#remarkV").removeAttr("readonly");
		$("#status").removeAttr("readonly");

		$("#pass").click(function() {
			$("#status").attr("value", "1");//已认证
			var mymessage = confirm("确认审核该证书通过吗?");
			if (mymessage == true) {
				$("#inputForm").submit();
			} else {
				return false;
			}
		});
		$("#nopass").click(function() {
			$("#status").attr("value", "2");//待审核
			var mymessage = confirm("确认审核该证书不通过吗?");
			if (mymessage == true) {
				$("#inputForm").submit();
			} else {
				return false;
			}
		});
		$("#past").click(function() {
			$("#status").attr("value", "3");//已过期
			var mymessage = confirm("确认审核该证书已过期吗?");
			if (mymessage == true) {
				$("#inputForm").submit();
			} else {
				return false;
			}
		});
		$("#nullity").click(function() {
			$("#status").attr("value", "4");//无效
			var mymessage = confirm("确认审核该证书无效吗?");
			if (mymessage == true) {
				$("#inputForm").submit();
			} else {
				return false;
			}
		});
	});
</script>


</head>
<body>

	<form:form id="inputForm" modelAttribute="holdInfo" action="${ctx}/cert/holdinfo/pass" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="control-group">
			<div class="controls">
				<form:input path="id" htmlEscape="false" maxlength="50" type="hidden" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资格分类</label>
			<div class="controls">
				<form:select path="admClass" class="input-medium" style="width:270px">
					<form:options items="${fns:getDicList('DIC_CERT_CLASS')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行业分类</label>
			<div class="controls">
				<form:select path="industry" class="input-medium" style="width:270px">
					<form:options items="${fns:getDicList('DIC_CERT_INDUSTRY')}" itemLabel="pName" itemValue="pValue" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书名称</label>
			<div class="controls">
				<form:input path="certName" htmlEscape="false" maxlength="50" type="text" style="width:255px" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书编号</label>
			<div class="controls">
				<form:input path="certNo" htmlEscape="false" maxlength="50" type="text" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">发证日期</label>
			<div class="controls">
				<form:input path="issueDate" htmlEscape="false" maxlength="50" type="text" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">发证机构</label>
			<div class="controls">
				<form:input path="issueOrg" htmlEscape="false" maxlength="50" type="text" style="width:255px" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">证书等级</label>
			<div class="controls">
				<form:input path="certLevel" htmlEscape="false" maxlength="50" type="text" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">评定成绩</label>
			<div class="controls">
				<form:input path="certScore" htmlEscape="false" maxlength="50" type="text" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">证书扫描件</label>
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


		<!-- 证书审核通过 -->
		<form:input path="status" id="status" htmlEscape="false" maxlength="50" type="hidden" />

		<div class="control-group">
			<label class="control-label">审核意见</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="5" maxlength="200" class="input-xlarge" style="width:300px;" />
			</div>
		</div>

		<div class="form-actions">
			<input id="pass" class="btn" type="button" value="通过" style="background-color: blue;color:white"> <input class="btn" id="nullity" type="button" value="无效" style="background-color: blue;color:white"> <input class="btn" type="button" value="返回" onclick="history.go(-1)" />
		</div>

	</form:form>

</body>
</html>