<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

<%@include file="/WEB-INF/views/include/head.jsp"%>

</head>
<script type="text/javascript">
	//重置选择内容
	$(document).ready(function() {

	});
</script>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">

					<div class="span6">
						<div class="accordion">
							<div class="accordion-group">
								<div class="accordion-heading"background-color: #F0F0F1;>
									<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: #F0F0F1;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">短信发送明细<!-- <i class="icon-refresh pull-right"></i> --></a>
								</div>

								<div class="accordion-body collapse in">
									<div class="accordion-inner">
										<div style="width:100%;float:left; overflow:auto;">
											<iframe src="${ctx}/sms/home/data1" width="100%" height="350px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="span6">

						<div class="accordion">
							<div class="accordion-group">
								<div class="accordion-heading"background-color: #F0F0F1;>
									<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: #F0F0F1;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">各个系统发送量<!-- <i class="icon-refresh pull-right"></i> --></a>
								</div>

								<div class="accordion-body collapse in">
									<div class="accordion-inner">
										<div style="width:100%;float:left; overflow:auto;">
											<iframe src="${ctx}/sms/home/data2" width="100%" height="350px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">

						<div class="accordion">
							<div class="accordion-group">
								<div class="accordion-heading"background-color: #F0F0F1;>
									<a class="accordion-toggle" style="color: #555;font-size: 14;background-color: #F0F0F1;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">月短信发送趋势图<!-- <i class="icon-refresh pull-right"></i> --></a>
								</div>

								<div class="accordion-body collapse in">
									<div class="accordion-inner">
										<div style="width:100%;float:left; overflow:auto;">
											<iframe src="${ctx}/sms/home/data3" width="100%" height="400px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>