package com.court.tools.core.util;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Base64Util {

	/**
	 * 文件读取缓冲区大小
	 */
	private static final int CACHE_SIZE = 1024;

	/**
	 * <p>
	 * BASE64字符串解码为二进制数据
	 * </p>
	 * 
	 * @param base64
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(String base64) {
		byte[] temp = null;
		try {
			
			if (CStringUtils.isNotBlank(base64))
				temp = new sun.misc.BASE64Decoder().decodeBuffer(new String(base64.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static String decodeStr(String base64) {
		String temp = null;
		try {
			if (CStringUtils.isNotBlank(base64))
				temp = new String(
						new sun.misc.BASE64Decoder().decodeBuffer(base64));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static String decodeStrGbk(String base64) {
		String temp = null;
		try {
			if (CStringUtils.isNotBlank(base64))
				temp = new String(
						new sun.misc.BASE64Decoder().decodeBuffer(base64),"GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * <p>
	 * 二进制数据编码为BASE64字符串
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] encode2b(String enStr) {
		byte[] tmp = new sun.misc.BASE64Encoder().encode(enStr.getBytes())
				.getBytes();
		return tmp;
	}

	public static String encode(String enStr) {
		String rec = "";
		try {
			rec = new String(new sun.misc.BASE64Encoder().encode(enStr
					.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rec;
	}

	public static String encodeGbk(String enStr) {
		try {
			if (CStringUtils.isNotEmpty(enStr)) {
				enStr = new String(enStr.getBytes("GBK"), "GBK");
				if (PropertiesUtil.readValue("sfxc.properties", "court.data.field.base64").equals("1"))
					enStr = new String(
							new sun.misc.BASE64Encoder().encode(enStr
									.getBytes("GBK")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enStr;
	}

	/**
	 * <p>
	 * 将文件编码为BASE64字符串
	 * </p>
	 * <p>
	 * 大文件慎用，可能会导致内存溢出
	 * </p>
	 * 
	 * @param filePath
	 *            文件绝对路径
	 * @return
	 * @throws Exception
	 */
	public static String encodeFile(String filePath) throws Exception {
		byte[] bytes = fileToByte(filePath);
		return encode(new String(bytes));
	}

	/**
	 * <p>
	 * BASE64字符串转回文件
	 * </p>
	 * 
	 * @param filePath
	 *            文件绝对路径
	 * @param base64
	 *            编码字符串
	 * @throws Exception
	 */
	public static void decodeToFile(String filePath, String base64)
			throws Exception {
		byte[] bytes = decode(base64);
		byteArrayToFile(bytes, filePath);
	}

	public static void encodeToFile(String filePath, String srcStr)
			throws Exception {
		byte[] bytes = encode2b(srcStr);
		byteArrayToFile(bytes, filePath);
	}

	/**
	 * <p>
	 * 文件转换为二进制数组
	 * </p>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public static byte[] fileToByte(String filePath) {
		byte[] data = new byte[0];
		File file = new File(filePath);
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		if (file.exists()) {
			try {
				in = new FileInputStream(file);
				out = new ByteArrayOutputStream(2048);

				byte[] cache = new byte[CACHE_SIZE];
				int nRead = 0;
				while ((nRead = in.read(cache)) != -1) {
					out.write(cache, 0, nRead);
					out.flush();
				}
				data = out.toByteArray();
			} catch (Exception e) {
			} finally {

				try {
					if(out!=null)
					    out.close();
					if(in!=null)
					    in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return data;
	}

	/**
	 * <p>
	 * 二进制数据写文件
	 * </p>
	 * 
	 * @param bytes
	 *            二进制数据
	 * @param filePath
	 *            文件生成目录
	 */
	public static void byteArrayToFile(byte[] bytes, String filePath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new ByteArrayInputStream(bytes);
			File destFile = new File(filePath);
			if (!destFile.getParentFile().exists()) {
				destFile.getParentFile().mkdirs();
			}
			destFile.createNewFile();
			out = new FileOutputStream(destFile);
			byte[] cache = new byte[CACHE_SIZE];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
		} catch (Exception e) {
		} finally {
			try {
				if(out!=null)
				    out.close();
				if(in!=null)
				    in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	// 加密  
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "UTF-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
	public static void main(String[] args) {
    	String xm = "<?xml version='1.0' encoding='UTF-8' ?><result errMsg='如果没有错误，则无该结点信息，如果有错误，则为具体的中文错误信息描述，会作为提示信息展现在客户端；如显示用户名密码验证错误或数据库异常'>"
    			+ "<cxjglist><jg bdhm='20111021320000100001' result='success' msg=''></jg><jg bdhm='20111021320000100002' result='fail' msg='若成功则空，失败显示具体中文描述'></jg></cxjglist></result>";
          xm=encodeGbk(xm);
	}

}
