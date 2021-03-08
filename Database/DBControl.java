package Database;

import java.sql.*;

public class DBControl implements I_Database {
	public DatabaseGateway DBGateway;

	public void connectDB() throws SQLException {
		DBGateway.connectToDB();
	}

	public void disconnectDB() throws SQLException {
		DBGateway.disconnectFromDB();
	}

	public ResultSet read(String sql) throws SQLException {
		return DBGateway.read(sql);
	}
	public void write(String sql) throws SQLException {
		DBGateway.write(sql);
	}

	public DBControl() throws ClassNotFoundException {
		DBGateway = new DatabaseGateway();
	}
}