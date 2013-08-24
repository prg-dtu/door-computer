package Boundary;

public class GPIODoorSySoTest {
	private static GPIODoorI doorsys = new GPIODoor();
	public static void main(String[] args){
		while(true){
			//		doorsys.unlockDoor5sec();
			doorsys.unlockDoor();
			doorsys.doorBuzzerOn();
			doorsys.redLedOff();
			doorsys.greenLedOn();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			doorsys.lockDoor();
			doorsys.doorBuzzerOff();
			doorsys.redLedOn();
			doorsys.greenLedOff();

			System.out.println("isDoor open: " + doorsys.isDoorOpen() );
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
