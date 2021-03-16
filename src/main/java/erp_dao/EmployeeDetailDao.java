package erp_dao;

import erp_dto.Employee;
import erp_dto.EmployeeDetail;

public interface EmployeeDetailDao {
	
	EmployeeDetail selectEmployeeDetailByNo(Employee employee);
	
	int insertEmployeeDetail(EmployeeDetail empDetail);
	int updateEmployeeDetail(EmployeeDetail empDetail);
	int deliteEmployeeDetail(Employee employee);
}
