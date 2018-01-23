<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<title>甘肃地图</title>
</head>

<body>
	<!--定义页面图表容器-->
	<!-- 必须制定容器的大小（height、width） -->
	<div id="gansuMap" style="width: 98%; height: 500px;">
	</div>

	<script src="${ctxStatic}/echarts/2.2.7/build/dist/echarts-all.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>

	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('gansuMap'));
		option = {
			tooltip : {
				trigger : 'item'
			},
			dataRange : {
				min : 0,
				max : 300,
				x : 'left',
				y : 'bottom',
				text : [ '高', '低' ], // 文本，默认为数值文本
				color : [ 'orangered', 'yellow', 'lightskyblue' ],
				calculable : true
			},
			toolbox : {
				show : true,
				orient : 'vertical',
				x : 'right',
				y : 'center',
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			/* roamController : {
				show : true,
				x : 'right',
				mapTypeControl : {
					'china' : true
				}
			}, */
			series : [ {
				name : '甘肃',
				type : 'map',
				mapType : '甘肃',
				roam : false,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					},
					emphasis : {
						label : {
							show : true
						}
					}
				},
				data : [ {
					name : '酒泉市',
					value : 58
				}, {
					name : '嘉峪关市',
					value : 0
				}, {
					name : '张掖市',
					value : 96
				}, {
					name : '金昌市',
					value : 270
				}, {
					name : '武威市',
					value : 1
				}, {
					name : '白银市',
					value : 107
				}, {
					name : '兰州市',
					value : 2
				}, {
					name : '临夏回族自治州',
					value : 100
				}, {
					name : '甘南藏族自治州',
					value : 3
				}, {
					name : '定西市',
					value : 91
				}, {
					name : '天水市',
					value : 24
				}, {
					name : '陇南市',
					value : 0
				}, {
					name : '平凉市',
					value : 23
				}, {
					name : '庆阳市',
					value : 0
				}

				]
			} ]
		};
		myChart.setOption(option);
	</script>
</body>

</html>