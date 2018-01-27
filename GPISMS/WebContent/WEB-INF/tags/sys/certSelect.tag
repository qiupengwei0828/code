<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="隐藏域ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域NAME"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="按钮名称"%>
<%@ attribute name="onclick" type="java.lang.String" required="false" description="点击事件"%>

<input type="hidden" id="${id}" name="${name}" />
<input id="showCert" class="btn btn-primary" type="button" value="${value}" />

<script type="text/javascript">
	var certIdV;
	var vals = parent.posCodeV;
	$("#showCert").click(function() {
		top.$.jBox.open("iframe:${ctx}/cert/cert/certSelect?posCode=" + vals, "选择证书", $(top.document).width() - 600, $(top.document).height() - 200, {
			buttons : {
				"确定" : "ok",
				"清除" : "clear",
				"关闭" : true
			},
			submit : function(v, h, f) {
				if (v == "ok") {
					h.find("iframe")[0].contentWindow.getv();
					certIdV = h.find("iframe")[0].contentWindow.values;
					// runInsert(执行函数)
					execute();
					h.find("iframe")[0].contentWindow.cleanV();
				} else if (v == "clear") {
					h.find("iframe")[0].contentWindow.cleanV();
				}
			},

			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	});
</script>