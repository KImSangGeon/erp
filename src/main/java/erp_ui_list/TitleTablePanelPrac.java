package erp_ui_list;

import javax.swing.SwingConstants;

import erp_dto.Title;
import erp_ui_service.TitleService;
import erp_ui_service.TitleServicePrac;

@SuppressWarnings("serial")
public class TitleTablePanelPrac extends AbstractCustomTablePanel<Title> {
	
	private TitleServicePrac service ;
	
	@Override
	public String[] getColumnNames() {
		return new String [] {"직책번호", "직책명"};
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1);
		setTableCellWidth(100, 250);
	}

	@Override
	protected Object[] toArray(Title t) {
		return new Object[] {t.gettNo(),t.gettName()};
	}
	
	@Override
	public void initList() {
		list = service.showTitless();
	}
	
	public void setService(TitleServicePrac service) {
		this.service = service;
	}

}
