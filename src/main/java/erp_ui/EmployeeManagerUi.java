package erp_ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import erp_dto.Employee;
import erp_dto.EmployeeDetail;
import erp_ui_content.AbstractContentPanel;
import erp_ui_content.EmployeeDetailPanel;
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
		Employee emp = pList.getItem();
		EmployeeDetail empDetail = detailservice.selectEmployeeDetailByEmpNo(emp);
		//나중에 처리
				if(empDetail == null) {
					JOptionPane.showMessageDialog(null, "세부정보 없음");
					return;
				}
		JFrame subFrame = new JFrame("사원 세부 정보");		
		subFrame.setBounds(this.getWidth(), this.getHeight(), 450, 500);
		
		EmployeeDetailPanel subDetailPanel = new EmployeeDetailPanel();
		subDetailPanel.setItem(empDetail);
		
		subFrame.add(subDetailPanel, BorderLayout.CENTER);
		subFrame.setVisible(true);
				
//		throw new UnsupportedOperationException("구현안할거임");
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
