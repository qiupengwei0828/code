/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.base.utils.CacheUtils;
import com.sys.dao.DicDao;
import com.sys.entity.Dic;

/**
 * 字典工具类
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DicUtils {

	private static DicDao dicDao = SpringContextHolder.getBean(DicDao.class);

	public static final String CACHE_DICT_MAP = "dicMap";
	public static final String CACHE_DICT_UNION_MAP = "dicUnionMap";

	public static String getDicLabel(String value, String typeCode, String defaultValue) {
		if (StringUtils.isNotBlank(typeCode) && StringUtils.isNotBlank(value)) {
			for (Dic dic : getDicList(typeCode)) {
				if (typeCode.equals(dic.getTypeCode()) && value.equals(dic.getpValue())) {
					return dic.getpName();
				}
			}
		}
		return defaultValue;
	}

	public static String getDicLabels(String values, String typeCode, String defaultValue) {
		if (StringUtils.isNotBlank(typeCode) && StringUtils.isNotBlank(values)) {
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")) {
				valueList.add(getDicLabel(value, typeCode, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDicValue(String label, String typeCode, String defaultLabel) {
		if (StringUtils.isNotBlank(typeCode) && StringUtils.isNotBlank(label)) {
			for (Dic dic : getDicList(typeCode)) {
				if (typeCode.equals(dic.getTypeCode()) && label.equals(dic.getpName())) {
					return dic.getpValue();
				}
			}
		}
		return defaultLabel;
	}

	public static List<Dic> getDicList(String typeCode) {
		@SuppressWarnings("unchecked")
		Map<String, List<Dic>> dicMap = (Map<String, List<Dic>>) CacheUtils.get(CACHE_DICT_MAP);
		if (dicMap == null) {
			dicMap = Maps.newHashMap();
			Dic d = new Dic();
			List<Dic> dicAllList = dicDao.findAllList(d);
			for (Dic dic : dicAllList) {
				List<Dic> dicList = dicMap.get(dic.getTypeCode());
				if (dicList != null) {
					dicList.add(dic);
				} else {
					dicMap.put(dic.getTypeCode(), Lists.newArrayList(dic));
				}
			}
			CleanDicList();
			CacheUtils.put(CACHE_DICT_MAP, dicMap);
		}
		List<Dic> dicList = dicMap.get(typeCode);
		if (dicList == null) {
			dicList = Lists.newArrayList();
		}
		return dicList;
	}

	public static List<Dic> findUnionCodeAndNameList(String typeCode) {
		@SuppressWarnings("unchecked")
		Map<String, List<Dic>> dicMap = (Map<String, List<Dic>>) CacheUtils.get(CACHE_DICT_UNION_MAP);
		if (dicMap == null) {
			dicMap = Maps.newHashMap();
			Dic d = new Dic();
			List<Dic> dicAllList = dicDao.findUnionCodeAndNameList(typeCode);
			for (Dic dic : dicAllList) {
				List<Dic> dicList = dicMap.get(dic.getTypeCode());
				if (dicList != null) {
					dicList.add(dic);
				} else {
					dicMap.put(dic.getTypeCode(), Lists.newArrayList(dic));
				}
			}
			CleanDicList();
			CacheUtils.put(CACHE_DICT_UNION_MAP, dicMap);
		}
		List<Dic> dicList = dicMap.get(typeCode);
		if (dicList == null) {
			dicList = Lists.newArrayList();
		}
		return dicList;
	}

	// 清空字典
	public static void CleanDicList() {
		CacheUtils.remove(CACHE_DICT_MAP);
		CacheUtils.remove(CACHE_DICT_UNION_MAP);
	}
}
