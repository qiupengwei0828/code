<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>短信统计</title>
<%-- <script src="${ctxStatic}/echarts/3.1.6/echarts.min.js" type="text/javascript"></script> --%>
<script src="${ctxStatic}/echarts/2.2.7/build/dist/echarts-all.js" type="text/javascript"></script>

</head>

<body>

	<div id="data2" style="width: 100%;height:100%;"></div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('data2'));
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
				type : 'category',
				//data : [ '未发送', '已发送' ]
				data : [ ${appName} ]
			},
			yAxis : {
				type : 'value'
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
				data : [ ${counts} ]
			//data : [ 320, 302, 301 ]
			} ]
		};

		myChart.setOption(option);
	</script>
</body>

</html>