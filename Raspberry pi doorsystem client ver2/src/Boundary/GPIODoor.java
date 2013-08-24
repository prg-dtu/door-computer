package Boundary;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GPIODoor implements GPIODoorI {
	final GpioController gpio;
    final GpioPinDigitalOutput doorLock;
    final GpioPinDigitalOutput doorBuzzer;
    final GpioPinDigitalOutput doorRedLed;
    final GpioPinDigitalOutput doorGreenLed;
    final GpioPinDigitalInput doorSensor;
    
	private class dooropener extends Thread{
		@Override
		public void run() {			
			unlockDoor();
			doorBuzzerOn();
			redLedOff();
			greenLedOn();
			try {Thread.sleep(3000);} catch (InterruptedException e1) {}
			lockDoor();
			doorBuzzerOff();
			redLedOn();
			greenLedOff();
			try{ Thread.sleep(5000); }catch(InterruptedException e){}
		}
		
	}
	dooropener door = new dooropener();
	
	public GPIODoor(){
		// create gpio controller
        gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pin and turn on
        doorLock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "DoorLock", PinState.LOW);
        System.out.println("Door Should be locked");
        
     // provision gpio pin #02 as an input pin and set pullDown resistor
//        doorSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, "DoorSensor", PinPullResistance.PULL_DOWN);
     
     // provision gpio pin #04 as an output pin and turn on
        doorBuzzer = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "DoorBuzzer", PinState.LOW);
        System.out.println("Buzzer");
        
     // provision gpio pin #05 as an output pin and turn on
        doorGreenLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "DoorGreenLed", PinState.HIGH);
        System.out.println("GreenLed should be off");
        
     // provision gpio pin #06 as an output pin and turn on
        doorRedLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "DoorRedLed", PinState.LOW);
        System.out.println("RedLed should be off");
        
     // provision gpio pin #07 as an output pin and turn on
        doorSensor = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, "DoorSensor", PinPullResistance.PULL_DOWN);
	}
	
	@Override
	public boolean isDoorOpen() {
       return doorSensor.isHigh();
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
		doorLock.high();
	}
	
	@Override
	public void lockDoor() {
		doorLock.low();
	}
	
	@Override
	public void doorBuzzerOn() {
		doorBuzzer.high();
	}
	
	@Override
	public void doorBuzzerOff() {
		doorBuzzer.low();
	}
	
	@Override
	public void redLedOn() {
		doorRedLed.low();
	}
	
	@Override
	public void redLedOff() {
		doorRedLed.high();
	}
	
	@Override
	public void greenLedOn() {
		doorGreenLed.low();
	}
	
	@Override
	public void greenLedOff() {
		doorGreenLed.high();
	}
	
}
