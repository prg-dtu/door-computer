package Boundary;

import java.util.Scanner;

public class GPIODoorSimulator implements GPIODoorI {
	
	Scanner scan = new Scanner(System.in);
	
	private class dooropener extends Thread{
		@Override
		public void run() {	
			System.out.println();
			unlockDoor();
			doorBuzzerOn();
			redLedOff();
			greenLedOn();
			System.out.println();
			try {Thread.sleep(3000);} catch (InterruptedException e1) {}
			lockDoor();
			doorBuzzerOff();
			redLedOn();
			greenLedOff();
			try{ Thread.sleep(5000); }catch(InterruptedException e){}
		}
		
	}
	dooropener door = new dooropener();
	
	public GPIODoorSimulator(){
		// create gpio controller
	}
	
	@Override
	public boolean isDoorOpen() {
		return true;
	}

	@Override
	public void unlockDoor5sec() {
		if(!door.isAlive()){
			door = new dooropener();
			door.start();
		}
	}
	
	@Override
	public void unlockDoor() {
		System.out.println("Door is unlocked");
	}
	
	@Override
	public void lockDoor() {
		System.out.println("Door is locked");
	}
	
	@Override
	public void doorBuzzerOn() {
		System.out.println("Buzzer ON");
	}
	
	@Override
	public void doorBuzzerOff() {
		System.out.println("Buzzer OFF");
	}
	
	@Override
	public void redLedOn() {
		System.out.println("Red LED ON ");
	}
	
	@Override
	public void redLedOff() {
		System.out.println("Red LED OFF");
	}
	
	@Override
	public void greenLedOn() {
		System.out.println("Green LED ON");
	}
	
	@Override
	public void greenLedOff() {
		System.out.println("Green LED OFF");
	}
	
}
