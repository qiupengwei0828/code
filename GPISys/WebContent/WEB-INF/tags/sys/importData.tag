<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ attribute name="modelAttribute" type="java.lang.Object" required="true"%>
<script src="${ctxStatic}/jquery-plugin/ajaxfileupload.js" type="text/javascript"></script>
<div class="popup_box">
	<span class="hint">*</span>附件：
	<input type="file" id="doc" size=50 name="myfiles" /><br/>
	<font size="2">说明</font>:<br/>
	&nbsp;&nbsp;&nbsp;1.请先下载模板,按照此模板进行数据的导入,请勿随意更改模板样式!<br/>
	&nbsp;&nbsp;&nbsp;2.确保没有重复的数据!<br/>
	<input id="btnBack" class="btn btn-primary" type="submit" onclick="window.parent.window.jBox.close();" value="返回" />
	<input id="btnSubmit" class="btn btn-primary" type="submit" onclick="javascript:sub();" value="保存" />
	<div id="picTip"></div>	
</div>
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
	    url:"${ctx}/sys/importData/importData?id="+id+"&modelAttribute="+s, 
	    secureuri:false, 
	    fileElementId:"doc",
	    dataType: "text", 
	    success: function (data) {
		    if(data.indexOf("success") > -1){
			     window.parent.location.reload();			     
			     window.parent.window.jBox.close(); 
	        }else{
	        	 window.parent.window.jBox.close();
	        }	    
		      
	    }
	  });
	  }
  }
</script>