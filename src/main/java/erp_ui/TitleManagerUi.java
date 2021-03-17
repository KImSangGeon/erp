package erp_ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp_dto.Employee;
import erp_dto.Title;
import erp_ui_content.AbstractContentPanel;
import erp_ui_content.TitlePanel;
import erp_ui_list.AbstractCustomTablePanel;
import erp_ui_list.TitleTablePanel;
import erp_ui_service.TitleService;

@SuppressWarnings("serial")
public class TitleManagerUi extends AbstractManagerUi<Title> {
	
	private TitleService service;
	
	public TitleManagerUi() {
		empListByTitleItem.setText(AbstractManagerUi.TITLE_MENU);
	}
	
	@Override
	protected void setService() {
		service = new TitleService();
	}

	@Override
	protected void TableLoadData() {
		((TitleTablePanel)pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<Title> createContentPanel() {		
		return new TitlePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Title> createTablePanel() {
		return new TitleTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		Title title = pList.getItem();
		List<Employee> list = service.showEmployeeGroupByTitle(title);
		
		if(list ==null) {
			JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		List<String> strList = list
				.parallelStream()
				.map(s->{return String.format("%s(%d)", s.getEmpName(), s.getEmpNo());})
				.collect(Collectors.toList());
		
		JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		Title updateTitle = pList.getItem();
		pContent.setItem(updateTitle);
		btnAdd.setText("수정");
		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		Title delTitle = pList.getItem();
		service.removeTitle(delTitle);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delTitle + "삭제되었습니다.");
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Title UpdateItem = pContent.getItem();
		service.modifyTitle(UpdateItem);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, UpdateItem.gettName() + "정보가 수정되었습니다. ");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
		service.addTitle(title);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, title + "추가했습니다.");
		
		
	}

}
