package erp_ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

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
import erp_ui_Exception.NotSelectedExceotion;
import erp_ui_Exception.SqlConstraintException;
import erp_ui_content.DeptPanel;
import erp_ui_content.InterfaceItem;
import erp_ui_list.AbstractCustomTablePanel;
import erp_ui_list.DepartmentTablePanel;
import erp_ui_service.DeptService;

@SuppressWarnings("serial")
public class DeptManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private InterfaceItem<Department> pContent;
	private DepartmentTablePanel pList;
	private DeptService service;
	private JButton btnAdd;
	private JButton btnCancel;

	
	public DeptManager() {
		service = new DeptService();
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
		contentPane.add((DeptPanel) pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		pList =  new DepartmentTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = creatPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu creatPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListener);
		popMenu.add(deleteItem);		
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 직책 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListener);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}
	
	ActionListener popupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
		if(e.getActionCommand().contentEquals("삭제")) {
			Department delTitle = pList.getItem();
			service.removeTitle(delTitle);
			pList.loadData();
			JOptionPane.showMessageDialog(null, delTitle + "삭제되었습니다.");			
		}
		if(e.getActionCommand().contentEquals("수정")) {
			Department updateDept = pList.getItem();
			pContent.setItem(updateDept);
			btnAdd.setText("수정");
			JOptionPane.showMessageDialog(null, "수정하자");
		}
		if(e.getActionCommand().contentEquals("동일 직책 사원 보기")) {
			/*
			 * 1. EmployeeDao -> selectEMployeeByTitle()추가
			 * 2. EmployeeDaoImpl -> selectEmployeeByTitle() 구현
			 * 3. EmployeeDaoTest -> Test 하기 
			 * 4. TitleService -> EmployeeDaoImpl field 추가 및 메서드 추가
			 * 5. 아래 기능 추가
			 * 6. 예외에서 찾아서 추가하기 (신규 직책 추가 시 NullPointException)				  
			 */
			
			Department department = pList.getItem();
			List<Employee> list = service.showEmployeeGroupByDept(department);
			
			if(list ==null) {
				JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);;
				return;
			}
			List<String> strList = list
					.parallelStream()
					.map(s->{return String.format("%s(%d)", s.getEmpName(), s.getEmpNo());})
					.collect(Collectors.toList());
			
			JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
			}
		
		}catch(NotSelectedExceotion | SqlConstraintException e2) {
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
			}else {
				actionPerformedBtnUpdate(e);
			}
			
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department updateDept = pContent.getItem();
		service.modifyDept(updateDept);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() +"수정되었습니다");
		
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getItem();
		service.addDepartment(department);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, department + "추가했습니다.");
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
	}
}
