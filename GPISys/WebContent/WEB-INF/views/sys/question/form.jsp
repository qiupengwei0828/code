<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>问题管理</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {

	});
</script>

</head>
<body>
	<p></p>

	<form id="inputForm" action="" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label">标题</label>
			<div class="controls">
				<input id="title" type="text" name="title" class="required" style="width: 80%;" />
				<!--  -->
				<span class="help-inline"><font color="red" id="titleError">*</font></span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">描述</label>
			<div class="controls">
				<textarea id="fullEdit" style="height: 480px;"></textarea>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">注意事项</label>
			<div class="controls">
				<div>
					<p></p>
					<p>*非IE8浏览器，截图可直接粘贴</p>
					<p>*IE8下，需要手动选择图片</p>
				</div>
			</div>
		</div>


		<input name="content" id="content" type="hidden">


		<!--  -->
		<input id="status" name="status" id="status" type="hidden" value="1">


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="提 交" />
		</div>
	</form>


	<!--这里引用jquery和wangEditor.js-->
	<script type="text/javascript">
		var editor = new wangEditor('fullEdit');
		// 菜单配置
		editor.config.menus = [ 'img', '|', 'bold', 'underline', 'italic', 'strikethrough', 'eraser', 'forecolor', '|', 'quote', 'fontfamily', 'fontsize', 'head', 'unorderlist', 'orderlist',
				'alignleft', 'aligncenter', 'alignright', '|', 'table' ];

		//只粘贴纯文本
		editor.config.pasteText = true;
		//上传图片server_url
		editor.config.uploadImgUrl = "${ctx}/sys/question/upload_question_img";
		editor.config.uploadImgFileName = 'questionImg';

		//创建富文本编辑器
		editor.create();

		$("#title").blur(function() {
			if (title != "") {
				$("#titleError").text("*");
			}
		});

		$('#btnSubmit').click(function() {
			// 获取编辑器区域完整html代码
			var html = editor.$txt.html();
			// 获取编辑器纯文本内容
			var text = editor.$txt.text();
			// 获取格式化后的纯文本
			var formatText = editor.$txt.formatText();
			// 获取编辑区域的所有图片
			var imgs = editor.$txt.find('img');
			$("#content").attr("value", formatText);

			var title = $("#title").attr("value");

			var status = $("#status").attr("value");

			var explain = $("#explain").attr("value");
			if (title == "") {
				$("#titleError").text("标题不能为空！");
				return false;
			}

			$.ajax({
				type : "post",
				url : "${ctx}/sys/question/insert",
				dataType : "json",
				data : {
					"title" : title,
					"content" : html,
					"status" : status,
					"explain" : explain
				},
				success : function(data) {
					if (data.success == "success") {
						alert("提交成功！");
						$("#title").attr("value", "");
						$("#explain").attr("value", "");
						editor.$txt.html('');
					}
				}
			});

		});
	</script>

	<script type="text/javascript">
		
	</script>
</body>
</html>