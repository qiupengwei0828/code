<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位持证添加</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	/*
	 * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#pageForm").submit();
		return false;
	}

	var posV = parent.posCodeV;
	var holdV = parent.holdReq;

	function execute() {
		if (holdV != "" && posV != "") {
			window.location.href = "${ctx}/cert/certjobcfg/insert?posCode=" + posV + "&certCode=" + certIdV + "&holdReq=" + holdV;
		} else {
			$("#ErrorMessage").modal("show");
		}
	}

	function check() {
		var posV = $.trim($("#posCodeV").val());
		$("#searchForm").submit();
	}
	$().ready(function() {
		$("#showCert").mouseup(function() {
			if (holdV == "") {
				$("#ErrorMessage").modal("show");
				$("#showCert").unbind("click");
			}
		});
	});
</script>


</head>
<body>

	<form:form id="pageForm" modelAttribute="certJobCfg" action="${ctx}/cert/certjobcfg/hold_list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>

	<sys:certSelect id="certCode" name="certCode" value="添加证书" />

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<th style="display: none;">ID</th>
			<th>岗位</th>
			<th>证书名称</th>
			<th>证书编码</th>
			<th>持有要求</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="certJobCfg">
			<tr>
				<td style="display: none;">${certJobCfg.id}</td>
				<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
				<td>${certJobCfg.certName}</td>
				<td>${certJobCfg.certCode}</td>
				<td>${fns:getDicLabel(certJobCfg.holdReq,"DIC_CERT_HOLD", "")}</td>
				<td><a href="${ctx}/cert/certjobcfg/delete?id=${certJobCfg.id}&posCode=${certJobCfg.posCode}&holdReq=${certJobCfg.holdReq}" onclick="return confirmx('确认要删除该配置吗？', this.href)">移除</a></td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">${page}</div>


	<div id=ErrorMessage class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<span><a style="text-decoration: none;"><img alt="" src="${ctxStatic}/images/alert_icon.png" style="float: left;width: 30px;height: 30px;">
					<h3 id="myModalLabel">错误提示</h3></a></span>
		</div>
		<div class="modal-body">
			<p>
			<h4>未选择持有要求</h4>
			</br>
			<h5>请先选择持有要求后再添加！</h5>

			</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>





</body>
</html>