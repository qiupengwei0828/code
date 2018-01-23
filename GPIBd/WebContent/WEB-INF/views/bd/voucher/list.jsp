<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购入列表</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctx}/js/autocomplete/autocomplete.js" type="text/javascript"></script>
<link href="${ctx}/js/autocomplete/jquery.autocomplete.css" rel="stylesheet" />
</head>
  <script type="text/javascript">
   
/*
   * 分页
	 */
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
		}
		function updateData(id,planNum){
		var purNum = parseInt(document.getElementById(id).value);
		if(purNum>planNum){
		showTips( '实际购入量不能大于请领量！', 300, 300,4 );
		}else{
		var url = "${ctx}/bd/voucher/update?id="+id+"&purNum="+purNum;
		window.location.href=url;
		}
	}
	function showTips(tips, height, sizeWidth, time) {
    var windowWidth = document.documentElement.clientWidth;
    var tipsDiv = '<div class="tipsClass">' + tips + '</div>';
    $('body').append(tipsDiv);
    $('div.tipsClass').css({
        'top': '200px',
        'right': 450+ 'px',
        'position': 'absolute',
        'padding': '3px 5px',
        'background': '#8FBC8F',
        'font-size': 12 + 'px',
        'margin': '0 auto',
        'text-align': 'center',
        'width': sizeWidth + 'px',
        'color': '#fff',
        'opacity': '0.8'
    }).show();
    setTimeout(function () { $('div.tipsClass').fadeOut(); }, (time * 1000));
}
  </script>
<body>
	<form:form id="searchForm" modelAttribute="bdVoucherDetail" action="${ctx}/bd/voucher/list" method="post" class="breadcrumb form-search" style="display:none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />	
		
		<input type="hidden" name="orgNo" value="${bdVoucherDetail.orgNo}">
		<input type="hidden" name="certactName" value="${bdVoucherDetail.certactName}">
		<input type="hidden" name="certactCode" value="${bdVoucherDetail.certactCode}">
	</form:form>
	
	<!--  
	<bd:message content="${message}"/>-->
	<form:form id="searchForm" modelAttribute="bdVoucherDetail" action="${ctx}/bd/voucher/list" method="post" class="breadcrumb form-search">
	    <label>机构号：</label>
		<sys:treeselect id="orgNo" name="orgNo" value="${store.orgNo}" labelName="orgName" labelValue="${store.orgName}" title="机构" url="/bd/bdstore/treeData" cssStyle="width:150px;font-size:10px;" />
		<label>单证名称：</label>
		<form:input path="certactName" htmlEscape="false" maxlength="50" style="width:120px" id="certactName" />
		<label>单证代码：</label>
		<form:input path="certactCode" htmlEscape="false" maxlength="50" style="width:120px" id="certactCode"  />		
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		<input id="reset" class="btn btn-primary" name="reset" type="reset" value="重置" />
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
		    <th>请领单编号</th>
		    <th>季度</th>
		    <th>日期</th>
		    <th>机构号</th>
		    <th>机构名称</th>
			<th>单证代码</th>
			<th>单证名称</th>
			<th>审批通过数量</th>
			<th>实际购入数量</th>
			<th>购入时间</th>
			<th>操作人员</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.list}" var="bdVoucherDetail">
			<tr>
			    <td>${bdVoucherDetail.planNumber}</td>
			    <td>${bdVoucherDetail.quarter}</td>
			    <td>${bdVoucherDetail.planDate}</td>
			    <td>${bdVoucherDetail.orgNo}</td>
			    <td>${bdVoucherDetail.orgName}</td>
				<td>${bdVoucherDetail.certactCode}</td>
				<td>${bdVoucherDetail.certactName}</td>
			    <td>${bdVoucherDetail.planNum}</td>
			    <c:if test="${bdVoucherDetail.purTime==null}">				
			    <td><input id="${bdVoucherDetail.id}" value="${bdVoucherDetail.purNum}" style="width:70px" /></td>
			    </c:if>
			    <c:if test="${bdVoucherDetail.purTime!=null}">
			    <td>${bdVoucherDetail.purNum}</td>
			    </c:if>	
			    <td>${bdVoucherDetail.purTime}</td>
			    <td>${bdVoucherDetail.userId}</td>
			    <c:if test="${bdVoucherDetail.purTime==null}">
				<td><a
					href="javascript:updateData(${bdVoucherDetail.id},${bdVoucherDetail.planNum})" onclick="return confirmx('确定要购入吗？', this.href)">购入</a>
					 <a href="${ctx}/bd/bdplan/detail?planNumber=${bdVoucherDetail.planNumber}">详情</a></td>
				</c:if>	
				<c:if test="${bdVoucherDetail.purTime!=null}">
				<td> <a href="${ctx}/bd/bdplan/detail?planNumber=${bdVoucherDetail.planNumber}">详情</a></td>
				</c:if>				
			</tr>
		</c:forEach>
	</table>
      <div class="pagination">${page}</div>
</body>
</html>
