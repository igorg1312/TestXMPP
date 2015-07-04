package igor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;


public class Xmpp {

	public static void main(String[] args) throws XMPPException, IOException{
		XmppManag c = new XmppManag();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String msg;
		
		XMPPConnection.DEBUG_ENABLED=true;
		
		c.login("testuser2", "igorigor","192.168.1.7", 5222, "pegasus.domain");
		c.displayBuddyList();
		
		System.out.println("----------");
		
		System.out.println("Who do you want to talk to? Type full email");
		String talkTo = br.readLine();
		
		System.out.println("----------");
		
		System.out.println("----------");
		System.out.println("all messages will be sent to " + talkTo);
		System.out.println("Enter your message in the console");
		System.out.println("-----\n");
		
		while(!(msg=br.readLine()).equals("bye")) {
			c.sendMessage(msg, talkTo);
		}
		
		c.disconnect();
		System.exit(0);
		
		
	}

}
