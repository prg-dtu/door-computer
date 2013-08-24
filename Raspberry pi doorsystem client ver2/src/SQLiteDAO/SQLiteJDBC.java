package SQLiteDAO;
import java.sql.*;
import java.util.ArrayList;

import Data.AccessMember;

public class SQLiteJDBC
{
	private static Connection c = null;
	private static Statement stmt = null;
	
	public SQLiteJDBC() {
		
	}

	private static synchronized Connection connectToSQL() throws SQLException{
		if(c == null || c.isClosed()){
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");
		}
		return c;
	}
	
	public void createTable(){
		
	}
	
	public static synchronized AccessMember getUserByHashedCard(String HashedCard) throws SQLException{
		
		AccessMember user;
		c = connectToSQL();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM accessmembers WHERE hashedcard = '" + HashedCard + "';" );
		rs.next();
		user = new AccessMember(rs.getString("membernumber"), rs.getString("name"), rs.getString("lastname"), rs.getString("membernumber"), rs.getDate("enrollmentdate"));
		rs.close();
		stmt.close();
		c.close();
		return user;
	}
	
	public static synchronized AccessMember getMemberByMemberNumber(String membernumber) throws SQLException{
		AccessMember user;
		c = connectToSQL();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM accessmembers WHERE membernumber = '"+membernumber+"';" );
		rs.next();
		user = new AccessMember(rs.getString("membernumber"), rs.getString("name"), rs.getString("lastname"), rs.getString("membernumber"), rs.getDate("enrollmentdate"));
		rs.close();
		stmt.close();
		c.close();
		return user;
	}

	public static synchronized ArrayList<AccessMember> getMembers() throws SQLException{
		ArrayList<AccessMember> users = new ArrayList<AccessMember>();
		c = connectToSQL();
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery( "SELECT * FROM accessmembers;" );
		while ( rs.next() ) {
		 users.add( new AccessMember(rs.getString("membernumber"), rs.getString("name"), rs.getString("lastname"), rs.getString("membernumber"), rs.getDate("enrollmentdate")) );
		}
		rs.close();
		stmt.close();
		c.close();
		return users;
	}
	
	public static synchronized void setMember(String membernumber, String name, String lastName,	String studentnumber, Date enrollmentDate, String hashedcard) throws SQLException{
		c = connectToSQL();
		stmt = c.createStatement();
		String sql = "INSERT OR REPLACE INTO accessmembers (membernumber, name, lastname, studentnumber, enrollmentdate, hashedcard) " +
				"VALUES ('"+membernumber+"', '"+name+"', '"+lastName+"', '"+studentnumber+"', '"+enrollmentDate+"', '"+hashedcard+"' );"; 
		stmt.executeUpdate(sql);

		stmt.close();
		c.commit();
		c.close();
	}

	
	public static synchronized void removeAllMembers() throws SQLException{
		c = connectToSQL();
		stmt = c.createStatement();
		String sql = "DELETE from accessmembers;";
		stmt.executeUpdate(sql);
		c.commit();
		stmt.close();
		c.close();
	}
	
	public static synchronized void removeAllMembersOlderThan(int days) throws SQLException{
		c = connectToSQL();
		stmt = c.createStatement();
		String sql = "DELETE FROM accessmembers WHERE enrollmentdate <= date('now','-"+days+" day')";
		stmt.executeUpdate(sql);
		c.commit();
		stmt.close();
		c.close();
	}
	
	public static synchronized void removeMemberByHashedcard(String hashedcard) throws SQLException{
		c = connectToSQL();
		stmt = c.createStatement();
		String sql = "DELETE from COMPANY where hashedcard = '"+hashedcard+"';";
		stmt.executeUpdate(sql);
		c.commit();
		stmt.close();
		c.close();
	}
	
	public static synchronized void removeMemberByMemberNumber(String membernumber) throws SQLException{
		c = connectToSQL();
		stmt = c.createStatement();
		String sql = "DELETE from COMPANY where membernumber = '"+membernumber+"';";
		stmt.executeUpdate(sql);
		c.commit();
		stmt.close();
		c.close();
	}
}