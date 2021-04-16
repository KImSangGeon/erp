package erp_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.NotSerializableException;
import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.standard.JobPriority;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp_dto.Department;
import erp_dto.Employee;
import erp_ui_Exception.NotSelectedException;
import erp_ui_Exception.SqlConstraintException;
import erp_ui_content.DeptPanel;
import erp_ui_list.DepartmentTablePanelPrac;
import erp_ui_service.DeptServicePrac;

public class DeptManagerPrac extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DeptServicePrac service;
	private DepartmentTablePanelPrac pList;
	private JButton btnAdd;
	private JButton btnCancel;
	private DeptPanel pContent;

	
	public DeptManagerPrac() {
		service = new DeptServicePrac();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new DeptPanel();
		contentPane.add(pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		pList = new DepartmentTablePanelPrac();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(PopupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(PopupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원보기");
		empListByDeptItem.addActionListener(PopupMenuListener);
		popMenu.add(empListByDeptItem);
		return popMenu;
	}
	
	ActionListener PopupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			if(e.getActionCommand().contentEquals("삭제")) {
				Department delDept = pList.getItem();
				service.removeDept(delDept);
				pList.loadData();
				JOptionPane.showMessageDialog(null, delDept + "삭제되었습니다.");
				
			}
			if(e.getActionCommand().contentEquals("수정")) {
				Department updateDept = pList.getItem();
				service.modifyDept(updateDept);
				pContent.setItem(updateDept);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("동일 부서 사원보기")) {
				Department department = pList.getItem();
				List<Employee> list = service.showEmployeeGroupByDept(department);
				
				if(list == null) {
					JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
					return;					
				}
				List<String> strList = list.parallelStream()
						.map(s->{return String.format("%s(%d)", s.getEmpName(), s.getEmpNo());})
						.collect(Collectors.toList());
				
				JOptionPane.showMessageDialog(null, strList, "동일 직책사원", JOptionPane.INFORMATION_MESSAGE );
			}
			}catch(NotSelectedException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	};
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
			}else{
			actionPerformedBtnUpdate(e);
			}
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department UpdateItem = pContent.getItem();
		service.addDept(UpdateItem);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, UpdateItem.getDeptName() + "정보가 수정되었습니다.");
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getItem();
		service.addDept(department);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, department.getDeptName() + "추가했습니다.");
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		
		if(btnAdd.equals("수정")) {
			btnAdd.setText("추가");
		}
		
		
	}
}
