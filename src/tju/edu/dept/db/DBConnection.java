package tju.edu.dept.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ������
 * @author jerry
 *
 */
public class DBConnection {


	// ����������
	public final static String DB_DRIVER_CLASS =
		"org.mariadb.jdbc.Driver";
	// �����ַ���URL
	public final static String DB_URL =
        "jdbc:mariadb://localhost:3306/Employees";
	// ��¼�û���
    public final static String USERNAME = "root";
    // ��¼����
    public final static String PASSWORD = "123";
    
    // ��̬��
	static {
		// ������������
		try {
			Class.forName(DB_DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	// ���JDBC����
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					DB_URL, USERNAME, PASSWORD);
			
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return conn;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
