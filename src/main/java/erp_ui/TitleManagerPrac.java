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
import erp_ui_Exception.NotSelectedException;
import erp_ui_Exception.SqlConstraintException;
import erp_ui_content.TitlePanel;
import erp_ui_list.TitleTablePanelPrac;
import erp_ui_service.TitleServicePrac;

public class TitleManagerPrac extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private TitleServicePrac service;
	private JButton btnAdd;
	private TitlePanel pContent;
	private TitleTablePanelPrac pList;
	private JButton btnCancel;
	
	public TitleManagerPrac() {
		service = new TitleServicePrac();
		initialize();
	}
	private void initialize() {
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		pList = new TitleTablePanelPrac();
		pList.setService(service);
		pList.loadData();	
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(PopupMenuListener);
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(PopupMenuListener);
		popMenu.add(deleteItem);
		
		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원보기");
		empListByTitleItem.addActionListener(PopupMenuListener);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}
	
	ActionListener PopupMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			if(e.getActionCommand().contentEquals("삭제")) {
				Title delTitle = pList.getItem();
				service.removeTitle(delTitle);
				pList.loadData();
				JOptionPane.showMessageDialog(null, delTitle +"삭제되었습니다.");
				
			}
			if(e.getActionCommand().equals("수정")) {
				Title updateTitle = pList.getItem();
				service.modifyTitle(updateTitle);
				pContent.setItem(updateTitle);
				btnAdd.setText("수정");
			}
			if(e.getActionCommand().contentEquals("동일 직책 사원보기")) {
				Title title = pList.getItem();
				List<Employee> list = service.showEmployeeGroupByTitle(title);
				
				if(list == null) {
					JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				List<String> strList = list.parallelStream()
						.map(s->{return String.format("%s(%d)", s.getEmpName(), s.getEmpNo());})
								.collect(Collectors.toList());
								
				JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);							
			}
			}catch(NotSelectedException | SqlConstraintException e2) {
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
			if(e.getActionCommand().contentEquals("추가")){
				actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
		}catch(InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			pContent.clearTf();
		}catch (Exception e1) {
			e1.printStackTrace();
			
			
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Title UpdateItem = pContent.getItem();
		service.modifyTitle(UpdateItem);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, UpdateItem.gettName() + "정보가 수정되었습니다.");
		
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
		
		if(btnAdd.equals("수정")) {
			btnAdd.setText("추가");
		}
	}
}


