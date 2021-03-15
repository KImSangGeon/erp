package erp_ui_service;

import java.util.List;

import erp_dao.EmployeeDao;
import erp_dao.TitleDao;
import erp_dao_impl.EmployeeImpl;
import erp_dao_impl.TitleDaoImpl;
import erp_dto.Employee;
import erp_dto.Title;

public class TitleService {
	private TitleDao dao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeImpl.getInstance();
	
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
	}
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}	
	public void modifyTitle(Title title) {
		dao.updateTitle(title);
	}
	public List<Employee> showEmployeeGroupByTitle(Title title){
		return empDao.selectEmployeeByTitle(title);
	}
	
	
}



