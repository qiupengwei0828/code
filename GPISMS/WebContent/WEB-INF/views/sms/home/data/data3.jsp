<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>data3</title>
<script src="${ctxStatic}/echarts/2.2.7/build/dist/echarts-all.js" type="text/javascript"></script>
</head>

<body>
	<div id="data3" style="width: 100%;height:100%;"></div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var myChart = echarts.init(document.getElementById('data3'));
		var option = {
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '已发送' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				//data : [ '2015/1', '2015/2', '2015/3', '2015/4' ]
				data : [ ${months} ]
			},
			yAxis : {
				type : 'value',
				//设置成 true 后坐标刻度不会强制包含零刻度
				scale : true
			},
			series : [ {
				name : '已发送',
				type : 'line',
				//data : [ 6973, 7245, 7360 ]
				data : [ ${sums} ]
			}
			//	, {
			//	name : '企业',
			//	type : 'line',
			//	data : [ 2805, 2716, 2881 ]
			//	}, {
			//	name : '政府',
			//	type : 'line',
			//	data : [ 4618, 4444, 4644 ]
			//	}
			]
		};
		// 为echarts对象加载数据 
		myChart.setOption(option);
	</script>
</body>

</html>