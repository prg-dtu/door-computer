package Boundary;

import java.rmi.RemoteException;
import java.rmi.ConnectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryServer {

	public RegistryServer() {
		createRegistryServer();
	}

	private void createRegistryServer() {
		try {
			// Enten: Kør programmet 'rmiregistry' fra mappen med
			// .class-filerne, eller:
			java.rmi.registry.LocateRegistry.createRegistry(1099); // start i
			// server-JVM
			System.out.println("Rmi Registry Server is running");
		} catch (RemoteException e) {
		}
	}

	public String[] getDoors() {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			String[] boundNames = registry.list();
			return boundNames;
		} catch (ConnectException e) {
			return null;
		} catch (RemoteException remoteE) { System.err.println("RemoteException: "	+ remoteE.toString());
		}
		return null;
	}
}