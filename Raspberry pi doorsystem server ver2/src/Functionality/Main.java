package Functionality;

import interfaces.DTOI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import Boundary.Sha1Encryption;
import Data.AccessMember;


public class Main {

	public static void main(String[] args) throws RemoteException, InterruptedException, SQLException {
		
		DTOI prg = null;
		boolean connected = false;
		while(!connected){
			try{
				prg = (DTOI) Naming.lookup("rmi://localhost/PRG1");
				connected = true;
			}catch(Exception e){
			}
			try{ Thread.sleep(100); }catch(InterruptedException e){}
		}
		System.out.println("Connected");

		//		try{ Thread.sleep(15000); }catch(InterruptedException e){}
		String pass = Sha1Encryption.encryptPassword("<CARD DATA>");
		java.util.Date date = new java.util.Date();
		if(prg.addMember("<MEMBERNUMBER>", "<MEMBERNAME>", "<SURNAME>", "<STUDENTNUMBER>", new Date(date.getTime()), pass ) ){
			System.out.println("card Registret");
		}
		else{
			System.out.println("card Not Registret");
		}
		
		for(AccessMember m : prg.getMembers()){
			System.out.println(m);
		}

	}
}