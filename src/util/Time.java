package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time
 * 
 * @author Bearcat Mail:hacker.bear.cat@qq.com
 * @since JDK 1.8
 * @version 1.0 2017-04-2
 */
public class Time {
	public static String time() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// �������ڸ�ʽ
		return df.format(new Date());// ���� new Date()��ȡ��ǰϵͳʱ��
	}
}
