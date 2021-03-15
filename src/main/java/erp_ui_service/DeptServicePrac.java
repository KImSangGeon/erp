package erp_ui_service;

import java.util.List;

import erp_dao.DepartmentDao;
import erp_dao_impl.DepartmentImpl;
import erp_dto.Department;

public class DeptServicePrac {
	 private DepartmentDao dao = DepartmentImpl.getInstance();
	 
	 public List<Department> showDepartmentss(){
		return dao.selectDepartmentByAll();
		 
	 }
}
