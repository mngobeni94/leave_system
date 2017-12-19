package leave.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static Connection conn = null;
	static Statement stmt = null;
	static final String URL = "jdbc:postgresql://localhost:5432/db_leave_system";
	static final String PASS = "54321";
	static final String USER = "postgres";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setAutoCommit(false);
			return conn;
	}

	public void closeConnect() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
