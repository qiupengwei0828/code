<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="按钮value值"%>
<%@ attribute name="callback" type="java.lang.String" required="false" description="回掉函数"%>


<input id="${id}" name="${name}" type="hidden" />
<input id="showUser" class="btn btn-primary" type="button" value="${labelValue}" />


<script type="text/javascript">
	var userIdValues;
	$("#showUser").click(function() {
		top.$.jBox.open("iframe:${ctx}/tag/tagUserSelectIndex", "选择用户", $(top.document).width() - 400, $(top.document).height() - 150, {
			buttons : {
				"确定" : "ok",
				"清除" : "clear",
				"关闭" : true
			},
			submit : function(v, h, f) {
				if (v == "ok") {
					h.find("iframe")[0].contentWindow.frames['userListIfram'].getv();
					userIdValues = h.find("iframe")[0].contentWindow.frames['userListIfram'].vla;
					${callback}(userIdValues);
				} else if (v == "clear") {
					h.find("iframe")[0].contentWindow.frames['userListIfram'].cleanV();
				}
			},

			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	});
</script>