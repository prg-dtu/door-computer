package interfaces;

import java.rmi.RemoteException;

public interface AccessMemberI {

	//AccessMember
	public String getName(String password) throws RemoteException;
	public String getLastName(String password) throws RemoteException;
	public String getMemberNumber(String password) throws RemoteException;
}
