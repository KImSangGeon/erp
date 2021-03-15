package erp_ui_list;

import javax.swing.SwingConstants;

import erp_dto.Department;
import erp_ui_service.DeptServicePrac;

@SuppressWarnings("serial")
public class DepartmentTablePanelPrac extends AbstractCustomTablePanel<Department> {
	
	private DeptServicePrac service = new DeptServicePrac(); 
	
	@Override
	public void initList() {
		list = service.showDepartmentss();
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"부서번호", "부서명", "위치"};
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

}
