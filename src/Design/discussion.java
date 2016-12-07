package Design;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Design.Login;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class discussion extends JFrame implements Runnable
{
	JPanel contentPane;
	ImageIcon b1,b2,b3,b4,b5;
	JTextField textField;
	JTextArea messageArea;
	String id;
	/**
	 * Launch the application.
	 */
	Login client = new Login();
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	public static void main(String[] args) throws Exception 
	{
			discussion check = new discussion();
		new Thread(check).start();
			check.setVisible(true);
	}
	discussion()
	{	

		b1 = new ImageIcon("image/discussion_back.jpg");
		b2 = new ImageIcon("image/discussion_info1.png");
		b3 = new ImageIcon("image/exit.png");
		b4 = new ImageIcon("image/chat_border.png");
		b5 = new ImageIcon("image/discussion_title.png");
		setBounds(400, 100, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1200, 800);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(b1.getImage(),0,0,1200,800,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		contentPane.setBounds(0, 0, 1200, 800);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panel = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(b2.getImage(),0,0,438,471,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(691, 158, 438, 471);
		contentPane.add(panel);

		Image originImg = b3.getImage();
		Image changedImg= originImg.getScaledInstance(207, 64, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JButton button1 = new JButton(Icon);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.setBounds(922, 661, 207, 64);
		contentPane.add(button1);

		panel_1 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(b4.getImage(),0,0,567,567,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_1.setBounds(67, 158, 567, 567);
		panel_1.setLayout(null);
		contentPane.add(panel_1);

		textField = new JTextField();
		textField.setBounds(38, 483, 495, 55);
		panel_1.add(textField);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 50, 495, 396);
		panel_1.add(scrollPane);
		messageArea = new JTextArea();
		scrollPane.setViewportView(messageArea);
		messageArea.setEditable(false);
		
		panel_2 = new JPanel(){
			public void paintComponent(Graphics g){
			Dimension d = getSize();
			g.drawImage(b5.getImage(),0,0,1064,115,null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
		panel_2.setBounds(65, 12, 1064, 115);
		contentPane.add(panel_2);

		textField.addActionListener(new ActionListener(){
			/**
			 * Responds to pressing the enter key in the textfield by sending
			 * the contents of the text field to the server.Then clear
			 * the text area in preparation for the next message.
			 */
			public void actionPerformed(ActionEvent e) {
				client.output.println(textField.getText());
				textField.setText("");
			}
		});
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button1)) {
					new GameMode().setVisible(true);
					setVisible(false);
				}
			}
		});

		setVisible(true);
	}
	/**
	 * Create the frame.
	 */  
	public void run() 
	{
		System.out.println("1");
		try {
			client.out.println("[Chat]");
			System.out.println("2");
			String id ="";
			id = client.in.readLine();
			System.out.println(id);
			while (true) {
				//Read line from server
				String line = client.input.readLine();
				System.out.println(line);
				if (line.startsWith("SUBMITNAME")) {
					client.output.println(id);
				} else if (line.startsWith("NAMEACCEPTED")) {
					textField.setEditable(true); 
				}else if(line.startsWith("FIRST")){
					messageArea.append(line.substring(5) + "\n");
				}else if (line.startsWith("MESSAGE")) {
					messageArea.append(line.substring(8) + "\n");
				}else if(line.startsWith("ENTRANCE")){ //Entrance prototype
					messageArea.append(line.substring(9) + "\n");
				}else if(line.startsWith("EXIT")){ //Exit prototype
					messageArea.append(line.substring(5) + "\n");
				}else if(line.startsWith("WHISPER")){ //whisper prototype
					messageArea.append(line.substring(8)+ "\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
