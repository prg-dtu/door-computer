package Controllers;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.HashMap;

import Boundary.CardReaderI;
import Boundary.GPIODoorI;
import Data.AccessMember;
import SQLiteDAO.SQLiteJDBC;

public class DoorController extends Thread{
	CardReaderI card; 
	GPIODoorI door;

	public DoorController(CardReaderI card, GPIODoorI door) {
		this.card = card;
		this.door = door;
	}

	@Override
	public void run() {
		String password;
		while(true){
			password = this.card.getPasword();
			if(password != null){
				try{
					AccessMember user = SQLiteJDBC.getUserByHashedCard(password);
					//TODO test udskrift af bruger og tid for indlogningen
					System.out.println(user.getName() + " has logged in: " + DateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.SHORT).format( new java.util.Date() ) );
					door.unlockDoor5sec();
					//TODO her skal bruger logges til DB -> this.accessList.logMember( this.accessList.get(password) );
				}catch(SQLException e){
					//TODO test udskrift af ukendt kort 
					System.out.println("Unknown card exception");
				}
			}
			try{ Thread.sleep(10); } catch(InterruptedException e){}
		}
	}


}
