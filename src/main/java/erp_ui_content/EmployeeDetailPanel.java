package erp_ui_content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import erp_dto.Employee;
import erp_dto.EmployeeDetail;
import erp_ui_Exception.InvalidCheckException;

public class EmployeeDetailPanel extends AbstractContentPanel<EmployeeDetail> implements ActionListener {
	
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	
	private JLabel lblPic;
	private JButton btnAddPic;
	private JDateChooser dateHire;
	private JRadioButton rdbtnFemale;
	private JLabel lblConfirm;
	private JPasswordField tFPass1;
	private JPasswordField tFPass2;
	private JTextField tFEmpNo;
	

	public EmployeeDetailPanel() {
		
		initialize();
		loadPic(null);
	}
	private void loadPic(String imgFilePath) {
		Image changeImageIcon =null;
		if(imgFilePath == null) {
			 ImageIcon icon = new ImageIcon(imgPath + "NoImage.jpg");
			changeImageIcon = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImageIcon = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);	
		}
		ImageIcon changeIcon = new ImageIcon(changeImageIcon);
		lblPic.setIcon(changeIcon);
	}
	private void initialize() {
		setBorder(new TitledBorder(null, "사원세부정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel();
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.NORTH);
		
		btnAddPic = new JButton("사진 추가");
		btnAddPic.addActionListener(this);
		btnAddPic.setVerticalAlignment(SwingConstants.BOTTOM);
		pPic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContet = new JPanel();
		pItem.add(pContet);
		pContet.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblEmpNo = new JLabel("사원번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContet.add(lblEmpNo);
		
		tFEmpNo = new JTextField();
		tFEmpNo.setEditable(false);
		pContet.add(tFEmpNo);
		tFEmpNo.setColumns(10);
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContet.add(lblHireDate);
		
		dateHire = new JDateChooser();
		pContet.add(dateHire);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContet.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContet.add(pGender);
		
		rdbtnFemale = new JRadioButton("여자");
		rdbtnFemale.setSelected(true);
		rdbtnFemale.setVerticalAlignment(SwingConstants.TOP);
		pGender.add(rdbtnFemale);
		
		rdbtnMale = new JRadioButton("남자");
		rdbtnMale.setVerticalAlignment(SwingConstants.BOTTOM);
		pGender.add(rdbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContet.add(lblPass1);
		
		tFPass1 = new JPasswordField();
		tFPass1.getDocument().addDocumentListener(listener);
		pContet.add(tFPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContet.add(lblPass2);
		
		tFPass2 = new JPasswordField();
		tFPass2.getDocument().addDocumentListener(listener);
		pContet.add(tFPass2);
		
		JPanel pSpace = new JPanel();
		pContet.add(pSpace);
		
		lblConfirm = new JLabel("New label");
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("굴림", Font.BOLD, 20));
		pContet.add(lblConfirm);
		
	}
	public void setTfempno(Employee empNo) {
		tFEmpNo.setText(String.valueOf(empNo.getEmpNo()));

	}
	
	@Override
	public void setItem(EmployeeDetail item) {
		tFEmpNo.setText(String.valueOf(item.getEmpNo()));
		byte[] iconBytes = item.getPic();
		ImageIcon icon = new ImageIcon(iconBytes);
		lblPic.setIcon(icon);
		dateHire.setDate(item.getHiredate());
		if(item.isGender()) {
			rdbtnFemale.setSelected(true);
		}else {
			rdbtnMale.setSelected(true);
		}
	}

	@Override
	public EmployeeDetail getItem() {
		validCheck();		
		int empNo = Integer.parseInt(tFEmpNo.getText().trim());
		
		boolean gender = rdbtnFemale.isSelected()? true :false;
		
		Date hiredate = dateHire.getDate();
	
		String pass = String.valueOf(tFPass1.getPassword());
	
		byte[] pic = getImage();
		return new EmployeeDetail(empNo, gender, hiredate, pass, pic);
	}
	
	private byte[] getImage() {		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			//이미지 생성, 만들어주기   icon-> image
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(icon.getImage(), 0, 0, null);
			g2.dispose();
			//입출력
			ImageIO.write(bi, "png", baos);
			return baos.toByteArray();
			
		} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}

	@Override
	public void validCheck() {
		if(lblConfirm.getText().equals("불일치")) {
			throw new InvalidCheckException("패스워드 불일치");
		}
		
	}

	@Override
	public void clearTf() {
		loadPic(null);
		dateHire.setDate(new Date());
		rdbtnFemale.setSelected(true);
		tFPass1.setText("");
		tFPass2.setText("");
		lblConfirm.setText("");
		
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter
				("JPG & PNG & GIF images", "jpg", "png", "gif");
		
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int res = chooser.showOpenDialog(null);
		if(res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았음", "경고", JOptionPane.WARNING_MESSAGE);
			return;
			
		}
		String chooserFilePath = chooser.getSelectedFile().getPath();
		loadPic(chooserFilePath);	
	}
	
	DocumentListener listener = new DocumentListener() {		
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
		}
		
		private void getMessage() {
			String pw1 = new String(tFPass1.getPassword());
			String pw2 = String.valueOf(tFPass2.getPassword());
			if(pw1.equals(pw2)) {
				lblConfirm.setText("일치");
			}else {
				lblConfirm.setText("불일치");
			}
			
			
		}
	};
	private JRadioButton rdbtnMale;
}
