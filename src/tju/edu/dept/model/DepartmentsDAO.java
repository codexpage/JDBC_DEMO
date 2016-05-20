package tju.edu.dept.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tju.edu.dept.db.DBConnection;

public class DepartmentsDAO {
	/**
	 * 查询:返回全部部门
	 * @param emp
	 */
	public List<Departments> findAll() {		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Departments> list = new ArrayList<Departments>(); 
		try {
			conn = DBConnection.getConnection();
			String sql = 
				"SELECT dept_no, dept_name " + 
				"FROM departments";
			System.out.println(sql);
			stat = conn.createStatement();			
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Departments dept = new Departments();
				dept.setDeptNo(rs.getString("dept_no"));
				dept.setDeptName(rs.getString("dept_name"));
				list.add(dept);
			}
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}
	
	/**
	 * 增加
	 * @param dept
	 */
	public void insert(Departments dept) {
		
		String deptNo = dept.getDeptNo();
		String deptName = dept.getDeptName();
		assert deptNo != null;
		assert deptName != null;
		
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBConnection.getConnection();
			String sql = 
				"INSERT INTO departments " + 
				"VALUES (?, ?)";
			System.out.println(sql);
			stat = conn.prepareStatement(sql);
			stat.setString(1, deptNo);
			stat.setString(2, deptName);			
			int res = stat.executeUpdate();			
			assert res == 1;
			
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	/**
	 * 删除
	 * @param dept
	 */
	public void delete(Departments dept) {
		
		String deptNo = dept.getDeptNo();
		assert deptNo != null;
		
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBConnection.getConnection();
			String sql = 
				"DELETE FROM departments " + 
				"WHERE dept_no = ?";
			System.out.println(sql);
			stat = conn.prepareStatement(sql);
			stat.setString(1, deptNo);
			int res = stat.executeUpdate();			
			assert res == 1;
			
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	/**
	 * 修改
	 * @param dept
	 */
	public void update(Departments dept) {
		
		String deptNo = dept.getDeptNo();
		String deptName = dept.getDeptName();
		assert deptNo != null;
		assert deptName != null;
		
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBConnection.getConnection();
			String sql = 
				"UPDATE departments " + 
				"SET dept_name = ? " +
				"WHERE dept_no = ?";
			System.out.println(sql);
			stat = conn.prepareStatement(sql);
			stat.setString(1, deptName);
			stat.setString(2, deptNo);
			int res = stat.executeUpdate();			
			assert res == 1;
			
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	/**
	 * 查询:查找指定编号的员工（根据主键查找）
	 * @param emp
	 */
	public Departments query(String deptNo) {
		
		assert deptNo != null;
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		Departments dept = new Departments(); 
		try {
			conn = DBConnection.getConnection();
			String sql = 
				"SELECT dept_no, dept_name " + 
				"FROM departments " +
				"WHERE dept_no = ?";
			System.out.println(sql);
			stat = conn.prepareStatement(sql);
			stat.setString(1, deptNo);
			
			rs = stat.executeQuery();			
			
			if (rs.next()) { 				
				dept.setDeptNo(rs.getString("dept_no"));
				dept.setDeptName(rs.getString("dept_name"));
			}
			
		} catch (SQLException e) {
		   e.printStackTrace();
		   
		} finally {
			try {
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return dept;
	}
}
