package erp_ui_service;

import java.util.List;

import erp_dao.DepartmentDao;
import erp_dao.EmployeeDao;
import erp_dao_impl.DepartmentImpl;
import erp_dao_impl.EmployeeImpl;
import erp_dto.Department;
import erp_dto.Employee;

public class DeptService {
	private DepartmentDao dao = DepartmentImpl.getInstance();
	private EmployeeDao EmpDao = EmployeeImpl.getInstance();
	
	public List<Department> showDepartments(){
		return dao.selectDepartmentByAll();
	}
	public void addDepartment(Department department) {
		dao.insertDepartment(department);
	}
	public void removeTitle(Department department) {
		dao.deleteDepartment(department.getDeptNo());	
	}
	public void modifyDept(Department department) {
		dao.updateDepartment(department);
	}
	public List<Employee> showEmployeeGroupByDept(Department department){
		return EmpDao.selectEmployeeByDept(department);
	}

}
