//JavaScript Document
//var box_view_btn = "loginBtn";

$(function() {

	$("#loginForm").validate({
		messages : {
			username : {
				required : "请填写用户名."
			},
			password : {
				required : "请填写密码."
			}
		},
		errorLabelContainer : "#messageBox",
		errorPlacement : function(error, element) {
			error.appendTo($("#loginError").parent());
		}
	});

	if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0) {
		alert('未登录或登录超时。请重新登录，谢谢！');
		top.location = "${ctx}";
	}

	function clearLoginForm() {
		$("#verifyCodeId").val("");
		$("#passwordId").val("");
	}

});