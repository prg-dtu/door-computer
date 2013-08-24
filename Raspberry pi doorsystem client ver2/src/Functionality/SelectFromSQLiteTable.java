package Functionality;
import java.sql.*;

public class SelectFromSQLiteTable
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM accessmembers;" );
      while ( rs.next() ) {
         
         System.out.println( rs.getString("membernumber"));
         System.out.println( rs.getString("name") );
         System.out.println( rs.getString("lastname") );
         System.out.println( rs.getString("Enrollmentdate") );
         System.out.println( rs.getString("hashedcard") );
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}