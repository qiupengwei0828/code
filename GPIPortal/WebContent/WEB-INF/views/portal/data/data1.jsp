<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>数据1</title>
<script src="${ctxStatic}/echarts/2.2.7/build/dist/echarts-all.js" type="text/javascript"></script>
</head>

<body>
	<div id="data1" style="width: 100%;height:350px;">
	</div>

	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var myChart = echarts.init(document.getElementById('data1'));
		var option = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			//calculable : true,
			series : [ {
				type : 'pie',
				radius : '80%',
				center : [ '50%', '50%' ],
				label : {
					show : false,
					position : 'inner',
				},
				data : [ {
					value : 78689267,
					name : '住户'
				}, {
					value : 52413256,
					name : '非金融企业'
				}, {
					value : 29817434,
					name : '广义政府'
				}, {
					value : 3006940,
					name : '非银行业金融机构'
				} ],
				itemStyle : {
					normal : {
						label : {
							position : 'inner'
						},
						labelLine : {
							//是否显示标题线
							show : false
						}
					}
				},
			} ]
		};
		// 为echarts对象加载数据 
		myChart.setOption(option);
	</script>
</body>

</html>