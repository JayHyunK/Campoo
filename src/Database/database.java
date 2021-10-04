package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

	private database() {
		// 생성자 못하게함 > 다른 곳에서 못 부름 
		// 때문에 메소드가 아닌 펑션 (메소드는 객체가 수행하는 것임) > static으로 만들어야함 
	}
	private static database instance= new database();
	
	public static database getInstance() {
		return instance;
	}
	// 커넥션 연결 처리 
	public Connection getConnection() throws Exception{//오류가 나면 예외 처리 
		Connection conn=null;
		String url = "jdbc:mysql://127.0.0.1:3306/Campoo";
		String id = "root";
		String pw = "iotiot";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(url, id, pw);
		return conn;
	}
}
// select 검색이 잘 안되는 경우 > 
// alter database campoo default character set utf8mb4;