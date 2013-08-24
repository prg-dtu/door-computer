package Boundary;

import interfaces.DTOI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

public class RmiServer{
	
	private DTOI door;
	
	public RmiServer(String unitName, HashMap<String, RmiServer> doors) {
		try {
			System.out.println(unitName);
			this.door = (DTOI) Naming.lookup("rmi://localhost/" + unitName);
		} catch (MalformedURLException | RemoteException e){
			
		}catch(NotBoundException e) {
			doors.remove(this);
		}
	}

	public DTOI getDoor() { return door; }
	public void setDoor(DTOI door) { this.door = door; }
	
	
}