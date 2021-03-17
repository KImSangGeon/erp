package erp_ui_service;

import erp_dao.EmployeeDetailDao;
import erp_dao_impl.EmployeeDetailDaoImpl;
import erp_dto.Employee;
import erp_dto.EmployeeDetail;

public class EmployeeDetailService {
	private EmployeeDetailDao empDetailDao = EmployeeDetailDaoImpl.getInstance();
	

	public EmployeeDetail selectEmployeeDetailByEmpNo(Employee	employee) {
		return empDetailDao.selectEmployeeDetailByNo(employee);
	}
	
	public void addEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.insertEmployeeDetail(empDetail);
	}
	
	public void modifyEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.updateEmployeeDetail(empDetail);
	}
	public void removeEmployeeDetail(Employee employee) {
		empDetailDao.deleteEmployeeDetail(employee);

	}
}
