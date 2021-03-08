package Database;

import java.sql.*;

public class DatabaseGateway {
	public DBControl DBControl;
	private Connection connection;

	//connects to localhost
	public void connectToDB() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		//initialise JDBC driver for class
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection("dbc:mysql://localhost/bapers/bapers_v4", "root", "");
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
		connectToDB();
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		disconnectFromDB();
		return result;
	}

	//handles writing to database. Call this function if writing is required.
	public void write(String sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		connectToDB();
		Statement stmt = connection.createStatement();
		stmt.executeQuery(sql);
		disconnectFromDB();
	}

	public DatabaseGateway() throws ClassNotFoundException {
	}
}