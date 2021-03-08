package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface I_Database {

	public void connectDB() throws ClassNotFoundException, SQLException;

	public void disconnectDB() throws SQLException;

	public ResultSet read(String sql) throws SQLException, ClassNotFoundException;

	public void write(String sql) throws SQLException, ClassNotFoundException;
}