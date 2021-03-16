package erp_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp_dto.Title;
import erp_ui_Exception.InvalidCheckException;
import erp_ui_Exception.SqlConstraintException;
import erp_ui_content.AbstractContentPanel;
import erp_ui_content.TitlePanel;
import erp_ui_list.AbstractCustomTablePanel;
import erp_ui_list.TitleTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManagerUi<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;

	protected JButton btnAdd;
	protected JButton btnClear;

	
	public AbstractManagerUi() {
		
		setService();  //service 연결
		
		initialize();
		
 		TableLoadData();  //component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = createContentPanel();
		contentPane.add(pContent);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);
		
		pList = createTablePanel();		
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	

	private JPopupMenu createPopupMenu() {
		JPopupMenu Popup = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		Popup.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		Popup.add(deleteItem);
		
		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(this);
		Popup.add(empListByTitleItem);		
		
		return Popup;
	}
	
	public void actionPerformed(ActionEvent e) {	
		try {
			if(e.getSource() instanceof JMenuItem) {
			if(e.getActionCommand().contentEquals("삭제")) {
				actionPerformedMenuDelete();
			}
			if(e.getActionCommand().contentEquals("수정")) {
				actionPerformedMenuUpdate();
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
				actionPerformedMenuGubun();
			 }
			}else {			
			if (e.getSource() == btnClear) {
				actionPerformedBtnCancel(e);
			}
		
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
				}
			}
		}
		}catch(InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			pContent.clearTf();
		}catch(Exception e1) {
			e1.printStackTrace();
			
		}
	}
	
	protected abstract void setService(); 	
	
	protected abstract void TableLoadData();
	
	protected abstract AbstractContentPanel<T> createContentPanel();	
	
	protected abstract AbstractCustomTablePanel<T> createTablePanel();	
	
	protected abstract void actionPerformedMenuGubun(); 
	
	protected abstract void actionPerformedMenuUpdate();
	
	protected abstract void actionPerformedMenuDelete();
	
	protected abstract void actionPerformedBtnUpdate(ActionEvent e); 
	
	protected abstract void actionPerformedBtnAdd(ActionEvent e);
	
	protected void actionPerformedBtnCancel(ActionEvent e) {		
		pContent.clearTf();
		
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
