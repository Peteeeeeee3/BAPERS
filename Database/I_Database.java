package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface I_Database {

	public void connectDB() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

	public void disconnectDB() throws SQLException;

	public ResultSet read(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

	public void write(PreparedStatement sql) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;
}