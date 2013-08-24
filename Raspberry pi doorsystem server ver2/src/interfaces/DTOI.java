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
	public AccessMember getMember(String hashedcard) throws RemoteException;
	public boolean deleteMember(String hashedcard) throws RemoteException;
	public boolean clearMembers() throws RemoteException;
	public ArrayList<AccessMember> getMembers() throws RemoteException;
	public ArrayList<AccessLog> getLogs() throws RemoteException;
	
	//Methods for communicating with GPIO
	public boolean isDoorOpen() throws RemoteException;
	public void openDoor() throws RemoteException;

}
