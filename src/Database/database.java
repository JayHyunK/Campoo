package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

	private database() {
		// ������ ���ϰ��� > �ٸ� ������ �� �θ� 
		// ������ �޼ҵ尡 �ƴ� ��� (�޼ҵ�� ��ü�� �����ϴ� ����) > static���� �������� 
	}
	private static database instance= new database();
	
	public static database getInstance() {
		return instance;
	}
	// Ŀ�ؼ� ���� ó�� 
	public Connection getConnection() throws Exception{//������ ���� ���� ó�� 
		Connection conn=null;
		String url = "jdbc:mysql://127.0.0.1:3306/Campoo";
		String id = "root";
		String pw = "iotiot";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url, id, pw);
		return conn;
	}
}
// select �˻��� �� �ȵǴ� ��� > 
// alter database campoo default character set utf8mb4;