package Database;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class DatabaseGateway {

//	private Connection connection;
//
//	//connects to localhost
//	public void connectToDB() {
//		try {
//			//Peter
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_v4", "root", "");
//
//		    //Hanan
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/BAPERS", "root", "");
//
//			//Tulsi
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_database", "root", "");
//
//			//Abdullah
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers", "root", "");
//
//			//Farhan
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/teamproject", "root", "");
//
//			//Abdullah
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");
//
//			//Munish
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");
//
//			//Rashidul
//			//connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");
//
//			connection.setAutoCommit(false);
//			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//
//	//terminates connection to localhost
//	public void disconnectFromDB()  {
//		try {
//			connection.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void backupToDB() {
//		throw new UnsupportedOperationException();
//	}
//
//	public void restoreFromDB() {
//		throw new UnsupportedOperationException();
//	}
//
//	//handles reading from database. Call this function if reading is required.
//	public ResultSet read(PreparedStatement sql) {
//		try {
//			ResultSet rs = sql.executeQuery();
//			connection.commit();
//			return rs;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	//handles writing to database. Call this function if writing is required.
//	public void write(PreparedStatement sql) {
//		try {
//			sql.executeUpdate();
//			connection.commit();
//		} catch (SQLException e ) {
//			e.printStackTrace();
//		}
//}
//
//	public DatabaseGateway() {
//		try {
//			//initialise JDBC driver for class
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		connectToDB();
//	}
//
//	public Connection getConnection() {
//		return connection;
//	}
//=======

    private Connection connection;

    //connects to localhost
    public void connectToDB() {
        try {
            //Peter
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_v4", "root", "");

            //Hanan
            connection = DriverManager.getConnection("jdbc:mysql://localhost/BAPER_v4", "root", "");

            //Tulsi
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_database", "root", "");

            //Abdullah
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers", "root", "");

            //Farhan

            //connection = DriverManager.getConnection("jdbc:mysql://localhost/tp4", "root", "");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost/tp5", "root", "");


            //Abdullah
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");

            //Munish
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");

            //Rashidul
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/<replace this with name of your database>", "root", "");

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //terminates connection to localhost
    public void disconnectFromDB() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbBackup(String dbUser, String dbPass, String dbName) {
        String savePath = "dbBackup.sql";
        String executeCmd = ("C:\\xampp\\mysql\\bin\\mysqldump -u " + dbUser + " -p" + dbPass + "  --databases " + dbName + " -r " + savePath);
        try {
            Process p = Runtime.getRuntime().exec(executeCmd);
            int processComplete = p.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup Created Success");
            } else {
                System.out.println("Backup Unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBackup() {
        String backupName = null;
        // change to directory of where backup should be
        String directory = ("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin");
        File f = new File(directory);
        for (File x : Objects.requireNonNull(f.listFiles())) {
            if (x.getName().contains(".sql")) {
                backupName = x.getName();
                break;
            } else {
            }
        }
        return true;
    }

    public void restoreDB(String dbName, String dbUserName, String dbPassword, String source) {
        String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql", "--user=" + dbUserName, "--password=" + dbPassword, dbName, "-e", " source " + source};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup restored Successfully");
            } else {
                System.out.println("Failed Backup");
            }
        } catch (IOException | InterruptedException e) {
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
    public ResultSet read(PreparedStatement sql) {
        try {
            ResultSet rs = sql.executeQuery();
            connection.commit();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //handles writing to database. Call this function if writing is required.
    public void write(PreparedStatement sql) {
        try {
            sql.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            //e.printStackTrace();

            System.err.println("Message: " + e.getMessage());

            Throwable t = e.getCause();
            while (t != null) {
                System.out.println("Cause: " + t);
                t = t.getCause();
            }
        }
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