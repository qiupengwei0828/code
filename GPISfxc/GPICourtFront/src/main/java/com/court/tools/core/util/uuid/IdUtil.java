package com.court.tools.core.util.uuid;


/**
 * <p>
 * ID 生成器
 * </p>
 * <p>
 *
 * 
 * @author vurtne
 * @version 1.0 Jul 18, 2011 9:29:54 PM
 * @since JDK 6.0
 */
public class IdUtil {

	/**
	 * 四位随机数与String类型当前日期时分秒毫秒组成的字符串组成的ID,共30位
	 * 
	 * @return Id
	 */

	public static String getId() {

		return (String) new UUIDHexGenerator().generate();

	}

	/**
	 * 随即获得四位随机数
	 * @author Javaing(www.javaing.net)
	 * @return 四位随机数 int
	 */
	public static int getMyRandom() {

		int myRandom = 1;

		while (true) {

			int temp = (int) ((Math.random() *9+1)* 1000);

			if (temp > 0 || temp <= 9) {

				myRandom = temp;

				break;

			}

		}

		return myRandom;

	}
}
