package Data;

import java.sql.Date;

public class AccessMember{
	String membernumber, name, lastName, studentnumber;
	Date enrollmentdate;
	
	public AccessMember(String membernumber, String name, String lastName, String studentnumber, Date enrollmentdate) {
		this.membernumber = membernumber;
		this.name = name;
		this.lastName = lastName;
		this.studentnumber = studentnumber;
		this.enrollmentdate = enrollmentdate;
	}

	public String getName() {return name;}
	public String getLastName() {return lastName;}
	public String getMemberNumber() {return membernumber;}
	public String getStudentnumber() {return studentnumber;}
	public Date getEnrollmentdate() {return enrollmentdate;}

	@Override
	public String toString() {
		return "name = " + name + ", lastName = " + lastName + ", studentnumber = " + studentnumber;
	}
}