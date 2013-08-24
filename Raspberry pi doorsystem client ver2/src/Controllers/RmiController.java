package Controllers;

import interfaces.DTOI;

import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Boundary.*;
import Data.DTO;

public class RmiController extends Thread{
	private String ipAddress;
	private String unitName;
	private DTOI rmiDto;
	
	
	public RmiController( String unitName, String ipAddress, GPIODoorI door) throws RemoteException
	  {
		this.ipAddress = ipAddress;
		this.unitName = unitName;
		this.rmiDto = new DTO(door);
		  
		/* Kan bruges istedet for at have separat rmi registryServer kørende (I særskilt process)
	     * 
		// Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
	    java.rmi.registry.LocateRegistry.createRegistry(1099); // start i server-JVM
	    
		*/
	  }
	
	
	
	public String getIpAddress() {return ipAddress;}
	public void setIpAddress(String ipAddress) {this.ipAddress = ipAddress;}
	
	public String getUnitName() {return unitName;}
	public void setUnitName(String unitName) {this.unitName = unitName;}



	@Override
	public void run() {
	
		try {
			
			Naming.rebind("rmi://" + this.ipAddress + "/"+this.unitName, this.rmiDto);
			System.out.println("Kontotjeneste registreret.");
			while(true){
				Naming.lookup("rmi://" + this.ipAddress + "/"+this.unitName);
				try{ Thread.sleep(1000); }catch(InterruptedException Ie){}
			}
		} catch (RemoteException | NotBoundException e) {
			System.out.println("No connection to server");
			try{ Thread.sleep(1000*30); }catch(InterruptedException Ie){}
			this.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
