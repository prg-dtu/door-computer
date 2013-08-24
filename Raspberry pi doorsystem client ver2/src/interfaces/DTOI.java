package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import Data.AccessLog;
import Data.AccessMember;

public interface DTOI extends Remote{
	
	//methods for DB
	public boolean addMember(String membernumber, String name, String lastName, String studentnumber, Date enrollmentDate, String hashedcard) throws RemoteException;
	public AccessMember getMember(String hashedcard) throws RemoteException; //no
	public boolean deleteMember(String hashedcard) throws RemoteException; //yes -> tjek sql
	public boolean clearMembers() throws RemoteException; // Yes
	public ArrayList<AccessMember> getMembers() throws RemoteException; //no
	public ArrayList<AccessLog> getLogs() throws RemoteException;
	
	//Methods for communicating with GPIO
	public boolean isDoorOpen() throws RemoteException; //Yes
	public void openDoor() throws RemoteException; //yes

}
