package com.pro.tools.core.util;

import java.util.ArrayList;
import java.util.List;


public class StringUtil {
	
	//左、右括号常量
    private static final char LEFTBRACKET = '{';
    
    private static final char RIGHTBRACKET = '}';
	
	 /**
     * 从合法的字符串数组中，找到指定的字符串
     * @param cArr
     * @return 
     * @see
     */
    public static String getStringFromBracket(char[] cArr,int TargetNum){
 
        //成对括号计数器
        int counter = 0;
 
        int i = 0;
        
        //找到第N个左括号
        
        for (; i < cArr.length && counter < TargetNum; i++){
        	
            if (LEFTBRACKET == cArr[i]){
            	
                counter++;
                
            }
            
        }
 
        //从目标左括号开始截取字符串
        int leftNum = 0;
        
        StringBuilder sb = new StringBuilder();
        
        outer: for (; i < cArr.length; i++){
        	
            if (cArr[i] == RIGHTBRACKET){
            	
                //左右括号数匹配了
                --leftNum;
                
                if (leftNum < 0){
                	
                    break outer;
                    
                }
                
            } else if (cArr[i] == LEFTBRACKET){
            	
                //多余的左括号也要加入到String中，这里不需要break操作
                ++leftNum;
                
            }
            
            sb.append(cArr[i]);
            
        }
        
        return sb.toString();
        
    }

	/**
	 * 第一个字符小写
	 * @param str
	 * @return
	 */
	public String lowerCaseFirstChar(String str) {

		char ch = str.charAt(0);

		if (!Character.isLowerCase(ch)) {

			str = str.replace(str.charAt(0), (char) (str.charAt(0) + 32));

		}

		return str;

	}

	/**
	 * 首字符大写
	 * @param str
	 * @return
	 */
	public String upperCaseFirstChar(String str) {

		char ch = str.charAt(0);

		if (!Character.isUpperCase(ch)) {

			str = String.valueOf(((char) (str.charAt(0) - 32)))
					+ str.substring(1);

		}

		return str;
	}

	/**
	 * 在给定字符串source中查找target在第startIndex出现的位置
	 * 
	 * @param startIndex
	 *            第几次出现
	 * @param source
	 *            在此字符串中进行查找
	 * @param target
	 *            要查找的目标字符串
	 * @return 索引位置
	 */
	public int index(int startIndex, String source, String target) {

		int index = 0;

		for (int i = 0; i < startIndex; i++) {

			index += source.indexOf(target, index);

		}

		return index;

	}

	/**
	 * 在给定字符串source中查找target在第startIndex出现的位置和第endIndex出现的位置间的字符
	 * 
	 * @param startIndex
	 *            第几次出现
	 * @param source
	 *            在此字符串中进行查找
	 * @param target
	 *            要查找的目标字符串
	 * @return 索引位置
	 */
	public String subString(int startIndex, int endIndex, String source,
			String target) {

		int index = 0;

		for (int i = 0; i < startIndex; i++) {

			index += source.indexOf(target, index);

		}

		return source.substring(index(startIndex, source, target),
				index(endIndex, source, target));

	}
	
	/**
	 * 将指定字符串按逗号分开，因当全部为逗号时无法分开而创建此方法
	 * @param src
	 * @return
	 */
	public List<String> splitComma(String src){
		
		List<String> result=new ArrayList<String>();
		
		src=src.replace(",", ";,;");
		
		String[] array=src.split(",");
		
		for (String string : array) {
			
			result.add(string.replace(";", ""));
			
		}
		
		return result;
		
	}
	public static String  clearRN(String str){
		return str.replaceAll("\r", "").replaceAll("\n", "").replaceAll("	", "");
	}

}
