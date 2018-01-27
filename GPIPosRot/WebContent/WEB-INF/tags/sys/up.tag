<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="name" type="java.lang.String" required="true"%><!-- 要上传文件input的name -->
<%@ attribute name="id" type="java.lang.String" required="true"%><!-- 要上传文件input的name -->
<%@ attribute name="url" type="java.lang.String" required="true"%><!-- 要上传文件input的name -->

<script src="${ctxStatic}/jquery-plugin/ajaxfileupload.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>


<div class="popup_box" id="jboxs">
	<span style="font-size: 18px;">*附件：</span>
	<!--  -->
	<input id="${id}Id" type="file" size="50" name="${name}">
	<!--  -->
	<input id="${id}Data" type="hidden">
	<!--  -->
	<input id="${id}Type" type="hidden">
	<p style="font-size: 18px;">说明</p>
	<p style="margin-left: 20px;font-size: 16px;">1.请先下载模板,填写模板后进行文件上传!</p>
	<p style="margin-left: 20px;font-size: 16px;">2.确认数据填写正确无误!</p>
	<p style="margin-left: 20px;font-size: 16px;">3.每个计划只能上传一份交接清单！</p>
	<input id="btnSubmit" class="btn btn-primary" type="button" onclick="javascript:sub();" value="上传" />
	<div id="picTip"></div>
</div>
<script type="text/javascript">
	function sub() {
		var f = $("#${id}Id").val();
		var str = "";
		var id = "";
		if (f == null || f == "") {
			$("#picTip").html("<span style='color:Red'>错误提示:上传文件不能为空,请重新选择文件</span>");
			return false;
		} else {
			$.ajaxFileUpload({
				url : "${url}",
				secureuri : false,
				fileElementId : "${id}Id",
				dataType : "json",
				data : {
					"tabKey" : $("#jjqdFileData").val(),
					"actionType" : $("#jjqdFileType").val(),
				},
				success : function(data) {
					$("#up_jjqd_tool").modal('hide');
				}
			});
		}
	}
</script>