package erp_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import erp_ui_content.TitlePanel;
import erp_ui_list.TitleTablePanelPrac;
import erp_ui_service.TitleServicePrac;

public class TitleManagerPrac extends JFrame {

	private JPanel contentPane;
	private TitleTablePanelPrac pList;
	private TitlePanel pContet;
	private TitleServicePrac service;


	
	public TitleManagerPrac() {
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
		
		pContet = new TitlePanel();
		contentPane.add(pContet);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);
		
		JButton btnAdd = new JButton("추가");
		pBtns.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		pBtns.add(btnCancel);
		
		
		
		TitleTablePanelPrac panel = new TitleTablePanelPrac();
		contentPane.add(panel);
	}

}
