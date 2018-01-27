<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<title>分配角色</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
	var appTree; //zTree 所有的应用系统  
	var roleTree; //zTree 选中系统后加载的角色列表
	var grantMenuTree;//zTree 角色授权菜单，属于选中的系统

	// 初始化，加载应用系统列表
	$(document).ready(function() {
		//appTree = $.fn.zTree.init($("#appTree"), setting, officeNodes);
		//selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
	});
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

	var pre_ids = "${selectIds}".split(",");
	var ids = "${selectIds}".split(",");

	//当前应用系统和角色变量
	var curAppCode, curRoleCode;

	//点击选择项回调
	function treeOnClick(event, treeId, treeNode, clickFlag) {
		$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);

		if ("appTree" == treeId) {
			curAppCode = treeNode.id;
			$.get("${ctx}/sys/role/treeData?appCode=" + treeNode.id, function(roleNodes) {
				//alert(roleNodes);
				$.fn.zTree.init($("#roleTree"), setting, roleNodes);
			});
			//清除menu
			$.fn.zTree.init($("#grantMenuTree"), grantSetting, "");
		}
		if ("roleTree" == treeId) {
			curRoleCode = treeNode.id;
			$.get("${ctx}/sys/menu/grantTreeData?roleCode=" + treeNode.id + "&appCode=" + curAppCode, function(menuNodes) {
				grantMenuTree = $.fn.zTree.init($("#grantMenuTree"), grantSetting, menuNodes);
				grantMenuTree.expandAll(true);
			});

		}
	};

	function clearAssign() {
		var submit = function(v, h, f) {
			if (v == 'ok') {
				var tips = "";
				if (pre_ids.sort().toString() == ids.sort().toString()) {
					tips = "未给角色【${role.name}】分配新成员！";
				} else {
					tips = "已选人员清除成功！";
				}
				ids = pre_ids.slice(0);
				selectedNodes = pre_selectedNodes;
				$.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
				top.$.jBox.tip(tips, 'info');
			} else if (v == 'cancel') {
				// 取消
				top.$.jBox.tip("取消清除操作！", 'info');
			}
			return true;
		};
		tips = "确定清除角色【${role.name}】下的已选人员？";
		top.$.jBox.confirm(tips, "清除确认", submit);
	};

	//保存授权
	function saveGrant() {
		var ids = [], nodes = grantMenuTree.getCheckedNodes(true);
		for (var i = 0; i < nodes.length; i++) {
			ids.push(nodes[i].id);
		}
		//alert(curRoleCode+"--"+ids.join(""));
		$.ajax({
			type : "GET",
			url : "${ctx}/sys/rolegrant/saveGrant",
			//data: "roleCode="+curRoleCode+"&menuIds="+ids.join(""),
			data : {
				roleCode : curRoleCode,
				menuIds : ids.join(",")
			},
			dataType : 'json',
			success : function(msg) {
				alert("角色授权" + msg);
			}
		});
	}
</script>

</head>
<body>
	<div class="control-group pagination-right">
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保存授权" onclick="saveGrant();" />
	</div>
	<div id="grantCtrl" class="row-fluid span12">
		<div class="span3" style="border-right: 1px solid #A8A8A8;">
			<p>所属应用：</p>
			<div id="appTree" class="ztree"></div>
		</div>
		<div class="span3">
			<p>待选角色：</p>
			<div id="roleTree" class="ztree"></div>
		</div>
		<div class="span4" style="padding-left:16px;border-left: 1px solid #A8A8A8;">
			<p>角色授权：</p>
			<div id="grantMenuTree" class="ztree"></div>
		</div>
	</div>

</body>
</html>