package erp_ui_content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp_dto.Title;
import erp_ui_Exception.InvalidCheckException;

public class TitlePanel extends InterfaceItem<Title> {
	private JTextField tFNo;
	private JTextField tFName;

	
	public TitlePanel() {

		initialize();
	}
	
	@Override
	public void initialize() {
		setBorder(new TitledBorder(null, "직책정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("직책번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tFNo = new JTextField();
		add(tFNo);
		tFNo.setColumns(10);
		
		JLabel lblName = new JLabel("직책이름");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tFName = new JTextField();
		tFName.setColumns(10);
		add(tFName);
	}
	
	
	@Override
	public void setItem(Title t) {
		tFNo.setText(t.gettNo()+ "");
		tFName.setText(t.gettName());
	}
	@Override
	public Title getItem() {
		validCheck();
		int tNo = Integer.parseInt(tFNo.getText().trim());
		String tName = tFName.getText().trim();
		return new Title(tNo,tName);

	}
		

	@Override
	public void validCheck() {
		if(tFNo.getText().contentEquals("") || tFName.getText().equals("")) {
			throw new InvalidCheckException();
	}
	}
	@Override
	public void clearTf() {
		tFNo.setText("");
		tFName.setText("");		
	}

}
