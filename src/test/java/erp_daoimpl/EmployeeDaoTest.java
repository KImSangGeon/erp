package erp_daoimpl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_dao.EmployeeDao;
import erp_dao_impl.EmployeeImpl;
import erp_dto.Department;
import erp_dto.Employee;
import erp_dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {

	private static EmployeeDao dao = EmployeeImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test01SelectEmployeeByAll() {
		System.out.printf("%s()%n", "testSelectEmployeeByAll");
		List<Employee> employeeList = dao.selectEmployeeByAll();
		Assert.assertNotNull(employeeList);
		for (Employee e : employeeList) {
			System.out.println(e);
		}
	}

	@Test
	public void test02SelectEmployeeByNo() {
		System.out.printf("%s()%n", "testSelectEmployeeByNo");
		Employee selEmp = new Employee(2106);
		Employee emp = dao.selectEmployeeByNo(selEmp);
		Assert.assertNotNull(emp);
		System.out.println(emp);

	}

	@Test
	public void test03InsertEmployee() {
		System.out.printf("%s()%n", "testInsertEmployee");
		Employee newEMP = new Employee(1004, "천사", new Title(5), new Employee(4377), 2000000, new Department(1));
		int res = dao.insertEmployee(newEMP);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(newEMP));
	}

	@Test
	public void test04UpdateEmployee() {
		System.out.printf("%s()%n ", "testUpdateEmployee");
		Employee newEMP = new Employee(1004, "천사2", new Title(4), new Employee(1003), 2000000, new Department(2));
		int res = dao.updateEmployee(newEMP);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectEmployeeByNo(newEMP));

	}

	@Test
	public void test05DeleteEmployee() {
		Employee newEMP = new Employee(1004);
		int res = dao.deleteEmployee(newEMP);
		Assert.assertEquals(1, res);
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
	}

}
