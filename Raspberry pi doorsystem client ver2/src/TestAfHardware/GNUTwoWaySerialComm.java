package TestAfHardware;


import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class GNUTwoWaySerialComm {

	void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
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

				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();

				(new Thread(new SerialReader(in))).start();
				(new Thread(new SerialWriter(out))).start();

			} else {
				System.out
						.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	public static class SerialReader implements Runnable {

		InputStream in;

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void run() {
			byte[] buffer = new byte[1024];
			int len = -1;
			try {
				String str = "";
				while ((len = this.in.read(buffer)) > 0) {
					System.out.println(new String(buffer, 0, len));

					str += new String(buffer, 0, len);

					if (this.in.available() < 1) {
						try {
							PrintWriter out = new PrintWriter( new BufferedWriter(new FileWriter("/home/ok/outfilename.txt", true)));
							out.println(str);
							out.close();
							String pass = "%JOHANSEN^KASPER BANTZ             S\\NDRE FASANVEJ 91 2 TH           1012500?;9208100403118811911003786084101010311?";
							if (str.startsWith(pass)) {
								System.out.println("du logget ind");
							} else {
								System.out.println("sorry: \n" + pass + "\n" + str);
							}
							System.out.println();
							System.out
									.println("Should have been written to file");
							str = "";
						} catch (IOException e) {
							System.out.println("oh noes! " + e.getMessage()
									+ " -> " + e.getStackTrace());
						}
					}
					try{ Thread.sleep(10); }catch(InterruptedException e){}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static class SerialWriter implements Runnable {

		OutputStream out;

		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		public void run() {
			try {
				int c = 0;
				while ((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			(new GNUTwoWaySerialComm()).connect("/dev/ttyUSB0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}