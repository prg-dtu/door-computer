package Data;


public class AccessLog {
	private AccessMember member;
	private java.sql.Date loggedIn;

	public AccessLog(AccessMember member){
		this.member = member;
		java.util.Date date = new java.util.Date();
		this.loggedIn = new java.sql.Date(date.getTime());
	}

	public AccessLog(String membernumber, String name, String lastName, String studentnumber, java.sql.Date enrollmentdate){
		this.member = new AccessMember(membernumber, name, lastName, studentnumber, enrollmentdate);
		java.util.Date date = new java.util.Date();
		this.loggedIn = new java.sql.Date(date.getTime());
	}

	public AccessMember getMember() {
		return member;
	}

	public java.sql.Date getLoggedIn() {
		return loggedIn;
	}

	@Override
	public String toString() {
		return "AccessLog [" + member.toString() + ", loggedIn = " + loggedIn + "]";
	}
}
