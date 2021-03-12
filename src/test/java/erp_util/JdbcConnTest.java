package erp_util;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class JdbcConnTest {
	
	@Test
	public void testGetConnection() {
		System.out.printf("%s()%n ","testGetConnection" );//3
		Connection con = JdbcConn.getConnection();
		System.out.println("con > " + con);
		Assert.assertNotNull(con);
	}

}
