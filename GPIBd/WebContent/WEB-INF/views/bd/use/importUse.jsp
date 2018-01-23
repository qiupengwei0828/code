<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="${ctxStatic}/jquery-plugin/ajaxfileupload.js" type="text/javascript"></script>
</head>
  <script type="text/javascript">
 function sub() {
  var f = $("#doc").val();  
    if(f==null||f==""){  
      $("#picTip").html("<span style='color:Red'>错误提示:上传文件不能为空,请重新选择文件</span>");
      return false;
      }else{ 
	  $.ajaxFileUpload ({
	    url:"${ctx}/bd/use/importData", 
	    secureuri:false, 
	    fileElementId:"doc",
	    dataType: "text", 
	    success: function (data) {
		    if(data.indexOf("success") > -1){
		     window.parent.location.reload();	
		     showTips( '导入成功', 100, 100,3 );
		     window.parent.window.jBox.close(); 
	        }else{
	        	showTips( '导入失败', 100, 100,3 );
	        	window.parent.window.jBox.close();
	        }	    
		      
	    }
	  });
	  }
  }
  function showTips(tips, height, sizeWidth, time) {
    var windowWidth = document.documentElement.clientWidth;
    var tipsDiv = '<div class="tipsClass">' + tips + '</div>';
    $('body').append(tipsDiv);
    $('div.tipsClass').css({
        'top': '200px',
        'right': 450+ 'px',
        'position': 'absolute',
        'padding': '3px 5px',
        'background': '#8FBC8F',
        'font-size': 12 + 'px',
        'margin': '0 auto',
        'text-align': 'center',
        'width': sizeWidth + 'px',
        'color': '#fff',
        'opacity': '0.8'
    }).show();
    setTimeout(function () { $('div.tipsClass').fadeOut(); }, (time * 1000));
}
  </script>
  <body>
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
</body>
</html>

