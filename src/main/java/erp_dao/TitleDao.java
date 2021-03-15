package erp_dao;

import java.util.List;

import erp_dto.Title;

/**
 * 	C(insert)
 *  R(select, select where)
 *  U(update)
 *  D(delete)
 *
 */
public interface TitleDao {
	
	List<Title> selectTitleByAll();
	Title selectTitleByNo(Title title);    // int tNo해도 상관없음 (매개변수)
	
	int insertTitle(Title title);
	int updateTitle(Title title);
	int deleteTitle(int titleNo);
		
}
