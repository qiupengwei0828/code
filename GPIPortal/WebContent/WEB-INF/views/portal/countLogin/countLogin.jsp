<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>登陆统计</title>
<script src="${ctxStatic}/echarts/3.1.6/echarts.min.js" type="text/javascript"></script>

</head>

<body>
    
	<div id="login_log" style="width: 100%;height:250px;"></div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('login_log'));
		// 指定图表的配置项和数据
		option = {
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
			    type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'value'
			},
			yAxis : {
				type : 'category',
				data : [ ${title} ]
			//data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
			},
			series : [ {
				name : '直接访问',
				type : 'bar',
				stack : '总量',
				label : {
					normal : {
						show : true,
						position : 'insideRight'
					}
				},
				data : [ ${cntNum} ]
			//data : [ 320, 302, 301, 334, 390, 330, 320 ]
			} ]
		};

		myChart.setOption(option);
	</script>
</body>

</html>