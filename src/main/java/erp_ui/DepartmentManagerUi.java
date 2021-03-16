package erp_ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp_dto.Department;
import erp_dto.Employee;
import erp_ui_content.AbstractContentPanel;
import erp_ui_content.DeptPanel;
import erp_ui_list.AbstractCustomTablePanel;
import erp_ui_list.DepartmentTablePanel;
import erp_ui_service.DeptService;

@SuppressWarnings("serial")
public class DepartmentManagerUi extends AbstractManagerUi<Department> {
	
	private DeptService service;
	
	@Override
	protected void setService() {
		service = new DeptService();
		
	}

	@Override
	protected void TableLoadData() {
		((DepartmentTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Department> createContentPanel() {		
		return new DeptPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> createTablePanel() {
		return new DepartmentTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
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

	@Override
	protected void actionPerformedMenuUpdate() {
		Department updateDept = pList.getItem();
		pContent.setItem(updateDept);
		btnAdd.setText("수정");
		JOptionPane.showMessageDialog(null, "수정하자");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Department delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + "삭제되었습니다.");	
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Department updateDept = pContent.getItem();
		service.modifyDept(updateDept);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() +"수정되었습니다");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = pContent.getItem();
		service.addDepartment(department);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, department + "추가했습니다.");
	}

}
