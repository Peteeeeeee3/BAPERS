package Database;

import java.sql.*;

public class DatabaseGateway {
	public DBControl DBControl;
	private Connection connection;

	//connects to localhost
	public void connectToDB() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_v4", "root", "");
	}

	//terminates connection to localhost
	public void disconnectFromDB() throws SQLException {
		connection.close();
	}

	public void backupToDB() {
		throw new UnsupportedOperationException();
	}

	public void restoreFromDB() {
		throw new UnsupportedOperationException();
	}

	//handles reading from database. Call this function if reading is required.
	public ResultSet read(String sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Statement stmt = connection.createStatement();
		return stmt.executeQuery(sql);
	}

	//handles writing to database. Call this function if writing is required.
	public void write(String sql) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeQuery(sql);
	}

	public DatabaseGateway() throws ClassNotFoundException, SQLException {
		//initialise JDBC driver for class
		Class.forName("com.mysql.cj.jdbc.Driver");
		connectToDB();
	}
}