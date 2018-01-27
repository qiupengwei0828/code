package com.court.tools.core.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class DESCommon {
	
	    private static final String UTF8 = "utf-8";//�ַ����  	     
	    private static final String ALGORITHM_DESEDE = "DESede";//�������ģʽ  
	  
	   	    	      
	  
	      
	    /** 
	     * �ַ�ת�ֽ����� 
	     */ 
	    private byte[] str2ByteArray(String s) { //ֻ�ڽ�����ʹ�� 
	        int byteArrayLength = s.length()/2;  
	        byte[] b = new byte[byteArrayLength];  
	        for (int i = 0; i < byteArrayLength; i++) {  
	            byte b0 = (byte) Integer.valueOf(s.substring(i*2, i*2+2), 16).intValue();  
	            b[i] = b0;  
	        }  	          
	        return b;  
	    } 
	      
	    /**
	     * ����3DES�ӽ��ܷ���key 
	     *  
	     */  
	    private byte[] build3DesKey(String keyStr) throws Exception {  
	        byte[] key = new byte[24];  
	        byte[] temp = keyStr.getBytes(UTF8);  
	        if (key.length > temp.length) {  
	            System.arraycopy(temp, 0, key, 0, temp.length);  
	        } else {  
	            System.arraycopy(temp, 0, key, 0, key.length);  
	        }  	          
	        return key;  
	    } 
	    	      
	    /** 
	     * 3DES���� 
	     */  
	    public String desedeDecoder(String dest, String key) throws Exception {  
	        SecretKey secretKey = new SecretKeySpec(build3DesKey(key), ALGORITHM_DESEDE);  
	        Cipher cipher = Cipher.getInstance(ALGORITHM_DESEDE);  
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);  
	        byte[] b = cipher.doFinal(str2ByteArray(dest));  
	          
	        return new String(b, UTF8);  
	      
	    }  
	    
	    
	
}
