package erp_ui_service;

import java.util.List;

import javax.swing.JPanel;

import erp_dao.DepartmentDao;
import erp_dao.EmployeeDao;
import erp_dao.TitleDao;
import erp_dao_impl.DepartmentImpl;
import erp_dao_impl.EmployeeImpl;
import erp_dao_impl.TitleDaoImpl;
import erp_dto.Department;
import erp_dto.Employee;
import erp_dto.Title;

@SuppressWarnings("serial")
public class EmployeeService extends JPanel {
	private DepartmentDao deptDao = DepartmentImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();	
	private EmployeeDao EmpDao = EmployeeImpl.getInstance();
	
	
	public List<Employee> showEmpList(){
		return EmpDao.selectEmployeeByAll();
	}
	
	public List<Department> showDeptList(){
		return deptDao.selectDepartmentByAll();
	}
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	public List<Employee> showEmployeeGroupByDept(Department dept){
		return EmpDao.selectEmployeeByDept(dept);
	}
	

}
