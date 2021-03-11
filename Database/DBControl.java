package Database;

import java.sql.*;

public class DBControl implements I_Database {
	private DatabaseGateway DBGateway;

	public void connectDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		DBGateway.connectToDB();
	}

	public void disconnectDB() throws SQLException {
		DBGateway.disconnectFromDB();
	}

	public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		return DBGateway.read(sql);
	}

	public void write(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		DBGateway.write(sql);

	}

	public void PreparedStatement(String sql) throws SQLException {
		DBGateway.PreparedStatement(sql);
	}

	public DBControl() throws ClassNotFoundException, SQLException {
		DBGateway = new DatabaseGateway();
	}

	public DatabaseGateway getDBGateway() {
		return DBGateway;
	}


}