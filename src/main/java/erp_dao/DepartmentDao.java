package erp_dao;

import java.util.List;

import erp_dto.Department;

public interface DepartmentDao {

	List<Department> selectDepartmentByAll();
	Department selectDepartmentByNo(Department department);
	
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(int deptNo);
}
