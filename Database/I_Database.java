package Database;

public interface I_Database {

	public void connectDB();

	public void disconnectDB();

	public ResultSet read(String sql);

	public void write(String sql);
}