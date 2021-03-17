package erp_ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp_dto.Employee;
import erp_dto.EmployeeDetail;
import erp_ui_content.AbstractContentPanel;
import erp_ui_content.EmployeePanel;
import erp_ui_list.AbstractCustomTablePanel;
import erp_ui_list.EmployeeTablePanel;
import erp_ui_service.EmployeeDetailService;
import erp_ui_service.EmployeeService;

@SuppressWarnings("serial")
public class EmployeeManagerUi extends AbstractManagerUi<Employee> {
	private EmployeeService service;
	private EmployeeDetailService detailservice;

	public EmployeeManagerUi() {
		empListByTitleItem.setText(AbstractManagerUi.EMP_MENU);
	}

	@Override
	protected void setService() {
		service = new EmployeeService();
		detailservice = new EmployeeDetailService();
	}

	@Override
	protected void TableLoadData() {
		((EmployeeTablePanel) pList).setService(service);
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
		Employee emp = pList.getItem();
		EmployeeDetail empDetail = detailservice.selectEmployeeDetailByEmpNo(emp);
		// 나중에 처리
		EmployeeDetailUi frame;
		if (empDetail == null) {
			frame = new EmployeeDetailUi(true, detailservice);
		} else {
			frame = new EmployeeDetailUi(false, detailservice);
			frame.setDetailItem(empDetail);
		}
		frame.setEmpNo(emp);
		frame.setVisible(true);
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
		JOptionPane.showMessageDialog(null, updateEmp.getEmpName() + "수정되었습니다");
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
