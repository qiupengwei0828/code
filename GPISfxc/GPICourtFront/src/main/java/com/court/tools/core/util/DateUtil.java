package com.court.tools.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	/**
	 * 根据2个日期进行中间的日期取值
	 * @param beginTime 开始日期
	 * @param endTime 结束日期
	 * @return 返回字符串类型的日期集合
	 */
	public List<String> between(String beginTime, String endTime) {

		List<String> result = new ArrayList<String>();
		
		result.add(beginTime);

		try {

			Date startDate = new SimpleDateFormat("yyyy-MM-dd")
					.parse(beginTime);

			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);

			Calendar startCal = Calendar.getInstance();

			startCal.setTime(startDate);

			Calendar endCal = Calendar.getInstance();

			endCal.setTime(endDate);

			do {

				startCal.add(Calendar.DAY_OF_MONTH, 1);

				result.add(new SimpleDateFormat("yyyy-MM-dd").format(startCal
						.getTime()));

			} while (!new SimpleDateFormat("yyyy-MM-dd").format(
					startCal.getTime())
					.equals(new SimpleDateFormat("yyyy-MM-dd").format(endCal
							.getTime())));

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return result;

	}
	
	/**
	 * 根据2个日期进行中间的月份取值
	 * @param beginTime 开始月份
	 * @param endTime 结束月份
	 * @return 返回字符串类型的月份集合
	 */
	public List<String> betweenMonth(String beginTime, String endTime) {

		List<String> result = new ArrayList<String>();
		
		result.add(beginTime);

		try {

			Date startDate = new SimpleDateFormat("yyyy-MM")
					.parse(beginTime);

			Date endDate = new SimpleDateFormat("yyyy-MM").parse(endTime);

			Calendar startCal = Calendar.getInstance();

			startCal.setTime(startDate);

			Calendar endCal = Calendar.getInstance();

			endCal.setTime(endDate);

			do {

				startCal.add(Calendar.MONTH, 1);

				result.add(new SimpleDateFormat("yyyy-MM").format(startCal
						.getTime()));

			} while (!new SimpleDateFormat("yyyy-MM").format(
					startCal.getTime())
					.equals(new SimpleDateFormat("yyyy-MM").format(endCal
							.getTime())));

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return result;

	}
	
	/**
	 * 根据2个日期进行中间的月份取值
	 * @param beginTime 开始月份
	 * @param endTime 结束月份
	 * @return 返回字符串类型的月份集合
	 */
	public List<String> betweenWeek(String beginTime, String endTime) {

		List<String> result = new ArrayList<String>();
		
		result.add(beginTime);

		try {

			Date startDate = new SimpleDateFormat("yyyy-ww")
					.parse(beginTime);

			Date endDate = new SimpleDateFormat("yyyy-ww").parse(endTime);

			Calendar startCal = Calendar.getInstance();

			startCal.setTime(startDate);

			Calendar endCal = Calendar.getInstance();

			endCal.setTime(endDate);

			do {

				startCal.add(Calendar.WEEK_OF_YEAR, 1);

				result.add(new SimpleDateFormat("yyyy-ww").format(startCal
						.getTime()));

			} while (!new SimpleDateFormat("yyyy-ww").format(
					startCal.getTime())
					.equals(new SimpleDateFormat("yyyy-ww").format(endCal
							.getTime())));

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return result;

	}
	
	/**
	 * 取得当前日期是一年中的第几个星期
	 * @return
	 */
	public static int getWeek(){
		
		return Integer.parseInt(new SimpleDateFormat("ww").format(new Date()));
		
	}
	
	/**
	 * 取得当前日期是一年中的第几个星期
	 * @return
	 */
	public static int getYear(){
		
		return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
		
	}
	
	/**
	 * 取得当前日期是一年中的第几个星期
	 * @return
	 */
	public static int getMonth(){
		
		return Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
		
	}
	
	public static void main(String[] args) {
		for (String string : new DateUtil().betweenMonth("2012-06", "2013-5")) {
			System.out.println(string);
		}
		for (String string : new DateUtil().betweenWeek("2012-06", "2013-05")) {
			System.out.println(string);
		}
	}

}
