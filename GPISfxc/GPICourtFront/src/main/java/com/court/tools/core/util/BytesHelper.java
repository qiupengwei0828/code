package com.court.tools.core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;

public final class BytesHelper {

	/**
	 * 将byte[]类型 转换为int 类型
	 * @param bytes 传入byte[]类型参数
	 * @return result 返回int类型值
	 * @throws IOException
	 */
	public static int toInt(byte[] bytes) throws IOException  {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	/**
	 * 将byte[]类型 转换为short 类型
	 * @param bytes 传入byte[]类型参数
	 * @return 返回short类型值
	 * @throws IOException
	 */
	public static short toShort(byte[] bytes) throws IOException  {
		return (short) (((-(short) Byte.MIN_VALUE + (short) bytes[0]) << 8)
				- (short) Byte.MIN_VALUE + (short) bytes[1]);
	}

	/**
	 * 将int类型 转换为 byte[]类型
	 * @param value 传入int类型参数
	 * @return result 返回byte[]类型值
	 * @throws IOException
	 */
	public static byte[] toBytes(int value) throws IOException  {
		byte[] result = new byte[4];
		for (int i = 3; i >= 0; i--) {
			result[i] = (byte) ((0xFFl & value) + Byte.MIN_VALUE);
			value >>>= 8;
		}
		return result;
	}

	/**
	 * 将short类型 转换为 byte[]类型
	 * @param value 传入short类型参数
	 * @return result 返回byte[]类型值
	 * @throws IOException
	 */
	public static byte[] toBytes(short value) throws IOException  {
		byte[] result = new byte[2];
		for (int i = 1; i >= 0; i--) {
			result[i] = (byte) ((0xFFl & value) + Byte.MIN_VALUE);
			value >>>= 8;
		}
		return result;
	}

	/**
	 * 将Object类型 转换为 byte[]类型
	 * @param object 传入object类型参数
	 * @return objbytes 返回byte[]类型值
	 * @throws IOException
	 */
	public static byte[] toBytes(Object object) throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream outObj = new ObjectOutputStream(byteOut);
		outObj.writeObject(object);
		byte[] objbytes = byteOut.toByteArray();
		return objbytes;
	}

	/**
	 * 将Blob类型 转换为 Object类型
	 * @param blob 传入blob类型参数
	 * @return object 返回object类型值
	 * @throws Exception
	 */
	public static Object toObject(Blob blob) throws Exception {
		InputStream is = blob.getBinaryStream();
		BufferedInputStream input = new BufferedInputStream(is);
		
		//byte[] buff = blob.getBytes(0, blob.length());
		byte[] buff = new byte[Integer.parseInt(blob.length()+"")];
		while (-1 != (input.read(buff, 0, buff.length)));

		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buff));
		Object object = (Object) in.readObject();
		return object;
	}
}
