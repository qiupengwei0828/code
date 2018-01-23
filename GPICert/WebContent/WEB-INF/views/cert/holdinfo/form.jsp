<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>证书管理</title>
<style type="text/css">
.certImgDiv {
	float: left;
}

.certImgClose {
	float: left;
	position: absolute;
	z-index: 2;
	width: 16px;
	height: 16px;
}
</style>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript" src="${ctxStatic}/static/jquery-plugin/ajaxfileupload.js"></script>
<script type="text/javascript">
	var imgfilePath = "";
	var mycars = new Array();
	var file_id = new Array();
	var file_edit_path = new Array();
	var file_idValue = "";
	$(document).ready(function() {
		var id = $("#id").val();
		if (id != "") {
			$("#inputForm").attr("action", "${ctx}/cert/holdinfo/update");

			var fileSaveName = $("#fileSaveName").val();

			for (var i = 0; i < 9; i++) {
				if ($("#imgSrcName" + i).text() != "") {
					file_edit_path.push($("#imgSrcName" + i).text());
					file_id.push($("#imgSrcId" + i).text());
				}
			}
			/*删除按钮*/
			for (var edit = 0; edit < file_edit_path.length; edit++) {
				$("#fileImg" + edit).attr("src", "/certfilepath/" + file_edit_path[edit]);
				$("#close" + edit).css("display", "inline");
				//$("#fileImg" + edit).css("width", "200px");
				$("#fileImg" + edit).css("height", "170px");
			}

		} else {
			$("#inputForm").attr("action", "${ctx}/cert/holdinfo/insert");
		}

		$("#btnSubmit").click(function() {
			var certCode = $.trim($("#certCode").val());
			var issueOrg = $.trim($("#issueOrg").val());
			var certNo = $.trim($("#certNo").val());
			/*-------------------------------------------------*/
			if (certCode == "") {
				$("#certCodeError").html("选择的证书编码不能为空！");
			}
			$("#certCode").change(function() {
				$("#certCodeError").html("*");
			});
			/*-------------------------------------------------*/
			if (certNo == "") {
				$("#certNoError").html("输入的证书编号不能为空！");
			}
			/*-------------------------------------------------*/
			if (issueOrg == "") {
				$("#issueOrgError").html("输入的机构不能为空！");
			}
			if (certNo != "" && issueOrg != "" && certCode != "") {
				changeBtnSubmit();
			}
		});

		function changeBtnSubmit() {

			for (var i = 0; i < 9; i++) {
				if ($("#imgSrcId" + i) != "") {
					file_id.push($("#imgSrcId" + i).text());
				}
			}
			for (var i = 0; i < file_id.length; i++) {
				file_idValue += file_id[i] + ",";
			}
			$("#fileId").val(file_idValue);
			$("#inputForm").submit();
		}
		/*
		 * 验证
		 */
		$("#certNo").blur(function() {
			var certNo = $.trim($("#certNo").val());
			if (certNo !== "") {
				$("#certNoError").html("*");
			}
		});
		$("#issueOrg").blur(function() {
			var issueOrg = $.trim($("#issueOrg").val());
			if (issueOrg !== "") {
				$("#issueOrgError").html("*");
			}
		});

		$("#checkImg").click(function() {
			window.open(imgfilePath);
		});

		$("[data-dismiss='modal']").click(function() {
			var num = $(this).attr("title");
			$(this).parent().remove();
			$("#photoCover").val("");

			//删除证书扫描件ID
			var number = $(this).attr("title");
			var dId = $("#imgSrcId" + number).text();
			$("#imgSrcId" + number).html("");
			$.ajax({
				data : "fileId=" + dId,
				type : "POST",
				dataType : 'json',
				url : "${ctx}/cert/certattinfo/deleteCertImg",
				success : function(data) {

				}
			});
		});
	});

	function certFileChange() {
		if (file_id.length > 9) {
			alert("最多只能选择10张！");
		} else {
			uploadImg();
		}
	}
	//在页面触发点击事件时进行上传：
	function uploadImg() {
		$("#photoCover").val($("#certFile").val());
		$("#photoCoverError").html("*");
		$.ajaxFileUpload({
			url : '${ctx}/cert/certattinfo/uploadFile',
			type : 'POST',
			data : "id=" + $("#id").val(),
			secureuri : false, //一般设置为false
			fileElementId : 'certFile', // 上传文件的id、name属性名
			dataType : 'json', //返回值类型，一般设置为json、application/json
			success : function(data) {
				for (var c = 0; c < 9; c++) {
					if ($("#fileImg" + c).attr("src") == "") {
						imgfilePath = data.filePath;//获取文件名称
						mycars[c] = data.filePath;
						//mycars.push(data.filePath);
						var temp_num = c;

						$("#imgSrcId" + temp_num).text(data.fileId);

						for (var i = 0; i < mycars.length; i++) {
							$("#fileImg" + i).attr("src", mycars[i]);
							//$("#fileImg" + i).css("width", "200px");
							$("#fileImg" + i).css("height", "170px");
							$("#fileImg" + i).css("display", "");

							//点击图片新窗口打开
							$("#checkImg" + i).attr("href", mycars[i]);
							$("#close" + i).css("display", "inline");
							//存储证书扫描件的ID
						}
						return false;
					}
				}
			},
			error : function(data) {
				alert("发生未知错误，请重试！");
			}
		});
	}
</script>

<script type="text/javascript">
	window.onload = fn;
	function fn() {
		//img为空时隐藏
		for (var int = 0; int < 9; int++) {
			if ($("#fileImg" + int.toString()).attr("src") != "") {
				$("#fileImg" + int.toString()).css("display", "");

			}
		}
	}
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cert/holdinfo/index">证书列表</a></li>
		<li class="active"><a href="${ctx}/cert/holdinfo/form?id=${holdInfo.id}">证书${not empty holdInfo.id?'修改':'添加'}</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="holdInfo" action="" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="control-group">
			<div class="controls">
				<form:input path="id" htmlEscape="false" maxlength="50" id="id" type="hidden" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书编码</label>
			<div class="controls">
				<form:select id="certCode" path="certCode" class="input-medium" style="width:290px">
					<form:option value="" label="" />
					<form:options items="${certList}" itemLabel="codeName" itemValue="certCode" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red" id="certCodeError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">证书编号</label>
			<div class="controls">
				<form:input path="certNo" htmlEscape="false" maxlength="50" id="certNo" style="width:225px" />
				<span class="help-inline"><font color="red" id="certNoError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发证日期</label>
			<div class="controls">
				<input id="issueDate" name="issueDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate" style="width:225px" value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发证机构</label>
			<div class="controls">
				<form:input path="issueOrg" htmlEscape="false" maxlength="50" id="issueOrg" style="width:275px" />
				<span class="help-inline"><font color="red" id="issueOrgError">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">等级</label>
			<div class="controls">
				<form:input path="certLevel" htmlEscape="false" maxlength="50" id="certLevel" style="width:225px" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评定成绩</label>
			<div class="controls">
				<form:input path="certScore" htmlEscape="false" maxlength="50" id="certScore" style="width:225px" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">证书扫描件</label>
			<div class="controls">
				<div id="yc">
					<!--  -->
					<div>
						<a class="btn_addpic" href="javascript:void(0);"
							style="text-decoration: none; display: block; position: relative; width: 140px; height: 39px; overflow: hidden; border: 1px solid #EBEBEB; background: none repeat scroll 0 0 #F3F3F3; color: #999999; cursor: pointer; text-align: center;float: left;"> <span
							style="	display: block; line-height: 39px;"> <em style="background: url(${ctxStatic}/static/images/add.png) 0 0; display: inline-block; width: 18px; height: 18px; overflow: hidden; margin: 10px 5px 10px 0; line-height: 20em; vertical-align: middle;">+</em> 添加图片
						</span> <input id="certFile" class="fileprew" onchange="certFileChange();" tabindex="3" type="file" size="3" name="certFile" style="display: block; position: absolute; top: 0; left: 0; width: 140px; height: 39px; font-size: 100px; opacity: 0; filter: alpha(opacity = 0);">
						</a> <span class="help-inline" style="float: left;"><font color="red" id="photoCoverError">*</font></span>
					</div>
					<!--  -->
					<form:input path="fileId" id="fileId" name="fileId" type="hidden" />
					<form:input path="fileSaveName" id="fileSaveName" name="fileSaveName" type="hidden" />
				</div>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label"></label>
			<div id="Img" class="col-sm-6 col-md-3">
				<div class="controls">

					<c:forEach var="i" begin="0" end="9">
						<div class="certImgDiv">
							<div title="ycx">
								<button id="close${i}" style="display: none;" type="button" class="close" data-dismiss="modal" aria-hidden="true" title="${i}">×</button>
							</div>
							<br> <a id="checkImg${i}" target="_Blank" title="${i}"> <img id="fileImg${i}" src="" alt="" style="display: none;">
							</a>
						</div>
					</c:forEach>


					<div id="inputId">
						<c:forEach items="${editlist}" var="editlist" varStatus="status">
							<!-- 证书扫描件路径 -->
							<p id="imgSrcName${status.index}" style="display: none;">${editlist.fileSaveName}</p>
							<!-- 证书扫描件ID -->
							<h5 id="imgSrcId${status.index}" style="display: none;">${editlist.fileId}</h5>
						</c:forEach>
					</div>


				</div>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge" style="width:330px;" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="onclickSubmit()" /> <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

	<c:if test="${holdInfo.status == '1'}">
		<script type="text/javascript">
			$("#certCode").attr("disabled", "disabled");
			$("textarea").attr("readonly", "readonly");
			$("input").attr("readonly", "readonly");
			$("[title='ycx']").hide();
			$("#yc").css("display", "none");
		</script>
	</c:if>

</body>
</html>