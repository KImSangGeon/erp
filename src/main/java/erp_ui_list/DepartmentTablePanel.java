package erp_ui_list;

import javax.swing.SwingConstants;

import erp_dto.Department;
import erp_dto.Title;
import erp_ui_Exception.NotSelectedException;
import erp_ui_service.DeptService;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {
	public DepartmentTablePanel() {
	}
	private DeptService service;
	
	
	@Override
	public void initList() {
		list = service.showDepartments();
	}

	@Override
	public String[] getColumnNames() {		
		return new String[] {"부서번호", "부서명", "위치"} ;
	}

	@Override
	protected void setAlignAndWidth() {
		
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		setTableCellWidth(100, 250);			
	}

	@Override
	protected Object[] toArray(Department t) {			
		return new Object[] {t.getDeptNo(),t.getDeptName(),t.getFloor()};
	}
	
	public void setService(DeptService service) {
		this.service = service;
	}

	@Override
	public Department getItem() {
		int row = table.getSelectedRow();
		int DeptNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Department(DeptNo)));		
	}

}
