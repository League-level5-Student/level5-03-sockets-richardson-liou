package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;
	private ChatApp chatApp;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port, ChatApp chatApp) {
		this.ip = ip;
		this.port = port;
		this.chatApp = chatApp;

	}
	
	public void start() {
		try {
			connection = new Socket(ip,port);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				JOptionPane.showMessageDialog(null, is.readObject());
				System.out.println(is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage (String message) {
		try {
			if (os != null) {
				os.writeObject("Client: " + message);
				os.flush();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void receiveMessage () {
		if(connection.isConnected()) {
			try {
				os.flush();
				String message = (String) is.readObject();
				String message2 = (String) is.readObject();
				System.out.println(message + " " + message2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
			
	}
}
}
