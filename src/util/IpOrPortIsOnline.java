package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ж������Ƿ����� �˿��Ƿ��
 * 
 * @author Bearcat Mail:hacker.bear.cat@qq.com
 * @since JDK 1.8
 * @version 1.0 2017-04-2
 */
public class IpOrPortIsOnline {

	private static final int timeOut = 3000; // ��ʱӦ����3������

	// �ж������Ƿ�����
	public static boolean isPing(String ip) {
		boolean status = false;
		if (ip != null) {
			try {
				status = InetAddress.getByName(ip).isReachable(timeOut);
			} catch (UnknownHostException e) {

			} catch (IOException e) {

			}
		}
		return status;
	}

	// �ж϶˿��Ƿ�������
	public static boolean isHostConnectable(String host, int port) {
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			// e.printStackTrace();
			return false;
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}
		return true;
	}
}