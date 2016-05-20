package tju.edu.dept.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库访问类
 * @author jerry
 *
 */
public class DBConnection {


	// 驱动程序类
	public final static String DB_DRIVER_CLASS =
		"org.mariadb.jdbc.Driver";
	// 连接字符串URL
	public final static String DB_URL =
        "jdbc:mariadb://localhost:3306/Employees";
	// 登录用户名
    public final static String USERNAME = "root";
    // 登录口令
    public final static String PASSWORD = "123";
    
    // 静态块
	static {
		// 加载驱动程序
		try {
			Class.forName(DB_DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	// 获得JDBC连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					DB_URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return conn;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
