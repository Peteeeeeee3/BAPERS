package Database;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;

public class DatabaseGateway {
	private Connection connection;

	//connects to localhost
	public void connectToDB() {
		try {
			//Peter
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_v4", "root", "");


		   //Hanan
			connection = DriverManager.getConnection("jdbc:mysql://localhost/Bapers_data", "root", "");

			//Tulsi
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_database", "root", "");

			//Abdullah
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers", "root", "");


			//Farhan
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/teamproject", "root", "");

			//Abdullah
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");

			//Munish
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");

			//Rashidul
			//connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	//terminates connection to localhost
	public void disconnectFromDB()  {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void backupToDB() {
		throw new UnsupportedOperationException();
	}

	public void restoreFromDB() {
		throw new UnsupportedOperationException();
	}

	//handles reading from database. Call this function if reading is required.
	public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		return sql.executeQuery();
	}

	//handles writing to database. Call this function if writing is required.
	public void write(PreparedStatement sql) {
		try {
			sql.executeUpdate();
		} catch (SQLException e ) {
			e.printStackTrace();
		}
}
	public void PreparedStatement(String sql) {
	}

	public DatabaseGateway() {
		try {
			//initialise JDBC driver for class
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connectToDB();
	}

	public Connection getConnection() {
		return connection;
	}


}