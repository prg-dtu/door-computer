package Functionality;

import Boundary.RegistryServer;

public class RegistryserverMain {

	private static int lastDoors;

	public static void main(String[] args) {
		
		RegistryServer regServer = new RegistryServer();
		
		while(true){
			int doors = regServer.getDoors().length;
			if( doors != lastDoors){
				lastDoors = doors;
				
				for(String door : regServer.getDoors()){
					System.out.println(door);
				}
				
			}
		}

	}

}
