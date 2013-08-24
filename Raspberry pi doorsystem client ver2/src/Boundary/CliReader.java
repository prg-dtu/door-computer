package Boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliReader implements CardReaderI {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public CliReader() {
		System.out.println("Venter på input:");
	}
	
	@Override
	public String getPasword() {
		try {
			if (in.ready()){
				return Sha1Encryption.encryptPassword(in.readLine());
			}
		} catch (IOException e) {}
		return null;
	}	
}
