package erp_ui_service;

import java.util.List;

import erp_dao.TitleDao;
import erp_dao_impl.TitleDaoImpl;
import erp_dto.Title;

public class TitleServicePrac {

	private TitleDao dao = TitleDaoImpl.getInstance();
	
	public List<Title> showTitless(){
		return dao.selectTitleByAll();
		
	}
	
	
}
