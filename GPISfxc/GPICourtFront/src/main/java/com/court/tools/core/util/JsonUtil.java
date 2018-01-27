package com.court.tools.core.util;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 
 * @author vurtne
 * @version 1.0 Json 字符串生成工具类
 *          <P>
 *          传入数扰载体，生成json格式字符串
 *          </p>
 */
public class JsonUtil {

	/**
	 * 功能描述:传入任意一个 object 对象生成一个指定规格的字符串,目前支持回车（换行符）
	 * 
	 * @param object
	 *            任意对象
	 * @return java.lang.String
	 */
	public static String objectToJson(Object object, boolean isClearSpecialChar) {

		StringBuilder json = new StringBuilder();

		if (object == null) {

			json.append("\"\"");

		} else if (object instanceof String) {

			json.append("\"").append((String) object).append("\"");

		} else if (object instanceof Boolean || object instanceof Integer) {

			json.append("").append(object).append("");

		} else {

			json.append(beanToJson(object));

		}

		if (isClearSpecialChar)

			return json.toString().replace("\n", "");

		else

			return json.toString();

	}
	
	public static String stringToJson(String key,String value){
		
		return "{\""+key+"\":\""+value+"\"}";
		
	}

	/**
	 * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串
	 * 
	 * @param bean
	 *            bean对象
	 * @return String
	 */
	public static String beanToJson(Object bean) {

		StringBuilder json = new StringBuilder();

		json.append("{");

		try {

			Field[] Fields = bean.getClass().getDeclaredFields();

			for (Field field : Fields) {

				String name = objectToJson(field.getName(), false);

				String value = objectToJson(
						ReflectUtil.getProperty(bean, field.getName()), false);

				json.append(name);

				json.append(":");

				json.append(value);

				json.append(",");

			}

			json.setCharAt(json.length() - 1, '}');

		}

		catch (IllegalArgumentException e) {

			e.printStackTrace();

		}

		return json.toString();

	}

	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串,该方法默认不清除特殊字符，如换行符。
	 * 
	 * @param list
	 *            要生成json的对象集合
	 * @return java.lang.String
	 */
	public static String listToJson(List<?> list) {

		return listToJson(list, false);

	}

	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串.
	 * 
	 * @param list
	 *            要生成json的对象集合
	 * @param isClearSpecialChar
	 *            ：true:清除特殊字符，false不清除。目前支持回车（换行符）
	 * 
	 * @return java.lang.String
	 */
	public static String listToJson(List<?> list, boolean isClearSpecialChar) {

		StringBuilder json = new StringBuilder();

		json.append("[");

		if (list != null && list.size() > 0) {

			for (Object obj : list) {

				json.append(objectToJson(obj, isClearSpecialChar));

				json.append(",");

			}

			json.setCharAt(json.length() - 1, ']');

		} else {

			json.append("]");

		}

		return json.toString();

	}

}
