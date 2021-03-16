package erp_dao;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import erp_dao_impl.EmployeeDetailDaoImpl;
import erp_dto.EmployeeDetail;


public class EmployeeDetailDaoTest {
	private EmployeeDetailDao Dao = EmployeeDetailDaoImpl.getInstance();


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectEmployeeDetailByNo() {
		fail("Not yet implemented");
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
	public void testUpdateEmployeeDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeliteEmployeeDetail() {
		fail("Not yet implemented");
	}

}
