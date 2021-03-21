package erp_ui_service;

import java.util.List;

import erp_dao.DepartmentDao;
import erp_dao.EmployeeDao;
import erp_dao.TitleDao;
import erp_dao_impl.DepartmentImpl;
import erp_dao_impl.EmployeeImpl;
import erp_dao_impl.TitleDaoImpl;
import erp_dto.Department;
import erp_dto.Employee;
import erp_dto.Title;

public class EmployeeServicePrac {
	private DepartmentDao deptDao = DepartmentImpl.getInstance();
	private EmployeeDao empDao = EmployeeImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	
	public List<Employee> showEmpLists(){
		return empDao.selectEmployeeByAll();
	}
	public List<Department> showDeptList(){
		return deptDao.selectDepartmentByAll();
	}
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	public List<Employee> showEmployeeGroupByDept(Department dept){
		return empDao.selectEmployeeByDept(dept);
	}
	
}
