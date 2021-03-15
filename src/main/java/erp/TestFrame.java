package erp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_dto.Department;
import erp_dto.Employee;
import erp_dto.Title;
import erp_ui_content.EmployeePanel;
import erp_ui_list.EmployeeTablePanel;
import erp_ui_service.EmployeeService;

public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSet;
	private JButton btnAdd;
	private JButton btnCancel;
	private EmployeePanel pEmpItem;
	private EmployeeTablePanel pList;
	private EmployeeService service;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		service = new EmployeeService();
		initialize();
	}
	private void initialize() {
		setTitle("직원관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 489, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

				
		
		EmployeeService service = new EmployeeService();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		pEmpItem = new EmployeePanel();
		pEmpItem.setService(service);
		
		contentPane.add(pEmpItem);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnSet = new JButton("설정");
		btnSet.addActionListener(this);
		pBtns.add(btnSet);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
	}

	public void actionPerformed(ActionEvent e) {
		try {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
		Employee emp = new Employee(1003, "조민희", new Title(3), new Employee(4377), 3000000, new Department(2));
		pEmpItem.setEmployee(emp);
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp = pEmpItem.getItem();
		String message = String.format(
				"empNo %d%n,"
				+ "empName %s%n"
				+ "title(%d)%n"		
				+ "dept(%d)%n"
				+ "manager(%s)%n"				
				+ "salary(%s)",
				emp.getEmpNo(),
				emp.getEmpName(),
				emp.getTitle().gettNo(),
				emp.getDept().getDeptNo(),
				emp.getManager().getEmpName(),
				emp.getSalary());
		JOptionPane.showMessageDialog(null, message);
		pList.loadData();
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pEmpItem.clearTf();
	}
}
