package Boundary;

public interface GPIODoorI {
	public boolean isDoorOpen(); // return true if the door is open
	public void unlockDoor5sec();
	public void unlockDoor();
	public void lockDoor();
	void redLedOff();
	void redLedOn();
	void greenLedOn();
	void greenLedOff();
	void doorBuzzerOn();
	void doorBuzzerOff();
}