package Boundary;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;

public class CardReader implements CardReaderI {

	private InputStream in;
	
	public CardReader(String portName) throws IOException, PortInUseException, UnsupportedCommOperationException, NoSuchPortException {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			int timeout = 2000;
			CommPort commPort = portIdentifier.open(this.getClass().getName(),
					timeout);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				in = serialPort.getInputStream();


			} else {
				System.out
						.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	@Override
	public String getPasword() {
		byte[] buffer;
		int len = -1;
		buffer = new byte[1024];
		try {
			String str = "";
			if (this.in.available() > 0) {
				while ( this.in.available() > 0 && (len = this.in.read(buffer)) > 0 ) {
					str += new String(buffer, 0, len);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
					}
				}
				//virker på windows 8 
				str = str.substring(0, str.length()-2);
				return Sha1Encryption.encryptPassword(str);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
