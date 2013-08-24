package Data;

import interfaces.DTOI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;

import SQLiteDAO.SQLiteJDBC;

import Boundary.GPIODoorI;

public class DTO extends UnicastRemoteObject implements DTOI{

	private static final long serialVersionUID = 1L;
	private GPIODoorI door;

	public DTO(GPIODoorI door) throws RemoteException {
		this.door = door;
	}
		
	//Methods for communicating with GPIO
	@Override
	public boolean isDoorOpen() throws RemoteException {return door.isDoorOpen();}
	
	@Override
	public void openDoor() throws RemoteException {door.unlockDoor5sec();}

	@Override
	public boolean addMember(String membernumber, String name, String lastName, String studentnumber, Date enrollmentDate, String hashedcard) throws RemoteException {
		try {
			SQLiteJDBC.setMember(membernumber, name, lastName, studentnumber, enrollmentDate, hashedcard);
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}
	
	@Override
	public AccessMember getMember(String hashedcard) throws RemoteException {
		try {
			return SQLiteJDBC.getUserByHashedCard(hashedcard);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean deleteMember(String hashedcard) throws RemoteException {
		try {
			SQLiteJDBC.removeMemberByHashedcard(hashedcard);
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}

	@Override
	public boolean clearMembers() throws RemoteException{
		try {
			SQLiteJDBC.removeAllMembers();
			return true;
		} catch (SQLException e) {
			return false;
		}	
	}
	
	@Override
	public ArrayList<AccessLog> getLogs() throws RemoteException {
		//TODO her skal vi hente alle logs fra DB -> return this.accessList.getLogs();
		return null;
	}

	@Override
	public ArrayList<AccessMember> getMembers() throws RemoteException {
		try {
			ArrayList<AccessMember> members = SQLiteJDBC.getMembers();
			return members;
		} catch (SQLException e) {
			return null;
		}
	}
}
