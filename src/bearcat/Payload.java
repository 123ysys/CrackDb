package bearcat;

import MssqlDbBlasting.MsSQLScript;
import MysqlDbBlasting.MySQLScript;

/**
 * �������
 * 
 * @author Bearcat Mail:hacker.bear.cat@qq.com
 * @since JDK 1.8
 * @version 1.0 2017-04-2
 */
public class Payload {
	public static void main(String[] args) {
		MySQLScript ms = new MySQLScript();
		MsSQLScript mss = new MsSQLScript();
		if(args.length < 1){
			System.out.println("[~]ʹ��˵��	: CrackDb.jar <--mysql> <�ֵ�:username.CrackDb>||<root>");
			System.out.println("[~]֧�ֵ����ݿ�	: Access | Mysql | SQL Server | Oracle");
			System.out.println("[~]�汾		: V 1.0");
			System.out.println("[~]�ֵ�����	: CrackDb.jar�򿪷�ʽΪWinRAR xxxxxDbBlasting\\*.CrackDb");
			System.out.println("[~]����		: http://www.secfree.com/");
			System.out.println("[~]����		: Bearcat Mail:hacker.bear.cat@qq.com");
		}else{
			String code = args[0];
			
			switch (code) {
			case "--mysql":ms.Mysql(args[1]);
				break;
			case "--mssql":mss.Mssql(args[1]);
				break;
			default:
				System.out.println("Access\t\t\t--access");
				System.out.println("Mysql\t\t\t--mysql");
				System.out.println("SQL Server\t\t--mssql");
				System.out.println("Oracl\t\t\t--oracle");
			}
		}
	}
}
