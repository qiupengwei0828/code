package com.court.tools.core.util;

import java.io.BufferedInputStream;

import java.io.InputStream;

import java.util.Properties;

/**
 * 属性文件读取工具
 * 
 * @author vurtne
 * 
 */
public class PropertiesUtil {

	/**
	 * 根据key读取value
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String readValue(String fileName, String key) {

		Properties props = new Properties();

		try {

			InputStream in = new BufferedInputStream(PropertiesUtil.class
					.getClassLoader().getResourceAsStream(fileName));

			props.load(in);

			String value = props.getProperty(key);

			in.close();

			return value;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	
	/**
	 * 读取properties的全部信息
	 * @param fileName
	 * @return
	 */
	public static Properties readProperties(String fileName) {

		Properties props = new Properties();

		try {

			InputStream in = new BufferedInputStream(PropertiesUtil.class
					.getClassLoader().getResourceAsStream(fileName));

			props.load(in);

			in.close();

			return props;

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}
	
	/**
	 * 路径读取方法，无视结局符是否为“\\”,“/”,“”
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String readPathValue(String fileName, String key) {
		
		String result=readValue(fileName, key).replace("\\", "/");
		
		if(!result.endsWith("\\")){
			
			result+="/";
			
		}
		
		return result;
		
	}

}
