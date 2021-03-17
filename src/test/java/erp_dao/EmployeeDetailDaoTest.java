package erp_dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_dao_impl.EmployeeDetailDaoImpl;
import erp_dto.Employee;
import erp_dto.EmployeeDetail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDetailDaoTest {
	private EmployeeDetailDao Dao = EmployeeDetailDaoImpl.getInstance();


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test02SelectEmployeeDetailByNo() {
		System.out.printf("%s()%n", "test02SelectEmployeeDetailByNo");
		
		EmployeeDetail employeeDetail = Dao.selectEmployeeDetailByNo(new Employee(1003));
		System.out.println(employeeDetail);
		Assert.assertNotNull(employeeDetail);
	}

	@Test
	public void test01InsertEmployeeDetail() {
		System.out.printf("%s()%n", "test01InsertEmployeeDetail");
		
		EmployeeDetail detail = new EmployeeDetail(1003, true, new Date(), "1234", getImage("NoImage.jpg"));
		int res = Dao.insertEmployeeDetail(detail);
		Assert.assertEquals(1, res);
				
	}
	private byte[] getImage(String imgName) {
		byte[] pic = null;
		// images/imgName
		File file = new File(System.getProperty("user.dir") + File.separator + "images", imgName);
		try(InputStream is = new FileInputStream(file)){
			pic = new byte[is.available()];   //file로 부터 읽은 이미지의 바이트길이로 배열 생성
			is.read(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}
	@Test
	public void test03UpdateEmployeeDetail() {
		System.out.printf("%s()%n", "test03UpdateEmployeeDetail");
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.getTime();
		
		EmployeeDetail empDetail = new EmployeeDetail(1003, false, cal.getTime(), "1234", getImage("Shin.jpg"));
		int res = Dao.updateEmployeeDetail(empDetail);
		Assert.assertEquals(1, res);
		System.out.println(Dao.selectEmployeeDetailByNo(new Employee(1003)));
	}

	@Test
	public void test04DeleteEmployeeDetail() {
		System.out.printf("%s()%n", "test04DeleteEmployeeDetail");
		Employee newEMP = new Employee(1003);
		int res = Dao.deleteEmployeeDetail(newEMP);
		Assert.assertEquals(1, res);
	}

}
