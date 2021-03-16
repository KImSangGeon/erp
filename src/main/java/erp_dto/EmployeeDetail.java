package erp_dto;

import java.util.Arrays;
import java.util.Date;

public class EmployeeDetail {
	
	private int empNo;
	private boolean gender;
	private Date hiredate;
	private byte[] pic;
	private String Pass;
	public EmployeeDetail() {
		super();
	}
	public EmployeeDetail(int empNo) {
		this.empNo = empNo;
	}
	public EmployeeDetail(int empNo, boolean gender, Date hiredate, String pass, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hiredate = hiredate;
		this.Pass =pass;
		this.pic = pic;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	@Override
	public String toString() {
		return String.format("EmpDetail [empNo=%s, gender=%s, hiredate=%s, pic=%s]",
				empNo, gender, hiredate, pic.length);
				
	}

	
}
