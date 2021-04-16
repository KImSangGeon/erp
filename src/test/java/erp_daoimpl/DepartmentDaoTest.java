package erp_daoimpl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_dao.DepartmentDao;
import erp_dao_impl.DepartmentImpl;
import erp_dto.Department;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DepartmentDaoTest {
	
	private static DepartmentDao dao = DepartmentImpl.getInstance();

	@After
	public void tearDown() throws Exception{
		System.out.println();
	}

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s()%n", "testSelectDepartmentByAll");
		List<Department> departmentList = dao.selectDepartmentByAll();
		Assert.assertNotNull(departmentList);
		for(Department d : departmentList) {
			System.out.println(d);
		}
	}

	@Test
	public void test05SelectDepartmentByNo() {
	System.out.printf("%s()%n", "testSelectDepartmentByNo");
	Department department = new Department(3);  //숫자 채우기
	Department searchDepartment = dao.selectDepartmentByNo(department);
	Assert.assertNotNull(searchDepartment);
	System.out.println(searchDepartment);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "testInsertDepartment");
		Department newDP = new Department(5, "연구", 6);
		int res = dao.insertDepartment(newDP);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDP));
		
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n ", "testUpdateDepartment");
		Department newDPT = new Department(5, "인사", 6);
		int res = dao.updateDepartment(newDPT);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(newDPT));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n ", "testDeleteDepartment");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1, res);
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
	}

}