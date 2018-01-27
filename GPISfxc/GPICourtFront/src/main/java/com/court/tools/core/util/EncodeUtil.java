package com.court.tools.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 编码工具类
 * @author yangj
 *
 */
public class EncodeUtil {
	
    /**
     * UTF-8编码字符串转换为根本gb2312编码格式
     * @param str
     * @return
     */
    public static String utf8Togb2312(String str){

        StringBuffer sb = new StringBuffer();

        for ( int i=0; i<str.length(); i++) {

            char c = str.charAt(i);

            switch (c) {

               case '+' :

                   sb.append( ' ' );

               break ;

               case '%' :

                   try {

                        sb.append(( char )Integer.parseInt (

                        str.substring(i+1,i+3),16));

                   }

                   catch (NumberFormatException e) {

                       throw new IllegalArgumentException();

                  }

                  i += 2;

                  break ;

               default :

                  sb.append(c);

                  break ;

             }

        }

        String result = sb.toString();

        String res= null ;

        try {

             byte [] inputBytes = result.getBytes( "8859_1" );

            res= new String(inputBytes, "UTF-8" );

        }

        catch (Exception e){}

        return res;

  } 
    
 // 将 GB2312 编码格式的字符串转换为 UTF-8 格式的字符串：

    public static String gb2312ToUtf8(String str) {

            String urlEncode = "" ;

            try {

                urlEncode = URLEncoder.encode (str, "UTF-8" );

            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();

            }

            return urlEncode;

    } 
    /**
     * UTF-8转换为GBK
     * @param str
     * @return
     */
    public static String utf8Togbk(String str){

        StringBuffer sb = new StringBuffer();

        for ( int i=0; i<str.length(); i++) {

            char c = str.charAt(i);

            switch (c) {

               case '+' :

                   sb.append( ' ' );

               break ;

               case '%' :

                   try {

                        sb.append(( char )Integer.parseInt (

                        str.substring(i+1,i+3),16));

                   }

                   catch (NumberFormatException e) {

                       throw new IllegalArgumentException();

                  }

                  i += 2;

                  break ;

               default :

                  sb.append(c);

                  break ;

             }

        }

        String result = sb.toString();

        String res= null ;

        try {

             byte [] inputBytes = result.getBytes( "8859_1" );

            res= new String(inputBytes, "GBK" );

        }

        catch (Exception e){}

        return res;

  } 
    public static String gbkToUtf8(String str) {

        String urlEncode = "" ;

        try {

            urlEncode = URLEncoder.encode (str, "GBK" );

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }

        return urlEncode;

} 
}
