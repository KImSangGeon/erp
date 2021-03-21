package erp;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_ui_content.EmployeePanelPrac;
import erp_ui_list.DepartmentTablePanelPrac;
import erp_ui_list.TitleTablePanelPrac;
import erp_ui_service.DeptServicePrac;
import erp_ui_service.EmployeeServicePrac;
import erp_ui_service.TitleServicePrac;
import erp_ui_list.EmployeeTablePanelPrac;

public class TestFramePra extends JFrame  {

	private JPanel contentPane;
	private TitleServicePrac service;
	private TitleTablePanelPrac titlePanel;
	private DepartmentTablePanelPrac deptPanel;
	private DeptServicePrac	deptservice;
	private EmployeeServicePrac empservice;
	private EmployeeTablePanelPrac empPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					TestFramePra frame = new TestFramePra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFramePra() {
		service = new TitleServicePrac();
		deptservice = new DeptServicePrac();
		empservice = new EmployeeServicePrac();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		titlePanel = new TitleTablePanelPrac();
		titlePanel.setService(service);		
		titlePanel.loadData();
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(titlePanel);
		
		deptPanel = new DepartmentTablePanelPrac();
		deptPanel.setService(deptservice);		
		deptPanel.loadData();
		contentPane.add(deptPanel);		
		
		
		empPanel = new EmployeeTablePanelPrac();
		empPanel.setService(empservice);
		empPanel.loadData();
		contentPane.add(empPanel);
	}

	
}
