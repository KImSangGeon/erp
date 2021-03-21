package erp_ui_content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.omg.CORBA.IMP_LIMIT;

import com.toedter.calendar.JDateChooser;

import erp_dto.EmployeeDetail;
import erp_ui_Exception.InvalidCheckException;

public class EmployeeDetailPanelPrac extends AbstractContentPanel<EmployeeDetail> implements ActionListener {
	private JTextField tFempNo;
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JPanel pPic;
	private JButton btnAddPic;
	private JDateChooser dateHire;
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JLabel lblConfirm;
	
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	private JLabel lblPic;
	
	public EmployeeDetailPanelPrac() {

		initialize();
		loadPic(null);
	}
	private void loadPic(String imgFilePath) {
		Image changeImageIcon = null;
		if(imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "NoImage.jpg");
			changeImageIcon = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImageIcon = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon setIcon = new ImageIcon(changeImageIcon);
		lblPic.setIcon(setIcon);
		
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		pTop.setBorder(new TitledBorder(null, "직원정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pTop, BorderLayout.NORTH);
		
		pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel();
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.WEST);
		
		btnAddPic = new JButton("사진추가");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pContent = new JPanel();
		add(pContent, BorderLayout.CENTER);
		pContent.setLayout(new GridLayout(0, 2, 10, 2));
		
		JLabel lblempNo = new JLabel("사원번호");
		lblempNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblempNo);
		
		tFempNo = new JTextField();
		tFempNo.setEditable(false);
		pContent.add(tFempNo);
		tFempNo.setColumns(10);
		
		JLabel lblhireDate = new JLabel("입사일");
		lblhireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblhireDate);
		
		dateHire = new JDateChooser();
		pContent.add(dateHire);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		pGender.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rdbtnFemale = new JRadioButton("여성");
		rdbtnFemale.setSelected(true);
		pGender.add(rdbtnFemale);
		
		rdbtnMale = new JRadioButton("남성");
		pGender.add(rdbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass1);
		
		pass1 = new JPasswordField();
		pContent.add(pass1);
		
		JLabel labPass2 = new JLabel("비밀번호확인");
		labPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(labPass2);
		
		pass2 = new JPasswordField();
		pContent.add(pass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblConfirm = new JLabel("New label");
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setFont(new Font("굴림", Font.BOLD, 22));
		pContent.add(lblConfirm);
	}
	@Override
	public void setItem(EmployeeDetail item) {
		tFempNo.setText(String.valueOf(item.getEmpNo()));
		byte[] iconBytes =item.getPic();
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
		int empNo = Integer.parseInt(tFempNo.getText().trim());
		boolean gender = rdbtnFemale.isSelected()? true : false;
		Date hiredate = dateHire.getDate();
		String pass = String.valueOf(pass1.getPassword());
		byte[] pic = getImage();
		return new EmployeeDetail(empNo, gender, hiredate, pass, pic);
		}
	private byte[] getImage() {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			//이미지 생성 icon -> image
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
		if(lblConfirm.getText().equals("불일치")){
			throw new InvalidCheckException("패스워드 불일치");
		}
	}
	@Override
	public void clearTf() {
		loadPic(null);
		dateHire.setDate(new Date());
		rdbtnFemale.setSelected(true);
		pass1.setText("");
		pass2.setText("");
		lblConfirm.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG & GIF images", "jpg", "png", "gif");
	
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
			String pw1 = new String(pass1.getPassword());
			String pw2 = new String(pass2.getPassword());
			
			if(pw1.equals(pw2)) {
				lblConfirm.setText("일치");
			}else {
				lblConfirm.setText("불일치");
			}
			
		}
	};
}
