package erp_ui_content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp_dto.Department;
import erp_dto.Employee;
import erp_dto.Title;
import erp_ui_Exception.InvalidCheckException;
import erp_ui_service.EmployeeService;

@SuppressWarnings("serial")
public class EmployeePanel extends InterfaceItem<Employee> implements ItemListener  {
	private JPanel pItem;
	private JLabel lblNo;
	private JTextField tFNo;
	private JLabel lblName;
	private JTextField tFName;
	private JLabel lblTitle;
	private JComboBox <Title> cmbTitle;
	private JLabel lblManager;
	private JComboBox<Employee> cmbManager;
	private JLabel lblSalary;
	private JSpinner spinSalary;
	private JLabel lblDept;
	private JComboBox<Department> cmbDept;
	
	private EmployeeService service;

	
	public EmployeePanel() {

		initialize();
	}
	
	public void setService(EmployeeService service) {
		this.service = service;
		
		List<Department> deptList = service.showDeptList();
		DefaultComboBoxModel deptModel = new DefaultComboBoxModel<Department>(new Vector<>(deptList));
		cmbDept.setModel(deptModel);
		
		List<Title> titleList = service.showTitleList();
		DefaultComboBoxModel titleModel = new DefaultComboBoxModel<Title>(new Vector<>(titleList));
		cmbTitle.setModel(titleModel);
			
		
		cmbDept.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
	}
	
	
	@Override
	public void initialize() {
		setBorder(new TitledBorder(null, "직원정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		pItem = new JPanel();
		add(pItem);
		pItem.setLayout(new GridLayout(0, 2, 10, 0));
		
		lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblNo);
		
		tFNo = new JTextField();
		tFNo.setColumns(10);
		pItem.add(tFNo);
		
		lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblName);
		
		tFName = new JTextField();
		tFName.setColumns(10);
		pItem.add(tFName);	
		
		
		lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblDept);
		
		cmbDept = new JComboBox<>();
		cmbDept.addItemListener(this);
		pItem.add(cmbDept);
		
		lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblManager);
		
		cmbManager = new JComboBox<>();
		pItem.add(cmbManager);	 
	
	
		lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pItem.add(cmbTitle);		
		
		
		lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblSalary);
		
		spinSalary = new JSpinner();
		spinSalary.setModel(new SpinnerNumberModel(2000000, 1000000, 5000000, 100000));
		pItem.add(spinSalary);
	}
//	public Employee(int empNo, String empName, 
//	Title title, Employee manager, int salary, Department dept)
	public void setEmployee(Employee employee) {
		
		
	}
	

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			itemStateChangedCmbDept(e);
		}
	}
	protected void itemStateChangedCmbDept(ItemEvent e) {
	if(e.getStateChange() == ItemEvent.SELECTED) {
		Department dept = (Department) cmbDept.getSelectedItem();
		List<Employee> empList = service.showEmployeeGroupByDept(dept);
		if(empList == null) {
			empList = new ArrayList<>();
		}	
		
		DefaultComboBoxModel<Employee> model = new DefaultComboBoxModel<>(new Vector<>(empList));
		cmbManager.setModel(model);
		cmbManager.setSelectedIndex(-1);
	}
		
	}

	@Override
	public void setItem(Employee t) {
		tFNo.setText(t.getEmpNo()+ "");
		tFName.setText(t.getEmpName());
		cmbTitle.setSelectedItem(t.getTitle());
		cmbDept.setSelectedItem(t.getDept());
		cmbManager.setSelectedItem(t.getManager());
		spinSalary.setValue(t.getSalary());
		
	}

	@Override
	public Employee getItem() {
		int empNo = Integer.parseInt(tFNo.getText().trim());
		String empName = tFName.getText().trim();
		Title title  = (Title) cmbTitle.getSelectedItem();
		Employee manager = (Employee) cmbManager.getSelectedItem();
		int salary = (int) spinSalary.getValue();
		Department dept = (Department) cmbDept.getSelectedItem();
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public void validCheck() {
		if(tFNo.getText().contentEquals("") || tFName.getText().equals("")
				|| cmbTitle.getSelectedIndex() ==-1 ||cmbDept.getSelectedIndex() == -1
				|| cmbManager.getSelectedIndex()== -1) {
			throw new InvalidCheckException();
		}
		
	}

	@Override
	public void clearTf() {
		tFNo.setText( "");
		tFName.setText("");
		cmbTitle.setSelectedItem(-1);
		cmbDept.setSelectedItem(-1);
		cmbManager.setSelectedItem(-1);
		spinSalary.setValue("");
		
	}

	
}
