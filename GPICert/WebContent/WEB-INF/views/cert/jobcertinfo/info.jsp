<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详情</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>


<script type="text/javascript">
	function check() {
		$("#searchForm").submit();
	}
</script>



</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cert/jobcertinfo/list">岗位持证情况详情</a></li>
		<li class="active"><a>详情</a></li>
	</ul>

	<table class="table table-bordered table-hover">

		<c:forEach items="${list}" var="certJobCfg" begin="0" end="0">
			<tr>
				<td><h4>姓名：${certJobCfg.userName}</h4></td>
				<td><h4>当前岗位：${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</h4></td>
				<td><h4>所在机构：${certJobCfg.orgName}</h4></td>
			</tr>
		</c:forEach>
	</table>

	<c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.holdReq eq '1'}">
			<c:set var="hold1" scope="page" value="1" />
		</c:if>
	</c:forEach>
	<c:if test="${hold1 eq 1}">

		<h6 style="margin: 10px 15px;">要求持有：</h6>
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<tr>
				<!-- <th>持有要求</th> -->
				<th style="width: 6%;">证书编号</th>
				<th style="width: 40%;">证书名称</th>
				<th style="width: 6%;">资格分类</th>
				<th style="width: 15%;">行业分类</th>
				<th style="width: 6%;">持证情况</th>
				<th style="width: 15%">证书编号</th>
				<th style="width: 12%">发证日期</th>
			</tr>

			<c:forEach items="${list}" var="certJobCfg">
				<c:if test="${certJobCfg.holdReq eq '1'}">
					<c:if test="${certJobCfg.status == null}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<tr>
					</c:if>
					<%-- <td>${fns:getDicLabel(certJobCfg.holdReq,"DIC_CERT_HOLD", "")}</td> --%>
					<td>${certJobCfg.certCode}</td>
					<td>${certJobCfg.certName}</td>
					<td>${fns:getDicLabel(certJobCfg.admClass,"DIC_CERT_CLASS", "")}</td>
					<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
					<c:if test="${certJobCfg.status == null}">
						<td>未持有</td>
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<td>无效</td>
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<td>已过期</td>
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<td>待审核</td>
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<td>${fns:getDicLabel(certJobCfg.status,"DIC_CERT_STATUS", "")}</td>
					</c:if>
					<td>${certJobCfg.certNo}</td>
					<td>${certJobCfg.issueDate}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>

	<!-- ************************************** -->
	<%-- <c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.status == 1}">
			<c:set var="status2" scope="page" value="1" />
		</c:if>
	</c:forEach>
	<c:if test="${status2 == 1}">
		<h6 style="margin: 10px 15px;">已持有：</h6>
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<tr>
				<!-- <th>持有要求</th> -->
				<th style="width: 6%;">证书编号</th>
				<th style="width: 40%;">证书名称</th>
				<th style="width: 6%;">资格分类</th>
				<th style="width: 15%;">行业分类</th>
				<th style="width: 6%;">持证情况</th>
				<th style="width: 15%">证书编号</th>
				<th style="width: 12%">发证日期</th>
			</tr>
			<c:forEach items="${list}" var="certJobCfg">
				<c:if test="${certJobCfg.status == 1}">
					<tr>
						<td>${fns:getDicLabel(certJobCfg.holdReq,"DIC_CERT_HOLD", "")}</td>
						<td>${certJobCfg.certCode}</td>
						<td>${certJobCfg.certName}</td>
						<td>${fns:getDicLabel(certJobCfg.admClass,"DIC_CERT_CLASS", "")}</td>
						<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
						<c:if test="${certJobCfg.status == null}">
							<td>未持有</td>
						</c:if>
						<c:if test="${certJobCfg.status == '4'}">
							<td>无效</td>
						</c:if>
						<c:if test="${certJobCfg.status == '3'}">
							<td>已过期</td>
						</c:if>
						<c:if test="${certJobCfg.status == '2'}">
							<td>待审核</td>
						</c:if>
						<c:if test="${certJobCfg.status == '1'}">
							<td>${fns:getDicLabel(certJobCfg.status,"DIC_CERT_STATUS", "")}</td>
						</c:if>
						<td>${certJobCfg.certNo}</td>
						<td>${certJobCfg.issueDate}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>

	<!-- ************************************** -->
	<h6 style="margin: 10px 15px;">未持有：</h6>
	<table id="contentTable" class="table table-bordered table-condensed table-hover">
		<tr>
			<!-- <th>持有要求</th> -->
			<th style="width: 6%;">证书编号</th>
			<th style="width: 40%;">证书名称</th>
			<th style="width: 6%;">资格分类</th>
			<th style="width: 15%;">行业分类</th>
			<th style="width: 6%;">持证情况</th>
			<th style="width: 15%">证书编号</th>
			<th style="width: 12%">发证日期</th>
		</tr>
		<c:forEach items="${list}" var="certJobCfg">
			<c:if test="${empty certJobCfg.status || certJobCfg.status > 2}">
				<tr>
					<td>${fns:getDicLabel(certJobCfg.holdReq,"DIC_CERT_HOLD", "")}</td>
					<td>${certJobCfg.certCode}</td>
					<td>${certJobCfg.certName}</td>
					<td>${fns:getDicLabel(certJobCfg.admClass,"DIC_CERT_CLASS", "")}</td>
					<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
					<c:if test="${certJobCfg.status == null}">
						<td>未持有</td>
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<td>无效</td>
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<td>已过期</td>
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<td>待审核</td>
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<td>${fns:getDicLabel(certJobCfg.status,"DIC_CERT_STATUS", "")}</td>
					</c:if>
					<td>${certJobCfg.certNo}</td>
					<td>${certJobCfg.issueDate}</td>
				</tr>
			</c:if>
		</c:forEach>
	</table> --%>









	<c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.holdReq == '2'}">
			<c:set var="hold2" scope="page" value="2" />
		</c:if>
	</c:forEach>
	<c:if test="${hold2 == 2}">
		<h6 style="margin: 10px 15px;">自选持有：</h6>
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<tr>
				<!-- <th>持有要求</th> -->
				<th style="width: 6%;">证书编号</th>
				<th style="width: 40%;">证书名称</th>
				<th style="width: 6%;">资格分类</th>
				<th style="width: 15%;">行业分类</th>
				<th style="width: 6%;">持证情况</th>
				<th style="width: 15%">证书编号</th>
				<th style="width: 12%">发证日期</th>
			</tr>
			<c:forEach items="${list}" var="certJobCfg" varStatus="status">
				<c:if test="${certJobCfg.holdReq == '2'}">
					<c:if test="${certJobCfg.status == null}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<tr>
					</c:if>
					<%-- <td>${fns:getDicLabel(certJobCfg.holdReq,"DIC_CERT_HOLD", "")}</td> --%>
					<td>${certJobCfg.certCode}</td>
					<td>${certJobCfg.certName}</td>
					<td>${fns:getDicLabel(certJobCfg.admClass,"DIC_CERT_CLASS", "")}</td>
					<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
					<c:if test="${certJobCfg.status == null}">
						<td>未持有</td>
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<td>无效</td>
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<td>已过期</td>
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<td>待审核</td>
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<td>${fns:getDicLabel(certJobCfg.status,"DIC_CERT_STATUS", "")}</td>
					</c:if>
					<td>${certJobCfg.certNo}</td>
					<td>${certJobCfg.issueDate}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>

	<c:forEach items="${list}" var="certJobCfg">
		<c:if test="${certJobCfg.holdReq == '3'}">
			<c:set var="hold3" scope="page" value="3" />
		</c:if>
	</c:forEach>
	<c:if test="${hold3 == 3}">
		<h6 style="margin: 10px 15px;">鼓励持有：</h6>
		<table id="contentTable" class="table table-bordered table-condensed table-hover">
			<tr>
				<!-- <th>持有要求</th> -->
				<th style="width: 6%;">证书编号</th>
				<th style="width: 40%;">证书名称</th>
				<th style="width: 6%;">资格分类</th>
				<th style="width: 15%;">行业分类</th>
				<th style="width: 6%;">持证情况</th>
				<th style="width: 15%">证书编号</th>
				<th style="width: 12%">发证日期</th>
			</tr>
			<c:forEach items="${list}" var="certJobCfg" varStatus="status">
				<c:if test="${certJobCfg.holdReq == '3'}">
					<c:if test="${certJobCfg.status == null}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<tr class="warning">
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<tr>
					</c:if>
					<%-- <td>${fns:getDicLabel(certJobCfg.holdReq,"DIC_CERT_HOLD", "")}</td> --%>
					<td>${certJobCfg.certCode}</td>
					<td>${certJobCfg.certName}</td>
					<td>${fns:getDicLabel(certJobCfg.admClass,"DIC_CERT_CLASS", "")}</td>
					<td>${fns:getDicLabel(certJobCfg.posCode,"DIC_USER_POSITION", "")}</td>
					<c:if test="${certJobCfg.status == null}">
						<td>未持有</td>
					</c:if>
					<c:if test="${certJobCfg.status == '4'}">
						<td>无效</td>
					</c:if>
					<c:if test="${certJobCfg.status == '3'}">
						<td>已过期</td>
					</c:if>
					<c:if test="${certJobCfg.status == '2'}">
						<td>待审核</td>
					</c:if>
					<c:if test="${certJobCfg.status == '1'}">
						<td>${fns:getDicLabel(certJobCfg.status,"DIC_CERT_STATUS", "")}</td>
					</c:if>
					<td>${certJobCfg.certNo}</td>
					<td>${certJobCfg.issueDate}</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>











</body>
</html>