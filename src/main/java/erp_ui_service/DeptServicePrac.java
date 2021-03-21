package erp_ui_service;

import java.util.List;

import erp_dao.DepartmentDao;
import erp_dao.EmployeeDao;
import erp_dao_impl.DepartmentImpl;
import erp_dao_impl.EmployeeImpl;
import erp_dto.Department;
import erp_dto.Employee;

public class DeptServicePrac {
	 private DepartmentDao dao = DepartmentImpl.getInstance();
	 private EmployeeDao empDao = EmployeeImpl.getInstance();
	 
	 public List<Department> showDepartmentss(){
		return dao.selectDepartmentByAll();
	 }
	 public void addDept(Department department) {
		 dao.insertDepartment(department);
	 }
	 public void removeDept(Department department) {
		 dao.deleteDepartment(department.getDeptNo());
	 }
	 public void modifyDept(Department department) {
		 dao.updateDepartment(department);
	 }
	 public List<Employee> showEmployeeGroupByDept(Department department){
		 return empDao.selectEmployeeByDept(department);
	 }
	 
		 
	 }

