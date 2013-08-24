package Functionality;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import Boundary.*;
import Controllers.DoorController;
import Controllers.RmiController;
import Data.AccessMember;
import SQLiteDAO.SQLiteJDBC;

public class Main {

	public static void main(String[] args) {
		
		try{
			String pass = Sha1Encryption.encryptPassword("<CARD DATA>");
			java.util.Date date = new java.util.Date();
			SQLiteJDBC.setMember("<MEMBERNUMBER>", "<MEMBERNAME>", "<SURNAME>", "<STUDENTNUMBER>", new Date(date.getTime()), pass);
						
			for( AccessMember a : SQLiteJDBC.getMembers()){
				System.out.println(a.toString());
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		try {
			//to door program
//			CardReaderI cardReader = new CardReader("/dev/ttyUSB0");
//			CardReaderI cardReader = new CardReader("COM3");
			CardReaderI cardReader = new CliReader();
			
			GPIODoorI gpio = new GPIODoorSimulator();			//to rmi interface			String unitName = "PRG1";			String ipAddress ="localhost";			DoorController door = new DoorController(cardReader, gpio );			RmiController rmi = new RmiController(unitName, ipAddress, gpio);
			door.start();
			rmi.start();
			
//			int last=0;
//			while(true){
//				if(AccessLogs.getLogs().size() != last){
//					for(AccessLog l : AccessLogs.getLogs()){
//						System.out.println( l );
//					}
//				last = AccessLogs.getLogs().size();
//				}
//				try{ Thread.sleep(10); } catch(InterruptedException e){}
//			}
			
//		}catch(IOException IOE){
//			System.out.println("system down");
//			IOE.printStackTrace();
//		}catch(PortInUseException PortInUseE){
//			System.out.println("ComPort Is In Use");
//		}catch(UnsupportedCommOperationException UnSupportedOperationE){
//			System.out.println("UnsupportedOperation on ComPort");
//		}catch(NoSuchPortException NoPortE){
//			System.out.println("ComPort doesn't exsists");
		}catch(Exception E){
			System.out.println("system down");
			E.printStackTrace();
		}
	}
}
