package erp_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_dto.Employee;
import erp_dto.EmployeeDetail;
import erp_ui_content.EmployeeDetailPanel;
import erp_ui_service.EmployeeDetailService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeDetailUi extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private JPanel pBtns;
	private JButton btnAdd;
	private EmployeeDetailPanel pItem;
	private EmployeeDetailService service;
	private JButton btnCancel;
	private JButton btnDel;
	
	

	public EmployeeDetailUi(boolean isBtns, EmployeeDetailService service) {
		this.service = service;
		initialize(isBtns);
	}
	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);
		
		pBtns = new JPanel();
		if(isBtns) {		
		contentPane.add(pBtns, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		
	}else {
		contentPane.add(pBtns, BorderLayout.SOUTH);
		
		btnDel = new JButton("삭제");
		btnDel.addActionListener(this);
		pBtns.add(btnDel);
		
	}
	}
////////////////////////////////////////////
	public void setEmpNo(Employee empNo) {
		pItem.setTfempno(empNo);
	}
	public void setDetailItem(EmployeeDetail empDetail) {
		btnAdd.setText("수정");
		pItem.setItem(empDetail);
		
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetailService service = new EmployeeDetailService();
					EmployeeDetailUi frame = new EmployeeDetailUi(true, service);
//					frame.setEmpNo(new Employee(1365));
					frame.setEmpNo(new Employee(2106));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDel) {
			actionPerformedBtnDel(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
	}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail UpdateEmpDetail = pItem.getItem();
		service.modifyEmployeeDetail(UpdateEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "수정완료");
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
	//1. 입력된 empdetail 가져오기
	//2. service에 적용
		EmployeeDetail newEmpDetail = pItem.getItem();
		service.addEmployeeDetail(newEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "추가완료");
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pItem.clearTf();
		if(btnAdd.getText().contentEquals("수정")){
			btnAdd.setText("추가");
			
		}
		
	}
	protected void actionPerformedBtnDel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo()));
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "삭제완료");
		
	}
}
	








