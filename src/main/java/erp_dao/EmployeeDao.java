package erp_dao;

import java.util.List;

import erp_dto.Department;
import erp_dto.Employee;
import erp_dto.Title;

public interface EmployeeDao {
	
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByNo(Employee employee);
	
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	
	List<Employee> selectEmployeeByTitle(Title title);
	List<Employee> selectEmployeeByDept(Department department);

}
