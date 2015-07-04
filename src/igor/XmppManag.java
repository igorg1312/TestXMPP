package igor;

import java.util.Collection;


import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;







public class XmppManag implements MessageListener {

	XMPPConnection connection;
	
	
	public void login(String userName, String password, String server, int port, String domain) throws XMPPException {
		ConnectionConfiguration config = new ConnectionConfiguration(server, port, domain);
		connection = new XMPPConnection(config);
		
		connection.connect();
		SASLAuthentication.supportSASLMechanism("PLAIN", 0);
		connection.login(userName, password);
	}
	
	
	public void sendMessage(String message, String to) throws XMPPException{
		Chat chat = connection.getChatManager().createChat(to, this);
		chat.sendMessage(message);
	}

	
	public void displayBuddyList(){
		Roster roster = connection.getRoster();
		Collection<RosterEntry> entries = roster.getEntries();
		
		System.out.println("\n\n" + entries.size() + " buddy(ies):");
		for (RosterEntry r : entries) {
			System.out.println(r.getUser());
		}
	}

	
	public void disconnect(){
		connection.disconnect();
	}
	
	
	public void processMessage(Chat chat, Message message){
		if (message.getType() == Message.Type.chat){
			System.out.println(chat.getParticipant() + " says: " + message.getBody());
		try {
			chat.sendMessage(message.getBody() + " echo");
			
		  }catch (XMPPException ex){
			  System.out.println(ex);
		  }
		}
	}
	
}
