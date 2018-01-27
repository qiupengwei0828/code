<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
<sys:importData modelAttribute="${store }" value="T_BD_STORE" id="1"></sys:importData>
</body>
</html>
