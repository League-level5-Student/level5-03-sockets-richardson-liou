package _02_Chat_Application;

import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {
	String ip;
	int port;

	Socket connection;
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel messages = new JLabel();
	JButton button = new JButton();
	JTextField textf = new JTextField();


	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;

	}
	
	public void start() {
		panel.setSize(new Dimension(500,100));
		textf.setColumns(10);
		panel.add(messages);
		panel.add(button);
		button.setText("Send");
		frame.add(panel);
		panel.add(textf);
		frame.setSize(new Dimension(500,800));
		frame.setVisible(true);
		
		try {
			connection = new Socket(ip,port);
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();
			while (connection.isConnected()) {
				try {
					Object message = is.readObject();
					JOptionPane.showMessageDialog(null, message);
					System.out.println(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}
	
	public void SendMessage(String str) {
		try {
			if (os != null) {
				os.writeObject(str + ".");
				messages.setText(messages.getText() + "You said, " + str);
				os.flush();
			} else {
				JOptionPane.showMessageDialog(null, "Error");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Sending message");
	}
	
}
