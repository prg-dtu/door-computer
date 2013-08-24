package Data;

import interfaces.DTOI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.sql.Date;

public class RMIDoorDAO implements DTOI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RMIDoorDAO() throws RemoteException {
		connect();
	}

	private DTOI door = null;

	public boolean connect(){ 
		try{
			door = (DTOI) Naming.lookup("rmi://localhost/PRG1");
			System.out.println("Connected");
			return true;
		}catch(Exception e){
			return false;
		}
	}

	//Methods for communicating with GPIO
	@Override
	public boolean isDoorOpen() {try {
		return door.isDoorOpen();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	}

	@Override
	public void openDoor() {try {
		door.openDoor();
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}

	@Override
	public boolean addMember(String membernumber, String name, String lastName, String studentnumber, Date enrollmentDate, String hashedcard) throws RemoteException {
		try{
			door.addMember(membernumber, name, lastName, studentnumber, enrollmentDate, hashedcard);
			return true;
		} catch (RemoteException e) {
			return false;
		}	
	}

	@Override
	public AccessMember getMember(String hashedcard) {
		try {
			return door.getMember(hashedcard);
		} catch (RemoteException e) {
			return null;
		}
	}

	@Override
	public boolean deleteMember(String hashedcard){
		try {
			door.deleteMember(hashedcard);
			return true;
		} catch (RemoteException e) {
			return false;
		}	
	}

	@Override
	public boolean clearMembers(){
		try {
			door.clearMembers();
			return true;
		} catch (RemoteException e) {
			return false;
		}	
	}

	@Override
	public ArrayList<AccessLog> getLogs(){
		//TODO her skal vi hente alle logs fra DB -> return this.accessList.getLogs();
		return null;
	}

	@Override
	public ArrayList<AccessMember> getMembers(){
		try {
			ArrayList<AccessMember> members = door.getMembers();
			return members;
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
