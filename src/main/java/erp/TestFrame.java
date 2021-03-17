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
import erp_dto.EmployeeDetail;
import erp_dto.Title;
import erp_ui_content.EmployeePanel;
import erp_ui_list.EmployeeTablePanel;
import erp_ui_service.EmployeeDetailService;
import erp_ui_service.EmployeeService;
import erp_ui_content.EmployeeDetailPanel;

public class TestFrame extends JFrame implements ActionListener  {
	
	private JPanel contentPane;
	private EmployeeTablePanel pList;
	@SuppressWarnings("unused")
	private EmployeeService service;
	private EmployeeDetailPanel panel;
	private JPanel panel_1;
	private JButton btnCome;
	private JButton btnSet;
	
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
		setBounds(100, 100, 507, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

				
		
		EmployeeService service = new EmployeeService();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		panel = new EmployeeDetailPanel();
		panel.setTfempno(new Employee(1003));
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnCome = new JButton("가져오기");
		btnCome.addActionListener(this);
		panel_1.add(btnCome);
		
		btnSet = new JButton("불러오기");
		btnSet.addActionListener(this);
		panel_1.add(btnSet);
	}	
		


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		if (e.getSource() == btnCome) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		System.out.println("SDAD");
		EmployeeDetail employeeDetail = panel.getItem();
		JOptionPane.showMessageDialog(null, employeeDetail);
		
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
		EmployeeDetailService service = new EmployeeDetailService();
		EmployeeDetail empDetail = service.selectEmployeeDetailByEmpNo(new Employee(1003));
		panel.setItem(empDetail);
	
	}
}
