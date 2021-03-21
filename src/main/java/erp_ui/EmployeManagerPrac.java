package erp_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import erp_ui_content.EmployeePanel;
import erp_ui_service.EmployeeService;
import erp_ui_service.EmployeeServicePrac;
import erp_ui_content.EmployeeDetailPanel;
import erp_ui_content.EmployeePanelPrac;
import erp_ui_content.EmployeeDetailPanelPrac;

public class EmployeManagerPrac extends JFrame {

	private JPanel contentPane;
	private EmployeeServicePrac service;
	private EmployeeDetailPanelPrac pList;
	private EmployeePanelPrac pEmpItem;
	
	public EmployeManagerPrac() {
		service = new EmployeeServicePrac();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		
		pEmpItem = new EmployeePanelPrac();
		pEmpItem.setService(service);		
		contentPane.add(pEmpItem);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		JButton btnAdd = new JButton("추가");
		pBtns.add(btnAdd);
		
		JButton btnSet = new JButton("수정");
		pBtns.add(btnSet);
		
		JButton btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
		
		pList = new EmployeeDetailPanelPrac();
		
		
		
		contentPane.add(pList);
	}

}
