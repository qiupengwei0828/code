<<<<<<< .mine
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="按钮value值"%>


<input id="${id}" name="${name}" type="hidden" />

<input id="showUser" class="btn btn-primary" type="button" value="${labelValue}" />

<script type="text/javascript">
	var user;

	$("#showUser").click(function() {
		top.$.jBox.open("iframe:${ctx}/posrot/tags/tagUserSelectIndex", "选择用户", $(top.document).width() - 400, $(top.document).height() - 150, {
			buttons : {
				"确定" : "ok"
			//"关闭" : true
			},
			submit : function(v, h, f) {
				if (v == "ok") {
					user = h.find("iframe")[0].contentWindow.userInfo;
					if (user == undefined) {
						alert("未选择员工，请选择员工后再试！！！");
						return false;
					}
					setValue();
				}
			},

			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	});
||||||| .r1240
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="按钮value值"%>


<input id="${id}" name="${name}" type="hidden" />

<input id="showUser" class="btn btn-primary" type="button" value="${labelValue}" />

<script type="text/javascript">
	var user;

	$("#showUser").click(function() {
		top.$.jBox.open("iframe:${ctx}/posrot/tags/tagUserSelectIndex", "选择用户", $(top.document).width() - 400, $(top.document).height() - 150, {
			buttons : {
				"确定" : "ok"
			//"关闭" : true
			},
			submit : function(v, h, f) {
				if (v == "ok") {
					user = h.find("iframe")[0].contentWindow.userInfo;
					if (user == undefined) {
						alert("未选择员工，请选择员工后再试！！！");
						return false;
					}
					setValue();
				}
			},

			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	});
=======
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="按钮value值"%>


<input id="${id}" name="${name}" type="hidden" />

<input id="${id}showUser" class="btn btn-primary" type="button" value="${labelValue}" />

<script type="text/javascript">
	var user;

	$("#${id}showUser").click(function() {
		top.$.jBox.open("iframe:${ctx}/posrot/tags/tagUserSelectIndex", "选择用户", $(top.document).width() - 400, $(top.document).height() - 150, {
			buttons : {
				"确定" : "ok"
			//"关闭" : true
			},
			submit : function(v, h, f) {
				if (v == "ok") {
					user = h.find("iframe")[0].contentWindow.userInfo;
					if (user == undefined) {
						alert("未选择员工，请选择员工后再试！！！");
						return false;
					}
					if ($("#${id}showUser").attr("id") == "recUserIdshowUser") {
						setRec();
					} else if ($("#${id}showUser").attr("id") == "repUserIdshowUser") {
						setRep();
					}
				}
			},

			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	});
>>>>>>> .r1280
</script>