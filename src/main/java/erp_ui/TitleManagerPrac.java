package erp_ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_ui_list.TitleTablePanelPrac;
import erp_ui_service.TitleServicePrac;
import erp_ui_content.TitlePanel;

public class TitleManagerPrac extends JFrame {

	private JPanel contentPane;
	private TitleTablePanelPrac pList;
	private TitleServicePrac service;
	
	public TitleManagerPrac() {
		service = Tit
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
		
		TitlePanel panel = new TitlePanel();
		contentPane.add(panel);
				
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		JButton btnAdd = new JButton("추가");
		pBtns.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		pBtns.add(btnCancel);		
		
		
		TitleTablePanelPrac pPanel = new TitleTablePanelPrac();
		contentPane.add(pPanel);
	}

}
