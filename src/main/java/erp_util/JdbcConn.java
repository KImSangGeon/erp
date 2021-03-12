package erp_util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConn {
	

	public static Connection getConnection() {
		
		String propertiesPath = "db.properties"; // 리소스 밑에 파일명과 일치해야됨
		Connection con = null;

		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)) {
			Properties prop = new Properties(); // key = value
			prop.load(in);

			try {
				con = DriverManager.getConnection(prop.getProperty("url"), prop);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return con;
	}

}
