package erp_ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp_dto.Employee;
import erp_ui_content.AbstractContentPanel;
import erp_ui_content.EmployeePanel;
import erp_ui_list.AbstractCustomTablePanel;
import erp_ui_list.EmployeeTablePanel;
import erp_ui_service.EmployeeService;

@SuppressWarnings("serial")
public class EmployeeManagerUi extends AbstractManagerUi<Employee> {
	private EmployeeService service;
	
	@Override
	protected void setService() {
		service = new EmployeeService();
	}

	@Override
	protected void TableLoadData() {
		((EmployeeTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Employee> createContentPanel() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		return empPanel;
	}

	@Override
	protected AbstractCustomTablePanel<Employee> createTablePanel() {
		return new EmployeeTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		throw new UnsupportedOperationException("구현안할거임");
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Employee updateEmp = pList.getItem();
		pContent.setItem(updateEmp);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Employee delEmp = pList.getItem();
		service.removeEmployee(delEmp);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delEmp + "삭제되었습니다.");
		
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Employee updateEmp = pContent.getItem();
		service.modifyEmployee(updateEmp);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateEmp.getEmpName() +"수정되었습니다");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee employee = pContent.getItem();
		service.addEmployee(employee);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, employee + "추가했습니다.");
		
	}

}
