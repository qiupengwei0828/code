<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>数据2</title>
<script src="${ctxStatic}/echarts/2.2.7/build/dist/echarts-all.js" type="text/javascript"></script>
</head>

<body>
	<div id="data2" style="width: 100%;height:300px;"></div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var myChart = echarts.init(document.getElementById('data2'));
		var option = {
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '住户', '企业', '政府', '非银行金融' ]
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
				data : [ '2015/1', '2015/2', '2015/3', '2015/4', '2015/5', '2015/6', '2015/7', '2015/8', '2015/9', '2015/10', '2015/11', '2015/12', '2016/1', '2016/2', '2016/3', '2016/4' ]
			},
			yAxis : {
				type : 'value',
				//设置成 true 后坐标刻度不会强制包含零刻度
				scale : true
			},
			series : [ {
				name : '住户',
				type : 'line',
				data : [ 6973, 7245, 7360, 7280, 7256, 7424, 7429, 7475, 7605, 7572, 7617, 7805, 7869, 8083, 8211, 8107 ]
			}, {
				name : '企业',
				type : 'line',
				data : [ 2805, 2716, 2881, 2884, 3003, 3012, 3162, 3301, 3266, 3272, 3144, 2874, 5241, 5175, 5431, 5476 ]
			}, {
				name : '政府',
				type : 'line',
				data : [ 4618, 4444, 4644, 4619, 4806, 4897, 4866, 4990, 4987, 5152, 5116, 5289, 2982, 2938, 3048, 3346 ]

			}, {
				name : '非银行金融',
				type : 'line',
				//stack : '总量',//数据相加后绘制折线统计图
				data : [ 115, 112, 129, 159, 195, 241, 218, 264, 238, 259, 278, 330, 301, 327, 314, 290 ]
			} ]
		};
		// 为echarts对象加载数据 
		myChart.setOption(option);
	</script>
</body>

</html>