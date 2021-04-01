package Database;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;

public class DatabaseGateway {

    private Connection connection;

    //connects to localhost
    public void connectToDB() {
        try {
            //Peter
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_v6", "root", "");

            //Hanan
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/Bapers_data", "root", "");

            //Tulsi
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers_database", "root", "");

            //Abdullah
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/bapers", "root", "");

            //Farhan
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

    public String checkOS() {
        return System.getProperty("os.name");
    }

    public void backupToDB() {
        try {

            String os = System.getProperty("os.name");
            System.out.println(os);


            if (os.contains("Mac OS X")) {

                File backupFile = new File("databaseBackup.sh");
                FileWriter myWriter = new FileWriter(backupFile);

                //pathForBackup = "/Applications/XAMPP/xamppfiles/bin/mysql";

                myWriter.write("#!/bin/sh\n" +
                        "/Applications/XAMPP/xamppfiles/bin/mysqldump -uroot" + " " + "bapers_v6>latest_backup.sql\n");

                myWriter.close();
                Path path = Paths.get("databaseBackup.sh");
                String s = path.toAbsolutePath().toString();


                Process process = Runtime.getRuntime().exec("chmod a+x " + path.toAbsolutePath());

                StringBuilder output = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                int exitVal = process.waitFor();

                if (exitVal == 0) {
                    System.out.println("success");
                    System.out.println(output);
                    Process process2 = Runtime.getRuntime().exec(s);
                    int exitVal2 = process2.waitFor();


                    if (exitVal2 == 0) {
                        System.out.println("saved");
                    } else {
                        System.out.println("not saved");
                    }
                    System.exit(0);

                } else {
                    System.out.println("not working");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void restoreToDB() {

        String os = System.getProperty("os.name");
        System.out.println(os);

        try {
            String dropStmt = "DROP DATABASE bapers_v6";         //drops the database
            PreparedStatement stmt = connection.prepareStatement(dropStmt);   //

            String createStmt = "CREATE DATABASE bapers_v6";      //creates new one
            PreparedStatement stmt2 = connection.prepareStatement(createStmt);

            stmt.executeUpdate();      //do not remove
            stmt2.executeUpdate();     //do not remove


            if (os.contains("Mac OS X")) {

                File databaseRestore = new File("databaseRestore.sh");     //creates shell file
                FileWriter myWriter = new FileWriter(databaseRestore);

                if (databaseRestore.createNewFile()) {
                    System.out.println("File created: " + databaseRestore.getName()); //if it exists then print it exists, if not then generate
                } else {
                    System.out.println("file exists");
                }

                //pathForBackup = "/Applications/XAMPP/xamppfiles/bin/mysqldump";

                myWriter.write("#!/bin/sh\n" +
                        "/Applications/XAMPP/xamppfiles/bin/mysql -uroot bapers_v6<latest_backup.sql\n");     //writes to shell file
                myWriter.close();

                Path path = Paths.get("databaseRestore.sh");     //gets path of shell file

                Process process = Runtime.getRuntime().exec("chmod a+x " + path.toAbsolutePath());   //changes permission of shell file

                StringBuilder output = new StringBuilder();  //string reader
                //BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                int exitVal = process.waitFor();    //waits till its finished

                if (exitVal == 0) {    //if finished do this
                    System.out.println("success");
                    System.out.println(output);

                    Process process2 = Runtime.getRuntime().exec(path.toAbsolutePath() + "");     //runs shell file

                    int exitVal2 = process2.waitFor();   //waits for this finish
                    if (exitVal2 == 0) {        //if finished do this
                        System.out.println("saved");
                    } else {
                        System.out.println("not saved");  //this means that the shell file did not run
                    }
                    System.exit(0);

                } else {
                    System.out.println("not working");   //this means that there's a problem in the write statement (most likely)
                }
            } else if (os.contains("Windows")) {

                File restoreFile = new File("databaseRestoreWIN.bat");        //creates batch file for windows
                FileWriter myWriter = new FileWriter(restoreFile);

                Path path = Paths.get("databaseRestoreWin.bat");   //gets path for batch file

                if (restoreFile.createNewFile()) {
                    System.out.println("File created: " + restoreFile.getName());    //same as before applies
                } else {
                    System.out.println("file exists");
                }

                myWriter.write("@echo off \n" +
                        "c: /" + "\nC:\\xampp\\mysql\\bin\\mysql -uroot bapers_v6<latest_backup.sql\n");    //writes to batch file

                myWriter.close();
                Process process = Runtime.getRuntime().exec(("" + path.toAbsolutePath()));    //runs batch file (quicker than mac as permissions do not need to be changed

                StringBuilder output = new StringBuilder();
                //BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                int exitVal = process.waitFor();

                if (exitVal == 0) {
                    System.out.println("success");     //ran successfully
                    System.out.println(output);
                    System.exit(0);

                } else {
                    System.out.println("not working");    //didn't run
                }
            } else {
                System.out.println("OPERATING SYSTEM NOT SUPPORTED");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//handles reading from database. Call this function if reading is required.
public ResultSet read(PreparedStatement sql){
        try{
        ResultSet rs=sql.executeQuery();
        connection.commit();
        return rs;
        }catch(Exception e){
        e.printStackTrace();
        }
        return null;
        }

//handles writing to database. Call this function if writing is required.
public void write(PreparedStatement sql){
        try{
        sql.executeUpdate();
        connection.commit();
        }catch(SQLException e){
        e.printStackTrace();

//            System.err.println("Message: " + e.getMessage());
//
//            Throwable t = e.getCause();
//            while (t != null) {
//                System.out.println("Cause: " + t);
//                t = t.getCause();
//            }
        }
        }

public DatabaseGateway(){
        try{
        //initialise JDBC driver for class
        Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
        e.printStackTrace();
        }
        connectToDB();
        }

public Connection getConnection(){
        return connection;
        }


        }