package Design;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Login extends JFrame {
	public static Login clients;
	public static Clip clip;
	public static String pass_id;
	JButton button1,button2,button3;
	private JPanel contentPane;
	TextField textField,textField_1;
	ImageIcon icon1,icon2,icon3,icon4,icon5,icon6,icon7,icon8,icon9;
	public static BufferedReader in;
	public static PrintWriter out;
	public static BufferedReader input;
	public static PrintWriter output;
	public void run() throws IOException {
		// Make connection and initialize streams
		Socket socket = new Socket("127.0.0.1", 9000);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		Socket socket1 = new Socket("127.0.0.1", 8001);
		input = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
		output = new PrintWriter(socket1.getOutputStream(), true);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		clients = new Login();
		clients.run();
		clients.sound("bgm/Opening_bgm.wav", true);
		clients.setVisible(true);
		clients.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Create the frame.
	 */

	public Login() {
		icon1 = new ImageIcon("image/main_back.png");
		icon2 = new ImageIcon("image/Login_title.png");
		icon3 = new ImageIcon("image/Login.png");
		icon4 = new ImageIcon("image/create_account.png");
		icon5 = new ImageIcon("image/cancle.png");
		icon6 = new ImageIcon("image/PW.png");
		icon7 = new ImageIcon("image/ID.png");
		icon8 = new ImageIcon("image/Eureka_info.png");
		icon9 = new ImageIcon("image/Login_left.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1200, 800);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon1.getImage(),0,0,1200,800,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon2.getImage(),0,0,694,164,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(424, 56, 694, 164);
		contentPane.add(panel);
		textField_1 = new TextField();
		textField_1.setColumns(20);
		textField_1.setBounds(49, 303, 255, 38);
		textField_1.setEchoChar('*');
		contentPane.add(textField_1);
		
		Image originImg = icon3.getImage();
		Image changedImg= originImg.getScaledInstance(334, 64, Image.SCALE_SMOOTH );
		ImageIcon Icon = new ImageIcon(changedImg);
		JButton button1 = new JButton(Icon);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.setFocusPainted(false);
		button1.setBounds(580, 303, 334, 64);
		contentPane.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button1)) {
					String id=textField.getText().trim();
					String pw =textField_1.getText().trim();
					try {
						out.println("[login]");
						out.println(id);
						out.println(pw);
						int line = in.read();
						if(line==49)
						{
							new MainMenu().setVisible(true);
							setVisible(false);
						}
						else
							System.out.println("Error");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		Image originImg1 = icon4.getImage();
		Image changedImg1= originImg1.getScaledInstance(334, 64, Image.SCALE_SMOOTH );
		ImageIcon Icon1 = new ImageIcon(changedImg1);
		JButton button2 = new JButton(Icon1);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setFocusPainted(false);
		button2.setBounds(580, 427, 334, 64);
		contentPane.add(button2);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button2)) {
					new member().setVisible(true);
					setVisible(false);
				}
			}
		});
		
		Image originImg2 = icon5.getImage();
		Image changedImg2= originImg2.getScaledInstance(366, 64, Image.SCALE_SMOOTH );
		ImageIcon Icon2 = new ImageIcon(changedImg2);
		JButton button3 = new JButton(Icon2);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setFocusPainted(false);
		button3.setBounds(580, 574, 360, 64);
		contentPane.add(button3);
		
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(button3)) {
					setVisible(false);
				}
			}
		});
		
		JPanel panel_3 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon9.getImage(),0,0,380,673,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_3.setBounds(35, 52, 361, 673);
		panel_3.setLayout(null);
		contentPane.add(panel_3);
		
		
		JPanel panel_1 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon6.getImage(),0,0,124,60,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_1.setBounds(14, 179, 124, 60);
		panel_3.add(panel_1);
		textField = new TextField();
		textField.setBounds(14, 119, 253, 38);
		panel_3.add(textField);
		textField.setColumns(10);
		Font f1 = new Font("휴먼모음T",Font.PLAIN,20);
		textField.setFont(f1);
		
		JPanel panel_2 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon7.getImage(),0,0,124,58,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_2.setBounds(14, 55, 124, 58);
		panel_3.add(panel_2);
		JPanel panel_4 = new JPanel(){
			public void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(icon8.getImage(),0,0,333,347,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel_4.setBounds(14, 314, 333, 347);
		panel_3.add(panel_4);
	}
	public void sound(String file, boolean Loop){
		//사운드재생용메소드
		//메인 클래스에 추가로 메소드를 하나 더 만들었습니다.
		//사운드파일을받아들여해당사운드를재생시킨다.
		try {
			if(clip!=null)
				clip.stop();
		AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
		clip = AudioSystem.getClip();
		clip.open(ais);
		clip.start();
		
		if ( Loop) clip.loop(-1);
		//Loop 값이true면 사운드재생을무한반복시킵니다.
		//false면 한번만재생시킵니다.
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

}