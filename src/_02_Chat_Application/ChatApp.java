package _02_Chat_Application;

import java.util.Random;

import javax.swing.JOptionPane;


/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	Server server;
	Client client;
	
	int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "",
			JOptionPane.YES_NO_OPTION);
		
		public void start() {
			if (response == JOptionPane.YES_OPTION) {
				Random ran = new Random();
			    server = new Server(999+ran.nextInt(8881));
			    
			    JOptionPane.showMessageDialog(null,
			            "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			    server.button.addActionListener((e) -> {
			        server.SendMessage(server.textf.getText());
			    });
			    server.start();
			} else {
			    String ip = JOptionPane.showInputDialog("Enter your IP address");
			    String prt = JOptionPane.showInputDialog("Enter Port number");
			    int port = Integer.parseInt(prt);
			    client = new Client(ip, port);
			    client.button.addActionListener((e) -> {
			        client.SendMessage(client.textf.getText()); 
			    });
			    client.start();
			}
		}
	
	
	public static void main(String[] args) {
		ChatApp ca = new ChatApp();
		ca.start();
	}
}
