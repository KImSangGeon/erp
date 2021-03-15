package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import erp_ui_list.TitleTablePanelPrac;
import erp_ui_list.DepartmentTablePanelPrac;

public class TestFramePra extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFramePra frame = new TestFramePra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFramePra() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		TitleTablePanelPrac titlePanel = new TitleTablePanelPrac();
		titlePanel.loadData();
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(titlePanel);
		
		DepartmentTablePanelPrac deptPanel = new DepartmentTablePanelPrac();
		deptPanel.loadData();
		contentPane.add(deptPanel);
	}

}
