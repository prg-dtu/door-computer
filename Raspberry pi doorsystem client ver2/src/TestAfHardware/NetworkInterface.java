package TestAfHardware;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkInterface {

	private static String getHostName()
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            //System.out.println(addr.getHostAddress());
            return hostname;
        }catch(UnknownHostException e)
        {
             //throw Exception
        }
        return null;
    }
	
	private static String getIPaddress()
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            //String hostname = addr.getHostName();
            return addr.getHostAddress();
            //return hostname;
        }catch(UnknownHostException e)
        {
             //throw Exception
        }
        return null;
    }

	public static void main(String args[]) {
		System.out.println(getHostName() + "\n" + getIPaddress());
	}

}
