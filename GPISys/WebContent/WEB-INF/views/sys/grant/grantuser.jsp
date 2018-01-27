<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>分配用户</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
	/*
	 * 分页
	 */
	/* 	function page(n, s) {
	 $("#pageNo").val(n);
	 $("#pageSize").val(s);
	 $("#searchForm").submit();
	 return false;
	 }; */

	var appTree; //zTree 所有的应用系统  
	var roleTree; //zTree 选中系统后加载的角色列表

	//应用与角色设置
	var setting = {
		view : {
			selectedMulti : false,
			nameIsHTML : true,
			showTitle : false,
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : treeOnClick
		}
	};

	//菜单授权树设置
	var grantSetting = {
		check : {
			enable : true,
			nocheckInherit : true
		},
		view : {
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : function(id, node) {
				tree.checkNode(node, !node.checked, true, true);
				return false;
			}
		}
	};

	//加载应用系统列表
	function loadAppTree() {
		$.getJSON("${ctx}/sys/app/treeData", function(data) {
			$.fn.zTree.init($("#appTree"), setting, data).expandAll(true);
		});
	}

	// 初始化，加载应用系统列表
	loadAppTree();

	//当前应用系统和角色变量
	var curAppCode;
	var curRoleCode;
	//点击选择项回调
	function treeOnClick(event, treeId, treeNode, clickFlag) {
		$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
		if ("appTree" == treeId) {
			curAppCode = treeNode.id;
			$.get("${ctx}/sys/role/treeData?appCode=" + treeNode.id, function(roleNodes) {
				$.fn.zTree.init($("#roleTree"), setting, roleNodes);
			});
		}

		if ("roleTree" == treeId) {
			curRoleCode = treeNode.id;
			$('#officeContent').attr("src", "${ctx}/sys/usergrant/userlist?roleCode=" + treeNode.id);
		}
	};

	var userIdStr;
	function grantUsers(UserIds) {

		userIdStr = UserIds;

		if (curRoleCode != undefined && userIdStr != undefined) {
			saveUserRole();
		} else {
			if (curRoleCode == undefined) {
				$("#nullRoleCode").modal("show");
			}
			if (userIdStr == undefined) {
				$("#nullUserId").modal("show");
			}
		}
	}

	function saveUserRole() {
		$.ajax({
			data : "roleCode=" + curRoleCode + "&userId=" + userIdStr,
			type : "GET",
			dataType : 'json',
			url : "${ctx}/sys/usergrant/saveuserrole",
			success : function(data) {
				if (data.msg == "success") {
					$('#officeContent').attr("src", "${ctx}/sys/usergrant/userlist?roleCode=" + curRoleCode);
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="control-group pagination-right">

		<sys:userSelect id="uId" name="userName" labelValue="添加用户" callback="grantUsers" />

	</div>
	<div id="grantCtrl" class="row-fluid span12">
		<div class="span2" style="border-right: 1px solid #A8A8A8;">
			<p>所属应用：</p>
			<div id="appTree" class="ztree"></div>
		</div>
		<div class="span2">
			<p>角色：</p>
			<div id="roleTree" class="ztree"></div>
		</div>
		<div class="span8" style="padding-left:2px;padding-right:5px;border-left: 1px solid #A8A8A8;">
			<p>已授权用户：</p>
			<div style="height: 445px">
				<iframe id="officeContent" src="" width="100%" height="91%" frameborder="0"></iframe>
			</div>
		</div>
	</div>



	<div id="nullRoleCode" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">错误提示</h3>
		</div>
		<div class="modal-body">
			<p>未选择角色
			</h4>

			</br>
			<h5>请先选择角色后重试！</h5>

			</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>


	<div id="nullUserId" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">错误提示</h3>
		</div>
		<div class="modal-body">
			<p>
			<h4>未选择用户</h4>
			</br>
			<h5>请先选择用户后重试！</h5>

			</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>

</body>
</html>