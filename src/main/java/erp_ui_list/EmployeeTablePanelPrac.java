package erp_ui_list;

import javax.swing.SwingConstants;

import erp_dto.Employee;
import erp_ui_Exception.NotSelectedException;
import erp_ui_service.EmployeeServicePrac;

@SuppressWarnings("serial")
public class EmployeeTablePanelPrac extends AbstractCustomTablePanel<Employee> {
	public EmployeeTablePanelPrac() {
	}
	private EmployeeServicePrac service;
	
	public void setService(EmployeeServicePrac service) {
		this.service = service;
	}

	@Override
	public Employee getItem() {
		int row = table.getSelectedRow();
		int EmpNo = (int) table.getValueAt(row, 0);

		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new Employee(EmpNo)));
	}

	@Override
	public void initList() {
		list = service.showEmpLists();		
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"직원번호", "직원이름", "직책", "직속상사", "급여", "부서"};
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		setTableCellAlign(SwingConstants.RIGHT, 4);
		
		setTableCellWidth(100, 250, 200, 300, 300, 200);
		
	}

	@Override
	protected Object[] toArray(Employee t) {
		return new Object[] {
				t.getEmpNo(),
				t.getEmpName(),
				String.format("%s(%d)", t.getTitle().gettName(), t.getTitle().gettNo()),
				t.getManager().getEmpNo()==0? "" : String.format("%s(%d)", t.getManager().getEmpName(), t.getManager().getEmpNo()),
				String.format("%,d", t.getSalary()),
				String.format("%s(%d)", t.getDept().getDeptName(), t.getDept().getDeptNo())
		};
	}

}
