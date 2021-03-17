package erp_dao;

import java.util.List;

import erp_dto.Employee;
import erp_dto.EmployeeDetail;

public interface EmployeeDetailDao {
	
	EmployeeDetail selectEmployeeDetailByNo(Employee employee);
	
	int insertEmployeeDetail(EmployeeDetail empDetail);
	int updateEmployeeDetail(EmployeeDetail empDetail);
	int deleteEmployeeDetail(Employee employee);
}
