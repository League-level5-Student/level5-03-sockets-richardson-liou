package _02_Chat_Application;

import java.awt.Dimension;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Server {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	static JLabel messages = new JLabel();
	JButton button = new JButton();
	JTextField textf = new JTextField();
	int port;

	ServerSocket server;
	Socket connection;


	ObjectOutputStream os;
	ObjectInputStream is;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() {
		panel.setSize(new Dimension(500,100));
		textf.setColumns(10);
		panel.add(messages);
		panel.add(button);
		panel.add(textf);
		button.setText("Send");
		frame.add(panel);
		frame.setSize(new Dimension(500, 800));
		frame.setVisible(true);
		
		try {
			server = new ServerSocket(port, 5300);

			connection = server.accept();

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			while (connection.isConnected()) {
				try {
					Object message = is.readObject();
					JOptionPane.showMessageDialog(null, "Client: " + message);
					System.out.println(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SendMessage(String str) {
	    try {
	        if (os != null) {
	            os.writeObject(str);
	            messages.setText("<html>" + messages.getText() + "<br>You: " + str + "</html>");
	            os.flush();
	        } else {
	            JOptionPane.showMessageDialog(null, "Error");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!";
		}
	}

	public int getPort() {
		return port;
	}
	

		
	}
	

