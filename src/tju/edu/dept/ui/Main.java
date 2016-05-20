package tju.edu.dept.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tju.edu.dept.model.Departments;
import tju.edu.dept.model.DepartmentsDAO;


/**
 * main()
 * @author jerry
 *
 */
public class Main implements ActionListener {

	private DepartmentsDAO deptDAO = new DepartmentsDAO();
	
	private JFrame jFrame;
	private JPanel jContentPane;
	private JMenuBar jJMenuBar;
	
	private JLabel lblDeptList = null;
	@SuppressWarnings("rawtypes")
	private JList lstDeptList = null;
	private JScrollPane scrDeptList = null;

	private JLabel lblDeptNo = null;
	private JTextField tfDeptNo = null;

	private JLabel lblDeptName = null;
	private JTextField tfDeptName = null;

	private JButton btnInsert = null;
	private JButton btnDelete = null;
	private JButton btnUpdate = null;
	private JButton btnQuery = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JScrollPane getLstDeptList() {
		if (lstDeptList == null) {
			List<Departments> list = deptDAO.findAll();
			Object[] objs = list.toArray();
			lstDeptList = new JList(objs);
			scrDeptList = new JScrollPane(lstDeptList);
			scrDeptList.setBounds(new Rectangle(115, 20, 197, 180));
		}
		return scrDeptList;
	}
	
	@SuppressWarnings("unchecked")
	private void updateLstDeptList() {
		List<Departments> list = deptDAO.findAll();
		Object[] objs = list.toArray();
		lstDeptList.setListData(objs);
	}
	
	private JTextField getTfDeptNo() {
		if (tfDeptNo == null) {
			tfDeptNo = new JTextField();
			tfDeptNo.setBounds(new Rectangle(115, 214, 197, 25));
		}
		return tfDeptNo;
	}

	private JTextField getTfDeptName() {
		if (tfDeptName == null) {
			tfDeptName = new JTextField();
			tfDeptName.setBounds(new Rectangle(115, 252, 197, 25));
		}
		return tfDeptName;
	}

	private JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton();
			btnInsert.setBounds(new Rectangle(36, 292, 64, 28));
			btnInsert.addActionListener(this);
			btnInsert.setText("添加");
		}
		return btnInsert;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setBounds(new Rectangle(108, 292, 64, 28));
			btnDelete.addActionListener(this);
			btnDelete.setText("删除");
		}
		return btnDelete;
	}

	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton();
			btnUpdate.setBounds(new Rectangle(180, 292, 64, 28));
			btnUpdate.addActionListener(this);
			btnUpdate.setText("修改");
		}
		return btnUpdate;
	}

	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton();
			btnQuery.setBounds(new Rectangle(254, 292, 64, 28));
			btnQuery.addActionListener(this);
			btnQuery.setText("查询");
		}
		return btnQuery;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main application = new Main();
				application.getJFrame().setVisible(true);
			}
		});
	}

	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setResizable(false);
			jFrame.setLocation(new Point(500, 265));
			jFrame.setJMenuBar(getJMenuBar());
			jFrame.setSize(353, 375);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("部门管理");
		}
		return jFrame;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			lblDeptList = new JLabel();
			lblDeptList.setBounds(new Rectangle(37, 20, 61, 25));
			lblDeptList.setText("部门列表");

			lblDeptNo = new JLabel();
			lblDeptNo.setBounds(new Rectangle(37, 214, 61, 25));
			lblDeptNo.setText("部门编号");
			
			lblDeptName = new JLabel();
			lblDeptName.setText("部门名称");
			lblDeptName.setBounds(new Rectangle(37, 252, 61, 25));
			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(Color.LIGHT_GRAY);
			
			jContentPane.add(lblDeptList, null);
			
			jContentPane.add(lblDeptNo, null);
			jContentPane.add(getTfDeptNo(), null);
			
			jContentPane.add(lblDeptName, null);
			jContentPane.add(getTfDeptName(), null);
			
			jContentPane.add(getLstDeptList(), null);
			jContentPane.add(getBtnInsert(), null);
			jContentPane.add(getBtnDelete(), null);
			jContentPane.add(getBtnUpdate(), null);
			jContentPane.add(getBtnQuery(), null);
		}
		return jContentPane;
	}

	private JMenuBar getJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
		}
		return jJMenuBar;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("添加")) {
			if (tfDeptNo.getText().trim().equals("")
					|| tfDeptName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jFrame, "部门编号、部门名称不能为空");
			} else {
				Departments dept = new Departments();
				dept.setDeptNo(tfDeptNo.getText());
				dept.setDeptName(tfDeptName.getText());
				deptDAO.insert(dept);
				JOptionPane.showMessageDialog(jFrame, "添加成功");
				updateLstDeptList();
			}
		} else if (e.getActionCommand().equals("删除")) {
			if (tfDeptNo.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jFrame, "请输入部门编号");
			} else {
				String deptNo = tfDeptNo.getText();
				Departments dept = new Departments();
				dept.setDeptNo(deptNo);
				deptDAO.delete(dept);
				JOptionPane.showMessageDialog(jFrame, "删除成功");
				updateLstDeptList();
			}
		} else if (e.getActionCommand().equals("修改")) {
			if (tfDeptNo.getText().trim().equals("")
					|| tfDeptName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jFrame, "部门编号、部门名称不能为空");
			} else {
				Departments dept = new Departments();
				dept.setDeptNo(tfDeptNo.getText());
				dept.setDeptName(tfDeptName.getText());
				deptDAO.update(dept);
				JOptionPane.showMessageDialog(jFrame, "修改成功");
				updateLstDeptList();
			}
		} else if (e.getActionCommand().equals("查询")) {
			if (tfDeptNo.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(jFrame, "请输入部门编号");
			} else {
				String deptNo = tfDeptNo.getText();
				Departments dept = deptDAO.query(deptNo);
				JOptionPane.showMessageDialog(jFrame,
						"部门编号：" + dept.getDeptNo() + "\n" +
						"部门名称：" + dept.getDeptName());
			}
		}
	}

}
