package Functionality;
import java.sql.*;

public class CreateSQLiteTable
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      // (name, lastname, studentnumber, enrollmentdate, hashedcard)
      String sql = "CREATE TABLE accessmembers " +
                   " (membernumber	varchar(60)	NOT NULL," +
                   " name           TEXT    	NOT NULL, " + 
                   " lastname       TEXT    	NOT NULL, " + 
                   " studentnumber	varchar(7)," + 
                   " enrollmentdate	date, " + 
                   " hashedcard		varchar(200), " +
                   " PRIMARY KEY (membernumber) )"; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}