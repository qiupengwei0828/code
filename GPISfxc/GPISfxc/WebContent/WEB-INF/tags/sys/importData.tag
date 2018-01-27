<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- jqGrid -->
<script src="${ctxStatic}/jqGrid/4.8/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jqGrid/4.8/i18n/grid.locale-cn.js" type="text/javascript"></script>
<link href="${ctxStatic}/jqGrid/4.8/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
<!-- jqGrid -->
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ attribute name="modelAttribute" type="java.lang.Object" required="true"%>
<script src="${ctxStatic}/jquery-plugin/ajaxfileupload.js" type="text/javascript"></script>
<div class="popup_box" id="importData">
	<span class="hint">*</span>附件：
	<input type="file" id="doc" size=50 name="myfiles" /><br/>
	<font size="2">说明</font>:<br/>
	&nbsp;&nbsp;&nbsp;1.请先下载模板,按照此模板进行数据的导入,请勿随意更改模板样式!<br/>
	&nbsp;&nbsp;&nbsp;2.确保没有重复的数据!<br/>
	<input id="btnBack" class="btn btn-primary" type="submit" onclick="window.parent.window.jBox.close();" value="返回" />
	<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="javascript:sub();" value="保存" />
	<div id="picTip"></div>	
</div>
 <div class="popup_box" id="button" style="display:none;align:right">
 <input type="hidden" style="width: 942px" /><input class="btn btn-primary" type="button" value="确认" onclick="btnsub" id="btnsub" style="align:right;">
</div>  
<table id="jqGrid">

</table>

<script type="text/javascript">
	function sub() { 
    var f = $("#doc").val();  
    var str = "${modelAttribute}";
    var id = "${value}";
    var s = str.substring(0,str.indexOf("@"));
    if(f==null||f==""){  
      $("#picTip").html("<span style='color:Red'>错误提示:上传文件不能为空,请重新选择文件</span>");
      return false;
      }else{ 
	  $.ajaxFileUpload ({
	    url:"${ctx}/sys/importData/importData?id="+id+"&modelAttribute="+s+"&value="+${id}, 
	    secureuri:false, 
	    fileElementId:"doc",
	    dataType: "text", 
	    success: function (data) {	       
		    if(data.indexOf("/") > -1){
		     document.getElementById("importData").style.display="none";
		     document.getElementById("button").style.display="";
		     $.ajax({
		       url : "${ctx}/sys/importData/imp?id="+id+"&site="+data+"&modelAttribute="+s,
		       type:"POST",
		       async: false, 
		       success:function(dataData){
		         var obj = JSON.parse(dataData);
		       $("#jqGrid").jqGrid({
		       url : "${ctx}/sys/importData/impData?id="+id+"&site="+data+"&modelAttribute="+s,
		        regional : "cn",
		        rownumbers : true,
				//显示数据条数
				viewrecords : true,
				//表格宽度
				width : $(top.document).width() - 220,
				//表格高度
				height : $(top.document).height() - 240,
		        altRows : true,
		        datatype : "json",
				colNames : obj.ColNs,
				colModel : obj.ColNsName			
			});
		   }
		   });	
			
	        }else if(data=="-1"){
	           $("#picTip").html("<span style='color:Red'>错误提示:上传文件为.xls格式,请重新选择文件</span>");
	        }else{
	           window.parent.window.jBox.close();
	        }	    		      
	    }
	  });
	  }
  }
  $("#btnsub").click(function() {
 var r = confirm('是否备份数据？');
 var flag=0;
 if(r){
    flag=1;
 }
  var str = "${modelAttribute}";
    var id = "${value}";
    var s = str.substring(0,str.indexOf("@"));
    $.ajax({
		       url : "${ctx}/sys/importData/impUse?id="+id+"&flag="+flag,
		       type:"POST",
		       async: false, 
		       success:function(dataData){
		        if(dataData.indexOf("success")>-1){
		          window.parent.location.reload();	
		          window.parent.window.jBox.close(); 
	        }else{
	        	window.parent.window.jBox.close();
	        }
		   }
         });	
  });
</script>