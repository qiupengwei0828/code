package com.pro.tools.core.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CStringUtils extends org.apache.commons.lang.StringUtils
{
  public static final String EVAL_PREFIX = "${";
  public static final String EVAL_SUFFIX = "}";

  
  
  public static String replaceRegexp(String srcStr,String regExp,String withStr)
  {
	  Pattern pattern = Pattern.compile(regExp); 
	  Matcher matcher = pattern.matcher(srcStr); 
	  while(matcher.find())
	  {
		  srcStr = matcher.replaceAll(withStr);   
	  }
	  return srcStr;
  } 
  
  
  
  public static String removeEnd(String src, int length)
  {
    if ((src == null) || (src.length() <= 1))
    {
      return null;
    }

    return src.substring(0, src.length() - length);
  }

  public static String deleteExceptBlank(String psql)
  {
    String sql = deleteHtml(psql);
    StringBuffer sb = new StringBuffer(sql + " $");
    int blankCount = 0;
    char ch = sb.charAt(0);
    int i = 0;
    int k = 0;
    int[] pos = new int[sb.length()];
    while (ch != '$')
    {
      ch = sb.charAt(i);
      if (ch == ' ')
      {
        blankCount++;
      }
      else
      {
        blankCount = 0;
      }
      if (blankCount > 1)
      {
        pos[k] = i;
        k++;
      }
      i++;
    }
    StringBuffer sb1 = new StringBuffer();
    boolean isBlank = false;
    for (int i1 = 0; i1 < sb.length(); i1++)
    {
      isBlank = false;
      if ((k > 0) && (i1 <= pos[(k - 1)]))
      {
        for (int i2 = 0; i2 < k; i2++)
        {
          if (i1 != pos[i2])
            continue;
          isBlank = true;
        }

        if (isBlank)
          continue;
        sb1.append(sb.charAt(i1));
      }
      else
      {
        sb1.append(sb.charAt(i1));
      }
    }

    sb1.deleteCharAt(sb1.lastIndexOf("$"));
    sql = sb1.toString();
    return sql;
  }

  public static String deleteHtml(String psql)
  {
    psql = psql + "$";
    StringBuffer sb = new StringBuffer(psql);
    for (int i = 0; i < psql.length() - 1; i++)
    {
      char ch = psql.charAt(i);
      if ((ch != '\r') && (ch != '\n'))
        continue;
      sb.replace(i, i + 1, " ");
    }

    sb.deleteCharAt(sb.length() - 1);
    String sql2 = sb.toString();
    return sql2;
  }

  public static String deleteAllBlank(String str)
  {
    String sql1 = str;
    StringBuffer sb = new StringBuffer(sql1);
    if (sql1.indexOf("$") == -1)
    {
      sb.append("$");
    }
    char ch = sb.charAt(0);
    int i = 0;
    int k = 0;
    int[] pos = new int[sb.length() + 1];
    while (ch != '$')
    {
      if (ch == ' ')
      {
        pos[k] = i;
        k++;
      }
      i++;
      ch = sb.charAt(i);
    }
    for (i = 0; i < k; i++)
    {
      sb.deleteCharAt(pos[i] - i);
    }

    sb.deleteCharAt(sb.lastIndexOf("$"));
    return sb.toString();
  }

  public static String blankIfNull(Object obj)
  {
    return obj == null ? "" : obj.toString();
  }

  public static String repeatSymbolSplittedString(String element, int num, String split)
  {
    StringBuffer sb = new StringBuffer(250);
    for (int i = 0; i < num; i++)
    {
      if (i != 0)
      {
        sb.append(split);
      }
      sb.append(element);
    }
    return sb.toString();
  }

  public static String repeatSymbolSplittedString(String element, int num)
  {
    return repeatSymbolSplittedString(element, num, ", ");
  }

  public static int getRepeatCount(String src, char c)
  {
    int count = 0;
    for (int i = 0; i < src.length(); i++)
    {
      if (src.charAt(i) != c)
        continue;
      count++;
    }

    return count;
  }

  public static String addQuoteToStr(String inputStr, String token, String quote)
  {
    String result = inputStr;
    String[] splitStr = inputStr.split(token);
    for (int i = 0; i < splitStr.length; i++)
    {
      splitStr[i] = (quote + splitStr[i] + quote);
    }
    result = ArrayUtils.toClearString(splitStr);
    return result;
  }

  public static String addQuoteToStr(String inputStr)
  {
    return addQuoteToStr(inputStr, ",", "'");
  }

  public static String addQuoteToStr(String[] str)
  {
    String inputStr = "";
    if ((str != null) && (str.length > 0))
    {
      for (int i = 0; i < str.length; i++)
      {
        inputStr = inputStr + str[i] + ",";
      }
      return addQuoteToStr(inputStr, ",", "'");
    }

    return "''";
  }

  public static String[][] lstToTwoDimStringArr(List lst)
  {
    if (lst == null)
    {
      return null;
    }

    String[][] arr = new String[lst.size()][];
    for (int i = 0; i < lst.size(); i++)
    {
      List _lst = (List)lst.get(i);
      String[] str = (String[])_lst.toArray(new String[_lst.size()]);
      arr[i] = str;
    }
    return arr;
  }

  public static String addCommaToArray(Object[] obj)
  {
    String inputStr = "";
    if ((obj != null) && (obj.length > 0))
    {
      for (int i = 0; i < obj.length; i++)
      {
        if ("".equals(obj[i]))
          continue;
        inputStr = inputStr + obj[i].toString() + ",";
      }

      return inputStr.substring(0, inputStr.length() - 1);
    }

    return "";
  }

  public static String ellipse(String src, int byteLength, String tail)
  {
    if (src == null) {
      return src;
    }
    StringBuffer s = new StringBuffer();
    for (int i = 0; (i < src.length()) && (byteLength > 0); i++)
    {
      char c = src.charAt(i);
      s.append(c);
      byteLength -= String.valueOf(c).getBytes().length;
    }
    if ((tail != null) && (byteLength <= 0))
    {
      byteLength = tail.getBytes().length;
      for (int i = s.length() - 1; (i >= 0) && (byteLength > 0); i--)
      {
        char c = s.charAt(i);
        s.deleteCharAt(i);
        byteLength -= String.valueOf(c).getBytes().length;
      }
      return tail;
    }

    return s.toString();
  }

  public static String[] ellipse(String[] src, int byteLength, String tail)
  {
    String[] returnSrc = new String[src.length];
    for (int i = 0; i < src.length; i++)
    {
      returnSrc[i] = ellipse(src[i], byteLength, tail);
    }
    return returnSrc;
  }

  public static String replace(String valueString, String prefix, String suffix, IStringReplaceProcess process)
  {
    StringBuffer src = new StringBuffer(valueString);
    int prefixIndex = 0; int suffixIndex = -1;
    while (((prefixIndex = src.indexOf(prefix, prefixIndex)) != -1) && 
      ((suffixIndex = findMatchesSuffixIndex(src, 
      prefixIndex, prefix, suffix)) != -1))
    {
      int start = prefixIndex + prefix.length();
      String code = src.substring(start, suffixIndex);

      code = replace(code, prefix, suffix, process);

      String msg = process.doReplace(code, src, start, suffixIndex);
      if (prefixIndex < src.length())
      {
        src.delete(prefixIndex, suffixIndex + suffix.length());
      }

      if ((!isNotEmpty(msg)) || (prefixIndex > src.length()))
        continue;
      src.insert(prefixIndex, msg);
      prefixIndex += msg.length();
    }

    return src.toString();
  }

  private static int findMatchesSuffixIndex(StringBuffer valueStringBuffer, int formIndex, String prefix, String suffix)
  {
    int suffixIndex = valueStringBuffer.indexOf(suffix, formIndex + prefix.length());
    int firstIndex = suffixIndex;
    while (suffixIndex != -1)
    {
      String code = valueStringBuffer.substring(formIndex, suffixIndex + suffix.length());
      if (countMatches(code, prefix) == countMatches(code, suffix))
        break;
      suffixIndex = valueStringBuffer.indexOf(suffix, suffixIndex + suffix.length());
    }

    return suffixIndex == -1 ? firstIndex : suffixIndex;
  }

  public static String replaceEval(String valueString, IStringReplaceProcess process)
  {
    return replace(valueString, "${", "}", process);
  }

  public static String valueOf(Collection collection, String token)
  {
    if ((collection == null) || (collection.isEmpty()) || (token == null))
    {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    for (Iterator it = collection.iterator(); it.hasNext(); )
    {
      String value = it.next().toString();
      sb.append(value + token);
    }
    sb.delete(sb.length() - token.length(), sb.length());
    return sb.toString();
  }

  public static boolean startsWithIgnoreCase(String s1, String s2)
  {
    if ((s1 == null) || (s2 == null))
    {
      return false;
    }
    return s1.toLowerCase(Locale.ENGLISH).startsWith(s2.toLowerCase(Locale.ENGLISH));
  }

  public static boolean endsWithIgnoreCase(String s1, String s2)
  {
    if ((s1 == null) || (s2 == null))
    {
      return false;
    }
    return s1.toLowerCase(Locale.ENGLISH).endsWith(s2.toLowerCase(Locale.ENGLISH));
  }

  public static String trimBlank(String s)
  {
    return trimTrailingBlank(trimLeadingBlank(s));
  }

  public static String trimLeadingBlank(String s)
  {
    if ((isEmpty(s)) || (!s.startsWith(" ")))
    {
      return s;
    }
    return trimLeadingBlank(substring(s, 1));
  }

  public static String trimTrailingBlank(String s)
  {
    if ((isEmpty(s)) || (!s.endsWith(" ")))
    {
      return s;
    }
    return trimTrailingBlank(substring(s, 0, s.length() - 1));
  }

  public static Map<String, String> strToMap(String value, String itemSplit, String keyValueSplit)
  {
    Map result = new HashMap();
    if (value == null)
    {
      return result;
    }

    StringTokenizer token = new StringTokenizer(value, itemSplit);

    while (token.hasMoreElements())
    {
      String item = token.nextToken();
      result.put(substringBefore(item, keyValueSplit), 
        substringAfter(item, keyValueSplit));
    }

    return result;
  }

  public static String cleanPath(String path)
  {
    return org.springframework.util.StringUtils.cleanPath(path);
  }

  public static String[] tokenizeToStringArray(String str, String delimiters)
  {
    return org.springframework.util.StringUtils.tokenizeToStringArray(str, delimiters);
  }
}
