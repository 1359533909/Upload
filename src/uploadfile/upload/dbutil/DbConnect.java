package uploadfile.upload.dbutil;
import java.sql.*;

public class DbConnect {
	public static Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/file_db?user=root&password=123456&useUnicode=true&characterEncoding=utf-8";
			try {
				conn=DriverManager.getConnection(url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print("链接数据库失败 ");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("驱动程序加载失败 ");
			e.printStackTrace();
		}
		return conn;
	}

}
