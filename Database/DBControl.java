package Database;

import java.sql.*;

public class DBControl implements I_Database {
	public DatabaseGateway DBGateway;

	public void connectDB() throws SQLException, ClassNotFoundException {
		DBGateway.connectToDB();
	}

	public void disconnectDB() throws SQLException {
		DBGateway.disconnectFromDB();
	}

	public ResultSet read(String sql) throws SQLException, ClassNotFoundException {
		return DBGateway.read(sql);
	}
	public void write(String sql) throws SQLException, ClassNotFoundException {
		DBGateway.write(sql);
	}

	public DBControl() throws ClassNotFoundException {
		DBGateway = new DatabaseGateway();
	}
}