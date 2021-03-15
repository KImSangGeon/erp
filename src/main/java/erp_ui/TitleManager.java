package erp_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp_dto.Employee;
import erp_dto.Title;
import erp_ui_Exception.InvalidCheckException;
import erp_ui_Exception.NotSelectedExceotion;
import erp_ui_Exception.SqlConstraintException;
import erp_ui_content.TitlePanel;
import erp_ui_list.TitleTablePanel;
import erp_ui_service.TitleService;

public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private TitlePanel pContent;
	private TitleTablePanel pList;
	private TitleService service;
	private JButton btnAdd;
	private JButton btnCancel;
	
	public TitleManager() {
		service = new TitleService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new TitlePanel();
		contentPane.add(pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}
	


	private JPopupMenu createPopupMenu() {
		JPopupMenu Popup = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(PopupMenuListener);
		Popup.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(PopupMenuListener);
		Popup.add(deleteItem);
		
		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(PopupMenuListener);
		Popup.add(empListByTitleItem);		
		
		return Popup;
	}
	
	
	ActionListener PopupMenuListener = new ActionListener() {
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().contentEquals("삭제")) {
				Title delTitle = pList.getItem();
				service.removeTitle(delTitle);
				pList.loadData();
				JOptionPane.showMessageDialog(null, delTitle + "삭제되었습니다.");
			}
			if(e.getActionCommand().contentEquals("수정")) {
				Title updateTitle = pList.getItem();
				pContent.setItem(updateTitle);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("동일 직책 사원 보기")) {
				/*
				 * 1. EmployeeDao -> selectEmployeeByTitle() 추가
				 * 2. EmployeeDaoImpl -> selectEmployeeByTitle() 구현
				 * 3. EmployeeDaoTest -> Test하기
				 * 4. TitleService -> EmployeeDaoImpl field 추가 및 메서드 추가
				 * 5. 아래 기능 추가
				 * 6. 예외찾아서 추가하기 (신규 직책 추가 시 NullPointException)
				 */
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
		}catch(NotSelectedExceotion | SqlConstraintException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}catch(Exception e2) {
			e2.printStackTrace();
		}
			
		}
	};
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		try {
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
		}catch(InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			pContent.clearTf();
		}catch(Exception e1) {
			e1.printStackTrace();
			
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Title UpdateItem = pContent.getItem();
		service.modifyTitle(UpdateItem);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, UpdateItem.gettName() + "정보가 수정되었습니다. ");
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = pContent.getItem();
		service.addTitle(title);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, title + "추가했습니다.");
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pContent.clearTf();
		
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
