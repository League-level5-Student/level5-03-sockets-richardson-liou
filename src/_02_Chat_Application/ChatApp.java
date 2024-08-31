package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton send = new JButton("Send");
	JTextArea texta = new JTextArea();
	JTextField textf = new JTextField(20);
	
	Server server;
	Client client;
	
	int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "",
			JOptionPane.YES_NO_OPTION);
		
		public void start() {
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8081);
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			server.button.addActionListener((e)-> {
				server.SendMessage(server.textf.getText());
			});
			server.start();
		} 
		else {
			String ip = JOptionPane.showInputDialog("Enter your ip adress");
			String prt = JOptionPane.showInputDialog("Enter Port number");
			int port = Integer.parseInt(prt);
			client = new Client(ip, port);
			client.button.addActionListener((e)-> {
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
